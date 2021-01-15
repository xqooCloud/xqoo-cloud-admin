package com.xqoo.feign.common.bo.paycenter;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 微信支付相关参数bean
 */
public class WxConfigProperties {

    private String appId;
    // APP端appid
    private String appAppId;
    // 公众号端appid
    private String oaAppId;
    // 小程序端appid
    private String spAppId;
    // 商户号
    private String mchId;
    private String feeType;
    private String spbillCreateIp;
    private String notifyUrl;
    private String tradeType;
    // 公钥
    private String apiKey;
    // 私钥(PC)
    private String pcMchKey;
    // 私钥(APP)
    private String appMchKey;
    // 私钥(公众号)
    private String oaMchKey;
    // 私钥(小程序)
    private String spMchKey;
    // 支付网关
    private String gatewayUrl;
    // 退款网关
    private String refundGatewayUrl;
    // 商户证书路径
    private String sslPath;
    // 商户证书密码
    private String sslPassword;
    // 商户付款到个人网关
    private String payToPersonalGateway;
    // ip
    private String ip;
    // 支付商家的名字
    private String subject;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppAppId() {
        return appAppId;
    }

    public void setAppAppId(String appAppId) {
        this.appAppId = appAppId;
    }

    public String getOaAppId() {
        return oaAppId;
    }

    public void setOaAppId(String oaAppId) {
        this.oaAppId = oaAppId;
    }

    public String getSpAppId() {
        return spAppId;
    }

    public void setSpAppId(String spAppId) {
        this.spAppId = spAppId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public String getSpbillCreateIp() {
        return spbillCreateIp;
    }

    public void setSpbillCreateIp(String spbillCreateIp) {
        this.spbillCreateIp = spbillCreateIp;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getPcMchKey() {
        return pcMchKey;
    }

    public void setPcMchKey(String pcMchKey) {
        this.pcMchKey = pcMchKey;
    }

    public String getAppMchKey() {
        return appMchKey;
    }

    public void setAppMchKey(String appMchKey) {
        this.appMchKey = appMchKey;
    }

    public String getOaMchKey() {
        return oaMchKey;
    }

    public void setOaMchKey(String oaMchKey) {
        this.oaMchKey = oaMchKey;
    }

    public String getSpMchKey() {
        return spMchKey;
    }

    public void setSpMchKey(String spMchKey) {
        this.spMchKey = spMchKey;
    }

    public String getGatewayUrl() {
        return gatewayUrl;
    }

    public void setGatewayUrl(String gatewayUrl) {
        this.gatewayUrl = gatewayUrl;
    }

    public String getRefundGatewayUrl() {
        return refundGatewayUrl;
    }

    public void setRefundGatewayUrl(String refundGatewayUrl) {
        this.refundGatewayUrl = refundGatewayUrl;
    }

    public String getSslPath() {
        return sslPath;
    }

    public void setSslPath(String sslPath) {
        this.sslPath = sslPath;
    }

    public String getSslPassword() {
        return sslPassword;
    }

    public void setSslPassword(String sslPassword) {
        this.sslPassword = sslPassword;
    }

    public String getPayToPersonalGateway() {
        return payToPersonalGateway;
    }

    public void setPayToPersonalGateway(String payToPersonalGateway) {
        this.payToPersonalGateway = payToPersonalGateway;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WxConfigProperties that = (WxConfigProperties) o;

        return new EqualsBuilder().append(appId, that.appId).append(appAppId, that.appAppId).append(oaAppId, that.oaAppId).append(spAppId, that.spAppId).append(mchId, that.mchId).append(feeType, that.feeType).append(spbillCreateIp, that.spbillCreateIp).append(notifyUrl, that.notifyUrl).append(tradeType, that.tradeType).append(apiKey, that.apiKey).append(pcMchKey, that.pcMchKey).append(appMchKey, that.appMchKey).append(oaMchKey, that.oaMchKey).append(spMchKey, that.spMchKey).append(gatewayUrl, that.gatewayUrl).append(refundGatewayUrl, that.refundGatewayUrl).append(sslPath, that.sslPath).append(sslPassword, that.sslPassword).append(payToPersonalGateway, that.payToPersonalGateway).append(ip, that.ip).append(subject, that.subject).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(appId).append(appAppId).append(oaAppId).append(spAppId).append(mchId).append(feeType).append(spbillCreateIp).append(notifyUrl).append(tradeType).append(apiKey).append(pcMchKey).append(appMchKey).append(oaMchKey).append(spMchKey).append(gatewayUrl).append(refundGatewayUrl).append(sslPath).append(sslPassword).append(payToPersonalGateway).append(ip).append(subject).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("appId", appId)
                .append("appAppId", appAppId)
                .append("oaAppId", oaAppId)
                .append("spAppId", spAppId)
                .append("mchId", mchId)
                .append("feeType", feeType)
                .append("spbillCreateIp", spbillCreateIp)
                .append("notifyUrl", notifyUrl)
                .append("tradeType", tradeType)
                .append("apiKey", apiKey)
                .append("pcMchKey", pcMchKey)
                .append("appMchKey", appMchKey)
                .append("oaMchKey", oaMchKey)
                .append("spMchKey", spMchKey)
                .append("gatewayUrl", gatewayUrl)
                .append("refundGatewayUrl", refundGatewayUrl)
                .append("sslPath", sslPath)
                .append("sslPassword", sslPassword)
                .append("payToPersonalGateway", payToPersonalGateway)
                .append("ip", ip)
                .append("subject", subject)
                .toString();
    }
}
