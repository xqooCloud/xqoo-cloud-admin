package com.xqoo.authorization.bo;

import com.xqoo.common.page.PageRequestBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

@ApiModel("用户登录历史查询实体")
public class QueryUserLoginHistoryBO extends PageRequestBean {
    private static final long serialVersionUID = 303578765433435102L;

    @ApiModelProperty("使用何种方式登录，具体参见系统中枚举")
    private String loginType;

    @ApiModelProperty("登录的ip，支持右模糊匹配")
    private String loginIp;

    @ApiModelProperty("登录请求来源，网站，APP等，详见系统中枚举")
    private String loginSource;

    @ApiModelProperty("登录用户的id")
    private String userId;

    @Override
    public String
    toString() {
        return "QueryUserLoginHistoryBO{" +
                "loginType='" + loginType + '\'' +
                ", loginIp='" + loginIp + '\'' +
                ", loginSource='" + loginSource + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        QueryUserLoginHistoryBO that = (QueryUserLoginHistoryBO) o;
        return Objects.equals(loginType, that.loginType) &&
                Objects.equals(loginIp, that.loginIp) &&
                Objects.equals(loginSource, that.loginSource) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), loginType, loginIp, loginSource, userId);
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getLoginSource() {
        return loginSource;
    }

    public void setLoginSource(String loginSource) {
        this.loginSource = loginSource;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
