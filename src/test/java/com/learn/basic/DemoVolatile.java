package com.learn.basic;

/**
 * @description: volatile
 * @author: Huo
 * @create: 2020-03-30 10:23
 */
public class DemoVolatile {
    //volatile关键字能保证可见性，但是没能保证原子性,因此最终打印的值不一定是10000
    public volatile int inc = 0;
    public void increase() {
        inc++;
    }


    //可以通过三种方法保证打印的是10000
    //1.increase方法加上synchronized
//    public  int inc = 0;
//    public synchronized void increase() {
//        inc++;
//    }

    //2. increase方法加上lock
//    public  int inc = 0;
//    Lock lock = new ReentrantLock();
//
//    public  void increase() {
//        lock.lock();
//        try {
//            inc++;
//        } finally{
//            lock.unlock();
//        }
//    }

    //3.采用AtomicInteger
//    public AtomicInteger inc = new AtomicInteger();
//
//    public void increase() {
//        inc.getAndIncrement();
//    }


    public static void main(String[] args) {
        final DemoVolatile test = new DemoVolatile();
        for(int i=0;i<10;i++){
            new Thread(()-> {
                for(int j=0;j<1000;j++) {
                    test.increase();
                }
            }).start();
        }
        while(Thread.activeCount()>2) {
            System.out.println(Thread.activeCount() + "activeCount");
            Thread.yield();
        }

        System.out.println(test.inc);
    }
}
