/*
 * 
 * Copyright 2019 FMARSLAN
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package com.fmarslan.spring.base.common.exceptions;

public class PermissionAccessDenied extends AbstractException {

	private static final long serialVersionUID = 2865499046396533106L;

	public PermissionAccessDenied() {
	}

	public PermissionAccessDenied(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PermissionAccessDenied(String message, Throwable cause) {
		super(message, cause);
	}

	public PermissionAccessDenied(String message) {
		super(message);
	}

	public PermissionAccessDenied(String message, Object... params) {
		super(message, params);
	}

	public PermissionAccessDenied(Throwable cause) {
		super(cause);
	}

	@Override
	public String getCode() {
		return "PERMISSION_ACCESS_DENIED";
	}

}
