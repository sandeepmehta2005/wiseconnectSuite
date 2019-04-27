package com.wisilica.wiseconnect.core.service;

import org.springframework.stereotype.Service;

@Service
public interface MessageService {
	
	void publish(String message, String channel);
	
	String getValue(String key);
	
	void setValue(String key, String value);

}
