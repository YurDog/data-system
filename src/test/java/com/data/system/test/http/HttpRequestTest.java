package com.data.system.test.http;

import org.junit.Test;

import com.data.system.util.HttpClientUtil;

public class HttpRequestTest {
	/**
	 * 请求项目接口，测试熔断器
	 * @throws InterruptedException 
	 */
	@Test
	public void test() throws InterruptedException {
		for (int i = 0; i < 40; i++) {
			Thread.sleep(1000);
			StringBuffer buffer = new StringBuffer();
			buffer.append("http://localhost:8080/data-system/my/test?id=").append(i).append("&phone=1");
			String result = HttpClientUtil.doGet(buffer.toString());
			System.out.println("第" + i + "个请求返回结果：" + result);
		}

	}
}
