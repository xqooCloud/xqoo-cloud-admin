package com.xqoo.gateway.fitter;

import com.xqoo.common.entity.ResultEntity;
import com.xqoo.common.core.utils.JacksonUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 验证码处理
 */
@Component
public class ImgCodeFilter extends AbstractGatewayFilterFactory<ImgCodeFilter.Config> {
    private final static String AUTH_URL = "/oauth/token";

    @Autowired
    private StringRedisTemplate redisTemplate;

    public ImgCodeFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            URI uri = request.getURI();
            // 不是登录请求，直接向下执行
            if (!StringUtils.containsIgnoreCase(uri.getPath(), AUTH_URL)) {
                return chain.filter(exchange);
            }
            try {
                MultiValueMap<String, String> params = request.getQueryParams();
                String code = params.getFirst("captcha");
                String randomStr = params.getFirst("randomStr");
                // 校验验证码
                String key = "random_code_" + randomStr;
                String saveCode = redisTemplate.opsForValue().get(key);
                redisTemplate.delete(key);
                if (!code.equalsIgnoreCase(saveCode)) {
                    ServerHttpResponse response = exchange.getResponse();
                    response.setStatusCode(HttpStatus.NOT_ACCEPTABLE);
                    response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
                    String msg = JacksonUtils.toJson(new ResultEntity<>(HttpStatus.NOT_ACCEPTABLE, "验证码不正确"));
                    DataBuffer bodyDataBuffer = response.bufferFactory().wrap(msg.getBytes());
                    return response.writeWith(Mono.just(bodyDataBuffer));
                }
            } catch (Exception e) {
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
                response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
                String msg = JacksonUtils.toJson(e.getMessage());
                DataBuffer bodyDataBuffer = response.bufferFactory().wrap(msg.getBytes());
                return response.writeWith(Mono.just(bodyDataBuffer));
            }
            return chain.filter(exchange);
        };
    }

    private String resolveBodyFromRequest(ServerHttpRequest serverHttpRequest) {
        // 获取请求体
        Flux<DataBuffer> body = serverHttpRequest.getBody();
        AtomicReference<String> bodyRef = new AtomicReference<>();
        body.subscribe(buffer -> {
            CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer.asByteBuffer());
            DataBufferUtils.release(buffer);
            bodyRef.set(charBuffer.toString());
        });
        return bodyRef.get();
    }

    public static class Config {
    }
}
