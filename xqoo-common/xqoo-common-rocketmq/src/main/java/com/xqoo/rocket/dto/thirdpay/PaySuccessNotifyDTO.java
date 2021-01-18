package com.xqoo.rocket.dto.thirdpay;

import java.math.BigDecimal;
import java.util.Objects;

public class PaySuccessNotifyDTO {

    // 支付单号
    private String payTransactionId;

    // 支付平台流水号
    private String transactionId;

    // 支付平台用户唯一标识
    private String ciPayId;

    // 实际收款总额
    private BigDecimal totalAmount;

    // 支付平台
    private String payPlatType;

    public String getPayTransactionId() {
        return payTransactionId;
    }

    public void setPayTransactionId(String payTransactionId) {
        this.payTransactionId = payTransactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getCiPayId() {
        return ciPayId;
    }

    public void setCiPayId(String ciPayId) {
        this.ciPayId = ciPayId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPayPlatType() {
        return payPlatType;
    }

    public void setPayPlatType(String payPlatType) {
        this.payPlatType = payPlatType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaySuccessNotifyDTO that = (PaySuccessNotifyDTO) o;
        return Objects.equals(payTransactionId, that.payTransactionId) &&
                Objects.equals(transactionId, that.transactionId) &&
                Objects.equals(ciPayId, that.ciPayId) &&
                Objects.equals(totalAmount, that.totalAmount) &&
                Objects.equals(payPlatType, that.payPlatType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(payTransactionId, transactionId, ciPayId, totalAmount, payPlatType);
    }
}
