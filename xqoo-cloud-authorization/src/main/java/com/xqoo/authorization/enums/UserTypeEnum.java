package com.xqoo.authorization.enums;

public enum  UserTypeEnum {
    SUPER_ADMIN(99, "超级管理员"),
    CONSOLE_USER(88, "控制台用户"),
    WEB_USER(10, "web端用户"),
    TMP_USER(9,"临时用户");

    private Integer key;
    private String value;

    UserTypeEnum(Integer key, String value) {
        this.key = key;
        this.value = value;

    }

    public static String getValueByKey(Integer key) {
        UserTypeEnum[] userTypeEnums = values();
        for (UserTypeEnum item : userTypeEnums) {
            if (item.getKey().equals(key)) {
                return item.getValue();
            }
        }
        return null;
    }

    public static Integer getKeyByValue(String value) {
        UserTypeEnum[] userTypeEnums = values();
        for (UserTypeEnum item : userTypeEnums) {
            if (item.getValue().equalsIgnoreCase(value)) {
                return item.getKey();
            }
        }
        return null;
    }

    public static UserTypeEnum getEnumsByKey(Integer key) {
        UserTypeEnum[] userTypeEnums = values();
        for (UserTypeEnum item : userTypeEnums) {
            if (item.getKey().equals(key)) {
                return item;
            }
        }
        return null;
    }

    public static UserTypeEnum getEnumsByValue(String value) {
        UserTypeEnum[] userTypeEnums = values();
        for (UserTypeEnum item : userTypeEnums) {
            if (item.getValue().equalsIgnoreCase(value)) {
                return item;
            }
        }
        return null;
    }

    public Integer getKey() { return key; }

    public String getValue() { return value; }
}
