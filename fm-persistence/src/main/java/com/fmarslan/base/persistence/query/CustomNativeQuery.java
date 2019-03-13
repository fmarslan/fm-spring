package com.fmarslan.base.persistence.query;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class CustomNativeQuery implements CustomQueryBase {

	private String query;
	private IParameterBinder parameterBinder;

	public CustomNativeQuery(String query, IParameterBinder parameterBinder) {
		this.query = query;
		this.parameterBinder = parameterBinder;
	}

	public CustomNativeQuery(String query) {
		this(query, null);
	}

	public <T> Query createQuery(EntityManager em, Class<T> clazz) {
		Query _query = em.createNativeQuery(query, clazz);
		if (parameterBinder != null) {
			parameterBinder.bind(_query);
		}
		return _query;
	}
}
