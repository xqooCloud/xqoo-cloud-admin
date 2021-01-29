package com.xqoo.device.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author gaoyang
 */
@ApiModel("屏幕信息表实体")
public class DeviceInfoVO {

    @ApiModelProperty("主键值")
    private String id;

    @ApiModelProperty("逻辑删除，0-未删除，1-已删除")
    private Integer delFlag;

    @ApiModelProperty("屏幕状态，0-计划部署，1-正在部署，2-已部署投入使用，3-故障停机，4-拆机移除")
    private Integer screenStatus;

    @ApiModelProperty("屏幕所在经度")
    @NotNull(message = "经度数值不能为空")
    @Max(value = 180, message = "经度最大数180")
    @Min(value = -180, message = "经度最小数-180")
    private BigDecimal screenPositionLong;

    @ApiModelProperty("屏幕所在纬度")
    @NotNull(message = "纬度数值不能为空")
    @Max(value = 180, message = "纬度最大数90")
    @Min(value = -180, message = "纬度最小数-90")
    private BigDecimal screenPositionLati;

    @ApiModelProperty("屏幕尺寸")
    @NotNull(message = "屏幕尺寸不能为空")
    @Max(value = 2000, message = "屏幕最大尺寸2000")
    @Min(value = 1, message = "屏幕最小尺寸1")
    private BigDecimal screenSize;

    @ApiModelProperty("屏幕名字")
    @NotBlank(message = "屏幕名字不能为空")
    @Size(min = 4, max = 255, message = "地址最多255字，最少4字")
    private String screenName;

    @ApiModelProperty("屏幕所在地址")
    @NotBlank(message = "屏幕所在地址不能为空")
    @Size(min = 2, max = 32, message = "屏幕名字最多32字，最少2字")
    private String screenAddress;

    @ApiModelProperty("屏幕标签，多个使用|隔开")
    @NotNull(message = "屏幕标签不能为空")
    private List<String> screenLabel;

    @ApiModelProperty("屏幕安装人员名称")
    @NotBlank(message = "屏幕安装人员名字不能为空")
    @Size(min = 2, max = 32, message = "名字最多32字，最少2字")
    private String screenInstaller;

    @ApiModelProperty("屏幕安装人员联系电话")
    @NotBlank(message = "屏幕安装人员电话不能为空")
    @Size(min = 6, max = 16, message = "电话位数最少6位，最多16位")
    private String screenInstallerPhone;

    @ApiModelProperty("屏幕说明，不超过512字")
    @NotBlank(message = "屏幕说明信息不能为空")
    @Size(min = 6, max = 512, message = "说明最多不超过512字,最少6字")
    private String screenTips;

    @ApiModelProperty("记录创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date createDate;

    @ApiModelProperty("记录创建人")
    private String createBy;

    @ApiModelProperty("最近更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date updateDate;

    @ApiModelProperty("最新跟进人")
    private String updateBy;

    @ApiModelProperty("备注说明")
    private String remarkTips;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("delFlag", delFlag)
                .append("screenStatus", screenStatus)
                .append("screenPositionLong", screenPositionLong)
                .append("screenPositionLati", screenPositionLati)
                .append("screenSize", screenSize)
                .append("screenName", screenName)
                .append("screenAddress", screenAddress)
                .append("screenLabel", screenLabel)
                .append("screenInstaller", screenInstaller)
                .append("screenInstallerPhone", screenInstallerPhone)
                .append("screenTips", screenTips)
                .append("createDate", createDate)
                .append("createBy", createBy)
                .append("updateDate", updateDate)
                .append("updateBy", updateBy)
                .append("remarkTips", remarkTips)
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

        DeviceInfoVO that = (DeviceInfoVO) o;

        return new EqualsBuilder().append(id, that.id).append(delFlag, that.delFlag).append(screenStatus, that.screenStatus).append(screenPositionLong, that.screenPositionLong).append(screenPositionLati, that.screenPositionLati).append(screenSize, that.screenSize).append(screenName, that.screenName).append(screenAddress, that.screenAddress).append(screenLabel, that.screenLabel).append(screenInstaller, that.screenInstaller).append(screenInstallerPhone, that.screenInstallerPhone).append(screenTips, that.screenTips).append(createDate, that.createDate).append(createBy, that.createBy).append(updateDate, that.updateDate).append(updateBy, that.updateBy).append(remarkTips, that.remarkTips).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(delFlag).append(screenStatus).append(screenPositionLong).append(screenPositionLati).append(screenSize).append(screenName).append(screenAddress).append(screenLabel).append(screenInstaller).append(screenInstallerPhone).append(screenTips).append(createDate).append(createBy).append(updateDate).append(updateBy).append(remarkTips).toHashCode();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getScreenStatus() {
        return screenStatus;
    }

    public void setScreenStatus(Integer screenStatus) {
        this.screenStatus = screenStatus;
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

    public List<String> getScreenLabel() {
        return screenLabel;
    }

    public void setScreenLabel(List<String> screenLabel) {
        this.screenLabel = screenLabel;
    }

    public String getScreenInstaller() {
        return screenInstaller;
    }

    public void setScreenInstaller(String screenInstaller) {
        this.screenInstaller = screenInstaller;
    }

    public String getScreenInstallerPhone() {
        return screenInstallerPhone;
    }

    public void setScreenInstallerPhone(String screenInstallerPhone) {
        this.screenInstallerPhone = screenInstallerPhone;
    }

    public String getScreenTips() {
        return screenTips;
    }

    public void setScreenTips(String screenTips) {
        this.screenTips = screenTips;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getRemarkTips() {
        return remarkTips;
    }

    public void setRemarkTips(String remarkTips) {
        this.remarkTips = remarkTips;
    }
}
