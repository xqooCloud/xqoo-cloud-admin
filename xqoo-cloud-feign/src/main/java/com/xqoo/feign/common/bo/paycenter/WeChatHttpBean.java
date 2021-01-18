package com.xqoo.feign.common.bo.paycenter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

@ApiModel
public class WeChatHttpBean implements Serializable {

    @ApiModelProperty(value = "小程序需要的字段")
    private String grantType;

    @ApiModelProperty(value = "encryptedData用户信息（明文,加密数据)")
    private String encryptedData;

    @ApiModelProperty(value = "iv 加密算法的初始向量")
    private String iv;
    @ApiModelProperty(value = "微信appId")
    private String appId;
    @ApiModelProperty(value = "微信secret")
    private String secret;
    @ApiModelProperty(value = "微信code")
    private String code;
    @ApiModelProperty(value = "微信code")
    private String accessToken;
    @ApiModelProperty(value = "微信openId")
    private String openId;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("grantType", grantType)
                .append("encryptedData", encryptedData)
                .append("iv", iv)
                .append("appId", appId)
                .append("secret", secret)
                .append("code", code)
                .append("accessToken", accessToken)
                .append("openId", openId)
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

        WeChatHttpBean that = (WeChatHttpBean) o;

        return new EqualsBuilder().append(grantType, that.grantType).append(encryptedData, that.encryptedData).append(iv, that.iv).append(appId, that.appId).append(secret, that.secret).append(code, that.code).append(accessToken, that.accessToken).append(openId, that.openId).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(grantType).append(encryptedData).append(iv).append(appId).append(secret).append(code).append(accessToken).append(openId).toHashCode();
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getEncryptedData() {
        return encryptedData;
    }

    public void setEncryptedData(String encryptedData) {
        this.encryptedData = encryptedData;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
