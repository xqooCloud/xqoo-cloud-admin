package com.xqoo.sms.service;

import com.xqoo.common.entity.ResultEntity;
import com.xqoo.sms.vo.SendSmsVo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SendSmsService {
    Map<String,SmsSendService> smsSendServiceMap = new HashMap<>();
    // 构造函数，如果你是集合接口对象，那么久会把spring容器中所有关于该接口的子类，全部抓出来放入到集合中
    public SendSmsService(List<SmsSendService> smsSendServices){
        for (SmsSendService smsSendService : smsSendServices) {
            smsSendServiceMap.put(smsSendService.servicePlatformId(), smsSendService);
        }
    }

    public ResultEntity sendShortMessage(String servicePlatformId, SendSmsVo sendSmsVo){
        SmsSendService smsSendService = smsSendServiceMap.getOrDefault(servicePlatformId,null);
        if (smsSendService==null){
            return new ResultEntity(HttpStatus.METHOD_NOT_ALLOWED,"短信服务出错");
        }
        return smsSendService.sendShortMessage(sendSmsVo);
    }

    public ResultEntity previewShortMessage(String servicePlatformId, SendSmsVo sendSmsVo){
        SmsSendService smsSendService = smsSendServiceMap.getOrDefault(servicePlatformId,null);
        if (smsSendService==null){
            return new ResultEntity(HttpStatus.METHOD_NOT_ALLOWED,"短信服务出错");
        }
        return smsSendService.previewShortMessage(sendSmsVo);
    }
}
