package com.wisilica.wiseconnect.commons.constants;

import java.util.EnumMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

public enum ResponseCodeEnum {

	SUCCESS(HttpStatus.OK, WiseconnectStatusEnum.SUCCESS, "Success"),
	BAD_REQUEST(HttpStatus.BAD_REQUEST, "Bad Request"),
	UNPROCESSABLE_ENTITY(HttpStatus.UNPROCESSABLE_ENTITY, WiseconnectStatusEnum.VALIDATION_FAILED, "Unprocessable Entity", ErrorCodeEnum.REQUEST_VALIDATION_FAILED),
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, WiseconnectStatusEnum.FAILED, "Internal Server Error", ErrorCodeEnum.UN_EXPECTED_ERROR),
	INVALID_HEADER(HttpStatus.UNAUTHORIZED, WiseconnectStatusEnum.INVALID_HEADER, ResponseCodeEnum.UN_AUTH_MESSAGE, ErrorCodeEnum.UN_AUTHORIZED_ACCESS),
	INVALID_AUTH(HttpStatus.UNAUTHORIZED, WiseconnectStatusEnum.INVALID_AUTH_DETAILS, ResponseCodeEnum.UN_AUTH_MESSAGE, ErrorCodeEnum.UN_AUTHORIZED_ACCESS),
	INVALID_PHONE_ID(HttpStatus.UNAUTHORIZED, WiseconnectStatusEnum.INVALID_PHONE_ID, ResponseCodeEnum.UN_AUTH_MESSAGE, ErrorCodeEnum.UN_AUTHORIZED_ACCESS),
	GROUP_OR_DEVICE_NOT_EXISTS(HttpStatus.UNPROCESSABLE_ENTITY, WiseconnectStatusEnum.GROUP_OR_DEVICE_NOT_EXISTS, "Group or Device not exists", ErrorCodeEnum.GROUP_OR_DEVICE_NOT_EXISTS),
	TIME_SYNC_DATA_GET_ERROR(HttpStatus.UNPROCESSABLE_ENTITY, WiseconnectStatusEnum.TIME_SYNC_DATA_GET_ERROR, "Time Sync data get error", ErrorCodeEnum.TIME_SYNC_DATA_GET_ERROR);

	private static final Map<ErrorCodeEnum, ResponseCodeEnum> ERROR_CODE_RESPONSE_CODE_MAP = new EnumMap <>(ErrorCodeEnum.class);

	private static final String UN_AUTH_MESSAGE = "Un authorized access";

	private final HttpStatus httpStatus;

	private final WiseconnectStatusEnum wiseconnectStatus;

	private final String message;

	private final ErrorCodeEnum errorCode;

	private static final ResponseCodeEnum[] values;

	static {
		values = values();
		for (ResponseCodeEnum res : values) {
			if (res.getErrorCode() != null) {
				ERROR_CODE_RESPONSE_CODE_MAP.put(res.getErrorCode(), res);
			}
		}
	}
	
	ResponseCodeEnum(HttpStatus httpStatus, String type) {
		this.httpStatus = httpStatus;
		this.wiseconnectStatus = null;
		this.message = type;
		this.errorCode = null;
	}

	ResponseCodeEnum(HttpStatus httpStatus, WiseconnectStatusEnum wiseconnectStatus, String type,
			ErrorCodeEnum errorCode) {
		this.httpStatus = httpStatus;
		this.wiseconnectStatus = wiseconnectStatus;
		this.message = type;
		this.errorCode = errorCode;
	}

	ResponseCodeEnum(HttpStatus httpStatus, WiseconnectStatusEnum wiseconnectStatus, String type) {
		this.httpStatus = httpStatus;
		this.wiseconnectStatus = wiseconnectStatus;
		this.message = type;
		this.errorCode = null;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public ErrorCodeEnum getErrorCode() {
		return errorCode;
	}

	public WiseconnectStatusEnum getWiseconnectStatus() {
		return wiseconnectStatus;
	}

	public static ResponseCodeEnum getByCode(HttpStatus httpStatus) {
		if (httpStatus == null) {
			return null;
		}
		for (ResponseCodeEnum responseCode : values) {
			if (responseCode.getHttpStatus().equals(httpStatus)) {
				return responseCode;
			}
		}
		return null;
	}

	public static ResponseCodeEnum getByErrorCode(ErrorCodeEnum errorCode) {
		if (errorCode == null) {
			return null;
		}
		return ERROR_CODE_RESPONSE_CODE_MAP.get(errorCode);
	}
}
