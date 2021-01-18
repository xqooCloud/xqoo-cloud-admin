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

    @Bean(name="aLiSmsConfigBean")
    public ALiSmsConfigBean aLiSmsConfigBean(){
        return this.aLiSmsConfigBean;
    }

    @PostConstruct
    public void getALiSmsConfigBean() {
        QueryWrapper<ServicePlatformEntity> platformEntityQueryWrapper = new QueryWrapper<>();
        platformEntityQueryWrapper.lambda().eq(ServicePlatformEntity::getServicePlatformName, SmsConstant.A_LI_SMS);
        ServicePlatformEntity platformEntity = servicePlatformService.getOne(platformEntityQueryWrapper);
        List<ErrCodeMessageEntity> list = errCodeMessageService.list(new QueryWrapper<ErrCodeMessageEntity>().lambda().eq(ErrCodeMessageEntity::getServicePlatorm,SmsConstant.A_LI_SMS));
        System.out.println(platformEntity.toString());
        Map<String,String> errCodeMap = new HashMap<>();
        for (ErrCodeMessageEntity errCodeMessageEntity : list) {
            errCodeMap.put(errCodeMessageEntity.getCode(), errCodeMessageEntity.getMessage());
        }
        ALiSmsConfigBean aLiSmsConfigBean = JacksonUtils.toObj(platformEntity.getSecretParam(), ALiSmsConfigBean.class);
        aLiSmsConfigBean.setErrCodeMap(errCodeMap);
        aLiSmsConfigBean.setName(platformEntity.getServicePlatformName());
        this.aLiSmsConfigBean = aLiSmsConfigBean;
    }
}
