package com.xqoo.paycenter.service.goldcoin;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xqoo.paycenter.entity.goldcoin.CoinConfigEntity;

import java.util.List;

/**
 * 内购配置表(CoinConfig)表服务接口
 *
 * @author liangyongpeng
 * @since 2020-04-29 15:33:42
 */
public interface CoinConfigService extends IService<CoinConfigEntity> {
    /**
     * 根据id获取内购配置信息
     *
             * @param chargeId
     * @return
             */
    List<CoinConfigEntity> getCoinConfigList(String chargeId);
}
