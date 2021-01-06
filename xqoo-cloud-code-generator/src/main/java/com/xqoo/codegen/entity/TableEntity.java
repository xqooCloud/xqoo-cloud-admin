package com.xqoo.codegen.entity;

import java.io.Serializable;
import java.util.Objects;

public class TableEntity implements Serializable {

    private static final long serialVersionUID = -3853091738278518932L;
    // 集合名，mysql中等同于库名
    private String schemeName;

    // 表名
    private String tableName;

    // 表注释
    private String comment;

    @Override
    public String toString() {
        return "TableEntity{" +
                "schemeName='" + schemeName + '\'' +
                ", tableName='" + tableName + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableEntity that = (TableEntity) o;
        return Objects.equals(schemeName, that.schemeName) &&
                Objects.equals(tableName, that.tableName) &&
                Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schemeName, tableName, comment);
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
