package com.xqoo.sms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.sms.bean.ALiSmsConfigBean;
import com.xqoo.sms.config.SmsConfig;
import com.xqoo.sms.mapper.ServicePlatformMapper;
import com.xqoo.sms.entity.ServicePlatformEntity;
import com.xqoo.sms.service.ServicePlatformService;
import com.xqoo.sms.utils.SpringBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (ServicePlatform)表服务实现类
 *
 * @author makejava
 * @since 2021-01-11 14:41:26
 */
@Service("servicePlatformService")
public class ServicePlatformServiceImpl extends ServiceImpl<ServicePlatformMapper, ServicePlatformEntity> implements ServicePlatformService {
    @Resource
    SmsConfig smsConfig;
    @Resource(name="aLiSmsConfigBean")
    ALiSmsConfigBean aLiSmsConfigBean;

    @Override
    public ResultEntity changeServiceParam() {
        ALiSmsConfigBean aLiSmsConfigBeana = smsConfig.aLiSmsConfigBean();
        SpringBeanUtil.updateBean(this.aLiSmsConfigBean, aLiSmsConfigBeana);
        System.out.println(aLiSmsConfigBean.getName());
        return new ResultEntity(aLiSmsConfigBean.getName());
    }

}