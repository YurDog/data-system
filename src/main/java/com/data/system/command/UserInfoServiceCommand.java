package com.data.system.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.data.system.po.UserInfo;
import com.data.system.service.UserInfoService;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPool.HystrixThreadPoolDefault;
import com.netflix.hystrix.HystrixThreadPoolProperties;

public class UserInfoServiceCommand extends HystrixCommand<Integer> {

	private final static Logger logger = LoggerFactory.getLogger(UserInfoServiceCommand.class);

	private UserInfoService userInfoService;

	private Integer id;

	private String phone;

	public UserInfoServiceCommand(UserInfoService userInfoService, Integer id, String phone) {

		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("userInfoService")).andCommandKey(HystrixCommandKey.Factory.asKey("getUserInfo")).andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withCircuitBreakerRequestVolumeThreshold(10)// 至少有10个请求，熔断器才会进行错误率的计算
				.withCircuitBreakerErrorThresholdPercentage(50)// 错误率达到50开启熔断保护
				.withCircuitBreakerSleepWindowInMilliseconds(2000)// 熔断器中断请求5s后会进入半打开状态，放部分流量过去重试
				.withExecutionTimeoutEnabled(true)).andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(1)));
//		 super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("userInfoService")) .andCommandKey(HystrixCommandKey.Factory.asKey("getUserInfo")) .andCommandPropertiesDefaults(HystrixCommandProperties.Setter() .withCircuitBreakerRequestVolumeThreshold(10)////至少有10个请求，熔断器才进行错误率的计算 
//				 .withCircuitBreakerSleepWindowInMilliseconds(5000)//熔断器中断请求5秒后会进入半打开状态,放部分流量过去重试 
//				 .withCircuitBreakerErrorThresholdPercentage(50)//错误率达到50开启熔断保护 
//				 .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE) .withExecutionIsolationSemaphoreMaxConcurrentRequests(10)));//最大并发请求量


		this.userInfoService = userInfoService;
		this.id = id;
		this.phone = phone;
	}

	@Override
	protected Integer run() throws Exception {
		
		JSONObject result = userInfoService.getUserInfo(id, phone);
		if (result != null) {
			return 1;
		}
		return -1;
	}

	@Override
	protected Integer getFallback() {
		return -1;
	}
}
