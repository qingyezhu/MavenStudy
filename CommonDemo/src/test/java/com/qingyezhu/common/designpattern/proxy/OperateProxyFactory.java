package com.qingyezhu.common.designpattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class OperateProxyFactory {
	/**
	 * 通过被代理类(委托类)对象，获取代理类对象<br/>
	 * 要求被代理类中有无参构造方法<br/>
	 * 
	 * @param t
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T extends IOperate> T create(T t) {
		return (T) new OperateDynamicProxy().bind(t);
	}

	/**
	 * 通过被代理类(委托类)的类对象，获取代理类对象<br/>
	 * 要求被代理类中有有参构造方法<br/>
	 * 
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T extends IOperate> T create(Class<T> clazz) throws Exception {
		// 创建代理类的中间类，用于生成代理类
		// 被代理类(委托类)的加载器；被代理类已经实现的接口，保证代理类也实现了这些接口；中间类对象，此时需要被代理类实例
		T t = (T) Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(),
				new OperateDynamicProxy(clazz.newInstance()));
		return t;
	}
	
	public IOperate createIOperate(InvocationHandler handler){
		return newProxyInstance(IOperate.class, handler);
	}
	
	public <T> T newProxyInstance(Class<T> type, InvocationHandler handler){
		return type.cast(Proxy.newProxyInstance(handler.getClass().getClassLoader(), new Class<?>[]{type}, handler));
	}
}
