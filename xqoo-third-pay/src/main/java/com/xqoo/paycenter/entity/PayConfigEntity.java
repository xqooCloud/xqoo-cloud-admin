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
@ApiModel("支付配置总表")
@TableName(value = "pay_config")
public class PayConfigEntity extends Model<PayConfigEntity> {


    @ApiModelProperty("自增长id")
    @TableId(value = "id", type= IdType.AUTO)
    private Integer id;

    @ApiModelProperty("支付平台，WxPay-微信支付,AliPay-支付宝，IOSApp-ios内购项目")
    private String payPlat;

    @ApiModelProperty("配置版本，1000开始")
    private Integer configVersion;

    @ApiModelProperty("使用状态，0-历史版本，1-当前版本，只能存在一个当前版本")
    private Integer activeState;

    @ApiModelProperty("产品状态，0-草稿，1-已发布，2-已废弃")
    private Integer configStatus;

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
                .append("payPlat", payPlat)
                .append("configVersion", configVersion)
                .append("activeState", activeState)
                .append("configStatus", configStatus)
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

        PayConfigEntity that = (PayConfigEntity) o;

        return new EqualsBuilder().append(id, that.id).append(payPlat, that.payPlat).append(configVersion, that.configVersion).append(activeState, that.activeState).append(configStatus, that.configStatus).append(createBy, that.createBy).append(createDate, that.createDate).append(updateBy, that.updateBy).append(updateDate, that.updateDate).append(remarkTips, that.remarkTips).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(payPlat).append(configVersion).append(activeState).append(configStatus).append(createBy).append(createDate).append(updateBy).append(updateDate).append(remarkTips).toHashCode();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPayPlat() {
        return payPlat;
    }

    public void setPayPlat(String payPlat) {
        this.payPlat = payPlat;
    }

    public Integer getConfigVersion() {
        return configVersion;
    }

    public void setConfigVersion(Integer configVersion) {
        this.configVersion = configVersion;
    }

    public Integer getActiveState() {
        return activeState;
    }

    public void setActiveState(Integer activeState) {
        this.activeState = activeState;
    }

    public Integer getConfigStatus() {
        return configStatus;
    }

    public void setConfigStatus(Integer configStatus) {
        this.configStatus = configStatus;
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
        return this.id;
    }

}
