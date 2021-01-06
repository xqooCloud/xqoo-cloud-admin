package com.xqoo.authorization.bo;

import com.xqoo.common.page.PageRequestBean;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

public class QueryUserInfoBO extends PageRequestBean {
    private static final long serialVersionUID = 7817705298834474552L;

    @ApiModelProperty("userId")
    private String userId;

    @ApiModelProperty("登录名id")
    private String loginId;

    @ApiModelProperty("用户状态，0-正常，1-封禁，2-停用")
    private Integer userStatus;

    @ApiModelProperty("用户类型，88-后台用户(包含前台用户),10-前台用户-不可登录后台管理系统，9-临时用户，后续有新的用户类型继续在中间相加")
    private Integer userType;

    @ApiModelProperty("用户昵称")
    private String userName;

    @Override
    public String toString() {
        return "QueryUserInfoBO{" +
                "userId='" + userId + '\'' +
                ", loginId='" + loginId + '\'' +
                ", userStatus=" + userStatus +
                ", userType=" + userType +
                ", userName='" + userName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        QueryUserInfoBO that = (QueryUserInfoBO) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(loginId, that.loginId) &&
                Objects.equals(userStatus, that.userStatus) &&
                Objects.equals(userType, that.userType) &&
                Objects.equals(userName, that.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId, loginId, userStatus, userType, userName);
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
}
