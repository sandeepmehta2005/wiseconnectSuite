package com.wisilica.wiseconnect.commons.exception;

import java.util.Map;

import com.wisilica.wiseconnect.commons.constants.ErrorCodeEnum;

public class ServiceException extends WiseconnectException {

	private static final long serialVersionUID = 1L;

	private final String message;
	
	private final String statusMessage;

	private final ErrorCodeEnum errorCode;	
	
	private final Map<String, String> customErrors;

	public ServiceException(ErrorCodeEnum errorCode, String statusMessage, String message) {
		super(message);
		this.message = message;
		this.statusMessage = statusMessage;
		this.errorCode = errorCode;
		this.customErrors = null;
	}
	
	public ServiceException(ErrorCodeEnum errorCode, Map<String, String> customErrors, String message) {
		super(message);
		this.message = message;
		this.customErrors = customErrors;
		this.errorCode = errorCode;
		this.statusMessage = null;
	}

	public ServiceException(ErrorCodeEnum errorCode, String statusMessage, String message, Throwable cause) {
		super(message, cause);
		this.message = message;
		this.statusMessage = statusMessage;
		this.errorCode = errorCode;
		this.customErrors = null;
	}

	@Override
	public String toString() {
		return message;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public ErrorCodeEnum getErrorCode() {
		return errorCode;
	}
	
	public Map<String, String> getCustomErrors() {
		return customErrors;
	}
}
