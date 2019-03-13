package com.fmarslan.base.persistence.query;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public interface CustomQueryBase {

	public interface IParameterBinder {
		void bind(Query query);
	}

	<T> Query createQuery(EntityManager em, Class<T> clazz);

}
