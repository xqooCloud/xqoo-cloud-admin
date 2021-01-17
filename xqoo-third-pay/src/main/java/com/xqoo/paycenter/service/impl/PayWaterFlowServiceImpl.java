package com.xqoo.paycenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.xqoo.common.page.PageResponseBean;
import com.xqoo.paycenter.bo.PayWaterFlowQueryBO;
import com.xqoo.paycenter.entity.PayWaterFlowEntity;
import com.xqoo.paycenter.mapper.PayWaterFlowMapper;
import com.xqoo.paycenter.service.PayWaterFlowService;
import com.xqoo.paycenter.vo.PayWaterFlowVO;
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
 * 支付流水表(PayWaterFlow)表服务实现类
 *
 * @author makejava
 * @since 2020-03-16 16:45:47
 */
@Service("payWaterFlowService")
public class PayWaterFlowServiceImpl extends ServiceImpl<PayWaterFlowMapper, PayWaterFlowEntity> implements PayWaterFlowService {

    private final static Logger log = LoggerFactory.getLogger(PayWaterFlowServiceImpl.class);

    @Autowired
    private PayWaterFlowMapper payWaterFlowMapper;

    @Override
    public PageResponseBean<PayWaterFlowVO> queryPagePayWaterFlow(PayWaterFlowQueryBO queryBO) {
        LambdaQueryWrapper<PayWaterFlowEntity> queryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotEmpty(queryBO.getPayTransactionId())){
            queryWrapper.like(PayWaterFlowEntity::getPayTransactionId, queryBO.getPayTransactionId());
        }
        if(StringUtils.isNotEmpty(queryBO.getPayPlat())){
            queryWrapper.eq(PayWaterFlowEntity::getPayPlat, queryBO.getPayPlat());
        }
        if(StringUtils.isNotEmpty(queryBO.getPayDevice())){
            queryWrapper.eq(PayWaterFlowEntity::getPayDevice, queryBO.getPayDevice());
        }
        if(StringUtils.isNotEmpty(queryBO.getClientPayId())){
            queryWrapper.like(PayWaterFlowEntity::getClientPayId, queryBO.getClientPayId());
        }
        if(StringUtils.isNotEmpty(queryBO.getTransactionId())){
            queryWrapper.eq(PayWaterFlowEntity::getTransactionId, queryBO.getTransactionId());
        }
        if(queryBO.getRefundStatus() != null){
            queryWrapper.eq(PayWaterFlowEntity::getRefundStatus, queryBO.getRefundStatus());
        }
        if(queryBO.getPayStatus() != null){
            queryWrapper.eq(PayWaterFlowEntity::getPayStatus, queryBO.getPayStatus());
        }
        if(queryBO.getStartDate() != null){
            queryWrapper.ge(PayWaterFlowEntity::getCreateDate, queryBO.getStartDate());
        }
        if(queryBO.getEndDate() != null){
            queryWrapper.le(PayWaterFlowEntity::getCreateDate, queryBO.getEndDate());
        }
        int count = 0;
        count = payWaterFlowMapper.selectCount(queryWrapper);
        PageResponseBean<PayWaterFlowVO> result = new PageResponseBean<>(queryBO, count);
        if(count < 1){
            result.setContent(Collections.emptyList());
            return result;
        }
        queryWrapper.orderByDesc(PayWaterFlowEntity::getCreateDate);
        PageHelper.startPage(queryBO.getPage(), queryBO.getPageSize());
        List<PayWaterFlowEntity> list = payWaterFlowMapper.selectList(queryWrapper);
        List<PayWaterFlowVO> voList = new ArrayList<>(list.size());
        list.forEach(item -> {
            PayWaterFlowVO vo = new PayWaterFlowVO();
            BeanUtils.copyProperties(item, vo);
            voList.add(vo);
        });
        result.setContent(voList);
        return result;
    }

    @Override
    public List<PayWaterFlowVO> queryPayWaterFlow(PayWaterFlowQueryBO queryBO) {
        LambdaQueryWrapper<PayWaterFlowEntity> queryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotEmpty(queryBO.getPayTransactionId())){
            queryWrapper.like(PayWaterFlowEntity::getPayTransactionId, queryBO.getPayTransactionId());
        }
        if(StringUtils.isNotEmpty(queryBO.getPayPlat())){
            queryWrapper.eq(PayWaterFlowEntity::getPayPlat, queryBO.getPayPlat());
        }
        if(StringUtils.isNotEmpty(queryBO.getPayDevice())){
            queryWrapper.eq(PayWaterFlowEntity::getPayDevice, queryBO.getPayDevice());
        }
        if(StringUtils.isNotEmpty(queryBO.getClientPayId())){
            queryWrapper.like(PayWaterFlowEntity::getClientPayId, queryBO.getClientPayId());
        }
        if(StringUtils.isNotEmpty(queryBO.getTransactionId())){
            queryWrapper.eq(PayWaterFlowEntity::getTransactionId, queryBO.getTransactionId());
        }
        if(queryBO.getRefundStatus() != null){
            queryWrapper.eq(PayWaterFlowEntity::getRefundStatus, queryBO.getRefundStatus());
        }
        if(queryBO.getPayStatus() != null){
            queryWrapper.eq(PayWaterFlowEntity::getPayStatus, queryBO.getPayStatus());
        }
        if(queryBO.getStartDate() != null){
            queryWrapper.ge(PayWaterFlowEntity::getPayTime, queryBO.getStartDate());
        }
        if(queryBO.getEndDate() != null){
            queryWrapper.le(PayWaterFlowEntity::getPayTime, queryBO.getEndDate());
        }
        List<PayWaterFlowEntity> list = payWaterFlowMapper.selectList(queryWrapper);
        List<PayWaterFlowVO> voList = new ArrayList<>(list.size());
        list.forEach(item -> {
            PayWaterFlowVO vo = new PayWaterFlowVO();
            BeanUtils.copyProperties(item, vo);
            voList.add(vo);
        });
        return voList;
    }

    @Override
    public Boolean addPayRecord(PayWaterFlowEntity entity) {
        try {
            payWaterFlowMapper.insert(entity);
            return true;
        }catch (Exception e){
            log.info("[支付模块][error]插入支付记录时发生错误，错误信息：" + e.getMessage());
            return false;
        }
    }

    @Override
    public Boolean updatePayWaterFlowSingle(PayWaterFlowEntity entity) {
        LambdaQueryWrapper<PayWaterFlowEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PayWaterFlowEntity::getPayTransactionId
                , StringUtils.isNotEmpty(entity.getPayTransactionId()) ? entity.getPayTransactionId() : "none");
        entity.setPayTransactionId(null);
        try {
            payWaterFlowMapper.update(entity, queryWrapper);
            return true;
        }catch (Exception e){
            log.info("[支付模块][error]更新支付记录时发生错误，错误信息：" + e.getMessage());
            return false;
        }
    }
}
