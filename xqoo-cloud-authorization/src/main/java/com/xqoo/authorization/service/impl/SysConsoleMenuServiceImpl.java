package com.xqoo.authorization.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.collect.Ordering;
import com.xqoo.authorization.entity.SysConsoleMenuEntity;
import com.xqoo.authorization.mapper.SysConsoleMenuMapper;
import com.xqoo.authorization.service.SysConsoleMenuService;
import com.xqoo.authorization.service.SysRoleMenuService;
import com.xqoo.authorization.vo.ResourceMenuVO;
import com.xqoo.authorization.vo.SysConsoleMenuVO;
import com.xqoo.common.constants.SqlQueryConstant;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.core.utils.JacksonUtils;
import com.xqoo.common.core.utils.StringUtils;
import com.xqoo.common.dto.SystemCommunicateDTO;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.enums.CommunicateStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 管理控制台菜单表(SysConsoleMenu)表服务实现类
 *
 * @author makejava
 * @since 2020-12-06 11:50:06
 */
@Service("sysConsoleMenuService")
public class SysConsoleMenuServiceImpl extends ServiceImpl<SysConsoleMenuMapper, SysConsoleMenuEntity> implements SysConsoleMenuService {

    private final static Logger logger = LoggerFactory.getLogger(SysConsoleMenuServiceImpl.class);

