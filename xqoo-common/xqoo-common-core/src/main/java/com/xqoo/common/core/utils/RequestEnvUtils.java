package com.xqoo.common.core.utils;

/**
 * 请求来源环境工具
 * @author Gaoyang by 2020-11-21
 */
public class RequestEnvUtils {

    public static String GetRequestEnv(String userAgent){
        return GetDevice(userAgent);
    }

    private static String GetDevice(String userAgent){
        if(userAgent.contains("Android")){
            return "安卓手机";
        }
        if(userAgent.contains("iPhone")){
            return "苹果手机";
        }
        if(userAgent.contains("iPad")){
            return "苹果平板";
        }
        if(userAgent.contains("Windows")){
            return "PC电脑";
        }
        if(userAgent.contains("Linux")){
            return "Linux电脑";
        }
        return "未知设备";
    }
}
