package com.xqoo.device.config;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author gaoyang
 */
@Component
@ConfigurationProperties(prefix = "xqoo.device")
public class DeviceConfigProperties {

    private Integer minPropertiesCount;

    private Integer maxPropertiesCount;

    private Integer maxImageCount;

    private Long maxImageSize;

    private List<String> defaultLabel;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("minPropertiesCount", minPropertiesCount)
                .append("maxPropertiesCount", maxPropertiesCount)
                .append("maxImageCount", maxImageCount)
                .append("maxImageSize", maxImageSize)
                .append("defaultLabel", defaultLabel)
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

        DeviceConfigProperties that = (DeviceConfigProperties) o;

        return new EqualsBuilder().append(minPropertiesCount, that.minPropertiesCount).append(maxPropertiesCount, that.maxPropertiesCount).append(maxImageCount, that.maxImageCount).append(maxImageSize, that.maxImageSize).append(defaultLabel, that.defaultLabel).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(minPropertiesCount).append(maxPropertiesCount).append(maxImageCount).append(maxImageSize).append(defaultLabel).toHashCode();
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

    public List<String> getDefaultLabel() {
        return defaultLabel;
    }

    public void setDefaultLabel(List<String> defaultLabel) {
        this.defaultLabel = defaultLabel;
    }
}
