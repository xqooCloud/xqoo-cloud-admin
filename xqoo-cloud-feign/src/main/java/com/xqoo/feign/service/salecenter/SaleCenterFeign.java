package com.xqoo.feign.service.salecenter;

import com.xqoo.feign.config.FeignSupportConfig;
import com.xqoo.feign.factory.salecenter.SaleCenterFeignFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author gaoyang
 */
@Component
@FeignClient(name = "xqoo-sale-center", fallbackFactory = SaleCenterFeignFactory.class, configuration = FeignSupportConfig.class)
public interface SaleCenterFeign {

    @PostMapping("/saleCenterHandleFeign/getHasSaleInfoScreen")
    byte[] getHasSaleInfoScreen(@RequestBody List<String> screenIds);

    @GetMapping("/saleCenterHandleFeign/getExistSaleInfoByScreenId")
    Boolean getExistSaleInfoByScreenId(@RequestParam(value = "screenId") String screenId);
}
