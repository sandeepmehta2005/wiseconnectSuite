package com.wisilica.wiseconnect.model.entity;
/**
 *  Wiseconnect AppInfo
 *
 *  @author Bhaskaran
 *  @Date  19-Mar-2019
 *
 * Description : Entity for tbl_tag
 *
 * */

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_tag")
public class Tag {

    private Long tagId;

    private String tagName;

    private Long shortId;

    private Long orgId;

    private Long userId;

    private String major;

    private String minor;

    private String tagGuid;

    private Long deviceType;

    private Long txPower;

    private Long channel;

    private Long advInterval;

    private Long isMotherTag;

    private Long isListenerTag;

    private Long isThirdPartyTag;

    private Long listenerId;

    private Long timeInterval;

    private Long duration;

    private Long active;

    private Long checkOutStatus;

    private Long checkOutInterval;

    private double checkOutTimestamp;

    private String assignName;

    private Long assignStatus;

    private Long assignInterval;

    private double assignTimestamp;

    private Long assignMessageId;

    private Long assignMessageType;

    private Float batteryLevel;

    private double batteryUpdateTimestamp;

    private Long tamper;

    private double tamperUpdateTimestamp;

    private Long count;

    private String hwVersion;

    private String swVersion;

    private String firmVersion;

    private Long dataCountToBeProcessed;

    private Long rssiAdjust;

    private double timestamp;

    private Date createdTime;

    private Date updatedTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    @Column(name = "tag_name")
    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
    @Column(name = "short_id")
    public Long getShortId() {
        return shortId;
    }

    public void setShortId(Long shortId) {
        this.shortId = shortId;
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

    @Column(name = "major")
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Column(name = "minor")
    public String getMinor() {
        return minor;
    }

    public void setMinor(String minor) {
        this.minor = minor;
    }

    @Column(name = "tag_guid")
    public String getTagGuid() {
        return tagGuid;
    }

    public void setTagGuid(String tagGuid) {
        this.tagGuid = tagGuid;
    }

    @Column(name = "device_type")
    public Long getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Long deviceType) {
        this.deviceType = deviceType;
    }

    @Column(name = "tx_power")
    public Long getTxPower() {
        return txPower;
    }

    public void setTxPower(Long txPower) {
        this.txPower = txPower;
    }

    @Column(name = "channel")
    public Long getChannel() {
        return channel;
    }

    public void setChannel(Long channel) {
        this.channel = channel;
    }

    @Column(name = "adv_interval")
    public Long getAdvInterval() {
        return advInterval;
    }

    public void setAdvInterval(Long advInterval) {
        this.advInterval = advInterval;
    }

    @Column(name = "is_mother_tag")
    public Long getIsMotherTag() {
        return isMotherTag;
    }

    public void setIsMotherTag(Long isMotherTag) {
        this.isMotherTag = isMotherTag;
    }

    @Column(name = "is_listener_tag")
    public Long getIsListenerTag() {
        return isListenerTag;
    }

    public void setIsListenerTag(Long isListenerTag) {
        this.isListenerTag = isListenerTag;
    }

    @Column(name = "is_third_party_tag")
    public Long getIsThirdPartyTag() {
        return isThirdPartyTag;
    }

    public void setIsThirdPartyTag(Long isThirdPartyTag) {
        this.isThirdPartyTag = isThirdPartyTag;
    }

    @Column(name = "listener_id")
    public Long getListenerId() {
        return listenerId;
    }

    public void setListenerId(Long listenerId) {
        this.listenerId = listenerId;
    }

    @Column(name = "time_interval")
    public Long getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(Long timeInterval) {
        this.timeInterval = timeInterval;
    }

    @Column(name = "duration")
    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    @Column(name = "active")
    public Long getActive() {
        return active;
    }

    public void setActive(Long active) {
        this.active = active;
    }

    @Column(name = "check_out_status")
    public Long getCheckOutStatus() {
        return checkOutStatus;
    }

