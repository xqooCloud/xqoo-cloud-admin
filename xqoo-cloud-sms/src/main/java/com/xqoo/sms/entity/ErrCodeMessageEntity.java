package com.xqoo.sms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.xqoo.sms.constant.SmsConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;


/**
 * @author mumu
 * @date 2019/11/08 上午11:17
 **/
@SuppressWarnings("serial")

@ApiModel("")
@TableName(value = "err_code_message")
public class ErrCodeMessageEntity extends Model<ErrCodeMessageEntity> {


        
    @TableId(value = "id")
    private String id;

                        @ApiModelProperty("错误代码")
        private String code;

                        @ApiModelProperty("错误信息")
        private String message;

                        @ApiModelProperty("平台")
        private String servicePlatorm;

    public ErrCodeMessageEntity() {
    }

    public ErrCodeMessageEntity(String code, String message) {
        this.code = code;
        this.message = message;
        this.servicePlatorm = SmsConstant.A_LI_SMS;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getServicePlatorm() {
        return servicePlatorm;
    }

    public void setServicePlatorm(String servicePlatorm) {
        this.servicePlatorm = servicePlatorm;
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
    
}