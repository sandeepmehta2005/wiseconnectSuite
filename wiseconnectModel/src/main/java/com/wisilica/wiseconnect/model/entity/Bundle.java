package com.wisilica.wiseconnect.model.entity;
/**
 *  WiseConnect Bundle
 *
 *  @author Josny Jose
 *  @Date  29-Mar-2019
 *
 * Description : Entity for tbl_bundle
 *
 * */
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tbl_bundle")
public class Bundle implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long bundleId;

    private String bundleClientId;

    private BundleClientMapping bundleClientMapping;

    private Long customerId;

    private String bundleName;

    private Long featureId;

    private Double timestamp;

    private Date createdTime;

    private Date updatedTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bundle_client_id", insertable = false, updatable = false)
    public BundleClientMapping getBundleClientMapping() {
        return bundleClientMapping;
    }

    public void setBundleClientMapping(BundleClientMapping bundleClientMapping) {
        this.bundleClientMapping = bundleClientMapping;
    }

    @Column(name = "customer_id")
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Column(name = "bundle_name")
    public String getBundleName() {
        return bundleName;
    }

    public void setBundleName(String bundleName) {
        this.bundleName = bundleName;
    }

    @Column(name = "feature_id")
    public Long getFeatureId() {
        return featureId;
    }

    public void setFeatureId(Long featureId) {
        this.featureId = featureId;
    }

    @Column(name = "timestamp")
    public Double getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Double timestamp) {
        this.timestamp = timestamp;
    }

    @Column(name = "created_time")
    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Column(name = "updated_time")
    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
}
