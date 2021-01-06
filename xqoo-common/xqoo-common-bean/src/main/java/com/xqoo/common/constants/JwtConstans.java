package com.xqoo.common.constants;

public class JwtConstans {

    public static class ExceptionTypeConstants{

        // token失效异常
        public static final String TOKEN_EXPIRE_EXCEPTION = "TokenExpiredException";
    }

    public static class PayloadConstants{
        // 用户基本信息
        public static final String CUSTOM_INFO_KEY = "info";
        // 用户登录ip
        public static final String LOGIN_IP = "loginIp";
        // 用户登录时间
        public static final String LOGIN_TIME = "loginTime";
        // 用户登录环境
        public static final String LOGIN_ENV = "loginEnv";
    }

}
