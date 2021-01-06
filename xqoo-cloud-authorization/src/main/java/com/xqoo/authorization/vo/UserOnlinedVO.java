package com.xqoo.authorization.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Objects;

@ApiModel("用户在线token状态vo")
public class UserOnlinedVO{

    @ApiModelProperty("token是否在线")
    private Boolean isOnlined;

    @ApiModelProperty("token在线的平台")
    private List<String> onlinedType;

    @Override
    public String toString() {
        return "UserOnliedVO{" +
                "isOnlined=" + isOnlined +
                ", onlinedType=" + onlinedType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserOnlinedVO that = (UserOnlinedVO) o;
        return Objects.equals(isOnlined, that.isOnlined) &&
                Objects.equals(onlinedType, that.onlinedType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isOnlined, onlinedType);
    }

    public Boolean getOnlined() {
        return isOnlined;
    }

    public void setOnlined(Boolean onlined) {
        isOnlined = onlined;
    }

    public List<String> getOnlinedType() {
        return onlinedType;
    }

    public void setOnlinedType(List<String> onlinedType) {
        this.onlinedType = onlinedType;
    }
}
