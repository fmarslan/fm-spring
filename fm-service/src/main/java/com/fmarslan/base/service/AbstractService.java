package com.fmarslan.base.service;

import java.io.Serializable;
import java.util.List;

import com.fmarslan.base.common.dto.BaseDto;
import com.fmarslan.base.common.request.ListRequest;
import com.fmarslan.base.common.response.ListModel;
import com.fmarslan.base.persistence.BaseEntity;
import com.fmarslan.base.service.security.Auth;

public abstract class AbstractService<ID extends Serializable, DTO extends BaseDto<ID>, ENTITY extends BaseEntity<ID>> {

//	private Logger logger = LoggerFactory.getLogger(this.getClass());

	protected abstract AbstractLogic<ID, ENTITY> getLogic();

	protected abstract AbstractMapper<ID, ENTITY, DTO> getMapper();

	@Auth
	public DTO create(DTO dto) {
		return getMapper().convertDto(getLogic().create(getMapper().convertEntity(dto)));
	}

	@Auth
	public DTO update(DTO dto) {
		return getMapper().convertDto(getLogic().update(getMapper().convertEntity(dto)));
	}

	@Auth
	public void delete(DTO dto, boolean hardDelete) {
		getLogic().delete(getMapper().convertEntity(dto), hardDelete);
	}

	@Auth
	public DTO findByID(ID id) {
		return getMapper().convertDto(getLogic().findByID(id));
	}

	@Auth
	public List<DTO> getAll() {
		return getMapper().getDtoList(getLogic().getAll());
	}

	@Auth
	public List<DTO> update(List<DTO> dtoList) {
		return getMapper().getDtoList(getLogic().update(getMapper().getEntityList(dtoList)));
	}

	@Auth
	public List<DTO> create(List<DTO> dtoList) {
		return getMapper().getDtoList(getLogic().create(getMapper().getEntityList(dtoList)));
	}

	@Auth
	public void delete(List<DTO> dtoList, boolean hardDelete) {
		getLogic().delete(getMapper().getEntityList(dtoList), hardDelete);
	}

	@Auth
	public void delete(List<DTO> dtoList) {
		getLogic().delete(getMapper().getEntityList(dtoList));
	}

	@Auth
	public void delete(DTO dto) {
		getLogic().delete(getMapper().convertEntity(dto));
	}

	@Auth
	public ListModel<DTO> getPage(ListRequest request) {
		return getMapper().convertListModel(getLogic().getPage(request));
	}
}
