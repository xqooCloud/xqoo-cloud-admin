package com.xqoo.authorization.controller.resource;

import cn.hutool.core.collection.CollUtil;
import com.xqoo.authorization.bo.AddUserRoleInfoBO;
import com.xqoo.authorization.constants.AuthorizationCenterConstant;
import com.xqoo.authorization.entity.SysRoleEntity;
import com.xqoo.authorization.service.SysUserRoleService;
import com.xqoo.authorization.vo.SysUserRoleDetailVO;
import com.xqoo.authorization.vo.UserHaveNotRoleVO;
import com.xqoo.authorization.vo.UserOnlinedVO;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.core.utils.StringUtils;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageRequestBean;
import com.xqoo.common.page.PageResponseBean;
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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/sysUserRole")
@Api(tags = "系统用户角色控制器")
@Validated
public class SysUserRoleController {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @GetMapping("/getUserRoleListByUserId")
    @ApiOperation("根据角色id获取该角色用户列表")
    public ResultEntity<SysUserRoleDetailVO> getUserRoleListByUserId(@RequestParam(required = false, value = "userId")
                                                  @NotNull(message = "用户ID不能为空") String userId){
        if(AuthorizationCenterConstant.SUPER_ADMIN_UID.equals(userId)){
            SysUserRoleDetailVO vo = new SysUserRoleDetailVO();
            UserOnlinedVO onlinedVO = new UserOnlinedVO();
            onlinedVO.setOnlined(false);
            onlinedVO.setOnlinedType(Collections.emptyList());
            vo.setOnlined(onlinedVO);
            vo.setAdmin(true);
            vo.setUserRoleList(Collections.emptyList());
            return new ResultEntity<>(HttpStatus.OK, "ok", vo);
        }
        return sysUserRoleService.getUserRoleDetailInfo(userId);
    }

    @PostMapping("/deleteUserRole")
    @ApiOperation("删除用户角色信息")
    @OperationLog(tips = "删除用户角色信息", operatorType = OperationTypeEnum.REMOVE, isSaveRequestData = true)
    public ResultEntity delUserRoleByList(@RequestBody List<Integer> userRoleList,
                                          @RequestParam(required = false, value = "userId") @NotBlank(message = "用户id不能为空") String userId,
                                          @ApiIgnore @LoginUser CurrentUser currentUser){
        if(StringUtils.isEmpty(currentUser.getUserId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到当前登录人信息，不允许操作");
        }
        if(CollUtil.isEmpty(userRoleList)){
            return new ResultEntity<>(HttpStatus.OK, "删除的内容为空，已确定操作");
        }
        return sysUserRoleService.delUserRoleByList(userRoleList, userId);
    }

    @PostMapping("/addUserRole")
    @ApiOperation("增加用户角色")
    @OperationLog(tips = "增加用户角色", operatorType = OperationTypeEnum.ADD, isSaveRequestData = true)
    public ResultEntity addUserRole(@RequestBody @Valid AddUserRoleInfoBO bo,
                                    @ApiIgnore @LoginUser CurrentUser currentUser){
        if(StringUtils.isEmpty(currentUser.getUserId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到当前登录人信息，不允许操作");
        }
        if(AuthorizationCenterConstant.SUPER_ADMIN_UID.equals(bo.getUserId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "超级管理员角色信息不允许编辑");
        }
        return sysUserRoleService.addListRole(bo, currentUser);
    }

    @PostMapping("/getUserNoRoles")
    @ApiOperation("获得用户没有的角色")
    public ResultEntity<UserHaveNotRoleVO> getUserNoRoles(@RequestParam(required = false, value = "userId")
                                                                                @NotBlank(message = "用户id不能为空") String userId,
                                                          @RequestBody PageRequestBean page,
                                                          @ApiIgnore @LoginUser CurrentUser currentUser){
        if(StringUtils.isEmpty(currentUser.getUserId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到当前登录人信息，不允许操作");
        }
        if(AuthorizationCenterConstant.SUPER_ADMIN_UID.equals(userId)){
            UserHaveNotRoleVO vo = new UserHaveNotRoleVO();
            vo.setAdmin(true);
            PageResponseBean<SysRoleEntity> result = new PageResponseBean<>(page.getPage(), page.getPageSize(), 0);
            result.setContent(Collections.emptyList());
            vo.setResult(result);
            return new ResultEntity<>(HttpStatus.OK, "超级管理员已拥有所有权限", vo);
        }
        return sysUserRoleService.getUserNoRoles(userId, page.getPage(), page.getPageSize());
    }
}
