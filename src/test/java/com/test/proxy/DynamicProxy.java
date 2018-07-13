package com.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理类
 * 
 * @author 小二狗
 *
 */
public class DynamicProxy implements InvocationHandler {

	/**
	 * 要代理的真实对象
	 */
	private Object subject;

	public DynamicProxy(Object subject) {
		this.subject = subject;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("before rent house");
		System.out.println("Method:" + method);
		// 当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
		// 通过反射调用被代理类的方法
		method.invoke(subject, args);
		System.out.println("after rent house");
		return null;
	}

}
