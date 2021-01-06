package com.xqoo.common.redis.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;

import java.time.Duration;

/**
 * @author GaoYang by 2020 11 21
 */
@Configuration
public class LettuceRedisConfig {

    @Autowired
    @Qualifier("redisConfigProperties")
    private RedisConfigProperties redisConfigProperties;

    @Autowired
    @Qualifier("redisLettucePoolConfigProperties")
    private RedisLettucePoolConfigProperties redisLettucePoolConfigProperties;

    @Bean
    LettuceConnectionFactory lettuceConnectionFactory(GenericObjectPoolConfig genericObjectPoolConfig) {

        LettuceClientConfiguration clientConfig = LettucePoolingClientConfiguration.builder()
                .commandTimeout(Duration.ofMillis(redisConfigProperties.getTimeout()))
                .poolConfig(genericObjectPoolConfig)
                .build();

        /**
         * 切换集群或单机注释一边关闭一边即可
         * 注意配置文件里要有集群节点配置
         */

        // 单机版配置
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setDatabase(redisConfigProperties.getDatabase());
        redisStandaloneConfiguration.setHostName(redisConfigProperties.getHost());
        redisStandaloneConfiguration.setPort(redisConfigProperties.getPort());
        redisStandaloneConfiguration.setPassword(RedisPassword.of(redisConfigProperties.getPassword()));
        LettuceConnectionFactory factory = new LettuceConnectionFactory(redisStandaloneConfiguration,clientConfig);

        // 集群版配置
        /*RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        Set<RedisNode> nodes = new HashSet<RedisNode>();
        for(RedisConfigProperties.RedisClusterNodeBO clusterNodeBO : redisConfigProperties.getClusterNode()) {
            nodes.add(new RedisNode(clusterNodeBO.getNodeHost().trim(), clusterNodeBO.getNodePort()));
        }
        redisClusterConfiguration.setPassword(RedisPassword.of(redisConfigProperties.getPassword()));
        redisClusterConfiguration.setClusterNodes(nodes);
        redisClusterConfiguration.setMaxRedirects(redisConfigProperties.getMaxRedirects());
        LettuceConnectionFactory factory = new LettuceConnectionFactory(redisClusterConfiguration,clientConfig);*/

        return factory;
    }

    /**
     * GenericObjectPoolConfig 连接池配置
     *
     * @return
     */
    @Bean
    public GenericObjectPoolConfig genericObjectPoolConfig() {
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxIdle(redisLettucePoolConfigProperties.getMaxIdle());
        genericObjectPoolConfig.setMinIdle(redisLettucePoolConfigProperties.getMinIdle());
        genericObjectPoolConfig.setMaxTotal(redisLettucePoolConfigProperties.getMaxActive());
        genericObjectPoolConfig.setMaxWaitMillis(redisLettucePoolConfigProperties.getMaxWait());
        genericObjectPoolConfig.setTestOnBorrow(true);
        genericObjectPoolConfig.setTestOnCreate(false);
        genericObjectPoolConfig.setTestOnReturn(false);
        genericObjectPoolConfig.setTestWhileIdle(true);
        return genericObjectPoolConfig;
    }
}
