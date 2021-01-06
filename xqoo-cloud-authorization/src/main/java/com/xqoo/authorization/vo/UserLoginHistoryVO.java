package com.xqoo.authorization.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@ApiModel("用户登录历史记录")
public class UserLoginHistoryVO implements Serializable {

    private static final long serialVersionUID = -2224410509197912179L;
    @ApiModelProperty("自增长id")
    private Long id;

    @ApiModelProperty("对应用户id")
    private String userId;

    @ApiModelProperty("登录时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date loginDate;

    @ApiModelProperty("登录ip")
    private String loginIp;

    @ApiModelProperty("使用何种方式登录，具体参见系统中枚举")
    private String loginType;

    @ApiModelProperty("登录方式的名字")
    private String loginTypeName;

    @ApiModelProperty("登录环境，一些文本描述")
    private String loginEnv;

    @ApiModelProperty("登录请求来源，网站，APP等")
    private String loginSource;

    @ApiModelProperty("登录请求来源，网站，APP等")
    private String loginSourceName;

    @Override
    public String toString() {
        return "UserLoginHistoryVO{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", loginDate=" + loginDate +
                ", loginIp='" + loginIp + '\'' +
                ", loginType='" + loginType + '\'' +
                ", loginTypeName='" + loginTypeName + '\'' +
                ", loginEnv='" + loginEnv + '\'' +
                ", loginSource='" + loginSource + '\'' +
                ", loginSourceName='" + loginSourceName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserLoginHistoryVO that = (UserLoginHistoryVO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(loginDate, that.loginDate) &&
                Objects.equals(loginIp, that.loginIp) &&
                Objects.equals(loginType, that.loginType) &&
                Objects.equals(loginTypeName, that.loginTypeName) &&
                Objects.equals(loginEnv, that.loginEnv) &&
                Objects.equals(loginSource, that.loginSource) &&
                Objects.equals(loginSourceName, that.loginSourceName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, loginDate, loginIp, loginType, loginTypeName, loginEnv, loginSource, loginSourceName);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getLoginTypeName() {
        return loginTypeName;
    }

    public void setLoginTypeName(String loginTypeName) {
        this.loginTypeName = loginTypeName;
    }

    public String getLoginEnv() {
        return loginEnv;
    }

    public void setLoginEnv(String loginEnv) {
        this.loginEnv = loginEnv;
    }

    public String getLoginSource() {
        return loginSource;
    }

    public void setLoginSource(String loginSource) {
        this.loginSource = loginSource;
    }

    public String getLoginSourceName() {
        return loginSourceName;
    }

    public void setLoginSourceName(String loginSourceName) {
        this.loginSourceName = loginSourceName;
    }
}
