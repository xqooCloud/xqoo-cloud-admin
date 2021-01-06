package com.xqoo.common.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.Objects;

@ApiModel("用户系统基本信息")
public class SysUserBaseInfoEntity {

    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("逻辑删除标记，0-未删除，1-已删除")
    private Integer delFlag;

    @ApiModelProperty("登录名，为考虑一手机多用户，此处必须与手机并存唯一，不能使用特殊字符")
    private String loginId;

    @ApiModelProperty("用户状态，0-正常，1-封禁，2-停用")
    private Integer userStatus;

    @ApiModelProperty("用户类型，99-超级管理员，88-后台用户(包含前台用户),10-前台用户-不可登录后台管理系统，9-临时用户，后续有新的用户类型继续在中间相加")
    private Integer userType;

    @ApiModelProperty("用户名，最多32字")
    private String userName;

    @ApiModelProperty("密码，允许为空，为空时密码登录将无效")
    private String password;

    @ApiModelProperty("盐，随机8位字母+数字")
    private String salt;

    @ApiModelProperty("上次登录时间")
    private Date lastLoginTime;

    @ApiModelProperty("头像地址")
    private String profileUrl;

    @Override
    public String toString() {
        return "SysUserBaseInfoEntity{" +
                "userId='" + userId + '\'' +
                ", delFlag=" + delFlag +
                ", loginId='" + loginId + '\'' +
                ", userStatus=" + userStatus +
                ", userType=" + userType +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", lastLoginTime=" + lastLoginTime +
                ", profileUrl='" + profileUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysUserBaseInfoEntity that = (SysUserBaseInfoEntity) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(delFlag, that.delFlag) &&
                Objects.equals(loginId, that.loginId) &&
                Objects.equals(userStatus, that.userStatus) &&
                Objects.equals(userType, that.userType) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(password, that.password) &&
                Objects.equals(salt, that.salt) &&
                Objects.equals(lastLoginTime, that.lastLoginTime) &&
                Objects.equals(profileUrl, that.profileUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, delFlag, loginId, userStatus, userType, userName, password, salt, lastLoginTime, profileUrl);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }
}
