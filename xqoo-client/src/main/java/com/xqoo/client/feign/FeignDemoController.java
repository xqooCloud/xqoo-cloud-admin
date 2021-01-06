package com.xqoo.client.feign;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * FileName: FeignDemoController.java
 * 服务消费DEMO,作为服务提供给其他模块调用
 * 调用参照clientTwo模块调用
 * @author 高扬
 * @Date   2019-11-09
 * @version 1.00

 */

@RestController
@RequestMapping(value = "/feignDemo")
public class FeignDemoController {

    @Value("${server.port}")
    private int port;

    @GetMapping(value = "/FeignDemo")
    public String FeignDemo(){
        String ftw = "Hello World，This is Client No.1,port is :[" + port + "]";
        return ftw;
    }
}
