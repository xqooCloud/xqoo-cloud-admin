package com.xqoo.paycenter.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@ApiModel("支付配查询实体")
public class PayConfigQueryBO {
    @ApiModelProperty("支付平台，WxPay-微信支付,AliPay-支付宝，IOSApp-ios内购项目")
    private String payPlat;

    @ApiModelProperty("使用状态，0-历史版本，1-当前版本，只能存在一个当前版本")
    private Integer activeState;

    @ApiModelProperty("产品状态，0-草稿，1-已发布，2-已废弃")
    private Integer configStatus;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("payPlat", payPlat)
                .append("activeState", activeState)
                .append("configStatus", configStatus)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) return false;

        PayConfigQueryBO that = (PayConfigQueryBO) o;

        return new EqualsBuilder().append(payPlat, that.payPlat).append(activeState, that.activeState).append(configStatus, that.configStatus).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(payPlat).append(activeState).append(configStatus).toHashCode();
    }

    public String getPayPlat() {
        return payPlat;
    }

    public void setPayPlat(String payPlat) {
        this.payPlat = payPlat;
    }

    public Integer getActiveState() {
        return activeState;
    }

    public void setActiveState(Integer activeState) {
        this.activeState = activeState;
    }

    public Integer getConfigStatus() {
        return configStatus;
    }

    public void setConfigStatus(Integer configStatus) {
        this.configStatus = configStatus;
    }
}
