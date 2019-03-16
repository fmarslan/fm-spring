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
package com.fmarslan.spring.base.common.response;

import java.io.Serializable;
import java.util.List;

public class ListModel<T> implements Serializable {

	private static final long serialVersionUID = -894012163978638125L;

	private Integer count = 0;
	private Long totalCount = 0l;
	private List<T> data;
	private Integer offset;

	public ListModel() {
	}

	public ListModel(Integer count, Long totalCount, List<T> data, Integer offset) {
		super();
		this.count = count;
		this.totalCount = totalCount;
		this.data = data;
		this.offset = offset;
	}

	public Integer getPageNumber() {
		if (getTotalCount() == 0l || getCount() == 0)
			return 0;
		return Math.round(getTotalCount() / getCount());
	}

	public Integer getCount() {
		return count;
	}

	public ListModel<T> setCount(Integer count) {
		this.count = count;
		return this;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public ListModel<T> setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
		return this;
	}

	public List<T> getData() {
		return data;
	}

	public ListModel<T> setData(List<T> data) {
		this.data = data;
		return this;
	}

	public Integer getOffset() {
		return offset;
	}

	public ListModel<T> setOffset(Integer offset) {
		this.offset = offset;
		return this;
	}
}
