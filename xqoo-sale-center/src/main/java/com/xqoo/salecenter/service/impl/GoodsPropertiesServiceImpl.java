package com.xqoo.salecenter.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xqoo.salecenter.service.GoodsPropertiesService;
import com.xqoo.salecenter.entity.GoodsPropertiesEntity;
import com.xqoo.salecenter.mapper.GoodsPropertiesMapper;
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
 * 数据源表(goods_properties)表服务实现类
 *
 * @author xqoo-code-gen
 * @date 2021-02-01 15:02:42
 */
@Service("goodsPropertiesService")
public class GoodsPropertiesServiceImpl extends ServiceImpl<GoodsPropertiesMapper, GoodsPropertiesEntity> implements GoodsPropertiesService {

    private final static Logger logger = LoggerFactory.getLogger(GoodsPropertiesServiceImpl.class);


    @Autowired
    private GoodsPropertiesMapper goodsPropertiesMapper;

    @Override
    public ResultEntity<PageResponseBean<GoodsPropertiesEntity>> pageGetList(PageRequestBean page){
        LambdaQueryWrapper<GoodsPropertiesEntity> queryWrapper = new LambdaQueryWrapper<>();
        Integer count = goodsPropertiesMapper.selectCount(queryWrapper);
        PageResponseBean<GoodsPropertiesEntity> result = new PageResponseBean<>(page.getPage(), page.getPageSize(), count);
        if(count == null || count < 1){
            result.setContent(Collections.emptyList());
            return new ResultEntity<>(HttpStatus.OK, "ok", result);
        }
        PageHelper.startPage(page.getPage(), page.getPageSize());
        List<GoodsPropertiesEntity> list = goodsPropertiesMapper.selectList(queryWrapper);
        result.setContent(list);
        return new ResultEntity<>(HttpStatus.OK, "ok", result);
    }

    @Override
    public ResultEntity insertList(List<GoodsPropertiesEntity> list, CurrentUser currentUser){
        if(CollUtil.isEmpty(list)){
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        }
        try{
            goodsPropertiesMapper.insertList(list);
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        }catch (RuntimeException e){
            logger.error("[com.xqoo.salecenter]数据库批量新增错误，错误原因：{}，错误信息：{}", e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "新增失败");
        }
    }

    @Override
    public GoodsPropertiesEntity getOneGoodsPropertiesEntityByPrimaryKey(Long id){
        LambdaQueryWrapper<GoodsPropertiesEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(GoodsPropertiesEntity::getId, id);
        GoodsPropertiesEntity entity = goodsPropertiesMapper.selectOne(queryWrapper);
        if(entity != null){
            return entity;
        }
        return new GoodsPropertiesEntity();
    }
}
