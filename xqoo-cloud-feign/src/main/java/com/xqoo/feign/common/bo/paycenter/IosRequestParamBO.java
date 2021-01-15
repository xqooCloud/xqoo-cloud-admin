package com.xqoo.feign.common.bo.paycenter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @ClassName: IosRequestParamBO
 * @Description: Ios客户端内购参数
 * @Author: liangyongpeng
 * @Date: 2020/4/29 15:17
 **/
@ApiModel(value = "Ios客户端内购参数")
public class IosRequestParamBO {

    @ApiModelProperty(value = "交易流水号,用来保存在充值记录中查账用",required = true)
    private String transactionId;

    @ApiModelProperty(value = "支付用户id",required = true)
    private String userId;

    @ApiModelProperty(value = "交易凭证字符串，最大1024",required = true)
    private String applePayLoad;

    @ApiModelProperty(value = "订单号id",required = true)
    private String orderCode;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("transactionId", transactionId)
                .append("userId", userId)
                .append("applePayLoad", applePayLoad)
                .append("orderCode", orderCode)
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

        IosRequestParamBO that = (IosRequestParamBO) o;

        return new EqualsBuilder().append(transactionId, that.transactionId).append(userId, that.userId).append(applePayLoad, that.applePayLoad).append(orderCode, that.orderCode).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(transactionId).append(userId).append(applePayLoad).append(orderCode).toHashCode();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getApplePayLoad() {
        return applePayLoad;
    }

    public void setApplePayLoad(String applePayLoad) {
        this.applePayLoad = applePayLoad;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }
}
