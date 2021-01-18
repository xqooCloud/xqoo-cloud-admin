package com.xqoo.authorization.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ApiModel("新增用户实体")
public class AddUserInfoBO implements Serializable {

    private static final long serialVersionUID = 3413483763539290513L;
    @ApiModelProperty("登录名，不可缺失")
    @NotNull(message = "loginId不能为空")
    @NotBlank(message = "loginId不能为空")
    @Pattern(regexp = "[^`$%^&+=\\[\\]~！￥…*（）—{}【】‘；：”“’。，、？]+", message = "登录名不能含有特殊字符")
    private String loginId;

    @ApiModelProperty("用户名，最多32字，不可为空")
    @NotNull(message = "用户名不能为空")
    @NotBlank(message = "登录名不能为空")
    @Size(max = 32, min = 2, message = "用户名字数最少3字，组多32字")
    private String userName;

    @ApiModelProperty("用户电话，最少4位最多16位")
    @Size(max = 16, min = 4, message = "用户名字数最少3字，组多32字")
    private String userPhone;

    @ApiModelProperty("用户用户邮箱")
    @Pattern(regexp = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", message = "邮箱格式有误")
    private String userEmail;

    @ApiModelProperty("密码，")
    @NotNull(message = "密码不能为空")
    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty("确认密码，用来比对")
    @NotNull(message = "确认密码不能为空")
    @NotBlank(message = "确认密码不能为空")
    private String confirmPassword;

    @ApiModelProperty("用户类型，88-后台用户(包含前台用户),10-前台用户-不可登录后台管理系统，后台用户默认添加一条控制台角色，前台用户则不添加")
    private Integer userType;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AddUserInfoBO that = (AddUserInfoBO) o;

        return new EqualsBuilder().append(loginId, that.loginId).append(userName, that.userName).append(userPhone, that.userPhone).append(userEmail, that.userEmail).append(password, that.password).append(confirmPassword, that.confirmPassword).append(userType, that.userType).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(loginId).append(userName).append(userPhone).append(userEmail).append(password).append(confirmPassword).append(userType).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("loginId", loginId)
                .append("userName", userName)
                .append("userPhone", userPhone)
                .append("userEmail", userEmail)
                .append("password", password)
                .append("confirmPassword", confirmPassword)
                .append("userType", userType)
                .toString();
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}
