package com.wisilica.wiseconnect.commons.exception;

import org.springframework.validation.Errors;

import com.wisilica.wiseconnect.commons.constants.ResponseCodeEnum;

public class ControllerException extends WiseconnectException {

	private static final long serialVersionUID = 1L;

	private final String message;

	private final ResponseCodeEnum responseCode;
	
	private final transient Errors bindingErrors;
	
	public ControllerException(ResponseCodeEnum responseCode, String message) {	
		super(message);
		this.responseCode = responseCode;
		this.message = message;
		this.bindingErrors = null;
		
	}
	
	public ControllerException(Errors bindingErrors, String message) {	
		super(message);
		this.responseCode = ResponseCodeEnum.UNPROCESSABLE_ENTITY;
		this.bindingErrors = bindingErrors;
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

	public ResponseCodeEnum getResponseCode() {
		return responseCode;
	}	

	public Errors getBindingErrors() {
		return bindingErrors;
	}
}
