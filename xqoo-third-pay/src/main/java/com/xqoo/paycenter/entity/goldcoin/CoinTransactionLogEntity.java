package com.xqoo.paycenter.entity.goldcoin;

import com.baomidou.mybatisplus.annotation.*;
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
@ApiModel("金币交易流水记录表")
@TableName(value = "coin_transaction_log")
public class CoinTransactionLogEntity extends Model<CoinTransactionLogEntity> {


    @ApiModelProperty("自增长id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户userId，与sys_user表userId一致，一个用户只拥有一条账目记录")
    private String userId;

    @ApiModelProperty("逻辑删除标记，0-未删除，1-已删除")
    private String delFlag;

    @ApiModelProperty("交易类型，0-收入，1-支出")
    private Integer tradeType;

    @ApiModelProperty("订单号")
    private String orderCode;

    @ApiModelProperty("交易流水号，仅在收入时填入")
    private String transactionId;

    @ApiModelProperty("交易金额，正整数存储")
    private BigInteger tradeAmount;

    @ApiModelProperty("交易说明，文本内容，不超过32个字")
    private String tradeComment;

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
                .append("id", id)
                .append("userId", userId)
                .append("delFlag", delFlag)
                .append("tradeType", tradeType)
                .append("orderCode", orderCode)
                .append("transactionId", transactionId)
                .append("tradeAmount", tradeAmount)
                .append("tradeComment", tradeComment)
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

        CoinTransactionLogEntity that = (CoinTransactionLogEntity) o;

        return new EqualsBuilder().append(id, that.id).append(userId, that.userId).append(delFlag, that.delFlag).append(tradeType, that.tradeType).append(orderCode, that.orderCode).append(transactionId, that.transactionId).append(tradeAmount, that.tradeAmount).append(tradeComment, that.tradeComment).append(createDate, that.createDate).append(createBy, that.createBy).append(updateDate, that.updateDate).append(updateBy, that.updateBy).append(remarkTips, that.remarkTips).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(userId).append(delFlag).append(tradeType).append(orderCode).append(transactionId).append(tradeAmount).append(tradeComment).append(createDate).append(createBy).append(updateDate).append(updateBy).append(remarkTips).toHashCode();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getTradeType() {
        return tradeType;
    }

    public void setTradeType(Integer tradeType) {
        this.tradeType = tradeType;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public BigInteger getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(BigInteger tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public String getTradeComment() {
        return tradeComment;
    }

    public void setTradeComment(String tradeComment) {
        this.tradeComment = tradeComment;
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
        return this.id;
    }

}
