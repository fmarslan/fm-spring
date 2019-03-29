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
package com.fmarslan.spring.base.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import com.fmarslan.spring.base.common.request.ListRequest;
import com.fmarslan.spring.base.common.response.ListModel;
import com.fmarslan.spring.base.persistence.AbstractRepository;
import com.fmarslan.spring.base.persistence.BaseEntity;

public abstract class AbstractLogic<ID extends Serializable, ENTITY extends BaseEntity<ID>> {

	protected abstract AbstractRepository<ID, ENTITY> getRepository();

	public AbstractLogic() {
	}

	public ENTITY create(ENTITY entity) {
		return getRepository().create(entity);
	}

	public ENTITY update(ENTITY entity) {
		return getRepository().update(entity);
	}

	public void delete(ENTITY entity, boolean hardDelete) {
		getRepository().delete(entity, hardDelete);
	}

	public Optional<ENTITY> findByID(ID id) {
		return getRepository().findByID(id);
	}

	public List<ENTITY> getAll() {
		return getRepository().getAll();
	}

	public List<ENTITY> update(List<ENTITY> entities) {
		return getRepository().update(entities);
	}

	public List<ENTITY> create(List<ENTITY> entities) {
		return getRepository().create(entities);
	}

	public void delete(List<ENTITY> entities, boolean hardDelete) {
		getRepository().delete(entities, hardDelete);
	}

	public void delete(List<ENTITY> entities) {
		getRepository().delete(entities);
	}

	public void delete(ENTITY entity) {
		getRepository().delete(entity);
	}

	public ListModel<ENTITY> getPage(ListRequest request) {
		return getRepository().getPage(request);
	}

}
