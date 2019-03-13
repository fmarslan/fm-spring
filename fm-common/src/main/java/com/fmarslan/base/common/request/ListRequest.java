package com.fmarslan.base.common.request;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fmarslan.base.common.configuration.AppConfig;

public class ListRequest implements Serializable {

	private static final long serialVersionUID = 6795035057074563871L;

	private Integer offset = 0;
	private Integer count = AppConfig.getPageSize();
	private Map<String, Object> filter;
	private List<String> order;

	public ListRequest() {
	}

	public ListRequest(Integer offset, Integer count, Map<String, Object> filter, List<String> order) {
		super();
		this.offset = offset;
		this.count = count;
		this.filter = filter;
		this.order = order;
	}

	public Integer getOffset() {
		return offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Map<String, Object> getFilter() {
		return filter;
	}

	public void setFilter(Map<String, Object> filter) {
		this.filter = filter;
	}

	public List<String> getOrder() {
		return order;
	}

	public void setOrder(List<String> order) {
		this.order = order;
	}

}
