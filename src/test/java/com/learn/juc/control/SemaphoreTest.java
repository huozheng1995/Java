package com.learn.juc.control;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
//十个人用三把钥匙开门
public class SemaphoreTest {
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final  Semaphore sp = new Semaphore(3);
		System.out.println(sp.availablePermits());
		for(int i=0;i<10;i++){
			Runnable runnable = new Runnable(){
					public void run(){
					try {
						sp.acquire();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					System.out.println("线程" + Thread.currentThread().getName() +
							"===========" + (3-sp.availablePermits()) + "===========");
					try {
						Thread.sleep((long)(Math.random()*10000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("线程" + Thread.currentThread().getName() +
							"xxxxxxxxxx");
					sp.release();
					//���������ʱ��ִ�в�׼ȷ����Ϊ��û�к�����Ĵ���ϳ�ԭ�ӵ�Ԫ
					System.out.println("线程" + Thread.currentThread().getName() +
							"+++++++++++" + (3-sp.availablePermits()) + "+++++++++++");
				}
			};
			service.execute(runnable);			
		}
	}

}
