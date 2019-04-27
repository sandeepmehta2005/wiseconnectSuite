package com.wisilica.wiseconnect.commons.constants;

public enum APIIdEnum {

	REMOTE_OPERATION("/public/operate/", 22);

	private String url;

	private Integer id;

	APIIdEnum(String url, Integer id) {
		this.url = url;
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public Integer getId() {
		return id;
	}

	public static APIIdEnum getByUrl(String url) {
		if (url == null) {
			return null;
		}
		for (APIIdEnum apiId : values()) {
			if (apiId.getUrl().equalsIgnoreCase(url)) {
				return apiId;
			}
		}
		return null;
	}
}
