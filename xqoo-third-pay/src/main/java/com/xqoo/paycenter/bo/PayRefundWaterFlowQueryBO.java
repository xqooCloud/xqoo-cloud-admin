package com.xqoo.paycenter.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xqoo.common.page.PageRequestBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

@ApiModel("退款记录流水查询实体")
public class PayRefundWaterFlowQueryBO extends PageRequestBean {

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

    @ApiModelProperty("退款起始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date startDate;

    @ApiModelProperty("退款结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date endDate;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("refundCode", refundCode)
                .append("refundStatus", refundStatus)
                .append("payTransactionId", payTransactionId)
                .append("refundPlat", refundPlat)
                .append("refundUserId", refundUserId)
                .append("tradeId", tradeId)
                .append("startDate", startDate)
                .append("endDate", endDate)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) return false;

        PayRefundWaterFlowQueryBO that = (PayRefundWaterFlowQueryBO) o;

        return new EqualsBuilder().appendSuper(super.equals(o)).append(refundCode, that.refundCode).append(refundStatus, that.refundStatus).append(payTransactionId, that.payTransactionId).append(refundPlat, that.refundPlat).append(refundUserId, that.refundUserId).append(tradeId, that.tradeId).append(startDate, that.startDate).append(endDate, that.endDate).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).appendSuper(super.hashCode()).append(refundCode).append(refundStatus).append(payTransactionId).append(refundPlat).append(refundUserId).append(tradeId).append(startDate).append(endDate).toHashCode();
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
