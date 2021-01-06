package com.xqoo.authorization.pojo;

import com.xqoo.common.enums.LoginSourceEnum;
import com.xqoo.common.core.entity.CurrentUser;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.Objects;

/**
 * @author Gao by
 * @version 1.0
 */
public class ValidateLoginEffectivePOJO {

    // 授权请求是否有效
    private boolean effective;

    // 验证结果状态嘛，以Http状态码为准
    private HttpStatus code;

    // 验证返回信息
    private String message;

    // 用户id
    private String userId;

    // 上次登录时间")
    private Date lastLoginTime;

    // 错误验证码
    private String errCode;

    // 登录来源
    private LoginSourceEnum loginSourceEnum;

    // 登录用户信息
    private CurrentUser currentUser;

    // 错误次数
    private Integer errCount;


    @Override
    public String toString() {
        return "ValidateLoginEffectivePOJO{" +
                "effective=" + effective +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", userId='" + userId + '\'' +
                ", lastLoginTime=" + lastLoginTime +
                ", errCode='" + errCode + '\'' +
                ", loginSourceEnum=" + loginSourceEnum +
                ", currentUser=" + currentUser +
                ", errCount=" + errCount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValidateLoginEffectivePOJO pojo = (ValidateLoginEffectivePOJO) o;
        return effective == pojo.effective &&
                code == pojo.code &&
                Objects.equals(message, pojo.message) &&
                Objects.equals(userId, pojo.userId) &&
                Objects.equals(lastLoginTime, pojo.lastLoginTime) &&
                Objects.equals(errCode, pojo.errCode) &&
                loginSourceEnum == pojo.loginSourceEnum &&
                Objects.equals(currentUser, pojo.currentUser) &&
                Objects.equals(errCount, pojo.errCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(effective, code, message, userId, lastLoginTime, errCode, loginSourceEnum, currentUser, errCount);
    }

    /**
     * 下面接着权限、等等需要处理的基础信息
     */
    public boolean isEffective() {
        return effective;
    }

    public void setEffective(boolean effective) {
        this.effective = effective;
    }

    public boolean isNotEffective () {
        if(this.effective){
            return false;
        }
        return true;
    }
    public HttpStatus getCode() {
        return code;
    }

    public void setCode(HttpStatus code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public LoginSourceEnum getLoginSourceEnum() {
        return loginSourceEnum;
    }

    public void setLoginSourceEnum(LoginSourceEnum loginSourceEnum) {
        this.loginSourceEnum = loginSourceEnum;
    }

    public CurrentUser getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    public Integer getErrCount() {
        return errCount;
    }

    public void setErrCount(Integer errCount) {
        this.errCount = errCount;
    }
}
