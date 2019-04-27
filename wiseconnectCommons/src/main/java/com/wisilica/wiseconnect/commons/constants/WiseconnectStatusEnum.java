package com.wisilica.wiseconnect.commons.constants;

public enum WiseconnectStatusEnum {

	SUCCESS(20001, "API success"), 
	FAILED(20008, "API failed"),
	VALIDATION_FAILED(20006, "Validation error"),
	INVALID_AUTH_DETAILS(20038, "Invalid authorization details"),
	INVALID_HEADER(20003, "Header data is not valid"),
	INVALID_PHONE_ID(20005, "invalid phoneID for this token"),
	GROUP_OR_DEVICE_NOT_EXISTS(20022, "Group or Device not exists"),
	TIME_SYNC_DATA_GET_ERROR(20090, "Time Sync data get error");

	private Integer value;

	private String description;

	WiseconnectStatusEnum(Integer value, String description) {
		this.value = value;
		this.description = description;
	}

	public Integer getValue() {
		return value;
	}

	public String getDescription() {
		return description;
	}
}
