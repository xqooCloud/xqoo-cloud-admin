package com.xqoo.authorization.bo;

import java.io.Serializable;
import java.util.Objects;

public class SysUserRoleMapBO implements Serializable  {

    private static final long serialVersionUID = -1341079544055875215L;
    private Integer roleId;

    private String roleName;

    private boolean admin;

    @Override
    public String toString() {
        return "SySUserRoleMapBO{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", admin=" + admin +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysUserRoleMapBO that = (SysUserRoleMapBO) o;
        return admin == that.admin &&
                Objects.equals(roleId, that.roleId) &&
                Objects.equals(roleName, that.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, roleName, admin);
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

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
