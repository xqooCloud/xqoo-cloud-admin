package com.xqoo.salecenter.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xqoo.salecenter.service.GoodsCommentService;
import com.xqoo.salecenter.entity.GoodsCommentEntity;
import com.xqoo.salecenter.mapper.GoodsCommentMapper;
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
 * 数据源表(goods_comment)表服务实现类
 *
 * @author xqoo-code-gen
 * @date 2021-02-01 14:46:30
 */
@Service("goodsCommentService")
public class GoodsCommentServiceImpl extends ServiceImpl<GoodsCommentMapper, GoodsCommentEntity> implements GoodsCommentService {

    private final static Logger logger = LoggerFactory.getLogger(GoodsCommentServiceImpl.class);


    @Autowired
    private GoodsCommentMapper goodsCommentMapper;

    @Override
    public ResultEntity<PageResponseBean<GoodsCommentEntity>> pageGetList(PageRequestBean page){
        LambdaQueryWrapper<GoodsCommentEntity> queryWrapper = new LambdaQueryWrapper<>();
        Integer count = goodsCommentMapper.selectCount(queryWrapper);
        PageResponseBean<GoodsCommentEntity> result = new PageResponseBean<>(page.getPage(), page.getPageSize(), count);
        if(count == null || count < 1){
            result.setContent(Collections.emptyList());
            return new ResultEntity<>(HttpStatus.OK, "ok", result);
        }
        PageHelper.startPage(page.getPage(), page.getPageSize());
        List<GoodsCommentEntity> list = goodsCommentMapper.selectList(queryWrapper);
        result.setContent(list);
        return new ResultEntity<>(HttpStatus.OK, "ok", result);
    }

    @Override
    public ResultEntity insertList(List<GoodsCommentEntity> list, CurrentUser currentUser){
        if(CollUtil.isEmpty(list)){
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        }
        list.forEach(item -> {
            item.setCreateBy(currentUser.getUserId());
            item.setCreateDate(new Date());
        });
        try{
            goodsCommentMapper.insertList(list);
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        }catch (RuntimeException e){
            logger.error("[com.xqoo.salecenter]数据库批量新增错误，错误原因：{}，错误信息：{}", e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "新增失败");
        }
    }

    @Override
    public GoodsCommentEntity getOneGoodsCommentEntityByPrimaryKey(String commentId){
        LambdaQueryWrapper<GoodsCommentEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(GoodsCommentEntity::getCommentId, commentId);
        GoodsCommentEntity entity = goodsCommentMapper.selectOne(queryWrapper);
        if(entity != null){
            return entity;
        }
        return new GoodsCommentEntity();
    }
}
