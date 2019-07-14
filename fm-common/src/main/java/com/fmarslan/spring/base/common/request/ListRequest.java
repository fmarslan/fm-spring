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
package com.fmarslan.spring.base.common.request;

import java.io.Serializable;
import java.util.Map;
import com.fmarslan.spring.base.common.application.AppConfig;

public class ListRequest implements Serializable {

  private static final long serialVersionUID = 6795035057074563871L;

  private Integer offset = 0;
  private boolean onlyCount = false;
  private boolean onlyData = false;
  private Integer size = AppConfig.getPageSize();
  private Map<String, Object> filter;
  private Map<String, EnumOrderType> order;

  public ListRequest() {}

  public ListRequest(Integer offset, Integer count, Map<String, Object> filter,
      Map<String, EnumOrderType> order) {
    super();
    this.offset = offset;
    this.size = count;
    this.filter = filter;
    this.order = order;
  }

  public Integer getOffset() {
    return offset;
  }

  public void setOffset(Integer offset) {
    this.offset = offset;
  }

  public Integer getSize() {
    return size;
  }

  public void setSize(Integer count) {
    this.size = count;
  }

  public Map<String, Object> getFilter() {
    return filter;
  }

  public void setFilter(Map<String, Object> filter) {
    this.filter = filter;
  }

  public Map<String, EnumOrderType> getOrder() {
    return order;
  }

  public void setOrder(Map<String, EnumOrderType> order) {
    this.order = order;
  }

  /**
   * @return the onlyCount
   */
  public boolean isOnlyCount() {
    return onlyCount;
  }

  /**
   * @param onlyCount the onlyCount to set
   */
  public void setOnlyCount(boolean onlyCount) {
    this.onlyCount = onlyCount;
  }

  /**
   * @return the onlyData
   */
  public boolean isOnlyData() {
    return onlyData;
  }

  /**
   * @param onlyData the onlyData to set
   */
  public void setOnlyData(boolean onlyData) {
    this.onlyData = onlyData;
  }

}
