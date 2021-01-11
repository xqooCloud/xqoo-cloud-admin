package com.xqoo.sms.service.impl;

import com.xqoo.common.entity.ResultEntity;
import com.xqoo.sms.service.SmsSendService;
import com.xqoo.sms.vo.SendSmsVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ALiSmsService implements SmsSendService {
    @Override
    public String servicePlatformId() {
        return "1";
    }

    @Override
    public ResultEntity sendShortMessage(SendSmsVo sendSmsVo) {
        System.out.println("阿里 "+sendSmsVo.getSign());
        return null;
    }

    @Override
    public ResultEntity previewShortMessage(List phontNumber, SendSmsVo sendSmsVo) {
        return null;
    }
}
