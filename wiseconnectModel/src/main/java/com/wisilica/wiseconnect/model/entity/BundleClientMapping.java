package com.wisilica.wiseconnect.model.entity;
/**
 *  WiseConnect BundleClientMapping
 *
 *  @author Bhaskaran
 *  @modified By Josny Jose
 *  @Date  25-Mar-2019
 *
 * Description : Entity for tbl_bundle_client_mapping
 *
 * */

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tbl_bundle_client_mapping")
public class BundleClientMapping implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long mappingId;

    private Long bundleId;

    private String bundleClientId;

    private Long applicationId;

    private Long type;

    private Long featureId;

    private String bundlePackage;

    private Long certificateId;

    private String gcmKey;

    private double timeStamp;

    private Date createdTime;

    private Date updatedTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mapping_id")
    public Long getMappingId() {
        return mappingId;
    }

    public void setMappingId(Long mappingId) {
        this.mappingId = mappingId;
    }

    @Column(name = "bundle_id")
    public Long getBundleId() {
        return bundleId;
    }

    public void setBundleId(Long bundleId) {
        this.bundleId = bundleId;
    }

    @Column(name = "bundle_client_id")
    public String getBundleClientId() {
        return bundleClientId;
    }

    public void setBundleClientId(String bundleClientId) {
        this.bundleClientId = bundleClientId;
    }

    @Column(name = "application_id")
    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    @Column(name = "type")
    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    @Column(name = "feature_id")
    public Long getFeatureId() {
        return featureId;
    }

    public void setFeatureId(Long featureId) {
        this.featureId = featureId;
    }

    @Column(name = "bundle_package")
    public String getBundlePackage() {
        return bundlePackage;
    }

    public void setBundlePackage(String bundlePackage) {
        this.bundlePackage = bundlePackage;
    }

    @Column(name = "certificate_id")
    public Long getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(Long certificateId) {
        this.certificateId = certificateId;
    }

    @Column(name = "gcm_key")
    public String getGcmKey() {
        return gcmKey;
    }

    public void setGcmKey(String gcmKey) {
        this.gcmKey = gcmKey;
    }

    @Column(name = "timestamp")
    public double getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(double timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Column(name = "created_time")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Column(name = "updated_time")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}
