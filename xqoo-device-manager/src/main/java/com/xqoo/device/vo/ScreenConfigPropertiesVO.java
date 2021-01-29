package com.xqoo.device.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;
import java.util.Map;

/**
 * @author gaoyang
 */
@ApiModel("屏幕信息配置参数")
public class ScreenConfigPropertiesVO {

    @ApiModelProperty("最少输入参数限制")
    private Integer minPropertiesCount;

    @ApiModelProperty("最大输入参数限制")
    private Integer maxPropertiesCount;

    @ApiModelProperty("最大图片上传限制")
    private Integer maxImageCount;

    @ApiModelProperty("最大单张图片大小限制，单位kb")
    private Long maxImageSize;

    @ApiModelProperty("默认标签参数")
    private List<Map<String, String>> labelList;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("minPropertiesCount", minPropertiesCount)
                .append("maxPropertiesCount", maxPropertiesCount)
                .append("maxImageCount", maxImageCount)
                .append("maxImageSize", maxImageSize)
                .append("labelList", labelList)
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

        ScreenConfigPropertiesVO that = (ScreenConfigPropertiesVO) o;

        return new EqualsBuilder().append(minPropertiesCount, that.minPropertiesCount).append(maxPropertiesCount, that.maxPropertiesCount).append(maxImageCount, that.maxImageCount).append(maxImageSize, that.maxImageSize).append(labelList, that.labelList).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(minPropertiesCount).append(maxPropertiesCount).append(maxImageCount).append(maxImageSize).append(labelList).toHashCode();
    }

    public Integer getMinPropertiesCount() {
        return minPropertiesCount;
    }

    public void setMinPropertiesCount(Integer minPropertiesCount) {
        this.minPropertiesCount = minPropertiesCount;
    }

    public Integer getMaxPropertiesCount() {
        return maxPropertiesCount;
    }

    public void setMaxPropertiesCount(Integer maxPropertiesCount) {
        this.maxPropertiesCount = maxPropertiesCount;
    }

    public Integer getMaxImageCount() {
        return maxImageCount;
    }

    public void setMaxImageCount(Integer maxImageCount) {
        this.maxImageCount = maxImageCount;
    }

    public Long getMaxImageSize() {
        return maxImageSize;
    }

    public void setMaxImageSize(Long maxImageSize) {
        this.maxImageSize = maxImageSize;
    }

    public List<Map<String, String>> getLabelList() {
        return labelList;
    }

    public void setLabelList(List<Map<String, String>> labelList) {
        this.labelList = labelList;
    }
}
