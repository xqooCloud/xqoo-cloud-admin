package com.xqoo.authorization.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@ApiModel("用户角色明细vo")
public class SysUserRoleDetailVO implements Serializable {

    private static final long serialVersionUID = 8779370326176039380L;
    @ApiModelProperty
    private UserOnlinedVO onlined;

    @ApiModelProperty("是否默认管理员账号")
    private Boolean admin;

    @ApiModelProperty("所拥有的角色列表")
    private List<SysUserRoleInfoVO> userRoleList;

    public UserOnlinedVO getOnlined() {
        return onlined;
    }

    public void setOnlined(UserOnlinedVO onlined) {
        this.onlined = onlined;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public List<SysUserRoleInfoVO> getUserRoleList() {
        return userRoleList;
    }

    public void setUserRoleList(List<SysUserRoleInfoVO> userRoleList) {
        this.userRoleList = userRoleList;
    }

    @Override
    public String toString() {
        return "SysUserRoleDetailVO{" +
                "onlined=" + onlined +
                ", admin=" + admin +
                ", userRoleList=" + userRoleList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysUserRoleDetailVO that = (SysUserRoleDetailVO) o;
        return Objects.equals(onlined, that.onlined) &&
                Objects.equals(admin, that.admin) &&
                Objects.equals(userRoleList, that.userRoleList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(onlined, admin, userRoleList);
    }

}
