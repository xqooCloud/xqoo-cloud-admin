package com.xqoo.paycenter.bo;

import com.xqoo.paycenter.utils.WxPayUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;
import java.util.Map;

@ApiModel("微信退款前台参数")
public class WxRefundNeedParam {

    @ApiModelProperty("系统生成的支付单号")
    private String payTransactionId;

    @ApiModelProperty("退款的明细订单号")
    private String orderDetailId;

    @ApiModelProperty("退款金额")
    private BigDecimal refundAmount;

    @ApiModelProperty("原付款单金额")
    private BigDecimal payAmount;

    @ApiModelProperty("退款原因")
    private String refundReason;

    @ApiModelProperty("退款发起方")
    private String refundLaunch;

    public String getPayTransactionId() {
        return payTransactionId;
    }

    public void setPayTransactionId(String payTransactionId) {
        this.payTransactionId = payTransactionId;
    }

    public String getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(String orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }

    public String getRefundLaunch() {
        return refundLaunch;
    }

    public void setRefundLaunch(String refundLaunch) {
        this.refundLaunch = refundLaunch;
    }

    public void fromMap(Map<String, Object> map){
        this.setPayTransactionId(map.getOrDefault("payTransactionId",null).toString());
        this.setOrderDetailId(map.getOrDefault("orderDetailId",null).toString());
        this.setRefundAmount(WxPayUtil.valueToNum(map.getOrDefault("refundAmount",BigDecimal.ZERO).toString()));
        this.setPayAmount(WxPayUtil.valueToNum(map.getOrDefault("payAmount",BigDecimal.ZERO).toString()));
        this.setRefundReason(map.getOrDefault("refundReason",null).toString());
        this.setRefundLaunch(map.getOrDefault("refundLaunch",null).toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WxRefundNeedParam that = (WxRefundNeedParam) o;

        return new EqualsBuilder().append(payTransactionId, that.payTransactionId).append(orderDetailId, that.orderDetailId).append(refundAmount, that.refundAmount).append(payAmount, that.payAmount).append(refundReason, that.refundReason).append(refundLaunch, that.refundLaunch).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(payTransactionId).append(orderDetailId).append(refundAmount).append(payAmount).append(refundReason).append(refundLaunch).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("payTransactionId", payTransactionId)
                .append("orderDetailId", orderDetailId)
                .append("refundAmount", refundAmount)
                .append("payAmount", payAmount)
                .append("refundReason", refundReason)
                .append("refundLaunch", refundLaunch)
                .toString();
    }
}
