package com.qingyezhu.common.designpattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 创建代理类的中间类，用于生成代理类<br/>
 * 被代理类(委托类)的加载器；被代理类已经实现的接口，保证代理类也实现了这些接口；中间类对象，此时需要被代理类实例<br/>
 * 参考资料：<br/>
 * http://www.jasongj.com/design_pattern/dynamic_proxy_cglib/<br/>
 * http://www.codekk.com/blogs/detail/54cfab086c4761e5001b253d<br/>
 * 
 * @author zhuwang208531
 *
 */
public class OperateDynamicProxy implements InvocationHandler {

	private static final Logger logger = LoggerFactory.getLogger(OperateDynamicProxy.class);

	private Object delegate;

	public OperateDynamicProxy() {
	}

	/**
	 * 初始化被代理类(委托类)对象<br/>
	 * 
	 * @param delegate
	 */
	public OperateDynamicProxy(Object delegate) {
		this.delegate = delegate;
	}
	
	private static final OperateProxyFactory factory = new OperateProxyFactory();
	
	public static IOperate wrap(IOperate operate){
		return factory.createIOperate(new OperateDynamicProxy(operate));
	}

	/**
	 * 每次进行代理类的执行，都会调用invoke方法<br/>
	 * proxy:
	 * 被代理类(委托类)，与上面的delegate不同的是，该被代理类含有当前中间类OperateDynamicProxy的引用，故不能直接使用<br/>
	 * method: 被代理类中的方法<br/>
	 * args: 被代理类中的方法所需要的参数<br/>
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object ret = null;
		try {
			// 可以在此处增加一些方法调用前的额外的一些处理，如权限校验等
			logger.info("method before ......");
			// 使用反射机制调用被代理类(委托类)的方法
			ret = method.invoke(delegate, args);
			logger.info("method end .......");
			// 可以在此处增加一些方法调用后的额外的一些处理，如耗时统计等
		} catch (Exception e) {
			logger.error("catch error msg=" + e.getMessage(), e);
		}

		return ret;
	}

	/**
	 * 使用被代理类对象进行动态绑定，获取代理类对象<br/>
	 * 
	 * @param delegate
	 *            被代理类对象
	 * @return
	 */
	public Object bind(Object delegate) {
		Class<?> clazz = delegate.getClass();
		return bind(delegate, clazz);
	}

	private Object bind(Object delegate, Class<?> clazz) {
		this.delegate = delegate;
		return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
	}

}
