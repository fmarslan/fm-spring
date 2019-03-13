package com.fmarslan.base.service;

import java.io.Serializable;
import java.util.List;

import com.fmarslan.base.common.request.ListRequest;
import com.fmarslan.base.common.response.ListModel;
import com.fmarslan.base.persistence.AbstractRepository;
import com.fmarslan.base.persistence.BaseEntity;



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

	public ENTITY findByID(ID id) {
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
