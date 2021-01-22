package com.xqoo.filemanager.enums;

/**
 * @author gaoyang
 */

public enum UploadStatusEnum {

    INIT("INIT","初始化"),
    UPLOADING("UPLOADING","上传中"),
    OFFLINE("OFFLINE","异常中断 "),
    CANCEL("CANCEL","取消"),
    FINISH("FINISH","已上传");

    private String key;
    private String value;

    UploadStatusEnum(String key, String value) {
        this.key = key;
        this.value = value;

    }

    public static String getValueByKey(String key) {
        UploadStatusEnum[] uploadStatusEnum = values();
        for (UploadStatusEnum item : uploadStatusEnum) {
            if (item.getKey().equals(key)) {
                return item.getValue();
            }
        }
        return null;
    }

    public static String getKeyByValue(String value) {
        UploadStatusEnum[] uploadStatusEnum = values();
        for (UploadStatusEnum item : uploadStatusEnum) {
            if (item.getValue().equalsIgnoreCase(value)) {
                return item.getKey();
            }
        }
        return null;
    }

    public static UploadStatusEnum getEnumsByKey(String key) {
        UploadStatusEnum[] uploadStatusEnum = values();
        for (UploadStatusEnum item : uploadStatusEnum) {
            if (item.getKey().equals(key)) {
                return item;
            }
        }
        return null;
    }

    public static UploadStatusEnum getEnumsByValue(String value) {
        UploadStatusEnum[] uploadStatusEnum = values();
        for (UploadStatusEnum item : uploadStatusEnum) {
            if (item.getValue().equalsIgnoreCase(value)) {
                return item;
            }
        }
        return null;
    }

    public String getKey() { return key; }

    public String getValue() { return value; }
}
