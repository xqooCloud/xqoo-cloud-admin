package com.xqoo.common.core.entity;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import com.xqoo.common.core.utils.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.*;

/**
 * @author mumu
 * @date 2019/11/08 上午11:17
 **/
@ApiModel("用户信息表")
public class CurrentUser implements Serializable {

    private static final long serialVersionUID = -1959092157632528455L;
    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("用户电话号码")
    private String userPhone;

    @ApiModelProperty("用户邮箱")
    private String userEmail;

    @ApiModelProperty("上次登录时间")
    private Date lastLoginTime;

    @ApiModelProperty("登录来源")
    private String loginSource;

    @ApiModelProperty("登录来源中文")
    private String loginSourceName;

    @ApiModelProperty("头像地址")
    private String profileUrl;

    @ApiModelProperty("角色数组roleIds")
    private List<Integer> roleIds;

    @ApiModelProperty("角色名数组roleNames")
    private List<String> roleNames;

    @ApiModelProperty("是否超级管理员")
    private Boolean admin;


    public Map<String, Object> toMap(){
        Map<String, Object> map = new HashMap<>(8);
        map.put("userId", this.userId == null ? "" : this.userId);
        map.put("userName", this.userName == null ? "" : this.userName);
        map.put("lastLoginTime", this.lastLoginTime == null ? "" : this.lastLoginTime.getTime());
        map.put("loginSource", this.loginSource == null ? "" : this.loginSource);
        map.put("loginSourceName", this.loginSourceName == null ? "" : this.loginSourceName);
        map.put("profileUrl", this.profileUrl == null ? "" : this.profileUrl);
        map.put("roleIds", CollUtil.isEmpty(this.roleIds) ? Collections.emptyList() : this.roleIds);
        map.put("roleNames", CollUtil.isEmpty(this.roleNames) ? Collections.emptyList() : this.roleNames);
        map.put("admin", this.admin != null ? this.admin : false);
        return map;
    }

    public void fromMap(Map<String, Object> map){
        this.userId = map.getOrDefault("userId", "unKnow").toString();
        this.userName = map.getOrDefault("userName", "unKnow").toString();
        if(!map.containsKey("lastLoginTime") || StringUtils.isEmpty(map.get("lastLoginTime").toString())){
            this.lastLoginTime = new Date();
        }else{
            this.lastLoginTime = DateUtil.date(Long.parseLong(map
                    .getOrDefault("lastLoginTime", System.currentTimeMillis()).toString()));
        }
        this.loginSource = map.getOrDefault("loginSource", "UnKnow").toString();
        this.loginSourceName = map.getOrDefault("loginSourceName", "未知来源").toString();
        this.profileUrl = map.getOrDefault("profileUrl", "none").toString();
        if(map.getOrDefault("roleIds", Collections.emptyList()) instanceof List){
            this.roleIds = (List) map.getOrDefault("roleIds", Collections.emptyList());
        }else{
            this.roleIds = Collections.emptyList();
        }
        if(map.getOrDefault("roleNames", Collections.emptyList()) instanceof List){
            this.roleNames = (List) map.getOrDefault("roleNames", Collections.emptyList());
        }else{
            this.roleNames = Collections.emptyList();
        }
        if(map.getOrDefault("admin", Collections.emptyList()) instanceof Boolean){
            this.admin = (Boolean) map.getOrDefault("admin", false);
        }else{
            this.admin = false;
        }
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userId", userId)
                .append("userName", userName)
                .append("userPhone", userPhone)
                .append("userEmail", userEmail)
                .append("lastLoginTime", lastLoginTime)
                .append("loginSource", loginSource)
                .append("loginSourceName", loginSourceName)
                .append("profileUrl", profileUrl)
                .append("roleIds", roleIds)
                .append("roleNames", roleNames)
                .append("admin", admin)
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

        CurrentUser that = (CurrentUser) o;

        return new EqualsBuilder().append(userId, that.userId).append(userName, that.userName).append(userPhone, that.userPhone).append(userEmail, that.userEmail).append(lastLoginTime, that.lastLoginTime).append(loginSource, that.loginSource).append(loginSourceName, that.loginSourceName).append(profileUrl, that.profileUrl).append(roleIds, that.roleIds).append(roleNames, that.roleNames).append(admin, that.admin).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(userId).append(userName).append(userPhone).append(userEmail).append(lastLoginTime).append(loginSource).append(loginSourceName).append(profileUrl).append(roleIds).append(roleNames).append(admin).toHashCode();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getLoginSource() {
        return loginSource;
    }

    public void setLoginSource(String loginSource) {
        this.loginSource = loginSource;
    }

    public String getLoginSourceName() {
        return loginSourceName;
    }

    public void setLoginSourceName(String loginSourceName) {
        this.loginSourceName = loginSourceName;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
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
