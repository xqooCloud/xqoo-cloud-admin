package com.xqoo.common.dto;

import org.springframework.http.HttpStatus;

import java.util.Objects;

public class AuthenDTO {
    // 是否通过
    private boolean success;
    // 说明信息
    private String message;
    // 网络状态码
    private HttpStatus status;
    // 鉴权用户
    private String userId;
    // 用户登录类型
    private String loginSource;
    // 鉴权用户名
    private String userName;

    public AuthenDTO() {
    }

    @Override
    public String toString() {
        return "AuthenDTO{" +
                "success=" + success +
                ", message='" + message + '\'' +
                ", status=" + status +
                ", userId='" + userId + '\'' +
                ", loginSource='" + loginSource + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthenDTO authenDTO = (AuthenDTO) o;
        return success == authenDTO.success &&
                Objects.equals(message, authenDTO.message) &&
                status == authenDTO.status &&
                Objects.equals(userId, authenDTO.userId) &&
                Objects.equals(loginSource, authenDTO.loginSource) &&
                Objects.equals(userName, authenDTO.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(success, message, status, userId, loginSource, userName);
    }

    public boolean isSuccess() {
        return success;
    }

    public boolean isNotSuccess() {
        if(this.success){
            return false;
        }
        return true;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoginSource() {
        return loginSource;
    }

    public void setLoginSource(String loginSource) {
        this.loginSource = loginSource;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
