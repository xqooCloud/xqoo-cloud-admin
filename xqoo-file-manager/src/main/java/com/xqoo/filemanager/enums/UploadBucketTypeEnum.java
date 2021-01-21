package com.xqoo.filemanager.enums;

public enum UploadBucketTypeEnum {

    PUBLIC("public","公共读"),
    PROTECTED("protected","私有访问");

    private String key;
    private String value;

    UploadBucketTypeEnum(String key, String value) {
        this.key = key;
        this.value = value;

    }

    public static String getValueByKey(String key) {
        UploadBucketTypeEnum[] uploadPlatEnumEnums = values();
        for (UploadBucketTypeEnum item : uploadPlatEnumEnums) {
            if (item.getKey().equals(key)) {
                return item.getValue();
            }
        }
        return null;
    }

    public static String getKeyByValue(String value) {
        UploadBucketTypeEnum[] uploadPlatEnumEnums = values();
        for (UploadBucketTypeEnum item : uploadPlatEnumEnums) {
            if (item.getValue().equalsIgnoreCase(value)) {
                return item.getKey();
            }
        }
        return null;
    }

    public static UploadBucketTypeEnum getEnumsByKey(String key) {
        UploadBucketTypeEnum[] uploadPlatEnumEnums = values();
        for (UploadBucketTypeEnum item : uploadPlatEnumEnums) {
            if (item.getKey().equals(key)) {
                return item;
            }
        }
        return null;
    }

    public static UploadBucketTypeEnum getEnumsByValue(String value) {
        UploadBucketTypeEnum[] uploadPlatEnumEnums = values();
        for (UploadBucketTypeEnum item : uploadPlatEnumEnums) {
            if (item.getValue().equalsIgnoreCase(value)) {
                return item;
            }
        }
        return null;
    }

    public String getKey() { return key; }

    public String getValue() { return value; }
}
