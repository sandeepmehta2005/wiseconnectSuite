package com.wisilica.wiseconnect.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "tbl_user_login_info")
public class UserLoginInfo implements Serializable {	
	
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Long userId;
	
	private User user;
	
	private Long organizationId;
	
	private Long userPhoneId;
	
	private Long appId;
	
	private String sessionId;
	
	private String token;
	
	private Boolean sessionExpired;
	
	private String publicKey;
	
	private String privateKey;
	
	private Date createdTime;
	
	private Date lastUpdatedTime;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "organization_id")
	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
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

	@Column(name = "session_id")
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	@Column(name = "token")
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Column(name = "session_expired_or_not")
	public Boolean isSessionExpired() {
		return sessionExpired;
	}

	public void setSessionExpired(Boolean sessionExpired) {
		this.sessionExpired = sessionExpired;
	}

	@Column(name = "public_key")
	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	@Column(name = "private_key")
	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
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
