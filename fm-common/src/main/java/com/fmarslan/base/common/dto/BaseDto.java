package com.fmarslan.base.common.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fmarslan.base.common.shared.IHasPrimaryKey;

public abstract class BaseDto<ID extends Serializable> implements IHasPrimaryKey<ID> {

	private String createdUser;
	private Timestamp createdAt;
	private String updatedUser;
	private Timestamp updatedAt;
	private int version;

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedUser() {
		return updatedUser;
	}

	public void setUpdatedUser(String updatedUser) {
		this.updatedUser = updatedUser;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

}
