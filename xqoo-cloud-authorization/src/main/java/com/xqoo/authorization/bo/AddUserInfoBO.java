package com.xqoo.authorization.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

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
    public String toString() {
        return "AddUserInfoBO{" +
                "loginId='" + loginId + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", userType=" + userType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddUserInfoBO that = (AddUserInfoBO) o;
        return Objects.equals(loginId, that.loginId) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(password, that.password) &&
                Objects.equals(confirmPassword, that.confirmPassword) &&
                Objects.equals(userType, that.userType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loginId, userName, password, confirmPassword, userType);
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
