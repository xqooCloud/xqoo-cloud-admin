package com.xqoo.feign.enums.operlog;

public enum OperationTypeEnum {
    OTHER(0, "其他"),
    QUERY(1, "查询"),
    ADD(2, "新增"),
    EDIT(3, "修改"),
    REMOVE(4, "删除");

    private Integer key;
    private String value;

    OperationTypeEnum(Integer key, String value) {
        this.key = key;
        this.value = value;

    }

    public static String getValueByKey(Integer key) {
        OperationTypeEnum[] operationTypeEnum = values();
        for (OperationTypeEnum item : operationTypeEnum) {
            if (item.getKey().equals(key)) {
                return item.getValue();
            }
        }
        return null;
    }

    public static Integer getKeyByValue(String value) {
        OperationTypeEnum[] operationTypeEnum = values();
        for (OperationTypeEnum item : operationTypeEnum) {
            if (item.getValue().equalsIgnoreCase(value)) {
                return item.getKey();
            }
        }
        return null;
    }

    public static OperationTypeEnum getEnumsByKey(Integer key) {
        OperationTypeEnum[] operationTypeEnum = values();
        for (OperationTypeEnum item : operationTypeEnum) {
            if (item.getKey().equals(key)) {
                return item;
            }
        }
        return null;
    }

    public static OperationTypeEnum getEnumsByValue(String value) {
        OperationTypeEnum[] operationTypeEnum = values();
        for (OperationTypeEnum item : operationTypeEnum) {
            if (item.getValue().equalsIgnoreCase(value)) {
                return item;
            }
        }
        return null;
    }

    public Integer getKey() { return key; }

    public String getValue() { return value; }
}
