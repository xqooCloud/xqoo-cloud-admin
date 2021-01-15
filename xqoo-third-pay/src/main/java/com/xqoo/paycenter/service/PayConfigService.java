package com.xqoo.paycenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xqoo.paycenter.bo.PayConfigQueryBO;
import com.xqoo.paycenter.entity.PayConfigEntity;

import java.util.List;

/**
 * 支付配置总表(PayConfig)表服务接口
 *
 * @author makejava
 * @since 2020-03-14 16:01:08
 */
public interface PayConfigService extends IService<PayConfigEntity> {

    List<PayConfigEntity> queryPayConfig(PayConfigQueryBO queryBO);

}
