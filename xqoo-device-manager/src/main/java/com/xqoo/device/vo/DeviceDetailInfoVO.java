package com.xqoo.device.vo;

import com.xqoo.device.entity.ScreenPropertiesEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * @author gaoyang
 */
@ApiModel("屏幕信息表明细信息实体")
public class DeviceDetailInfoVO extends DeviceInfoVO {

    @ApiModelProperty("参数列表")
    private List<ScreenPropertiesEntity> propertiesList;

    @ApiModelProperty("图片列表")
    private List<ScreenPictureDetailVO> pictureList;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DeviceDetailInfoVO that = (DeviceDetailInfoVO) o;

        return new EqualsBuilder().appendSuper(super.equals(o)).append(propertiesList, that.propertiesList).append(pictureList, that.pictureList).isEquals();
    }


    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).appendSuper(super.hashCode()).append(propertiesList).append(pictureList).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("propertiesList", propertiesList)
                .append("pictureList", pictureList)
                .toString();
    }

    public List<ScreenPropertiesEntity> getPropertiesList() {
        return propertiesList;
    }

    public void setPropertiesList(List<ScreenPropertiesEntity> propertiesList) {
        this.propertiesList = propertiesList;
    }

    public List<ScreenPictureDetailVO> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<ScreenPictureDetailVO> pictureList) {
        this.pictureList = pictureList;
    }
}
