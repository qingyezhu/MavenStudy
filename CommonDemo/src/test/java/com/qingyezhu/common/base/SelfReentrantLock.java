package com.qingyezhu.common.base;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 用于自测Lock锁，在公平或不公平下的不同<br/>
 * @author zhuwang208531
 *
 */
public class SelfReentrantLock extends ReentrantLock {

	public SelfReentrantLock(){
		super();
	}
	public SelfReentrantLock(boolean fair){
		super(fair);
	}
	
	public void printQueue(){
		System.out.println(Thread.currentThread() + "===" + super.getQueuedThreads());
	}
}
