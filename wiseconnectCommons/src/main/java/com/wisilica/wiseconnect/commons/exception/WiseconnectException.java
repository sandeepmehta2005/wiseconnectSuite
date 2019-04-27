package com.wisilica.wiseconnect.commons.exception;

public class WiseconnectException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final String message;	

	public WiseconnectException(String message) {
		super(message, null, false, false);
		this.message = message;
	}

	public WiseconnectException(String message, Throwable cause) {
		super(message, cause, false, false);
		this.message = message;
	}

	@Override
	public String toString() {
		return message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
