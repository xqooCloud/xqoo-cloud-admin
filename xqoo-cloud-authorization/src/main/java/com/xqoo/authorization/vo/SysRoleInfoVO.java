package com.xqoo.authorization.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class SysRoleInfoVO implements Serializable {


    private static final long serialVersionUID = 5187071741536407814L;
    @ApiModelProperty("自增长id")
    private Integer id;

    @ApiModelProperty("是否超级管理员，此角色有且仅有一个，初始化数据")
    private Boolean admin;

    @ApiModelProperty("逻辑删除标志，0-未删除，1-已删除")
    private Boolean delFlag;

    @ApiModelProperty("角色的key值，自定义，英文字符，不超过32字")
    private String roleKey;

    @ApiModelProperty("角色名称，中文名称，不超过16字")
    private String roleName;

    @ApiModelProperty("角色名称，中文名称，不超过16字")
    private Integer bindUserCount;

    @ApiModelProperty("是否拥有此角色")
    private Boolean hasRole;

    @ApiModelProperty("创建人")
    private String createBy;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date createDate;

    @ApiModelProperty("最后修改人")
    private String updateBy;

    @ApiModelProperty("最后修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date updateDate;

    @ApiModelProperty("备注")
    private String remarkTips;

    @Override
    public String toString() {
        return "SysRoleInfoVO{" +
                "id=" + id +
                ", admin=" + admin +
                ", delFlag=" + delFlag +
                ", roleKey='" + roleKey + '\'' +
                ", roleName='" + roleName + '\'' +
                ", bindUseCount=" + bindUserCount +
                ", hasRole=" + hasRole +
                ", createBy='" + createBy + '\'' +
                ", createDate=" + createDate +
                ", updateBy='" + updateBy + '\'' +
                ", updateDate=" + updateDate +
                ", remarkTips='" + remarkTips + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysRoleInfoVO that = (SysRoleInfoVO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(admin, that.admin) &&
                Objects.equals(delFlag, that.delFlag) &&
                Objects.equals(roleKey, that.roleKey) &&
                Objects.equals(roleName, that.roleName) &&
                Objects.equals(bindUserCount, that.bindUserCount) &&
                Objects.equals(hasRole, that.hasRole) &&
                Objects.equals(createBy, that.createBy) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(updateBy, that.updateBy) &&
                Objects.equals(updateDate, that.updateDate) &&
                Objects.equals(remarkTips, that.remarkTips);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, admin, delFlag, roleKey, roleName, bindUserCount, hasRole, createBy, createDate, updateBy, updateDate, remarkTips);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getBindUseCount() {
        return bindUserCount;
    }

    public void setBindUseCount(Integer bindUseCount) {
        this.bindUserCount = bindUseCount;
    }

    public Boolean getHasRole() {
        return hasRole;
    }

    public void setHasRole(Boolean hasRole) {
        this.hasRole = hasRole;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarkTips() {
        return remarkTips;
    }

    public void setRemarkTips(String remarkTips) {
        this.remarkTips = remarkTips;
    }
}
