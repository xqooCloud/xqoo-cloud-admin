package com.xqoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@EnableFeignClients
public class ApplicationGateway {

    /**
     *  此处为方法实现路由配置，和配置文件中配置路由一致，默认情况下相同id会覆盖配置文件中的配置
     * */
    /*@Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes ()
                .route (r -> r.path ("/client/**")
                        .filters (f -> f.stripPrefix (1))
                        .uri ("https://www.baidu.com")
                        .id ("xqoo-client")
                )
                .build ();
    }*/

    public static void main(String[] args) {
        SpringApplication.run(ApplicationGateway.class, args);
    }
}
