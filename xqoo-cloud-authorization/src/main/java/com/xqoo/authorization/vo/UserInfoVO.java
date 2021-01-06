package com.xqoo.authorization.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@ApiModel("用户列表实体vo")
public class UserInfoVO implements Serializable {

    private static final long serialVersionUID = -2043462610167221468L;
    @ApiModelProperty("用户id")
    private String userId;

    @ApiModelProperty("登录名，为考虑一手机多用户，此处必须与手机并存唯一，不能使用特殊字符")
    private String loginId;

    @ApiModelProperty("用户状态，0-正常，1-封禁，2-停用")
    private Integer userStatus;

    @ApiModelProperty("用户状态中文，0-正常，1-封禁，2-停用")
    private String userStatusName;

    @ApiModelProperty("用户类型，88-后台用户(包含前台用户),10-前台用户-不可登录后台管理系统，9-临时用户，后续有新的用户类型继续在中间相加")
    private Integer userType;

    @ApiModelProperty("用户类型中文，88-后台用户(包含前台用户),10-前台用户-不可登录后台管理系统，9-临时用户，后续有新的用户类型继续在中间相加")
    private String userTypeName;

    @ApiModelProperty("用户名，最多32字")
    private String userName;

    @ApiModelProperty("是否选中用户，默认均为false，特殊情况标记为true")
    private Boolean checked;

    @ApiModelProperty("拥有角色数量统计")
    private Integer roleCount;

    @ApiModelProperty("上次登录时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date lastLoginTime;

    @ApiModelProperty("头像地址")
    private String profileUrl;

    @ApiModelProperty("创建人")
    private String createBy;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date createDate;

    @ApiModelProperty("最近修改人")
    private String updateBy;

    @ApiModelProperty("最近修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date updateDate;

    @ApiModelProperty("备注信息")
    private String remarkTips;

    @Override
    public String toString() {
        return "UserInfoVO{" +
                "userId='" + userId + '\'' +
                ", loginId='" + loginId + '\'' +
                ", userStatus=" + userStatus +
                ", userStatusName='" + userStatusName + '\'' +
                ", userType=" + userType +
                ", userTypeName='" + userTypeName + '\'' +
                ", userName='" + userName + '\'' +
                ", checked=" + checked +
                ", roleCount=" + roleCount +
                ", lastLoginTime=" + lastLoginTime +
                ", profileUrl='" + profileUrl + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createDate=" + createDate +
                ", updateBy='" + updateBy + '\'' +
                ", updateDate=" + updateDate +
                ", remarkTips='" + remarkTips + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfoVO that = (UserInfoVO) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(loginId, that.loginId) &&
                Objects.equals(userStatus, that.userStatus) &&
                Objects.equals(userStatusName, that.userStatusName) &&
                Objects.equals(userType, that.userType) &&
                Objects.equals(userTypeName, that.userTypeName) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(checked, that.checked) &&
                Objects.equals(roleCount, that.roleCount) &&
                Objects.equals(lastLoginTime, that.lastLoginTime) &&
                Objects.equals(profileUrl, that.profileUrl) &&
                Objects.equals(createBy, that.createBy) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(updateBy, that.updateBy) &&
                Objects.equals(updateDate, that.updateDate) &&
                Objects.equals(remarkTips, that.remarkTips);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, loginId, userStatus, userStatusName, userType, userTypeName, userName, checked, roleCount, lastLoginTime, profileUrl, createBy, createDate, updateBy, updateDate, remarkTips);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getUserStatusName() {
        return userStatusName;
    }

    public void setUserStatusName(String userStatusName) {
        this.userStatusName = userStatusName;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Integer getRoleCount() {
        return roleCount;
    }

    public void setRoleCount(Integer roleCount) {
        this.roleCount = roleCount;
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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarkTips() {
        return remarkTips;
    }

    public void setRemarkTips(String remarkTips) {
        this.remarkTips = remarkTips;
    }
}
