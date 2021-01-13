package com.xqoo.sms.controller;

import com.xqoo.sms.entity.ServicePlatformEntity;
import com.xqoo.sms.service.ServicePlatformService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (ServicePlatform)表控制层
 *
 * @author makejava
 * @since 2021-01-11 14:41:26
 */
@RestController
@RequestMapping("servicePlatform")
public class ServicePlatformController {
    /**
     * 服务对象
     */
    @Resource
    private ServicePlatformService servicePlatformService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public ServicePlatformEntity selectOne(String id) {
        return null;
    }

}