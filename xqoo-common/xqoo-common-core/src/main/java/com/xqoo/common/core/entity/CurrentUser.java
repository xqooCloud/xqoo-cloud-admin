package com.xqoo.common.core.entity;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import com.xqoo.common.core.utils.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
        return "CurrentUser{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", lastLoginTime=" + lastLoginTime +
                ", loginSource='" + loginSource + '\'' +
                ", loginSourceName='" + loginSourceName + '\'' +
                ", profileUrl='" + profileUrl + '\'' +
                ", roleIds=" + roleIds +
                ", roleNames=" + roleNames +
                ", admin=" + admin +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrentUser that = (CurrentUser) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(lastLoginTime, that.lastLoginTime) &&
                Objects.equals(loginSource, that.loginSource) &&
                Objects.equals(loginSourceName, that.loginSourceName) &&
                Objects.equals(profileUrl, that.profileUrl) &&
                Objects.equals(roleIds, that.roleIds) &&
                Objects.equals(roleNames, that.roleNames) &&
                Objects.equals(admin, that.admin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, lastLoginTime, loginSource, loginSourceName, profileUrl, roleIds, roleNames, admin);
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
