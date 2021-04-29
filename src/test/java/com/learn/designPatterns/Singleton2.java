package com.learn.designPatterns;

/**
 * @description: 登记式/静态内部类
 * @author: Huo
 * @create: 2020-04-22 11:00
 */
public class Singleton2 {
    static {
        System.out.println("Singleton2初始化了");
    }
    //SingletonHolder无论加不加final，如果不调用getInstance都不会初始化
    private static class SingletonHolder {
        static {
            System.out.println("SingletonHolder初始化了");
        }
        private static final Singleton2 singleton2 = new Singleton2();
    }
    public static Singleton2 getInstance() {
        return SingletonHolder.singleton2;
    }

    public static void main(String[] args) {
        System.out.println("开始。。。");
//        Singleton2.getInstance();
    }
}
