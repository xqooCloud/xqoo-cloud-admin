package com.xqoo.authorization.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.Objects;

/**
 * @author mumu
 * @date 2019/11/08 上午11:17
 **/
@ApiModel("角色表")
@TableName(value = "sys_role")
public class SysRoleEntity extends Model<SysRoleEntity> {


    private static final long serialVersionUID = -639692076974864446L;
    @ApiModelProperty("自增长id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("是否超级管理员，此角色有且仅有一个，初始化数据")
    private Boolean admin;

    @ApiModelProperty("逻辑删除标志，0-未删除，1-已删除")
    private Integer delFlag;

    @ApiModelProperty("角色的key值，自定义，英文字符，不超过32字")
    private String roleKey;

    @ApiModelProperty("角色名称，中文名称，不超过16字")
    private String roleName;

    @TableField(value = "create_by", fill = FieldFill.INSERT)
    @ApiModelProperty("创建人")
    private String createBy;

    @TableField(value = "create_date", fill = FieldFill.INSERT)
    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date createDate;

    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    @ApiModelProperty("最后修改人")
    private String updateBy;

    @TableField(value = "update_date", fill = FieldFill.UPDATE)
    @ApiModelProperty("最后修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date updateDate;

    @ApiModelProperty("备注")
    private String remarkTips;

    @Override
    public String toString() {
        return "SysRoleEntity{" +
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
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysRoleEntity that = (SysRoleEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(admin, that.admin) &&
                Objects.equals(delFlag, that.delFlag) &&
                Objects.equals(roleKey, that.roleKey) &&
                Objects.equals(roleName, that.roleName) &&
                Objects.equals(createBy, that.createBy) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(updateBy, that.updateBy) &&
                Objects.equals(updateDate, that.updateDate) &&
                Objects.equals(remarkTips, that.remarkTips);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, admin, delFlag, roleKey, roleName, createBy, createDate, updateBy, updateDate, remarkTips);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
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
}
