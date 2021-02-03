package com.xqoo.feign.factory.device;

import com.xqoo.feign.service.device.DeviceHandleFeign;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author gaoyang
 */
@Component
public class DeviceHandleFeignFactory implements FallbackFactory<DeviceHandleFeign> {

    private final static Logger logger = LoggerFactory.getLogger(DeviceHandleFeignFactory.class);

    @Override
    public DeviceHandleFeign create(Throwable throwable) {
        logger.error("[设备模块]服务不可用，请检查服务是否正常运行" + throwable.getMessage());
        return new DeviceHandleFeign() {
            @Override
            public byte[] getDeviceInfo(String id) {
                return new byte[0];
            }
        };
    }
}
