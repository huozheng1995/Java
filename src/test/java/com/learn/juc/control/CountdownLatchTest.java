package com.learn.juc.control;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//赛跑准备阶段，await等待，countDown数量足够await被触发
public class CountdownLatchTest {

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final CountDownLatch cdOrder = new CountDownLatch(1);
		final CountDownLatch cdAnswer = new CountDownLatch(3);		
		for(int i=0;i<3;i++){
			Runnable runnable = new Runnable(){
					public void run(){
					try {
						System.out.println("aaa" + Thread.currentThread().getName() +
								"��׼����������");
						cdOrder.await();
						System.out.println("bbb" + Thread.currentThread().getName() +
						"�ѽ�������");								
						Thread.sleep((long)(Math.random()*10000));	
						System.out.println("ccc" + Thread.currentThread().getName() +
								"��Ӧ�������");						
						cdAnswer.countDown();						
					} catch (Exception e) {
						e.printStackTrace();
					}				
				}
			};
			service.execute(runnable);
		}		
		try {
			Thread.sleep((long)(Math.random()*10000));
		
			System.out.println("AAA" + Thread.currentThread().getName() +
					"������������");						
			cdOrder.countDown();
			System.out.println("BBB" + Thread.currentThread().getName() +
			"�ѷ���������ڵȴ����");	
			cdAnswer.await();
			System.out.println("CCC" + Thread.currentThread().getName() +
			"���յ�������Ӧ���");	
		} catch (Exception e) {
			e.printStackTrace();
		}				
		service.shutdown();

	}
}