    @Autowired
    private SysConsoleMenuMapper sysConsoleMenuMapper;

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Override
    public List<SysConsoleMenuEntity> getAllActiveMenuList() {
        LambdaQueryWrapper<SysConsoleMenuEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysConsoleMenuEntity::getDelFlag, SqlQueryConstant.NOT_LOGIC_DEL);
        List<SysConsoleMenuEntity> list = sysConsoleMenuMapper.selectList(queryWrapper);
        if(CollUtil.isEmpty(list)){
            return Collections.emptyList();
        }
        return list;
    }

    @Override
    public List<SysConsoleMenuEntity> getRoleMenuListByRoleIds(List<Integer> roleIds) {
        List<Integer> menuIds = sysRoleMenuService.getRoleMenuIds(roleIds);
        if(CollUtil.isEmpty(menuIds)){
            return Collections.emptyList();
        }
        LambdaQueryWrapper<SysConsoleMenuEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(SysConsoleMenuEntity::getId, menuIds);
        queryWrapper.eq(SysConsoleMenuEntity::getDelFlag, SqlQueryConstant.NOT_LOGIC_DEL);
        List<SysConsoleMenuEntity> list = sysConsoleMenuMapper.selectList(queryWrapper);
        if(CollUtil.isEmpty(list)){
            return Collections.emptyList();
        }
        return list;
    }

    @Override
    public List<ResourceMenuVO> handleMenuTreeToVo(List<SysConsoleMenuEntity> menuList, boolean simpleInfo, boolean skipParent, boolean skipRedirect) {
        if(CollUtil.isEmpty(menuList)){
            return Collections.emptyList();
        }
        List<ResourceMenuVO> voList;
        if(!skipParent){
            voList = this.getMenuTreeToVo(menuList, SqlQueryConstant.NOT_LOGIC_DEL, simpleInfo, skipRedirect);
        }else{
            voList = this.getEachSingleMenuInfo(menuList, simpleInfo, skipParent, skipRedirect);
        }
        if(voList == null){
            return Collections.emptyList();
        }
        return voList;
    }

    @Override
    public boolean checkHasChild(Integer id) {
        LambdaQueryWrapper<SysConsoleMenuEntity> queryWrapper = new LambdaQueryWrapper<>();
        if(id == null){
            return false;
        }
        queryWrapper.eq(SysConsoleMenuEntity::getDelFlag, SqlQueryConstant.NOT_LOGIC_DEL);
        queryWrapper.eq(SysConsoleMenuEntity::getParentId, id);
        Integer count = sysConsoleMenuMapper.selectCount(queryWrapper);
        if(count == null || count < 1){
            return false;
        }
        return true;
    }

    @Override
    public ResultEntity addMenuHandle(SysConsoleMenuVO vo) {
        SystemCommunicateDTO<SysConsoleMenuEntity> finalData = handleVoDataToEntity(vo);
        if(CommunicateStatusEnum.FAIL.equals(finalData.getStatus())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, finalData.getMessage());
        }
        try{
            sysConsoleMenuMapper.insert(finalData.getResult());
            return new ResultEntity<>(HttpStatus.OK, "新增成功");
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("[资源中心]新增后台管理菜单出错，错误信息：{},原因：{}", e.getMessage(), e.getClass().getSimpleName());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "数据写入时发生错误，请稍后重试");
        }
    }

    @Override
    public ResultEntity updateMenuHandle(SysConsoleMenuVO vo,  CurrentUser currentUser) {
        SysConsoleMenuEntity entity = isAuthHandleMenu(vo.getId(), currentUser);
        if(entity == null){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "您没有此菜单的权限，无法编辑");
        }
        if(vo.getParentId().equals(vo.getId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "不能将菜单本身设为菜单的父级");
        }
        SystemCommunicateDTO<SysConsoleMenuEntity> finalData = handleVoDataToEntity(vo);
        if(CommunicateStatusEnum.FAIL.equals(finalData.getStatus())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, finalData.getMessage());
        }
        try{
            sysConsoleMenuMapper.updateById(finalData.getResult());
            return new ResultEntity<>(HttpStatus.OK, "修改成功", entity.getId());
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("[资源中心]新增后台管理菜单出错，错误信息：{},原因：{}", e.getMessage(), e.getClass().getSimpleName());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "数据写入时发生错误，请稍后重试");
        }
    }

    @Override
    public ResultEntity removeMenuHandle(Integer menuId, CurrentUser currentUser) {
        SysConsoleMenuEntity entity = isAuthHandleMenu(menuId, currentUser);
        if(entity == null){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "您没有此菜单的权限，无法删除");
        }
        LambdaQueryWrapper<SysConsoleMenuEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysConsoleMenuEntity::getDelFlag, SqlQueryConstant.NOT_LOGIC_DEL);
        queryWrapper.eq(SysConsoleMenuEntity::getParentId, menuId);
        Integer count = sysConsoleMenuMapper.selectCount(queryWrapper);
        if(count != null && count > 0){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "当前菜单还有子级菜单，无法删除");
        }
        entity.setDelFlag(SqlQueryConstant.LOGIC_DEL);
        entity.setId(menuId);
        try{
            sysConsoleMenuMapper.updateById(entity);
            return new ResultEntity<>(HttpStatus.OK, "修改成功");
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.error("[资源中心]新增后台管理菜单出错，错误信息：{},原因：{}", e.getMessage(), e.getClass().getSimpleName());
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "数据写入时发生错误，请稍后重试");
        }
    }

    private SysConsoleMenuEntity isAuthHandleMenu(Integer menuId, CurrentUser currentUser){
        List<SysConsoleMenuEntity> userMenuList;
        if(currentUser.getAdmin()){
            userMenuList = getAllActiveMenuList();
        }else{
            userMenuList = getRoleMenuListByRoleIds(currentUser.getRoleIds());
        }
        Optional<SysConsoleMenuEntity> find = userMenuList.stream().filter(item -> item.getId().equals(menuId)).findFirst();
        if(find.isPresent()){
            return find.get();
        }
        return null;
    }

    private SystemCommunicateDTO<SysConsoleMenuEntity> handleVoDataToEntity(SysConsoleMenuVO vo){
        SysConsoleMenuEntity entity = new SysConsoleMenuEntity();
        BeanUtils.copyProperties(vo, entity);
        if(CollUtil.isNotEmpty(vo.getParentKeys())){
            try{
                entity.setParentKeys(JacksonUtils.toJsonStr(vo.getParentKeys()));
            }catch (Exception e){
                return new SystemCommunicateDTO<>(CommunicateStatusEnum.FAIL, "转换高亮父级菜单错误，请重新选择重试");
            }
        }else{
            entity.setParentKeys("[]");
        }

        entity.setPath(vo.getParentPath() + vo.getPath());
        return new SystemCommunicateDTO<>(CommunicateStatusEnum.SUCCESS, "成功", entity);
    }

    private List<ResourceMenuVO> getEachSingleMenuInfo(List<SysConsoleMenuEntity> menuList, boolean simpleInfo,
                                                       boolean skipParent, boolean skipRedirect){
        return menuList.stream().map(item -> {
            ResourceMenuVO vo = new ResourceMenuVO();
            if(StringUtils.isNotEmpty(item.getRedirect()) && skipRedirect){
                return null;
            }
            if(skipParent){
                Optional<SysConsoleMenuEntity> find = menuList.stream().filter(filterItem -> item.getId()
                        .equals(filterItem.getParentId())).findFirst();
                if(find.isPresent()){
                    return null;
                }
            }
            vo.setPath(item.getPath());
            if(StringUtils.isNotEmpty(item.getComponent())){
                vo.setComponent(item.getComponent());
            }
            if(StringUtils.isNotEmpty(item.getName())){
                vo.setName(item.getName());
                vo.setTitle(item.getName());
            }
            if(StringUtils.isNotEmpty(item.getIcon())){
                vo.setIcon(item.getIcon());
            }
            if(simpleInfo){
                vo.setDefaultFlag(item.getDefaultFlag());
                vo.setKey(item.getId());
                vo.setValue(item.getId());
                vo.setHideInMenu(item.getHideInMenu());
                return vo;
            }
            BeanUtils.copyProperties(item, vo);
            return vo;
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }

    // 获取树状菜单
    private List<ResourceMenuVO> getMenuTreeToVo(List<SysConsoleMenuEntity> menuList, Integer parentId,
                                                 boolean simpleInfo, boolean skipRedirect){
        List<SysConsoleMenuEntity> children = menuList.stream().filter(filterItem -> parentId.equals(filterItem.getParentId()))
                .collect(Collectors.toList());
        if(CollUtil.isEmpty(children)){
            return null;
        }
        // 排序
        children = new Ordering<SysConsoleMenuEntity>() {
            @Override
            public int compare(SysConsoleMenuEntity left, SysConsoleMenuEntity right) {
                if(left.getSortNo() > right.getSortNo()){
                    return 1;
                }else if(left.getSortNo().equals(right.getSortNo())){
                    return 0;
                }else{
                    return -1;
                }
            }
        }.immutableSortedCopy(children);

        return children.stream().map(item -> {
            ResourceMenuVO vo = new ResourceMenuVO();
            // 这项是必须参数，其余参数可变配置
            vo.setPath(item.getPath());
            if(StringUtils.isNotEmpty(item.getComponent())){
                vo.setComponent(item.getComponent());
            }
            if(StringUtils.isNotEmpty(item.getName())){
                vo.setTitle(item.getName());
                vo.setName(item.getName());
            }
            if(StringUtils.isNotEmpty(item.getIcon())){
                vo.setIcon(item.getIcon());
            }
            // 当排除重定向路由时，直接跳出
            if(StringUtils.isNotEmpty(item.getRedirect()) && skipRedirect){
                return null;
            }
            List<ResourceMenuVO> childrenList = this.getMenuTreeToVo(menuList, item.getId(), simpleInfo, skipRedirect);
            if(CollUtil.isNotEmpty(childrenList)){
                vo.setChildren(childrenList);
            }
            // 当只需要简要信息时，直接返回
            if(simpleInfo){
                vo.setDefaultFlag(item.getDefaultFlag());
                vo.setKey(item.getId());
                vo.setHideInMenu(item.getHideInMenu());
                vo.setValue(item.getId());
                return vo;
            }
            if(StringUtils.isNotEmpty(item.getTarget())){
                vo.setTarget(item.getTarget());
            }
            // 如果是外部跳转连接，则后续属性统统无效
            if(item.getOutSideJump()){
                return vo;
            }
            // 隐藏菜单显示，只在true时才传
            if(item.getHideInMenu()){
                vo.setHideInMenu(true);
            }
            // 如果是跳转路径，忽略下方所有属性， 移除组件指向
            if(StringUtils.isNotEmpty(item.getRedirect())){
                vo.setComponent(null);
                vo.setRedirect(item.getRedirect());
                return vo;
            }
            // 如果有子路径，下方属性统统不配置，由子路径自行配置，否则容易继承造成配置与实际不符
//            if(CollUtil.isNotEmpty(childrenList)){
//                voList.add(vo);
//                return;
//            }

//            固定页头属性为全局setting，官网虽说支持菜单局部变动，但是测试后发现一旦刷新将会被默认属性覆盖，不可恢复，意义不大
            vo.setFixedHeader(item.getFixedHeader());
            vo.setFixSiderbar(item.getFixSiderbar());

            // 没设默认值的参数只有当false时才传给前端，否则前端渲染会报错

            if(StringUtils.isNotEmpty(item.getLayout())){
                vo.setLayout(item.getLayout());
            }
            if(!item.getMenuRender()){
                vo.setMenuRender(false);
            }
            if(!item.getHeaderRender()){
                vo.setHeaderRender(false);
            }
            if(!item.getFooterRender()){
                vo.setFooterRender(false);
            }
            if(!item.getMenuHeaderRender()){
                vo.setMenuHeaderRender(false);
            }
            if(StringUtils.isNotEmpty(item.getNavTheme())){
                vo.setNavTheme(item.getNavTheme());
            }
            if(StringUtils.isNotEmpty(item.getHeaderTheme())){
                vo.setHeaderTheme(item.getHeaderTheme());
            }
            if(StringUtils.isNotEmpty(item.getParentKeys())){
                try {
                    List<String> parentKeys = JacksonUtils.toObj(item.getParentKeys(), new TypeReference<List<String>>() {});
                    vo.setParentKeys(parentKeys);
                }catch (Exception e){
                    logger.error("[资源中心]转换菜单的高亮父级菜单时发生错误，原因：{},信息：{}", e.getClass().getSimpleName(), e.getMessage());
                }
            }
            return vo;
        }).filter(Objects::nonNull).collect(Collectors.toList());
    }
}
