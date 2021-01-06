package com.xqoo.gateway.fitter;


import com.xqoo.common.core.utils.JacksonUtils;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.gateway.bean.GatewayConfigProperties;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.*;

@Component
public class GatewayResponseFilter implements GlobalFilter, Ordered {

    private static final Logger log = LoggerFactory.getLogger(GatewayResponseFilter.class);

    private static final List<Integer> SUCCESS_STATUS = new ArrayList<>(Arrays.asList(200, 201, 202, 203, 204, 205, 206, 207, 208, 226));

    @Autowired
    private GatewayConfigProperties gatewayConfigProperties;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpResponse originalResponse = exchange.getResponse();
        DataBufferFactory bufferFactory = originalResponse.bufferFactory();
        ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
            @Override
            public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                final HttpStatus statusCode = originalResponse.getStatusCode() == null ? HttpStatus.INTERNAL_SERVER_ERROR : originalResponse.getStatusCode();
                // 200系列的返回直接放行
                if(SUCCESS_STATUS.contains(statusCode.value())){
                    return super.writeWith(body);
                }
                // 被系统定义了的错误直接放行
                if(isExistsCustomStatus(statusCode.value())){
                    return super.writeWith(body);
                }
                if (body instanceof Flux) {
                    Flux<? extends DataBuffer> fluxBody = (Flux<? extends DataBuffer>) body;
                    return super.writeWith(fluxBody.map(dataBuffer -> {
                        // probably should reuse buffers
                        byte[] content = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(content);
                        //释放掉内存
                        DataBufferUtils.release(dataBuffer);
                        String s = new String(content, StandardCharsets.UTF_8);
                        //TODO，s就是response的值，想修改、查看就随意而为了
                        log.error("[网关]请求路径[{}]发生了异常返回，返回值为{}", exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR), s);
                        byte[] uppedContent = JacksonUtils.toJsonBytes(new ResultEntity<>(statusCode, "返回数据发生异常"));
                        return bufferFactory.wrap(uppedContent);
                    }));
                }
                return super.writeWith(body);
            }
        };
        return chain.filter(exchange.mutate().response(decoratedResponse).build());
    }

    private boolean isExistsCustomStatus(Integer statusCode){
        Optional<GatewayConfigProperties.FilterHttpStatus> find = gatewayConfigProperties
                .getFilterHttpStatus().stream().filter(item -> item.getStatusCode().equals(statusCode)).findAny();
        if(find.isPresent()){
            return true;
        }
        return false;
    }

    @Override
    public int getOrder() {
        return -10000;
    }
}
