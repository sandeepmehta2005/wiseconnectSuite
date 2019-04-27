package com.wisilica.wiseconnect.model;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractWisconnectBean {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractWisconnectBean.class);

	@Override
	public String toString() {
		Map<String, String> propertyMap = null;
		try {
			propertyMap = BeanUtils.describe(this);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			LOGGER.error("Error while getting the bean properties. Returning the Super.toString()", e);
			return super.toString();
		}
		StringBuilder builder = new StringBuilder();
		builder.append(System.lineSeparator());
		propertyMap.forEach((k, v) -> builder.append(k).append(" : ").append(v).append(System.lineSeparator()));
		return builder.toString();
	}
}
