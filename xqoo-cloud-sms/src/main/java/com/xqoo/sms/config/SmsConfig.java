package com.xqoo.sms.config;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xqoo.common.core.utils.JacksonUtils;
import com.xqoo.sms.bean.ALiSmsConfigBean;
import com.xqoo.sms.constant.SmsConstant;
import com.xqoo.sms.entity.ErrCodeMessageEntity;
import com.xqoo.sms.entity.ServicePlatformEntity;
import com.xqoo.sms.entity.SysSmsInitEntity;
import com.xqoo.sms.service.ErrCodeMessageService;
import com.xqoo.sms.service.ServicePlatformService;
import com.xqoo.sms.service.SysSmsInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class SmsConfig {
    @Resource
    ServicePlatformService servicePlatformService;

    @Autowired
    ErrCodeMessageService errCodeMessageService;

    @Resource
    RedisTemplate redisTemplate;
    @Autowired
    SysSmsInitService sysSmsInitService;
    private ALiSmsConfigBean aLiSmsConfigBean =new ALiSmsConfigBean();

    public ALiSmsConfigBean getaLiSmsConfigBean() {
        return aLiSmsConfigBean;
    }

    public void setaLiSmsConfigBean(ALiSmsConfigBean aLiSmsConfigBean) {
        this.aLiSmsConfigBean = aLiSmsConfigBean;
    }

    @Bean(name="aLiSmsConfigBean")
    public ALiSmsConfigBean aLiSmsConfigBean(){
        return this.aLiSmsConfigBean;
    }
    @PostConstruct
    public void initALiSmsConfigBean(){
        servicePlatformService.getALiSmsConfigBean();
    }


}
