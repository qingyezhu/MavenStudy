package com.qingyezhu.common.designpattern.proxy;

import org.junit.Test;

public class OperateProxyTest {
	
	@Test
	public void testInstance(){
		//创建被代理类，即委托类
		IOperate target = new OperateImpl();
		//使用JDK的动态代理，其中需要实现InvocationHandler接口
		IOperate proxy = OperateProxyFactory.create(target);
		//直接使用代理类进行操作
		proxy.work(12, "hello");
	}
	
	@Test
	public void testClazz(){
		IOperate proxy;
		try {
			//此时需要被代理类中有无参构造方法
			proxy = OperateProxyFactory.create(OperateImpl.class);
			proxy.work(100, "world");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFactory(){
		IOperate proxy;
		try {
			IOperate target = new OperateImpl();
			//此时需要被代理类中有无参构造方法
			proxy = OperateDynamicProxy.wrap(target);
			proxy.work(101, "buhao");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
