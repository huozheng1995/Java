package com.learn.juc.thread;

import java.util.HashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @description: 缓存器
 * @author: Huo
 * @create: 2020-03-11 14:05
 */
public class CacheDemo {
    private static HashMap<String,Object> cache = new HashMap<>();
    private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public static Object getData(String key) {
        Object value = null;
        readWriteLock.readLock().lock();
        try {
            value = cache.get(key);
            if(value==null) {
                readWriteLock.readLock().unlock();
                readWriteLock.writeLock().lock();
                try {
                    if(value==null) {//防止后面的线程也加载数据
                        value = "newValue";//查数据库
                        cache.put(key, value);
                    }
                } finally {
                    readWriteLock.readLock().lock();
                    readWriteLock.writeLock().unlock();
                }
            }
        } finally {
            readWriteLock.readLock().unlock();
        }

        return value;
    }
}
