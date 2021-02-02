package com.xqoo.salecenter.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.bean.BeanUtil;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Map;

import java.lang.Long;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.lang.Integer;

/**
 * comment_conten_info实体类
 *
 * @author xqoo-code-gen
 * @date 2021-02-01 14:45:10
 */
@ApiModel("评论信息表实体")
@TableName(value = "comment_conten_info")
public class CommentContentInfoEntity extends Model<CommentContentInfoEntity> {

    private static final long serialVersionUID = 306379029426793962L;

    @ApiModelProperty("")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("父级id")
    private String parentCommentId;

    @ApiModelProperty("叙述内容，不超过65000字符")
    private String contentValue;

    @ApiModelProperty("附件，只能为媒体文件链接，json数组，最大数量需要限制")
    private String contentAccessories;

    @ApiModelProperty("回复人id")
    private String replyUserId;

    @ApiModelProperty("1-店家回复，2-平台回复")
    private Integer replyType;

    @ApiModelProperty("内容")
    private String replyContent;

    @ApiModelProperty("创建人")
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date createDate;

    @ApiModelProperty("最后修改人")
    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    private String updateBy;

    @ApiModelProperty("最后修改时间")
    @TableField(value = "update_date", fill = FieldFill.UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date updateDate;

    @ApiModelProperty("备注")
    private String remarkTips;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }

    public void setParentCommentId(String parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public String getParentCommentId(){
        return this.parentCommentId;
    }

    public void setContentValue(String contentValue) {
        this.contentValue = contentValue;
    }

    public String getContentValue(){
        return this.contentValue;
    }

    public void setContentAccessories(String contentAccessories) {
        this.contentAccessories = contentAccessories;
    }

    public String getContentAccessories(){
        return this.contentAccessories;
    }

    public void setReplyUserId(String replyUserId) {
        this.replyUserId = replyUserId;
    }

    public String getReplyUserId(){
        return this.replyUserId;
    }

    public void setReplyType(Integer replyType) {
        this.replyType = replyType;
    }

    public Integer getReplyType(){
        return this.replyType;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getReplyContent(){
        return this.replyContent;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateBy(){
        return this.createBy;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCreateDate(){
        return this.createDate;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateBy(){
        return this.updateBy;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getUpdateDate(){
        return this.updateDate;
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
    public CommentContentInfoEntity fromMap(Map<String, Object> valueMap) {
        return BeanUtil.mapToBean(valueMap, this.getClass(), false, null);
    }

    @Override
    public String toString(){
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("parentCommentId", getParentCommentId())
            .append("contentValue", getContentValue())
            .append("contentAccessories", getContentAccessories())
            .append("replyUserId", getReplyUserId())
            .append("replyType", getReplyType())
            .append("replyContent", getReplyContent())
            .append("createBy", getCreateBy())
            .append("createDate", getCreateDate())
            .append("updateBy", getUpdateBy())
            .append("updateDate", getUpdateDate())
            .append("remarkTips", getRemarkTips())
            .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        CommentContentInfoEntity that = (CommentContentInfoEntity) o;
        return new EqualsBuilder()
                .append(id, that.id)
                .append(parentCommentId, that.parentCommentId)
                .append(contentValue, that.contentValue)
                .append(contentAccessories, that.contentAccessories)
                .append(replyUserId, that.replyUserId)
                .append(replyType, that.replyType)
                .append(replyContent, that.replyContent)
                .append(createBy, that.createBy)
                .append(createDate, that.createDate)
                .append(updateBy, that.updateBy)
                .append(updateDate, that.updateDate)
                .append(remarkTips, that.remarkTips)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(parentCommentId)
                .append(contentValue)
                .append(contentAccessories)
                .append(replyUserId)
                .append(replyType)
                .append(replyContent)
                .append(createBy)
                .append(createDate)
                .append(updateBy)
                .append(updateDate)
                .append(remarkTips)
                .toHashCode();
    }
}
