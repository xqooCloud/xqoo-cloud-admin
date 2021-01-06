package com.xqoo.gateway.config;


import com.xqoo.common.core.utils.JacksonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 *  将定义好的路由表信息通过此类读写到redis中
 */

@Component
public class RedisRouteDefinitionRepository implements RouteDefinitionRepository {

    private static final Logger log = LoggerFactory.getLogger(RedisRouteDefinitionRepository.class);

    public static final String  GATEWAY_ROUTES = "gateway:routes";

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        List<RouteDefinition> routeDefinitions = new ArrayList<>();
        try {
            redisTemplate.opsForHash().values(GATEWAY_ROUTES).forEach(routeDefinition -> {
                RouteDefinition routeDefinition1 = JacksonUtils.toObj(routeDefinition.toString(), RouteDefinition.class);
                routeDefinitions.add(routeDefinition1);
            });
            return Flux.fromIterable(routeDefinitions);
        }catch (Exception e){
            System.out.print(e.getMessage());
            return Flux.fromIterable(routeDefinitions);
        }

    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return route
                .flatMap(routeDefinition -> {
                    String routeDefinStr = JacksonUtils.toJson(routeDefinition);
                    redisTemplate.opsForHash().put(GATEWAY_ROUTES, routeDefinition.getId(),
                                routeDefinStr);

                    return Mono.empty();
                });
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return routeId.flatMap(id -> {
            if (redisTemplate.opsForHash().hasKey(GATEWAY_ROUTES, id)) {
                redisTemplate.opsForHash().delete(GATEWAY_ROUTES, id);
                return Mono.empty();
            }
            return Mono.defer(() -> Mono.error(new NotFoundException("路由文件没有找到: " + routeId)));
        });
    }
}
