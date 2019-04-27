package com.wisilica.wiseconnect.commons.constants;

public enum OperateModeEnum {
	
	GROUP(0),
	DEVICE(1),
	MULTI_GROUP(2),
	SCENE(3),	
	SCHEDULE(4);
	
	private final Integer id;
	
	private OperateModeEnum(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
	

	public static OperateModeEnum getById(Integer id) {
		if (id == null) {
			return null;
		}
		for (OperateModeEnum operateModeEnum : values()) {
			if (operateModeEnum.getId().equals(id)) {
				return operateModeEnum;
			}
		}
		return null;
	}
}
