package com.xqoo.gateway.config;

import com.xqoo.gateway.bean.ServerPortBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerPortConfig {

    @Bean
    public ServerPortBean serverPortBean() {
        return new ServerPortBean();
    }
}
