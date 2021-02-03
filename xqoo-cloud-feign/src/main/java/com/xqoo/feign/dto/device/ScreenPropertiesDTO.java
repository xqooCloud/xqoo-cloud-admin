package com.xqoo.feign.dto.device;

import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ScreenPropertiesDTO {

    private Long id;

    @ApiModelProperty("父级id")
    private String parentId;

    @ApiModelProperty("标签名")
    private String propertiesLabel;

    @ApiModelProperty("标签值")
    private String propertiesValue;

    @ApiModelProperty("显示排序")
    private Integer sortNo;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("parentId", parentId)
                .append("propertiesLabel", propertiesLabel)
                .append("propertiesValue", propertiesValue)
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
        ScreenPropertiesDTO that = (ScreenPropertiesDTO) o;
        return new EqualsBuilder()
                .append(id, that.id)
                .append(parentId, that.parentId)
                .append(propertiesLabel, that.propertiesLabel)
                .append(propertiesValue, that.propertiesValue)
                .append(sortNo, that.sortNo)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(parentId)
                .append(propertiesLabel)
                .append(propertiesValue)
                .append(sortNo)
                .toHashCode();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getPropertiesLabel() {
        return propertiesLabel;
    }

    public void setPropertiesLabel(String propertiesLabel) {
        this.propertiesLabel = propertiesLabel;
    }

    public String getPropertiesValue() {
        return propertiesValue;
    }

    public void setPropertiesValue(String propertiesValue) {
        this.propertiesValue = propertiesValue;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
