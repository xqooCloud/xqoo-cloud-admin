package com.xqoo.paycenter.entity.goldcoin;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mkinjava
 * @date 2020/04/21 上午11:57
 **/
@ApiModel("内购配置表")
@TableName(value = "coin_config")
public class CoinConfigEntity extends Model<CoinConfigEntity> {


    @ApiModelProperty("充值面额id，固定规则主键")
    @TableId(value = "charge_id")
    private String chargeId;

    @ApiModelProperty("逻辑删除标记，0-未删除，1-已删除")
    private String delFlag;

    @ApiModelProperty("对应面额，1:1作为货币使用，整形")
    private Integer quotaMoney;

    @TableField(value = "create_date", fill = FieldFill.INSERT)
    @ApiModelProperty("记录创建时间")
    private Date createDate;

    @TableField(value = "create_by", fill = FieldFill.INSERT)
    @ApiModelProperty("记录创建人")
    private String createBy;

    @TableField(value = "update_date", fill = FieldFill.UPDATE)
    @ApiModelProperty("记录修改时间")
    private Date updateDate;

    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    @ApiModelProperty("记录修改人")
    private String updateBy;

    @ApiModelProperty("备注说明")
    private String remarkTips;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("chargeId", chargeId)
                .append("delFlag", delFlag)
                .append("quotaMoney", quotaMoney)
                .append("createDate", createDate)
                .append("createBy", createBy)
                .append("updateDate", updateDate)
                .append("updateBy", updateBy)
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

        CoinConfigEntity that = (CoinConfigEntity) o;

        return new EqualsBuilder().append(chargeId, that.chargeId).append(delFlag, that.delFlag).append(quotaMoney, that.quotaMoney).append(createDate, that.createDate).append(createBy, that.createBy).append(updateDate, that.updateDate).append(updateBy, that.updateBy).append(remarkTips, that.remarkTips).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(chargeId).append(delFlag).append(quotaMoney).append(createDate).append(createBy).append(updateDate).append(updateBy).append(remarkTips).toHashCode();
    }

    public String getChargeId() {
        return chargeId;
    }

    public void setChargeId(String chargeId) {
        this.chargeId = chargeId;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getQuotaMoney() {
        return quotaMoney;
    }

    public void setQuotaMoney(Integer quotaMoney) {
        this.quotaMoney = quotaMoney;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
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
        return this.chargeId;
    }

}
