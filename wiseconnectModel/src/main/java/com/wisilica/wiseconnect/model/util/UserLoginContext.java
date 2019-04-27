package com.wisilica.wiseconnect.model.util;

import java.util.Locale;

public class UserLoginContext {

	private Long userId;

	private Long phoneId;

	private Long organizationId;

	private Integer userTypeId;

	private Long customerId;
	
	private Locale locale;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getPhoneId() {
		return phoneId;
	}

	public void setPhoneId(Long phoneId) {
		this.phoneId = phoneId;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Integer getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(Integer userTypeId) {
		this.userTypeId = userTypeId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}
}
