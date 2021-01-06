package com.xqoo.authorization.controller.authorization;

import cn.hutool.core.date.DateUtil;
import com.xqoo.authorization.bo.LoginModelBO;
import com.xqoo.authorization.entity.LoginReturnEntity;
import com.xqoo.common.enums.LoginSourceEnum;
import com.xqoo.common.enums.LoginTypeEnum;
import com.xqoo.authorization.service.authorization.LoginByPasswordService;
import com.xqoo.common.constants.HttpReqeustConstant;
import com.xqoo.common.constants.SystemPublicConstant;
import com.xqoo.common.core.utils.GetIPAddress;
import com.xqoo.common.core.utils.RequestEnvUtils;
import com.xqoo.common.core.utils.StringUtils;
import com.xqoo.common.entity.ResultEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.util.Map;

@RestController
@RequestMapping("/author")
@Api(tags = "授权中心验证控制器")
@Validated
public class AuthorizationCenterController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private LoginByPasswordService loginByPasswordService;

    @GetMapping("/loginByPwd")
    @ApiOperation(value = "登录名密码登录")
    public ResultEntity<LoginReturnEntity> loginByIdAndPassword(@NotBlank(message = "请填写登录账号") @RequestParam(required = false, value = "loginId") String loginId,
                                                     @NotBlank(message = "请填写密码") @RequestParam(required = false, value = "password") String password,
                                                     @NotBlank(message = "随机字符串丢失") @RequestParam(required = false, value = "randomStr") String randomStr,
                                                     @NotBlank(message = "登录来源丢失") @RequestParam(required = false, value = "loginSource") String loginSource,
                                                     @NotBlank(message = "登录类型丢失") @RequestParam(required = false, value = "loginType") String loginType,
                                                     @RequestParam(required = false, value = "errorCode") String errorCode){
        String ip = GetIPAddress.getIPAddress(request);
        String env = RequestEnvUtils.GetRequestEnv(request.getHeader(HttpReqeustConstant.HeaderConstant.USER_AGENT));
        Long loginTime = DateUtil.current();
        LoginModelBO bo = new LoginModelBO();
        bo.setLoginEnv(env);
        bo.setLoginCurrentTimes(loginTime);
        bo.setLoginId(loginId);
        bo.setPassword(password);
        bo.setRandomStr(randomStr);
        bo.setLoginIp(ip);
        bo.setErrCode(errorCode);
        LoginTypeEnum loginTypeEnum = LoginTypeEnum.getEnumsByKey(loginType);
        LoginSourceEnum loginSourceEnum = LoginSourceEnum.getEnumsByKey(loginSource);
        if(loginTypeEnum == null){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "登录类型不合法", new LoginReturnEntity());
        }
        if(loginSourceEnum == null){
            loginSourceEnum = LoginSourceEnum.UnKnow;
        }
        bo.setLoginSource(loginSourceEnum);
        bo.setLoginType(loginTypeEnum);
        return loginByPasswordService.getToken(bo);
    }

    @GetMapping("/loginOut")
    @ApiOperation(value = "登出接口")
    public ResultEntity<Map<String, Object>> loginOut(@RequestHeader(value = SystemPublicConstant.AUTH_HEADER_KEY_NAME, required = false) String token,
                                                      @RequestHeader(value = SystemPublicConstant.USER_LOGIN_SOURCE_HEADER_KEY_NAME, required = false) String loginSource){
        if(StringUtils.isEmpty(token)){
            return new ResultEntity<>(HttpStatus.OK, "登出成功");
        }
        LoginSourceEnum loginSourceEnum = LoginSourceEnum.getEnumsByKey(loginSource);
        if(loginSourceEnum == null){
            loginSourceEnum = LoginSourceEnum.UnKnow;
        }
        return loginByPasswordService.loginOut(token, loginSourceEnum);
    }

}
