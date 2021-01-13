package com.xqoo.sms.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

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

                        @ApiModelProperty("模板名称")
        private String templateName;

                        @ApiModelProperty("模板内容")
        private String templateContent;

                        @ApiModelProperty("模板类型")
        private String templateType;

                        @ApiModelProperty("平台id")
        private String servicePlatformId;

                        @ApiModelProperty("平台名称")
        private String servicePlatformName;

                        @ApiModelProperty("模板状态0:可用 1:不可以")
        private Integer state;

        @TableField(value = "create_by", fill = FieldFill.INSERT)
                        @ApiModelProperty("创建人")
        private String createBy;

            @TableField(value = "create_date", fill = FieldFill.INSERT)
                    @ApiModelProperty("创建时间")
        private Date createDate;

                @TableField(value = "update_by", fill = FieldFill.UPDATE)
                @ApiModelProperty("最近修改人")
        private String updateBy;

                    @TableField(value = "update_date", fill = FieldFill.UPDATE)
            @ApiModelProperty("最近修改时间")
        private Date updateDate;

                        @ApiModelProperty("备注信息")
        private String remarkTips;

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

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateContent() {
        return templateContent;
    }

    public void setTemplateContent(String templateContent) {
        this.templateContent = templateContent;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemarkTips() {
        return remarkTips;
    }

    public void setRemarkTips(String remarkTips) {
        this.remarkTips = remarkTips;
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
    
}