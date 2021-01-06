package com.xqoo.gateway.handler;

import com.xqoo.common.core.utils.JacksonUtils;
import com.xqoo.common.core.utils.SpringPathMather;
import com.xqoo.common.core.utils.StringUtils;
import com.xqoo.common.entity.ResultEntity;
import com.xqoo.gateway.bean.GatewayConfigProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.UnknownHostException;
import java.util.Optional;

/**
 * 网关统一异常处理
 *
 * @author ruoyi
 */
@Order(-1)
@Configuration
public class GatewayExceptionHandler implements ErrorWebExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GatewayExceptionHandler.class);


    @Autowired
    private GatewayConfigProperties gatewayConfigProperties;

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex){
        ServerHttpResponse response = exchange.getResponse();
        if (exchange.getResponse().isCommitted()) {
            return Mono.error(ex);
        }
        final String exceptionType = ex.getClass().getSimpleName();

        final String[] msg = {"这个错误类型【" + exceptionType + "】没有被收录,请上报扣除程序员小哥的奖金！"};

        ServerHttpRequest request = exchange.getRequest();
        if(SpringPathMather.MatchPath("/webjars/**", request.getPath().value())){
            return response.writeWith(Mono.fromSupplier(() -> {
                DataBufferFactory bufferFactory = response.bufferFactory();
                return bufferFactory.wrap(JacksonUtils.toJsonBytes(new ResultEntity<>(HttpStatus.OK, "webjars资源请求，忽视")));
            }));
        }

        log.error("[网关响应异常]异常类型:{},异常信息:{}请求路径:{}", exceptionType, ex.getMessage(), exchange.getRequest().getPath());

        // 下面的判断顺序不能乱放
        if (ex instanceof ResponseStatusException) {
            msg[0] = GetSystemHandleErr(((ResponseStatusException) ex).getStatus().value());
            if(StringUtils.isEmpty(msg)){
                ResponseStatusException responseStatusException = (ResponseStatusException) ex;
                msg[0] = responseStatusException.getMessage();
            }
        }

        if (ex instanceof NotFoundException) {
            msg[0] = "当前访问的连接已经消失,换个地方试试";
        }

        if(ex instanceof UnknownHostException){
            msg[0] = "您锁访问的目的地恐怕不在地求上,请转航天局处理^_^";
        }
        if(ex instanceof NullPointerException){
            msg[0] = "程序内部代码产生了黑洞,吞噬了整个系统……";
        }
        if(ex instanceof QueryTimeoutException){
            msg[0] = "数据存储元件发生了一点小问题,请等待程序员小哥加班";
        }

        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        response.setStatusCode(HttpStatus.OK);

        return response.writeWith(Mono.fromSupplier(() -> {
            DataBufferFactory bufferFactory = response.bufferFactory();
            return bufferFactory.wrap(JacksonUtils.toJsonBytes(new ResultEntity<>(HttpStatus.SERVICE_UNAVAILABLE, msg[0])));
        }));
    }

    private String GetSystemHandleErr(Integer statusCode){
        Optional<GatewayConfigProperties.FilterHttpStatus> find = gatewayConfigProperties
                .getFilterHttpStatus().stream().filter(item -> item.getStatusCode().equals(statusCode)).findAny();
        if(find.isPresent()){
            return find.get().getReason();
        }
        return null;
    }
}
