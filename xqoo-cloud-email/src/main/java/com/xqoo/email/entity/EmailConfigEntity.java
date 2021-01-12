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

import java.lang.Integer;

/**
 * email_config实体类
 *
 * @author xqoo-code-gen
 * @date 2021-01-11 16:52:01
 */
@ApiModel("email_config实体")
@TableName(value = "email_config")
public class EmailConfigEntity extends Model<EmailConfigEntity> {

    private static final long serialVersionUID = -268100368287165301L;

    @ApiModelProperty("")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("邮箱配置key")
    private String configKey;

    @ApiModelProperty("邮箱配置value")
    private String configValue;

    @ApiModelProperty("邮箱配置组")
    private String emailGroup;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId(){
        return this.id;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigKey(){
        return this.configKey;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public String getConfigValue(){
        return this.configValue;
    }

    public void setEmailGroup(String emailGroup) {
        this.emailGroup = emailGroup;
    }

    public String getEmailGroup(){
        return this.emailGroup;
    }

    public Map<String, Object> toMap(){
        return BeanUtil.beanToMap(this);
    }

    /**
     * 将map直接赋值到bean中并返回一个新的bean
     * @param valueMap 需要转换的map
     */
    public EmailConfigEntity fromMap(Map<String, Object> valueMap) {
        return BeanUtil.mapToBean(valueMap, this.getClass(), false, null);
    }

    @Override
    public String toString(){
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("configKey", getConfigKey())
            .append("configValue", getConfigValue())
            .append("emailGroup", getEmailGroup())
            .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailConfigEntity that = (EmailConfigEntity) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(configKey, that.configKey) &&
            Objects.equals(configValue, that.configValue) &&
            Objects.equals(emailGroup, that.emailGroup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                    id,
                    configKey,
                    configValue,
                    emailGroup
                );
    }
}
