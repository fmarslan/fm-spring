package com.fmarslan.base.service.security;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.fmarslan.base.common.configuration.AppContext;
import com.fmarslan.base.common.exceptions.PermissionAccessDenied;

@Aspect
@Configuration
public class SecurityAspect {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Around("@annotation(org.springframework.stereotype.Service)")
	public void around(ProceedingJoinPoint joinPoint) throws Throwable {

		long startTime = System.currentTimeMillis();

		if (AppContext.getCurrentUserHandler() != null) {

			MethodSignature signature = (MethodSignature) joinPoint.getSignature();
			Method method = signature.getMethod();

			Auth authTag = method.getAnnotation(Auth.class);

			if (authTag.value().equals(Auth.BLOCKED))
				throw new PermissionAccessDenied(authTag.value());

			if (authTag.value().equals(Auth.UNSECURED) == false
					&& AppContext.getCurrentUserHandler().getCurrentUserAuths().contains(authTag.value()) == false)
				throw new PermissionAccessDenied(authTag.value());
		}

		joinPoint.proceed();

		long timeTaken = System.currentTimeMillis() - startTime;

		logger.debug("Time Taken by {} is {}", joinPoint, timeTaken);
	}

}
