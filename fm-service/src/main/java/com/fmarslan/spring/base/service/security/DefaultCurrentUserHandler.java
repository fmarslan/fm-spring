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

package com.fmarslan.spring.base.service.security;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import com.fmarslan.spring.base.common.shared.ICurrentUserDetail;

/**
 * @author fmarslan
 *
 */
public class DefaultCurrentUserHandler implements ICurrentUserDetail {

  private static Logger logger = LoggerFactory.getLogger(ICurrentUserDetail.class);

  /**
   * 
   */
  public DefaultCurrentUserHandler() {
    logger.debug(DefaultCurrentUserHandler.class.getName(), " initialized");
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.fmarslan.spring.base.common.shared.ICurrentUserDetail#getCurrentUserAuths ()
   */
  @Override
  public List<String> getCurrentUserAuths() {
    return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
        .map(x -> x.getAuthority()).collect(Collectors.toList());
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.fmarslan.spring.base.common.shared.ICurrentUserDetail#getCurrentUserName( )
   */
  @Override
  public String getCurrentUserName() {
    return SecurityContextHolder.getContext().getAuthentication().getName();
  }

}
