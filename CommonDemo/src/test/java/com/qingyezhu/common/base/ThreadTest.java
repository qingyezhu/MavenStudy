package com.qingyezhu.common.base;

import org.junit.Test;

public class ThreadTest {

	static class Thread1 extends Thread{
		Thread1(String name){
			super(name);
			System.out.println(Thread.currentThread() + "-----thread1------" + name);
		}
		
		static{
			System.out.println(Thread.currentThread() + "-----thread1------");
		}
		
		@Override
		public void run() {
			System.out.println(Thread.currentThread() + "-----thread1--run----");
			
			new Thread2("b").start();;
		}
	}
	
	static class Thread2 extends Thread{
		Thread2(String name){
			super(name);
			System.out.println(Thread.currentThread() + "-----thread2------" + name);
		}
		
		static{
			System.out.println(Thread.currentThread() + "-----thread2------");
		}
		
		@Override
		public void run() {
			System.out.println(Thread.currentThread() + "-----thread2--run----");
		}
	}
	
	
	@Test
	public void testThead(){
		new Thread1("a").start();
	}
	
}
