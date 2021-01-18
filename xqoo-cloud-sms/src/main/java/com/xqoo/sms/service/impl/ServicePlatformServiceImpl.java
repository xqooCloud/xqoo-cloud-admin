package com.xqoo.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xqoo.common.core.utils.JacksonUtils;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.sms.bean.ALiSmsConfigBean;
import com.xqoo.sms.config.SmsConfig;
import com.xqoo.sms.constant.SmsConstant;
import com.xqoo.sms.entity.ErrCodeMessageEntity;
import com.xqoo.sms.entity.SysSmsInitEntity;
import com.xqoo.sms.mapper.ServicePlatformMapper;
import com.xqoo.sms.entity.ServicePlatformEntity;
import com.xqoo.sms.service.ErrCodeMessageService;
import com.xqoo.sms.service.ServicePlatformService;
import com.xqoo.sms.service.SmsSignService;
import com.xqoo.sms.service.SysSmsInitService;
import com.xqoo.sms.utils.SpringBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (ServicePlatform)表服务实现类
 *
 * @author makejava
 * @since 2021-01-11 14:41:26
 */
@Service("servicePlatformService")
public class ServicePlatformServiceImpl extends ServiceImpl<ServicePlatformMapper, ServicePlatformEntity> implements ServicePlatformService {
    @Resource
    RedisTemplate redisTemplate;
    @Resource(name="aLiSmsConfigBean")
    ALiSmsConfigBean aLiSmsConfigBean;
    @Resource
    SysSmsInitService sysSmsInitService;
    @Resource
    ErrCodeMessageService errCodeMessageService;

    @Override
    public ResultEntity changeServiceParam() {
        getALiSmsConfigBean();
        return new ResultEntity("更新短信参数成功");
    }

    public void getALiSmsConfigBean() {
        QueryWrapper<ServicePlatformEntity> platformEntityQueryWrapper = new QueryWrapper<>();
        platformEntityQueryWrapper.lambda().eq(ServicePlatformEntity::getServicePlatformName, SmsConstant.A_LI_SMS);
        ServicePlatformEntity platformEntity = this.getOne(platformEntityQueryWrapper);
        List<ErrCodeMessageEntity> list = errCodeMessageService.list(new QueryWrapper<ErrCodeMessageEntity>().lambda().eq(ErrCodeMessageEntity::getServicePlatorm,SmsConstant.A_LI_SMS));
        System.out.println(platformEntity.toString());
        Map<String,String> errCodeMap = new HashMap<>();
        for (ErrCodeMessageEntity errCodeMessageEntity : list) {
            errCodeMap.put(errCodeMessageEntity.getCode(), errCodeMessageEntity.getMessage());
        }
        ALiSmsConfigBean aLiSmsConfigBean = JacksonUtils.toObj(platformEntity.getSecretParam(), ALiSmsConfigBean.class);
        aLiSmsConfigBean.setErrCodeMap(errCodeMap);
        aLiSmsConfigBean.setName(platformEntity.getServicePlatformName());
        //SpringBeanUtil.updateBean(aLiSmsConfigBean, this.aLiSmsConfigBean);
        this.aLiSmsConfigBean.setName(aLiSmsConfigBean.getName());
        this.aLiSmsConfigBean.setErrCodeMap(aLiSmsConfigBean.getErrCodeMap());
        this.aLiSmsConfigBean.setAccessKeyId(aLiSmsConfigBean.getAccessKeyId());
        this.aLiSmsConfigBean.setRegionId(aLiSmsConfigBean.getRegionId());
        this.aLiSmsConfigBean.setAccessKeySecret(aLiSmsConfigBean.getAccessKeySecret());
        this.aLiSmsConfigBean.setVersion(aLiSmsConfigBean.getVersion());
        this.aLiSmsConfigBean.setSysDomain(aLiSmsConfigBean.getSysDomain());
        this.aLiSmsConfigBean.setSign(aLiSmsConfigBean.getSign());
        initSmsRedis();
        //this.aLiSmsConfigBean = aLiSmsConfigBean;
    }

    @Override
    public void initSmsRedis() {
        SysSmsInitEntity initEntity = sysSmsInitService.getById(1);
       Map<String, Object> map = JacksonUtils.toObj(initEntity.getInitParam(),Map.class);
        for (String s : map.keySet()) {
          redisTemplate.opsForValue().set(s,map.getOrDefault(s,"ALiSms"));
       }
    }

}