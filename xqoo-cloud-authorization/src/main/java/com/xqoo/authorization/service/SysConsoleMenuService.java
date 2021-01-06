package com.xqoo.authorization.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xqoo.authorization.entity.SysConsoleMenuEntity;
import com.xqoo.authorization.vo.ResourceMenuVO;
import com.xqoo.authorization.vo.SysConsoleMenuVO;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.entity.ResultEntity;

import java.util.List;

/**
 * 管理控制台菜单表(SysConsoleMenu)表服务接口
 *
 * @author makejava
 * @since 2020-12-06 11:50:06
 */
public interface SysConsoleMenuService extends IService<SysConsoleMenuEntity> {


    /**
     * 获取全部可用的菜单
     * @return
     */
    List<SysConsoleMenuEntity> getAllActiveMenuList();

    /**
     * 根据角色获取可用的菜单
     * @param roleIds
     * @return
     */
    List<SysConsoleMenuEntity> getRoleMenuListByRoleIds(List<Integer> roleIds);

    /**
     * 处理菜单数据转换为树形结构
     * @param menuList
     * @param simpleInfo
     * @param skipParent
     * @param skipRedirect
     * @return
     */
    List<ResourceMenuVO> handleMenuTreeToVo(List<SysConsoleMenuEntity> menuList, boolean simpleInfo, boolean skipParent, boolean skipRedirect);

    /**
     * 验证此菜单是否用子级
     * @param id
     * @return
     */
    boolean checkHasChild(Integer id);

    /**
     * 增加菜单数据
     * @param vo
     * @return
     */
    ResultEntity addMenuHandle(SysConsoleMenuVO vo);

    /**
     * 修改菜单数据
     * @param vo
     * @return
     */
    ResultEntity updateMenuHandle(SysConsoleMenuVO vo, CurrentUser currentUser);

    /**
     * 删除菜单
     */
    ResultEntity removeMenuHandle(Integer menuId, CurrentUser currentUser);

}
