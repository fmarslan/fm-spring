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
import org.springframework.transaction.annotation.Transactional;
import com.fmarslan.spring.base.common.dto.BaseDto;
import com.fmarslan.spring.base.common.exceptions.EntityNotExistsException;
import com.fmarslan.spring.base.common.request.ListRequest;
import com.fmarslan.spring.base.common.response.ListModel;
import com.fmarslan.spring.base.persistence.BaseEntity;
import com.fmarslan.spring.base.service.security.Auth;

public abstract class AbstractService<ID extends Serializable, DTO extends BaseDto<ID>, ENTITY extends BaseEntity<ID>> {

//	private Logger logger = LoggerFactory.getLogger(this.getClass());


	Class<DTO> dtoClazz;
	
	public AbstractService(Class<DTO> dtoClazz) {
		this.dtoClazz = dtoClazz;
	}
	
	protected abstract AbstractLogic<ID, ENTITY> getLogic();

	protected abstract AbstractMapper<ID, ENTITY, DTO> getMapper();
	
	

	@Auth
	public DTO create(DTO dto) {
		return getMapper().convertToDto(getLogic().create(getMapper().convertToEntity(dto)));
	}

	@Auth
	public DTO update(DTO dto) {
		return getMapper().convertToDto(getLogic().update(getMapper().convertToEntity(dto)));
	}

	@Auth
	public void delete(DTO dto, boolean hardDelete) {
		getLogic().delete(getMapper().convertToEntity(dto), hardDelete);
	}

	@Auth
	public DTO findByID(ID id) {
		if (getLogic().findByID(id).isPresent())
			return getMapper().convertToDto(getLogic().findByID(id).get());
		else
			throw new EntityNotExistsException(dtoClazz.getName());
	}

	@Auth
	public List<DTO> getAll() {
		return getMapper().convertToDtoList(getLogic().getAll());
	}

	@Auth
	public List<DTO> update(List<DTO> dtoList) {
		return getMapper().convertToDtoList(getLogic().update(getMapper().convertToEntityList(dtoList)));
	}

	@Auth
	public List<DTO> create(List<DTO> dtoList) {
		return getMapper().convertToDtoList(getLogic().create(getMapper().convertToEntityList(dtoList)));
	}

	@Auth
	public void delete(List<DTO> dtoList, boolean hardDelete) {
		getLogic().delete(getMapper().convertToEntityList(dtoList), hardDelete);
	}

	@Auth
	public void delete(List<DTO> dtoList) {
		getLogic().delete(getMapper().convertToEntityList(dtoList));
	}

	@Auth
	public void delete(DTO dto) {
		getLogic().delete(getMapper().convertToEntity(dto));
	}

	@Auth
	public ListModel<DTO> getPage(ListRequest request) {
		return getMapper().convertListModel(getLogic().getPage(request));
	}
}
