package com.wisilica.wiseconnect.commons.constants;

public enum DeviceTypeEnum {

	NEW_RAYMX(1010), NEW_MESH_T5(1013), FAN(7001), MULTI_SENSOR(2009), OS_RAM_TYPE(1017), CEILING_FAN(1501),
	RGB_CCT_LAMP(1021), CCT_TUBE(1022), CCT_2X4_RETROFIT_KIT(1502), BULB_NEW(1024), DIMMABLE_BULB_NEW(1023),
	RGB_LED_NEW(1026), SWITCH_SENSOR(2018), WISE_BRIDGE(13001);

	private final Integer id;

	private DeviceTypeEnum(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
	
	public static DeviceTypeEnum getById(Integer id) {
		if (id == null) {
			return null;
		}
		for (DeviceTypeEnum deviceTypeEnum : values()) {
			if (deviceTypeEnum.getId().equals(id)) {
				return deviceTypeEnum;
			}
		}
		return null;
	}

}
