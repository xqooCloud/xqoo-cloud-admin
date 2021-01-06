package com.xqoo.authorization.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xqoo.authorization.entity.SysRoleApiEntity;
import com.xqoo.authorization.mapper.SysRoleApiMapper;
import com.xqoo.authorization.service.SysRoleApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色和api接口关联表(SysRoleApi)表服务实现类
 *
 * @author makejava
 * @since 2020-12-06 19:20:40
 */
@Service("sysRoleApiService")
public class SysRoleApiServiceImpl extends ServiceImpl<SysRoleApiMapper, SysRoleApiEntity> implements SysRoleApiService {

    @Autowired
    private SysRoleApiMapper sysRoleApiMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public boolean insertList(Integer roleId, List<Integer> apiIds, String userId) throws RuntimeException {
        if(CollUtil.isEmpty(apiIds)){
            return true;
        }
        List<SysRoleApiEntity> list = apiIds.stream().map(item -> {
            SysRoleApiEntity entity = new SysRoleApiEntity();
            entity.setRoleId(roleId);
            entity.setApiId(item);
            entity.setCreateBy(userId);
            entity.setCreateDate(new Date());
            return entity;
        }).collect(Collectors.toList());
        sysRoleApiMapper.insertList(list);
        return true;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void updateUserApiList(Integer roleId, List<Integer> apiIds, String userId) throws RuntimeException {
        LambdaQueryWrapper<SysRoleApiEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysRoleApiEntity::getRoleId, roleId);
        List<SysRoleApiEntity> entityList = sysRoleApiMapper.selectList(queryWrapper);
        List<Integer> entityApiIdList = new ArrayList<>();
        List<Integer> removeIdList = new ArrayList<>();
        // apiIds里不存在的原有数据，就是删除数据
        entityList.forEach(item -> {
            if(!apiIds.contains(item.getApiId())){
                removeIdList.add(item.getId());
            }
            entityApiIdList.add(item.getApiId());
        });
        // 原有数据里不存在的apiId,就是新增数据
        List<Integer> willAddList = apiIds.stream().filter(item -> !entityApiIdList.contains(item)).collect(Collectors.toList());
        // 重新赋值新增数据
        entityList = new ArrayList<>(willAddList.size());
        for (Integer item : willAddList) {
            SysRoleApiEntity entity = new SysRoleApiEntity();
            entity.setApiId(item);
            entity.setRoleId(roleId);
            entity.setCreateBy(userId);
            entity.setCreateDate(new Date());
            entityList.add(entity);
        }

        if(CollUtil.isNotEmpty(entityList)){
            sysRoleApiMapper.insertList(entityList);
        }
        if(CollUtil.isNotEmpty(removeIdList)){
            queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(SysRoleApiEntity::getId, removeIdList);
            sysRoleApiMapper.delete(queryWrapper);
        }
    }
}
