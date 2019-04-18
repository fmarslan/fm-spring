/**
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

package com.fmarslan.spring.base.application.handler;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.fmarslan.spring.base.common.exceptions.AbstractException;
import com.fmarslan.spring.base.common.response.ResponseModel;
import com.fmarslan.spring.base.common.shared.IExceptionParser;
import com.fmarslan.spring.base.common.shared.ITranslateService;

/**
 * @author fmarslan
 *
 */
@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

  @Autowired(required = false)
  ITranslateService translateService;

  @Autowired(required = false)
  IExceptionParser parserService;

  @ExceptionHandler({Throwable.class, RuntimeException.class})
  public final ResponseEntity<Object> handle(Exception ex, WebRequest request) throws Exception {
    return handleExceptionInternal(ex, null, null, null, request);
  }

  @PostConstruct
  public void init() {
    if (parserService == null)
      parserService = new DefaultExceptionParser(translateService);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler#
   * handleExceptionInternal(java.lang.Exception, java.lang.Object,
   * org.springframework.http.HttpHeaders, org.springframework.http.HttpStatus,
   * org.springframework.web.context.request.WebRequest)
   */
  @Override
  protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
      HttpHeaders headers, HttpStatus status, WebRequest request) {

    ResponseModel<Object> response = null;
    Throwable e = getCause(ex);
    if (e instanceof AbstractException)
      response = handle((AbstractException) e);
    else
      response = handle(e);
    return new ResponseEntity<>(response, headers, HttpStatus.valueOf(response.getStatus()));
  }


  private Throwable getCause(Throwable throwable) {
    if (throwable.getCause() != null)
      return getCause(throwable.getCause());
    else
      return throwable;
  }

  public ResponseModel<Object> handle(Throwable e) {
    return parserService.parse(e);
  }

}
