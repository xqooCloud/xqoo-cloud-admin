package com.xqoo.gateway.fitter;

import com.xqoo.common.constants.SystemPublicConstant;
import com.xqoo.common.core.utils.SpringPathMather;
import com.xqoo.gateway.bean.GatewayConfigProperties;
import com.xqoo.gateway.enums.InterceptTypeEnmu;
import com.xqoo.gateway.service.SysGatewayInterceptLogService;
import com.xqoo.gateway.utils.PublicImmediatelyResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


/**
 * @author gaoyang by 2020/11/21 18:40
 * 网关黑名单请求拦截器
 */
@Component
public class GateWayBlackListFitter implements GlobalFilter, Ordered {

    private static final Logger log = LoggerFactory.getLogger(GateWayBlackListFitter.class);

    @Autowired
    private GatewayConfigProperties gatewayConfigProperties;

    @Autowired
    private SysGatewayInterceptLogService sysGatewayInterceptLogService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取连接参数
        URI factUrl = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR);
        if (factUrl == null) {
            log.warn("[springCloudGateway网关]请求地址为null，不是有效的请求地址，直接返回");
            return PublicImmediatelyResponse.responseNow(exchange, HttpStatus.BAD_GATEWAY, "不是有效的请求地址", null);
        }
        String host = "/" + factUrl.getHost();
        String path = factUrl.getPath();
        // 获取原始来源的ip和端口
        InetSocketAddress socketAddress = exchange.getRequest().getRemoteAddress();
        String remoteAddress = Objects.requireNonNull(socketAddress.getAddress()).getHostAddress();
        int remotePort = socketAddress.getPort();
        boolean isRemoteBlackList = SpringPathMather.MatchPath(gatewayConfigProperties.getRemoteBlackListFilter(), remoteAddress + ":" + remotePort);
        // 排除本机访问限制
        if(isRemoteBlackList && !remoteAddress.equals(factUrl.getHost())){
            Map<String, Object> rtnMap = new HashMap<>();
            rtnMap.put("reason", "您的ip已被系统禁止，无法访问");
            log.warn("[springCloudGateway网关]请求remoteIp:{},端口:{}为系统黑名单，不允许进入", remoteAddress, remotePort);
            rtnMap.put("remoteAddress", remoteAddress);
            rtnMap.put("remotePort", remotePort);
            // 增加拦截记录
            sysGatewayInterceptLogService.addInterceptRecord(remoteAddress, String.valueOf(remotePort), host + path, InterceptTypeEnmu.REMOTE);
            return PublicImmediatelyResponse.responseNow(exchange, HttpStatus.NOT_EXTENDED, "当前访问被拒绝，您的ip已被封禁", rtnMap);
        }
        exchange.getRequest().mutate().header(SystemPublicConstant.REMOTE_REQUEST_IP, remoteAddress).build();
        exchange.getRequest().mutate().header(SystemPublicConstant.REMOTE_REQUEST_PORT, String.valueOf(remotePort)).build();

        // 如果path为根目录，则在path后面添加随机值，否则会被/*/abcd的规则匹配，这显然不合理
        if("/".equals(path)){
            path += System.currentTimeMillis();
        }
        // 校验请求是否存在系统黑名单
        boolean isTargetBlackList = SpringPathMather.MatchPath(gatewayConfigProperties.getTargetBlackListFilter(), host + path);
        if(isTargetBlackList){
            Map<String, Object> rtnMap = new HashMap<>();
            rtnMap.put("reason", "存在于系统黑名单，不允许访问");
            log.warn("[springCloudGateway网关]请求uri：{}为系统黑名单，不允许访问", factUrl);
            rtnMap.put("targetUrl", factUrl);
            // 增加拦截记录
            sysGatewayInterceptLogService.addInterceptRecord(remoteAddress, String.valueOf(remotePort), host + path, InterceptTypeEnmu.TARGET);
            return PublicImmediatelyResponse.responseNow(exchange, HttpStatus.NETWORK_AUTHENTICATION_REQUIRED, "当前请求路径已被列入黑名单", rtnMap);
        }

        return chain.filter(exchange);
    }



    @Override
    public int getOrder() {
        //加载顺序
        return 2147483500;
    }


}
