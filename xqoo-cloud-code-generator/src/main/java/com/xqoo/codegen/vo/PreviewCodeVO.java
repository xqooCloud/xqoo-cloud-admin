package com.xqoo.codegen.vo;

import java.io.Serializable;
import java.util.Objects;

public class PreviewCodeVO implements Serializable {

    private static final long serialVersionUID = -5292887925387186452L;
    private String fileType;

    private String content;

    private String fileName;

    private Integer index;

    @Override
    public String toString() {
        return "PreviewCodeVO{" +
                "fileType='" + fileType + '\'' +
                ", content='" + content + '\'' +
                ", fileName='" + fileName + '\'' +
                ", index='" + index + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PreviewCodeVO that = (PreviewCodeVO) o;
        return Objects.equals(fileType, that.fileType) &&
                Objects.equals(content, that.content) &&
                Objects.equals(fileName, that.fileName) &&
                Objects.equals(index, that.index);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileType, content, fileName, index);
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
