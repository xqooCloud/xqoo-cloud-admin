package com.xqoo.codegen.entity;

import java.io.Serializable;
import java.util.Objects;

public class TableColumnsEntity implements Serializable {

    private static final long serialVersionUID = -6061015884896920171L;

    // 是否主键
    private Boolean primaryKey;
    // 是否自动递增
    private Boolean autoIncrement;

    // 字段最大字数
    private Integer columnDisplaySize;

    // 列名
    private String columnName;

    // 列类型名
    private String columnsTypeName;

    // 是否允许为空
    private Boolean nullAble;

    // 列注释
    private String comment;

    private Boolean checked;

    @Override
    public String toString() {
        return "TableColumnsEntity{" +
                "primaryKey=" + primaryKey +
                "autoIncrement=" + autoIncrement +
                ", columnDisplaySize=" + columnDisplaySize +
                ", columnName='" + columnName + '\'' +
                ", columnsTypeName='" + columnsTypeName + '\'' +
                ", nullAble=" + nullAble + '\'' +
                ", comment='" + comment + '\'' +
                ", checked='" + checked + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableColumnsEntity that = (TableColumnsEntity) o;
        return Objects.equals(primaryKey, that.primaryKey) &&
                Objects.equals(autoIncrement, that.autoIncrement) &&
                Objects.equals(columnDisplaySize, that.columnDisplaySize) &&
                Objects.equals(columnName, that.columnName) &&
                Objects.equals(columnsTypeName, that.columnsTypeName) &&
                Objects.equals(nullAble, that.nullAble) &&
                Objects.equals(comment, that.comment) &&
                Objects.equals(checked, that.checked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(primaryKey, autoIncrement, columnDisplaySize, columnName, columnsTypeName, nullAble, comment, checked);
    }

    public Boolean getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(Boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    public Boolean getAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(Boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    public Integer getColumnDisplaySize() {
        return columnDisplaySize;
    }

    public void setColumnDisplaySize(Integer columnDisplaySize) {
        this.columnDisplaySize = columnDisplaySize;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnsTypeName() {
        return columnsTypeName;
    }

    public void setColumnsTypeName(String columnsTypeName) {
        this.columnsTypeName = columnsTypeName;
    }

    public Boolean getNullAble() {
        return nullAble;
    }

    public void setNullAble(Boolean nullAble) {
        this.nullAble = nullAble;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
