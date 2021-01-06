package com.xqoo.feign.enums.operlog;

public enum OperationStatusEnum {
    SUCCESS(0, "正常"),
    FAIL(1, "错误");

    private Integer key;
    private String value;

    OperationStatusEnum(Integer key, String value) {
        this.key = key;
        this.value = value;

    }

    public static String getValueByKey(Integer key) {
        OperationStatusEnum[] operationTypeEnum = values();
        for (OperationStatusEnum item : operationTypeEnum) {
            if (item.getKey().equals(key)) {
                return item.getValue();
            }
        }
        return null;
    }

    public static Integer getKeyByValue(String value) {
        OperationStatusEnum[] operationTypeEnum = values();
        for (OperationStatusEnum item : operationTypeEnum) {
            if (item.getValue().equalsIgnoreCase(value)) {
                return item.getKey();
            }
        }
        return null;
    }

    public static OperationStatusEnum getEnumsByKey(Integer key) {
        OperationStatusEnum[] operationTypeEnum = values();
        for (OperationStatusEnum item : operationTypeEnum) {
            if (item.getKey().equals(key)) {
                return item;
            }
        }
        return null;
    }

    public static OperationStatusEnum getEnumsByValue(String value) {
        OperationStatusEnum[] operationTypeEnum = values();
        for (OperationStatusEnum item : operationTypeEnum) {
            if (item.getValue().equalsIgnoreCase(value)) {
                return item;
            }
        }
        return null;
    }

    public Integer getKey() { return key; }

    public String getValue() { return value; }
}
