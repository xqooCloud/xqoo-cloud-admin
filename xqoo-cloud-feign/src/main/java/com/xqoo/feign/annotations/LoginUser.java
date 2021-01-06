package com.xqoo.feign.annotations;

import java.lang.annotation.*;

/**
 * @author mumu
 * @date 2019/11/12 下午5:42
 * 获取当前登录用户的注解  使用方式  xxx(@LoginUser CurrentUser user) 会自动注入当前的登录用户
 **/
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {

}
