package com.xqoo.salecenter.pojo;

import com.xqoo.common.page.PageRequestBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author gaoyang
 */
@ApiModel("商品查询实体")
public class SaleGoodsInfoQuery extends PageRequestBean {

    @ApiModelProperty("商品id")
    private String goodsId;

    @ApiModelProperty("屏幕id")
    private String screenId;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("售卖标题")
    private String saleTitle;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("goodsId", goodsId)
                .append("screenId", screenId)
                .append("status", status)
                .append("saleTitle", saleTitle)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SaleGoodsInfoQuery that = (SaleGoodsInfoQuery) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(goodsId, that.goodsId)
                .append(screenId, that.screenId)
                .append(status, that.status)
                .append(saleTitle, that.saleTitle)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(goodsId)
                .append(screenId)
                .append(status)
                .append(saleTitle)
                .toHashCode();
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getScreenId() {
        return screenId;
    }

    public void setScreenId(String screenId) {
        this.screenId = screenId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSaleTitle() {
        return saleTitle;
    }

    public void setSaleTitle(String saleTitle) {
        this.saleTitle = saleTitle;
    }
}
