package com.wisilica.wiseconnect.model.response;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wisilica.wiseconnect.model.AbstractWisconnectBean;

public class Status extends AbstractWisconnectBean{

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long startTime;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer apiId;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Integer statusCode;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String statusMessage;
	
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Map<String, Map<String, String>> detailMessage = new HashMap<>();	

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long endTime;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String version;

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Integer getApiId() {
		return apiId;
	}

	public void setApiId(Integer apiId) {
		this.apiId = apiId;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public Map<String, Map<String, String>> getDetailMessage() {
		return detailMessage;
	}

	public void setDetailMessage(Map<String, Map<String, String>> detailMessage) {
		this.detailMessage = detailMessage;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
}
