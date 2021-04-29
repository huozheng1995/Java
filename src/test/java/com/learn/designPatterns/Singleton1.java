package com.learn.designPatterns;

/**
 * @description: 双检锁/双重校验锁（DCL，即 double-checked locking）
 * @author: Huo
 * @create: 2020-04-22 10:37
 */
public class Singleton1 {
    private volatile static Singleton1 singleton1 = null;
    public static Singleton1 getInstance() {
        if(singleton1==null) {
            synchronized (Singleton1.class) {
                if(singleton1==null) {
                    singleton1 = new Singleton1();
                }
            }
        }
        return singleton1;
    }
}
