package com.xqoo.authorization.enums;

public enum LoginSingleEnum {
    SOURCE("source", "来源限制单一登录"),
    ALL("all", "全平台唯一单一登录");

    private String key;
    private String value;

    LoginSingleEnum(String key, String value) {
        this.key = key;
        this.value = value;

    }

    public static String getValueByKey(String key) {
        LoginSingleEnum[] loginTypeEnums = values();
        for (LoginSingleEnum item : loginTypeEnums) {
            if (item.getKey().equalsIgnoreCase(key)) {
                return item.getValue();
            }
        }
        return null;
    }

    public static String getKeyByValue(String value) {
        LoginSingleEnum[] loginTypeEnums = values();
        for (LoginSingleEnum item : loginTypeEnums) {
            if (item.getValue().equalsIgnoreCase(value)) {
                return item.getKey();
            }
        }
        return null;
    }

    public static LoginSingleEnum getEnumsByKey(String key) {
        LoginSingleEnum[] loginTypeEnums = values();
        for (LoginSingleEnum item : loginTypeEnums) {
            if (item.getKey().equalsIgnoreCase(key)) {
                return item;
            }
        }
        return null;
    }

    public static LoginSingleEnum getEnumsByValue(String value) {
        LoginSingleEnum[] loginTypeEnums = values();
        for (LoginSingleEnum item : loginTypeEnums) {
            if (item.getValue().equalsIgnoreCase(value)) {
                return item;
            }
        }
        return null;
    }

    public String getKey() { return key; }

    public String getValue() { return value; }
}
