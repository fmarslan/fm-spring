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

package com.fmarslan.spring.base.common.dto;

import java.io.Serializable;
import com.fmarslan.spring.base.common.shared.IHasPrimaryKey;
import io.swagger.annotations.ApiParam;

public abstract class BaseDto<ID extends Serializable> implements IHasPrimaryKey<ID>, Serializable {

  private static final long serialVersionUID = 450802434669437310L;
  @ApiParam(hidden = true)
  private String createdUser;
  @ApiParam(hidden = true)
  private Long createdAt;
  @ApiParam(hidden = true)
  private String updatedUser;
  @ApiParam(hidden = true)
  private Long updatedAt;
  @ApiParam(hidden = true)
  private int version;

  public String getCreatedUser() {
    return createdUser;
  }

  public void setCreatedUser(String createdUser) {
    this.createdUser = createdUser;
  }

  public Long getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Long createdAt) {
    this.createdAt = createdAt;
  }

  public String getUpdatedUser() {
    return updatedUser;
  }

  public void setUpdatedUser(String updatedUser) {
    this.updatedUser = updatedUser;
  }

  public Long getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Long updatedAt) {
    this.updatedAt = updatedAt;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

}
