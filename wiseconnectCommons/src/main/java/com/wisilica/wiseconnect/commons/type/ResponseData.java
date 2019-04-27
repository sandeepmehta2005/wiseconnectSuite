package com.wisilica.wiseconnect.commons.type;

import com.wisilica.wiseconnect.commons.constants.ResponseCodeEnum;

public class ResponseData {
	
	private ResponseCodeEnum responseCode;
	
	private String statusMessage;

	public ResponseCodeEnum getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(ResponseCodeEnum responseCode) {
		this.responseCode = responseCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
}
