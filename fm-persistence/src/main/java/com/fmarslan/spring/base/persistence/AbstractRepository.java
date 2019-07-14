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
package com.fmarslan.spring.base.persistence;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.fmarslan.spring.base.common.application.MessageLabels;
import com.fmarslan.spring.base.common.exceptions.LogicalException;
import com.fmarslan.spring.base.common.request.ListRequest;
import com.fmarslan.spring.base.common.response.ListModel;
import com.fmarslan.spring.base.common.shared.ISoftDelete;
import com.fmarslan.spring.base.persistence.query.CustomQueryBase;

public abstract class AbstractRepository<ID extends Serializable, ENTITY extends BaseEntity<ID>> {


  Class<ENTITY> entityClazz;

  @PersistenceContext
  EntityManager entityManager;

  HashMap<String, String> filter = new HashMap<String, String>();
  HashMap<String, String> order = new HashMap<String, String>();
  LinkedList<String> joinEntities = new LinkedList<String>();


  public AbstractRepository(Class<ENTITY> entityClazz) {
    this.entityClazz = entityClazz;
  }

  /**
   * @return the filter
   */
  public HashMap<String, String> getFilter() {
    return filter;
  }

  /**
   * @return the order
   */
  public HashMap<String, String> getOrder() {
    return order;
  }

  protected EntityManager getEntityManager() {
    return entityManager;
  }

  public ENTITY create(ENTITY entity) {
    if (entity == null)
      throw new LogicalException(MessageLabels.MUST_BE_NOT_NULL, entityClazz.getName());
    if (entity.getPrimaryKey() != null)
      throw new LogicalException(MessageLabels.PRIMARY_KEY_MUST_BE_NULL, entityClazz.getName());
    getEntityManager().persist(entity);
    return entity;
  }

  public ENTITY update(ENTITY entity) {
    if (entity == null)
      throw new LogicalException(MessageLabels.MUST_BE_NOT_NULL, entityClazz.getName());
    if (entity.getPrimaryKey() == null)
      throw new LogicalException(MessageLabels.PRIMARY_KEY_MUST_BE_NOT_NULL, entityClazz.getName());
    return getEntityManager().merge(entity);
    // return entity;
  }

  public void delete(ENTITY entity, boolean hardDelete) {
    if (entity == null)
      throw new LogicalException(MessageLabels.MUST_BE_NOT_NULL, entityClazz.getName());
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
   * get page by count and offset filter and sorting will not working you must override for their
   * operation
   */
  public ListModel<ENTITY> getPage(ListRequest request) {

    ListModel<ENTITY> result = new ListModel<ENTITY>();

    String queryString = " FROM %s e " + String.join(" ", joinEntities);

    HashMap<String, Object> params = new HashMap<String, Object>();

    if (request.getFilter() != null && request.getFilter().size() > 0) {
      List<String> filters = new ArrayList<String>();
      request.getFilter().forEach((k, v) -> {
        if (getFilter().containsKey(k)) {
          String f = getFilter().get(k);
          filters.add(f);
          if (f.indexOf(":" + k) > -1) {
            params.put(k, v);
          }
        }
      });

      if (filters.size() > 0) {
        queryString += " WHERE " + String.join(" AND ", filters);
      }
    }

    Long count = null;

    if (request.isOnlyData() == false) {
      count = setParameterToQuery(
          getEntityManager().createQuery("SELECT COUNT(e) " + queryString, Long.class), params)
              .getSingleResult();
      result.setTotalCount(count);
    }

    if (request.isOnlyCount() == false && (request.isOnlyData() || count > 0)) {

      String orders = " ";
      if (request.getOrder() != null && request.getOrder().size() > 0) {
        LinkedList<String> _order = new LinkedList<String>();
        request.getOrder().forEach((k, v) -> {
          if (getOrder().containsKey(k)) {
            String o = getOrder().get(k);
            _order.add(o + " " + v.toString());
          }
        });
        orders = String.join(" , ", _order);
      }



      TypedQuery<ENTITY> query =
          getEntityManager().createQuery("SELECT e " + queryString + orders, entityClazz);
      query.setFirstResult(request.getOffset());
      query.setMaxResults(request.getSize());

      List<ENTITY> allitems = query.getResultList();

      result.setCount(allitems.size());
      result.setOffset(request.getOffset());

    }
    return result;
  }

  private <T> TypedQuery<T> setParameterToQuery(TypedQuery<T> query,
      Map<String, Object> parameters) {
    parameters.forEach((k, v) -> {
      query.setParameter(k, v);
    });
    return query;
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
