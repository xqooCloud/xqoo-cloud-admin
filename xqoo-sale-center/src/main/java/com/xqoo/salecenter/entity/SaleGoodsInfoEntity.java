package com.xqoo.salecenter.entity;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * sale_goods_info实体类
 *
 * @author xqoo-code-gen
 * @date 2021-02-01 15:08:34
 */
@ApiModel("产品售卖信息表实体")
@TableName(value = "sale_goods_info")
public class SaleGoodsInfoEntity extends Model<SaleGoodsInfoEntity> {

    private static final long serialVersionUID = 683268377346644535L;

    @ApiModelProperty("销售单号")
    @TableId(value = "goods_id")
    private String goodsId;

    @ApiModelProperty("对应的设备屏幕id")
    private String screenId;

    @ApiModelProperty("销售单状态，0-草稿，1-审核中，2-已审核，3-已上架，4-废弃版本")
    private Integer status;

    @ApiModelProperty("售卖单价")
    private BigDecimal salePrice;

    @ApiModelProperty("售卖标价")
    private BigDecimal saleOrgPrice;

    @ApiModelProperty("屏幕所在经度")
    private BigDecimal screenPositionLong;

    @ApiModelProperty("屏幕所在纬度")
    private BigDecimal screenPositionLati;

    @ApiModelProperty("覆盖人群，单位百人")
    private Integer overPeople;

    @ApiModelProperty("展示名title")
    private String saleTitle;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date createDate;

    @ApiModelProperty("创建人id")
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_date", fill = FieldFill.UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date updateDate;

    @ApiModelProperty("更新人id")
    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    private String updateBy;

    @ApiModelProperty("备注说明")
    private String remarkTips;

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsId(){
        return this.goodsId;
    }

    public void setScreenId(String screenId) {
        this.screenId = screenId;
    }

    public String getScreenId(){
        return this.screenId;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus(){
        return this.status;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public BigDecimal getSalePrice(){
        return this.salePrice;
    }

    public void setSaleOrgPrice(BigDecimal saleOrgPrice) {
        this.saleOrgPrice = saleOrgPrice;
    }

    public BigDecimal getSaleOrgPrice(){
        return this.saleOrgPrice;
    }

    public BigDecimal getScreenPositionLong() {
        return screenPositionLong;
    }

    public void setScreenPositionLong(BigDecimal screenPositionLong) {
        this.screenPositionLong = screenPositionLong;
    }

    public BigDecimal getScreenPositionLati() {
        return screenPositionLati;
    }

    public void setScreenPositionLati(BigDecimal screenPositionLati) {
        this.screenPositionLati = screenPositionLati;
    }

    public void setOverPeople(Integer overPeople) {
        this.overPeople = overPeople;
    }

    public Integer getOverPeople(){
        return this.overPeople;
    }

    public void setSaleTitle(String saleTitle) {
        this.saleTitle = saleTitle;
    }

    public String getSaleTitle(){
        return this.saleTitle;
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
    public SaleGoodsInfoEntity fromMap(Map<String, Object> valueMap) {
        return BeanUtil.mapToBean(valueMap, this.getClass(), false, null);
    }

    @Override
    public String toString(){
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("goodsId", getGoodsId())
            .append("screenId", getScreenId())
            .append("status", getStatus())
            .append("salePrice", getSalePrice())
            .append("saleOrgPrice", getSaleOrgPrice())
            .append("screenPositionLati", getScreenPositionLati())
            .append("screenPositionLong", getScreenPositionLong())
            .append("overPeople", getOverPeople())
            .append("saleTitle", getSaleTitle())
            .append("createDate", getCreateDate())
            .append("createBy", getCreateBy())
            .append("updateDate", getUpdateDate())
            .append("updateBy", getUpdateBy())
            .append("remarkTips", getRemarkTips())
            .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        SaleGoodsInfoEntity that = (SaleGoodsInfoEntity) o;
        return new EqualsBuilder()
                .append(goodsId, that.goodsId)
                .append(screenId, that.screenId)
                .append(status, that.status)
                .append(salePrice, that.salePrice)
                .append(saleOrgPrice, that.saleOrgPrice)
                .append(screenPositionLati, that.screenPositionLati)
                .append(screenPositionLong, that.screenPositionLong)
                .append(overPeople, that.overPeople)
                .append(saleTitle, that.saleTitle)
                .append(createDate, that.createDate)
                .append(createBy, that.createBy)
                .append(updateDate, that.updateDate)
                .append(updateBy, that.updateBy)
                .append(remarkTips, that.remarkTips)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(goodsId)
                .append(screenId)
                .append(status)
                .append(salePrice)
                .append(saleOrgPrice)
                .append(screenPositionLati)
                .append(screenPositionLong)
                .append(overPeople)
                .append(saleTitle)
                .append(createDate)
                .append(createBy)
                .append(updateDate)
                .append(updateBy)
                .append(remarkTips)
                .toHashCode();
    }
}
