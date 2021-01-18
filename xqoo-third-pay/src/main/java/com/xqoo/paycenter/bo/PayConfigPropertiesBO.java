package com.xqoo.paycenter.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@ApiModel("支付参数配置明细表")
public class PayConfigPropertiesBO {

    @ApiModelProperty("自增长id")
    private Integer id;

    @ApiModelProperty("父级id")
    private Integer parentId;

    @ApiModelProperty("父级版本号")
    private Integer parentVersion;

    @ApiModelProperty("属性标签，单个版本内唯一，最长32位")
    private String propertiesLabel;

    @ApiModelProperty("属性值，最长2048位")
    private String propertiesValue;

    @ApiModelProperty("校验属性值正则表达式，为null或空则不校验")
    private String propertiesPattern;

    @ApiModelProperty("属性备注说明，最多64字")
    private String propertiesRemark;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("parentId", parentId)
                .append("parentVersion", parentVersion)
                .append("propertiesLabel", propertiesLabel)
                .append("propertiesValue", propertiesValue)
                .append("propertiesPattern", propertiesPattern)
                .append("propertiesRemark", propertiesRemark)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) return false;

        PayConfigPropertiesBO that = (PayConfigPropertiesBO) o;

        return new EqualsBuilder().append(id, that.id).append(parentId, that.parentId).append(parentVersion, that.parentVersion).append(propertiesLabel, that.propertiesLabel).append(propertiesValue, that.propertiesValue).append(propertiesPattern, that.propertiesPattern).append(propertiesRemark, that.propertiesRemark).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(parentId).append(parentVersion).append(propertiesLabel).append(propertiesValue).append(propertiesPattern).append(propertiesRemark).toHashCode();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getParentVersion() {
        return parentVersion;
    }

    public void setParentVersion(Integer parentVersion) {
        this.parentVersion = parentVersion;
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

    public String getPropertiesPattern() {
        return propertiesPattern;
    }

    public void setPropertiesPattern(String propertiesPattern) {
        this.propertiesPattern = propertiesPattern;
    }

    public String getPropertiesRemark() {
        return propertiesRemark;
    }

    public void setPropertiesRemark(String propertiesRemark) {
        this.propertiesRemark = propertiesRemark;
    }
}
