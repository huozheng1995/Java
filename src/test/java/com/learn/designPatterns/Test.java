package com.learn.designPatterns;

/**
 * @description:
 * @author: Huo
 * @create: 2020-04-23 17:14
 */
public class Test {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(()->{
                System.out.println(Thread.currentThread().getName() + " " + Singleton2.getInstance().toString());
                }
            );
            thread.setName("Thread-" + i);
            thread.start();
        }

    }

    static class Singleton {
        private static Singleton cache = null;
        private static Object mutexObj = new Object();

        private Singleton() {

        }

        public static Singleton getInstance() {
            if (cache == null) {
                System.out.println(Thread.currentThread().getName() + " in outer if block");
                synchronized (mutexObj) {
                    System.out.println(Thread.currentThread().getName() + " in synchronized block");
                    if (cache == null) {
                        System.out.println(Thread.currentThread().getName() + " in inner if block");
                        cache = new Singleton();
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + " out inner if block");
                }
                System.out.println(Thread.currentThread().getName() + " out synchronized block");
            }
            System.out.println(Thread.currentThread().getName() + " out outer if block");
            return cache;
        }
    }
}
