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
package com.fmarslan.spring.base.common.application;

import java.util.HashMap;
import java.util.Map;
import com.fmarslan.spring.base.common.shared.ICurrentUserDetail;

public class AppContext {

  static ThreadLocal<Map<String, String>> requestData = new ThreadLocal<>();

  private AppContext() {}

  public static Map<String, String> put(String key, String value) {
    if (requestData.get() == null)
      requestData.set(new HashMap<String, String>());
    requestData.get().put(key, value);
    return requestData.get();
  }

  public static String get(String key) {
    if (requestData.get() == null)
      requestData.set(new HashMap<String, String>());
    return requestData.get().get(key);
  }

  static ICurrentUserDetail currentUserHandler;

  public static void setCurrentUserHandler(ICurrentUserDetail currentUserHandler) {
    AppContext.currentUserHandler = currentUserHandler;
  }

  public static ICurrentUserDetail getCurrentUserHandler() {
    return currentUserHandler;
  }

}
