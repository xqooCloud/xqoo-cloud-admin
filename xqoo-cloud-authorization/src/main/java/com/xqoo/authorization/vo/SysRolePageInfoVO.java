package com.xqoo.authorization.vo;

import com.xqoo.common.page.PageResponseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@ApiModel("账号获取角色信息vo")
public class SysRolePageInfoVO implements Serializable {

    private static final long serialVersionUID = -4468105114718851406L;
    @ApiModelProperty("分页角色信息")
    private PageResponseBean<SysRoleInfoVO> pageRoleInfo;

    @ApiModelProperty("当前账号拥有的角色id数组")
    private List<Integer> roleIds;

    @ApiModelProperty("当前账号用的角色名称数组")
    private List<String> roleNames;

    @ApiModelProperty("当前账号是否有权编辑角色")
    private Boolean isAuthRole;

    @Override
    public String toString() {
        return "SysRoleInfoVO{" +
                "pageRoleInfo=" + pageRoleInfo +
                ", roleIds=" + roleIds +
                ", roleNames=" + roleNames +
                ", isAuthRole=" + isAuthRole +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysRolePageInfoVO that = (SysRolePageInfoVO) o;
        return Objects.equals(pageRoleInfo, that.pageRoleInfo) &&
                Objects.equals(roleIds, that.roleIds) &&
                Objects.equals(roleNames, that.roleNames) &&
                Objects.equals(isAuthRole, that.isAuthRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageRoleInfo, roleIds, roleNames, isAuthRole);
    }

    public PageResponseBean<SysRoleInfoVO> getPageRoleInfo() {
        return pageRoleInfo;
    }

    public void setPageRoleInfo(PageResponseBean<SysRoleInfoVO> pageRoleInfo) {
        this.pageRoleInfo = pageRoleInfo;
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

    public Boolean getAuthRole() {
        return isAuthRole;
    }

    public void setAuthRole(Boolean authRole) {
        isAuthRole = authRole;
    }
}
