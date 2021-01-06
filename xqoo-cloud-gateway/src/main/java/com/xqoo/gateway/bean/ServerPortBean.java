package com.xqoo.gateway.bean;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@RefreshScope
public class ServerPortBean {

    private String serverPort;

    @Value("${server.port}")
    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }

    public String getServerPort(){
        return this.serverPort;
    }
}
