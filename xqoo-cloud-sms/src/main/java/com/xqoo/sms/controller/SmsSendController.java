package com.xqoo.sms.controller;

import com.xqoo.common.core.utils.JacksonUtils;
import com.xqoo.common.core.utils.StringUtils;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.feign.annotations.OperationLog;
import com.xqoo.feign.enums.operlog.OperationTypeEnum;
import com.xqoo.sms.service.SendSmsService;
import com.xqoo.sms.service.SmsSendService;
import com.xqoo.sms.vo.SendSmsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        if (sendSmsVo.getPhoneNumbers().get(0).toString().length()!=11){
            return new ResultEntity( METHOD_NOT_ALLOWED,"手机号码不正确");
        }
        String servicePlatformId = redisTemplate.opsForValue().get("servicePlatformId").toString();
        return sendSmsService.sendShortMessage(servicePlatformId,sendSmsVo);
    }
    @GetMapping("previewShortMessage")
    @ApiOperation(value = "预览短信")
    ResultEntity previewShortMessage(@NotBlank(message = "必须需要签名") @RequestParam(required = true, value = "sign") String sign,
                                     @NotBlank(message = "必须需要模板") @RequestParam(required = true, value = "templateCode") String templateCode,
                                     @RequestParam(required = false, value = "smsParamJson") String smsParamJson) {
        return null;
    }
}
