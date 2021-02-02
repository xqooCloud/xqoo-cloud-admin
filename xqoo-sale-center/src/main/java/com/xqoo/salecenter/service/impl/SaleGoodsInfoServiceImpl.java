package com.xqoo.salecenter.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.core.utils.StringUtils;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageResponseBean;
import com.xqoo.salecenter.entity.SaleGoodsInfoEntity;
import com.xqoo.salecenter.enums.SaleGoodsStatusEnum;
import com.xqoo.salecenter.mapper.SaleGoodsInfoMapper;
import com.xqoo.salecenter.pojo.SaleGoodsInfoQuery;
import com.xqoo.salecenter.service.SaleGoodsInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 数据源表(sale_goods_info)表服务实现类
 *
 * @author xqoo-code-gen
 * @date 2021-02-01 15:08:34
 */
@Service("saleGoodsInfoService")
public class SaleGoodsInfoServiceImpl extends ServiceImpl<SaleGoodsInfoMapper, SaleGoodsInfoEntity> implements SaleGoodsInfoService {

    private final static Logger logger = LoggerFactory.getLogger(SaleGoodsInfoServiceImpl.class);


    @Autowired
    private SaleGoodsInfoMapper saleGoodsInfoMapper;

    @Override
    public ResultEntity<PageResponseBean<SaleGoodsInfoEntity>> pageGetList(SaleGoodsInfoQuery query){
        LambdaQueryWrapper<SaleGoodsInfoEntity> queryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotEmpty(query.getGoodsId())){
            queryWrapper.likeRight(SaleGoodsInfoEntity::getGoodsId, query.getGoodsId());
        }
        if(StringUtils.isNotEmpty(query.getScreenId())){
            queryWrapper.likeRight(SaleGoodsInfoEntity::getScreenId, query.getScreenId());
        }
        if(StringUtils.isNotEmpty(query.getSaleTitle())){
            queryWrapper.like(SaleGoodsInfoEntity::getSaleTitle, query.getSaleTitle());
        }
        if(query.getStatus() != null){
            queryWrapper.eq(SaleGoodsInfoEntity::getStatus, query.getStatus());
        }
        Integer count = saleGoodsInfoMapper.selectCount(queryWrapper);
        PageResponseBean<SaleGoodsInfoEntity> result = new PageResponseBean<>(query.getPage(), query.getPageSize(), count);
        if(count == null || count < 1){
            result.setContent(Collections.emptyList());
            return new ResultEntity<>(HttpStatus.OK, "ok", result);
        }
        queryWrapper.orderByDesc(SaleGoodsInfoEntity::getCreateDate);
        PageHelper.startPage(query.getPage(), query.getPageSize());
        List<SaleGoodsInfoEntity> list = saleGoodsInfoMapper.selectList(queryWrapper);
        result.setContent(list);
        return new ResultEntity<>(HttpStatus.OK, "ok", result);
    }

    @Override
    public ResultEntity insertList(List<SaleGoodsInfoEntity> list, CurrentUser currentUser){
        if(CollUtil.isEmpty(list)){
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        }
        list.forEach(item -> {
            item.setCreateBy(currentUser.getUserId());
            item.setCreateDate(new Date());
        });
        try{
            saleGoodsInfoMapper.insertList(list);
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        }catch (RuntimeException e){
            logger.error("[com.xqoo.salecenter]数据库批量新增错误，错误原因：{}，错误信息：{}", e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "新增失败");
        }
    }

    @Override
    public ResultEntity<String> sendAudit(String goodsId) {
        SaleGoodsInfoEntity entity = saleGoodsInfoMapper.selectOne(new LambdaQueryWrapper<SaleGoodsInfoEntity>()
                .eq(SaleGoodsInfoEntity::getGoodsId, goodsId));
        if(entity == null || StringUtils.isEmpty(entity.getGoodsId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到相应商品信息");
        }
        if(!SaleGoodsStatusEnum.DRAFT.getKey().equals(entity.getStatus())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "商品状态不为草稿，无法提交审核");
        }
        entity.setStatus(SaleGoodsStatusEnum.AUDITING.getKey());
        try{
            saleGoodsInfoMapper.updateById(entity);
            return new ResultEntity<>(HttpStatus.OK, "提交审核成功");
        }catch(Exception e){
            logger.error("[销售中心]更改商品状态为 审核中 时发生错误，错误原因：{}，错误信息：{}", e.getClass().getSimpleName(),
                    e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "更新状态时发生错误，请稍后再试");
        }
    }

    @Override
    public ResultEntity<String> auditPass(String goodsId) {
        SaleGoodsInfoEntity entity = saleGoodsInfoMapper.selectOne(new LambdaQueryWrapper<SaleGoodsInfoEntity>()
                .eq(SaleGoodsInfoEntity::getGoodsId, goodsId));
        if(entity == null || StringUtils.isEmpty(entity.getGoodsId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到相应商品信息");
        }
        if(!SaleGoodsStatusEnum.AUDITING.getKey().equals(entity.getStatus())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "商品状态不为审核中，无法通过");
        }
        entity.setStatus(SaleGoodsStatusEnum.AUDITED.getKey());
        try{
            saleGoodsInfoMapper.updateById(entity);
            return new ResultEntity<>(HttpStatus.OK, "审核完成");
        }catch(Exception e){
            logger.error("[销售中心]更改商品状态为 审核通过 时发生错误，错误原因：{}，错误信息：{}", e.getClass().getSimpleName(),
                    e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "更新状态时发生错误，请稍后再试");
        }
    }

    @Override
    public ResultEntity<String> aboardGoods(String goodsId) {
        SaleGoodsInfoEntity entity = saleGoodsInfoMapper.selectOne(new LambdaQueryWrapper<SaleGoodsInfoEntity>()
                .eq(SaleGoodsInfoEntity::getGoodsId, goodsId));
        if(entity == null || StringUtils.isEmpty(entity.getGoodsId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到相应商品信息");
        }
        if(!SaleGoodsStatusEnum.DRAFT.getKey().equals(entity.getStatus())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "商品状态不为草稿，无法废除");
        }
        entity.setStatus(SaleGoodsStatusEnum.ABOARD.getKey());
        try{
            saleGoodsInfoMapper.updateById(entity);
            return new ResultEntity<>(HttpStatus.OK, "已废除商品");
        }catch(Exception e){
            logger.error("[销售中心]更改商品状态为 废除 时发生错误，错误原因：{}，错误信息：{}", e.getClass().getSimpleName(),
                    e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "更新状态时发生错误，请稍后再试");
        }
    }

    @Override
    public ResultEntity<String> publishGoods(List<String> goodsIds) {
        LambdaQueryWrapper<SaleGoodsInfoEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(SaleGoodsInfoEntity::getGoodsId, goodsIds);
        List<SaleGoodsInfoEntity> goodsList = saleGoodsInfoMapper.selectList(queryWrapper);
        for (SaleGoodsInfoEntity entity : goodsList) {
            if(SaleGoodsStatusEnum.AUDITED.getKey().equals(entity.getStatus())){
                continue;
            }
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "产品【" + entity.getGoodsId() + "】的状态不为审核通过，无法上架");
        }
        SaleGoodsInfoEntity updateEntity = new SaleGoodsInfoEntity();
        updateEntity.setStatus(SaleGoodsStatusEnum.PUBLISH.getKey());
        try{
            saleGoodsInfoMapper.update(updateEntity, queryWrapper);
            return new ResultEntity<>(HttpStatus.OK, "(" + goodsIds.size() + ")件商品已上架成功");
        }catch(Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("[销售中心]更改商品状态为 上架 时发生错误，错误原因：{}，错误信息：{}", e.getClass().getSimpleName(),
                    e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "上架商品时发生错误，请稍后再试");
        }
    }

    @Override
    public ResultEntity<String> undercarriageGoods(List<String> goodsIds) {
        LambdaQueryWrapper<SaleGoodsInfoEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(SaleGoodsInfoEntity::getGoodsId, goodsIds);
        List<SaleGoodsInfoEntity> goodsList = saleGoodsInfoMapper.selectList(queryWrapper);
        for (SaleGoodsInfoEntity entity : goodsList) {
            if(SaleGoodsStatusEnum.PUBLISH.getKey().equals(entity.getStatus())){
                continue;
            }
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "产品【" + entity.getGoodsId() + "】的状态不为上架，无法下架");
        }
        SaleGoodsInfoEntity updateEntity = new SaleGoodsInfoEntity();
        updateEntity.setStatus(SaleGoodsStatusEnum.DRAFT.getKey());
        try{
            saleGoodsInfoMapper.update(updateEntity, queryWrapper);
            return new ResultEntity<>(HttpStatus.OK, "(" + goodsIds.size() + ")件商品已下架");
        }catch(Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("[销售中心]更改商品状态为 下架 时发生错误，错误原因：{}，错误信息：{}", e.getClass().getSimpleName(),
                    e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "下架商品时发生错误，请稍后再试");
        }
    }

    @Override
    public SaleGoodsInfoEntity getOneSaleGoodsInfoEntityByPrimaryKey(String goodsId){
        LambdaQueryWrapper<SaleGoodsInfoEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SaleGoodsInfoEntity::getGoodsId, goodsId);
        SaleGoodsInfoEntity entity = saleGoodsInfoMapper.selectOne(queryWrapper);
        if(entity != null){
            return entity;
        }
        return new SaleGoodsInfoEntity();
    }
}
