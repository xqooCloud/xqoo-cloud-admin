package com.xqoo.feign.aspects.operation;


import com.alibaba.fastjson.JSON;
import com.xqoo.common.constants.SystemPublicConstant;
import com.xqoo.common.core.utils.JacksonUtils;
import com.xqoo.common.core.utils.MD5Util;
import com.xqoo.common.core.utils.ServletUtils;
import com.xqoo.feign.annotations.OperationLog;
import com.xqoo.feign.common.bo.OperationLogRecordBO;
import com.xqoo.feign.entity.SysOperationLogEntity;
import com.xqoo.feign.entity.SysOperationLogRequestInfoEntity;
import com.xqoo.feign.entity.SysOperationLogResponseInfoEntity;
import com.xqoo.feign.enums.operlog.OperationStatusEnum;
import com.xqoo.feign.enums.operlog.OperationTypeEnum;
import com.xqoo.feign.service.operlog.OperationLogFeign;
import com.xqoo.feign.utils.FeignReturnDataGzip;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@Aspect
@Component
public class OperationLogAspect {

    private final static Logger logger = LoggerFactory.getLogger(OperationLogAspect.class);

    @Autowired
    private OperationLogFeign operationLogFeign;

    // 配置织入点
    @Pointcut("@annotation(com.xqoo.feign.annotations.OperationLog)")
    public void logPointCut() {
    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "logPointCut()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult){
        handleLog(joinPoint, null, jsonResult);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e 异常
     */
    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e){
        handleLog(joinPoint, e, null);
    }

