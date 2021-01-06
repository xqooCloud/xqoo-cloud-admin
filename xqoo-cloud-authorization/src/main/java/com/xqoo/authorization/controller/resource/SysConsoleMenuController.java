package com.xqoo.authorization.controller.resource;

import com.xqoo.authorization.service.SysConsoleMenuService;
import com.xqoo.authorization.vo.SysConsoleMenuVO;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.core.utils.StringUtils;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.feign.annotations.LoginUser;
import com.xqoo.feign.annotations.OperationLog;
import com.xqoo.feign.enums.operlog.OperationTypeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/sysMenu")
@Api(tags = "控制台菜单")
@Validated
public class SysConsoleMenuController {

    @Autowired
    private SysConsoleMenuService sysConsoleMenuService;

    @ApiOperation("新增/编辑菜单属性")
    @PostMapping("/updateMenu")
    @OperationLog(tips = "新增/编辑后台控制台菜单", operatorType = OperationTypeEnum.EDIT, isSaveRequestData = true)
    public ResultEntity updateMenu(@Valid @RequestBody SysConsoleMenuVO vo,
                                   @ApiIgnore @LoginUser CurrentUser currentUser){
        if(StringUtils.isEmpty(currentUser.getUserId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "丢失系统用户id，无法完成操作");
        }
        if(vo.getId() == null || vo.getId() == 0){
            return sysConsoleMenuService.addMenuHandle(vo);
        }
        return sysConsoleMenuService.updateMenuHandle(vo, currentUser);
    }

    @ApiOperation("删除菜单")
    @GetMapping("/removeMenu")
    @OperationLog(tips = "删除后台控制台菜单", operatorType = OperationTypeEnum.REMOVE)
    public ResultEntity removeMenu(@RequestParam(required = false, value = "id") @NotNull(message = "删除的菜单id不能为空") Integer id,
                                   @LoginUser CurrentUser currentUser){
        if(StringUtils.isEmpty(currentUser.getUserId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "丢失系统用户id，无法删除菜单");
        }
        return sysConsoleMenuService.removeMenuHandle(id, currentUser);
    }
}
