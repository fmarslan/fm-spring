package com.fmarslan.base.common.exceptions;

public class LogicalException extends AbstractException {

	private static final long serialVersionUID = 2865499046396533106L;

	public LogicalException() {
	}

	public LogicalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public LogicalException(String message, Throwable cause) {
		super(message, cause);
	}

	public LogicalException(String message) {
		super(message);
	}

	public LogicalException(String message, Object... params) {
		super(message, params);
	}

	public LogicalException(Throwable cause) {
		super(cause);
	}

	@Override
	public String getCode() {
		return "LOGIC-001";
	}

}
