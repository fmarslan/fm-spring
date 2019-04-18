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
package com.fmarslan.spring.base.common.exceptions;

import java.util.Arrays;
import java.util.function.Function;

public abstract class AbstractException extends RuntimeException {

  private static final long serialVersionUID = 8529479987398884574L;

  private String message;
  private Object[] params;
  private Function<String, String> translateFunction;

  public AbstractException() {
    super();
  }

  public AbstractException(String message, Throwable cause) {
    super(message, cause);
    this.message = message;
  }

  public AbstractException(String message) {
    super(message);
    this.message = message;
  }

  public AbstractException(String message, Object... params) {
    super(message);
    this.message = message;
    this.params = params;
  }

  public AbstractException(Throwable cause) {
    super(cause);
    this.message = cause.getMessage();
  }

  public abstract String getCode();

  public void translate(Function<String, String> translateFunction) {
    this.translateFunction = translateFunction;
  }

  public int getStatusCode() {
    return 400;
  }

  @Override
  public String getMessage() {
    if (translateFunction != null) {
      message = translateFunction.apply(message);
      Arrays.stream(params).forEach(x -> {
        if (x instanceof String)
          x = translateFunction.apply((String) x);
      });
    }
    if (params != null && params.length > 0)
      return String.format(message, params);
    else
      return message;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Throwable#getLocalizedMessage()
   */
  @Override
  public String getLocalizedMessage() {
    return getMessage();
  }

  @Override
  public String toString() {
    return getMessage();
  }

}
