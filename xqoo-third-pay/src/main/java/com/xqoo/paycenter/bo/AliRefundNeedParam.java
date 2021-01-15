package com.xqoo.paycenter.bo;

import com.xqoo.paycenter.utils.WxPayUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;
import java.util.Map;

@ApiModel("支付宝退款前台参数")
public class AliRefundNeedParam {

    @ApiModelProperty("系统生成的支付单号")
    private String payTransactionId;

    @ApiModelProperty("退款的明细订单号")
    private String orderDetailId;

    @ApiModelProperty("退款金额")
    private BigDecimal refundAmount;

    @ApiModelProperty("退款原因")
    private String refundReason;

    @ApiModelProperty("退款发起方")
    private String refundLaunch;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("payTransactionId", payTransactionId)
                .append("orderDetailId", orderDetailId)
                .append("refundAmount", refundAmount)
                .append("refundReason", refundReason)
                .append("refundLaunch", refundLaunch)
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

        AliRefundNeedParam that = (AliRefundNeedParam) o;

        return new EqualsBuilder().append(payTransactionId, that.payTransactionId).append(orderDetailId, that.orderDetailId).append(refundAmount, that.refundAmount).append(refundReason, that.refundReason).append(refundLaunch, that.refundLaunch).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(payTransactionId).append(orderDetailId).append(refundAmount).append(refundReason).append(refundLaunch).toHashCode();
    }

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
        this.setRefundReason(map.getOrDefault("refundReason",null).toString());
        this.setRefundLaunch(map.getOrDefault("refundReason",null).toString());
    }
}
