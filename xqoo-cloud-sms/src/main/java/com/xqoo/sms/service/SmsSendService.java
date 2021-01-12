package com.xqoo.sms.service;

import com.xqoo.common.entity.ResultEntity;
import com.xqoo.sms.vo.SendSmsVo;

import java.util.List;

public interface SmsSendService {
    public String servicePlatformId();
    ResultEntity sendShortMessage( SendSmsVo sendSmsVo);
    ResultEntity previewShortMessage(List phontNumber, SendSmsVo sendSmsVo);


}
