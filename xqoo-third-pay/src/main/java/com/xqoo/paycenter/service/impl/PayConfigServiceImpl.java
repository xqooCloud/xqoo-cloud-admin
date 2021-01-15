package com.xqoo.paycenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xqoo.paycenter.bo.PayConfigQueryBO;
import com.xqoo.paycenter.entity.PayConfigEntity;
import com.xqoo.paycenter.mapper.PayConfigMapper;
import com.xqoo.paycenter.service.PayConfigService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 支付配置总表(PayConfig)表服务实现类
 *
 * @author makejava
 * @since 2020-03-14 16:01:08
 */
@Service("payConfigService")
public class PayConfigServiceImpl extends ServiceImpl<PayConfigMapper, PayConfigEntity> implements PayConfigService {

    @Autowired
    private PayConfigMapper payConfigMapper;

    @Override
    public List<PayConfigEntity> queryPayConfig(PayConfigQueryBO queryBO) {
        LambdaQueryWrapper<PayConfigEntity> queryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotEmpty(queryBO.getPayPlat())){
            queryWrapper.eq(PayConfigEntity::getPayPlat, queryBO.getPayPlat());
        }
        if(queryBO.getActiveState() != null){
            queryWrapper.eq(PayConfigEntity::getActiveState, queryBO.getActiveState());
        }
        if(queryBO.getConfigStatus() != null){
            queryWrapper.eq(PayConfigEntity::getConfigStatus, queryBO.getConfigStatus());
        }
        queryWrapper.orderByDesc(PayConfigEntity::getConfigVersion);
        List<PayConfigEntity> payConfigEntityList = payConfigMapper.selectList(queryWrapper);
        return payConfigEntityList;
    }

}
