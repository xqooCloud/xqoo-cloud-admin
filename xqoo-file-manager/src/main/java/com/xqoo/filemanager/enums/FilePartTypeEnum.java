package com.xqoo.filemanager.enums;

public enum FilePartTypeEnum {


    ALL("ALL","整个文件上传"),
    PART("PART","分片上传");

    private String key;
    private String value;

    FilePartTypeEnum(String key, String value) {
        this.key = key;
        this.value = value;

    }

    public static String getValueByKey(String key) {
        FilePartTypeEnum[] filePartTypeEnum = values();
        for (FilePartTypeEnum item : filePartTypeEnum) {
            if (item.getKey().equals(key)) {
                return item.getValue();
            }
        }
        return null;
    }

    public static String getKeyByValue(String value) {
        FilePartTypeEnum[] filePartTypeEnum = values();
        for (FilePartTypeEnum item : filePartTypeEnum) {
            if (item.getValue().equalsIgnoreCase(value)) {
                return item.getKey();
            }
        }
        return null;
    }

    public static FilePartTypeEnum getEnumsByKey(String key) {
        FilePartTypeEnum[] filePartTypeEnum = values();
        for (FilePartTypeEnum item : filePartTypeEnum) {
            if (item.getKey().equals(key)) {
                return item;
            }
        }
        return null;
    }

    public static FilePartTypeEnum getEnumsByValue(String value) {
        FilePartTypeEnum[] filePartTypeEnum = values();
        for (FilePartTypeEnum item : filePartTypeEnum) {
            if (item.getValue().equalsIgnoreCase(value)) {
                return item;
            }
        }
        return null;
    }

    public String getKey() { return key; }

    public String getValue() { return value; }
}
