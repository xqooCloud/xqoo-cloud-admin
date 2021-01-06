package com.xqoo.client.controller.demo;

import cn.hutool.core.date.DateUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.xqoo.common.core.exception.SystemException;
import com.xqoo.common.core.utils.JacksonUtils;
import com.xqoo.common.core.utils.RandomUtil;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.feign.annotations.OperationLog;
import com.xqoo.feign.enums.operlog.OperationTypeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * FileName: HelloController.java
 * 控制器演示，与常规springBoot项目无异
 * @author 高扬
 * @Date   2019-11-09
 * @version 1.00

 */
@RestController(value = "clientTest")
@RequestMapping(value = "/hello")
@Api("常规")
public class TestController {

    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @Value("${ribbon.ReadTimeout}")
    private int time;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

//
//    @XxlConf(value = "default.test")
//    private String test;

    @GetMapping(value = "/oneTest")
    @ApiOperation(value = "测试控制器")
    @OperationLog(tips = "测试日志注解", operatorType = OperationTypeEnum.ADD)
    public ResultEntity<String> oneTest(@RequestParam(required = false) String dd){
//        throw new SystemException("dqwdqwdqd");
        logger.info("开始执行");
        return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "ddasdaasdasd");
    }

    @PostMapping(value = "/testRequestData")
    @ApiOperation(value = "测试put数据是添加请求参数到操作日志")
    @OperationLog(tips = "测试添加请求日志注解", operatorType = OperationTypeEnum.ADD, isSaveRequestData = true)
    public ResultEntity<String> testRequestData(@RequestBody Map<String, String> map){
//        throw new SystemException("dqwdqwdqd");
        return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "ddasdaasdasd");
    }

    @PostMapping(value = "/testRequestErrData")
    @ApiOperation(value = "测试接口异常添加到操作日志")
    @OperationLog(tips = "测试添加请求日志注解", operatorType = OperationTypeEnum.ADD, isSaveRequestData = true)
    public ResultEntity<String> testRequestErrData(@RequestBody Map<String, String> map){
        throw new SystemException("dqwdqwdqd");
//        return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "ddasdaasdasd");
    }

    @PostMapping(value = "/addRedisData")
    @ApiOperation(value = "测试接口异常添加到操作日志")
    public ResultEntity<String> addRedisData(){
        for(int i = 0;i < 2000; i++){
            ObjectNode objectNode = JacksonUtils.createObjectNode();
            objectNode.put("sendType", 0);
            objectNode.put("showName", "原告-" + i%100 + "-李某说：");
            if(i%100 < 1){
                objectNode.put("userName", "原告-李某");
            }
            else if(i%100 < 5){
                objectNode.put("userName", "原告-张王企鹅群翁");
            }
            else if(i%100 < 10){
                objectNode.put("userName", "原告-计划更换就");
            }
            else if(i%100 < 15){
                objectNode.put("userName", "原告-土匪哥");
            }
            else if(i%100 < 19){
                objectNode.put("userName", "原告-我二姨");
            }
            else{
                objectNode.put("userName", "原告-恶趣味");
            }
            objectNode.put("roleName", "原告");
            objectNode.put("sortTime", System.currentTimeMillis());
            objectNode.put("createTime", DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
            objectNode.put("text", "随机字符串-" + RandomUtil.randomStr(6) + "-" + i%100);
            objectNode.put("trialId", "1696524695319744");
            redisTemplate.opsForList().leftPush("speechAi:2646219023306726", objectNode);
        }
        return new ResultEntity<>(HttpStatus.OK, "完成");
    }


    @GetMapping(value="/twoTest")
    @ApiOperation(value = "测试控制器2")
    @SentinelResource(value="xqoo-test-two", blockHandler="helloHandler")
    public ResultEntity<String> twoTest() throws InterruptedException {
        redisTemplate.opsForValue().get("dd");
        Thread.sleep(20000L);
//        throw new BusinessException("司机里");
        return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, String.valueOf(time));
    }

    public ResultEntity<String> helloHandler(BlockException ex) {
        System.out.println("Oops: " + ex.getClass().getCanonicalName());
        return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "此接口被限流了哦");
    }
}
