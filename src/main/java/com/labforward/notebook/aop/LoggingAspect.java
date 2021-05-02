package com.labforward.notebook.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author apurva.patil
 * 
 * This class is used to declare aspects for logging around method call,
 * after exception.
 *
 */
@Aspect
@Component
@Scope(scopeName = "request")
public class LoggingAspect {

	@Around("execution(* com.labforward.notebook.controller..*(..)))")
	public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		return LoggingJoinpoint.aroundController(proceedingJoinPoint);
	}

	
}
