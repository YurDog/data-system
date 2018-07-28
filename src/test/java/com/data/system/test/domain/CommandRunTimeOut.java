package com.data.system.test.domain;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;

public class CommandRunTimeOut extends HystrixCommand<String> {

	private final String name;
	
	private final int index;

	public CommandRunTimeOut(String name,int i) {
		super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(name))
				.andCommandKey(HystrixCommandKey.Factory.asKey(name))
				.andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey(name))
				.andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
						.withCoreSize(10)
						.withMaxQueueSize(10)
						.withQueueSizeRejectionThreshold(2))
				.andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
						.withCircuitBreakerEnabled(true)
						.withCircuitBreakerRequestVolumeThreshold(7)
						.withCircuitBreakerErrorThresholdPercentage(40)
						.withCircuitBreakerSleepWindowInMilliseconds(5000)));
		this.name = name;
		this.index = i;
	}

	@Override
	protected String run() throws Exception {
		if(index % 2 != 0 && (index < 10 || index >16)) {
			int j=0;
			System.out.println(index + "进入死循环");
			while(true) {
				j++;
			}
		}
		return name + "-" + index + "正常";
	}
	@Override
	protected String getFallback() {
		return name +"-" + index+ "错误";
	}
}
