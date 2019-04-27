package com.wisilica.wiseconnect.commons.constants;

public enum OperationEnum {
	
    //set on
	ON(1),
	//set off
	OFF(0),
	//set reset bit
	RESET_BIT(1),
	//set rgb operation type
	WISE_MESH_SET_RGB(19),
	//set device status operationId
	WISE_MESH_DEVICE_OP_ID(2),
	//set intensity
	WISE_MESH_SET_INTENSITY(14),
	//set fan speed
	WISE_MESH_SET_FAN_SPEED(15),
	//setwarm cool
	WISE_MESH_SET_WARM_COOL(16),
	//set muti sensor enable none
	WISE_MULTI_SENSOR_ENABLE_NONE(29),
	//set muti sensor enable pir
	WISE_MULTI_SENSOR_ENABLE_PIR(30),
	//set muti sensor enable ldr
	WISE_MULTI_SENSOR_ENABLE_LDR(31),
	//set muti sensor enable ldr max intensity
	WISE_MULTI_SENSOR_ENABLE_LDR_MAX_INTENSITY(32),
	//set muti sensor enable ldr min intensity
	WISE_MULTI_SENSOR_ENABLE_LDR_MIN_INTENSITY(33),
	//set muti sensor pir trigger interval
	WISE_MULTI_SENSOR_SET_PIR_TIMER_TRIGGER_INTERVAL(34),
	//set mesh shutter up
	WISE_MESH_SHUTTER_UP(38),
	//set mesh shutter down
	WISE_MESH_SHUTTER_DOWN(39),
	//set mesh shutter stop
	WISE_MESH_SHUTTER_STOP(40),
	//set mesh shutter spike up
	WISE_MESH_SHUTTER_SPIKE_UP(41),
	//set mesh shutter spike down
	WISE_MESH_SHUTTER_SPIKE_DOWN(42),
	//set mesh shutter lock
	WISE_MESH_SHUTTER_LOCK(43),
	//set mesh shutter unlock
	WISE_MESH_SHUTTER_UNLOCK(44),
	//set mesh shutter firmware update mode
	WISE_MESH_SHUTTER_FIRMWAREUPDATE_MODE(45),
	//set mesh shutter remote link with channel
	WISE_MESH_SHUTTER_LINK_REMOTE_WITH_CHANNEL(46),
	//set mesh shutter remote delink with channel
	WISE_MESH_SHUTTER_DELINK_REMOTE_WITH_CHANNEL(47),
	//set mesh mixer off
	WISE_MESH_MIXER_OFF(48),
	//set mesh mixer speed 1
	WISE_MESH_MIXER_SPEED_1(49),
	//set mesh mixer speed 2
	WISE_MESH_MIXER_SPEED_2(50),
	//set mesh mixer speed 3
	WISE_MESH_MIXER_SPEED_3(51),
	//set mesh mixer pulse
	WISE_MESH_MIXER_PULSE(52),
	//set mesh cooler off
	WISE_MESH_COOLER_OFF(53),
	//set mesh cooler speed 1
	WISE_MESH_COOLER_SPEED_1(54),
	//set mesh cooler speed 2
	WISE_MESH_COOLER_SPEED_2(55),
	//set mesh cooler speed 3
	WISE_MESH_COOLER_SPEED_3(56),
	//set mesh cooler swing
	WISE_MESH_COOLER_SWING(57),
	//set mesh cooler pumb
	WISE_MESH_COOLER_PUMB(58),
	//set mesh cooler swing pumb
	WISE_MESH_COOLER_SWING_PUMB(59),
	//set mesh cooler pumb swing off
	WISE_MESH_COOLER_PUMB_SWING_OFF(60),
	//set led bloom
	WISE_MESH_LEDBLOOM_DEVICE_COLOR(65),
	//set led bloomDEVICE_OPERATION_RULE_CACHE(40),
	WISE_MESH_LEDBLOOM_GROUP_COLOR(66),
	//set wind sensor
	WIND_SENSOR(82),
	//set osram
	OS_RAM(93),
	//mesh enable
	WISE_MESH_ENABLE_OP(95),
	//mesh disable
	WISE_MESH_DISABLE_OP(96),
	//set dual channel
	MESH_DEVICE_SET_DUAL_CHANNEL(127),
	//set group shutter up
	WISE_MESH_GROUP_SHUTTER_UP(137),
	//set group shutter down
	WISE_MESH_GROUP_SHUTTER_DOWN(138),
	//set group shutter skipe up
	WISE_MESH_GROUP_SHUTTER_SPIKE_UP(139),
	//set group shutter spike down
	WISE_MESH_GROUP_SHUTTER_SPIKE_DOWN(140),
	//set group shutter lock
	WISE_MESH_GROUP_SHUTTER_LOCK(141),
	//set group shutter unlock
	WISE_MESH_GROUP_SHUTTER_UNLOCK(142),
	//set group shutter stop
	WISE_MESH_GROUP_SHUTTER_STOP(143),
	//set group intensity
	WISE_MESH_SET_GROUP_INTENSITY(114),
	//set group warm cool
	WISE_MESH_SET_GROUP_WARM_COOL(116),
	//set group fan speed
	WISE_MESH_SET_GROUP_FAN_SPEED(115),
	//set group rgb on
	WISE_MESH_SET_GROUP_RGB_ON(117),
	//set group rgb off
	WISE_MESH_SET_GROUP_RGB_OFF(118),
	//set group rgb off
	WISE_MESH_SET_GROUP_RGB(119),
	//set device off
	MESH_DEVICE_OFF_V2(500),
	//set device on
	MESH_DEVICE_ON_V2(501),
	//set device status
	WISE_MESH_STATUS_PACKET_V2(502),
	//set device status fan
	WISE_MESH_STATUS_PACKET_V2_FAN(5021),
	//set device intensity
	WISE_MESH_SET_INTENSITY_V2(503),
	//set device warm cool
	WISE_MESH_SET_WARM_COOL_V2(504),
	//set fan speed
	WISE_MESH_SET_FAN_SPEED_V2(505),
	//set fan direction 
	WISE_MESH_SET_FAN_DRECTION_V2(506),
	//set  rgb on
	WISE_MESH_SET_RGB_ON_V2(507),
	//set rgb off
	WISE_MESH_SET_RGB_OFF_V2(508),
	//set fan off
	MESH_DEVICE_FAN_OFF_V2(523),
	//set fan on
	MESH_DEVICE_FAN_ON_V2(524),
	//scene invocation TBD
	WISE_MESH_SCENE_INVOCATION(527),
	//time sync broadcast
	WISE_MESH_TIME_BROADCAST(72),
	//ota hash response operation id
	MESH_DEVICE_OTA_HASH_RESPONSE_OP(73),
	WISE_FIRMWARE_UPDATE_FEEDBACK(74),
	//device profile change operation
	WISE_MESH_PROFILE_CHANGE(600),
	//device specific time sync
	WISE_MESH_TIME_SYNC(601),
	//device specific firmware request
	WISE_MESH_FIRMWARE_CHECK(602),
	//schedule set
	WISE_MESH_SCHEDULE_SET_SCENE(603),
	//schedule set device
	WISE_MESH_SCHEDULE_SET_DEVICE(604),
	//schedule set group
	WISE_MESH_SCHEDULE_SET_GROUP(605),
	//schedule delete
	WISE_MESH_SCHEDULE_DELETE(606),	
	//Dali sub device add operation
	DALI_DEVICE_ADD_OPERATION(700),
	//group-device association
	WISE_MESH_ADD_GROUP(7),
	WISE_MESH_EDIT_GROUP(8),
	WISE_MESH_DELETE_GROUP(9),
	//iBecon Listen Enable
	MESH_DEVICE_IBEACON_LISTEN_ENABLE(557),
	//iBecon Listen Disable
	MESH_DEVICE_IBEACON_LISTEN_DISABLE(558),
	WISE_MESH_SET_SCENE_ON(510),
	WISE_MESH_SET_SCENE_OFF(511),
	WISE_MESH_SET_SCENE_INTENSITY(512),
	WISE_MESH_SET_SCENE_WARM_COOL(513),
	WISE_MESH_SET_SCENE_FAN_SPEED(514),
	WISE_MESH_SET_SCENE_FAN_DRECTION(515),
	WISE_MESH_SET_SCENE_SET_RGB_ON(516),
	WISE_MESH_SET_SCENE_SET_RGB_OFF(517),
	WISE_MESH_SET_SCENE_DELINK(518),
	WISE_MESH_SET_SCENE_FAN_ON(525),
	WISE_MESH_SET_SCENE_FAN_OFF(526),
	MESH_DEVICE_OFF_GROUP_V2( 500),
	MESH_DEVICE_ON_GROUP_V2( 501),
	WISE_MESH_SET_INTENSITY_GROUP_V2( 503),
	WISE_MESH_SET_WARM_COOL_GROUP_V2( 504),
	WISE_MESH_SET_FAN_SPEED_GROUP_V2( 505),
	WISE_MESH_SET_FAN_DRECTION_GROUP_V2( 506),
	WISE_MESH_SET_RGB_ON_GROUP_V2( 507),
	WISE_MESH_SET_RGB_OFF_GROUP_V2( 508),
	MESH_DEVICE_FAN_OFF_GROUP_V2( 523),
	MESH_DEVICE_FAN_ON_GROUP_V2( 524);
	
	
	
	private final Integer id;
	
	private OperationEnum(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}

	public static OperationEnum getById(Integer id) {
		if (id == null) {
			return null;
		}
		for (OperationEnum operationEnum : values()) {
			if (operationEnum.getId().equals(id)) {
				return operationEnum;
			}
		}
		return null;
	}
}
