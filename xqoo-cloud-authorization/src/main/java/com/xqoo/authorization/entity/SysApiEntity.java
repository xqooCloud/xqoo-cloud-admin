package com.xqoo.authorization.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.Objects;

/**
 * @author mumu
 * @date 2019/11/08 上午11:17
 **/
@ApiModel("用户接口资源")
@TableName(value = "sys_api")
public class SysApiEntity extends Model<SysApiEntity> {


    private static final long serialVersionUID = -6515854993517328323L;
    @ApiModelProperty("自增长id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("关联的菜单的id")
    private Integer menuId;

    @ApiModelProperty("接口权限字符")
    private String apiPermission;

    @ApiModelProperty("接口名称")
    private String apiName;

    @TableField(value = "create_by", fill = FieldFill.INSERT)
    @ApiModelProperty("创建人")
    private String createBy;

    @TableField(value = "create_date", fill = FieldFill.INSERT)
    @ApiModelProperty("创建时间")
    private Date createDate;

    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    @ApiModelProperty("最后修改人")
    private String updateBy;

    @TableField(value = "update_date", fill = FieldFill.UPDATE)
    @ApiModelProperty("最后修改时间")
    private Date updateDate;

    @ApiModelProperty("备注")
    private String remarkTips;

    @Override
    public String toString() {
        return "SysApiEntity{" +
                "id=" + id +
                ", menuId=" + menuId +
                ", apiPermission='" + apiPermission + '\'' +
                ", apiName='" + apiName + '\'' +
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
        SysApiEntity that = (SysApiEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(menuId, that.menuId) &&
                Objects.equals(apiPermission, that.apiPermission) &&
                Objects.equals(apiName, that.apiName) &&
                Objects.equals(createBy, that.createBy) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(updateBy, that.updateBy) &&
                Objects.equals(updateDate, that.updateDate) &&
                Objects.equals(remarkTips, that.remarkTips);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, menuId, apiPermission, apiName, createBy, createDate, updateBy, updateDate, remarkTips);
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

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getApiPermission() {
        return apiPermission;
    }

    public void setApiPermission(String apiPermission) {
        this.apiPermission = apiPermission;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
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
