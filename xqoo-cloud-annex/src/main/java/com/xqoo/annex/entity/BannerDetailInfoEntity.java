package com.xqoo.annex.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.bean.BeanUtil;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Map;
import java.util.Objects;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.lang.Integer;

/**
 * banner_detail_info实体类
 *
 * @author xqoo-code-gen
 * @date 2021-01-14 15:31:34
 */
@ApiModel("轮播图明细表实体")
@TableName(value = "banner_detail_info")
public class BannerDetailInfoEntity extends Model<BannerDetailInfoEntity> {

    private static final long serialVersionUID = -955783676382366513L;

    @ApiModelProperty("自增长id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("所属轮播图分组id")
    private Integer groupId;

    @ApiModelProperty("是否可用，0-否，1-是")
    private Boolean activeCode;

    @ApiModelProperty("媒体来源url，http地址")
    private String mediaUrl;

    @ApiModelProperty("前端tips或title展示语句，不超过64字")
    private String bannerTips;

    @ApiModelProperty("点击时间重定向触发类型，NONE-不处理，URL直接打开链接，METHOD调用指定前端方法")
    private String redirectType;

    @ApiModelProperty("点击事件重定向的值，最长512字符")
    private String redirectValue;

    @ApiModelProperty("排序顺序")
    private Integer sortNo;

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

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getGroupId(){
        return this.groupId;
    }

    public void setActiveCode(Boolean activeCode) {
        this.activeCode = activeCode;
    }

    public Boolean getActiveCode(){
        return this.activeCode;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getMediaUrl(){
        return this.mediaUrl;
    }

    public void setBannerTips(String bannerTips) {
        this.bannerTips = bannerTips;
    }

    public String getBannerTips(){
        return this.bannerTips;
    }

    public void setRedirectType(String redirectType) {
        this.redirectType = redirectType;
    }

    public String getRedirectType(){
        return this.redirectType;
    }

    public void setRedirectValue(String redirectValue) {
        this.redirectValue = redirectValue;
    }

    public String getRedirectValue(){
        return this.redirectValue;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Integer getSortNo(){
        return this.sortNo;
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
    public BannerDetailInfoEntity fromMap(Map<String, Object> valueMap) {
        return BeanUtil.mapToBean(valueMap, this.getClass(), false, null);
    }

    @Override
    public String toString(){
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("groupId", getGroupId())
            .append("activeCode", getActiveCode())
            .append("mediaUrl", getMediaUrl())
            .append("bannerTips", getBannerTips())
            .append("redirectType", getRedirectType())
            .append("redirectValue", getRedirectValue())
            .append("sortNo", getSortNo())
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
        BannerDetailInfoEntity that = (BannerDetailInfoEntity) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(groupId, that.groupId) &&
            Objects.equals(activeCode, that.activeCode) &&
            Objects.equals(mediaUrl, that.mediaUrl) &&
            Objects.equals(bannerTips, that.bannerTips) &&
            Objects.equals(redirectType, that.redirectType) &&
            Objects.equals(redirectValue, that.redirectValue) &&
            Objects.equals(sortNo, that.sortNo) &&
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
                    groupId,
                    activeCode,
                    mediaUrl,
                    bannerTips,
                    redirectType,
                    redirectValue,
                    sortNo,
                    createDate,
                    createBy,
                    updateDate,
                    updateBy,
                    remarkTips
                );
    }
}
