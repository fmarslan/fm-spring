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
package com.fmarslan.spring.base.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.fmarslan.spring.base.common.dto.BaseDto;
import com.fmarslan.spring.base.common.response.ListModel;
import com.fmarslan.spring.base.persistence.BaseEntity;

public abstract class AbstractMapper<ID extends Serializable, ENTITY extends BaseEntity<ID>, DTO extends BaseDto<ID>> {

  protected abstract DTO toDto(ENTITY entity);

  protected abstract ENTITY toEntity(DTO dto);

  public ENTITY convertToEntity(DTO dto) {
    if (dto == null)
      return null;
    ENTITY entity = toEntity(dto);
    if (dto.getCreatedAt() != null)
      entity.setCreatedAt(new Date(dto.getCreatedAt()));
    entity.setCreatedUser(dto.getCreatedUser());
    if (dto.getUpdatedAt() != null)
      entity.setUpdatedAt(new Date(dto.getUpdatedAt()));
    entity.setUpdatedUser(dto.getUpdatedUser());
    entity.setVersion(dto.getVersion());
    return entity;
  }

  public DTO convertToDto(ENTITY entity) {
    if (entity == null)
      return null;
    DTO dto = toDto(entity);
    if (entity.getCreatedAt() != null)
      dto.setCreatedAt(entity.getCreatedAt().getTime());
    dto.setCreatedUser(entity.getCreatedUser());
    if (entity.getUpdatedAt() != null)
      dto.setUpdatedAt(entity.getUpdatedAt().getTime());
    dto.setUpdatedUser(entity.getUpdatedUser());
    dto.setVersion(entity.getVersion());
    return dto;
  }

  public List<DTO> convertToDtoList(List<ENTITY> entityList) {
    if (entityList == null)
      return null;
    List<DTO> dtoList = new ArrayList<>();
    for (ENTITY e : entityList) {
      dtoList.add(convertToDto(e));
    }
    return dtoList;
  }

  public List<ENTITY> convertToEntityList(List<DTO> dtoList) {
    if (dtoList == null)
      return null;
    List<ENTITY> entities = new ArrayList<>();
    for (DTO e : dtoList) {
      entities.add(convertToEntity(e));
    }
    return entities;
  }

  public ListModel<DTO> convertListModel(ListModel<ENTITY> model) {
    ListModel<DTO> dtoList = new ListModel<DTO>();
    dtoList.setCount(model.getCount());
    dtoList.setOffset(model.getOffset());
    dtoList.setTotalCount(model.getTotalCount());
    dtoList.setData(convertToDtoList(model.getData()));
    return dtoList;
  }

}
