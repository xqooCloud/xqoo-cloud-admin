package com.xqoo.gateway.enums;

public enum InterceptTypeEnmu {
    REMOTE("REMOTE", "来源拦截"),
    TARGET("TARGET", "访问拦截");

    private String key;
    private String value;

    InterceptTypeEnmu(String key, String value) {
        this.key = key;
        this.value = value;

    }

    public static String getValueByKey(String key) {
        InterceptTypeEnmu[] interceptTypeEnmu = values();
        for (InterceptTypeEnmu item : interceptTypeEnmu) {
            if (item.getKey().equalsIgnoreCase(key)) {
                return item.getValue();
            }
        }
        return null;
    }

    public static String getKeyByValue(String value) {
        InterceptTypeEnmu[] interceptTypeEnmu = values();
        for (InterceptTypeEnmu item : interceptTypeEnmu) {
            if (item.getValue().equalsIgnoreCase(value)) {
                return item.getKey();
            }
        }
        return null;
    }

    public static InterceptTypeEnmu getEnumsByKey(String key) {
        InterceptTypeEnmu[] interceptTypeEnmu = values();
        for (InterceptTypeEnmu item : interceptTypeEnmu) {
            if (item.getKey().equalsIgnoreCase(key)) {
                return item;
            }
        }
        return null;
    }

    public static InterceptTypeEnmu getEnumsByValue(String value) {
        InterceptTypeEnmu[] interceptTypeEnmu = values();
        for (InterceptTypeEnmu item : interceptTypeEnmu) {
            if (item.getValue().equalsIgnoreCase(value)) {
                return item;
            }
        }
        return null;
    }

    public String getKey() { return key; }

    public String getValue() { return value; }
}
