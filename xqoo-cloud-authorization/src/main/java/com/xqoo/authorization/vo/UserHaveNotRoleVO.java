package com.xqoo.authorization.vo;

import com.xqoo.authorization.entity.SysRoleEntity;
import com.xqoo.common.page.PageResponseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Objects;

@ApiModel("用户还未拥有的角色信息")
public class UserHaveNotRoleVO implements Serializable {

    private static final long serialVersionUID = 7213187626404037582L;
    @ApiModelProperty("是否默认超级管理员")
    private Boolean admin;

    @ApiModelProperty("分页的角色数据")
    private PageResponseBean<SysRoleEntity> result;

    @Override
    public String toString() {
        return "UserHaveNotRoleVO{" +
                "admin=" + admin +
                ", result=" + result +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserHaveNotRoleVO that = (UserHaveNotRoleVO) o;
        return Objects.equals(admin, that.admin) &&
                Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(admin, result);
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public PageResponseBean<SysRoleEntity> getResult() {
        return result;
    }

    public void setResult(PageResponseBean<SysRoleEntity> result) {
        this.result = result;
    }
}
