package com.xqoo.codegen.dto;

import java.io.Serializable;
import java.util.Objects;

public class IndexListDTO implements Serializable {

    private static final long serialVersionUID = -6705423792637786992L;
    private String indexName;

    private String parentIndexName;

    @Override
    public String toString() {
        return "IndexListDTO{" +
                "indexName='" + indexName + '\'' +
                ", parentIndexName='" + parentIndexName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IndexListDTO that = (IndexListDTO) o;
        return Objects.equals(indexName, that.indexName) &&
                Objects.equals(parentIndexName, that.parentIndexName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(indexName, parentIndexName);
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public String getParentIndexName() {
        return parentIndexName;
    }

    public void setParentIndexName(String parentIndexName) {
        this.parentIndexName = parentIndexName;
    }
}
