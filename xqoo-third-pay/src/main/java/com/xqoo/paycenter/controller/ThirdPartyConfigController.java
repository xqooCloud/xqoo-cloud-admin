package com.xqoo.paycenter.controller;

import com.xqoo.common.entity.ResultEntity;
import com.xqoo.paycenter.bean.WxPayPropertiesBean;
import com.xqoo.paycenter.vo.WeixinConfigPropertiesVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/thirdPartyConfigProperties")
@Api(tags = "第三方公开参数获取")
public class ThirdPartyConfigController {

    @Autowired
    private WxPayPropertiesBean wxPayPropertiesBean;

    @ApiOperation("微信相关公开参数")
    @GetMapping("/weixinConfig")
    public ResultEntity<WeixinConfigPropertiesVO> weixinConfig(){
        WeixinConfigPropertiesVO vo = new WeixinConfigPropertiesVO();
        vo.setAppId(wxPayPropertiesBean.getAppId());
        vo.setAppAppId(wxPayPropertiesBean.getAppAppId());
        vo.setOaAppId(wxPayPropertiesBean.getOaAppId());
        vo.setSpAppId(wxPayPropertiesBean.getSpAppId());
        return new ResultEntity<>(HttpStatus.OK, "成功", vo);
    }
}
