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

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author fmarslan
 *
 */
public class CurrentUserDetails implements UserDetails {

  private static final long serialVersionUID = -4719286451387012185L;
  private String username;
  private Collection<? extends GrantedAuthority> auths;
  private String password;
  private boolean accountNonExpired = true;
  private boolean accountNonLocked = true;
  private boolean credentialsNonExpired = true;
  private boolean enabled = true;

  public CurrentUserDetails(String username, Collection<? extends GrantedAuthority> auths,
      String password) {
    this.username = username;
    this.auths = auths;
    this.password = password;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
   */
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return auths;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.security.core.userdetails.UserDetails#getPassword()
   */
  @Override
  public String getPassword() {
    return password;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.security.core.userdetails.UserDetails#getUsername()
   */
  @Override
  public String getUsername() {
    return username;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired ()
   */
  @Override
  public boolean isAccountNonExpired() {
    return accountNonExpired;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked( )
   */
  @Override
  public boolean isAccountNonLocked() {
    return accountNonLocked;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.security.core.userdetails.UserDetails# isCredentialsNonExpired()
   */
  @Override
  public boolean isCredentialsNonExpired() {
    return credentialsNonExpired;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.springframework.security.core.userdetails.UserDetails#isEnabled()
   */
  @Override
  public boolean isEnabled() {
    return enabled;
  }

  public Collection<? extends GrantedAuthority> getAuths() {
    return auths;
  }

  public void setAuths(Collection<? extends GrantedAuthority> auths) {
    this.auths = auths;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setAccountNonExpired(boolean accountNonExpired) {
    this.accountNonExpired = accountNonExpired;
  }

  public void setAccountNonLocked(boolean accountNonLocked) {
    this.accountNonLocked = accountNonLocked;
  }

  public void setCredentialsNonExpired(boolean credentialsNonExpired) {
    this.credentialsNonExpired = credentialsNonExpired;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

}
