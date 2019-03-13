package com.fmarslan.base.common.exceptions;

public class PermissionAccessDenied extends AbstractException {

	private static final long serialVersionUID = 2865499046396533106L;

	public PermissionAccessDenied() {
	}

	public PermissionAccessDenied(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PermissionAccessDenied(String message, Throwable cause) {
		super(message, cause);
	}

	public PermissionAccessDenied(String message) {
		super(message);
	}

	public PermissionAccessDenied(String message, Object... params) {
		super(message, params);
	}

	public PermissionAccessDenied(Throwable cause) {
		super(cause);
	}

	@Override
	public String getCode() {
		return "LOGIC-001";
	}

}
