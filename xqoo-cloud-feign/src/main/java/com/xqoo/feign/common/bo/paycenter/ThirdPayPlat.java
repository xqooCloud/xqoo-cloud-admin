package com.xqoo.feign.common.bo.paycenter;


/**
 * @ClassName CommonConstants
 * @Description 支付平台枚举类
 * @Author gaoyang
 * @Date 2019/8/10 10:59
 * @Verison 1.0
 **/
public enum ThirdPayPlat {
    WEIXIN("微信支付","WxPay"),
    ALIBABA("支付宝支付","AliPay"),
    UNION("银联支付","UnionPay "),
    IOS("苹果内购支付","IOSApp"),
    UNDERLINE("线下支付","UnderLine");

    private String key;
    private String value;

    ThirdPayPlat(String key, String value) {
        this.key = key;
        this.value = value;

    }

    public String getKey() { return key; }

    public String getValue() { return value; }
}
