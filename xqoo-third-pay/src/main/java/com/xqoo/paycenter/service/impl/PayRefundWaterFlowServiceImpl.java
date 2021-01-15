package com.xqoo.paycenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.xqoo.common.page.PageResponseBean;
import com.xqoo.paycenter.bo.PayRefundWaterFlowQueryBO;
import com.xqoo.paycenter.entity.PayRefundWaterFlowEntity;
import com.xqoo.paycenter.mapper.PayRefundWaterFlowMapper;
import com.xqoo.paycenter.service.PayRefundWaterFlowService;
import com.xqoo.paycenter.vo.PayRefundWaterFlowVO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 退款记录流水表(PayRefundWaterFlow)表服务实现类
 *
 * @author makejava
 * @since 2020-03-16 16:45:47
 */
@Service("payRefundWaterFlowService")
public class PayRefundWaterFlowServiceImpl extends ServiceImpl<PayRefundWaterFlowMapper, PayRefundWaterFlowEntity> implements PayRefundWaterFlowService {

    private final static Logger logger = LoggerFactory.getLogger(PayRefundWaterFlowServiceImpl.class);

    @Autowired
    private PayRefundWaterFlowMapper payRefundWaterFlowMapper;

    @Override
    public PageResponseBean<PayRefundWaterFlowVO> queryPageRefundWaterFlow(PayRefundWaterFlowQueryBO queryBO) {
        LambdaQueryWrapper<PayRefundWaterFlowEntity> queryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotEmpty(queryBO.getRefundCode())){
            queryWrapper.like(PayRefundWaterFlowEntity::getRefundCode, queryBO.getRefundCode());
        }
        if(StringUtils.isNotEmpty(queryBO.getRefundPlat())){
            queryWrapper.eq(PayRefundWaterFlowEntity::getRefundPlat, queryBO.getRefundPlat());
        }
        if(StringUtils.isNotEmpty(queryBO.getRefundUserId())){
            queryWrapper.eq(PayRefundWaterFlowEntity::getRefundUserId, queryBO.getRefundUserId());
        }
        if(StringUtils.isNotEmpty(queryBO.getPayTransactionId())){
            queryWrapper.like(PayRefundWaterFlowEntity::getPayTransactionId, queryBO.getPayTransactionId());
        }
        if(StringUtils.isNotEmpty(queryBO.getTradeId())){
            queryWrapper.eq(PayRefundWaterFlowEntity::getPayTransactionId, queryBO.getTradeId());
        }
        if(queryBO.getRefundStatus() != null){
            queryWrapper.eq(PayRefundWaterFlowEntity::getRefundStatus, queryBO.getRefundStatus());
        }
        if(queryBO.getStartDate() != null){
            queryWrapper.ge(PayRefundWaterFlowEntity::getRefundTime, queryBO.getStartDate());
        }
        if(queryBO.getEndDate() != null){
            queryWrapper.le(PayRefundWaterFlowEntity::getRefundTime, queryBO.getEndDate());
        }
        int count = 0;
        count = payRefundWaterFlowMapper.selectCount(queryWrapper);
        PageResponseBean<PayRefundWaterFlowVO> result = new PageResponseBean<>(queryBO, count);
        if(count < 1){
            result.setContent(Collections.emptyList());
            return result;
        }
        PageHelper.startPage(queryBO.getPage(), queryBO.getPageSize());
        List<PayRefundWaterFlowEntity> list = payRefundWaterFlowMapper.selectList(queryWrapper);
        List<PayRefundWaterFlowVO> voList = new ArrayList<>(list.size());
        list.forEach(item -> {
            PayRefundWaterFlowVO vo = new PayRefundWaterFlowVO();
            BeanUtils.copyProperties(item, vo);
            voList.add(vo);
        });
        result.setContent(voList);
        return result;
    }

    @Override
    public List<PayRefundWaterFlowVO> queryRefundWaterFlow(PayRefundWaterFlowQueryBO queryBO) {
        LambdaQueryWrapper<PayRefundWaterFlowEntity> queryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotEmpty(queryBO.getRefundCode())){
            queryWrapper.like(PayRefundWaterFlowEntity::getRefundCode, queryBO.getRefundCode());
        }
        if(StringUtils.isNotEmpty(queryBO.getRefundPlat())){
            queryWrapper.eq(PayRefundWaterFlowEntity::getRefundPlat, queryBO.getRefundPlat());
        }
        if(StringUtils.isNotEmpty(queryBO.getRefundUserId())){
            queryWrapper.eq(PayRefundWaterFlowEntity::getRefundUserId, queryBO.getRefundUserId());
        }
        if(StringUtils.isNotEmpty(queryBO.getPayTransactionId())){
            queryWrapper.like(PayRefundWaterFlowEntity::getPayTransactionId, queryBO.getPayTransactionId());
        }
        if(StringUtils.isNotEmpty(queryBO.getTradeId())){
            queryWrapper.eq(PayRefundWaterFlowEntity::getPayTransactionId, queryBO.getTradeId());
        }
        if(queryBO.getRefundStatus() != null){
            queryWrapper.eq(PayRefundWaterFlowEntity::getRefundStatus, queryBO.getRefundStatus());
        }
        if(queryBO.getStartDate() != null){
            queryWrapper.ge(PayRefundWaterFlowEntity::getRefundTime, queryBO.getStartDate());
        }
        if(queryBO.getEndDate() != null){
            queryWrapper.le(PayRefundWaterFlowEntity::getRefundTime, queryBO.getEndDate());
        }
        List<PayRefundWaterFlowEntity> list = payRefundWaterFlowMapper.selectList(queryWrapper);
        List<PayRefundWaterFlowVO> voList = new ArrayList<>(list.size());
        list.forEach(item -> {
            PayRefundWaterFlowVO vo = new PayRefundWaterFlowVO();
            BeanUtils.copyProperties(item, vo);
            voList.add(vo);
        });
        return voList;
    }

    @Override
    public Boolean addRefundRecord(PayRefundWaterFlowEntity entity){
        try {
            payRefundWaterFlowMapper.insert(entity);
            return true;
        }catch (Exception e){
            logger.info("[支付模块][error]插入付款记录时发生错误，错误信息：" + e.getMessage());
            return false;
        }
    }
}
