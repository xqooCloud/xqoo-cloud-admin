package com.xqoo.sms.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * @author mumu
 * @date 2019/11/08 上午11:17
 **/
@SuppressWarnings("serial")

@ApiModel("")
@TableName(value = "sys_sms_template")
public class SysSmsTemplateEntity extends Model<SysSmsTemplateEntity> {


    @TableId(value = "id")
    private String id;

    @ApiModelProperty("模板编号")
    private String templateCode;

    @ApiModelProperty("模板内容")
    private String templateContent;

    @ApiModelProperty("模板状态")
    private Integer templateState;

    @ApiModelProperty("平台id")
    private String servicePlatformId;

    @ApiModelProperty("平台名称")
    private String servicePlatformName;

    public SysSmsTemplateEntity() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getTemplateContent() {
        return templateContent;
    }

    public void setTemplateContent(String templateContent) {
        this.templateContent = templateContent;
    }

    public Integer getTemplateState() {
        return templateState;
    }

    public void setTemplateState(Integer templateState) {
        this.templateState = templateState;
    }

    public String getServicePlatformId() {
        return servicePlatformId;
    }

    public void setServicePlatformId(String servicePlatformId) {
        this.servicePlatformId = servicePlatformId;
    }

    public String getServicePlatformName() {
        return servicePlatformName;
    }

    public void setServicePlatformName(String servicePlatformName) {
        this.servicePlatformName = servicePlatformName;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SysSmsTemplateEntity").append('[')
                .append("id=")
                .append(id)
                .append(",templateCode=")
                .append(templateCode)
                .append(",templateContent=")
                .append(templateContent)
                .append(",templateState=")
                .append(templateState)
                .append(",servicePlatformId=")
                .append(servicePlatformId)
                .append(",servicePlatformName=")
                .append(servicePlatformName)
                .append(']');
        return sb.toString();
    }
}