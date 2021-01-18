package com.xqoo.annex.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.xqoo.annex.entity.FooterNavDetailEntity;
import com.xqoo.annex.mapper.FooterNavDetailMapper;
import com.xqoo.annex.service.FooterNavDetailService;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageRequestBean;
import com.xqoo.common.page.PageResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 数据源表(footer_nav_detail)表服务实现类
 *
 * @author xqoo-code-gen
 * @date 2021-01-14 15:29:04
 */
@Service("footerNavDetailService")
public class FooterNavDetailServiceImpl extends ServiceImpl<FooterNavDetailMapper, FooterNavDetailEntity> implements FooterNavDetailService {

    private final static Logger logger = LoggerFactory.getLogger(FooterNavDetailServiceImpl.class);


    @Autowired
    private FooterNavDetailMapper footerNavDetailMapper;

    @Override
    public ResultEntity<PageResponseBean<FooterNavDetailEntity>> pageGetList(PageRequestBean page){
        LambdaQueryWrapper<FooterNavDetailEntity> queryWrapper = new LambdaQueryWrapper<>();
        Integer count = footerNavDetailMapper.selectCount(queryWrapper);
        PageResponseBean<FooterNavDetailEntity> result = new PageResponseBean<>(page.getPage(), page.getPageSize(), count);
        if(count == null || count < 1){
            result.setContent(Collections.emptyList());
            return new ResultEntity<>(HttpStatus.OK, "ok", result);
        }
        PageHelper.startPage(page.getPage(), page.getPageSize());
        List<FooterNavDetailEntity> list = footerNavDetailMapper.selectList(queryWrapper);
        result.setContent(list);
        return new ResultEntity<>(HttpStatus.OK, "ok", result);
    }

    @Override
    public ResultEntity insertList(List<FooterNavDetailEntity> list, CurrentUser currentUser){
        if(CollUtil.isEmpty(list)){
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        }
        list.forEach(item -> {
            item.setCreateBy(currentUser.getUserId());
            item.setCreateDate(new Date());
        });
        try{
            footerNavDetailMapper.insertList(list);
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        }catch (RuntimeException e){
            logger.error("[com.xqoo.codegen]数据库批量新增错误，错误原因：{}，错误信息：{}", e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "新增失败");
        }
    }

    public FooterNavDetailEntity getOneFooterNavDetailEntityByPrimaryKey(Integer id){
        LambdaQueryWrapper<FooterNavDetailEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FooterNavDetailEntity::getId, id);
        FooterNavDetailEntity entity = footerNavDetailMapper.selectOne(queryWrapper);
        if(entity != null){
            return entity;
        }
        return new FooterNavDetailEntity();
    }
}

