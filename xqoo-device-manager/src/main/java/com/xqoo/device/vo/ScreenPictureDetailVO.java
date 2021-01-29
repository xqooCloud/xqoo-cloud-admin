package com.xqoo.device.vo;

import com.xqoo.device.entity.ScreenPictureViewEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author gaoyang
 */
@ApiModel("屏幕图片信息明细")
public class ScreenPictureDetailVO extends ScreenPictureViewEntity {
    @ApiModelProperty("文件地址，主要给前端展示使用")
    private String url;

    @ApiModelProperty("文件上传状态，主要给前端展示使用")
    private String status;

    @ApiModelProperty("文件上传进度，主要给前端展示使用")
    private Integer percent;

    @ApiModelProperty("文件uid")
    private String uid;

    @ApiModelProperty("文件key")
    private String key;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("url", url)
                .append("status", status)
                .append("percent", percent)
                .append("uid", uid)
                .append("key", key)
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

        ScreenPictureDetailVO that = (ScreenPictureDetailVO) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(url, that.url)
                .append(status, that.status)
                .append(percent, that.percent)
                .append(uid, that.uid)
                .append(key, that.key)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).appendSuper(super.hashCode())
                .append(url)
                .append(status)
                .append(percent)
                .append(uid)
                .append(key)
                .toHashCode();
    }
}
