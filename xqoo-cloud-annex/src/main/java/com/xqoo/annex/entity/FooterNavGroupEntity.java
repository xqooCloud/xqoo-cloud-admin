package com.xqoo.annex.entity;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * footer_nav_group实体类
 *
 * @author xqoo-code-gen
 * @date 2021-01-14 14:45:58
 */
@ApiModel("网页页脚展示信息分组实体")
@TableName(value = "footer_nav_group")
public class FooterNavGroupEntity extends Model<FooterNavGroupEntity> {

    private static final long serialVersionUID = 158375105305476295L;

    @ApiModelProperty("自增id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("是否可点击跳转，0-否，1-是")
    private boolean needRedirect;

    @ApiModelProperty("点击跳转的url")
    private String redirectUrl;

    @ApiModelProperty("图标名字，目前不支持url图标，仅支持系统内的字体图标")
    private String iconName;

    @ApiModelProperty("分组名称，不超过32字")
    private String groupName;

    @ApiModelProperty("辅助说明文字")
    private String groupTips;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date createDate;

    @ApiModelProperty("创建人user_id,系统则为system")
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_date", fill = FieldFill.UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date updateDate;

    @ApiModelProperty("更新人")
    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    private String updateBy;

    @ApiModelProperty("备注说明")
    private String remarkTips;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId(){
        return this.id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public boolean isNeedRedirect() {
        return needRedirect;
    }

    public void setNeedRedirect(boolean needRedirect) {
        this.needRedirect = needRedirect;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getRedirectUrl(){
        return this.redirectUrl;
    }

    public void setIconName(String iconName) {
        this.iconName = iconName;
    }

    public String getIconName(){
        return this.iconName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName(){
        return this.groupName;
    }

    public void setGroupTips(String groupTips) {
        this.groupTips = groupTips;
    }

    public String getGroupTips(){
        return this.groupTips;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCreateDate(){
        return this.createDate;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateBy(){
        return this.createBy;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getUpdateDate(){
        return this.updateDate;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateBy(){
        return this.updateBy;
    }

    public void setRemarkTips(String remarkTips) {
        this.remarkTips = remarkTips;
    }

    public String getRemarkTips(){
        return this.remarkTips;
    }

    public Map<String, Object> toMap(){
        return BeanUtil.beanToMap(this);
    }

    /**
     * 将map直接赋值到bean中并返回一个新的bean
     * @param valueMap 需要转换的map
     */
    public FooterNavGroupEntity fromMap(Map<String, Object> valueMap) {
        return BeanUtil.mapToBean(valueMap, this.getClass(), false, null);
    }

    @Override
    public String toString(){
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("needRedirect", isNeedRedirect())
                .append("redirectUrl", getRedirectUrl())
                .append("iconName", getIconName())
                .append("groupName", getGroupName())
                .append("groupTips", getGroupTips())
                .append("createDate", getCreateDate())
                .append("createBy", getCreateBy())
                .append("updateDate", getUpdateDate())
                .append("updateBy", getUpdateBy())
                .append("remarkTips", getRemarkTips())
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FooterNavGroupEntity that = (FooterNavGroupEntity) o;
        return
                Objects.equals(id, that.id) &&
                        Objects.equals(needRedirect, that.needRedirect) &&
                        Objects.equals(redirectUrl, that.redirectUrl) &&
                        Objects.equals(iconName, that.iconName) &&
                        Objects.equals(groupName, that.groupName) &&
                        Objects.equals(groupTips, that.groupTips) &&
                        Objects.equals(createDate, that.createDate) &&
                        Objects.equals(createBy, that.createBy) &&
                        Objects.equals(updateDate, that.updateDate) &&
                        Objects.equals(updateBy, that.updateBy) &&
                        Objects.equals(remarkTips, that.remarkTips);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                needRedirect,
                redirectUrl,
                iconName,
                groupName,
                groupTips,
                createDate,
                createBy,
                updateDate,
                updateBy,
                remarkTips
        );
    }
}
