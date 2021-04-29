package com.learn.juc.control;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//一起上山，到山腰等所有人到了再上
public class CyclicBarrierTest {

	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final  CyclicBarrier cb = new CyclicBarrier(3);
		for(int i=0;i<3;i++){
			Runnable runnable = new Runnable(){
					public void run(){
					try {
						Thread.sleep((long)(Math.random()*10000));	
						System.out.println("�߳�" + Thread.currentThread().getName() + 
								"aaa" + (cb.getNumberWaiting()+1) + "���Ѿ����" + (cb.getNumberWaiting()==2?"�������ˣ������߰�":"���ڵȺ�"));
						cb.await();
						
						Thread.sleep((long)(Math.random()*10000));	
						System.out.println("�߳�" + Thread.currentThread().getName() + 
								"bbb" + (cb.getNumberWaiting()+1) + "���Ѿ����" + (cb.getNumberWaiting()==2?"�������ˣ������߰�":"���ڵȺ�"));
						cb.await();	
						Thread.sleep((long)(Math.random()*10000));	
						System.out.println("�߳�" + Thread.currentThread().getName() + 
								"ccc" + (cb.getNumberWaiting() + 1) + "���Ѿ����" + (cb.getNumberWaiting()==2?"�������ˣ������߰�":"���ڵȺ�"));
						cb.await();						
					} catch (Exception e) {
						e.printStackTrace();
					}				
				}
			};
			service.execute(runnable);
		}
		service.shutdown();
	}
}
