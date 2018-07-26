package com.data.system.test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.data.system.command.UserInfoServiceCommand;
import com.data.system.service.UserInfoService;
import com.data.system.test.domain.CommandHelloWorld;
import com.data.system.test.domain.ObservableCommandHelloWorld;
import com.netflix.hystrix.HystrixCommand;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:/config/spring-mybatis.xml" })
public class TestHystrixCommand {
	Logger logger = LoggerFactory.getLogger(TestHystrixCommand.class);
	@Autowired
	private UserInfoService userInfoService;

//	@Test
	public void testExecuteCommand() throws InterruptedException {
		int i = 2;
		for (; i < 15; i++) {
			HystrixCommand<Integer> command = new UserInfoServiceCommand(userInfoService, i, "" + i);
			Integer r = command.execute();
			String method = r == -1 ? "fallback" : "run";
			logger.error("call {} times,result:{},method:{},isCircuitBreakerOpen:{}", i, r, method, command.isCircuitBreakerOpen());
		}
		// 等待6s，使得熔断器进入半打开状态
		Thread.sleep(6000);
		for (; i < 23; i++) {
			HystrixCommand<Integer> command = new UserInfoServiceCommand(userInfoService, i, "" + i);
			Integer r = command.execute();
			String method = r == -1 ? "fallback" : "run";
			logger.error("call {} times,result:{},method:{},isCircuitBreakerOpen:{}", i, r, method, command.isCircuitBreakerOpen());
		}
	}
	@Test
	public void testSynchronous() throws InterruptedException, ExecutionException {
		String commandHelloWorld = new CommandHelloWorld("World").execute();
		logger.info(commandHelloWorld);
		String commandHelloBob = new CommandHelloWorld("Bob").execute();
		logger.info(commandHelloBob);
		Future<String> fs = new CommandHelloWorld("Tom").queue();
		String commandTom  = fs.get();
		logger.info(commandTom);
//		String ObservableCommandHelloWorld = new ObservableCommandHelloWorld("Bob").observe(;
	}
}
