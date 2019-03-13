package com.fmarslan.base.common.exceptions;

public abstract class AbstractException extends RuntimeException {

	private static final long serialVersionUID = 8529479987398884574L;

	public AbstractException() {
		super();
	}

	public AbstractException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AbstractException(String message, Throwable cause) {
		super(message, cause);
	}

	public AbstractException(String message) {
		super(message);
	}

	public AbstractException(String message, Object... params) {
		super(String.format(message, params));
	}

	public AbstractException(Throwable cause) {
		super(cause);
	}

	public abstract String getCode();

	@Override
	public String getMessage() {
		return String.format("[%S] - %s", getCode(), super.getMessage());
	}

	@Override
	public String toString() {
		return getMessage();
	}

}
