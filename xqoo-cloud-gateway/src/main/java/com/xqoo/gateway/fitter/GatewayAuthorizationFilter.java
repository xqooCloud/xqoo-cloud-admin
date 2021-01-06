package com.xqoo.gateway.fitter;

import com.xqoo.common.constants.SystemPublicConstant;
import com.xqoo.common.core.utils.SpringPathMather;
import com.xqoo.common.dto.AuthenDTO;
import com.xqoo.feign.service.auth.AuthFeign;
import com.xqoo.feign.utils.FeignReturnDataGzip;
import com.xqoo.gateway.bean.GatewayConfigProperties;
import com.xqoo.gateway.utils.PublicImmediatelyResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class GatewayAuthorizationFilter implements GlobalFilter, Ordered {

    private static final Logger log = LoggerFactory.getLogger(GatewayAuthorizationFilter.class);

    @Autowired
    private GatewayConfigProperties gatewayConfigProperties;

    @Autowired
    private AuthFeign authFeign;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getHeaders().getFirst(SystemPublicConstant.AUTH_HEADER_KEY_NAME);
        URI factUrl = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR);
        ServerHttpRequest request = exchange.getRequest();
        // 是否已经登录
        boolean logined =false;
        if (factUrl == null) {
            log.warn("[springCloudGateway网关]请求地址为null，不是有效的请求地址，直接返回");
            return PublicImmediatelyResponse.responseNow(exchange, HttpStatus.BAD_GATEWAY, "不是有效的请求地址", null);
        }
        String host = "/" + factUrl.getHost();
        String userName = "游客";
        if (token != null && !token.isEmpty()) {
            AuthenDTO authenDTO = FeignReturnDataGzip.Unzip(authFeign.isLogin(token), AuthenDTO.class);
            if(authenDTO.isSuccess()){
                logined = true;
                request.mutate().header(SystemPublicConstant.USER_ID_HEADER_KEY_NAME, authenDTO.getUserId()).build();

                try {
                    userName = URLEncoder.encode(authenDTO.getUserName(), StandardCharsets.UTF_8.name());
                }catch (UnsupportedEncodingException e){
                    log.warn("[springCloudGateway网关]转换中文至请求头中发生异常，URLEncoder出错");
                }
                request.mutate().header(SystemPublicConstant.USER_LOGIN_SOURCE_HEADER_KEY_NAME, authenDTO.getLoginSource()).build();
            }
        }
        request.mutate().header(SystemPublicConstant.USER_NAME_HEADER_KEY_NAME, userName).build();
        String path = factUrl.getPath();
        if("/".equals(path)){
            path += System.currentTimeMillis();
        }
        // 校验请求是否需要登录
        boolean noNeedLogin = SpringPathMather.MatchPath(gatewayConfigProperties.getNoLoginListFilter(), host + path);
        // 不需要登录即可访问
        if(noNeedLogin){
            return chain.filter(exchange);
        }
        // 需要登录才可访问，判断是否登录过
        if(!logined){
            return PublicImmediatelyResponse.responseNow(exchange, HttpStatus.UNAUTHORIZED, "请先登录", null);
        }
        return chain.filter(exchange);

    }

    @Override
    public int getOrder() {
        return 2147483501;
    }
}
