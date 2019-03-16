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
package com.fmarslan.spring.base.persistence.query;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class CustomNativeQuery implements CustomQueryBase {

	private String query;
	private IParameterBinder parameterBinder;

	public CustomNativeQuery(String query, IParameterBinder parameterBinder) {
		this.query = query;
		this.parameterBinder = parameterBinder;
	}

	public CustomNativeQuery(String query) {
		this(query, null);
	}

	public <T> Query createQuery(EntityManager em, Class<T> clazz) {
		Query _query = em.createNativeQuery(query, clazz);
		if (parameterBinder != null) {
			parameterBinder.bind(_query);
		}
		return _query;
	}
}
