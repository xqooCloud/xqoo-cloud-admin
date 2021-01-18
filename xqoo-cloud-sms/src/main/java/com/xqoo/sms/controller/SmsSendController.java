package com.xqoo.sms.controller;

import com.xqoo.common.core.utils.StringUtils;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.feign.annotations.OperationLog;
import com.xqoo.feign.enums.operlog.OperationTypeEnum;
import com.xqoo.sms.config.SmsConfig;
import com.xqoo.sms.service.SendSmsService;
import com.xqoo.sms.vo.SendSmsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;

@RestController
@RequestMapping("sms")
@Api(tags = "短信发送控制器")
@Validated
public class SmsSendController {
    @Resource
    SendSmsService sendSmsService;
    @Resource
    RedisTemplate redisTemplate;

    @PostMapping("sendShortMessage")
    @ApiOperation(value = "发送短信")
    @OperationLog(tips = "发送短信新增发送记录", operatorType = OperationTypeEnum.ADD, isSaveRequestData = true)
    ResultEntity sendShortMessage(@RequestBody @Valid SendSmsVo sendSmsVo) {
        if (sendSmsVo.getPhoneNumbers().get(0).toString().length() != 11) {
            return new ResultEntity(METHOD_NOT_ALLOWED, "手机号码不正确");
        }
        String servicePlatformId = redisTemplate.opsForValue().get("servicePlatformId").toString();
        if (StringUtils.isEmpty(servicePlatformId)) {
            //重新加载配置进入redis
       //     smsConfig.initSmsRedis();
        }
        return sendSmsService.sendShortMessage(servicePlatformId, sendSmsVo);
    }

    @PostMapping("previewShortMessage")
    @ApiOperation(value = "预览短信")
    ResultEntity previewShortMessage(@RequestBody @Valid SendSmsVo sendSmsVo) {
        String servicePlatformId = redisTemplate.opsForValue().get("servicePlatformId").toString();
        if (StringUtils.isEmpty(servicePlatformId)) {
            //重新加载配置进入redis
         //   smsConfig.initSmsRedis();
        }
        return sendSmsService.previewShortMessage(servicePlatformId, sendSmsVo);
    }
}
