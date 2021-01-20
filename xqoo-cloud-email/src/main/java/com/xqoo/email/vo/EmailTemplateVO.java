package com.xqoo.email.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

/**
 * @author: zhangdong
 * @date 2021/1/20
 * @description TODO
 */
public class EmailTemplateVO {

    @ApiModelProperty("自增长id")
    private Integer id;

    @ApiModelProperty("模板名称")
    @NotNull(message = "模板名称不能为空")
    private String templateName;

    @ApiModelProperty("邮件标题")
    @NotNull(message = "邮件标题不能为空")
    private String emailSubject;

    @ApiModelProperty("邮件内容")
    @NotNull(message = "邮件内容不能为空")
    private String emailText;

    @ApiModelProperty("模板类型")
    private Integer templateType;

    @ApiModelProperty("删除标识")
    private Integer delFlag;

    @ApiModelProperty("邮件文件路径")
    private String emailFilePath;

    @ApiModelProperty("邮件文件名称")
    private String emailFileName;

    @ApiModelProperty("创建人")
    private String createBy;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date createDate;

    @ApiModelProperty("最近修改人")
    private String updateBy;

    @ApiModelProperty("最近修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date updateDate;

    @ApiModelProperty("备注信息")
    private String remarkTips;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmailTemplateVO)) return false;
        EmailTemplateVO that = (EmailTemplateVO) o;
        return Objects.equals(getTemplateName(), that.getTemplateName()) &&
                Objects.equals(getEmailSubject(), that.getEmailSubject()) &&
                Objects.equals(getEmailText(), that.getEmailText()) &&
                Objects.equals(getTemplateType(), that.getTemplateType()) &&
                Objects.equals(getDelFlag(), that.getDelFlag()) &&
                Objects.equals(getEmailFilePath(), that.getEmailFilePath()) &&
                Objects.equals(getEmailFileName(), that.getEmailFileName()) &&
                Objects.equals(getCreateBy(), that.getCreateBy()) &&
                Objects.equals(getCreateDate(), that.getCreateDate()) &&
                Objects.equals(getUpdateBy(), that.getUpdateBy()) &&
                Objects.equals(getUpdateDate(), that.getUpdateDate()) &&
                Objects.equals(getRemarkTips(), that.getRemarkTips());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTemplateName(), getEmailSubject(), getEmailText(), getTemplateType(), getDelFlag(), getEmailFilePath(), getEmailFileName(), getCreateBy(), getCreateDate(), getUpdateBy(), getUpdateDate(), getRemarkTips());
    }

    @Override
    public String toString() {
        return "EmailTemplateVO{" +
                "templateName='" + templateName + '\'' +
                ", emailSubject='" + emailSubject + '\'' +
                ", emailText='" + emailText + '\'' +
                ", templateType=" + templateType +
                ", delFlag=" + delFlag +
                ", emailFilePath='" + emailFilePath + '\'' +
                ", emailFileName='" + emailFileName + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createDate=" + createDate +
                ", updateBy='" + updateBy + '\'' +
                ", updateDate=" + updateDate +
                ", remarkTips='" + remarkTips + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getEmailText() {
        return emailText;
    }

    public void setEmailText(String emailText) {
        this.emailText = emailText;
    }

    public Integer getTemplateType() {
        return templateType;
    }

    public void setTemplateType(Integer templateType) {
        this.templateType = templateType;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getEmailFilePath() {
        return emailFilePath;
    }

    public void setEmailFilePath(String emailFilePath) {
        this.emailFilePath = emailFilePath;
    }

    public String getEmailFileName() {
        return emailFileName;
    }

    public void setEmailFileName(String emailFileName) {
        this.emailFileName = emailFileName;
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
}