    protected void handleLog(final JoinPoint joinPoint, final Exception e, Object jsonResult){
        try{
            // 获得注解
            OperationLog controllerLog = getAnnotationLog(joinPoint);
            if (controllerLog == null) {
                return;
            }
            // 设置默认字段
            HttpServletRequest request = ServletUtils.getRequest();
            // 类名
            String className;
            // 方法名
            String methodName;
            // 方法名
            Integer operationStatus = OperationStatusEnum.SUCCESS.getKey();
            // 请求来源ip
            String ip = "未知ip";
            // 请求来源端口
            String port = "未知端口";
            // 请求来源userId,可能为空
            String userId = "unKnow";
            // 请求来源userName, 可能为空
            String userName = "未知用户";
            // 请求登录来源，未登录则为未知
            String loginSource = "未知来源";
            // 执行方法返回的数据
            String returnJson = "无返回数据";
            // 注解标明的说明
            String tips = controllerLog.tips();
            // 注解标明的注解
            String msg = controllerLog.remark();
            // 错误信息
            String errorMsg = "";
            // 注解标明的操作类型
            OperationTypeEnum operatorType = controllerLog.operatorType();
            if(jsonResult != null){
                returnJson = JacksonUtils.toJson(jsonResult);
            }
            className = joinPoint.getTarget().getClass().getName();
            methodName = joinPoint.getSignature().getName();
            if(request.getHeader(SystemPublicConstant.REMOTE_REQUEST_IP) != null){
                ip = request.getHeader(SystemPublicConstant.REMOTE_REQUEST_IP);
            }
            if(request.getHeader(SystemPublicConstant.REMOTE_REQUEST_PORT) != null){
                port = request.getHeader(SystemPublicConstant.REMOTE_REQUEST_PORT);
            }
            if(request.getHeader(SystemPublicConstant.USER_ID_HEADER_KEY_NAME) != null){
                userId = request.getHeader(SystemPublicConstant.USER_ID_HEADER_KEY_NAME);
            }
            if(request.getHeader(SystemPublicConstant.USER_NAME_HEADER_KEY_NAME) != null){
                userName = request.getHeader(SystemPublicConstant.USER_NAME_HEADER_KEY_NAME);
                userName = URLDecoder.decode(userName, StandardCharsets.UTF_8.name());
            }
            if(request.getHeader(SystemPublicConstant.USER_LOGIN_SOURCE_HEADER_KEY_NAME) != null){
                loginSource = request.getHeader(SystemPublicConstant.USER_LOGIN_SOURCE_HEADER_KEY_NAME);
            }
            SysOperationLogEntity entity = new SysOperationLogEntity();
            entity.setCreateTime(System.currentTimeMillis());
            entity.setMethodName(className + "::" + methodName);
            entity.setRequestMethod(request.getMethod());
            entity.setOperationType(operatorType.getKey());
            entity.setTipsMessage(tips);
            entity.setOperatorMessage(msg);
            entity.setRequestUrl(request.getRequestURI());
            entity.setOperatorId(userId);
            entity.setOperatorRemoteIp(ip + ":" + port);
            entity.setOperatorName(userName);
            entity.setLoginSource(loginSource);
            String logId = System.currentTimeMillis() + ip + port;
            logId = MD5Util.MD5Encode(logId, StandardCharsets.UTF_8.name());
            entity.setLogId(logId);
            // 执行发生异常，记录操作失败
            if(e != null){
                operationStatus = OperationStatusEnum.FAIL.getKey();
                errorMsg = "程序执行发生异常:异常原因[" + e.getClass().getSimpleName() + "],异常信息[" + e.getMessage() + "]";
            }
            entity.setOperationStatus(operationStatus);
            entity.setOperatorErrorMessage(errorMsg);
            SysOperationLogRequestInfoEntity requestEntity = null;
            SysOperationLogResponseInfoEntity responseInfoEntity = null;
            // 开始判断参数是否需要存放
            Object[] requestParam = joinPoint.getArgs();
            // 请求参数封装
            if(requestParam != null && controllerLog.isSaveRequestData()){
                requestEntity = new SysOperationLogRequestInfoEntity();
                requestEntity.setParentId(logId);
                if (HttpMethod.PUT.name().equals(request.getMethod()) || HttpMethod.POST.name().equals(request.getMethod())){
                    requestEntity.setMessageContent(argsArrayToString(requestParam));
                }
            }
            // 返回数据封装
            if(controllerLog.isSaveResponseData()){
                responseInfoEntity = new SysOperationLogResponseInfoEntity();
                responseInfoEntity.setParentId(logId);
                responseInfoEntity.setMessageContent(returnJson);
            }
            OperationLogRecordBO finalBo = new OperationLogRecordBO();
            finalBo.setSysOperationLogEntity(entity);
            finalBo.setSysOperationLogRequestInfoEntity(requestEntity);
            finalBo.setSysOperationLogResponseInfoEntity(responseInfoEntity);
            Boolean saved = FeignReturnDataGzip.Unzip(operationLogFeign.addHandleRecord(finalBo), Boolean.class);
            if(!saved){
                logger.warn("[系统操作日志注解]日志信息保存失败，详细请参看redis遗失记录");
                /**
                 * 这里可以补充redis的防遗漏逻辑
                 */
            }
        }catch (Exception exp) {
            // 记录本地异常日志
            logger.error("[系统操作日志注解]注解执行发生错误，异常原因：{}，异常信息：{}",
                    exp.getClass().getSimpleName(), exp.getMessage());
        }
    }

    /**
     * 是否存在注解，如果存在就获取
     */
    private OperationLog getAnnotationLog(JoinPoint joinPoint) throws Exception {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null) {
            return method.getAnnotation(OperationLog.class);
        }
        return null;
    }

    /**
     * 参数拼装
     */
    private String argsArrayToString(Object[] paramsArray){
        StringBuilder params = new StringBuilder();
        if (paramsArray != null && paramsArray.length > 0){
            for (int i = 0; i < paramsArray.length; i++){
                if (!isFilterObject(paramsArray[i])){
                    try{
                        Object jsonObj = JSON.toJSON(paramsArray[i]);
                        params.append(jsonObj.toString()).append(" ");
                    }catch (Exception e){
                        params.append("数据解析出错").append(" ");
                    }
                }
            }
        }
        return params.toString().trim();
    }

    /**
     * 判断是否需要过滤的对象。
     *
     * @param o 对象信息。
     * @return 如果是需要过滤的对象，则返回true；否则返回false。
     */
    private boolean isFilterObject(final Object o){
        return o instanceof MultipartFile || o instanceof HttpServletRequest || o instanceof HttpServletResponse;
    }
}
