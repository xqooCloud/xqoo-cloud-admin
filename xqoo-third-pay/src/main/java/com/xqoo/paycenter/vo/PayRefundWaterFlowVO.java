package com.xqoo.paycenter.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;
import java.util.Date;

@ApiModel("退款记录流水表")
public class PayRefundWaterFlowVO {

    @ApiModelProperty("退款单号")
    private String refundCode;

    @ApiModelProperty("退款状态，0-退款失败，1-退款成功")
    private Integer refundStatus;

    @ApiModelProperty("系统支付单号")
    private String payTransactionId;

    @ApiModelProperty("退款平台，WxPay-微信支付，AliPay-支付宝支付，IOSApp-苹果内购，UnionPay-银联支付")
    private String refundPlat;

    @ApiModelProperty("退款的系统用户id")
    private String refundUserId;

    @ApiModelProperty("支付平台交易流水号")
    private String tradeId;

    @ApiModelProperty("退款金额")
    private BigDecimal refundAmount;

    @ApiModelProperty("退款原因")
    private String refundReason;

    @ApiModelProperty("退款时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date refundTime;

    @ApiModelProperty("退款发起方")
    private String refundLaunch;

    @ApiModelProperty("创建人")
    private String createBy;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date createDate;

    @ApiModelProperty("最近修改人")
    private String updateBy;

    @ApiModelProperty("最近修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date updateDate;

    @ApiModelProperty("备注信息")
    private String remarkTips;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("refundCode", refundCode)
                .append("refundStatus", refundStatus)
                .append("payTransactionId", payTransactionId)
                .append("refundPlat", refundPlat)
                .append("refundUserId", refundUserId)
                .append("tradeId", tradeId)
                .append("refundAmount", refundAmount)
                .append("refundReason", refundReason)
                .append("refundTime", refundTime)
                .append("refundLaunch", refundLaunch)
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

        if (o == null || getClass() != o.getClass()) return false;

        PayRefundWaterFlowVO that = (PayRefundWaterFlowVO) o;

        return new EqualsBuilder().append(refundCode, that.refundCode).append(refundStatus, that.refundStatus).append(payTransactionId, that.payTransactionId).append(refundPlat, that.refundPlat).append(refundUserId, that.refundUserId).append(tradeId, that.tradeId).append(refundAmount, that.refundAmount).append(refundReason, that.refundReason).append(refundTime, that.refundTime).append(refundLaunch, that.refundLaunch).append(createBy, that.createBy).append(createDate, that.createDate).append(updateBy, that.updateBy).append(updateDate, that.updateDate).append(remarkTips, that.remarkTips).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(refundCode).append(refundStatus).append(payTransactionId).append(refundPlat).append(refundUserId).append(tradeId).append(refundAmount).append(refundReason).append(refundTime).append(refundLaunch).append(createBy).append(createDate).append(updateBy).append(updateDate).append(remarkTips).toHashCode();
    }

    public String getRefundCode() {
        return refundCode;
    }

    public void setRefundCode(String refundCode) {
        this.refundCode = refundCode;
    }

    public Integer getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(Integer refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getPayTransactionId() {
        return payTransactionId;
    }

    public void setPayTransactionId(String payTransactionId) {
        this.payTransactionId = payTransactionId;
    }

    public String getRefundPlat() {
        return refundPlat;
    }

    public void setRefundPlat(String refundPlat) {
        this.refundPlat = refundPlat;
    }

    public String getRefundUserId() {
        return refundUserId;
    }

    public void setRefundUserId(String refundUserId) {
        this.refundUserId = refundUserId;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

    public Date getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(Date refundTime) {
        this.refundTime = refundTime;
    }

    public String getRefundLaunch() {
        return refundLaunch;
    }

    public void setRefundLaunch(String refundLaunch) {
        this.refundLaunch = refundLaunch;
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
}
