package com.xqoo.feign.dto.device;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author gaoyang
 */
public class DeviceInfoDetailDTO {

    private BigDecimal screenPositionLong;

    private BigDecimal screenPositionLati;

    private BigDecimal screenSize;

    private Integer screenMaxSourceCount;

    private String screenName;

    private String screenAddress;

    private List<ScreenPropertiesDTO> propertiesList;

    private List<ScreenPictureDetailDTO> pictureList;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("screenPositionLong", screenPositionLong)
                .append("screenPositionLati", screenPositionLati)
                .append("screenSize", screenSize)
                .append("screenMaxSourceCount", screenMaxSourceCount)
                .append("screenName", screenName)
                .append("screenAddress", screenAddress)
                .append("propertiesList", propertiesList)
                .append("pictureList", pictureList)
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
        DeviceInfoDetailDTO that = (DeviceInfoDetailDTO) o;
        return new EqualsBuilder()
                .append(screenPositionLong, that.screenPositionLong)
                .append(screenPositionLati, that.screenPositionLati)
                .append(screenSize, that.screenSize)
                .append(screenMaxSourceCount, that.screenMaxSourceCount)
                .append(screenName, that.screenName)
                .append(screenAddress, that.screenAddress)
                .append(propertiesList, that.propertiesList)
                .append(pictureList, that.pictureList)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(screenPositionLong)
                .append(screenPositionLati)
                .append(screenSize)
                .append(screenMaxSourceCount)
                .append(screenName)
                .append(screenAddress)
                .append(propertiesList)
                .append(pictureList)
                .toHashCode();
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

    public BigDecimal getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(BigDecimal screenSize) {
        this.screenSize = screenSize;
    }

    public Integer getScreenMaxSourceCount() {
        return screenMaxSourceCount;
    }

    public void setScreenMaxSourceCount(Integer screenMaxSourceCount) {
        this.screenMaxSourceCount = screenMaxSourceCount;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getScreenAddress() {
        return screenAddress;
    }

    public void setScreenAddress(String screenAddress) {
        this.screenAddress = screenAddress;
    }

    public List<ScreenPropertiesDTO> getPropertiesList() {
        return propertiesList;
    }

    public void setPropertiesList(List<ScreenPropertiesDTO> propertiesList) {
        this.propertiesList = propertiesList;
    }

    public List<ScreenPictureDetailDTO> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<ScreenPictureDetailDTO> pictureList) {
        this.pictureList = pictureList;
    }
}
