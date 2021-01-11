package com.xqoo.sms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xqoo.sms.mapper.ServicePlatformMapper;
import com.xqoo.sms.entity.ServicePlatformEntity;
import com.xqoo.sms.service.ServicePlatformService;
import org.springframework.stereotype.Service;

/**
 * (ServicePlatform)表服务实现类
 *
 * @author makejava
 * @since 2021-01-11 14:41:26
 */
@Service("servicePlatformService")
public class ServicePlatformServiceImpl extends ServiceImpl<ServicePlatformMapper, ServicePlatformEntity> implements ServicePlatformService {

}