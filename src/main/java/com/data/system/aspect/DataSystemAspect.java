package com.data.system.aspect;

import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.SourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.data.system.util.RequestChainSignature;
import com.data.system.util.ResponseFormatUtil;

@Aspect
@Component
public class DataSystemAspect {

	Logger logger = LoggerFactory.getLogger(DataSystemAspect.class);

	// @Before("execution(** com.data.system.controller..*.*(..))")
	// public void beforeController() {
	// logger.info("before controller");
	// }

	@Around("execution(** com.data.system.controller..*.*(..))")
	public String aroundController(JoinPoint joinPoint) throws Throwable {
		long beginTime = System.currentTimeMillis();
		ProceedingJoinPoint proceedingJoinPoint = (ProceedingJoinPoint) joinPoint;
		Object[] args = proceedingJoinPoint.getArgs();
		Signature signature = proceedingJoinPoint.getSignature();
		String key = UUID.randomUUID().toString();
		RequestChainSignature.setSignature(key);
		String method = signature.toString();
		logger.info(getLogInfo(method, "参数", Arrays.toString(args)));
		String result = null;;
		try {
			result = (String) proceedingJoinPoint.proceed();
		} catch (Exception e) {
			logger.info(getLogInfo(method, "异常", e));
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
	// @After("execution(** com.data.system.controller..*.*(..))")
	// public void afterController() {
	// logger.info("after controller");
	// }
	//
	// @AfterReturning("execution(** com.data.system.controller..*.*(..))")
	// public void afterReturnController() {
	// logger.info("afterReturn controller");
	// }
	//
	// @AfterThrowing("execution(** com.data.system.controller..*.*(..))")
	// public void afterThrowController() {
	// logger.info("afterThrow controller error");
	// }
}
