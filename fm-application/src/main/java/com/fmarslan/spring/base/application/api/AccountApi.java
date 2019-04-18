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

package com.fmarslan.spring.base.application.api;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fmarslan.spring.base.application.security.JwtConfig;
import com.fmarslan.spring.base.application.security.JwtTokenProvider;
import com.fmarslan.spring.base.common.application.MessageLabels;
import com.fmarslan.spring.base.common.exceptions.NotSupportException;
import com.fmarslan.spring.base.common.request.LoginRequest;
import com.fmarslan.spring.base.common.response.LoginResponse;
import com.fmarslan.spring.base.common.response.ResponseModel;

/**
 * @author fmarslan
 *
 */
@RestController
@RequestMapping("/api/account")
public class AccountApi {

  @Autowired(required = false)
  private AuthenticationManager authenticationManager;

  @Autowired
  JwtTokenProvider tokenProvider;

  @Autowired
  JwtConfig jwtConfig;

  @PostMapping(path="login")
  public ResponseModel<LoginResponse> authenticateUser(
      @Valid @RequestBody LoginRequest loginRequest) {
    if (authenticationManager != null) {
      Authentication authentication =
          authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
              loginRequest.getUsername(), loginRequest.getPassword()));

      SecurityContextHolder.getContext().setAuthentication(authentication);
      String token = tokenProvider.generateToken(authentication);
      return new ResponseModel<LoginResponse>()
          .setData(new LoginResponse(jwtConfig.getPrefix(), token));
    } else {
      throw new NotSupportException(MessageLabels.NOT_SUPPORTED);
    }
  }

}
