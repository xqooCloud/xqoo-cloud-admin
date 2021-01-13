package com.xqoo.email.bo;

import com.xqoo.common.page.PageRequestBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

@ApiModel("查询邮件配置实体")
public class EmailConfigBO extends PageRequestBean {

    @ApiModelProperty("配置key")
    private String configKey;

    @ApiModelProperty("配置组")
    private String emailGroup;


    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getEmailGroup() {
        return emailGroup;
    }

    public void setEmailGroup(String emailGroup) {
        this.emailGroup = emailGroup;
    }
}
