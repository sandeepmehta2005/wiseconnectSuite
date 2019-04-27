package com.wisilica.wiseconnect.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.wisilica.wiseconnect.core.service.MessageService;

@Service
public class RedisServiceImpl implements MessageService {	
	
	@Autowired
    private RedisTemplate< String, String > redisTemplate;

	@Override
	public void publish(String message, String channel) {
		redisTemplate.convertAndSend(channel, message);
	}

	@Override
	public String getValue(String key) {
		return redisTemplate.opsForValue().get(key);		
	}

	@Override
	public void setValue(String key, String value) {
		redisTemplate.opsForValue().set(key, value);
	}
}
