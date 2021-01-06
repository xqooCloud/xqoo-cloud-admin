package com.xqoo.common.enums;

public enum LoginSourceEnum {
    PcBrowser("PcBrowser", "浏览器"),
    PcClient("PcClient", "电脑客户端"),
    APP("APP", "手机app"),
    WeChat("WeChat","公众号"),
    SmallProgram("SmallProgram", "小程序"),
    UnKnow("UnKnow", "未知来源");

    private String key;
    private String value;

    LoginSourceEnum(String key, String value) {
        this.key = key;
        this.value = value;

    }

    public static String getValueByKey(String key) {
        LoginSourceEnum[] loginTypeEnums = values();
        for (LoginSourceEnum item : loginTypeEnums) {
            if (item.getKey().equalsIgnoreCase(key)) {
                return item.getValue();
            }
        }
        return "未知来源";
    }

    public static String getKeyByValue(String value) {
        LoginSourceEnum[] loginTypeEnums = values();
        for (LoginSourceEnum item : loginTypeEnums) {
            if (item.getValue().equalsIgnoreCase(value)) {
                return item.getKey();
            }
        }
        return "UnKnow";
    }

    public static LoginSourceEnum getEnumsByKey(String key) {
        LoginSourceEnum[] loginTypeEnums = values();
        for (LoginSourceEnum item : loginTypeEnums) {
            if (item.getKey().equalsIgnoreCase(key)) {
                return item;
            }
        }
        return null;
    }

    public static LoginSourceEnum getEnumsByValue(String value) {
        LoginSourceEnum[] loginTypeEnums = values();
        for (LoginSourceEnum item : loginTypeEnums) {
            if (item.getValue().equalsIgnoreCase(value)) {
                return item;
            }
        }
        return null;
    }

    public String getKey() { return key; }

    public String getValue() { return value; }
}
