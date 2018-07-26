package com.data.system.test.domain;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;

public class CommandHelloWorld extends HystrixCommand<String> {

	private final String name;

	public CommandHelloWorld(String name) {
		super(HystrixCommand.Setter
				//设置GroupKey用于dashboard分组展示
				.withGroupKey(HystrixCommandGroupKey.Factory.asKey(name))
				//设置commandKey，用户隔离线程池，不同的commandKey会使用不同的线程池
				.andCommandKey(HystrixCommandKey.Factory.asKey("hello" + name))
				//设置线程池名字的前缀，默认使用commandKey
				.andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("hello$Pool" + name))
				//设置线程池相关参数
				.andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
						.withCoreSize(15)
						.withMaxQueueSize(10)
						.withQueueSizeRejectionThreshold(2))
				//设置command相关参数
				.andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
						//开启熔断器机制
						.withCircuitBreakerEnabled(true)
						//舱壁隔离策略
						.withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
						//circuitBreaker打开多久后关闭
						.withCircuitBreakerSleepWindowInMilliseconds(5000)));
		this.name = name;
	}

	@Override
	protected String run() throws Exception {
		return "Hello" + name + "!";
	}
}
