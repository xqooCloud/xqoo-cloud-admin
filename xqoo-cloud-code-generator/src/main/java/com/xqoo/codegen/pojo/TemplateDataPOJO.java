package com.xqoo.codegen.pojo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class TemplateDataPOJO implements Serializable {

    private static final long serialVersionUID = 4892354601673443691L;
    private String label;

    private String value;

    private String type;

    private List<String> url;

    @Override
    public String toString() {
        return "TemplateDataPOJO{" +
                "label='" + label + '\'' +
                ", value='" + value + '\'' +
                ", type='" + type + '\'' +
                ", url=" + url +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TemplateDataPOJO that = (TemplateDataPOJO) o;
        return Objects.equals(label, that.label) &&
                Objects.equals(value, that.value) &&
                Objects.equals(type, that.type) &&
                Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, value, type, url);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getUrl() {
        return url;
    }

    public void setUrl(List<String> url) {
        this.url = url;
    }
}
