package com.learn.juc.base;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


public class CallableAndFuture {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExecutorService threadPool =  Executors.newSingleThreadExecutor();
		//线程数超过一个没有意义，第一个线程return后就会执行future.get()
//		ExecutorService threadPool =  Executors.newFixedThreadPool(3);
		Future<String> future =
			threadPool.submit(
				new Callable<String>() {
					public String call() throws Exception {
						Thread.sleep(2000);
						System.out.println("x");
						return "hello";
					};
				}
		);
		System.out.println("xxx");
		try {
			System.out.println("yyy" + future.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ExecutorService threadPool2 =  Executors.newFixedThreadPool(10);
		CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(threadPool2);
		for(int i=1;i<=10;i++){
			final int seq = i;
			completionService.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					Thread.sleep(new Random().nextInt(5000));
					return seq;
				}
			});
		}
		for(int i=0;i<10;i++){
			try {
				System.out.println(
						completionService.take().get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("over");
	}
	

}
