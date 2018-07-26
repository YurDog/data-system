package com.data.system.util;

/**
 * 静态ThreadLocal，用于串连http请求处理链
 * 
 * @author 小二狗
 *
 */
public class RequestChainSignature {

	private static ThreadLocal<String> threadLocal = new ThreadLocal<String>();

	public static String getSignature() {
		return threadLocal.get();
	}

	public static void setSignature(String signature) {
		threadLocal.set(signature);
	}

	public static void removeSignature() {
		threadLocal.remove();
	}
}
