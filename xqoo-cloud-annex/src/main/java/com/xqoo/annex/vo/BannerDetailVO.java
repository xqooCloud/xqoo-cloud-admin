package com.xqoo.annex.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@ApiModel("轮播图详情")
public class BannerDetailVO implements Serializable {

    private static final long serialVersionUID = -4147627522037201546L;

    @ApiModelProperty("自增长id")
    private Integer id;

    @ApiModelProperty("所属轮播图分组id")
    @NotNull(message = "所属轮播图分组id不能为空")
    private Integer groupId;

    @ApiModelProperty("是否可用，0-否，1-是")
    private Integer activeCode;

    @ApiModelProperty("媒体来源url，http地址")
    @NotNull(message = "媒体来源url，http地址不能为空")
    private String mediaUrl;

    @ApiModelProperty("前端tips或title展示语句，不超过64字")
    private String bannerTips;

    @ApiModelProperty("点击时间重定向触发类型，NONE-不处理，URL直接打开链接，METHOD调用指定前端方法")
    private String redirectType;

    @ApiModelProperty("点击事件重定向的值，最长512字符")
    private String redirectValue;

    @ApiModelProperty("排序顺序")
    private Integer sortNo;


    @ApiModelProperty("创建人")
    private String createBy;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date createDate;

    @ApiModelProperty("最近修改人")
    private String updateBy;

    @ApiModelProperty("最近修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date updateDate;

    @ApiModelProperty("备注信息")
    private String remarkTips;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getActiveCode() {
        return activeCode;
    }

    public void setActiveCode(Integer activeCode) {
        this.activeCode = activeCode;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }

    public String getBannerTips() {
        return bannerTips;
    }

    public void setBannerTips(String bannerTips) {
        this.bannerTips = bannerTips;
    }

    public String getRedirectType() {
        return redirectType;
    }

    public void setRedirectType(String redirectType) {
        this.redirectType = redirectType;
    }

    public String getRedirectValue() {
        return redirectValue;
    }

    public void setRedirectValue(String redirectValue) {
        this.redirectValue = redirectValue;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }
}
