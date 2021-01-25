package com.xqoo.authorization.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @author gaoyang
 */
@ApiModel("注册用户实体类")
public class NormalRegisterBO {

    @ApiModelProperty("注册手机号")
    @NotBlank(message = "手机号不能为空")
    @Pattern(message = "手机号格式有误", regexp = "^1[1-9]\\d{9}$")
    private String phonenumber;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "确认密码不能为空")
    private String confirmPassword;

    @NotBlank(message = "验证码不能为空")
    private String code;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("phonenumber", phonenumber)
                .append("password", password)
                .append("confirmPassword", confirmPassword)
                .append("code", code)
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

        NormalRegisterBO that = (NormalRegisterBO) o;

        return new EqualsBuilder().append(phonenumber, that.phonenumber).append(password, that.password).append(confirmPassword, that.confirmPassword).append(code, that.code).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(phonenumber).append(password).append(confirmPassword).append(code).toHashCode();
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
