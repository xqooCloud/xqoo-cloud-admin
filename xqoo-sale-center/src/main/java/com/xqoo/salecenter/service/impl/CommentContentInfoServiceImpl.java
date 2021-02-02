package com.xqoo.salecenter.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xqoo.salecenter.service.CommentContentInfoService;
import com.xqoo.salecenter.entity.CommentContentInfoEntity;
import com.xqoo.salecenter.mapper.CommentContentInfoMapper;
import com.github.pagehelper.PageHelper;
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
 * 数据源表(comment_conten_info)表服务实现类
 *
 * @author xqoo-code-gen
 * @date 2021-02-01 14:45:10
 */
@Service("commentContenInfoService")
public class CommentContentInfoServiceImpl extends ServiceImpl<CommentContentInfoMapper, CommentContentInfoEntity> implements CommentContentInfoService {

    private final static Logger logger = LoggerFactory.getLogger(CommentContentInfoServiceImpl.class);


    @Autowired
    private CommentContentInfoMapper commentContentInfoMapper;

    @Override
    public ResultEntity<PageResponseBean<CommentContentInfoEntity>> pageGetList(PageRequestBean page){
        LambdaQueryWrapper<CommentContentInfoEntity> queryWrapper = new LambdaQueryWrapper<>();
        Integer count = commentContentInfoMapper.selectCount(queryWrapper);
        PageResponseBean<CommentContentInfoEntity> result = new PageResponseBean<>(page.getPage(), page.getPageSize(), count);
        if(count == null || count < 1){
            result.setContent(Collections.emptyList());
            return new ResultEntity<>(HttpStatus.OK, "ok", result);
        }
        PageHelper.startPage(page.getPage(), page.getPageSize());
        List<CommentContentInfoEntity> list = commentContentInfoMapper.selectList(queryWrapper);
        result.setContent(list);
        return new ResultEntity<>(HttpStatus.OK, "ok", result);
    }

    @Override
    public ResultEntity insertList(List<CommentContentInfoEntity> list, CurrentUser currentUser){
        if(CollUtil.isEmpty(list)){
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        }
        list.forEach(item -> {
            item.setCreateBy(currentUser.getUserId());
            item.setCreateDate(new Date());
        });
        try{
            commentContentInfoMapper.insertList(list);
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        }catch (RuntimeException e){
            logger.error("[com.xqoo.salecenter]数据库批量新增错误，错误原因：{}，错误信息：{}", e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "新增失败");
        }
    }

    @Override
    public CommentContentInfoEntity getOneCommentContenInfoEntityByPrimaryKey(Long id){
        LambdaQueryWrapper<CommentContentInfoEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CommentContentInfoEntity::getId, id);
        CommentContentInfoEntity entity = commentContentInfoMapper.selectOne(queryWrapper);
        if(entity != null){
            return entity;
        }
        return new CommentContentInfoEntity();
    }
}
