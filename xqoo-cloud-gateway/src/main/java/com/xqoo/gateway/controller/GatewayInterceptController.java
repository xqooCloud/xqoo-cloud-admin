package com.xqoo.gateway.controller;

import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.page.PageResponseBean;
import com.xqoo.gateway.bean.GatewayInterceptQueryBO;
import com.xqoo.gateway.service.SysGatewayInterceptLogService;
import com.xqoo.gateway.vo.GatewayInterceptVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gatewayIntercept")
public class GatewayInterceptController {

    @Autowired
    private SysGatewayInterceptLogService sysGatewayInterceptLogService;

    @PostMapping("/getInterceptLogPageRecord")
    public ResultEntity<PageResponseBean<GatewayInterceptVO>> getInterceptResultPage(@RequestBody GatewayInterceptQueryBO bo){
        return sysGatewayInterceptLogService.getInterceptLogPageRecord(bo);
    }

}
