package com.xqoo.feign.service.uidgenerator;

import com.xqoo.feign.factory.uidgenerator.UidgeneratorFeignFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author gaoyang
 * @date 2019/11/13
 * 流水号uid获取工具
 **/

@Component
@FeignClient(name = "xqoo-uid-generator", fallbackFactory = UidgeneratorFeignFactory.class)
public interface UidGeneratorFeign {

    @GetMapping("/generatorUid/getUid")
    String getUid(@RequestParam(value = "type") String type);
}
