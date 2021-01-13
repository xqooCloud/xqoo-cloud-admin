package com.xqoo.sms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


/**
 * @author mumu
 * @date 2019/11/08 上午11:17
 **/
@SuppressWarnings("serial")

@ApiModel("")
@TableName(value = "service_platform")
public class ServicePlatformEntity extends Model<ServicePlatformEntity> {


    @ApiModelProperty("平台id")    
    @TableId(value = "service_platform_id")
    private String servicePlatformId;

                        @ApiModelProperty("平台名称")
        private String servicePlatformName;

                        @ApiModelProperty("连接参数")
        private String secretParam;

    public ServicePlatformEntity() {
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

    public String getSecretParam() {
        return secretParam;
    }

    public void setSecretParam(String secretParam) {
        this.secretParam = secretParam;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.servicePlatformId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ServicePlatformEntity").append('[')
                .append("servicePlatformId=")
                .append(servicePlatformId)
                .append(",servicePlatformName=")
                .append(servicePlatformName)
                .append(",secretParam=")
                .append(secretParam)
                .append(']');
        return sb.toString();
    }
}