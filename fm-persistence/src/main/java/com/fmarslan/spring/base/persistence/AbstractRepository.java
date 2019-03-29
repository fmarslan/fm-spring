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
package com.fmarslan.spring.base.persistence;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.fmarslan.spring.base.common.exceptions.LogicalException;
import com.fmarslan.spring.base.common.request.ListRequest;
import com.fmarslan.spring.base.common.response.ListModel;
import com.fmarslan.spring.base.common.shared.ISoftDelete;
import com.fmarslan.spring.base.persistence.query.CustomQueryBase;

public abstract class AbstractRepository<ID extends Serializable, ENTITY extends BaseEntity<ID>> {

	Class<ENTITY> entityClazz;

	@PersistenceContext
	EntityManager entityManager;

	public AbstractRepository(Class<ENTITY> entityClazz) {
		this.entityClazz = entityClazz;
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public ENTITY create(ENTITY entity) {
		if (entity == null)
			throw new LogicalException("%s should be not null", entityClazz.getName());
		if (entity.getPrimaryKey() != null)
			throw new LogicalException("%s Primary key should be null", entityClazz.getName());
		getEntityManager().persist(entity);
		return entity;
	}

	public ENTITY update(ENTITY entity) {
		if (entity == null)
			throw new LogicalException("%s should be not null", entityClazz.getName());
		if (entity.getPrimaryKey() == null)
			throw new LogicalException("%s Primary key should be not null", entityClazz.getName());
		return getEntityManager().merge(entity);
		//return entity;
	}

	public void delete(ENTITY entity, boolean hardDelete) {
		if (entity == null)
			throw new LogicalException("%s should be not null", entityClazz.getName());
		if (hardDelete || (entity instanceof ISoftDelete) == false)
			getEntityManager().remove(entity);
		else {
			((ISoftDelete) entity).setDeleted(true);
			update(entity);
		}
	}

	public Optional<ENTITY> findByID(ID id) {
		ENTITY entity = getEntityManager().find(entityClazz, id);
		return Optional.ofNullable(entity);
	}

	public List<ENTITY> getAll() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<ENTITY> cq = cb.createQuery(entityClazz);
		Root<ENTITY> from = cq.from(entityClazz);
		cq.select(from);
		TypedQuery<ENTITY> q = getEntityManager().createQuery(cq);
		List<ENTITY> allitems = q.getResultList();
		return allitems;
	}

	public List<ENTITY> update(List<ENTITY> entities) {
		for (ENTITY entity : entities)
			update(entity);
		return entities;
	}

	public List<ENTITY> create(List<ENTITY> entities) {
		for (ENTITY entity : entities)
			create(entity);
		return entities;
	}

	public void delete(List<ENTITY> entities, boolean hardDelete) {
		for (ENTITY entity : entities)
			delete(entity, hardDelete);
	}

	public void delete(List<ENTITY> entities) {
		delete(entities, false);
	}

	public void delete(ENTITY entity) {
		delete(entity, false);
	}

	/**
	 * get page by count and offset filter and sorting will not working you must
	 * override for their operation
	 */
	public ListModel<ENTITY> getPage(ListRequest request) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<ENTITY> cq = cb.createQuery(entityClazz);
		Root<ENTITY> from = cq.from(entityClazz);
		cq.select(from);
		
		//cq.where(/* where clause*/);
		
		//cq.orderBy(o) //order
		
		TypedQuery<ENTITY> q = getEntityManager().createQuery(cq);
		q.setFirstResult(request.getOffset());
		q.setMaxResults(request.getCount());
		List<ENTITY> allitems = q.getResultList();
		ListModel<ENTITY> result = new ListModel<ENTITY>();
		result.setCount(allitems.size());
		result.setOffset(request.getOffset());
		
		
		CriteriaBuilder qb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cqCount = qb.createQuery(Long.class);
		cqCount.select(qb.count(cq.from(entityClazz)));
		
		//cqCount.where(/* where clause*/);		
		
		Long count = entityManager.createQuery(cqCount).getSingleResult();
		
		result.setTotalCount(count);
		
		return result;
	}

	@SuppressWarnings("unchecked")
	protected List<ENTITY> executeResultList(CustomQueryBase query) {
		return query.createQuery(entityManager, entityClazz).getResultList();
	}

	@SuppressWarnings("unchecked")
	protected ENTITY executeSingleRow(CustomQueryBase query) {
		return (ENTITY) query.createQuery(entityManager, entityClazz).getSingleResult();
	}

	protected void executeUpdate(CustomQueryBase query) {
		query.createQuery(entityManager, entityClazz).executeUpdate();
	}

}
