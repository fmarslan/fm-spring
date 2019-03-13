package com.fmarslan.base.persistence.query;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class CustomDefinedQuery implements CustomQueryBase {

	private String query;
	private IParameterBinder parameterBinder;

	public CustomDefinedQuery(String query, IParameterBinder parameterBinder) {
		this.query = query;
		this.parameterBinder = parameterBinder;
	}

	public CustomDefinedQuery(String query) {
		this(query, null);
	}

	public <T> TypedQuery<T> createQuery(EntityManager em, Class<T> clazz) {
		TypedQuery<T> _query = em.createNamedQuery(query, clazz);
		if (parameterBinder != null) {
			parameterBinder.bind(_query);
		}
		return _query;
	}
}
