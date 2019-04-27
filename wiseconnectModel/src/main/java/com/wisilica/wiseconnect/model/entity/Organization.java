package com.wisilica.wiseconnect.model.entity;
/**
 *  Wiseconnect AppInfo
 *
 *  @author Bhaskaran
 *  @Date  19-Mar-2019
 *
 * Description : Entity for tbl_organization
 *
 * */
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_organization")
public class Organization {

    private Long organizationId;

    private String orgName;

    private Long orgType;

    private Long customerId;

    private Long parentId;

    private Long status;

    private String networkKey;

    private Long timeSyncSequenceNum;

    private Long iconId;

    private double timeStamp;

    private Date createdTime;

    private Date lastUpdatedTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organization_id")
    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    @Column(name = "org_name")
    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    @Column(name = "org_type")
    public Long getOrgType() {
        return orgType;
    }

    public void setOrgType(Long orgType) {
        this.orgType = orgType;
    }

    @Column(name = "customer_id")
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Column(name = "parent_id")
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    @Column(name = "status")
    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }
    @Column(name = "network_key")
    public String getNetworkKey() {
        return networkKey;
    }

    public void setNetworkKey(String networkKey) {
        this.networkKey = networkKey;
    }

    @Column(name = "time_sync_sequence_num")
    public Long getTimeSyncSequenceNum() {
        return timeSyncSequenceNum;
    }

    public void setTimeSyncSequenceNum(Long timeSyncSequenceNum) {
        this.timeSyncSequenceNum = timeSyncSequenceNum;
    }

    @Column(name = "icon_id")
    public Long getIconId() {
        return iconId;
    }

    public void setIconId(Long iconId) {
        this.iconId = iconId;
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

    @Column(name = "last_updated")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }
}
