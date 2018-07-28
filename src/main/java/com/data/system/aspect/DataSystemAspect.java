package com.data.system.aspect;

import java.util.Arrays;
import java.util.UUID;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.data.system.annotation.FileLog;
import com.data.system.command.ControllerHystrixCommand;
import com.data.system.util.RequestChainSignature;
import com.data.system.util.ResponseFormatUtil;

/**
 * Spring AOP 拦截接口请求，加入熔断器
 * 
 * @author 小二狗
 *
 */
// @Aspect
// @Component
public class DataSystemAspect {

	Logger logger = LoggerFactory.getLogger(DataSystemAspect.class);

	// @Pointcut("execution(** com.data.system.controller..*.*(..))")
	public void ControllerExecution() {

	}

	// @Around("ControllerExecution()")
	public String aroundController(ProceedingJoinPoint joinPoint) throws Throwable {
		long beginTime = System.currentTimeMillis();
		Object[] args = joinPoint.getArgs();
		Signature signature = joinPoint.getSignature();
		String key = UUID.randomUUID().toString();
		RequestChainSignature.setSignature(key);
		String method = signature.toString();
		logger.info(getLogInfo(method, "参数", Arrays.toString(args)));
		String result = null;
		try {
			result = new ControllerHystrixCommand(joinPoint).execute();
			if (result == null) {// 说明接口报错,或FallBack
				result = ResponseFormatUtil.error().toJSONString();
			}
		} catch (Exception e) {
			logger.error(getLogInfo(method, "异常", result));
			result = ResponseFormatUtil.error().toJSONString();
		}
		logger.info(getLogInfo(method, "结果", result));
		logger.info(getLogInfo(method, "时间", (System.currentTimeMillis() - beginTime)));
		RequestChainSignature.removeSignature();
		return result;
	}

	private String getLogInfo(String method, String message, Object value) {
		StringBuffer buffer = new StringBuffer();
		if (value instanceof Exception) {
			Throwable throwable = (Throwable) value;
			logger.error(RequestChainSignature.getSignature() + " ", throwable);
			value = throwable.toString();
		}
		buffer.append(RequestChainSignature.getSignature()).append(" ").append(method).append(" ").append(message).append(" : ").append(value.toString());
		return buffer.toString();
	}

//	public void addSuccessLog(JoinPoint joinPoint, FileLog log) {
//		Object[] args = joinPoint.getArgs();
//		// 获取目标方法体参数
//		String className = joinPoint.getTarget().getClass().toString();
//		// 获取目标类名
//		String signature = joinPoint.getSignature().toString();
//		// 获取目标方法的签名
//		String methodName = signature.substring(signature.lastIndexOf(".") + 1, signature.indexOf("("));
//		// 获取注解值
//		String desc = log.value();
//		logger.info("asdasdassssssssssssssssssssssssssssssssssssssssssssssssssssssssss");
//	}

}
