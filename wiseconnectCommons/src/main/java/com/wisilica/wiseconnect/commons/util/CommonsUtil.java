package com.wisilica.wiseconnect.commons.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class CommonsUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonsUtil.class);
	
	public static final String UNIQUE_INSTANCE_ID = "unique_RequestId";
	
	private CommonsUtil() {}
	
	public static Double getCurrentTimeStamp() {
		return Double.valueOf(System.nanoTime())/Double.valueOf(1000000000);
	}
	
	public static String describeObject(Object object) {
		Map<String, String> propertyMap = null;
		try {
			propertyMap = BeanUtils.describe(object);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			LOGGER.error("Error while describibg the object {}", object, e);
			return null;
		}
		StringBuilder builder = new StringBuilder();
		propertyMap.forEach((k, v) -> builder.append(k).append(" : ").append(v).append(System.lineSeparator()));
		return builder.toString();
	}
}
