package com.xqoo.authorization.controller.resource;

import com.xqoo.authorization.bo.AddUserInfoBO;
import com.xqoo.authorization.bo.QueryUserInfoBO;
import com.xqoo.authorization.service.SysUserService;
import com.xqoo.authorization.vo.UserInfoVO;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.core.utils.StringUtils;
import com.xqoo.common.entity.ResultEntity;
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

@RestController
@RequestMapping("/sysUser")
@Api(tags = "系统用户控制器")
@Validated
public class SysUserController {


    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/pageGetUserInfo")
    @ApiOperation("分页获取用户信息")
    public ResultEntity<PageResponseBean<UserInfoVO>> pageGetUserInfo(@RequestBody QueryUserInfoBO bo){

        return sysUserService.pageGetUserInfo(bo);
    }

    @PostMapping("/addUser")
    @ApiOperation("新增用户")
    @OperationLog(tips = "新增系统用户", operatorType = OperationTypeEnum.ADD, isSaveRequestData = true)
    public ResultEntity<String> addUserInfo(@RequestBody @Valid AddUserInfoBO bo){
        if(!bo.getConfirmPassword().equals(bo.getPassword())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "两次密码输入不符，请重新输入");
        }
        return sysUserService.addUserInfo(bo);
    }

    @GetMapping("/delUser")
    @ApiOperation("删除用户")
    @OperationLog(tips = "删除后台用户", operatorType = OperationTypeEnum.REMOVE, isSaveRequestData = true)
    public ResultEntity delUserInfo(@RequestParam(required = false, value = "userId") @NotBlank(message = "用户id不能为空") String userId,
                                    @ApiIgnore @LoginUser CurrentUser currentUser){
        if(StringUtils.isEmpty(currentUser.getUserId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到当前登录人信息，请重新登录重试");
        }
        return sysUserService.delUserInfo(userId);
    }

    @GetMapping("/updateUserStatus")
    @ApiOperation("更新用户状态")
    @OperationLog(tips = "改变用户账户状态", operatorType = OperationTypeEnum.EDIT, isSaveRequestData = true)
    public ResultEntity updateUserStatus(@RequestParam(required = false, value = "userId") @NotBlank(message = "用户id不能为空") String userId,
                                         @RequestParam(required = false, value = "type") @NotNull(message = "更新状态不能为空") String type,
                                         @ApiIgnore @LoginUser CurrentUser currentUser){
        if(StringUtils.isEmpty(currentUser.getUserId())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未找到当前登录人信息，请重新登录重试");
        }
        return sysUserService.updateUserStatus(userId, type);
    }
}
