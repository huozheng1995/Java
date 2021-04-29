package com.learn.juc.base;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheDemo {

	private Map<String, Object> cache = new HashMap<String, Object>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CacheDemo cacheDemo = new CacheDemo();
		for(int i=0; i<10; i++) {
			new Thread(()->{
				cacheDemo.getData("key1");
//				cacheDemo.getData("key2");
			}).start();
		}
	}

	private ReadWriteLock rwl = new ReentrantReadWriteLock();
	public  Object getData(String key){
		rwl.readLock().lock();
		Object value = null;
		try{
			value = cache.get(key);
			System.out.println("aaa: " + Thread.currentThread().getName());
			Thread.sleep(50);
			if(value == null){
				rwl.readLock().unlock();
				rwl.writeLock().lock();
				System.out.println("yyy: " + Thread.currentThread().getName());
				try{
					value = cache.get(key);
					if(value==null){
						value = System.currentTimeMillis();//ʵ��ʧȥqueryDB();
						System.out.println(Thread.currentThread().getName() + ": " + value);
						cache.put(key, value);
					}
				}finally{
					rwl.writeLock().unlock();
				}
				rwl.readLock().lock();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally{
			rwl.readLock().unlock();
		}
		return value;
	}
}
