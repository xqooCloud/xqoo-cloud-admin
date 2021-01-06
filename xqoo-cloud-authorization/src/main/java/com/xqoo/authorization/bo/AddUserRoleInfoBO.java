package com.xqoo.authorization.bo;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@ApiModel("增加用户角色信息")
public class AddUserRoleInfoBO implements Serializable {
    private static final long serialVersionUID = 1508933783419344111L;

    @NotBlank(message = "用户id 不能为空")
    private String userId;

    @NotNull(message = "角色id不能为空")
    @Size(min = 1, message = "角色id不能为空")
    private List<Integer> roleId;

    @Override
    public String toString() {
        return "AddUserRoleInfoBO{" +
                "userId='" + userId + '\'' +
                ", roleId=" + roleId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddUserRoleInfoBO that = (AddUserRoleInfoBO) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(roleId, that.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, roleId);
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Integer> getRoleId() {
        return roleId;
    }

    public void setRoleId(List<Integer> roleId) {
        this.roleId = roleId;
    }
}
