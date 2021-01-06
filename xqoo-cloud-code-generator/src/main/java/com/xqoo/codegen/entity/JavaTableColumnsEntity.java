package com.xqoo.codegen.entity;

import java.util.Objects;

public class JavaTableColumnsEntity extends TableColumnsEntity {

    private static final long serialVersionUID = -8482935377716383263L;
    private String javaFieldName;

    private String javaFieldType;

    private String javaAttrName;

    @Override
    public String toString() {
        return "JavaTableColumnsEntity{" +
                "javaFieldName='" + javaFieldName + '\'' +
                ", javaFieldType='" + javaFieldType + '\'' +
                ", javaAttrName='" + javaAttrName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        JavaTableColumnsEntity that = (JavaTableColumnsEntity) o;
        return Objects.equals(javaFieldName, that.javaFieldName) &&
                Objects.equals(javaFieldType, that.javaFieldType) &&
                Objects.equals(javaAttrName, that.javaAttrName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), javaFieldName, javaFieldType, javaAttrName);
    }

    public String getJavaFieldName() {
        return javaFieldName;
    }

    public void setJavaFieldName(String javaFieldName) {
        this.javaFieldName = javaFieldName;
    }

    public String getJavaFieldType() {
        return javaFieldType;
    }

    public void setJavaFieldType(String javaFieldType) {
        this.javaFieldType = javaFieldType;
    }

    public String getJavaAttrName() {
        return javaAttrName;
    }

    public void setJavaAttrName(String javaAttrName) {
        this.javaAttrName = javaAttrName;
    }
}
