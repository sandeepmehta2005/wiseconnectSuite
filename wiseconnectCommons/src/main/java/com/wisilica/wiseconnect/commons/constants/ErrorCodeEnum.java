package com.wisilica.wiseconnect.commons.constants;


public enum ErrorCodeEnum {    
       
    UN_EXPECTED_ERROR("UN_EXPECTED_ERROR", "Un expected error"),        
    UN_AUTHORIZED_ACCESS("UN_AUTHORIZED_ACCESS", "Un authorized access"),
    GROUP_OR_DEVICE_NOT_EXISTS("GROUP_OR_DEVICE_NOT_EXISTS", "Group or Device not exists"),
    TIME_SYNC_DATA_GET_ERROR("TIME_SYNC_DATA_GET_ERROR", "Time Sync data get error"),
    REQUEST_VALIDATION_FAILED("REQUEST_VALIDATION_FAILED", "Request validation failed"),;
    
    private final String code;

    private final String description;

    private ErrorCodeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
