package com.data.system.test;

import org.junit.Test;

import com.data.system.test.domain.CommandRunTimeOut;

public class TestHystrixCommand1 {
	@Test
	public void test1() throws InterruptedException {
		for (int i = 0; i < 20; i++) {
			String name = new CommandRunTimeOut("command", i).execute();
			System.out.println("第" + i + "个处理结果：" + name);
			Thread.sleep(1000);
		}
	}
	
}
