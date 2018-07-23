package com.data.system.config;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.SourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSystemAspect {

	Logger logger = LoggerFactory.getLogger(DataSystemAspect.class);

	@Around("execution(** com.data.system.controller..*.*(..))")
	public Object aroundController(JoinPoint joinPoint) throws Throwable {
		long beginTime = System.currentTimeMillis();
		ProceedingJoinPoint proceedingJoinPoint = (ProceedingJoinPoint) joinPoint;
		Object[] args = proceedingJoinPoint.getArgs();
		logger.info("接口参数：" + Arrays.toString(args));
		String kind = proceedingJoinPoint.getKind();
		Signature signature = proceedingJoinPoint.getSignature();
		SourceLocation sourceLocation = proceedingJoinPoint.getSourceLocation();
		StaticPart staticPart = proceedingJoinPoint.getStaticPart();
		Object target = proceedingJoinPoint.getTarget();
		String string = proceedingJoinPoint.getThis().toString();
		Object object = proceedingJoinPoint.proceed();
		logger.info("接口返回值：" + object.toString());
		logger.info("接口响应：" + (System.currentTimeMillis() - beginTime));
		return object;
	}
}
