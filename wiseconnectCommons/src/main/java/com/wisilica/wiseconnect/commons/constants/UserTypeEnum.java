package com.wisilica.wiseconnect.commons.constants;

public enum UserTypeEnum {

	ANONYMOUS(-1, "anonymous"), ROOT_ADMIN(1, "root-admin"), MANAGER(2, "manager"), STANDARD_USER(3, "standard-user"),
	TECH_SUPPORT(4, "tech-support"), BRIDGE_USER(5, "bridge-user"), SUPER_ADMIN(7, "super-admin"),
	CUSTOMER_USER(8, "customer-user"), SECURITY(9, "security"), SUPPORT_USER(13, "support-user");

	private String role;

	private Integer id;

	UserTypeEnum(Integer id, String role) {
		this.id = id;
		this.role = role;
	}

	public static UserTypeEnum getById(Integer id) {
		if (id == null) {
			return ANONYMOUS;
		}
		for (UserTypeEnum userType : values()) {
			if (userType.getId().equals(id)) {
				return userType;
			}
		}
		return ANONYMOUS;
	}

	public String getRole() {
		return role;
	}

	public Integer getId() {
		return id;
	}
}
