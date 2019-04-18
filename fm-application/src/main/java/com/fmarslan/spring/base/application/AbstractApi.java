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
package com.fmarslan.spring.base.application;

import java.io.Serializable;
import java.util.List;
import org.springframework.http.HttpStatus;
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
import com.fmarslan.spring.base.common.response.ResponseModel;
import com.fmarslan.spring.base.service.AbstractService;

public abstract class AbstractApi<ID extends Serializable, DTO extends BaseDto<ID>> {

  protected abstract AbstractService<ID, DTO, ?> getService();

  @PostMapping(path = "save")
  public ResponseModel<DTO> create(@RequestBody DTO dto) {
    return new ResponseModel<DTO>().setData(getService().create(dto))
        .setStatus(HttpStatus.OK.value());
  }

  @PutMapping(path = "save")
  public ResponseModel<DTO> update(@RequestBody DTO dto) {
    return new ResponseModel<DTO>().setData(getService().update(dto));
  }

  @GetMapping(path = "get/{id}")
  public ResponseModel<DTO> findByID(@PathVariable(required = true) ID id) {
    return new ResponseModel<DTO>().setData(getService().findByID(id));
  }

  @GetMapping(path = "getall")
  public ResponseModel<ListModel<DTO>> getAll() {
    return new ResponseModel<ListModel<DTO>>()
        .setData(new ListModel<DTO>().setData(getService().getAll()));
  }

  @PutMapping(path = "saveall")
  public ResponseModel<ListModel<DTO>> update(@RequestBody List<DTO> dtoList) {
    return new ResponseModel<ListModel<DTO>>()
        .setData(new ListModel<DTO>().setData(getService().update(dtoList)));
  }

  @PostMapping(path = "saveall")
  public ResponseModel<ListModel<DTO>> create(@RequestBody List<DTO> dtoList) {
    return new ResponseModel<ListModel<DTO>>()
        .setData(new ListModel<DTO>().setData(getService().create(dtoList)));
  }

  @DeleteMapping(path = "deleteallhard")
  public ResponseModel<Boolean> deleteHard(@RequestBody List<DTO> dtoList) {
    getService().delete(dtoList, true);
    return new ResponseModel<Boolean>().setData(true);
  }

  @DeleteMapping(path = "deleteall")
  public ResponseModel<Boolean> delete(@RequestBody List<DTO> dtoList) {
    getService().delete(dtoList);
    return new ResponseModel<Boolean>().setData(true);
  }

  @DeleteMapping(path = "delete")
  public ResponseModel<Boolean> delete(@RequestBody DTO dto) {
    getService().delete(dto);
    return new ResponseModel<Boolean>().setData(true);
  }

  @DeleteMapping(path = "deletehard")
  public ResponseModel<Boolean> deleteHard(@RequestBody DTO dto) {
    getService().delete(dto, true);
    return new ResponseModel<Boolean>().setData(true);
  }

  @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, path = "page")
  public ResponseModel<ListModel<DTO>> getPage(@RequestBody ListRequest request) {
    return new ResponseModel<ListModel<DTO>>().setData(getService().getPage(request));
  }

}
