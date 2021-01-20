package com.xqoo.email.bo;

import com.xqoo.common.page.PageRequestBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("查询邮件模板实体")
public class EmailTemplateBO extends PageRequestBean {

    @ApiModelProperty("模板名称")
    private String templateName;

    @ApiModelProperty("邮件标题")
    private String emailSubject;

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
}
