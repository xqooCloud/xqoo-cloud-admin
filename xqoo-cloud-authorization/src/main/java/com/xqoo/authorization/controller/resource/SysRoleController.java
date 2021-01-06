package com.xqoo.authorization.controller.resource;


import com.xqoo.authorization.bo.QueryRoleBO;
import com.xqoo.authorization.service.SysRoleService;
import com.xqoo.authorization.vo.SysRoleDetailVO;
import com.xqoo.authorization.vo.SysRolePageInfoVO;
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
@RequestMapping("/sysRole")
@Api(tags = "系统角色控制器")
@Validated
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @PostMapping("/pageGetRoleInfo")
    @ApiOperation("分页获取系统角色信息")
    public ResultEntity<SysRolePageInfoVO> pageGetRoleInfo(@ApiIgnore @LoginUser CurrentUser currentUser,
                                                           @RequestBody QueryRoleBO bo){
        if(StringUtils.isEmpty(currentUser.getUserId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到当前登录人信息，请重新登录重试");
        }
        return sysRoleService.pageGetRoleInfo(bo, currentUser);
    }

    @GetMapping("/getRoleDetail")
    @ApiOperation("获取角色信息明细")
    public ResultEntity<SysRoleDetailVO> getRoleDetail(@RequestParam(required = false, value="roleId") @NotNull(message = "角色id不能为空") Integer roleId){
        return sysRoleService.getRoleDetail(roleId);
    }

    @PostMapping("/updateRoleInfo")
    @ApiOperation("更新，插入角色信息")
    @OperationLog(tips="插入,更新系统角色信息操作", operatorType =  OperationTypeEnum.EDIT, isSaveRequestData = true)
    public ResultEntity updateRoleInfo(@ApiIgnore @LoginUser CurrentUser currentUser,
                                       @RequestBody @Valid SysRoleDetailVO vo){
        if(StringUtils.isEmpty(currentUser.getUserId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到当前登录人信息，请重新登录重试");
        }
        return sysRoleService.updateRoleInfo(vo, currentUser);
    }

    @GetMapping("/removeRoleInfo")
    @ApiOperation("删除角色")
    @OperationLog(tips = "删除角色操作", operatorType = OperationTypeEnum.REMOVE, isSaveRequestData = true)
    public ResultEntity removeRoleInfo(@ApiIgnore @LoginUser CurrentUser currentUser,
                                       @RequestParam(required = false, value = "roleId") @NotNull(message = "角色id不能为空") Integer roleId){
        if(StringUtils.isEmpty(currentUser.getUserId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到当前登录人信息，请重新登录重试");
        }
        return sysRoleService.removeRoleInfo(roleId, currentUser);
    }
}
