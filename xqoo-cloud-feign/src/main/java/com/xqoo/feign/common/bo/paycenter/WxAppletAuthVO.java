package com.xqoo.feign.common.bo.paycenter;

import io.swagger.annotations.ApiModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

@ApiModel
public class WxAppletAuthVO implements Serializable {

    private static final long serialVersionUID = 6043302683138231669L;

    private String openId;
    private String sessionKey;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("openId", openId)
                .append("sessionKey", sessionKey)
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

        WxAppletAuthVO that = (WxAppletAuthVO) o;

        return new EqualsBuilder().append(openId, that.openId).append(sessionKey, that.sessionKey).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(openId).append(sessionKey).toHashCode();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }
}
