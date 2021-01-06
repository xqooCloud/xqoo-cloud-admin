package com.xqoo.client.controller.demo;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.feign.service.uidgenerator.UidGeneratorFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * FileName: HelloController.java
 * 控制器演示，与常规springBoot项目无异
 * @author 高扬
 * @Date   2019-11-09
 * @version 1.00

 */
@RestController(value = "client")
@RequestMapping(value = "/helloworld")
@Api("常规")
public class HelloController {

    @Value("${ribbon.ReadTimeout}")
    private int time;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private UidGeneratorFeign uidGeneratorFeign;
//
//    @XxlConf(value = "default.test")
//    private String test;

    @GetMapping(value = "/oneTest")
    @ApiOperation(value = "测试控制器")
    public ResultEntity<List<String>> dnqnqn(){
//        String uid = uidGeneratorFeign.getUid("dd");
        redisTemplate.opsForValue().get("dd");
        Set<String> keys = redisTemplate.keys("access:*");
        List<String> list = new ArrayList<>();
        keys.forEach(item -> {
            Long expire = redisTemplate.getExpire(item);
            if(expire > 258000L){
                list.add(item);
            }
        });
        return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "list", list);
    }

    @GetMapping(value="/twoTest")
    @ApiOperation(value = "测试控制器2")
    @SentinelResource(value="xqoo-test-one", blockHandler="helloworldHandler")
    public ResultEntity<String> dwqdqwd(){
        redisTemplate.opsForValue().get("dd");
        return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, String.valueOf(time));
    }

    public ResultEntity<String> helloworldHandler(BlockException ex) {
        System.out.println("Oops: " + ex.getClass().getCanonicalName());
        return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "此接口被限流了哦");
    }
}
