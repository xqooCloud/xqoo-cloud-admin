package com.xqoo.redlock.handle;

/**
 * Redisson单节点加锁主要方法
 * 代码原文 https://blog.csdn.net/weixin_41446894/article/details/86260854
 */
//@Component
/*
public class RedissonSingleServerLocker implements DistributedLocker {

    public final static String LOCKER_PREFIX = "lock:";

    // 注入单节点链接bean
    @Autowired
    RedissonSingleServerConnector redissonSingleServerConnector;
    @Override
    public <T> T lock(String resourceName, AquiredLockWorker<T> worker) throws InterruptedException, UnableToAquireLockException, Exception {

        return lock(resourceName, worker, 100);
    }

    @Override
    public <T> T lock(String resourceName, AquiredLockWorker<T> worker, int lockTime) throws UnableToAquireLockException, Exception {
        RedissonClient redisson= redissonSingleServerConnector.getClient();
        RLock lock = redisson.getLock(LOCKER_PREFIX + resourceName);
        // Wait for 100 seconds seconds and automatically unlock it after lockTime seconds
        boolean success = lock.tryLock(100, lockTime, TimeUnit.SECONDS);
        if (success) {
            try {
                return worker.invokeAfterLockAquire();
            } finally {
                lock.unlock();
            }
        }
        throw new UnableToAquireLockException();
    }
}
*/
