package com.xqoo.salecenter.vo;

import com.xqoo.feign.dto.device.ScreenPropertiesDTO;
import com.xqoo.salecenter.entity.GoodsPictureInfoEntity;
import com.xqoo.salecenter.entity.SaleGoodsInfoEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author gaoyang
 */
@ApiModel("产品售卖实体")
public class SaleGoodsInfoVO extends SaleGoodsInfoEntity {

    @ApiModelProperty("屏幕的图片列表")
    private List<GoodsPictureInfoEntity> pictureList;

    @ApiModelProperty("屏幕参数列表")
    private List<ScreenPropertiesDTO> propertiesList;

    @ApiModelProperty("屏幕尺寸")
    private BigDecimal screenSize;

    @ApiModelProperty("屏幕名字")
    private String screenName;

    @ApiModelProperty("设备默认最大资源承载量")
    private Integer screenMaxSourceCount;

    @ApiModelProperty("屏幕所在地址")
    private String screenAddress;

    @ApiModelProperty("屏幕的收藏数量")
    private Integer favoriteCount;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("pictureList", pictureList)
                .append("propertiesList", propertiesList)
                .append("screenSize", screenSize)
                .append("screenName", screenName)
                .append("screenMaxSourceCount", screenMaxSourceCount)
                .append("screenAddress", screenAddress)
                .append("favoriteCount", favoriteCount)
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
        SaleGoodsInfoVO that = (SaleGoodsInfoVO) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(pictureList, that.pictureList)
                .append(propertiesList, that.propertiesList)
                .append(screenSize, that.screenSize)
                .append(screenName, that.screenName)
                .append(screenMaxSourceCount, that.screenMaxSourceCount)
                .append(screenAddress, that.screenAddress)
                .append(favoriteCount, that.favoriteCount)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(pictureList)
                .append(propertiesList)
                .append(screenSize)
                .append(screenName)
                .append(screenMaxSourceCount)
                .append(screenAddress)
                .append(favoriteCount)
                .toHashCode();
    }

    public List<GoodsPictureInfoEntity> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<GoodsPictureInfoEntity> pictureList) {
        this.pictureList = pictureList;
    }

    public BigDecimal getScreenSize() {
        return screenSize;
    }

    public List<ScreenPropertiesDTO> getPropertiesList() {
        return propertiesList;
    }

    public void setPropertiesList(List<ScreenPropertiesDTO> propertiesList) {
        this.propertiesList = propertiesList;
    }

    public void setScreenSize(BigDecimal screenSize) {
        this.screenSize = screenSize;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public Integer getScreenMaxSourceCount() {
        return screenMaxSourceCount;
    }

    public void setScreenMaxSourceCount(Integer screenMaxSourceCount) {
        this.screenMaxSourceCount = screenMaxSourceCount;
    }

    public String getScreenAddress() {
        return screenAddress;
    }

    public void setScreenAddress(String screenAddress) {
        this.screenAddress = screenAddress;
    }

    public Integer getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(Integer favoriteCount) {
        this.favoriteCount = favoriteCount;
    }
}
