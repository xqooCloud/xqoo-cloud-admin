package com.xqoo.sms.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * @author mumu
 * @date 2019/11/08 上午11:17
 **/
@SuppressWarnings("serial")
@ApiModel("")
@TableName(value = "sms_sign")
public class SmsSignEntity extends Model<SmsSignEntity> {


        
    @TableId(value = "id")
    private Integer id;

                        @ApiModelProperty("签名名称")
        private String sign;

                        @ApiModelProperty("平台id")
        private String servicePlatformId;

                        @ApiModelProperty("平台名称")
        private String servicePlatformName;

    public SmsSignEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
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

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SmsSignEntity").append('[')
                .append("id=")
                .append(id)
                .append(",sign=")
                .append(sign)
                .append(",servicePlatformId=")
                .append(servicePlatformId)
                .append(",servicePlatformName=")
                .append(servicePlatformName)
                .append(']');
        return sb.toString();
    }
}