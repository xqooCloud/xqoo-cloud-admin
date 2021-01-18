package com.xqoo.paycenter.bean;

import com.xqoo.paycenter.constant.PayConfigInitConstant;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Map;

/**
 * 阿里支付相关参数bean
 */
public class AliPayPropertiesBean {

    private String appId;

    // 商户私钥
    private String privateKey;

    // 支付宝公钥
    private String publicKey;

    private String charset;

    private String format;

    // 支付网关
    private String gatewayUrl;

    private String signType;

    private String productCode;

    //支持退款的天数
    private String timeoutExpress;

    // 默认返回网页
    private String returnUrl;

    // 支付结果回调地址
    private String notifyUrl;

    // 支付商家的名字
    private String subject;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getGatewayUrl() {
        return gatewayUrl;
    }

    public void setGatewayUrl(String gatewayUrl) {
        this.gatewayUrl = gatewayUrl;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getTimeoutExpress() {
        return timeoutExpress;
    }

    public void setTimeoutExpress(String timeoutExpress) {
        this.timeoutExpress = timeoutExpress;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
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

        AliPayPropertiesBean that = (AliPayPropertiesBean) o;

        return new EqualsBuilder().append(appId, that.appId).append(privateKey, that.privateKey).append(publicKey, that.publicKey).append(charset, that.charset).append(format, that.format).append(gatewayUrl, that.gatewayUrl).append(signType, that.signType).append(productCode, that.productCode).append(timeoutExpress, that.timeoutExpress).append(returnUrl, that.returnUrl).append(notifyUrl, that.notifyUrl).append(subject, that.subject).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(appId).append(privateKey).append(publicKey).append(charset).append(format).append(gatewayUrl).append(signType).append(productCode).append(timeoutExpress).append(returnUrl).append(notifyUrl).append(subject).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("appId", appId)
                .append("privateKey", privateKey)
                .append("publicKey", publicKey)
                .append("charset", charset)
                .append("format", format)
                .append("gatewayUrl", gatewayUrl)
                .append("signType", signType)
                .append("productCode", productCode)
                .append("timeoutExpress", timeoutExpress)
                .append("returnUrl", returnUrl)
                .append("notifyUrl", notifyUrl)
                .append("subject", subject)
                .toString();
    }

    public void fromMap(Map<String, String> map){
        this.setAppId(map.getOrDefault("appId", PayConfigInitConstant.AliPayConfigDefult.APPID));
        this.setPrivateKey(map.getOrDefault("privateKey", PayConfigInitConstant.AliPayConfigDefult.PRIVATE_KEY));
        this.setPublicKey(map.getOrDefault("publicKey", PayConfigInitConstant.AliPayConfigDefult.PUBLIC_KEY));
        this.setCharset(map.getOrDefault("charset", PayConfigInitConstant.AliPayConfigDefult.CHARSET));
        this.setFormat(map.getOrDefault("format", PayConfigInitConstant.AliPayConfigDefult.FROMAT));
        this.setGatewayUrl(map.getOrDefault("gatewayUrl", PayConfigInitConstant.AliPayConfigDefult.GATEWAY_URL));
        this.setSignType(map.getOrDefault("signType", PayConfigInitConstant.AliPayConfigDefult.SIGN_TYPE));
        this.setProductCode(map.getOrDefault("productCode", PayConfigInitConstant.AliPayConfigDefult.PRODUCT_CODE));
        this.setTimeoutExpress(map.getOrDefault("timeoutExpress", PayConfigInitConstant.AliPayConfigDefult.TIMEOUT_EXPRESS));
        this.setReturnUrl(map.getOrDefault("returnUrl", PayConfigInitConstant.AliPayConfigDefult.RETURN_URL));
        this.setNotifyUrl(map.getOrDefault("notifyUrl", PayConfigInitConstant.AliPayConfigDefult.NOTIFY_URL));
        this.setSubject(map.getOrDefault("subject", PayConfigInitConstant.AliPayConfigDefult.SUBJECT));
    }
}
