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
import java.util.ArrayList;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import com.fmarslan.spring.base.application.security.JwtConfig;
import io.jsonwebtoken.Jwts;

/**
 * @author fmarslan
 *
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

  private JwtConfig jwtConfig;

  public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JwtConfig jwtConfig) {
    super(authenticationManager);
    this.jwtConfig = jwtConfig;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
      FilterChain chain) throws IOException, ServletException {
    String header = req.getHeader(jwtConfig.getHeader());

    if (header == null || !header.startsWith(jwtConfig.getPrefix())) {
      chain.doFilter(req, res);
      return;
    }
    UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
    SecurityContextHolder.getContext().setAuthentication(authentication);
    chain.doFilter(req, res);
  }

  private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
    String token = request.getHeader(jwtConfig.getHeader());
    if (token != null) {
      // parse the token.
      String user = Jwts.parser().setSigningKey(jwtConfig.getSecret().getBytes())
          .parseClaimsJws(token.replace(jwtConfig.getPrefix(), "")).getBody().getSubject();

      if (user != null) {
        return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
      }
      return null;
    }
    return null;
  }
}
