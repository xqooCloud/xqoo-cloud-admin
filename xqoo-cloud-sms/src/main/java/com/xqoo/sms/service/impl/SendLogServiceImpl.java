package com.xqoo.sms.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xqoo.sms.mapper.SendLogMapper;
import com.xqoo.sms.entity.SendLogEntity;
import com.xqoo.sms.service.SendLogService;
import org.springframework.stereotype.Service;

/**
 * (SendLog)表服务实现类
 *
 * @author makejava
 * @since 2021-01-12 11:50:26
 */
@Service("sendLogService")
public class SendLogServiceImpl extends ServiceImpl<SendLogMapper, SendLogEntity> implements SendLogService {

}