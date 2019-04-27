package com.wisilica.wiseconnect.model.entity;
/**
 *  Wiseconnect AppInfo
 *
 *  @author Bhaskaran
 *  @Date  19-Mar-2019
 *
 * Description : Entity for tbl_socket_authentication
 *
 * */

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_socket_authentication")
public class SocketAuthentication {

    private Long id;

    private Long userId;

    private String authtoken;

    private String socketSession;

    private Long userPhoneId;

    private Long orgId;

    private Long subOrgId;

    private Long isWiseBridge;

    private Date createdTime;

    private Date lastUpdatedTime;

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

    @Column(name = "authtoken")
    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    @Column(name = "socket_session")
    public String getSocketSession() {
        return socketSession;
    }

    public void setSocketSession(String socketSession) {
        this.socketSession = socketSession;
    }

    @Column(name = "user_phone_id")
    public Long getUserPhoneId() {
        return userPhoneId;
    }

    public void setUserPhoneId(Long userPhoneId) {
        this.userPhoneId = userPhoneId;
    }
    @Column(name = "org_id")
    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
    @Column(name = "sub_org_id")
    public Long getSubOrgId() {
        return subOrgId;
    }

    public void setSubOrgId(Long subOrgId) {
        this.subOrgId = subOrgId;
    }

    @Column(name = "is_wiseBridge")
    public Long getIsWiseBridge() {
        return isWiseBridge;
    }

    public void setIsWiseBridge(Long isWiseBridge) {
        this.isWiseBridge = isWiseBridge;
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
