package com.learn.juc.interrupt;

/**
 * @description: 线程中断示例写法
 *
 * @author: Huo
 * @create: 2020-06-17 15:59
 */
public class InterruptionInJava implements Runnable{
    private volatile static boolean on = false;
    public static void main(String[] args) throws InterruptedException {
        Thread testThread = new Thread(new InterruptionInJava(),"InterruptionInJava");
        //start thread
        testThread.start();
        Thread.sleep(1000);
        InterruptionInJava.on = true;
        testThread.interrupt();

        System.out.println("main end");

    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(10000000);
            }
        } catch (InterruptedException e) {
            System.out.println("caught exception right now: "+e);
        }
    }
}
