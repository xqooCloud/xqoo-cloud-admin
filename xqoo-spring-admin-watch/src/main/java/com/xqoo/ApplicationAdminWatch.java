package com.xqoo;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAdminServer
@EnableFeignClients
public class ApplicationAdminWatch {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationAdminWatch.class, args);
    }
}
