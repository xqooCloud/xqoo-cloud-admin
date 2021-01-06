package com.xqoo.gateway.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.xqoo.common.core.utils.JacksonUtils;
import com.xqoo.gateway.bean.GatewayRouteBO;
import com.xqoo.gateway.mapper.GatewayRouteMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author mumu
 * @create 2019/11/9 16:08
 * swagger 扫描配置
 */
@Component
@Primary
public class SwaggerProvider implements SwaggerResourcesProvider {

    private static final Logger log = LoggerFactory.getLogger(SwaggerProvider.class);
    public static final String API_URI = "/v2/api-docs";

    private final RouteLocator routeLocator;


    @Autowired
    private GatewayRouteMapper gatewayRouteMapper;

    public SwaggerProvider(RouteLocator routeLocator) {
        this.routeLocator = routeLocator;
    }

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        GatewayRouteBO find= new GatewayRouteBO();
        find.setServiceStatus(1);
        gatewayRouteMapper.queryRoutes(find).forEach(tmp-> {
            String swaggerUrl = null;
            List<Object> objects = JacksonUtils.toObj(tmp.getPredicates(), new TypeReference<List<Object>>() { });
            for (int i = 0; i < objects.size(); i++) {
                JsonNode object = JacksonUtils.transferToJsonNode(objects.get(i));
                if ("Path".equals(object.get("name").asText())) {
                    swaggerUrl =  JacksonUtils.transferToJsonNode(object.get("args"))
                            .get(NameUtils.GENERATED_NAME_PREFIX + "0").asText()
                            .replace("/**", API_URI);
                }
            }
            resources.add(swaggerResource(tmp.getServiceId(), swaggerUrl));
        });

        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }
}
