package com.xqoo.common.redis.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component("redisConfigProperties")
@ConfigurationProperties(prefix = "spring.redis")
public class RedisConfigProperties {

    // 所用库
    private int database;

    // 主机名
    private String host;

    // 密码
    private String password;

    //端口
    private int port;

    // 超时时间
    private long timeout;

    // 最大重定向次数，此项只有集群环境有效
    private int maxRedirects;

    // 集群节点扫描间隔，毫秒
    private int scanInterval;

    // 集群节点列表，此项只有集群有效
    private List<RedisClusterNodeBO> clusterNode;


    public static class RedisClusterNodeBO{

        private String nodeHost;

        private int nodePort;

        public String getNodeHost() {
            return nodeHost;
        }

        public void setNodeHost(String nodeHost) {
            this.nodeHost = nodeHost;
        }

        public int getNodePort() {
            return nodePort;
        }

        public void setNodePort(int nodePort) {
            this.nodePort = nodePort;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            RedisClusterNodeBO that = (RedisClusterNodeBO) o;
            return Objects.equals(nodeHost, that.nodeHost) &&
                    Objects.equals(nodePort, that.nodePort);
        }

        @Override
        public int hashCode() {
            return Objects.hash(nodeHost, nodePort);
        }

        @Override
        public String toString() {
            return "RedisClusterNodeBO{" +
                    "nodeHost='" + nodeHost + '\'' +
                    ", nodePort='" + nodePort + '\'' +
                    '}';
        }
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public int getMaxRedirects() {
        return maxRedirects;
    }

    public void setMaxRedirects(int maxRedirects) {
        this.maxRedirects = maxRedirects;
    }

    public int getScanInterval() {
        return scanInterval;
    }

    public void setScanInterval(int scanInterval) {
        this.scanInterval = scanInterval;
    }

    public List<RedisClusterNodeBO> getClusterNode() {
        return clusterNode;
    }

    public void setClusterNode(List<RedisClusterNodeBO> clusterNode) {
        this.clusterNode = clusterNode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RedisConfigProperties that = (RedisConfigProperties) o;
        return database == that.database &&
                port == that.port &&
                timeout == that.timeout &&
                maxRedirects == that.maxRedirects &&
                scanInterval == that.scanInterval &&
                Objects.equals(host, that.host) &&
                Objects.equals(password, that.password) &&
                Objects.equals(clusterNode, that.clusterNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(database, host, password, port, timeout, maxRedirects, scanInterval, clusterNode);
    }

    @Override
    public String toString() {
        return "RedisConfigProperties{" +
                "database=" + database +
                ", host='" + host + '\'' +
                ", password='" + password + '\'' +
                ", port=" + port +
                ", timeout=" + timeout +
                ", maxRedirects=" + maxRedirects +
                ", scanInterval=" + scanInterval +
                ", clusterNode=" + clusterNode +
                '}';
    }
}
