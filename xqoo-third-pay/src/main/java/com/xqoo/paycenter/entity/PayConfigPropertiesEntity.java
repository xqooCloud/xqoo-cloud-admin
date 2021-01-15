package com.xqoo.paycenter.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mumu
 * @date 2019/11/08 上午11:17
 **/
@ApiModel("支付参数配置明细表")
@TableName(value = "pay_config_properties")
public class PayConfigPropertiesEntity extends Model<PayConfigPropertiesEntity> {

    @ApiModelProperty("自增长id")
    @TableId(value = "id", type= IdType.AUTO)
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

    @TableField(value = "create_by", fill = FieldFill.INSERT)
    @ApiModelProperty("创建人")
    private String createBy;

    @TableField(value = "create_date", fill = FieldFill.INSERT)
    @ApiModelProperty("创建时间")
    private Date createDate;

    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    @ApiModelProperty("最近修改人")
    private String updateBy;

    @TableField(value = "update_date", fill = FieldFill.UPDATE)
    @ApiModelProperty("最近修改时间")
    private Date updateDate;

    @ApiModelProperty("备注信息")
    private String remarkTips;

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
                .append("createBy", createBy)
                .append("createDate", createDate)
                .append("updateBy", updateBy)
                .append("updateDate", updateDate)
                .append("remarkTips", remarkTips)
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

        PayConfigPropertiesEntity that = (PayConfigPropertiesEntity) o;

        return new EqualsBuilder().append(id, that.id).append(parentId, that.parentId).append(parentVersion, that.parentVersion).append(propertiesLabel, that.propertiesLabel).append(propertiesValue, that.propertiesValue).append(propertiesPattern, that.propertiesPattern).append(propertiesRemark, that.propertiesRemark).append(createBy, that.createBy).append(createDate, that.createDate).append(updateBy, that.updateBy).append(updateDate, that.updateDate).append(remarkTips, that.remarkTips).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(parentId).append(parentVersion).append(propertiesLabel).append(propertiesValue).append(propertiesPattern).append(propertiesRemark).append(createBy).append(createDate).append(updateBy).append(updateDate).append(remarkTips).toHashCode();
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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarkTips() {
        return remarkTips;
    }

    public void setRemarkTips(String remarkTips) {
        this.remarkTips = remarkTips;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.parentId;
    }

}
