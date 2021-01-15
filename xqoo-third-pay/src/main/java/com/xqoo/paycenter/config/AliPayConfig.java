package com.xqoo.paycenter.config;

import com.xqoo.paycenter.bean.AliPayPropertiesBean;
import com.xqoo.paycenter.service.PayConfigPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * 初始化支付宝支付参数Bean
 * @author GaoYang
 * @since 2020-03-14 17:31:25
 */
@Configuration
public class AliPayConfig {

    private AliPayPropertiesBean aliPayPropertiesBean = new AliPayPropertiesBean();

    @Autowired
    private PayConfigPropertiesService payConfigPropertiesService;

    @PostConstruct
    public void InitAliPayConfig(){
        Map<String, String> aliConfigMap = payConfigPropertiesService.getAliConigInit("PROD");
        aliPayPropertiesBean.fromMap(aliConfigMap);
    }

    @Bean(name = "AliPayConfig")
    public AliPayPropertiesBean bean(){
        return aliPayPropertiesBean;
    }
}
