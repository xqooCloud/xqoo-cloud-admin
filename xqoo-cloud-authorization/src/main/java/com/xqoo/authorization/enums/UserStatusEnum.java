package com.xqoo.authorization.enums;

public enum UserStatusEnum {
    NORMAL(0, "正常"),
    DENY(1, "封禁"),
    FREEZE(2, "停用");

    private Integer key;
    private String value;

    UserStatusEnum(Integer key, String value) {
        this.key = key;
        this.value = value;

    }

    public static String getValueByKey(Integer key) {
        UserStatusEnum[] userStatusEnums = values();
        for (UserStatusEnum item : userStatusEnums) {
            if (item.getKey().equals(key)) {
                return item.getValue();
            }
        }
        return null;
    }

    public static Integer getKeyByValue(String value) {
        UserStatusEnum[] userStatusEnums = values();
        for (UserStatusEnum item : userStatusEnums) {
            if (item.getValue().equalsIgnoreCase(value)) {
                return item.getKey();
            }
        }
        return null;
    }

    public static UserStatusEnum getEnumsByKey(Integer key) {
        UserStatusEnum[] userStatusEnums = values();
        for (UserStatusEnum item : userStatusEnums) {
            if (item.getKey().equals(key)) {
                return item;
            }
        }
        return null;
    }

    public static UserStatusEnum getEnumsByValue(String value) {
        UserStatusEnum[] userStatusEnums = values();
        for (UserStatusEnum item : userStatusEnums) {
            if (item.getValue().equalsIgnoreCase(value)) {
                return item;
            }
        }
        return null;
    }

    public Integer getKey() { return key; }

    public String getValue() { return value; }
}
