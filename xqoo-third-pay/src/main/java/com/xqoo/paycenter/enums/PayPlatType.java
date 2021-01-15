package com.xqoo.paycenter.enums;


/**
 * @ClassName CommonConstants
 * @Description 支付平台枚举类
 * @Author gaoyang
 * @Date 2019/8/10 10:59
 * @Verison 1.0
 **/
public enum PayPlatType {
    WEIXIN("微信支付","WxPay"),
    ALIBABA("支付宝支付","AliPay"),
    UNION("银联支付","UnionPay "),
    IOS("苹果内购支付","IOSApp"),
    UNDERLINE("线下支付","UnderLine");

    private String key;
    private String value;

    PayPlatType(String key, String value) {
        this.key = key;
        this.value = value;

    }

    public static String getValueByKey(String key) {
        PayPlatType[] payPlatTypeEnums = values();
        for (PayPlatType item : payPlatTypeEnums) {
            if (item.getKey().equals(key)) {
                return item.getValue();
            }
        }
        return null;
    }

    public static String getKeyByValue(String value) {
        PayPlatType[] payPlatTypeEnums = values();
        for (PayPlatType item : payPlatTypeEnums) {
            if (item.getValue().equalsIgnoreCase(value)) {
                return item.getKey();
            }
        }
        return null;
    }

    public static PayPlatType getEnumsByKey(String key) {
        PayPlatType[] payPlatTypeEnums = values();
        for (PayPlatType item : payPlatTypeEnums) {
            if (item.getKey().equals(key)) {
                return item;
            }
        }
        return null;
    }

    public static PayPlatType getEnumsByValue(String value) {
        PayPlatType[] payPlatTypeEnums = values();
        for (PayPlatType item : payPlatTypeEnums) {
            if (item.getValue().equalsIgnoreCase(value)) {
                return item;
            }
        }
        return null;
    }

    public String getKey() { return key; }

    public String getValue() { return value; }
}
