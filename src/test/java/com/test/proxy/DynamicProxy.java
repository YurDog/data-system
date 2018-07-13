package com.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * ��̬������
 * 
 * @author С����
 *
 */
public class DynamicProxy implements InvocationHandler {

	/**
	 * Ҫ�������ʵ����
	 */
	private Object subject;

	public DynamicProxy(Object subject) {
		this.subject = subject;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("before rent house");
		System.out.println("Method:" + method);
		// ��������������ʵ����ķ���ʱ������Զ�����ת��������������handler�����invoke���������е���
		// ͨ��������ñ�������ķ���
		method.invoke(subject, args);
		System.out.println("after rent house");
		return null;
	}

}
