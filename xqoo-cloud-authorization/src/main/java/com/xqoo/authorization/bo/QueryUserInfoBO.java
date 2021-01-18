package com.xqoo.authorization.bo;

import com.xqoo.common.page.PageRequestBean;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

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

    @ApiModelProperty("用户电话号码")
    private String userPhone;

    @ApiModelProperty("用户邮箱")
    private String userEmail;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        QueryUserInfoBO that = (QueryUserInfoBO) o;

        return new EqualsBuilder().appendSuper(super.equals(o)).append(userId, that.userId).append(loginId, that.loginId).append(userStatus, that.userStatus).append(userType, that.userType).append(userName, that.userName).append(userPhone, that.userPhone).append(userEmail, that.userEmail).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).appendSuper(super.hashCode()).append(userId).append(loginId).append(userStatus).append(userType).append(userName).append(userPhone).append(userEmail).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userId", userId)
                .append("loginId", loginId)
                .append("userStatus", userStatus)
                .append("userType", userType)
                .append("userName", userName)
                .append("userPhone", userPhone)
                .append("userEmail", userEmail)
                .toString();
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
}
