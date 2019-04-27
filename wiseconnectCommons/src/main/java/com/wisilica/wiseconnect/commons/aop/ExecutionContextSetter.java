package com.wisilica.wiseconnect.commons.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.wisilica.wiseconnect.commons.ExecutionContextHolder;
import com.wisilica.wiseconnect.commons.annotation.ExecutionContext;

@Aspect
@Order(0)
@Component
public class ExecutionContextSetter {

	@Around("execution(* com.wisilica.wiseconnect.core.service.*.*(..)) && @annotation(com.wisilica.wiseconnect.commons.annotation.ExecutionContext)")
	public Object executionContextSetAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {		
		ExecutionContext executionContext = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod()
				.getAnnotation(ExecutionContext.class);
		boolean datasourceSet = false;
		if(ExecutionContextHolder.getDatasourceType() == null) {
			ExecutionContextHolder.setDatasourceType(executionContext.datasourceType());
			datasourceSet = true;
		}
		try {
			return proceedingJoinPoint.proceed();
		}finally {
			if(datasourceSet) {
				ExecutionContextHolder.clearDatasourceType();
			}
		}
	}
}
