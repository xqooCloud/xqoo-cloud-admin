package com.xqoo.sms.controller;

import com.xqoo.common.entity.ResultEntity;
import com.xqoo.sms.entity.ServicePlatformEntity;
import com.xqoo.sms.service.ServicePlatformService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    @GetMapping("listService")
    public ResultEntity listService(){
        List<ServicePlatformEntity> list = servicePlatformService.list();
        return new ResultEntity(list);
    }

    @PostMapping("updateServicePlatform")
    public ResultEntity updateServicePlatform(@RequestBody ServicePlatformEntity servicePlatformEntity) {
        boolean b = servicePlatformService.updateById(servicePlatformEntity);
        return new ResultEntity(b);
    }
}