package com.wisilica.wiseconnect.core.util;

import java.io.Serializable;

/**
 *  WiseConnect Api Status
 *
 *  @author Josny Jose
 *  @Date  24-Mar-2019
 *
 * Description : Define Status Variables
 *
 * */

public class WiseStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer apiId;

    private Integer statusCode;

    private String statusMessage;

    private String detailMessage;

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

    public String getDetailMessage() {
        return detailMessage;
    }

    public void setDetailMessage(String detailMessage) {
        this.detailMessage = detailMessage;
    }
}
