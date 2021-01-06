package com.xqoo.gateway.config;

import com.alibaba.cloud.sentinel.SentinelProperties;
import com.alibaba.cloud.sentinel.datasource.config.NacosDataSourceProperties;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.fasterxml.jackson.core.type.TypeReference;
import com.xqoo.common.core.utils.JacksonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.Set;

@Configuration
@Order(2)
public class SentinelPersistenceConfig {
    @Autowired
    private SentinelProperties sentinelProperties;

    @Bean
    public SentinelPersistenceConfig init() throws Exception {
        loadGWFlowRule();
        return new SentinelPersistenceConfig();
    }

    private void loadGWFlowRule() {
        sentinelProperties.getDatasource().entrySet().stream().filter(map -> {
            return map.getValue().getNacos() != null;
        }).forEach(map -> {
            NacosDataSourceProperties nacos = map.getValue().getNacos();
            ReadableDataSource<String, Set<GatewayFlowRule>> gwFlowRuleDataSource = new NacosDataSource<>(
                    nacos.getServerAddr(), nacos.getGroupId(), nacos.getDataId(),
                    source -> JacksonUtils.toObj(source, new TypeReference<Set<GatewayFlowRule>>() {
                    }));
            GatewayRuleManager.register2Property(gwFlowRuleDataSource.getProperty());
        });
    }
}
