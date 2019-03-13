package com.fmarslan.base.persistence;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fmarslan.base.common.shared.IHasPrimaryKey;

@MappedSuperclass
public abstract class BaseEntity<ID extends Serializable> implements Serializable, IHasPrimaryKey<ID> {

	private static final long serialVersionUID = 6238771300196381192L;

	@Column(name = "CREATED_USER")
	private String createdUser;

	@CreationTimestamp
	@Column(name = "CREATED_AT")
	private Timestamp createdAt;

	@Column(name = "UPDATED_USER")
	private String updatedUser;

	@UpdateTimestamp
	@Column(name = "UPDATED_AT")
	private Timestamp updatedAt;

	@Version
	@Column(name = "ROW_VERSION")
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

	@JsonIgnore
	public abstract void setPrimaryKey(ID id);

}
