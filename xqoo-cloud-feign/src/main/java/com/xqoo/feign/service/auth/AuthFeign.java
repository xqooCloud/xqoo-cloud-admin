package com.xqoo.feign.service.auth;

import com.xqoo.feign.factory.auth.AuthFeignFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author mumu
 * @date 2019/11/15 上午11:26
 **/
@Component
@FeignClient(name = "xqoo-cloud-authorization", fallbackFactory = AuthFeignFactory.class)
public interface AuthFeign {

    @GetMapping("/authen/isLogin")
    byte[] isLogin(@RequestParam(required = false, value = "token") String token);

    @GetMapping("/authen/getLoginUserInfo")
    byte[] getLoginUserInfo(@RequestParam(required = false, value = "token") String token);
}
