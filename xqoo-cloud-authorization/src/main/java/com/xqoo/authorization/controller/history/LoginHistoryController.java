package com.xqoo.authorization.controller.history;

import com.xqoo.authorization.bo.QueryUserLoginHistoryBO;
import com.xqoo.authorization.service.SysUserLoginHistoryService;
import com.xqoo.authorization.vo.UserLoginHistoryVO;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageResponseBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "用户登录历史接口")
@RestController
@RequestMapping("/loginHistory")
public class LoginHistoryController {

    @Autowired
    private SysUserLoginHistoryService sysUserLoginHistoryService;

    @ApiOperation("获取用户登录历史记录")
    @PostMapping("/getLoginUserHistory")
    public ResultEntity<PageResponseBean<UserLoginHistoryVO>> getLoginUserHistory(@RequestBody QueryUserLoginHistoryBO bo){
        return sysUserLoginHistoryService.getLoginUserHistory(bo);
    }
}
