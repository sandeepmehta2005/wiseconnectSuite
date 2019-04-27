package com.wisilica.wiseconnect.model.entity;
/**
 *  Wiseconnect AppInfo
 *
 *  @author Bhaskaran
 *  @Date  19-Mar-2019
 *
 * Description : Entity for tbl_phone
 *
 * */
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_phone")
public class Phone {

    private Long userPhoneId;
    private Long userId;
    private Long organizationId;
    private Long appId;
    private Long phoneShortId;
    private Long isBridge;
    private Long messageId;
    private Long subscriptionId;
    private double bridgeHeartBeat;
    private Date lastUpdated;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_phone_id")
    public Long getUserPhoneId() {
        return userPhoneId;
    }

    public void setUserPhoneId(Long userPhoneId) {
        this.userPhoneId = userPhoneId;
    }

    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Column(name = "organization_id")
    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }
    @Column(name = "app_id")
    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }
    @Column(name = "phone_short_id")
    public Long getPhoneShortId() {
        return phoneShortId;
    }

    public void setPhoneShortId(Long phoneShortId) {
        this.phoneShortId = phoneShortId;
    }
    @Column(name = "is_bridge")
    public Long getIsBridge() {
        return isBridge;
    }

    public void setIsBridge(Long isBridge) {
        this.isBridge = isBridge;
    }
    @Column(name = "message_id")
    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    @Column(name = "subscription_id")
    public Long getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Long subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    @Column(name = "bridge_heart_beat")
    public double getBridgeHeartBeat() {
        return bridgeHeartBeat;
    }

    public void setBridgeHeartBeat(double bridgeHeartBeat) {
        this.bridgeHeartBeat = bridgeHeartBeat;
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
