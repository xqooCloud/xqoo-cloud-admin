package com.xqoo.authorization.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@ApiModel("角色明细vo")
public class SysRoleDetailVO implements Serializable {

    private static final long serialVersionUID = -1780222392952361111L;
    @ApiModelProperty("自增长id")
    private Integer id;

    @ApiModelProperty("是否超级管理员，此角色有且仅有一个，初始化数据")
    private Boolean admin;

    @ApiModelProperty("逻辑删除标志，0-未删除，1-已删除")
    private Boolean delFlag;

    @NotBlank(message = "角色标识key不能为空")
    @NotNull(message = "角色标识key不能为空")
    @ApiModelProperty("角色的key值，自定义，英文字符，不超过32字")
    private String roleKey;

    @NotBlank(message = "角色名称不能为空")
    @NotNull(message = "角色标识key不能为空")
    @ApiModelProperty("角色名称，中文名称，不超过16字")
    private String roleName;

    @ApiModelProperty("创建人")
    private String createBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    @ApiModelProperty("创建时间")
    private Date createDate;

    @ApiModelProperty("最后修改人")
    private String updateBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    @ApiModelProperty("最后修改时间")
    private Date updateDate;

    @ApiModelProperty("备注")
    private String remarkTips;

    @ApiModelProperty("关联的api列表")
    private List<Integer> roleApiList;

    @ApiModelProperty("关联的菜单列表")
    private List<Integer> roleMenuList;

    @Override
    public String toString() {
        return "SysRoleDetailVO{" +
                "id=" + id +
                ", admin=" + admin +
                ", delFlag=" + delFlag +
                ", roleKey='" + roleKey + '\'' +
                ", roleName='" + roleName + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createDate=" + createDate +
                ", updateBy='" + updateBy + '\'' +
                ", updateDate=" + updateDate +
                ", remarkTips='" + remarkTips + '\'' +
                ", roleApiList=" + roleApiList +
                ", roleMenuList=" + roleMenuList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysRoleDetailVO that = (SysRoleDetailVO) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(admin, that.admin) &&
                Objects.equals(delFlag, that.delFlag) &&
                Objects.equals(roleKey, that.roleKey) &&
                Objects.equals(roleName, that.roleName) &&
                Objects.equals(createBy, that.createBy) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(updateBy, that.updateBy) &&
                Objects.equals(updateDate, that.updateDate) &&
                Objects.equals(remarkTips, that.remarkTips) &&
                Objects.equals(roleApiList, that.roleApiList) &&
                Objects.equals(roleMenuList, that.roleMenuList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, admin, delFlag, roleKey, roleName, createBy, createDate, updateBy, updateDate, remarkTips, roleApiList, roleMenuList);
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

    public List<Integer> getRoleApiList() {
        return roleApiList;
    }

    public void setRoleApiList(List<Integer> roleApiList) {
        this.roleApiList = roleApiList;
    }

    public List<Integer> getRoleMenuList() {
        return roleMenuList;
    }

    public void setRoleMenuList(List<Integer> roleMenuList) {
        this.roleMenuList = roleMenuList;
    }
}
