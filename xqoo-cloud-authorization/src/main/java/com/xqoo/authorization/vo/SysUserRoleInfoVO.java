package com.xqoo.authorization.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@ApiModel("用户角色列表")
public class SysUserRoleInfoVO implements Serializable {

    private static final long serialVersionUID = -5607330809832904080L;
    @ApiModelProperty("自增长id")
    private Integer id;

    @ApiModelProperty("userId")
    private String userId;

    @ApiModelProperty("关联的roleId")
    private Integer roleId;

    @ApiModelProperty("角色名")
    private String roleName;

    @ApiModelProperty("角色标识")
    private String roleKey;

    @ApiModelProperty("角色标识")
    private Boolean admin;

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
        return "SysUserRoleInfoVO{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleKey='" + roleKey + '\'' +
                ", admin=" + admin +
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
        SysUserRoleInfoVO that = (SysUserRoleInfoVO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(roleId, that.roleId) &&
                Objects.equals(roleName, that.roleName) &&
                Objects.equals(roleKey, that.roleKey) &&
                Objects.equals(admin, that.admin) &&
                Objects.equals(createBy, that.createBy) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(updateBy, that.updateBy) &&
                Objects.equals(updateDate, that.updateDate) &&
                Objects.equals(remarkTips, that.remarkTips);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, roleId, roleName, roleKey, admin, createBy, createDate, updateBy, updateDate, remarkTips);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleKey() {
        return roleKey;
    }

    public void setRoleKey(String roleKey) {
        this.roleKey = roleKey;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
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
