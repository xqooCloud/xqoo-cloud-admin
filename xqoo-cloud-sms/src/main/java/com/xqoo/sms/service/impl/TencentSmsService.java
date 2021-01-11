package com.xqoo.sms.service.impl;

import com.xqoo.common.entity.ResultEntity;
import com.xqoo.sms.service.SmsSendService;
import com.xqoo.sms.vo.SendSmsVo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TencentSmsService implements SmsSendService {
    @Override
    public String servicePlatformId() {
        return "2";
    }

    @Override
    public ResultEntity sendShortMessage(SendSmsVo sendSmsVo) {
        System.out.println("腾讯sms: "+sendSmsVo.getSign());
        return null;
    }

    @Override
    public ResultEntity previewShortMessage(List phontNumber, SendSmsVo sendSmsVo) {
        return null;
    }
}
