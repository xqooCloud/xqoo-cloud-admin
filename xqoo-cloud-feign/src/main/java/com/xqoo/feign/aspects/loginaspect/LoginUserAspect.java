package com.xqoo.feign.aspects.loginaspect;

import com.xqoo.common.constants.SystemPublicConstant;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.core.utils.ServletUtils;
import com.xqoo.feign.service.auth.AuthFeign;
import com.xqoo.feign.utils.FeignReturnDataGzip;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author gaoyang by 2020-12-05
 * 注入当前登录用户
 **/
@Aspect
@Component
public class LoginUserAspect {

    @Autowired
    private AuthFeign authFeign;

    // 配置织入点
    @Pointcut("execution(public * com.xqoo..*(.., @com.xqoo.feign.annotations.LoginUser (*), ..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void beforeMethod(JoinPoint joinPoint) {
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Object returnModel;
        //获取请求头
        HttpServletRequest request = ServletUtils.getRequest();
        String token = request.getHeader(SystemPublicConstant.AUTH_HEADER_KEY_NAME);
        //获取目标方法的参数信息
        Object[] obj = pjp.getArgs();
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] instanceof CurrentUser) {
                if(StringUtils.isEmpty(token)){
                    obj[i] = new CurrentUser();
                    break;
                }
                obj[i] = FeignReturnDataGzip.Unzip(authFeign.getLoginUserInfo(token), CurrentUser.class);
                break;
            }
        }
        returnModel = pjp.proceed(obj);
        return returnModel;
    }

}
