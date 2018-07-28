package com.data.system.command;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolProperties;

/**
 * 熔断器
 * 
 * @author 小二狗
 *
 */
public class ControllerHystrixCommand extends HystrixCommand<String> {

	private final static Logger logger = LoggerFactory.getLogger(ControllerHystrixCommand.class);

	private ProceedingJoinPoint joinPint;
	

	public ControllerHystrixCommand(ProceedingJoinPoint joinPint) {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(joinPint.getSignature().toString()))
				.andCommandKey(HystrixCommandKey.Factory.asKey(joinPint.getSignature().toString()))
				.andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
						.withCircuitBreakerRequestVolumeThreshold(10)// 至少有10个请求，熔断器才会进行错误率的计算
						.withCircuitBreakerErrorThresholdPercentage(60)// 错误率达到50开启熔断保护
						.withCircuitBreakerSleepWindowInMilliseconds(5000)// 熔断器中断请求5s后会进入半打开状态，放部分流量过去重试
						.withExecutionIsolationThreadInterruptOnTimeout(true)
						.withExecutionTimeoutEnabled(true)//true，即run运行时间
						.withExecutionTimeoutInMilliseconds(2000)
						.withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD))
				.andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
						.withCoreSize(10)
						.withMaxQueueSize(10)));
		// super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("userInfoService"))
		// .andCommandKey(HystrixCommandKey.Factory.asKey("getUserInfo"))
		// .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
		// .withCircuitBreakerRequestVolumeThreshold(10)////至少有10个请求，熔断器才进行错误率的计算
		// .withCircuitBreakerSleepWindowInMilliseconds(5000)//熔断器中断请求5秒后会进入半打开状态,放部分流量过去重试
		// .withCircuitBreakerErrorThresholdPercentage(50)//错误率达到50开启熔断保护
		// .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
		// .withExecutionIsolationSemaphoreMaxConcurrentRequests(10)));//最大并发请求量

		this.joinPint = joinPint;
	}

	@Override
	protected String run() {
		String result = null;
		try {
			result = (String) joinPint.proceed();
		} catch (Throwable e) {
			logger.error("ControllerHystrixCommand run ERROR:" +e.getMessage(),e);
		}
		return result;
	}
	@Override
	protected String getFallback() {
		String method = joinPint.getSignature().toString();
		Object[] args = joinPint.getArgs();
		logger.error("method:" + method + ",args:" + args.toString() + " getFallBack");
		return  null;
	}
}
