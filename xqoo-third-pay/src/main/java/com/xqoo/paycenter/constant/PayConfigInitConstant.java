package com.xqoo.paycenter.constant;

/**
 * 初始化支付配置时一些默认值
 */
public class PayConfigInitConstant {

    /**
     * 支付宝的支付参数默认值
     */
    public static class AliPayConfigDefult{
        public final static String APPID = "none";
        // 商户私钥
        public final static String PRIVATE_KEY = "none";
        // 支付宝公钥
        public final static String PUBLIC_KEY = "none";
        public final static String CHARSET = "UTF-8";
        public final static String FROMAT = "JSON";
        // 支付网关
        public final static String GATEWAY_URL = "https://openapi.alipay.com/gateway.do";
        public final static String SIGN_TYPE = "RSA2";
        public final static String PRODUCT_CODE = "QUICK_MSECURITY_PAY";
        //支持退款的天数
        public final static String TIMEOUT_EXPRESS = "15d";
        // 默认返回网页
        public final static String RETURN_URL = "https://wwww.exqoo.com";
        // 支付结果回调地址
        public final static String NOTIFY_URL = "none";
        // 支付商家的名字
        public final static String SUBJECT = "贵州兴黔科技有限公司";
    }

    /**
     * 微信的支付参数默认值
     */
    public static class WxPayConfigDefult{
        // APPid
        public final static String APPID = "none";
        // (APP)支付时的appId
        public final static String APP_APPID = "none";
        // (公众号)支付时的appId
        public final static String OA_APPID = "none";
        // (小程序)支付时的appId
        public final static String SP_APPID = "none";
        // 商户号
        public final static String MCHID = "none";
        // 公钥
        public final static String API_KEY = "NONE";
        // 私钥 （PC)
        public final static String PC_MCH_KEY = "NONE";
        // 私钥 （APP)
        public final static String APP_MCH_KEY = "NONE";
        // 私钥 （公众号)
        public final static String OA_MCH_KEY = "NONE";
        // 私钥 （小程序)
        public final static String SP_MCH_KEY = "NONE";

        public final static String FEE_TYPE = "CNY";
        public final static String SPBILL_CREATE_IP = "";
        public final static String TRADE_TYPE = "NATIVE";
        // 支付网关
        public final static String GATEWAY_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        // 退款网关
        public final static String REFUND_GATEWAY_URL = "https://api.mch.weixin.qq.com/sandboxnew/pay/refund";
        // 微信证书路径
        public final static String SSL_PATH = "/home/file/wxPaySSL/apiclient_cert.p12";
        // 微信证书密码
        public final static String SSL_PASSWORD = "none";
        // 商户付款到个人网关
        public final static String PAY_TO_PERSONAL_GATEWAY = "https://api.mch.weixin.qq.com/mmpaymkttransfers/promotion/transfers";
        // 支付结果回调地址
        public final static String NOTIFY_URL = "none";
        // ip
        public final static String IP = "";
        // 支付公司
        public final static String SUBJECT = "贵州兴黔科技有限公司";

    }
}
