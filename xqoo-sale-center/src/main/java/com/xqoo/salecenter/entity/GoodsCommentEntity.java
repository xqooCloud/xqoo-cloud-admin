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
import java.util.Objects;

import java.math.BigDecimal;
import java.lang.Long;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * goods_comment实体类
 *
 * @author xqoo-code-gen
 * @date 2021-02-01 14:46:30
 */
@ApiModel("商品评价表实体")
@TableName(value = "goods_comment")
public class GoodsCommentEntity extends Model<GoodsCommentEntity> {

    private static final long serialVersionUID = 911600510062384617L;

    @ApiModelProperty("")
    @TableId(value = "comment_id")
    private String commentId;

    @ApiModelProperty("品论人userId")
    private String commentUserId;

    @ApiModelProperty("评论商品id")
    private String commentGoodsId;

    @ApiModelProperty("评论的屏幕id")
    private Long commentScreenId;

    @ApiModelProperty("评论的子订单id")
    private String commentOrderDetailId;

    @ApiModelProperty("评论人名字")
    private String commentUserName;

    @ApiModelProperty("评论总分，1位小数保存")
    private BigDecimal commentRate;

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

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getCommentId(){
        return this.commentId;
    }

    public void setCommentUserId(String commentUserId) {
        this.commentUserId = commentUserId;
    }

    public String getCommentUserId(){
        return this.commentUserId;
    }

    public void setCommentGoodsId(String commentGoodsId) {
        this.commentGoodsId = commentGoodsId;
    }

    public String getCommentGoodsId(){
        return this.commentGoodsId;
    }

    public void setCommentScreenId(Long commentScreenId) {
        this.commentScreenId = commentScreenId;
    }

    public Long getCommentScreenId(){
        return this.commentScreenId;
    }

    public void setCommentOrderDetailId(String commentOrderDetailId) {
        this.commentOrderDetailId = commentOrderDetailId;
    }

    public String getCommentOrderDetailId(){
        return this.commentOrderDetailId;
    }

    public void setCommentUserName(String commentUserName) {
        this.commentUserName = commentUserName;
    }

    public String getCommentUserName(){
        return this.commentUserName;
    }

    public void setCommentRate(BigDecimal commentRate) {
        this.commentRate = commentRate;
    }

    public BigDecimal getCommentRate(){
        return this.commentRate;
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
    public GoodsCommentEntity fromMap(Map<String, Object> valueMap) {
        return BeanUtil.mapToBean(valueMap, this.getClass(), false, null);
    }

    @Override
    public String toString(){
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("commentId", getCommentId())
            .append("commentUserId", getCommentUserId())
            .append("commentGoodsId", getCommentGoodsId())
            .append("commentScreenId", getCommentScreenId())
            .append("commentOrderDetailId", getCommentOrderDetailId())
            .append("commentUserName", getCommentUserName())
            .append("commentRate", getCommentRate())
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
        GoodsCommentEntity that = (GoodsCommentEntity) o;
        return new EqualsBuilder()
                .append(commentId, that.commentId)
                .append(commentUserId, that.commentUserId)
                .append(commentGoodsId, that.commentGoodsId)
                .append(commentScreenId, that.commentScreenId)
                .append(commentOrderDetailId, that.commentOrderDetailId)
                .append(commentUserName, that.commentUserName)
                .append(commentRate, that.commentRate)
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
                .append(commentId)
                .append(commentUserId)
                .append(commentGoodsId)
                .append(commentScreenId)
                .append(commentOrderDetailId)
                .append(commentUserName)
                .append(commentRate)
                .append(createBy)
                .append(createDate)
                .append(updateBy)
                .append(updateDate)
                .append(remarkTips)
                .toHashCode();
    }
}
