package com.xqoo.codegen.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class GeneratorCodeVO implements Serializable {

    private static final long serialVersionUID = -7518734443135401431L;
    private String downLoadKey;

    private List<PreviewCodeVO> previewCodeList;

    private List<IndexTreeVO> indexTree;

    @Override
    public String toString() {
        return "GeneratorCodeVO{" +
                "downLoadKey='" + downLoadKey + '\'' +
                ", previewCodeList=" + previewCodeList +
                ", indexTree=" + indexTree +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneratorCodeVO that = (GeneratorCodeVO) o;
        return Objects.equals(downLoadKey, that.downLoadKey) &&
                Objects.equals(previewCodeList, that.previewCodeList) &&
                Objects.equals(indexTree, that.indexTree);
    }

    @Override
    public int hashCode() {
        return Objects.hash(downLoadKey, previewCodeList, indexTree);
    }

    public String getDownLoadKey() {
        return downLoadKey;
    }

    public void setDownLoadKey(String downLoadKey) {
        this.downLoadKey = downLoadKey;
    }

    public List<PreviewCodeVO> getPreviewCodeList() {
        return previewCodeList;
    }

    public void setPreviewCodeList(List<PreviewCodeVO> previewCodeList) {
        this.previewCodeList = previewCodeList;
    }

    public List<IndexTreeVO> getIndexTree() {
        return indexTree;
    }

    public void setIndexTree(List<IndexTreeVO> indexTree) {
        this.indexTree = indexTree;
    }
}
