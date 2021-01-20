package com.xqoo.filemanager.enums;

/**
 * 上传平台枚举
 * @author gaoyang
 */
public enum UploadPlatEnum {

    ALI("ALI","阿里云OSS"),
    TENCENT("TENCENT","腾讯云COS"),
    QINIU("QINIU","七牛云 "),
    LOCAL("LOCAL","本地服务器");

    private String key;
    private String value;

    UploadPlatEnum(String key, String value) {
        this.key = key;
        this.value = value;

    }

    public static String getValueByKey(String key) {
        UploadPlatEnum[] uploadPlatEnumEnums = values();
        for (UploadPlatEnum item : uploadPlatEnumEnums) {
            if (item.getKey().equals(key)) {
                return item.getValue();
            }
        }
        return null;
    }

    public static String getKeyByValue(String value) {
        UploadPlatEnum[] uploadPlatEnumEnums = values();
        for (UploadPlatEnum item : uploadPlatEnumEnums) {
            if (item.getValue().equalsIgnoreCase(value)) {
                return item.getKey();
            }
        }
        return null;
    }

    public static UploadPlatEnum getEnumsByKey(String key) {
        UploadPlatEnum[] uploadPlatEnumEnums = values();
        for (UploadPlatEnum item : uploadPlatEnumEnums) {
            if (item.getKey().equals(key)) {
                return item;
            }
        }
        return null;
    }

    public static UploadPlatEnum getEnumsByValue(String value) {
        UploadPlatEnum[] uploadPlatEnumEnums = values();
        for (UploadPlatEnum item : uploadPlatEnumEnums) {
            if (item.getValue().equalsIgnoreCase(value)) {
                return item;
            }
        }
        return null;
    }

    public String getKey() { return key; }

    public String getValue() { return value; }
}
