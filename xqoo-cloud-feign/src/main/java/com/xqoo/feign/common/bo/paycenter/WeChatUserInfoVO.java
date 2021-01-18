package com.xqoo.feign.common.bo.paycenter;

import io.swagger.annotations.ApiModel;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

@ApiModel
public class WeChatUserInfoVO implements Serializable {

    private static final long serialVersionUID = -6193363761143475159L;
    private String openId;
    private String nickName;
    private int sex;
    private String province;
    private String city;
    private String country;
    private String headImgUrl;
    private String unionId;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("openId", openId)
                .append("nickName", nickName)
                .append("sex", sex)
                .append("province", province)
                .append("city", city)
                .append("country", country)
                .append("headImgUrl", headImgUrl)
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

        WeChatUserInfoVO that = (WeChatUserInfoVO) o;

        return new EqualsBuilder().append(sex, that.sex).append(openId, that.openId).append(nickName, that.nickName).append(province, that.province).append(city, that.city).append(country, that.country).append(headImgUrl, that.headImgUrl).append(unionId, that.unionId).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(openId).append(nickName).append(sex).append(province).append(city).append(country).append(headImgUrl).append(unionId).toHashCode();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }
}
