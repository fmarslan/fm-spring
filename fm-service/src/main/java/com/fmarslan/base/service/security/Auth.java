package com.fmarslan.base.service.security;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Auth {

	public static final String BLOCKED = "BLOCKED";
	public static final String UNSECURED = "UNSECURED";

	String value() default BLOCKED;
}
