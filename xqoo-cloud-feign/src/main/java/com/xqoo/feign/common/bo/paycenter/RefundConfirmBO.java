package com.xqoo.feign.common.bo.paycenter;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

public class RefundConfirmBO {

    private String payTransactionId;

    private String orderDetailId;

    private BigDecimal refundAmount;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("payTransactionId", payTransactionId)
                .append("orderDetailId", orderDetailId)
                .append("refundAmount", refundAmount)
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

        RefundConfirmBO that = (RefundConfirmBO) o;

        return new EqualsBuilder().append(payTransactionId, that.payTransactionId).append(orderDetailId, that.orderDetailId).append(refundAmount, that.refundAmount).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(payTransactionId).append(orderDetailId).append(refundAmount).toHashCode();
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
}
