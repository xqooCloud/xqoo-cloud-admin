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
import java.math.BigInteger;
import java.util.Date;

/**
 * @author mkinjava
 * @date 2020/04/21 上午11:57
 **/
@ApiModel("金币用户表")
@TableName(value = "coin_account_info")
public class CoinAccountInfoEntity extends Model<CoinAccountInfoEntity> {


    @ApiModelProperty("用户userId，与sys_user表userId一致，一个用户只拥有一条账目记录")
    @TableId(value = "user_id")
    private String userId;

    @ApiModelProperty("逻辑删除标记，0-未删除，1-已删除")
    private String delFlag;

    @ApiModelProperty("当前账户金币余额，整形存储，调用需要除以100，默认0")
    private BigInteger coinTotal;

    @ApiModelProperty("用户绑定手机，与customer_info表一致")
    private String ciPhone;

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
                .append("userId", userId)
                .append("delFlag", delFlag)
                .append("coinTotal", coinTotal)
                .append("ciPhone", ciPhone)
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

        CoinAccountInfoEntity that = (CoinAccountInfoEntity) o;

        return new EqualsBuilder().append(userId, that.userId).append(delFlag, that.delFlag).append(coinTotal, that.coinTotal).append(ciPhone, that.ciPhone).append(createDate, that.createDate).append(createBy, that.createBy).append(updateDate, that.updateDate).append(updateBy, that.updateBy).append(remarkTips, that.remarkTips).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(userId).append(delFlag).append(coinTotal).append(ciPhone).append(createDate).append(createBy).append(updateDate).append(updateBy).append(remarkTips).toHashCode();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public BigInteger getCoinTotal() {
        return coinTotal;
    }

    public void setCoinTotal(BigInteger coinTotal) {
        this.coinTotal = coinTotal;
    }

    public String getCiPhone() {
        return ciPhone;
    }

    public void setCiPhone(String ciPhone) {
        this.ciPhone = ciPhone;
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
        return this.userId;
    }

}
