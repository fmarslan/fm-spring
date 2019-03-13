package com.fmarslan.base.common.exceptions;

public class EntityNotExistsException extends AbstractException {

	private static final long serialVersionUID = 2865499046396533106L;

	public EntityNotExistsException() {
		
	}

	public EntityNotExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public EntityNotExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public EntityNotExistsException(String message) {
		super(message);
	}

	public EntityNotExistsException(String message, Object... params) {
		super(message, params);
	}

	public EntityNotExistsException(Throwable cause) {
		super(cause);
	}

	@Override
	public String getCode() {
		return "ENTITYNOTEXISTS-001";
	}

}
