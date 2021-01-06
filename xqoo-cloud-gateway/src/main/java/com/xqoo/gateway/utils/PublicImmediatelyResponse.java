package com.xqoo.gateway.utils;

import com.xqoo.common.core.utils.JacksonUtils;
import com.xqoo.common.entity.ResultEntity;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

public class PublicImmediatelyResponse {

    /**
     * 立即响应公共类
     * @param exchange
     * @param httpStatus
     * @param message
     * @param map
     * @return
     */
    public static Mono<Void> responseNow(ServerWebExchange exchange, HttpStatus httpStatus, String message, Map<String, Object> map){
        ServerHttpResponse originalResponse = exchange.getResponse();
        originalResponse.setStatusCode(HttpStatus.OK);
        if(httpStatus != null){
            originalResponse.setStatusCode(httpStatus);
        }
        originalResponse.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        byte[] response = JacksonUtils.toJsonBytes(new ResultEntity<>(httpStatus, message, map));
        DataBuffer buffer = originalResponse.bufferFactory().wrap(response);
        return originalResponse.writeWith(Flux.just(buffer));
    }
}
