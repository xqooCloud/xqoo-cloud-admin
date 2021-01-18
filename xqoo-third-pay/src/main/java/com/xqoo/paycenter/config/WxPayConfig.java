package com.xqoo.paycenter.config;

import com.xqoo.paycenter.bean.WxPayPropertiesBean;
import com.xqoo.paycenter.service.PayConfigPropertiesService;
import com.xqoo.paycenter.utils.WxPayJSAPIUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Map;

@Configuration
public class WxPayConfig {

    private WxPayPropertiesBean wxPayPropertiesBean = new WxPayPropertiesBean();

    @Autowired
    private PayConfigPropertiesService payConfigPropertiesService;

    @PostConstruct
    public void InitWxPayConfig(){
        Map<String, String> wxConfigMap = payConfigPropertiesService.getWxConigInit("PROD");
        this.wxPayPropertiesBean.fromMap(wxConfigMap);
    }

    @Bean(name = "WxPayConfig")
    public WxPayPropertiesBean bean(){
        return wxPayPropertiesBean;
    }

    @Bean("wxPayJSAPIUtil")
    public WxPayJSAPIUtil wxPayJSAPIUtil(){
        WxPayJSAPIUtil wxPayJSAPIUtil = new WxPayJSAPIUtil();
        return wxPayJSAPIUtil;
    }
}
