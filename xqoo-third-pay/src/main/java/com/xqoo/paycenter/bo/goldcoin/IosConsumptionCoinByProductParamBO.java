package com.xqoo.paycenter.bo.goldcoin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

/**
 * @ClassName: IosConsumptionCoinByProductParamBO
 * @Description: 苹果消耗金币购买产品参数
 * @Author: liangyongpeng
 * @Date: 2020/5/7 10:20
 **/
@ApiModel(value = "苹果消耗金币购买产品参数")
public class IosConsumptionCoinByProductParamBO {

    @ApiModelProperty(value = "购买产品的用户名",required = true)
    private String userName;

    @ApiModelProperty(value = "购买产品的用户id",required = true)
    private String userId;

    @ApiModelProperty(value = "支付单号",required = true)
    private String payTransactionId;

    @ApiModelProperty(value = "本次支付的所有订单的金币总和",required = true)
    private BigDecimal totalCoinConsumption;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userName", userName)
                .append("userId", userId)
                .append("payTransactionId", payTransactionId)
                .append("totalCoinConsumption", totalCoinConsumption)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) return false;

        IosConsumptionCoinByProductParamBO that = (IosConsumptionCoinByProductParamBO) o;

        return new EqualsBuilder().append(userName, that.userName).append(userId, that.userId).append(payTransactionId, that.payTransactionId).append(totalCoinConsumption, that.totalCoinConsumption).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(userName).append(userId).append(payTransactionId).append(totalCoinConsumption).toHashCode();
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

    public String getPayTransactionId() {
        return payTransactionId;
    }

    public void setPayTransactionId(String payTransactionId) {
        this.payTransactionId = payTransactionId;
    }

    public BigDecimal getTotalCoinConsumption() {
        return totalCoinConsumption;
    }

    public void setTotalCoinConsumption(BigDecimal totalCoinConsumption) {
        this.totalCoinConsumption = totalCoinConsumption;
    }
}
