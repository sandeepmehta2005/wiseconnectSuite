package com.wisilica.wiseconnect.model.entity;
/**
 *  Wiseconnect AppInfo
 *
 *  @author Bhaskaran
 *  @Date  19-Mar-2019
 *
 * Description : Entity for tbl_bridge_listener_info
 *
 * */
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "tbl_bridge_listener_info")
public class BridgeListenerInfo {

    private Long associationId;

    private Long appId;

    private Long phoneLongId;

    private Long deviceId;

    private Long orgId;

    private Long userId;

    private Date createdTime;

    private BigDecimal timeStamp;

    private Date lastUpdatedTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "association_id")
    public Long getAssociationId() {
        return associationId;
    }

    public void setAssociationId(Long associationId) {
        this.associationId = associationId;
    }
    @Column(name = "app_id")
    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    @Column(name = "phone_long_id")
    public Long getPhoneLongId() {
        return phoneLongId;
    }

    public void setPhoneLongId(Long phoneLongId) {
        this.phoneLongId = phoneLongId;
    }
    @Column(name = "device_id")
    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }
    @Column(name = "org_id")
    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Column(name = "created_time")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Column(name = "timestamp")
    public BigDecimal getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(BigDecimal timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Column(name = "last_updated")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }
}
