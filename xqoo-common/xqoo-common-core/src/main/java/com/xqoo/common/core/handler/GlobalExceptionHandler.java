package com.xqoo.common.core.handler;

import cn.hutool.core.collection.CollUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.base.Joiner;
import com.xqoo.common.constants.CustomExceptionConstant;
import com.xqoo.common.core.exception.AccessDenyException;
import com.xqoo.common.core.exception.BusinessException;
import com.xqoo.common.core.exception.SystemException;
import com.xqoo.common.core.exception.TokenAccessExpiredException;
import com.xqoo.common.core.utils.JacksonUtils;
import com.xqoo.common.entity.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;

/**
 * @author mumu
 * @date 2019/11/12 下午2:57
 * 全局异常拦截器
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    /**
     * 拦截业务运行时异常
     */
    @ExceptionHandler(BusinessException.class)
    public ResultEntity BussinessError(BusinessException e) {
        e.printStackTrace();
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }
        log.error("[系统业务运行异常]:{}", e.getErrorMessage());
        return new ResultEntity<>(HttpStatus.SERVICE_UNAVAILABLE,"运行中断：" + e.getErrorMessage(), e.getData());
    }

    /**
     * 需要登录异常
     * @param e
     * @return
     */
    @ExceptionHandler(AccessDenyException.class)
    public ResultEntity AccessDenyError(AccessDenyException e) {
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }
        log.warn("[此操作需要登录]:简述：{}", e.getErrorMessage());
        return new ResultEntity<>(HttpStatus.UNAUTHORIZED,"权限错误：" + e.getErrorMessage(), e.getData());
    }

    /**
     * token失效异常
     * @param e
     * @return
     */
    @ExceptionHandler(TokenAccessExpiredException.class)
    public ResultEntity TokenAccessExpiredError(TokenAccessExpiredException e) {
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }
        log.warn("[授权过期]:简述：{}", e.getErrorMessage());
        return new ResultEntity<>(HttpStatus.FORBIDDEN,"登录已失效" + e.getErrorMessage(), e.getData());
    }

    /**
     * 代码执行异常
     * @param e
     * @return
     */
    @ExceptionHandler(SystemException.class)
    public ResultEntity SystemError(SystemException e) {
//        e.printStackTrace();
//        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
//            throw e;
//        }
        log.error("[系统代码执行异常]:简述：{}，错误详细：{}", e.getErrorMessage(), e.getDetailMessage());
        return new ResultEntity<>(HttpStatus.SERVICE_UNAVAILABLE,"内部执行中断：" + e.getErrorMessage(), e.getData());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultEntity requestBodyError(HttpMessageNotReadableException e){
        log.error("[请求参数异常]:请求体为空，无法执行，简述：{}，参数：{}", e.getClass().getSimpleName(), e.getHttpInputMessage());
        return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE,"请求数据为空，无法执行此项操作");
    }
    /**
     * 实体字段缺失异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultEntity validJsonError(MethodArgumentNotValidException e){
        List<ObjectError> errorList = e.getBindingResult().getAllErrors();
        if(CollUtil.isNotEmpty(errorList)){
            try{
                List<ObjectNode> list = new ArrayList<>();
                StringBuilder sb = new StringBuilder();
                for (ObjectError objectError : errorList) {
                    ObjectNode json = JacksonUtils.createObjectNode();
                    JsonNode node = JacksonUtils.transferToJsonNode(objectError);
                    json.put(CustomExceptionConstant.VALID_ERR_MSG_KEY_NAME, node.get(CustomExceptionConstant.VALID_ERR_JSON_MSG_KEY_NAME).asText());
                    json.put(CustomExceptionConstant.VALID_ERR_FIELD_KEY_NAME, node.get(CustomExceptionConstant.VALID_ERR_FIELD_KEY_NAME).asText());
                    sb.append("[").append(node.get(CustomExceptionConstant.VALID_ERR_FIELD_KEY_NAME).asText()).append("]");
                    sb.append(node.get(CustomExceptionConstant.VALID_ERR_JSON_MSG_KEY_NAME).asText());
                    sb.append("\n");
                    list.add(json);
                }
                if(CollUtil.isNotEmpty(list)){
                    log.error("[json请求参数异常]:{}", list.toString());
                    return new ResultEntity<>(HttpStatus.METHOD_NOT_ALLOWED, sb.toString(), list);
                }
            }catch (Exception ex){
                log.error("[全局异常管理器]:{},信息：{}", ex.getClass().getSimpleName(), ex.getMessage());
                return new ResultEntity<>(HttpStatus.INTERNAL_SERVER_ERROR, "系统运行错误：" + ex.getMessage());
            }
        }

        log.error("[json请求参数异常]");
        return new ResultEntity<>(HttpStatus.METHOD_NOT_ALLOWED, "字段值缺失", Collections.emptyList());

    }

    /**
     * parameter字段缺失异常
     * @param e
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResultEntity validParamError(MissingServletRequestParameterException e){
        log.error("[param请求参数异常]:字段：{}，字段类型：{} 缺失", e.getParameterName(), e.getParameterType());
        return new ResultEntity<>(HttpStatus.METHOD_NOT_ALLOWED, "字段【" + e.getParameterName() + "】为必填字段");

    }

    /**
     * parameter字段未传值异常
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResultEntity validParamError(ConstraintViolationException e){
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        if(CollUtil.isNotEmpty(constraintViolations)){
            try{
                List<ObjectNode> list = new ArrayList<>();
                for(ConstraintViolation constraintViolation :constraintViolations){
                    ObjectNode json = JacksonUtils.createObjectNode();
                    json.put(CustomExceptionConstant.VALID_ERR_MSG_KEY_NAME, constraintViolation.getMessage());
                    constraintViolation.getPropertyPath().forEach(item -> {
                        if(CustomExceptionConstant.VALID_PARAM_FIELD_NAME.equals(item.getKind().name())){
                            json.put(CustomExceptionConstant.VALID_ERR_FIELD_KEY_NAME, item.getName());
                        }
                    });
                    list.add(json);
                }
                if(CollUtil.isNotEmpty(list)){
                    log.error("[param请求参数异常]:{}", list.toString());
                    return new ResultEntity<>(HttpStatus.METHOD_NOT_ALLOWED, "请完善表单信息", list);
                }
            }catch (Exception ex){
                log.error("[全局异常管理器]:{},信息：{}", ex.getClass().getSimpleName(), ex.getMessage());
                return new ResultEntity<>(HttpStatus.INTERNAL_SERVER_ERROR, "系统运行错误：" + ex.getMessage());
            }


        }
        log.info("[param请求参数异常]:");
        return new ResultEntity<>(HttpStatus.METHOD_NOT_ALLOWED, "字段值缺失", Collections.emptyList());
    }

    /**
     * 请求类型错误
     * @param e
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResultEntity requestTypeError(HttpRequestMethodNotSupportedException e){
        log.warn("[请求类型错误]:当前类型：{}，支持的类型为：{}", e.getMethod(),
                e.getSupportedMethods());
        String[] arr = {};
        if(e.getSupportedMethods() != null){
            arr = e.getSupportedMethods();
        }
        return new ResultEntity<>(HttpStatus.METHOD_NOT_ALLOWED, "当前接口仅支持" + Joiner.on(",").join(arr) + "请求方式");

    }

    /**
     * 请求类型错误
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResultEntity paramTypeError(MethodArgumentTypeMismatchException e){
        log.warn("[参数类型类型错误]:字段：{}，的值：{}类型转换失败，此方法只支持的类型为：{}", e.getName(), e.getValue(),
                Objects.requireNonNull(e.getRequiredType()).getSimpleName());

        return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "当前接口仅支持" + Objects.requireNonNull(e.getRequiredType()).getSimpleName()+ "型参数，字段【" +
                e.getName() + "】的值不能转换为此类型");

    }
    /**
     * 系统抛出异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResultEntity SystemError(Exception e) {
        e.printStackTrace();
        log.error("[系统运行异常]:异常原因：{}，异常信息：{}", e.getClass().getSimpleName(), e.getMessage());
        return new ResultEntity<>(HttpStatus.INTERNAL_SERVER_ERROR,"系统运行错误：" + e.getClass().getSimpleName());
    }
}
