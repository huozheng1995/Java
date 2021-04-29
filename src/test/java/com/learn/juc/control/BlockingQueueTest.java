package com.learn.juc.control;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

//队列
public class BlockingQueueTest {
    public static void main(String[] args) {
        final BlockingQueue queue = new ArrayBlockingQueue(3);
        for (int i = 0; i < 2; i++) {
            new Thread() {
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep((long) (Math.random() * 1000));
                            System.out.println(Thread.currentThread().getName() + "put");
                            queue.put(1);
                            System.out.println(Thread.currentThread().getName() + "put2" +
                                    "����Ŀǰ��" + queue.size() + "������");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }

            }.start();
        }

        new Thread() {
            public void run() {
                while (true) {
                    try {
                        //���˴���˯��ʱ��ֱ��Ϊ100��1000���۲����н��
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName() + "take");
                        queue.take();
                        System.out.println(Thread.currentThread().getName() + "take2" +
                                "����Ŀǰ��" + queue.size() + "������");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }.start();
    }
}
