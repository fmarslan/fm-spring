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
import javax.persistence.TypedQuery;

public class CustomNamedQuery implements CustomQueryBase {

	private String query;
	private IParameterBinder parameterBinder;

	public CustomNamedQuery(String query, IParameterBinder parameterBinder) {
		this.query = query;
		this.parameterBinder = parameterBinder;
	}

	public CustomNamedQuery(String query) {
		this(query, null);
	}

	public <T> TypedQuery<T> createQuery(EntityManager em, Class<T> clazz) {
		TypedQuery<T> _query = em.createQuery(query, clazz);
		if (parameterBinder != null) {
			parameterBinder.bind(_query);
		}
		return _query;
	}

}
