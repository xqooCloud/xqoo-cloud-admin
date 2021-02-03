package com.xqoo.device.feign;

import com.xqoo.device.service.ScreenBaseInfoService;
import com.xqoo.feign.dto.device.DeviceInfoDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gaoyang
 */
@RestController
@RequestMapping("/deviceHandleFeign")
public class DeviceHandleFeign {

    @Autowired
    private ScreenBaseInfoService screenBaseInfoService;

    @GetMapping("/getDeviceInfo")
    public DeviceInfoDetailDTO getDeviceInfo(@RequestParam(value = "id") String id){
        return screenBaseInfoService.getDeviceInfoForPrivate(id);
    }
}
