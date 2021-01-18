package com.xqoo.paycenter.service.impl.goldcoin;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xqoo.paycenter.entity.goldcoin.CoinConfigEntity;
import com.xqoo.paycenter.mapper.goldcoin.CoinConfigMapper;
import com.xqoo.paycenter.service.goldcoin.CoinConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 内购配置表(CoinConfig)表服务实现类
 *
 * @author liangyongpeng
 * @since 2020-04-29 15:33:42
 */
@Service("coinConfigService")
public class CoinConfigServiceImpl extends ServiceImpl<CoinConfigMapper, CoinConfigEntity> implements CoinConfigService {

    @Autowired
    private CoinConfigMapper coinConfigMapper;

    /**
     * 根据id获取内购配置信息
     *
     * @param chargeId
     * @return
     */
    @Override
    public List<CoinConfigEntity> getCoinConfigList(String chargeId){

        if(StringUtils.isEmpty(chargeId)){
            throw new RuntimeException("内购配置信息的id不能为空!");
        }

        return coinConfigMapper.getCoinConfigs(chargeId);
    }
}
