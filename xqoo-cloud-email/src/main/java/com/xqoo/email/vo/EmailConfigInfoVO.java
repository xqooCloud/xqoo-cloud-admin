package com.xqoo.email.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ApiModel("邮件配置展示")
public class EmailConfigInfoVO implements Serializable {

    @ApiModelProperty("自增长id")
    private Integer id;

    @ApiModelProperty("配置key")
    @NotNull(message = "配置key不能为空")
    private String configKey;

    @ApiModelProperty("配置值")
    @NotNull(message = "配置值不能为空")
    private String configValue;


    @ApiModelProperty("所属组")
    private String emailGroup;

    @Override
    public String toString() {
        return "EmailConfigInfoVO{" +
                "id=" + id +
                ", configKey='" + configKey + '\'' +
                ", configValue='" + configValue + '\'' +
                ", emailGroup='" + emailGroup + '\'' +
                '}';
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public String getEmailGroup() {
        return emailGroup;
    }

    public void setEmailGroup(String emailGroup) {
        this.emailGroup = emailGroup;
    }
}
