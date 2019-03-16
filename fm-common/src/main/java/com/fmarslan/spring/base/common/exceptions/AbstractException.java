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

public abstract class AbstractException extends RuntimeException {

	private static final long serialVersionUID = 8529479987398884574L;

	public AbstractException() {
		super();
	}

	public AbstractException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AbstractException(String message, Throwable cause) {
		super(message, cause);
	}

	public AbstractException(String message) {
		super(message);
	}

	public AbstractException(String message, Object... params) {
		super(String.format(message, params));
	}

	public AbstractException(Throwable cause) {
		super(cause);
	}

	public abstract String getCode();

	@Override
	public String getMessage() {
		return String.format("[%S] - %s", getCode(), super.getMessage());
	}

	@Override
	public String toString() {
		return getMessage();
	}

}
