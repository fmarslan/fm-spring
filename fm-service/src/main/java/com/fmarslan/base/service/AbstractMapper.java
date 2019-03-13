package com.fmarslan.base.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fmarslan.base.common.dto.BaseDto;
import com.fmarslan.base.common.response.ListModel;
import com.fmarslan.base.persistence.BaseEntity;

public abstract class AbstractMapper<ID extends Serializable, ENTITY extends BaseEntity<ID>, DTO extends BaseDto<ID>> {

	protected abstract DTO toDto(ENTITY entity);

	protected abstract ENTITY toEntity(DTO dto);

	public ENTITY convertEntity(DTO dto) {
		if (dto == null)
			return null;
		ENTITY entity = toEntity(dto);
		entity.setCreatedAt(dto.getCreatedAt());
		entity.setCreatedUser(dto.getCreatedUser());
		entity.setUpdatedAt(dto.getUpdatedAt());
		entity.setUpdatedUser(dto.getUpdatedUser());
		entity.setVersion(dto.getVersion());
		return entity;
	}

	public DTO convertDto(ENTITY entity) {
		if (entity == null)
			return null;
		DTO dto = toDto(entity);
		dto.setCreatedAt(entity.getCreatedAt());
		dto.setCreatedUser(entity.getCreatedUser());
		dto.setUpdatedAt(entity.getUpdatedAt());
		dto.setUpdatedUser(entity.getUpdatedUser());
		dto.setVersion(entity.getVersion());
		return dto;
	}

	public List<DTO> getDtoList(List<ENTITY> entities) {
		if (entities == null)
			return null;
		List<DTO> dtoList = new ArrayList<>();
		for (ENTITY e : entities) {
			dtoList.add(convertDto(e));
		}
		return dtoList;
	}

	public List<ENTITY> getEntityList(List<DTO> dtoList) {
		if (dtoList == null)
			return null;
		List<ENTITY> entities = new ArrayList<>();
		for (DTO e : dtoList) {
			entities.add(convertEntity(e));
		}
		return entities;
	}

	public ListModel<DTO> convertListModel(ListModel<ENTITY> model) {
		ListModel<DTO> dtoList = new ListModel<DTO>();
		dtoList.setCount(model.getCount());
		dtoList.setOffset(model.getOffset());
		dtoList.setTotalCount(model.getTotalCount());
		dtoList.setData(getDtoList(model.getData()));
		return dtoList;
	}

}
