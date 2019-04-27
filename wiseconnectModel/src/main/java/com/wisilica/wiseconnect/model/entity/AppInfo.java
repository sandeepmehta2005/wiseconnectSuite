package com.wisilica.wiseconnect.model.entity;
/**
 *  Wiseconnect AppInfo
 *
 *  @author Bhaskaran
 *
 *  @Modified By Josny
 *  @Date  29-Mar-2019
 *
 * Description : Entity for tbl_app_info
 *
 * */
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tbl_app_info")
public class AppInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long appId;
    private String deviceId;
    private String osInfo;
    private Long osType;
    private String modelInfo;
    private String deviceToken;
    private WideInfo wideInfo;
    private Long organizationId;
    private String appVersion;
    private Long installationCount;
    private Date createdTime;
    private Long projectId;
    private String bundlePackage;
    private String bundleClientId;
    private BundleClientMapping bundleClientMapping;
    private Date lastUpdated;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "app_id")
    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    @Column(name = "device_id")
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Column(name = "os_info")
    public String getOsInfo() {
        return osInfo;
    }

    public void setOsInfo(String osInfo) {
        this.osInfo = osInfo;
    }

    @Column(name = "os_type")
    public Long getOsType() {
        return osType;
    }

    public void setOsType(Long osType) {
        this.osType = osType;
    }

    @Column(name = "model_info")
    public String getModelInfo() {
        return modelInfo;
    }

    public void setModelInfo(String modelInfo) {
        this.modelInfo = modelInfo;
    }

    @Column(name = "device_token")
    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_token", insertable = false, updatable = false)
    public WideInfo getWideInfo() {
        return wideInfo;
    }

    public void setWideInfo(WideInfo wideInfo) {
        this.wideInfo = wideInfo;
    }

    @Column(name = "organization_id")
    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }
    @Column(name = "app_version")
    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    @Column(name = "installation_count")
    public Long getInstallationCount() {
        return installationCount;
    }

    public void setInstallationCount(Long installationCount) {
        this.installationCount = installationCount;
    }
    @Column(name = "created_time")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Column(name = "project_id")
    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
    @Column(name = "bundle_package")
    public String getBundlePackage() {
        return bundlePackage;
    }

    public void setBundlePackage(String bundlePackage) {
        this.bundlePackage = bundlePackage;
    }

    @Column(name = "bundle_client_id")
    public String getBundleClientId() {
        return bundleClientId;
    }

    public void setBundleClientId(String bundleClientId) {
        this.bundleClientId = bundleClientId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bundle_client_id", insertable = false, updatable = false)
    public BundleClientMapping getBundleClientMapping() {
        return bundleClientMapping;
    }

    public void setBundleClientMapping(BundleClientMapping bundleClientMapping) {
        this.bundleClientMapping = bundleClientMapping;
    }

    @Column(name = "last_updated")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
