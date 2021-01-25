package com.xqoo.authorization.controller.register;


import com.xqoo.authorization.bo.NormalRegisterBO;
import com.xqoo.authorization.service.register.RegisterHandleService;
import com.xqoo.common.constants.SystemPublicConstant;
import com.xqoo.common.core.utils.StringVerifUtil;
import com.xqoo.common.entity.ResultEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * @author gaoyang
 */
@Api(tags = "注册控制器")
@RestController
@RequestMapping("/register")
@Validated
public class RegisterHandleController {

    @Autowired
    private RegisterHandleService registerHandleService;

    @ApiOperation("获得注册短信验证码")
    @GetMapping("/getRegisterPhoneCode")
    public ResultEntity<String> getRegisterPhoneCode(@RequestParam(required = false, value = "phonenumber") @NotBlank(message = "手机号不能为空") String phonenumber,
                                                     @RequestParam(required = false, value = "tmpCode") @NotBlank(message = "验证码秘钥不能为空") String tmpCode,
                                                     @ApiIgnore @RequestHeader(SystemPublicConstant.REMOTE_REQUEST_IP) String requestId){
        if(!StringVerifUtil.isPhoneNumber(phonenumber)){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "手机号格式不正确");
        }
        return registerHandleService.getRegisterPhoneCode(phonenumber, tmpCode, requestId);
    }

    @ApiOperation("普通账号密码注册")
    @PostMapping("/normalRegister")
    public ResultEntity<String> normalRegister(@RequestBody @Valid NormalRegisterBO bo){
        if(!bo.getPassword().equals(bo.getConfirmPassword())){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "两次密码不一致，请重新输入");
        }
        return registerHandleService.normalRegister(bo);
    }
}
