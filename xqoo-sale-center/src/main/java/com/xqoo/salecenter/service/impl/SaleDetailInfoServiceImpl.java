package com.xqoo.salecenter.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xqoo.salecenter.service.SaleDetailInfoService;
import com.xqoo.salecenter.entity.SaleDetailInfoEntity;
import com.xqoo.salecenter.mapper.SaleDetailInfoMapper;
import com.github.pagehelper.PageHelper;
import com.xqoo.common.constants.SqlQueryConstant;
import com.xqoo.common.page.PageRequestBean;
import com.xqoo.common.page.PageResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.entity.ResultEntity;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 数据源表(sale_detail_info)表服务实现类
 *
 * @author xqoo-code-gen
 * @date 2021-02-01 15:07:13
 */
@Service("saleDetailInfoService")
public class SaleDetailInfoServiceImpl extends ServiceImpl<SaleDetailInfoMapper, SaleDetailInfoEntity> implements SaleDetailInfoService {

    private final static Logger logger = LoggerFactory.getLogger(SaleDetailInfoServiceImpl.class);


    @Autowired
    private SaleDetailInfoMapper saleDetailInfoMapper;

    @Override
    public ResultEntity<PageResponseBean<SaleDetailInfoEntity>> pageGetList(PageRequestBean page){
        LambdaQueryWrapper<SaleDetailInfoEntity> queryWrapper = new LambdaQueryWrapper<>();
        Integer count = saleDetailInfoMapper.selectCount(queryWrapper);
        PageResponseBean<SaleDetailInfoEntity> result = new PageResponseBean<>(page.getPage(), page.getPageSize(), count);
        if(count == null || count < 1){
            result.setContent(Collections.emptyList());
            return new ResultEntity<>(HttpStatus.OK, "ok", result);
        }
        PageHelper.startPage(page.getPage(), page.getPageSize());
        List<SaleDetailInfoEntity> list = saleDetailInfoMapper.selectList(queryWrapper);
        result.setContent(list);
        return new ResultEntity<>(HttpStatus.OK, "ok", result);
    }

    @Override
    public ResultEntity insertList(List<SaleDetailInfoEntity> list, CurrentUser currentUser){
        if(CollUtil.isEmpty(list)){
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        }
        try{
            saleDetailInfoMapper.insertList(list);
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        }catch (RuntimeException e){
            logger.error("[com.xqoo.salecenter]数据库批量新增错误，错误原因：{}，错误信息：{}", e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "新增失败");
        }
    }

    @Override
    public SaleDetailInfoEntity getOneSaleDetailInfoEntityByPrimaryKey(Long id){
        LambdaQueryWrapper<SaleDetailInfoEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SaleDetailInfoEntity::getId, id);
        SaleDetailInfoEntity entity = saleDetailInfoMapper.selectOne(queryWrapper);
        if(entity != null){
            return entity;
        }
        return new SaleDetailInfoEntity();
    }
}
