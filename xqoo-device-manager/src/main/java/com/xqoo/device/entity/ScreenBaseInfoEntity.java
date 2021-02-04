package com.xqoo.device.entity;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.annotation.*;
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
 * screen_base_info实体类
 *
 * @author xqoo-code-gen
 * @date 2021-01-27 10:50:14
 */
@ApiModel("屏幕信息表实体")
@TableName(value = "screen_base_info")
public class ScreenBaseInfoEntity extends Model<ScreenBaseInfoEntity> {

    private static final long serialVersionUID = -181450678835237478L;

    @ApiModelProperty("自增长id")
    @TableId(value = "id")
    private String id;

    @ApiModelProperty("逻辑删除，0-未删除，1-已删除")
    private Integer delFlag;

    @ApiModelProperty("屏幕状态，0-计划部署，1-正在部署，2-已部署投入使用，3-故障停机，4-拆机移除")
    private Integer screenStatus;

    @ApiModelProperty("屏幕所在经度")
    private BigDecimal screenPositionLong;

    @ApiModelProperty("屏幕所在纬度")
    private BigDecimal screenPositionLati;

    @ApiModelProperty("屏幕尺寸")
    private BigDecimal screenSize;

    @ApiModelProperty("屏幕名字")
    private String screenName;

    @ApiModelProperty("设备默认最大资源承载量")
    private Integer screenMaxSourceCount;

    @ApiModelProperty("屏幕所在地址")
    private String screenAddress;

    @ApiModelProperty("屏幕标签，多个使用|隔开")
    private String screenLabel;

    @ApiModelProperty("屏幕安装人员名称")
    private String screenInstaller;

    @ApiModelProperty("屏幕安装人员联系电话")
    private String screenInstallerPhone;

    @ApiModelProperty("屏幕说明，不超过512字")
    private String screenTips;

    @ApiModelProperty("记录创建时间")
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date createDate;

    @ApiModelProperty("记录创建人")
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty("最近更新时间")
    @TableField(value = "update_date", fill = FieldFill.UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date updateDate;

    @ApiModelProperty("最新跟进人")
    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    private String updateBy;

    @ApiModelProperty("备注说明")
    private String remarkTips;

    public void setId(String id) {
        this.id = id;
    }

    public String getId(){
        return this.id;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getDelFlag(){
        return this.delFlag;
    }

    public void setScreenStatus(Integer screenStatus) {
        this.screenStatus = screenStatus;
    }

    public Integer getScreenStatus(){
        return this.screenStatus;
    }

    public void setScreenPositionLong(BigDecimal screenPositionLong) {
        this.screenPositionLong = screenPositionLong;
    }

    public BigDecimal getScreenPositionLong(){
        return this.screenPositionLong;
    }

    public void setScreenPositionLati(BigDecimal screenPositionLati) {
        this.screenPositionLati = screenPositionLati;
    }

    public BigDecimal getScreenPositionLati(){
        return this.screenPositionLati;
    }

    public void setScreenSize(BigDecimal screenSize) {
        this.screenSize = screenSize;
    }

    public BigDecimal getScreenSize(){
        return this.screenSize;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getScreenName(){
        return this.screenName;
    }

    public Integer getScreenMaxSourceCount() {
        return screenMaxSourceCount;
    }

    public void setScreenMaxSourceCount(Integer screenMaxSourceCount) {
        this.screenMaxSourceCount = screenMaxSourceCount;
    }

    public void setScreenAddress(String screenAddress) {
        this.screenAddress = screenAddress;
    }

    public String getScreenAddress(){
        return this.screenAddress;
    }

    public void setScreenLabel(String screenLabel) {
        this.screenLabel = screenLabel;
    }

    public String getScreenLabel(){
        return this.screenLabel;
    }

    public void setScreenInstaller(String screenInstaller) {
        this.screenInstaller = screenInstaller;
    }

    public String getScreenInstaller(){
        return this.screenInstaller;
    }

    public void setScreenInstallerPhone(String screenInstallerPhone) {
        this.screenInstallerPhone = screenInstallerPhone;
    }

    public String getScreenInstallerPhone(){
        return this.screenInstallerPhone;
    }

    public void setScreenTips(String screenTips) {
        this.screenTips = screenTips;
    }

    public String getScreenTips(){
        return this.screenTips;
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
    public ScreenBaseInfoEntity fromMap(Map<String, Object> valueMap) {
        return BeanUtil.mapToBean(valueMap, this.getClass(), false, null);
    }

    @Override
    public String toString(){
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("delFlag", getDelFlag())
            .append("screenStatus", getScreenStatus())
            .append("screenPositionLong", getScreenPositionLong())
            .append("screenPositionLati", getScreenPositionLati())
            .append("screenSize", getScreenSize())
            .append("screenName", getScreenName())
            .append("screenMaxSourceCount", getScreenMaxSourceCount())
            .append("screenAddress", getScreenAddress())
            .append("screenLabel", getScreenLabel())
            .append("screenInstaller", getScreenInstaller())
            .append("screenInstallerPhone", getScreenInstallerPhone())
            .append("screenTips", getScreenTips())
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
        ScreenBaseInfoEntity that = (ScreenBaseInfoEntity) o;
        return new EqualsBuilder()
                .append(id, that.id)
                .append(delFlag, that.delFlag)
                .append(screenStatus, that.screenStatus)
                .append(screenPositionLong, that.screenPositionLong)
                .append(screenPositionLati, that.screenPositionLati)
                .append(screenSize, that.screenSize)
                .append(screenName, that.screenName)
                .append(screenMaxSourceCount, that.screenMaxSourceCount)
                .append(screenAddress, that.screenAddress)
                .append(screenLabel, that.screenLabel)
                .append(screenInstaller, that.screenInstaller)
                .append(screenInstallerPhone, that.screenInstallerPhone)
                .append(screenTips, that.screenTips)
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
                .append(id)
                .append(delFlag)
                .append(screenStatus)
                .append(screenPositionLong)
                .append(screenPositionLati)
                .append(screenSize)
                .append(screenName)
                .append(screenMaxSourceCount)
                .append(screenAddress)
                .append(screenLabel)
                .append(screenInstaller)
                .append(screenInstallerPhone)
                .append(screenTips)
                .append(createDate)
                .append(createBy)
                .append(updateDate)
                .append(updateBy)
                .append(remarkTips)
                .toHashCode();
    }
}
