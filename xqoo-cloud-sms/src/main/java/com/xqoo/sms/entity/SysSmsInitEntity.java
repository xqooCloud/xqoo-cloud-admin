package com.xqoo.sms.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * @author mumu
 * @date 2019/11/08 上午11:17
 **/
@SuppressWarnings("serial")
@ApiModel("")
@TableName(value = "sys_sms_init")
public class SysSmsInitEntity extends Model<SysSmsInitEntity> {


        
    @TableId(value = "id")
    private Integer id;

                        private String initParam;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInitParam() {
        return initParam;
    }

    public void setInitParam(String initParam) {
        this.initParam = initParam;
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