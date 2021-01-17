package com.xqoo.paycenter.enums;

/**
 * @ClassName CommonConstants
 * @Description 支付设备枚举类
 * @Author gaoyang
 * @Date 2020/03/16 10:59
 * @Verison 1.0
 **/
public enum PayDeviceType {
    PC("电脑WEB","PC"),
    APP("手机APP","APP"),
    MOBILE("手机WEB","MOBILE "),
    JSAPI("微信公众号","JSAPI"),
    SMP("小程序","SMP"),
    FACE("POS机扫码","POS");

    private String key;
    private String value;

    PayDeviceType(String key, String value) {
        this.key = key;
        this.value = value;

    }

    public static String getValueByKey(String key) {
        PayDeviceType[] payDeviceTypeEnums = values();
        for (PayDeviceType item : payDeviceTypeEnums) {
            if (item.getKey().equals(key)) {
                return item.getValue();
            }
        }
        return null;
    }

    public static String getKeyByValue(String value) {
        PayDeviceType[] payDeviceTypeEnums = values();
        for (PayDeviceType item : payDeviceTypeEnums) {
            if (item.getValue().equalsIgnoreCase(value)) {
                return item.getKey();
            }
        }
        return null;
    }

    public static PayDeviceType getEnumsByKey(String key) {
        PayDeviceType[] payDeviceTypeEnums = values();
        for (PayDeviceType item : payDeviceTypeEnums) {
            if (item.getKey().equals(key)) {
                return item;
            }
        }
        return null;
    }

    public static PayDeviceType getEnumsByValue(String value) {
        PayDeviceType[] payDeviceTypeEnums = values();
        for (PayDeviceType item : payDeviceTypeEnums) {
            if (item.getValue().equalsIgnoreCase(value)) {
                return item;
            }
        }
        return null;
    }

    public String getKey() { return key; }

    public String getValue() { return value; }
}
