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

package com.fmarslan.spring.base.application.security;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author fmarslan
 *
 */
public class JwtConfig {
  @Value("${security.jwt.uri:/login/**}")
  private String Uri;

  @Value("${security.jwt.header:Authorization}")
  private String header;

  @Value("${security.jwt.prefix:Bearer }")
  private String prefix;

  @Value("${security.jwt.expiration:#{5}}")
  private int expiration;

  @Value("${security.jwt.secret:FMARSLAN}")
  private String secret;

  public String getUri() {
    return Uri;
  }

  public void setUri(String uri) {
    Uri = uri;
  }

  public String getHeader() {
    return header;
  }

  public void setHeader(String header) {
    this.header = header;
  }

  public String getPrefix() {
    return prefix;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  public int getExpiration() {
    return expiration;
  }

  public void setExpiration(int expiration) {
    this.expiration = expiration;
  }

  public String getSecret() {
    return secret;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }

}
