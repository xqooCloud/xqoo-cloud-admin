package com.xqoo.authorization.service.resource.impl;

import cn.hutool.core.collection.CollUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.xqoo.authorization.entity.SysConsoleMenuEntity;
import com.xqoo.authorization.service.SysConsoleMenuService;
import com.xqoo.authorization.service.resource.ResourceCenterService;
import com.xqoo.authorization.vo.ResourceMenuVO;
import com.xqoo.authorization.vo.SysConsoleMenuVO;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.core.utils.JacksonUtils;
import com.xqoo.common.core.utils.StringUtils;
import com.xqoo.common.entity.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.*;

@Service("resourceCenterService")
public class ResourceCenterServiceImpl implements ResourceCenterService {

    private final static Logger logger = LoggerFactory.getLogger(ResourceCenterServiceImpl.class);

    @Autowired
    private SysConsoleMenuService sysConsoleMenuService;

    @Override
    public JsonNode getUserMenuList(CurrentUser currentUser) {
        List<SysConsoleMenuEntity> allMenuList = sysConsoleMenuService.getAllActiveMenuList();
        List<SysConsoleMenuEntity> userMenuList;
        if(currentUser.getAdmin()){
            userMenuList = allMenuList;
        }else{
            userMenuList = handleFinalShowMenuList(sysConsoleMenuService.getRoleMenuListByRoleIds(currentUser.getRoleIds()), allMenuList);
        }
        if(CollUtil.isEmpty(userMenuList)){
            return JacksonUtils.transferToJsonNode(Collections.emptyList());
        }
        List<ResourceMenuVO> vo = sysConsoleMenuService.handleMenuTreeToVo(userMenuList, false, false,false);
        if(CollUtil.isEmpty(vo)){
            return JacksonUtils.transferToJsonNode(Collections.emptyList());
        }
        return JacksonUtils.transferToJsonNode(vo);
    }

    @Override
    public JsonNode getConsoleMenuInfo() {
        List<SysConsoleMenuEntity> menuList = sysConsoleMenuService.getAllActiveMenuList();
        List<ResourceMenuVO> vo = sysConsoleMenuService.handleMenuTreeToVo(menuList, true, false, false);
        if(CollUtil.isEmpty(vo)){
            return JacksonUtils.transferToJsonNode(Collections.emptyList());
        }
        return JacksonUtils.transferToJsonNode(vo);
    }

