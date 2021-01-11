package com.xqoo.email.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import cn.hutool.core.bean.BeanUtil;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import java.util.Map;
import java.util.Objects;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.lang.Integer;

/**
 * email_template实体类
 *
 * @author xqoo-code-gen
 * @date 2021-01-11 17:06:49
 */
@ApiModel("email_template实体")
@TableName(value = "email_template")
public class EmailTemplateEntity extends Model<EmailTemplateEntity> {

    private static final long serialVersionUID = -575753128031158557L;

    @ApiModelProperty("模板主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("模板名称")
    private String templateName;

    @ApiModelProperty("模板类型：0文本型 ，1附件型 ，2静态资源类型")
    private Integer templateType;

    @ApiModelProperty("邮件标题")
    private String emailSubject;

    @ApiModelProperty("邮件文本内容")
    private String emailText;

    @ApiModelProperty("文件地址（多个以逗号隔开）")
    private String emailFilePath;

    @ApiModelProperty("文件名称（多个以逗号隔开）")
    private String emailFileName;

    @ApiModelProperty("逻辑删除标志，0-未删除，1-已删除")
    private Integer delFlag;

    @ApiModelProperty("创建人")
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private String createBy;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date createDate;

    @ApiModelProperty("修改人")
    @TableField(value = "update_by", fill = FieldFill.UPDATE)
    private String updateBy;

    @ApiModelProperty("修改时间")
    @TableField(value = "update_date", fill = FieldFill.UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date updateDate;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId(){
        return this.id;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateName(){
        return this.templateName;
    }

    public void setTemplateType(Integer templateType) {
        this.templateType = templateType;
    }

    public Integer getTemplateType(){
        return this.templateType;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getEmailSubject(){
        return this.emailSubject;
    }

    public void setEmailText(String emailText) {
        this.emailText = emailText;
    }

    public String getEmailText(){
        return this.emailText;
    }

    public void setEmailFilePath(String emailFilePath) {
        this.emailFilePath = emailFilePath;
    }

    public String getEmailFilePath(){
        return this.emailFilePath;
    }

    public void setEmailFileName(String emailFileName) {
        this.emailFileName = emailFileName;
    }

    public String getEmailFileName(){
        return this.emailFileName;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getDelFlag(){
        return this.delFlag;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateBy(){
        return this.createBy;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCreateDate(){
        return this.createDate;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateBy(){
        return this.updateBy;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getUpdateDate(){
        return this.updateDate;
    }

    public Map<String, Object> toMap(){
        return BeanUtil.beanToMap(this);
    }

    /**
     * 将map直接赋值到bean中并返回一个新的bean
     * @param valueMap 需要转换的map
     */
    public EmailTemplateEntity fromMap(Map<String, Object> valueMap) {
        return BeanUtil.mapToBean(valueMap, this.getClass(), false, null);
    }

    @Override
    public String toString(){
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("templateName", getTemplateName())
            .append("templateType", getTemplateType())
            .append("emailSubject", getEmailSubject())
            .append("emailText", getEmailText())
            .append("emailFilePath", getEmailFilePath())
            .append("emailFileName", getEmailFileName())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createDate", getCreateDate())
            .append("updateBy", getUpdateBy())
            .append("updateDate", getUpdateDate())
            .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailTemplateEntity that = (EmailTemplateEntity) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(templateName, that.templateName) &&
            Objects.equals(templateType, that.templateType) &&
            Objects.equals(emailSubject, that.emailSubject) &&
            Objects.equals(emailText, that.emailText) &&
            Objects.equals(emailFilePath, that.emailFilePath) &&
            Objects.equals(emailFileName, that.emailFileName) &&
            Objects.equals(delFlag, that.delFlag) &&
            Objects.equals(createBy, that.createBy) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(updateBy, that.updateBy) &&
            Objects.equals(updateDate, that.updateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                    id,
                    templateName,
                    templateType,
                    emailSubject,
                    emailText,
                    emailFilePath,
                    emailFileName,
                    delFlag,
                    createBy,
                    createDate,
                    updateBy,
                    updateDate
                );
    }
}
