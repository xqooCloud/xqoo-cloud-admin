package com.xqoo.authorization.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ApiModel("登录后返回的实体")
public class LoginReturnEntity implements Serializable {


    private static final long serialVersionUID = -5895826621975413005L;
    @ApiModelProperty("异常登录所需验证码")
    private String errCode;

    @ApiModelProperty("生成的token")
    private String token;

    @ApiModelProperty("登录的时间")
    private Long loginTime;

    @ApiModelProperty("登录的ip")
    private String loginIp;

    @ApiModelProperty("登录的环境")
    private String loginEnv;

    @ApiModelProperty("当前登录人userId")
    private String userId;

    @ApiModelProperty("上一次登录时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date lastLogin;

    @ApiModelProperty("登录人姓名")
    private String userName;

    @ApiModelProperty("用户电话号码")
    private String userPhone;

    @ApiModelProperty("用户邮箱")
    private String userEmail;

    @ApiModelProperty("登录人头像地址")
    private String avatar;

    @ApiModelProperty("角色id数组")
    private List<Integer> roleIds;

    @ApiModelProperty("角色名数组")
    private List<String> roleNames;

    @ApiModelProperty("是否超级管理员")
    private Boolean admin;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LoginReturnEntity that = (LoginReturnEntity) o;

        return new EqualsBuilder().append(errCode, that.errCode).append(token, that.token).append(loginTime, that.loginTime).append(loginIp, that.loginIp).append(loginEnv, that.loginEnv).append(userId, that.userId).append(lastLogin, that.lastLogin).append(userName, that.userName).append(userPhone, that.userPhone).append(userEmail, that.userEmail).append(avatar, that.avatar).append(roleIds, that.roleIds).append(roleNames, that.roleNames).append(admin, that.admin).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(errCode).append(token).append(loginTime).append(loginIp).append(loginEnv).append(userId).append(lastLogin).append(userName).append(userPhone).append(userEmail).append(avatar).append(roleIds).append(roleNames).append(admin).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("errCode", errCode)
                .append("token", token)
                .append("loginTime", loginTime)
                .append("loginIp", loginIp)
                .append("loginEnv", loginEnv)
                .append("userId", userId)
                .append("lastLogin", lastLogin)
                .append("userName", userName)
                .append("userPhone", userPhone)
                .append("userEmail", userEmail)
                .append("avatar", avatar)
                .append("roleIds", roleIds)
                .append("roleNames", roleNames)
                .append("admin", admin)
                .toString();
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getLoginEnv() {
        return loginEnv;
    }

    public void setLoginEnv(String loginEnv) {
        this.loginEnv = loginEnv;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<Integer> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Integer> roleIds) {
        this.roleIds = roleIds;
    }

    public List<String> getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(List<String> roleNames) {
        this.roleNames = roleNames;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