    @Override
    public ResultEntity<SysConsoleMenuVO> consoleMenuDetailInfo(Integer menuId, CurrentUser currentUser) {
        List<SysConsoleMenuEntity> allMenuList = sysConsoleMenuService.getAllActiveMenuList();
        List<SysConsoleMenuEntity> menuList;
        if(currentUser.getAdmin()){
            menuList = allMenuList;
        }else{
            menuList = handleFinalShowMenuList(sysConsoleMenuService.getRoleMenuListByRoleIds(currentUser.getRoleIds()), allMenuList);
        }
        if(CollUtil.isEmpty(menuList)){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "您没有此菜单权限，无法编辑");
        }
        Optional<SysConsoleMenuEntity> find = menuList.stream().filter(item -> menuId.equals(item.getId())).findFirst();
        final boolean[] success = {false};
        SysConsoleMenuVO vo = new SysConsoleMenuVO();
        find.ifPresent(x -> {
            success[0] = true;
            BeanUtils.copyProperties(x, vo);
            boolean hasChild = sysConsoleMenuService.checkHasChild(vo.getId());
            vo.setHasChild(hasChild);
            Map<String, String> formatPath = getParentPath(vo.getPath(), vo.getOutSideJump());
            vo.setParentPath(formatPath.get("parentPath"));
            vo.setPath(formatPath.get("path"));
            vo.setParentKeys(formatParentKeys(x.getParentKeys()));
            vo.setRedirectMenu(StringUtils.isNotEmpty(x.getRedirect()));
        });
        if(success[0]){
            return new ResultEntity<>(HttpStatus.OK, "ok", vo);
        }
        return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "您没有此菜单权限，无法编辑");
    }

    @Override
    public JsonNode getFactAndNoRedirectMenuDetailInfo(CurrentUser currentUser) {
        List<SysConsoleMenuEntity> menuList = sysConsoleMenuService.getAllActiveMenuList();
        List<ResourceMenuVO> vo = sysConsoleMenuService.handleMenuTreeToVo(menuList, true, true, true);
        if(CollUtil.isEmpty(vo)){
            return JacksonUtils.transferToJsonNode(Collections.emptyList());
        }
        return JacksonUtils.transferToJsonNode(vo);
    }

    // 处理最终返回的菜单结果
    private List<SysConsoleMenuEntity> handleFinalShowMenuList(List<SysConsoleMenuEntity> userList, List<SysConsoleMenuEntity> allList){
        List<SysConsoleMenuEntity> rtnList = new ArrayList<>();
        userList.forEach(item -> {
            rtnList.add(item);
            if(item.getParentId() == 0 || isExistNowList(userList, item.getParentId())){
                return;
            }
            rtnList.addAll(getAllParentMenuList(allList, item.getParentId()));
        });
        return rtnList;
    }

    // 查找已知存在的当前所有父级
    private List<SysConsoleMenuEntity> getAllParentMenuList(List<SysConsoleMenuEntity> allList, Integer parentId){
        if(parentId == 0){
            return Collections.emptyList();
        }
        List<SysConsoleMenuEntity> list = new ArrayList<>();
        Optional<SysConsoleMenuEntity> find = allList.stream().filter(item -> item.getId().equals(parentId)).findFirst();
        if(find.isPresent()){
            list.add(find.get());
            list.addAll(getAllParentMenuList(allList, find.get().getParentId()));
        }
        return list;
    }

    // 查看当前父级菜单是否已存在列表中
    private boolean isExistNowList(List<SysConsoleMenuEntity> userList, Integer parentId){
        Optional<SysConsoleMenuEntity> find = userList.stream().filter(item -> item.getId().equals(parentId)).findFirst();
        if(find.isPresent()){
            return true;
        }
        return false;
    }

    // 将parentKeys解析为list
    private List<String> formatParentKeys(String parentKeys){
        if(StringUtils.isEmpty(parentKeys)){
            return Collections.emptyList();
        }
        try {
            return JacksonUtils.toObj(parentKeys, new TypeReference<List<String>>() {
                @Override
                public Type getType() {
                    return super.getType();
                }
            });
        }catch (Exception e){
            logger.error("[资源中心]转换菜单父级高亮路由数组出错，原因:{},信息:{}", e.getClass().getSimpleName(), e.getMessage());
            return Collections.emptyList();
        }
    }

    // 拆分路径为自身路径和父级路径
    private Map<String, String> getParentPath(String path, Boolean isOutsideJump){
        Map<String, String> map = new HashMap<>(2);
        if(!isOutsideJump){
            map.put("path", "");
            map.put("parentPath", "/");
            if(StringUtils.isEmpty(path)){
                return map;
            }
            List<String> splitList = Splitter.on("/").trimResults().splitToList(path);
            if(CollUtil.isEmpty(splitList)){
                return map;
            }
            if(splitList.size() == 1){
                map.put("path", splitList.get(0));
                return map;
            }
            map.put("path", splitList.get(splitList.size() - 1));
            map.put("parentPath", Joiner.on("/").skipNulls().join(splitList.subList(0, splitList.size() - 1)) + "/");
            return map;
        }
        map.put("path", "");
        map.put("parentPath", "http://");
        if(StringUtils.isEmpty(path)){
            return map;
        }
        List<String> splitList = Splitter.on("//").trimResults().splitToList(path);
        if(CollUtil.isEmpty(splitList)){
            return map;
        }
        if(splitList.size() == 1){
            return map;
        }
        map.put("path", Joiner.on("//").skipNulls().join(splitList.subList(1, splitList.size())));
        map.put("parentPath", splitList.get(0) + "//");
        return map;
    }
}
