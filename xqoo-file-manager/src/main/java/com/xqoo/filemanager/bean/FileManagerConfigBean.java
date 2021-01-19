package com.xqoo.filemanager.bean;

import com.xqoo.filemanager.entity.FileManagerConfigEntity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

public class FileManagerConfigBean {

    private List<FileManagerConfigEntity> beanList;

    public List<FileManagerConfigEntity> getBeanList() {
        return beanList;
    }

    public void setBeanList(List<FileManagerConfigEntity> beanList) {
        this.beanList = beanList;
    }

    @Override
    public String toString() {
        return "FileManagerConfigBean{" +
                "beanList=" + beanList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FileManagerConfigBean that = (FileManagerConfigBean) o;

        return new EqualsBuilder().append(beanList, that.beanList).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(beanList).toHashCode();
    }
}
