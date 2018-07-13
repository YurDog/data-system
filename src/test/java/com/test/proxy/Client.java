package com.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Client {
	public static void main(String[] args) {
		// 我们要代理的真实对象
		Subject realSubject = new RealSubject();
		//
		InvocationHandler handler = new DynamicProxy(realSubject);

		Subject subject = (Subject) Proxy.newProxyInstance(handler.getClass().getClassLoader(), realSubject.getClass().getInterfaces(), handler);

		System.out.println(subject.getClass().getName());
		subject.rent();
		subject.hello("world");
	}
}
