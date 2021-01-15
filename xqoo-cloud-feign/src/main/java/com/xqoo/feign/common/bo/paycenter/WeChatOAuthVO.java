package com.xqoo.feign.common.bo.paycenter;

import io.swagger.annotations.ApiModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

@ApiModel
public class WeChatOAuthVO implements Serializable {

    private static final long serialVersionUID = 3540601909886482551L;
    //normal response
    private String accessToken;
    private Integer expiresIn;
    private String refreshToken;
    private String openId;
    private String scope;
    private String unionId;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("accessToken", accessToken)
                .append("expiresIn", expiresIn)
                .append("refreshToken", refreshToken)
                .append("openId", openId)
                .append("scope", scope)
                .append("unionId", unionId)
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

        WeChatOAuthVO that = (WeChatOAuthVO) o;

        return new EqualsBuilder().append(accessToken, that.accessToken).append(expiresIn, that.expiresIn).append(refreshToken, that.refreshToken).append(openId, that.openId).append(scope, that.scope).append(unionId, that.unionId).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(accessToken).append(expiresIn).append(refreshToken).append(openId).append(scope).append(unionId).toHashCode();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }
}
