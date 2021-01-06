package com.xqoo.authorization.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.xqoo.authorization.bo.QueryRoleBO;
import com.xqoo.authorization.entity.SysRoleEntity;
import com.xqoo.authorization.mapper.SysRoleMapper;
import com.xqoo.authorization.service.SysRoleApiService;
import com.xqoo.authorization.service.SysRoleMenuService;
import com.xqoo.authorization.service.SysRoleService;
import com.xqoo.authorization.service.SysUserRoleService;
import com.xqoo.authorization.vo.SysRoleDetailVO;
import com.xqoo.authorization.vo.SysRoleInfoVO;
import com.xqoo.authorization.vo.SysRolePageInfoVO;
import com.xqoo.common.constants.SqlQueryConstant;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.core.utils.StringUtils;
import com.xqoo.common.dto.SystemCommunicateDTO;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.enums.CommunicateStatusEnum;
import com.xqoo.common.page.PageResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 角色表(SysRole)表服务实现类
 *
 * @author makejava
 * @since 2020-12-06 19:20:40
 */
@Service("sysRoleService")
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRoleEntity> implements SysRoleService {

    private final static Logger logger = LoggerFactory.getLogger(SysRoleServiceImpl.class);

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysRoleApiService sysRoleApiService;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public ResultEntity<SysRolePageInfoVO> pageGetRoleInfo(QueryRoleBO bo, CurrentUser currentUser) {
        SysRolePageInfoVO vo = new SysRolePageInfoVO();
        vo.setRoleIds(currentUser.getRoleIds());
        // #需要改动 这里先默认都有权限，后面加入权限过滤器需要改写
        vo.setAuthRole(true);
        vo.setRoleNames(currentUser.getRoleNames());

        LambdaQueryWrapper<SysRoleEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysRoleEntity::getDelFlag, SqlQueryConstant.NOT_LOGIC_DEL);

        // 只差一条的走这边单独处理了
        if(bo.getRoleId() != null && bo.getRoleId() > 0){
            SysRoleEntity entity = sysRoleMapper.selectById(bo.getRoleId());
            int count = entity == null ? 0 : 1;
            PageResponseBean<SysRoleInfoVO> result = new PageResponseBean<>(bo.getPage(), bo.getPageSize(), count);
            if(entity == null){
                result.setContent(Collections.emptyList());
                vo.setPageRoleInfo(result);
                return new ResultEntity<>(HttpStatus.OK, "ok", vo);
            }
            SysRoleInfoVO voItem = new SysRoleInfoVO();
            voItem.setHasRole(false);
            voItem.setBindUseCount(sysUserRoleService.hasUserBindRole(entity.getId()));
            BeanUtils.copyProperties(entity, voItem);
            if(currentUser.getAdmin() || currentUser.getRoleIds().contains(entity.getId())){
                voItem.setHasRole(true);
            }
            result.setContent(new ArrayList<SysRoleInfoVO>(1){
                private static final long serialVersionUID = 6587670891645609020L;
                {
                    add(voItem);
                }
            });
            vo.setPageRoleInfo(result);
            return new ResultEntity<>(HttpStatus.OK, "ok", vo);

        }

        if(StringUtils.isNotEmpty(bo.getRoleKey())){
            queryWrapper.likeRight(SysRoleEntity::getRoleKey, bo.getRoleKey());
        }
        if(StringUtils.isNotEmpty(bo.getRoleName())){
            queryWrapper.like(SysRoleEntity::getRoleName, bo.getRoleName());
        }
        Integer count = sysRoleMapper.selectCount(queryWrapper);
        if(count == null){
            count = 0;
        }
        PageResponseBean<SysRoleInfoVO> result = new PageResponseBean<>(bo.getPage(), bo.getPageSize(), count);
        if(count < 1){
            result.setContent(Collections.emptyList());
            vo.setPageRoleInfo(result);
            return new ResultEntity<>(HttpStatus.OK, "ok", vo);
        }
        PageHelper.startPage(bo.getPage(), bo.getPageSize());
        List<SysRoleInfoVO> list = sysRoleMapper.getBindRoleUser(bo);

        list.forEach(item -> {
            item.setHasRole(false);
            if(currentUser.getAdmin() || currentUser.getRoleIds().contains(item.getId())){
                item.setHasRole(true);
            }
        });
        result.setContent(list);
        vo.setPageRoleInfo(result);
        return new ResultEntity<>(HttpStatus.OK, "ok", vo);
    }

    @Override
    public ResultEntity<SysRoleDetailVO> getRoleDetail(Integer roleId) {
        LambdaQueryWrapper<SysRoleEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysRoleEntity::getId, roleId);
        queryWrapper.eq(SysRoleEntity::getDelFlag, SqlQueryConstant.NOT_LOGIC_DEL);
        SysRoleEntity entity = sysRoleMapper.selectOne(queryWrapper);
        if(entity == null){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到相应角色信息");
        }
        SysRoleDetailVO vo = new SysRoleDetailVO();
        BeanUtils.copyProperties(entity, vo);
        List<Integer> roleMenuList = sysRoleMenuService.getRoleMenuIds(new ArrayList<Integer>(1){
            private static final long serialVersionUID = 2158811746592468030L;
            {
                add(roleId);
            }
        });
        vo.setRoleMenuList(roleMenuList);
        // #需要更改,此处暂时返空
        vo.setRoleApiList(Collections.emptyList());
        return new ResultEntity<>(HttpStatus.OK, "ok", vo);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public ResultEntity updateRoleInfo(SysRoleDetailVO vo, CurrentUser currentUser) {
        SysRoleEntity entity = new SysRoleEntity();
        boolean isAdd = vo.getId() == null || vo.getId() == 0;
        if(isAdd){
            LambdaQueryWrapper<SysRoleEntity> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(SysRoleEntity::getDelFlag, SqlQueryConstant.NOT_LOGIC_DEL);
            queryWrapper.eq(SysRoleEntity::getRoleKey, vo.getRoleKey());
            Integer count = sysRoleMapper.selectCount(queryWrapper);
            if(count != null && count > 0){
                return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "已存在标识为["
                        + vo.getRoleKey() + "]的数据，不允许重复插入");
            }
        }
        BeanUtils.copyProperties(vo, entity);
        SystemCommunicateDTO<Boolean> dto;
        if(isAdd){
            dto = addRoleInfo(entity, vo.getRoleMenuList(), vo.getRoleApiList(), currentUser.getUserId());
        }
        else{
            dto = editRoleInf(entity, vo.getRoleMenuList(), vo.getRoleApiList(), currentUser.getUserId());
        }
        if(CommunicateStatusEnum.FAIL.equals(dto.getStatus())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, dto.getMessage());
        }
        if(CommunicateStatusEnum.WARN.equals(dto.getStatus())){
            return new ResultEntity<>(HttpStatus.CREATED, dto.getMessage());
        }
        return new ResultEntity<>(HttpStatus.OK, "操作完成");
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public SystemCommunicateDTO<Boolean> addRoleInfo(SysRoleEntity entity, List<Integer> menuIdList,
                                                     List<Integer> apiIdList, String userId){
        try{
            sysRoleMapper.insert(entity);
            LambdaQueryWrapper<SysRoleEntity> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(SysRoleEntity::getRoleKey, entity.getRoleKey());
            entity = sysRoleMapper.selectOne(queryWrapper);
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.warn("[资源中心]新增角色发生错误，原因：{}，信息：{}",
                    e.getClass().getSimpleName(), e.getMessage());
            return new SystemCommunicateDTO<>(CommunicateStatusEnum.FAIL, "新增成功", true);
        }
        try{
            boolean menuSuccess = sysRoleMenuService.insertList(entity.getId(), menuIdList, userId);
            boolean apiSuccess = sysRoleApiService.insertList(entity.getId(), apiIdList, userId);
            if(menuSuccess && apiSuccess) {
                return new SystemCommunicateDTO<>(CommunicateStatusEnum.SUCCESS, "新增成功", true);
            }
            throw new RuntimeException("执行数据库操作错误");
        }catch (RuntimeException e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.warn("[资源中心]新增角色菜单权限和api权限发生错误，原因：{}，信息：{}",
                    e.getClass().getSimpleName(), e.getMessage());
            return new SystemCommunicateDTO<>(CommunicateStatusEnum.WARN, "角色信息已增加，但在配置菜单及api" +
                    "权限时发生错误，请重新处理此角色的权限信息", true);
        }
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public SystemCommunicateDTO<Boolean> editRoleInf(SysRoleEntity entity, List<Integer> menuIdList,
                                                     List<Integer> apiIdList, String userId){
        try{
            sysRoleMapper.updateById(entity);
            sysRoleMenuService.updateUserMenuList(entity.getId(), menuIdList, userId);
            sysRoleApiService.updateUserApiList(entity.getId(), apiIdList, userId);
            return new SystemCommunicateDTO<>(CommunicateStatusEnum.SUCCESS, "修改成功", true);
        }catch (RuntimeException e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.warn("[资源中心]新增角色菜单权限和api权限发生错误，原因：{}，信息：{}",
                    e.getClass().getSimpleName(), e.getMessage());
            return new SystemCommunicateDTO<>(CommunicateStatusEnum.FAIL, "修改角色信息失败，数据库处理出错", true);
        }
    }

    @Override
    public ResultEntity removeRoleInfo(Integer roleId, CurrentUser currentUser) {
        Integer hasUserBind = sysUserRoleService.hasUserBindRole(roleId);
        if(hasUserBind > 0){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "无法删除此角色，此角色仍有用户绑定，请先移除用户绑定关系后再删除角色");
        }
        SysRoleEntity entity = sysRoleMapper.selectById(roleId);
        if(entity == null){
            return new ResultEntity<>(HttpStatus.OK, "此角色信息已不复存在", roleId);
        }
        if(entity.getAdmin()){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "超级管理员角色无法删除");
        }
        entity.setDelFlag(SqlQueryConstant.LOGIC_DEL);
        try {
            sysRoleMapper.updateById(entity);
            return new ResultEntity<>(HttpStatus.OK, "删除成功", roleId);
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("[资源中心]角色删除失败，数据库操作发生错误，错误原因：{}，错误信息：{}", e.getClass().getSimpleName(), e.getMessage());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "删除角色发生错误，请稍后重试");
        }
    }

    @Override
    public Integer getUserNoRoleCount(String userId) {
        if(StringUtils.isEmpty(userId)){
            return 0;
        }
        return sysRoleMapper.queryUserNoRoleCount(userId);
    }

    @Override
    public List<SysRoleEntity> getUserNoRole(String userId, Integer page, Integer pageSize) {
        if(StringUtils.isEmpty(userId)){
            return Collections.emptyList();
        }
        if(page == null || pageSize == null){
            page = 1;
            pageSize = 10;
        }
        PageHelper.startPage(page, pageSize);
        return sysRoleMapper.queryUserNoRole(userId);
    }
}
