package com.xqoo.device.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.core.utils.StringUtils;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageRequestBean;
import com.xqoo.common.page.PageResponseBean;
import com.xqoo.device.entity.ScreenPropertiesEntity;
import com.xqoo.device.mapper.ScreenPropertiesMapper;
import com.xqoo.device.service.ScreenPropertiesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 数据源表(screen_properties)表服务实现类
 *
 * @author xqoo-code-gen
 * @date 2021-01-28 15:12:35
 */
@Service("screenPropertiesService")
public class ScreenPropertiesServiceImpl extends ServiceImpl<ScreenPropertiesMapper, ScreenPropertiesEntity> implements ScreenPropertiesService {

    private final static Logger logger = LoggerFactory.getLogger(ScreenPropertiesServiceImpl.class);


    @Autowired
    private ScreenPropertiesMapper screenPropertiesMapper;

    @Override
    public ResultEntity<PageResponseBean<ScreenPropertiesEntity>> pageGetList(PageRequestBean page){
        LambdaQueryWrapper<ScreenPropertiesEntity> queryWrapper = new LambdaQueryWrapper<>();
        Integer count = screenPropertiesMapper.selectCount(queryWrapper);
        PageResponseBean<ScreenPropertiesEntity> result = new PageResponseBean<>(page.getPage(), page.getPageSize(), count);
        if(count == null || count < 1){
            result.setContent(Collections.emptyList());
            return new ResultEntity<>(HttpStatus.OK, "ok", result);
        }
        PageHelper.startPage(page.getPage(), page.getPageSize());
        List<ScreenPropertiesEntity> list = screenPropertiesMapper.selectList(queryWrapper);
        result.setContent(list);
        return new ResultEntity<>(HttpStatus.OK, "ok", result);
    }

    @Override
    public ResultEntity insertList(List<ScreenPropertiesEntity> list, CurrentUser currentUser){
        if(CollUtil.isEmpty(list)){
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        }
        try{
            screenPropertiesMapper.insertList(list);
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        }catch (RuntimeException e){
            logger.error("[com.xqoo.device]数据库批量新增错误，错误原因：{}，错误信息：{}", e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "新增失败");
        }
    }

    @Override
    public void removeList(List<Long> ids) throws Exception {
        if(CollUtil.isNotEmpty(ids)){
            screenPropertiesMapper.delete(new LambdaQueryWrapper<ScreenPropertiesEntity>().in(ScreenPropertiesEntity::getId, ids));
        }
    }

    @Override
    public void updateList(List<ScreenPropertiesEntity> list) throws Exception {
        list.forEach(item -> {
            screenPropertiesMapper.updateById(item);
        });
    }

    @Override
    public ScreenPropertiesEntity getOneScreenPropertiesEntityByPrimaryKey(Long id){
        LambdaQueryWrapper<ScreenPropertiesEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ScreenPropertiesEntity::getId, id);
        ScreenPropertiesEntity entity = screenPropertiesMapper.selectOne(queryWrapper);
        if(entity != null){
            return entity;
        }
        return new ScreenPropertiesEntity();
    }

    @Override
    public List<ScreenPropertiesEntity> getListByParentId(String parentId) {
        List<ScreenPropertiesEntity> list = screenPropertiesMapper.selectList(new LambdaQueryWrapper<ScreenPropertiesEntity>()
                .eq(ScreenPropertiesEntity::getParentId, parentId));
        if(CollUtil.isEmpty(list)){
            return Collections.emptyList();
        }
        return list;
    }

    @Override
    public List<Long> getIdsByParentId(String parentId) {
        if(StringUtils.isEmpty(parentId)){
            return Collections.emptyList();
        }
        List<Long> list = screenPropertiesMapper.getIdsByParentId(parentId);
        if(CollUtil.isEmpty(list)){
            return Collections.emptyList();
        }
        return list;
    }
}
