package com.xqoo.authorization.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xqoo.authorization.bo.AddUserRoleInfoBO;
import com.xqoo.authorization.bo.SysUserRoleInfoBO;
import com.xqoo.authorization.bo.SysUserRoleMapBO;
import com.xqoo.authorization.entity.SysRoleEntity;
import com.xqoo.authorization.entity.SysUserRoleEntity;
import com.xqoo.authorization.mapper.SysUserRoleMapper;
import com.xqoo.authorization.service.SysRoleService;
import com.xqoo.authorization.service.SysUserRoleService;
import com.xqoo.authorization.service.base.AuthorizationBaseService;
import com.xqoo.authorization.vo.SysUserRoleDetailVO;
import com.xqoo.authorization.vo.SysUserRoleInfoVO;
import com.xqoo.authorization.vo.UserHaveNotRoleVO;
import com.xqoo.authorization.vo.UserOnlinedVO;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.core.utils.StringUtils;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户角色表(SysUserRole)表服务实现类
 *
 * @author makejava
 * @since 2020-12-06 19:20:40
 */
@Service("sysUserRoleService")
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRoleEntity> implements SysUserRoleService {

    private final static Logger logger = LoggerFactory.getLogger(SysUserRoleServiceImpl.class);

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private AuthorizationBaseService authorizationBaseService;

    @Autowired
    private SysRoleService sysRoleService;

    @Override
    public SysUserRoleInfoBO getUserRoleMap(String userId) {
        SysUserRoleInfoBO bo = new SysUserRoleInfoBO();
        if(StringUtils.isEmpty(userId)){
            bo.setRoleIds(Collections.emptyList());
            bo.setRoleNames(Collections.emptyList());
            return bo;
        }
        List<SysUserRoleMapBO> listBo = sysUserRoleMapper.getUserRoleMap(userId);
        if(CollUtil.isEmpty(listBo)){
            bo.setRoleIds(Collections.emptyList());
            bo.setRoleNames(Collections.emptyList());
            return bo;
        }
        List<Integer> roleIds = new ArrayList<>(listBo.size());
        List<String> roleNames = new ArrayList<>(listBo.size());
        boolean[] isAdmin = {false};
        listBo.forEach(item -> {
            if(item.isAdmin()){
                isAdmin[0] = true;
            }
            roleIds.add(item.getRoleId());
            roleNames.add(item.getRoleName());
        });
        bo.setAdmin(isAdmin[0]);
        bo.setRoleIds(roleIds);
        bo.setRoleNames(roleNames);
        return bo;
    }

    @Override
    public Integer hasUserBindRole(Integer roleId) {
        LambdaQueryWrapper<SysUserRoleEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUserRoleEntity::getRoleId, roleId);
        Integer count = sysUserRoleMapper.selectCount(queryWrapper);
        if(count == null || count < 1){
            return 0;
        }
        return count;
    }

    @Override
    public ResultEntity<SysUserRoleDetailVO> getUserRoleDetailInfo(String userId) {
        UserOnlinedVO onlinedVO = authorizationBaseService.getUserOnlineInfo(userId);
        List<SysUserRoleInfoVO> roleList = sysUserRoleMapper.getUserRoleListInfo(userId);
        SysUserRoleDetailVO vo = new SysUserRoleDetailVO();
        vo.setAdmin(false);
        vo.setOnlined(onlinedVO);
        if(CollUtil.isEmpty(roleList)){
            vo.setUserRoleList(Collections.emptyList());
            return new ResultEntity<>(HttpStatus.OK, "ok", vo);
        }
        vo.setUserRoleList(roleList);
        return new ResultEntity<>(HttpStatus.OK, "ok", vo);
    }

    @Override
    public ResultEntity delUserRoleByList(List<Integer> list, String userId) {
        LambdaQueryWrapper<SysUserRoleEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(SysUserRoleEntity::getId, list);
        try {
            sysUserRoleMapper.delete(queryWrapper);
            boolean success = authorizationBaseService.removeUserAllTokenByUserId(userId);
            if(!success){
                logger.warn("[资源中心]用户id为[{}]删除角色后踢出token失败", userId);
            }
            return new ResultEntity<>(HttpStatus.OK, "删除成功");
        }catch (Exception e){
            logger.error("[资源中心]删除用户角色发生错误，原因:{},信息:{}", e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "删除失败");
        }
    }

    @Override
    public ResultEntity addListRole(AddUserRoleInfoBO bo, CurrentUser currentUser) {
        String operUserId = currentUser.getUserId();
        Date now = new Date();
        List<SysUserRoleEntity> entities = bo.getRoleId().stream().map(item -> {
            SysUserRoleEntity entity = new SysUserRoleEntity();
            entity.setCreateBy(operUserId);
            entity.setCreateDate(now);
            entity.setRoleId(item);
            entity.setUserId(bo.getUserId());
            return entity;
        }).collect(Collectors.toList());
        if(CollUtil.isEmpty(entities)){
            return new ResultEntity<>(HttpStatus.OK, "增加数据为空，操作完成");
        }
        try {
            sysUserRoleMapper.insertList(entities);
            return new ResultEntity<>(HttpStatus.OK, "新增完成");
        }catch (Exception e){
            logger.error("[资源中心]新增用户角色发生错误，原因{}，信息{}", e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "新增数据时失败，请稍后重试");
        }
    }

    @Override
    public ResultEntity<UserHaveNotRoleVO> getUserNoRoles(String userId, Integer page, Integer pageSize) {
        Integer count = sysRoleService.getUserNoRoleCount(userId);
        PageResponseBean<SysRoleEntity> pageResult = new PageResponseBean<>(page, pageSize, count);
        UserHaveNotRoleVO vo = new UserHaveNotRoleVO();
        vo.setAdmin(false);
        if(count < 1){
            pageResult.setContent(Collections.emptyList());
            vo.setResult(pageResult);
            return new ResultEntity<>(HttpStatus.OK, "ok", vo);
        }
        List<SysRoleEntity> list = sysRoleService.getUserNoRole(userId, page, pageSize);
        if(CollUtil.isEmpty(list)){
            pageResult.setContent(Collections.emptyList());
        }else{
            pageResult.setContent(list);
        }
        vo.setResult(pageResult);
        return new ResultEntity<>(HttpStatus.OK, "ok", vo);
    }
}
