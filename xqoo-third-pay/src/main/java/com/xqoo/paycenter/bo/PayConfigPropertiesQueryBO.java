package com.xqoo.paycenter.bo;

import com.xqoo.common.page.PageRequestBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@ApiModel("支付参数配置明细查询实体")
public class PayConfigPropertiesQueryBO extends PageRequestBean {

    @ApiModelProperty("父级id")
    private Integer parentId;

    @ApiModelProperty("父级版本号")
    private Integer parentVersion;

    @ApiModelProperty("属性标签，单个版本内唯一，最长32位")
    private String propertiesLabel;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("parentId", parentId)
                .append("parentVersion", parentVersion)
                .append("propertiesLabel", propertiesLabel)
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

        PayConfigPropertiesQueryBO that = (PayConfigPropertiesQueryBO) o;

        return new EqualsBuilder().appendSuper(super.equals(o)).append(parentId, that.parentId).append(parentVersion, that.parentVersion).append(propertiesLabel, that.propertiesLabel).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).appendSuper(super.hashCode()).append(parentId).append(parentVersion).append(propertiesLabel).toHashCode();
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
}
