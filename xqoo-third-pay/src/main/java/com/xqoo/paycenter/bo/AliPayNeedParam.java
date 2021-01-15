package com.xqoo.paycenter.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

@ApiModel("支付宝支付前台参数")
public class AliPayNeedParam {

    @ApiModelProperty("发起支付人姓名")
    private String userName;

    @ApiModelProperty("发起支付人id")
    private String userId;

    @ApiModelProperty("发起支付的平台，PC-电脑WEB端，APP-手机APP，MOBILE-手机WEB端")
    private String deviceType;

    @ApiModelProperty("系统生成的支付单号")
    private String payTransactionId;

    @ApiModelProperty("此次支付金额")
    private BigDecimal payAmount;

    @ApiModelProperty("支付说明")
    private String payComment;

    @ApiModelProperty("支付完后返回的页面")
    private String returnUrl;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userName", userName)
                .append("userId", userId)
                .append("deviceType", deviceType)
                .append("payTransactionId", payTransactionId)
                .append("payAmount", payAmount)
                .append("payComment", payComment)
                .append("returnUrl", returnUrl)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) return false;

        AliPayNeedParam that = (AliPayNeedParam) o;

        return new EqualsBuilder().append(userName, that.userName).append(userId, that.userId).append(deviceType, that.deviceType).append(payTransactionId, that.payTransactionId).append(payAmount, that.payAmount).append(payComment, that.payComment).append(returnUrl, that.returnUrl).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(userName).append(userId).append(deviceType).append(payTransactionId).append(payAmount).append(payComment).append(returnUrl).toHashCode();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getPayTransactionId() {
        return payTransactionId;
    }

    public void setPayTransactionId(String payTransactionId) {
        this.payTransactionId = payTransactionId;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public String getPayComment() {
        return payComment;
    }

    public void setPayComment(String payComment) {
        this.payComment = payComment;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }
}
