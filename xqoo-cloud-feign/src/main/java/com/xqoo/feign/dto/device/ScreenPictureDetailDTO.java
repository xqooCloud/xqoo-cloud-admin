package com.xqoo.feign.dto.device;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ScreenPictureDetailDTO {

    private Long id;

    private String parentId;

    private String fileName;

    private String filePath;

    private String fileId;

    private Integer sortNo;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("parentId", parentId)
                .append("fileName", fileName)
                .append("filePath", filePath)
                .append("fileId", fileId)
                .append("sortNo", sortNo)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ScreenPictureDetailDTO that = (ScreenPictureDetailDTO) o;
        return new EqualsBuilder()
                .append(id, that.id)
                .append(parentId, that.parentId)
                .append(fileName, that.fileName)
                .append(filePath, that.filePath)
                .append(fileId, that.fileId)
                .append(sortNo, that.sortNo)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(parentId)
                .append(fileName)
                .append(filePath)
                .append(fileId)
                .append(sortNo)
                .toHashCode();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }
}
