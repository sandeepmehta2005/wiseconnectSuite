package com.wisilica.wiseconnect.model.entity;
/**
 *  WiseConnect Entity User
 *
 *  @Modified Josny Jose
 *  @Date  27-Mar-2019
 *
 * Description : For table tbl_user
 *
 * */

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tbl_user")
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private Long organizationId;
	
	private Long customerId;
	
	private Long appId;

	private AppInfo appInfo;
	
	private Long bundleId;
	
	private String userName;
	
	private String name;
	
	private String displayName;
	
	private String email;
	
	private String mobile;
	
	private String password;
	
	private Integer type;
	
	private Integer statusId;
	
	private Long trackingSubscriptionId;
	
	private Double trackingMessageTimeStamp;
	
	private Double operationMessageTimeStamp;
	
	private Double otherMessageTimeStamp;
	
	private Date lastLogin;
	
	private Double timeStamp;
	
	private Date createdTime;
	
	private Date updatedTime;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "organization_id")
	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	@Column(name = "customer_id")
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	@Column(name = "app_id")
	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "app_id", insertable = false, updatable = false)
	public AppInfo getAppInfo() {
		return appInfo;
	}

	public void setAppInfo(AppInfo appInfo) {
		this.appInfo = appInfo;
	}

	@Column(name = "bundle_id")
	public Long getBundleId() {
		return bundleId;
	}

	public void setBundleId(Long bundleId) {
		this.bundleId = bundleId;
	}

	@Column(name = "user_name")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "user_display_name")
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@Column(name = "user_email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "user_mobile")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}	

	@Column(name = "user_password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "user_type")
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Column(name = "status")
	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	@Column(name = "tracking_subscription_id")
	public Long getTrackingSubscriptionId() {
		return trackingSubscriptionId;
	}

	public void setTrackingSubscriptionId(Long trackingSubscriptionId) {
		this.trackingSubscriptionId = trackingSubscriptionId;
	}

	@Column(name = "tracking_message_timestamp")
	public Double getTrackingMessageTimeStamp() {
		return trackingMessageTimeStamp;
	}

	public void setTrackingMessageTimeStamp(Double trackingMessageTimeStamp) {
		this.trackingMessageTimeStamp = trackingMessageTimeStamp;
	}

	@Column(name = "operation_message_timestamp")
	public Double getOperationMessageTimeStamp() {
		return operationMessageTimeStamp;
	}

	public void setOperationMessageTimeStamp(Double operationMessageTimeStamp) {
		this.operationMessageTimeStamp = operationMessageTimeStamp;
	}

	@Column(name = "other_message_timestamp")
	public Double getOtherMessageTimeStamp() {
		return otherMessageTimeStamp;
	}

	public void setOtherMessageTimeStamp(Double otherMessageTimeStamp) {
		this.otherMessageTimeStamp = otherMessageTimeStamp;
	}

	@Column(name = "last_login")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	@Column(name = "timestamp")
	public Double getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Double timeStamp) {
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


