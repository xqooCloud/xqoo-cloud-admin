package com.xqoo.redlock.utils;


/**
 * @author Gaoyang by 2020 11 21
 * 单节点获取RedissonClient连接类
 * 代码原文 https://blog.csdn.net/weixin_41446894/article/details/86260854
 * 获取RedissonClient类经过改写，因为redis配置参数不在本项目，根据不同服务注入时获取，所以不能采用原文的初始化方式
 */
@Deprecated
//@Configuration
public class RedissonSingleServerConnector {

    /*@Autowired
    @Qualifier("redisConfigProperties")
    private RedisConfigProperties redisConfigProperties;

    @Autowired
    @Qualifier("redisLettucePoolConfigProperties")
    private RedisLettucePoolConfigProperties redisLettucePoolConfigProperties;

    RedissonClient redisson;
    @PostConstruct
    public void init(){
        Config config = new Config();

        *//**
         * 其他地方不用改，注释一遍关闭另一边就行了
         *
         * 单机配置
         *//*
        //指定使用单节点部署方式 集群方式需要改为ClusterServersConfig的配置
        SingleServerConfig singleConfig = config.useSingleServer();
        singleConfig.setAddress("redis://"+redisConfigProperties.getHost()+":"+redisConfigProperties.getPort());
        //设置密码
        singleConfig.setPassword(redisConfigProperties.getPassword());
        singleConfig.setDatabase(redisConfigProperties.getDatabase());
        singleConfig.setConnectionPoolSize(10);
        singleConfig.setKeepAlive(true);
        //设置对于master节点的连接池中连接数最大为500 这个属性集群不适用，
        singleConfig.setConnectionPoolSize(redisLettucePoolConfigProperties.getMaxActive());
        //如果当前连接池里的连接数量超过了最小空闲连接数，而同时有连接空闲时间超过了该数值，
        // 那么这些连接将会自动被关闭，并从连接池里去掉。时间单位是毫秒。
        singleConfig.setIdleConnectionTimeout((int)redisLettucePoolConfigProperties.getMaxWait());
        //同任何节点建立连接时的等待超时。时间单位是毫秒。
        singleConfig.setConnectTimeout((int)redisConfigProperties.getTimeout());
        //等待节点回复命令的时间。该时间从命令发送成功时开始计时。
        singleConfig.setTimeout((int)redisConfigProperties.getTimeout());
        //当与某个节点的连接断开时，等待与其重新建立连接的时间间隔。时间单位是毫秒。
        singleConfig.setKeepAlive(true);
        //创建客户端(发现创建RedissonClient非常耗时，基本在2秒-4秒左右)


        *//**
         * 集群配置
         *//*
        *//*ClusterServersConfig clusterServersConfig = config.useClusterServers();
        String[] nodes = new String[redisConfigProperties.getClusterNode().size()];
        int i = 0;
        for(RedisConfigProperties.RedisClusterNodeBO clusterNodeBO : redisConfigProperties.getClusterNode()){
            nodes[i] = "redis://"+clusterNodeBO.getNodeHost()+":"+clusterNodeBO.getNodePort();
            i += 1;
        }
        clusterServersConfig.addNodeAddress(nodes);
        //设置密码
        clusterServersConfig.setPassword(redisConfigProperties.getPassword());
        //集群部署设置，扫描节点时间间隔，毫秒
        clusterServersConfig.setScanInterval(redisConfigProperties.getScanInterval());
        clusterServersConfig.setSlaveConnectionPoolSize(10);
        clusterServersConfig.setMasterConnectionPoolSize(15);
        //如果当前连接池里的连接数量超过了最小空闲连接数，而同时有连接空闲时间超过了该数值，
        // 那么这些连接将会自动被关闭，并从连接池里去掉。时间单位是毫秒。
        clusterServersConfig.setIdleConnectionTimeout((int) redisLettucePoolConfigProperties.getMaxWait());
        //同任何节点建立连接时的等待超时。时间单位是毫秒。
        clusterServersConfig.setConnectTimeout((int) redisConfigProperties.getTimeout());
        //等待节点回复命令的时间。该时间从命令发送成功时开始计时。
        clusterServersConfig.setTimeout((int) redisConfigProperties.getTimeout());
        //当与某个节点的连接断开时，等待与其重新建立连接的时间间隔。时间单位是毫秒。
        clusterServersConfig.setKeepAlive(true);*//*

        redisson = Redisson.create(config);
    }

    public RedissonClient getClient(){
        return redisson;
    }*/

}

