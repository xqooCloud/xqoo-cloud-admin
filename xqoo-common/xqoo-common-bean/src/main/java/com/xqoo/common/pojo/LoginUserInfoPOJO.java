package com.xqoo.common.pojo;

import com.xqoo.common.entity.SysUserBaseInfoEntity;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author Gao by
 * @version 1.0
 */
public class LoginUserInfoPOJO implements Serializable {

    private static final long serialVersionUID = -5292113685476912557L;
    // 是否获取到用户信息
    private boolean getInfoSuccess;

    // 返回的状态码
    private HttpStatus code;

    // 处理结果信息
    private String handleMessage;

    // 用户userId
    private String userId;

    private String userPhone;

    private String userEmail;

    // 用户系统信息
    private SysUserBaseInfoEntity sysUserBaseInfoEntity;

    // 用户角色id数组
    private List<Integer> roleIds;

    // 用户角色名数组
    private List<String> roleNames;

    // 是否超级管理员
    private Boolean admin;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LoginUserInfoPOJO that = (LoginUserInfoPOJO) o;
        return getInfoSuccess == that.getInfoSuccess && code == that.code && Objects.equals(handleMessage, that.handleMessage) && Objects.equals(userId, that.userId) && Objects.equals(userPhone, that.userPhone) && Objects.equals(userEmail, that.userEmail) && Objects.equals(sysUserBaseInfoEntity, that.sysUserBaseInfoEntity) && Objects.equals(roleIds, that.roleIds) && Objects.equals(roleNames, that.roleNames) && Objects.equals(admin, that.admin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInfoSuccess, code, handleMessage, userId, userPhone, userEmail, sysUserBaseInfoEntity, roleIds, roleNames, admin);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LoginUserInfoPOJO{");
        sb.append("getInfoSuccess=").append(getInfoSuccess);
        sb.append(", code=").append(code);
        sb.append(", handleMessage='").append(handleMessage).append('\'');
        sb.append(", userId='").append(userId).append('\'');
        sb.append(", userPhone='").append(userPhone).append('\'');
        sb.append(", userEmail='").append(userEmail).append('\'');
        sb.append(", sysUserBaseInfoEntity=").append(sysUserBaseInfoEntity);
        sb.append(", roleIds=").append(roleIds);
        sb.append(", roleNames=").append(roleNames);
        sb.append(", admin=").append(admin);
        sb.append('}');
        return sb.toString();
    }

    /**
     * 需要其他信息请继续往下加
     */


    public boolean isGetInfoSuccess() {
        return getInfoSuccess;
    }

    public boolean isGetInfoFail() {
        if(getInfoSuccess){
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

    public void setGetInfoSuccess(boolean getInfoSuccess) {
        this.getInfoSuccess = getInfoSuccess;
    }

    public String getHandleMessage() {
        return handleMessage;
    }

    public void setHandleMessage(String handleMessage) {
        this.handleMessage = handleMessage;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public SysUserBaseInfoEntity getSysUserBaseInfoEntity() {
        return sysUserBaseInfoEntity;
    }

    public void setSysUserBaseInfoEntity(SysUserBaseInfoEntity sysUserBaseInfoEntity) {
        this.sysUserBaseInfoEntity = sysUserBaseInfoEntity;
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
