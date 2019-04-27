package com.wisilica.wiseconnect.model.entity;

import javax.persistence.*;
import java.util.Date;
/**
 *  Wiseconnect AppInfo
 *
 *  @author Bhaskaran
 *  @Date  19-Mar-2019
 *
 * Description : Entity for tbl_notification_settings
 *
 * */
@Entity
@Table(name = "tbl_notification_settings")
public class NotificationSettings {

    private Long id;

    private Long userId;

    private Long userPhoneId;

    private Long appId;

    private Long alertType;

    private String notificationSound;

    private Long active;

    private double timeStamp;

    private Date createdTime;

    private Date updatedTime;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Column(name = "user_phone_id")
    public Long getUserPhoneId() {
        return userPhoneId;
    }

    public void setUserPhoneId(Long userPhoneId) {
        this.userPhoneId = userPhoneId;
    }

    @Column(name = "app_id")
    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    @Column(name = "alert_type")
    public Long getAlertType() {
        return alertType;
    }

    public void setAlertType(Long alertType) {
        this.alertType = alertType;
    }

    @Column(name ="notification_sound")
    public String getNotificationSound() {
        return notificationSound;
    }

    public void setNotificationSound(String notificationSound) {
        this.notificationSound = notificationSound;
    }

    @Column(name ="active")
    public Long getActive() {
        return active;
    }

    public void setActive(Long active) {
        this.active = active;
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
