package com.xqoo.email.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Map;

/**
 * @author: zhangdong
 * @date 2021/1/23
 * @description TODO
 */
@ApiModel("邮件发送参数")
public class EmailSendBO {

    @ApiModelProperty("模板ID")
    @NotNull(message = "模板ID不能为空")
    private Integer templateId;

    @ApiModelProperty("接收人邮件")
    @NotNull(message = "接收人邮件不能为空")
    @Size(min = 1, message = "接收人邮件最少一条")
    private String[] receiverEmail;

    @ApiModelProperty("邮件参数占位符")
    @NotNull(message = "邮件参数占位符不能为空")
    private Map<String,Object> sendMap;

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public String[] getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String[] receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public Map<String, Object> getSendMap() {
        return sendMap;
    }

    public void setSendMap(Map<String, Object> sendMap) {
        this.sendMap = sendMap;
    }
}
