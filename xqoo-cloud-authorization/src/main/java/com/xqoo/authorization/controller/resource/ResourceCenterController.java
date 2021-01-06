package com.xqoo.authorization.controller.resource;

import com.fasterxml.jackson.databind.JsonNode;
import com.xqoo.authorization.service.resource.ResourceCenterService;
import com.xqoo.authorization.vo.SysConsoleMenuVO;
import com.xqoo.common.core.config.propetes.xqoo.AuthorizationConfigProperties;
import com.xqoo.common.core.entity.CurrentUser;
import com.xqoo.common.core.utils.JacksonUtils;
import com.xqoo.common.core.utils.StringUtils;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.feign.annotations.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotNull;
import java.util.Collections;

@RestController
@RequestMapping("/resource")
@Api(tags = "资源中心")
@Validated
public class ResourceCenterController {

    @Autowired
    private ResourceCenterService resourceCenterService;

    @Autowired
    private AuthorizationConfigProperties authorizationConfigProperties;

    @ApiOperation("获取用户控制台菜单权限")
    @GetMapping("/consoleMenu")
    public ResultEntity<JsonNode> getConsoleMenu(@ApiIgnore @LoginUser CurrentUser currentUser){
        String userId = currentUser.getUserId();
        if(StringUtils.isEmpty(userId)){
            return new ResultEntity<>(HttpStatus.OK, "ok", JacksonUtils.transferToJsonNode(Collections.emptyList()));
        }
        return new ResultEntity<>(HttpStatus.OK, "", resourceCenterService.getUserMenuList(currentUser));
    }


    @ApiOperation("获取全部菜单，菜单信息编辑接口用")
    @GetMapping("/consoleMenuInfo")
    public ResultEntity<JsonNode> getConsoleMenuInfo(){
        return new ResultEntity<>(HttpStatus.OK, "", resourceCenterService.getConsoleMenuInfo());
    }

    @ApiOperation("获取菜单明细信息")
    @GetMapping("/consoleMenuDetailInfo")
    public ResultEntity<SysConsoleMenuVO> consoleMenuDetailInfo(@RequestParam(required = false, value = "menuId") @NotNull(message = "菜单id不能为空！") Integer menuId,
                                                                @ApiIgnore @LoginUser CurrentUser currentUser){
        String userId = currentUser.getUserId();
        if(StringUtils.isEmpty(userId)){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未查到登录的用户信息，无法获得明细信息");
        }
        return resourceCenterService.consoleMenuDetailInfo(menuId, currentUser);
    }

    @ApiOperation("获取非目录型并且不包含重定向的菜单路径")
    @GetMapping("/getFactAndNoRedirectMenuDetailInfo")
    public ResultEntity<JsonNode> getFactAndNoRedirectMenuDetailInfo(@ApiIgnore @LoginUser CurrentUser currentUser){
        String userId = currentUser.getUserId();
        if(StringUtils.isEmpty(userId)){
            return new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "未查到登录的用户信息，无法获得明细信息");
        }
        return new ResultEntity<>(HttpStatus.OK, "",
                resourceCenterService.getFactAndNoRedirectMenuDetailInfo(currentUser));
    }

    @ApiOperation("获取授权中心配置参数")
    @GetMapping("/getAuthorizationCenterConfigProperties")
    public ResultEntity<AuthorizationConfigProperties> getAuthorizationCenterConfigProperties(){
        AuthorizationConfigProperties authPro = new AuthorizationConfigProperties();
        BeanUtils.copyProperties(authorizationConfigProperties, authPro);
        authPro.setJwtSecretKey("*********");
        return new ResultEntity<>(HttpStatus.OK, "ok", authPro);
    }
}
