package com.xqoo.authorization.bo;

import com.xqoo.common.page.PageRequestBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

@ApiModel("分页查询角色信息实体")
public class QueryRoleBO extends PageRequestBean {
    private static final long serialVersionUID = 3827772006496903432L;

    @ApiModelProperty("角色标识key，支持右模糊匹配")
    private Integer roleId;

    @ApiModelProperty("角色标识key，支持右模糊匹配")
    private String roleKey;

    @ApiModelProperty("角色名字。支持模糊匹配")
    private String roleName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        QueryRoleBO that = (QueryRoleBO) o;
        return Objects.equals(roleId, that.roleId) &&
                Objects.equals(roleKey, that.roleKey) &&
                Objects.equals(roleName, that.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), roleId, roleKey, roleName);
    }

    @Override
    public String toString() {
        return "QueryRoleBO{" +
                "roleId=" + roleId +
                ", roleKey='" + roleKey + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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
}
