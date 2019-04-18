/*
 * 
 * Copyright 2019 FMARSLAN
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 * 
 */
package com.fmarslan.spring.base.service.security;

import java.lang.reflect.Method;
import javax.annotation.PostConstruct;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import com.fmarslan.spring.base.common.application.AppContext;
import com.fmarslan.spring.base.common.exceptions.PermissionAccessDenied;

@Aspect
@Configuration
public class SecurityAspect {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @PostConstruct
  private void init() {
    AppContext.setCurrentUserHandler(new DefaultCurrentUserHandler());
  }

  @Around("@annotation(org.springframework.stereotype.Service)")
  public void around(ProceedingJoinPoint joinPoint) throws Throwable {

    long startTime = System.currentTimeMillis();

    if (AppContext.getCurrentUserHandler() != null) {

      MethodSignature signature = (MethodSignature) joinPoint.getSignature();
      Method method = signature.getMethod();

      Auth authTag = method.getAnnotation(Auth.class);

      if (authTag.value().equals(Auth.BLOCKED))
        throw new PermissionAccessDenied(authTag.value());

      if (authTag.value().equals(Auth.UNSECURED) == false && AppContext.getCurrentUserHandler()
          .getCurrentUserAuths().contains(authTag.value()) == false)
        throw new PermissionAccessDenied(authTag.value());
    }

    joinPoint.proceed();

    long timeTaken = System.currentTimeMillis() - startTime;

    logger.debug("Time Taken by {} is {}", joinPoint, timeTaken);
  }

}
