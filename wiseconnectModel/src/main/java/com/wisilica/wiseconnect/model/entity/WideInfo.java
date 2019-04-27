package com.wisilica.wiseconnect.model.entity;
/**
 *  WiseConnect Entity WideInfo
 *
 *  @author Bhaskaran
 *  @Modified Josny Jose
 *  @Date  22-Mar-2019
 *
 * Description : For table tbl_wide_info
 *
 * */
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tbl_wide_info")
public class WideInfo implements Serializable{

    private static final long serialVersionUID = 1L;
    private Long wideId;
    private Long orgId;
    private UserSubOrgPermissions userSubOrgPermissions;
    private Long userId;
    private Long wideShortId;
    private String wideUUID;
    private String wideName;
    private String widePairingInfo;
    private Long parentId;
    private String hwVersion;
    private String swVersion;
    private String firmVersion;
    private Long deviceType;
    private Long upTime;
    private Long powerConsumed;
    private Date createdOn;
    private Long remoteSequenceNum;
    private Long groupId;
    private Long groupCount;
    private Long messageId;
    private double listenerHeartBeat;
    private Long batteryLevel;
    private String customData;
    private String securityCode;
    private Long controlElement;
    private Long groupCapability;
    private Long sensorCapability;
    private Long sceneCapability;
    private String reserveParam;
    private Long iconId;
    private Long tmeStampBattLevel;
    private Long lastUpdatedUser;
    private double timeStamp;
    private double lastReportedTimestamp;
    private Date lastTimeSyncTimestamp;
    private Date lastUpdate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wide_id")
    public Long getWideId() {
        return wideId;
    }

    public void setWideId(Long wideId) {
        this.wideId = wideId;
    }
    @Column(name = "org_id")
    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id", insertable = false, updatable = false)
    public UserSubOrgPermissions getUserSubOrgPermissions() {
        return userSubOrgPermissions;
    }

    public void setUserSubOrgPermissions(UserSubOrgPermissions userSubOrgPermissions) {
        this.userSubOrgPermissions = userSubOrgPermissions;
    }

    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    @Column(name = "wide_short_id")
    public Long getWideShortId() {
        return wideShortId;
    }

    public void setWideShortId(Long wideShortId) {
        this.wideShortId = wideShortId;
    }

    @Column(name = "wide_uuid")
    public String getWideUUID() {
        return wideUUID;
    }

    public void setWideUUID(String wideUUID) {
        this.wideUUID = wideUUID;
    }

    @Column(name = "wide_name")
    public String getWideName() {
        return wideName;
    }

    public void setWideName(String wideName) {
        this.wideName = wideName;
    }

    @Column(name = "wide_pairing_info")
    public String getWidePairingInfo() {
        return widePairingInfo;
    }

    public void setWidePairingInfo(String widePairingInfo) {
        this.widePairingInfo = widePairingInfo;
    }

    @Column(name = "parent_id")
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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
    @Column(name = "device_type")
    public Long getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Long deviceType) {
        this.deviceType = deviceType;
    }
    @Column(name = "uptime")
    public Long getUpTime() {
        return upTime;
    }

    public void setUpTime(Long upTime) {
        this.upTime = upTime;
    }

    @Column(name = "power_consumed")
    public Long getPowerConsumed() {
        return powerConsumed;
    }

    public void setPowerConsumed(Long powerConsumed) {
        this.powerConsumed = powerConsumed;
    }

    @Column(name = "created_on")
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
    @Column(name = "remote_sequence_num")
    public Long getRemoteSequenceNum() {
        return remoteSequenceNum;
    }

    public void setRemoteSequenceNum(Long remoteSequenceNum) {
        this.remoteSequenceNum = remoteSequenceNum;
    }
    @Column(name = "group_id")
    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
    @Column(name = "group_count")
    public Long getGroupCount() {
        return groupCount;
    }

    public void setGroupCount(Long groupCount) {
        this.groupCount = groupCount;
    }
    @Column(name = "message_id")
    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }
    @Column(name = "listener_heart_beat")
    public double getListenerHeartBeat() {
        return listenerHeartBeat;
    }

    public void setListenerHeartBeat(double listenerHeartBeat) {
        this.listenerHeartBeat = listenerHeartBeat;
    }
    @Column(name = "battery_level")
    public Long getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(Long batteryLevel) {
        this.batteryLevel = batteryLevel;
    }
    @Column(name = "custom_data")
    public String getCustomData() {
        return customData;
    }

    public void setCustomData(String customData) {
        this.customData = customData;
    }
    @Column(name = "security_code")
    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }
    @Column(name = "control_element")
    public Long getControlElement() {
        return controlElement;
    }

    public void setControlElement(Long controlElement) {
        this.controlElement = controlElement;
    }
    @Column(name = "group_capability")
    public Long getGroupCapability() {
        return groupCapability;
    }

    public void setGroupCapability(Long groupCapability) {
        this.groupCapability = groupCapability;
    }
    @Column(name = "sensor_capability")
    public Long getSensorCapability() {
        return sensorCapability;
    }

    public void setSensorCapability(Long sensorCapability) {
        this.sensorCapability = sensorCapability;
    }
    @Column(name = "scene_capability")
    public Long getSceneCapability() {
        return sceneCapability;
    }

    public void setSceneCapability(Long sceneCapability) {
        this.sceneCapability = sceneCapability;
    }
    @Column(name = "reserve_param")
    public String getReserveParam() {
        return reserveParam;
    }

    public void setReserveParam(String reserveParam) {
        this.reserveParam = reserveParam;
    }
    @Column(name = "icon_id")
    public Long getIconId() {
        return iconId;
    }

    public void setIconId(Long iconId) {
        this.iconId = iconId;
    }
    @Column(name = "tme_stamp_batt_level")
    public Long getTmeStampBattLevel() {
        return tmeStampBattLevel;
    }

    public void setTmeStampBattLevel(Long tmeStampBattLevel) {
        this.tmeStampBattLevel = tmeStampBattLevel;
    }
    @Column(name = "last_updated_user")
    public Long getLastUpdatedUser() {
        return lastUpdatedUser;
    }

    public void setLastUpdatedUser(Long lastUpdatedUser) {
        this.lastUpdatedUser = lastUpdatedUser;
    }

    @Column(name = "timestamp")
    public double getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(double timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Column(name = "last_reported_timestamp")
    public double getLastReportedTimestamp() {
        return lastReportedTimestamp;
    }

    public void setLastReportedTimestamp(double lastReportedTimestamp) {
        this.lastReportedTimestamp = lastReportedTimestamp;
    }
    @Column(name = "last_time_sync_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getLastTimeSyncTimestamp() {
        return lastTimeSyncTimestamp;
    }

    public void setLastTimeSyncTimestamp(Date lastTimeSyncTimestamp) {
        this.lastTimeSyncTimestamp = lastTimeSyncTimestamp;
    }
    @Column(name = "last_update")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
