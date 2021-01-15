package com.xqoo.paycenter.bo;

import com.xqoo.common.page.PageRequestBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

@ApiModel("支付流水查询实体")
public class PayWaterFlowQueryBO extends PageRequestBean {

    @ApiModelProperty("系统申请付款预付单流水号")
    private String payTransactionId;

    @ApiModelProperty("所用支付平台，WxPay-微信支付，AliPay-支付宝支付，IOSApp-苹果内购，UnionPay-银联支付")
    private String payPlat;

    @ApiModelProperty("支付状态，0-已下单未支付，1-已支付")
    private Integer payStatus;

    @ApiModelProperty("交易平台的支付流水号")
    private String transactionId;

    @ApiModelProperty("交易平台客户的唯一标识")
    private String clientPayId;

    @ApiModelProperty("是否有退款，0-未退款，1-退过款")
    private Integer refundStatus;

    @ApiModelProperty("支付设备，PC-电脑浏览器，APP-手机APP，MOBILE-移动端网页，JSAPI-公众号，SMP-小程序，FACE-POS机扫码")
    private String payDevice;

    @ApiModelProperty("起始时间")
    private Date startDate;

    @ApiModelProperty("结束时间")
    private Date endDate;

    public String getPayTransactionId() {
        return payTransactionId;
    }

    public void setPayTransactionId(String payTransactionId) {
        this.payTransactionId = payTransactionId;
    }

    public String getPayPlat() {
        return payPlat;
    }

    public void setPayPlat(String payPlat) {
        this.payPlat = payPlat;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getClientPayId() {
        return clientPayId;
    }

    public void setClientPayId(String clientPayId) {
        this.clientPayId = clientPayId;
    }

    public Integer getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(Integer refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getPayDevice() {
        return payDevice;
    }

    public void setPayDevice(String payDevice) {
        this.payDevice = payDevice;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PayWaterFlowQueryBO that = (PayWaterFlowQueryBO) o;

        return new EqualsBuilder().appendSuper(super.equals(o)).append(payTransactionId, that.payTransactionId).append(payPlat, that.payPlat).append(payStatus, that.payStatus).append(transactionId, that.transactionId).append(clientPayId, that.clientPayId).append(refundStatus, that.refundStatus).append(payDevice, that.payDevice).append(startDate, that.startDate).append(endDate, that.endDate).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).appendSuper(super.hashCode()).append(payTransactionId).append(payPlat).append(payStatus).append(transactionId).append(clientPayId).append(refundStatus).append(payDevice).append(startDate).append(endDate).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("payTransactionId", payTransactionId)
                .append("payPlat", payPlat)
                .append("payStatus", payStatus)
                .append("transactionId", transactionId)
                .append("clientPayId", clientPayId)
                .append("refundStatus", refundStatus)
                .append("payDevice", payDevice)
                .append("startDate", startDate)
                .append("endDate", endDate)
                .toString();
    }
}
