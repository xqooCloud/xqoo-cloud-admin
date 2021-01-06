package com.xqoo.codegen.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class IndexTreeVO implements Serializable {

    private static final long serialVersionUID = 8302802127921803994L;

    private Boolean isLeaf;

    private Integer previewCodeIndex;

    // 显示名字
    private String title;

    // 唯一值
    private String key;

    // 子孙辈
    private List<IndexTreeVO> children;

    @Override
    public String toString() {
        return "IndexTreeVO{" +
                "isLeaf=" + isLeaf +
                ", previewCodeIndex=" + previewCodeIndex +
                ", title='" + title + '\'' +
                ", key='" + key + '\'' +
                ", children=" + children +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IndexTreeVO that = (IndexTreeVO) o;
        return Objects.equals(isLeaf, that.isLeaf) &&
                Objects.equals(previewCodeIndex, that.previewCodeIndex) &&
                Objects.equals(title, that.title) &&
                Objects.equals(key, that.key) &&
                Objects.equals(children, that.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isLeaf, previewCodeIndex, title, key, children);
    }

    public Boolean getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Boolean leaf) {
        isLeaf = leaf;
    }

    public Integer getPreviewCodeIndex() {
        return previewCodeIndex;
    }

    public void setPreviewCodeIndex(Integer previewCodeIndex) {
        this.previewCodeIndex = previewCodeIndex;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<IndexTreeVO> getChildren() {
        return children;
    }

    public void setChildren(List<IndexTreeVO> children) {
        this.children = children;
    }
}
