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
package com.fmarslan.spring.base.common.response;

import java.io.Serializable;

public class ResponseModel<T> implements Serializable {

	private static final long serialVersionUID = -3467927893854857910L;

	private T data;
	private String exception;
	private String exceptionCode;
	private String message;
	private int status;

	public static <T> ResponseModel<T> create() {
		return new ResponseModel<T>();
	}

	public ResponseModel() {
	}

	public ResponseModel(T data, String exception, String exceptionCode, String message, int status) {
		super();
		this.data = data;
		this.exception = exception;
		this.exceptionCode = exceptionCode;
		this.message = message;
		this.status = status;
	}

	public T getData() {
		return data;
	}

	public ResponseModel<T> setData(T data) {
		this.data = data;
		return this;
	}

	public String getException() {
		return exception;
	}

	public ResponseModel<T> setException(String exception) {
		this.exception = exception;
		return this;
	}

	public String getExceptionCode() {
		return exceptionCode;
	}

	public ResponseModel<T> setExceptionCode(String exceptionCode) {
		this.exceptionCode = exceptionCode;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public ResponseModel<T> setMessage(String message) {
		this.message = message;
		return this;
	}

	public int getStatus() {
		return status;
	}

	public ResponseModel<T> setStatus(int status) {
		this.status = status;
		return this;
	}
}
