package com.xqoo.annex.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.xqoo.annex.bo.QueryFooterNavGroupInfoBO;
import com.xqoo.annex.entity.FooterNavGroupEntity;
import com.xqoo.annex.mapper.FooterNavGroupMapper;
import com.xqoo.annex.service.FooterNavGroupService;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.core.utils.StringUtils;
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
 * 数据源表(footer_nav_group)表服务实现类
 *
 * @author xqoo-code-gen
 * @date 2021-01-14 15:42:57
 */
@Service("footerNavGroupService")
public class FooterNavGroupServiceImpl extends ServiceImpl<FooterNavGroupMapper, FooterNavGroupEntity> implements FooterNavGroupService {

    private final static Logger logger = LoggerFactory.getLogger(FooterNavGroupServiceImpl.class);


    @Autowired
    private FooterNavGroupMapper footerNavGroupMapper;

    @Override
    public ResultEntity<PageResponseBean<FooterNavGroupEntity>> pageGetList(QueryFooterNavGroupInfoBO page) {
        LambdaQueryWrapper<FooterNavGroupEntity> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(page.getGroupName())){
            queryWrapper.like(FooterNavGroupEntity::getGroupName, page.getGroupName());
        }
        Integer count = footerNavGroupMapper.selectCount(queryWrapper);
        PageResponseBean<FooterNavGroupEntity> result = new PageResponseBean<>(page.getPage(), page.getPageSize(), count);
        if (count == null || count < 1) {
            result.setContent(Collections.emptyList());
            return new ResultEntity<>(HttpStatus.OK, "ok", result);
        }
        PageHelper.startPage(page.getPage(), page.getPageSize());
        List<FooterNavGroupEntity> list = footerNavGroupMapper.selectList(queryWrapper);
        result.setContent(list);
        return new ResultEntity<>(HttpStatus.OK, "ok", result);
    }

    @Override
    public ResultEntity insertList(List<FooterNavGroupEntity> list, CurrentUser currentUser) {
        if (CollUtil.isEmpty(list)) {
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        }
        list.forEach(item -> {
            item.setCreateBy(currentUser.getUserId());
            item.setCreateDate(new Date());
        });
        try {
            footerNavGroupMapper.insertList(list);
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        } catch (RuntimeException e) {
            logger.error("[com.xqoo.codegen]数据库批量新增错误，错误原因：{}，错误信息：{}", e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "新增失败");
        }
    }

    @Override
    public ResultEntity insert(FooterNavGroupEntity footerNavGroupEntity) {

        boolean b = false;
        try {
            b = this.save(footerNavGroupEntity);
        } catch (Exception e) {
            logger.error("[com.xqoo.codegen]数据库新增错误，错误原因：{}，错误信息：{}", e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity(HttpStatus.NOT_ACCEPTABLE, "新增失败");
        }

        return new ResultEntity(true);


    }

    public FooterNavGroupEntity getOneFooterNavGroupEntityByPrimaryKey(Integer id) {
        LambdaQueryWrapper<FooterNavGroupEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(FooterNavGroupEntity::getId, id);
        FooterNavGroupEntity entity = footerNavGroupMapper.selectOne(queryWrapper);
        if (entity != null) {
            return entity;
        }
        return new FooterNavGroupEntity();
    }
}