    public void setCheckOutStatus(Long checkOutStatus) {
        this.checkOutStatus = checkOutStatus;
    }

    @Column(name = "check_out_interval")
    public Long getCheckOutInterval() {
        return checkOutInterval;
    }

    public void setCheckOutInterval(Long checkOutInterval) {
        this.checkOutInterval = checkOutInterval;
    }

    @Column(name = "check_out_timestamp")
    public double getCheckOutTimestamp() {
        return checkOutTimestamp;
    }

    public void setCheckOutTimestamp(double checkOutTimestamp) {
        this.checkOutTimestamp = checkOutTimestamp;
    }

    @Column(name = "assign_name")
    public String getAssignName() {
        return assignName;
    }

    public void setAssignName(String assignName) {
        this.assignName = assignName;
    }

    @Column(name = "assign_status")
    public Long getAssignStatus() {
        return assignStatus;
    }

    public void setAssignStatus(Long assignStatus) {
        this.assignStatus = assignStatus;
    }

    @Column(name = "assign_interval")
    public Long getAssignInterval() {
        return assignInterval;
    }

    public void setAssignInterval(Long assignInterval) {
        this.assignInterval = assignInterval;
    }

    @Column(name = "assign_timestamp")
    public double getAssignTimestamp() {
        return assignTimestamp;
    }

    public void setAssignTimestamp(double assignTimestamp) {
        this.assignTimestamp = assignTimestamp;
    }

    @Column(name = "assign_message_id")
    public Long getAssignMessageId() {
        return assignMessageId;
    }

    public void setAssignMessageId(Long assignMessageId) {
        this.assignMessageId = assignMessageId;
    }

    @Column(name = "assign_message_type")
    public Long getAssignMessageType() {
        return assignMessageType;
    }

    public void setAssignMessageType(Long assignMessageType) {
        this.assignMessageType = assignMessageType;
    }

    @Column(name = "battery_level")
    public Float getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(Float batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    @Column(name = "battery_update_timestamp")
    public double getBatteryUpdateTimestamp() {
        return batteryUpdateTimestamp;
    }

    public void setBatteryUpdateTimestamp(double batteryUpdateTimestamp) {
        this.batteryUpdateTimestamp = batteryUpdateTimestamp;
    }

    @Column(name = "tamper")
    public Long getTamper() {
        return tamper;
    }

    public void setTamper(Long tamper) {
        this.tamper = tamper;
    }

    @Column(name = "tamper_update_timestamp")
    public double getTamperUpdateTimestamp() {
        return tamperUpdateTimestamp;
    }

    public void setTamperUpdateTimestamp(double tamperUpdateTimestamp) {
        this.tamperUpdateTimestamp = tamperUpdateTimestamp;
    }

    @Column(name = "count")
    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Column(name = "hw_version")
    public String getHwVersion() {
        return hwVersion;
    }

    public void setHwVersion(String hwVersion) {
        this.hwVersion = hwVersion;
    }

    @Column(name = "sw_version")
    public String getSwVersion() {
        return swVersion;
    }

    public void setSwVersion(String swVersion) {
        this.swVersion = swVersion;
    }

    @Column(name = "firm_version")
    public String getFirmVersion() {
        return firmVersion;
    }

    public void setFirmVersion(String firmVersion) {
        this.firmVersion = firmVersion;
    }

    @Column(name = "data_count_to_be_processed")
    public Long getDataCountToBeProcessed() {
        return dataCountToBeProcessed;
    }

    public void setDataCountToBeProcessed(Long dataCountToBeProcessed) {
        this.dataCountToBeProcessed = dataCountToBeProcessed;
    }

    @Column(name = "rssi_adjust")
    public Long getRssiAdjust() {
        return rssiAdjust;
    }

    public void setRssiAdjust(Long rssiAdjust) {
        this.rssiAdjust = rssiAdjust;
    }

    @Column(name = "timestamp")
    public double getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(double timestamp) {
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
