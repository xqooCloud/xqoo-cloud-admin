package com.xqoo.paycenter.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@ApiModel("微信前端公开参数实体")
public class WeixinConfigPropertiesVO {

    @ApiModelProperty("PC appid")
    private String appId;
    @ApiModelProperty("App appid")
    private String appAppId;
    @ApiModelProperty("公众号 appid")
    private String oaAppId;
    @ApiModelProperty("小程序 appid")
    private String spAppId;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("appId", appId)
                .append("appAppId", appAppId)
                .append("oaAppId", oaAppId)
                .append("spAppId", spAppId)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) return false;

        WeixinConfigPropertiesVO that = (WeixinConfigPropertiesVO) o;

        return new EqualsBuilder().append(appId, that.appId).append(appAppId, that.appAppId).append(oaAppId, that.oaAppId).append(spAppId, that.spAppId).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(appId).append(appAppId).append(oaAppId).append(spAppId).toHashCode();
    }

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
}
