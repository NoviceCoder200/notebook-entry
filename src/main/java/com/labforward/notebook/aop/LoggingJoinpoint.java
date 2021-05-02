package com.labforward.notebook.aop;

import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author apurva.patil
 * 
 * This class is used to implement joinpoints for the aspects.
 *
 */
@Aspect
@Configuration
public class LoggingJoinpoint {
	private static String CORRELATION_ID = "correlationId";

	protected static Logger getLog(JoinPoint joinPoint) {
		return LoggerFactory.getLogger(joinPoint.getTarget().getClass());
	}

	static Object aroundController(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		Logger LOGGER = getLog(proceedingJoinPoint);

		String className = proceedingJoinPoint.getTarget().getClass().getSimpleName();
		String methodName = proceedingJoinPoint.getSignature().getName();
		MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
		String correlationId=getHeader(signature.getParameterNames());

		final StopWatch stopWatch = new StopWatch();

		// Start time
		stopWatch.start();
		Object result;
		try {
			result = proceedingJoinPoint.proceed();
			stopWatch.stop();

			// Log method execution time
			String id=null!=correlationId ? "[Correlation-Id :"+correlationId+"] " : "";
			LOGGER.info(
					id+"Executing " + className + "." + methodName + " " + ":: " + stopWatch.getTotalTimeMillis() + " ms");
			return result;
		} catch (Exception e) {
			LOGGER.error("Exception " + e.getMessage());
			throw e;
		}

	}

	public static String getHeader(String[] parameters) {
		HttpServletRequest request = currentRequest();
		if (Objects.isNull(request)) {
			return null;
		}

		for (String headerName : parameters) {
			String value = request.getHeader(headerName);
			if (headerName.equals(CORRELATION_ID) && StringUtils.hasText(value)) {
				return value;
			}
		}
		return null;

	}

	private static HttpServletRequest currentRequest() {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		return Optional.ofNullable(servletRequestAttributes).map(ServletRequestAttributes::getRequest).orElse(null);
	}

	public static void afterException(JoinPoint jp, Throwable error) throws Throwable {
		Logger LOGGER=getLog(jp);
		try {
			LOGGER.error("### Exception in "+jp.getTarget().getClass().getSimpleName()+"."
					+jp.getSignature().getName()+"():"+error.getClass().getSimpleName());
			LOGGER.error(error.getMessage());
			
		} catch (Exception e) {
			LOGGER.error(error.getMessage());
		}
	}

}
