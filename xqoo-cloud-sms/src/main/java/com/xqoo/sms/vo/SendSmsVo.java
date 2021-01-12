package com.xqoo.sms.vo;

import com.xqoo.common.core.utils.JacksonUtils;

import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;

public class SendSmsVo {
    @NotNull(message = "ewqewqewq")
    @Size(min = 1)
    List<String> phoneNumbers;
    @NotBlank(message = "")
    String sign;
    String templateCode;
    Map<String, Object> smsParamJson;


    public SendSmsVo() {

    }
    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public Map<String, Object> getSmsParamJson() {
        return smsParamJson;
    }

    public void setSmsParamJson(Map<String, Object> smsParamJson) {
        this.smsParamJson = smsParamJson;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SendSmsVo").append('[')
                .append("phoneNumbers=")
                .append(phoneNumbers)
                .append(",sign=")
                .append(sign)
                .append(",templateCode=")
                .append(templateCode)
                .append(",smsParamJson=")
                .append(smsParamJson)
                .append(']');
        return sb.toString();
    }
}
