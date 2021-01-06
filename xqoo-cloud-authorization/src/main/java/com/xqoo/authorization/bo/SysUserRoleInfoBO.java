package com.xqoo.authorization.bo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class SysUserRoleInfoBO implements Serializable {
    private static final long serialVersionUID = -4892588156281249548L;

    private boolean admin;

    private List<Integer> roleIds;

    private List<String> roleNames;

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
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

    @Override
    public String toString() {
        return "SysUserRoleInfoBO{" +
                "admin=" + admin +
                ", roleIds=" + roleIds +
                ", roleNames=" + roleNames +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysUserRoleInfoBO that = (SysUserRoleInfoBO) o;
        return admin == that.admin &&
                Objects.equals(roleIds, that.roleIds) &&
                Objects.equals(roleNames, that.roleNames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(admin, roleIds, roleNames);
    }
}
