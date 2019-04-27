package com.wisilica.wiseconnect.model.response;
/**
 *  WiseConnect oauth response
 *
 *  @author Josny Jose
 *  @Date  29-Mar-2019
 *
 * Description : For setting response values
 *
 * */
public class AuthResponse {

    private Integer status;

    private String message;

    private Integer appId;

    private Integer statusCode;

    private Integer organizationId;

    private Integer featureId;

    private String bundlePackage;

    private String bundleClientId;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getFeatureId() {
        return featureId;
    }

    public void setFeatureId(Integer featureId) {
        this.featureId = featureId;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getBundlePackage() {
        return bundlePackage;
    }

    public void setBundlePackage(String bundlePackage) {
        this.bundlePackage = bundlePackage;
    }

    public String getBundleClientId() {
        return bundleClientId;
    }

    public void setBundleClientId(String bundleClientId) {
        this.bundleClientId = bundleClientId;
    }
}
