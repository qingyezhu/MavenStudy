package com.qingyezhu.common.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class LockTest {

	@Test
	public void testLock() {
		final SelfReentrantLock lock = new SelfReentrantLock();
		ExecutorService executor = Executors.newFixedThreadPool(10);
		for(int i = 0;i < 10;i ++){
			executor.execute(new Runnable() {
				public void run() {
					System.out.println("start==" + Thread.currentThread().getName());
					lock.lock();
					try{
						System.out.println("end==" + Thread.currentThread().getName());
						lock.printQueue();
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}finally{
						lock.unlock();
					}
				}
			});
		}
		executor.shutdown();
		try {
			executor.awaitTermination(1, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
