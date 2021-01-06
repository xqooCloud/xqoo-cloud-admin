package com.xqoo.common.enums;

public enum LoginTypeEnum {
    PASSWORD("PASSWORD", "账号密码模式"),
    QRCODE("QRCODE", "二维码扫码模式"),
    PHONE("PHONE", "手机短信验证码模式"),
    EMAIL("EMAIL","邮件验证码模式"),
    FINGER("FINGER", "指纹模式"),
    FACE("FACE", "面部识别模式"),
    IDENTIFY("IDENTIFY", "证件扫描模式"),
    THIRDPARTY("THIRDPARTY", "第三方登录");

    private String key;
    private String value;

    LoginTypeEnum(String key, String value) {
        this.key = key;
        this.value = value;

    }

    public static String getValueByKey(String key) {
        LoginTypeEnum[] loginTypeEnums = values();
        for (LoginTypeEnum item : loginTypeEnums) {
            if (item.getKey().equalsIgnoreCase(key)) {
                return item.getValue();
            }
        }
        return null;
    }

    public static String getKeyByValue(String value) {
        LoginTypeEnum[] loginTypeEnums = values();
        for (LoginTypeEnum item : loginTypeEnums) {
            if (item.getValue().equalsIgnoreCase(value)) {
                return item.getKey();
            }
        }
        return null;
    }

    public static LoginTypeEnum getEnumsByKey(String key) {
        LoginTypeEnum[] loginTypeEnums = values();
        for (LoginTypeEnum item : loginTypeEnums) {
            if (item.getKey().equalsIgnoreCase(key)) {
                return item;
            }
        }
        return null;
    }

    public static LoginTypeEnum getEnumsByValue(String value) {
        LoginTypeEnum[] loginTypeEnums = values();
        for (LoginTypeEnum item : loginTypeEnums) {
            if (item.getValue().equalsIgnoreCase(value)) {
                return item;
            }
        }
        return null;
    }

    public String getKey() { return key; }

    public String getValue() { return value; }
}
