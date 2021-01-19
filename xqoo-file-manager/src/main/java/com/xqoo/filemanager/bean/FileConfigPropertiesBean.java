package com.xqoo.filemanager.bean;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Map;

/**
 * @author gaoyang
 */
public class FileConfigPropertiesBean {

    private Map<String, Map<String, String>> fileManagerConfigBean;

    @Override
    public String toString() {
        return "FileConfigPropertiesBean{" +
                "fileManagerConfigBean=" + fileManagerConfigBean +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        FileConfigPropertiesBean that = (FileConfigPropertiesBean) o;

        return new EqualsBuilder().append(fileManagerConfigBean, that.fileManagerConfigBean).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(fileManagerConfigBean).toHashCode();
    }

    public Map<String, Map<String, String>> getFileManagerConfigBean() {
        return fileManagerConfigBean;
    }

    public void setFileManagerConfigBean(Map<String, Map<String, String>> fileManagerConfigBean) {
        this.fileManagerConfigBean = fileManagerConfigBean;
    }
}
