package com.xqoo.operlog.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

@ApiModel("日志明细参数展示")
public class ShowOperationDataVO {

    @ApiModelProperty("是否有参数内容")
    private Boolean hasData;

    @ApiModelProperty("参数具体内容")
    private String content;

    @Override
    public String toString() {
        return "ShowOperationDataVO{" +
                "hasData=" + hasData +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShowOperationDataVO that = (ShowOperationDataVO) o;
        return Objects.equals(hasData, that.hasData) &&
                Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hasData, content);
    }

    public Boolean getHasData() {
        return hasData;
    }

    public void setHasData(Boolean hasData) {
        this.hasData = hasData;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
