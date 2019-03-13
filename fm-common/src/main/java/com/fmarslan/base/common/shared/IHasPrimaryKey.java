package com.fmarslan.base.common.shared;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface IHasPrimaryKey<ID> {

	@JsonIgnore
	ID getPrimaryKey();
	
}
