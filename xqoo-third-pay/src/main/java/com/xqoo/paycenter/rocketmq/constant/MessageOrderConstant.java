package com.xqoo.paycenter.rocketmq.constant;

public class MessageOrderConstant {

    /**
     * 刷新支付参数命令常量
     */
    public static class PayConfigRefreshOrder{

        /**
         * 刷新支付宝支付参数
         */
        public final static String ALI_CONFIG_REFRESH = "AliPayConfigRefresh";

        /**
         * 刷新微信支付参数
         */
        public final static String WX_CONFIG_REFRESH = "WxPayConfigRefresh";
    }
}
