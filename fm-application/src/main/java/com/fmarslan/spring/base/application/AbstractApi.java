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
package com.fmarslan.spring.base.application;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fmarslan.spring.base.common.dto.BaseDto;
import com.fmarslan.spring.base.common.request.ListRequest;
import com.fmarslan.spring.base.common.response.ListModel;
import com.fmarslan.spring.base.service.AbstractService;

public abstract class AbstractApi<ID extends Serializable, DTO extends BaseDto<ID>> {

	protected abstract AbstractService<ID, DTO, ?> getService();

	@PostMapping(path = "save")
	public DTO create(DTO dto) {
		return getService().create(dto);
	}

	@PutMapping(path = "save")
	public DTO update(DTO dto) {
		return getService().update(dto);
	}

	@GetMapping(path = "get/{id}")
	public DTO findByID(@PathVariable(required = true) ID id) {
		return getService().findByID(id);
	}

	@GetMapping(path = "getall")
	public List<DTO> getAll() {
		return getService().getAll();
	}

	@PutMapping(path = "saveall")
	public List<DTO> update(List<DTO> dtoList) {
		return getService().update(dtoList);
	}

	@PostMapping(path = "saveall")
	public List<DTO> create(List<DTO> dtoList) {
		return getService().create(dtoList);
	}

	@DeleteMapping(path = "deleteallhard")
	public void deleteHard(List<DTO> dtoList) {
		getService().delete(dtoList, true);
	}

	@DeleteMapping(path = "deleteall")
	public void delete(List<DTO> dtoList) {
		getService().delete(dtoList);
	}

	@DeleteMapping(path = "delete")
	public void delete(DTO dto) {
		getService().delete(dto);
	}

	@DeleteMapping(path = "deletehard")
	public void deleteHard(@RequestBody DTO dto) {
		getService().delete(dto, true);
	}

	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST }, path = "page")
	public ListModel<DTO> getPage(@RequestBody ListRequest request) {
		return getService().getPage(request);
	}

}
