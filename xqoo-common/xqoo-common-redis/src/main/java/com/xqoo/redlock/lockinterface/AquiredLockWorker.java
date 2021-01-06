package com.xqoo.redlock.lockinterface;



/**
 * redisson获取锁后需要处理的逻辑
 */
@Deprecated
public interface AquiredLockWorker<T> {
    T invokeAfterLockAquire() throws Exception;
}
