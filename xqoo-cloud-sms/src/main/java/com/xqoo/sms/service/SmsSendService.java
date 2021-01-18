package com.xqoo.sms.service;

import com.xqoo.common.entity.ResultEntity;
import com.xqoo.sms.vo.SendSmsVo;

public interface SmsSendService {
    public String servicePlatformId();
    ResultEntity sendShortMessage( SendSmsVo sendSmsVo);
    ResultEntity previewShortMessage(SendSmsVo sendSmsVo);


}
