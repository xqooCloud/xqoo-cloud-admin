package com.xqoo.device.pojo;

import com.xqoo.common.page.PageRequestBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

/**
 * @author gaoyang
 */
@ApiModel("设备查询对象实体")
public class DeviceInfoPageQueryPOJO extends PageRequestBean {

    @ApiModelProperty("屏幕状态，0-计划部署，1-正在部署，2-已部署投入使用，3-故障停机，4-拆机移除")
    private Integer screenStatus;

    @ApiModelProperty("屏幕尺寸")
    private BigDecimal screenSize;

    @ApiModelProperty("屏幕名字")
    private String screenName;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("screenStatus", screenStatus)
                .append("screenSize", screenSize)
                .append("screenName", screenName)
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

        DeviceInfoPageQueryPOJO that = (DeviceInfoPageQueryPOJO) o;

        return new EqualsBuilder().appendSuper(super.equals(o)).append(screenStatus, that.screenStatus).append(screenSize, that.screenSize).append(screenName, that.screenName).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).appendSuper(super.hashCode()).append(screenStatus).append(screenSize).append(screenName).toHashCode();
    }

    public Integer getScreenStatus() {
        return screenStatus;
    }

    public void setScreenStatus(Integer screenStatus) {
        this.screenStatus = screenStatus;
    }

    public BigDecimal getScreenSize() {
        return screenSize;
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
}
