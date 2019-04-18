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

package com.fmarslan.spring.base.application.filter;

import java.io.IOException;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.filter.GenericFilterBean;
import com.fmarslan.spring.base.common.application.AppConstant;
import com.fmarslan.spring.base.common.exceptions.NotSupportException;

/**
 * @author fmarslan
 *
 */
public class LocalizationFilter extends GenericFilterBean {


  private String defaultLanguage;
  private List<String> acceptLanguages;

  public LocalizationFilter(String defaultLanguage, List<String> acceptLanguages) {
    this.acceptLanguages = acceptLanguages;
    this.defaultLanguage = defaultLanguage;

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    String lang = ((HttpServletRequest) request).getHeader(AppConstant.ACCEPT_LANGUAGE);
    if (lang == null)
      lang = defaultLanguage;
    if (acceptLanguages.contains(lang) == false)
      throw new NotSupportException(String.format("%s language is not supported.", lang));
    chain.doFilter(request, response);
  }

}
