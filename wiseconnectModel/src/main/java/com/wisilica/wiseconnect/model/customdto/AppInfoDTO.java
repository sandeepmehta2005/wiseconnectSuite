package com.wisilica.wiseconnect.model.customdto;
/**
 *  WiseConnect App Info helper dto
 *
 *  @author Josny Jose
 *  @Date  25-Mar-2019
 *
 * Description : For selecting custom Fields while using join queries
 *
 * */
public class AppInfoDTO {

    private String deviceId;
    private int osType;
    private Long bundleId;
    private String bundlePackage;
    private String bundleClientId;
    private Long featureId;

    public AppInfoDTO(String deviceId, int osType, Long bundleId, String bundlePackage, String bundleClientId, Long featureId) {
        this.deviceId = deviceId;
        this.osType = osType;
        this.bundleId = bundleId;
        this.bundlePackage = bundlePackage;
        this.bundleClientId = bundleClientId;
        this.featureId = featureId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public int getOsType() {
        return osType;
    }

    public void setOsType(int osType) {
        this.osType = osType;
    }

    public Long getBundleId() {
        return bundleId;
    }

    public void setBundleId(Long bundleId) {
        this.bundleId = bundleId;
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

    public Long getFeatureId() {
        return featureId;
    }

    public void setFeatureId(Long featureId) {
        this.featureId = featureId;
    }
}
