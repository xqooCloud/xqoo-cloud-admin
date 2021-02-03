package com.xqoo.feign.service.device;

import com.xqoo.feign.config.FeignSupportConfig;
import com.xqoo.feign.factory.device.DeviceHandleFeignFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author gaoyang
 */
@Component
@FeignClient(name = "xqoo-device-manager", fallbackFactory = DeviceHandleFeignFactory.class, configuration = FeignSupportConfig.class)
public interface DeviceHandleFeign {

    @GetMapping("/deviceHandleFeign/getDeviceInfo")
    byte[] getDeviceInfo(@RequestParam(value = "id") String id);
}
