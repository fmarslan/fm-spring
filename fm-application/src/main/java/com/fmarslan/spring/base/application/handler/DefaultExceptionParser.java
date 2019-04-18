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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fmarslan.spring.base.common.exceptions.AbstractException;
import com.fmarslan.spring.base.common.response.ResponseModel;
import com.fmarslan.spring.base.common.shared.IExceptionParser;
import com.fmarslan.spring.base.common.shared.ITranslateService;

/**
 * @author fmarslan
 *
 */
public class DefaultExceptionParser implements IExceptionParser {

  private Logger logger = LoggerFactory.getLogger(DefaultExceptionParser.class);
  
  protected final ITranslateService translateService;

  public DefaultExceptionParser(ITranslateService translateService) {
    this.translateService = translateService;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.fmarslan.spring.base.common.shared.IExceptionParser#parse(java.lang.Throwable)
   */
  @Override
  public ResponseModel<Object> parse(Throwable throwable) {
    if (throwable instanceof AbstractException) {
      return handle((AbstractException) throwable);
    } else {
      return handle(throwable);
    }
  }

  public ResponseModel<Object> handle(AbstractException e) {
    if (translateService != null)
      e.translate(x -> translateService.translate(x));
    return new ResponseModel<Object>().setException(e.getMessage()).setExceptionCode(e.getCode())
        .setStatus(e.getStatusCode());
  }

  private ResponseModel<Object> handle(Throwable e) {
    String message;
    if (translateService != null)
      message = translateService.translate(e.getMessage());
    else
      message = e.getMessage();
    logger.error("{}", e);
    return new ResponseModel<Object>().setException(message).setExceptionCode("UNDEFINED")
        .setStatus(400);
  }


}
