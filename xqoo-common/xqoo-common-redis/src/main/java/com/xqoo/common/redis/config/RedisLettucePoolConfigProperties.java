package com.xqoo.common.redis.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component("redisLettucePoolConfigProperties")
@ConfigurationProperties(prefix = "spring.redis.lettuce.pool")
public class RedisLettucePoolConfigProperties {

    private int maxIdle;

    private int minIdle;

    private int maxActive;

    private long maxWait;

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public long getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(long maxWait) {
        this.maxWait = maxWait;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RedisLettucePoolConfigProperties that = (RedisLettucePoolConfigProperties) o;
        return maxIdle == that.maxIdle &&
                minIdle == that.minIdle &&
                maxActive == that.maxActive &&
                maxWait == that.maxWait;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxIdle, minIdle, maxActive, maxWait);
    }

    @Override
    public String toString() {
        return "RedisLettucePoolConfigProperties{" +
                "maxIdle=" + maxIdle +
                ", minIdle=" + minIdle +
                ", maxActive=" + maxActive +
                ", maxWait=" + maxWait +
                '}';
    }
}
