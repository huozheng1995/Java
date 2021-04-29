package com.learn.basic;


/**
 * @description: 多线程
 * @author: Huo
 * @create: 2019-10-21 17:19
 */
public class DemoThread {

    public static void main(String[] args) {
        //测试extends Thread
        MyThread thread1 = new MyThread("A");
        MyThread thread2 = new MyThread("B");
        thread1.start();
        thread2.start();
        //测试implements Runnable
//        MyRunnable myRunnable = new MyRunnable();
//        new Thread(myRunnable).start();
//        new Thread(myRunnable).start();
//        new Thread(myRunnable).start();


    }
}
//测试extends Thread
class MyThread extends Thread {

    private String name;

    public MyThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for(int i=0; i<10; i++) {
            System.out.println(name + ":" + i);
            try{
                sleep(1000);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
//测试implements Runnable
class MyRunnable implements Runnable {
    private int ticket = 10;
    @Override
    public synchronized void run() {
        for(int i=0; i<10; i++) {
            if(this.ticket > 0) {
                System.out.println("卖票：ticket" + this.ticket--);
            }
        }
    }
}