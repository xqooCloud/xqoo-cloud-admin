package com.xqoo.salecenter.enums;

/**
 * @author gaoyang
 */

public enum SaleGoodsStatusEnum {

    DRAFT(0,"草稿"),
    AUDITING(1,"审核中"),
    AUDITED(2,"审核完成"),
    PUBLISH(3,"已上架"),
    ABOARD(4,"废弃");

    private Integer key;
    private String value;

    SaleGoodsStatusEnum(Integer key, String value) {
        this.key = key;
        this.value = value;

    }

    public static String getValueByKey(Integer key) {
        SaleGoodsStatusEnum[] saleGoodsStatusEnum = values();
        for (SaleGoodsStatusEnum item : saleGoodsStatusEnum) {
            if (item.getKey().equals(key)) {
                return item.getValue();
            }
        }
        return null;
    }

    public static Integer getKeyByValue(String value) {
        SaleGoodsStatusEnum[] saleGoodsStatusEnum = values();
        for (SaleGoodsStatusEnum item : saleGoodsStatusEnum) {
            if (item.getValue().equalsIgnoreCase(value)) {
                return item.getKey();
            }
        }
        return null;
    }

    public static SaleGoodsStatusEnum getEnumsByKey(Integer key) {
        SaleGoodsStatusEnum[] saleGoodsStatusEnum = values();
        for (SaleGoodsStatusEnum item : saleGoodsStatusEnum) {
            if (item.getKey().equals(key)) {
                return item;
            }
        }
        return null;
    }

    public static SaleGoodsStatusEnum getEnumsByValue(String value) {
        SaleGoodsStatusEnum[] saleGoodsStatusEnum = values();
        for (SaleGoodsStatusEnum item : saleGoodsStatusEnum) {
            if (item.getValue().equalsIgnoreCase(value)) {
                return item;
            }
        }
        return null;
    }

    public Integer getKey() { return key; }

    public String getValue() { return value; }
}
