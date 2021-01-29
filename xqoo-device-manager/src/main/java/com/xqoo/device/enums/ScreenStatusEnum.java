package com.xqoo.device.enums;

/**
 * @author gaoyang
 */

public enum ScreenStatusEnum {

    PREPARE(0,"计划部署"),
    DEPLOY(1,"正在部署"),
    FINISH(2,"已部署使用"),
    STOP(3,"故障停机"),
    REMOVE(4,"拆除使用");

    private Integer key;
    private String value;

    ScreenStatusEnum(Integer key, String value) {
        this.key = key;
        this.value = value;

    }

    public static String getValueByKey(Integer key) {
        ScreenStatusEnum[] screenStatusEnum = values();
        for (ScreenStatusEnum item : screenStatusEnum) {
            if (item.getKey().equals(key)) {
                return item.getValue();
            }
        }
        return null;
    }

    public static Integer getKeyByValue(String value) {
        ScreenStatusEnum[] screenStatusEnum = values();
        for (ScreenStatusEnum item : screenStatusEnum) {
            if (item.getValue().equalsIgnoreCase(value)) {
                return item.getKey();
            }
        }
        return null;
    }

    public static ScreenStatusEnum getEnumsByKey(Integer key) {
        ScreenStatusEnum[] screenStatusEnum = values();
        for (ScreenStatusEnum item : screenStatusEnum) {
            if (item.getKey().equals(key)) {
                return item;
            }
        }
        return null;
    }

    public static ScreenStatusEnum getEnumsByValue(String value) {
        ScreenStatusEnum[] screenStatusEnum = values();
        for (ScreenStatusEnum item : screenStatusEnum) {
            if (item.getValue().equalsIgnoreCase(value)) {
                return item;
            }
        }
        return null;
    }

    public Integer getKey() { return key; }

    public String getValue() { return value; }
}
