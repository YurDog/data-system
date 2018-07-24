package com.data.system.util;

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
