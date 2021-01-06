package com.xqoo.common.enums;

public enum CommunicateStatusEnum {

    SUCCESS("SUCCESS", "成功"),
    WARN("WARN", "有警告"),
    FAIL("FAIL", "失败");

    private String key;
    private String value;

    CommunicateStatusEnum(String key, String value) {
        this.key = key;
        this.value = value;

    }

    public static String getValueByKey(String key) {
        CommunicateStatusEnum[] communicateStatusEnum = values();
        for (CommunicateStatusEnum item : communicateStatusEnum) {
            if (item.getKey().equalsIgnoreCase(key)) {
                return item.getValue();
            }
        }
        return null;
    }

    public static String getKeyByValue(String value) {
        CommunicateStatusEnum[] communicateStatusEnum = values();
        for (CommunicateStatusEnum item : communicateStatusEnum) {
            if (item.getValue().equalsIgnoreCase(value)) {
                return item.getKey();
            }
        }
        return null;
    }

    public static CommunicateStatusEnum getEnumsByKey(String key) {
        CommunicateStatusEnum[] communicateStatusEnum = values();
        for (CommunicateStatusEnum item : communicateStatusEnum) {
            if (item.getKey().equalsIgnoreCase(key)) {
                return item;
            }
        }
        return null;
    }

    public static CommunicateStatusEnum getEnumsByValue(String value) {
        CommunicateStatusEnum[] communicateStatusEnum = values();
        for (CommunicateStatusEnum item : communicateStatusEnum) {
            if (item.getValue().equalsIgnoreCase(value)) {
                return item;
            }
        }
        return null;
    }

    public String getKey() { return key; }

    public String getValue() { return value; }
}
