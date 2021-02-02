package com.xqoo.salecenter.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageRequestBean;
import com.xqoo.common.page.PageResponseBean;
import com.xqoo.salecenter.entity.GoodsPictureInfoEntity;
import com.xqoo.salecenter.mapper.GoodsPictureInfoMapper;
import com.xqoo.salecenter.service.GoodsPictureInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 数据源表(goods_picture_info)表服务实现类
 *
 * @author xqoo-code-gen
 * @date 2021-02-01 15:01:19
 */
@Service("goodsPictureInfoService")
public class GoodsPictureInfoServiceImpl extends ServiceImpl<GoodsPictureInfoMapper, GoodsPictureInfoEntity> implements GoodsPictureInfoService {

    private final static Logger logger = LoggerFactory.getLogger(GoodsPictureInfoServiceImpl.class);


    @Autowired
    private GoodsPictureInfoMapper goodsPictureInfoMapper;

    @Override
    public ResultEntity<PageResponseBean<GoodsPictureInfoEntity>> pageGetList(PageRequestBean page){
        LambdaQueryWrapper<GoodsPictureInfoEntity> queryWrapper = new LambdaQueryWrapper<>();
        Integer count = goodsPictureInfoMapper.selectCount(queryWrapper);
        PageResponseBean<GoodsPictureInfoEntity> result = new PageResponseBean<>(page.getPage(), page.getPageSize(), count);
        if(count == null || count < 1){
            result.setContent(Collections.emptyList());
            return new ResultEntity<>(HttpStatus.OK, "ok", result);
        }
        PageHelper.startPage(page.getPage(), page.getPageSize());
        List<GoodsPictureInfoEntity> list = goodsPictureInfoMapper.selectList(queryWrapper);
        result.setContent(list);
        return new ResultEntity<>(HttpStatus.OK, "ok", result);
    }

    @Override
    public ResultEntity insertList(List<GoodsPictureInfoEntity> list, CurrentUser currentUser){
        if(CollUtil.isEmpty(list)){
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        }
        try{
            goodsPictureInfoMapper.insertList(list);
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        }catch (RuntimeException e){
            logger.error("[com.xqoo.salecenter]数据库批量新增错误，错误原因：{}，错误信息：{}", e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "新增失败");
        }
    }

    @Override
    public GoodsPictureInfoEntity getOneGoodsPictureInfoEntityByPrimaryKey(Long id){
        LambdaQueryWrapper<GoodsPictureInfoEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(GoodsPictureInfoEntity::getId, id);
        GoodsPictureInfoEntity entity = goodsPictureInfoMapper.selectOne(queryWrapper);
        if(entity != null){
            return entity;
        }
        return new GoodsPictureInfoEntity();
    }
}
