package com.xqoo.filemanager.rocketmq.constant;

public class MessageFileManagerConstant {

    /**
     * 刷新支付参数命令常量
     */
    public static class FileConfigRefreshOrder{

        /**
         * 刷新支付宝支付参数
         */
        public final static String ALI_OSS_CONFIG_REFRESH = "aliOssRefresh";

        /**
         * 刷新微信支付参数
         */
        public final static String TENCENT_COS_CONFIG_REFRESH = "tencentRefresh";
    }
}
