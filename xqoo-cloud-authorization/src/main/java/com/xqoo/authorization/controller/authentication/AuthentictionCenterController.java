package com.xqoo.authorization.controller.authentication;

import com.xqoo.authorization.service.authentication.AuthenticationDefaultService;
import com.xqoo.common.core.config.JWTUtils;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.core.utils.StringUtils;
import com.xqoo.common.dto.AuthenDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authen")
@Api(tags = "鉴权中心控制器(内部接口)")
@Validated
public class AuthentictionCenterController {

    @Autowired
    private AuthenticationDefaultService authentictionDefaultService;

    @Autowired
    private JWTUtils jwtUtils;

    @GetMapping("/isLogin")
    @ApiOperation(value = "查看是否已登录(内部接口)")
    public AuthenDTO LoginStatus(@RequestParam(required = false) String token){
        if(StringUtils.isEmpty(token)){
            AuthenDTO dto = new AuthenDTO();
            dto.setSuccess(false);
            dto.setMessage("token丢失，请退出后重新登录");
            dto.setStatus(HttpStatus.NOT_ACCEPTABLE);
        }
        return authentictionDefaultService.loginCheck(token);
    }

    @GetMapping("/getLoginUserInfo")
    @ApiOperation(value = "获取已登录用户基本信息(内部接口)")
    public CurrentUser getLoginUserInfo(@RequestParam(required = false, value = "token") String token){
        if(StringUtils.isEmpty(token)){
            return new CurrentUser();
        }
        CurrentUser currentUser = authentictionDefaultService.getUserBaseInfo(token);
        return currentUser;
    }
}
