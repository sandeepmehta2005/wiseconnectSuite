/*
package com.wisilica.wiseconnect.commons.config.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {

	@Value("#{'${redis.clusterNodes}'.split(',')}")
	private List<String> clusterNodes;

	@Value("${redis.password}")
	private String password;

	@Value("${redis.minIdle}")
	private Integer minIdle = 0;

	@Value("${redis.maxIdle}")
	private Integer maxIdle = 1024;

	@Value("${redis.maxTotal}")
	private Integer maxTotal;

	@Value("${redis.ssl}")
	private boolean ssl;

	@Value("${redis.timeout}")
	private Integer timeout = 2000;

	@Bean
	public RedisConnectionFactory getRedisConnectionFactory() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxIdle(maxIdle);
		poolConfig.setMinIdle(minIdle);
		poolConfig.setMaxTotal(maxTotal);
		poolConfig.setTestOnBorrow(true);
		poolConfig.setTestOnReturn(true);
		poolConfig.setTestWhileIdle(true);

		return new JedisConnectionFactory(new RedisClusterConfiguration(clusterNodes), poolConfig);
	}
	
	@Bean
	public RedisSerializer<String> getKeySerializer() {
		return new StringRedisSerializer();
	}

	@Bean
	public RedisTemplate<String, String> redisTemplate() {
		RedisTemplate<String, String> template = new RedisTemplate<>();
		template.setConnectionFactory(getRedisConnectionFactory());
		RedisSerializer<String> redisSerializer = getKeySerializer();
		template.setDefaultSerializer(redisSerializer);
		return template;
	}

	public List<String> getClusterNodes() {
		return clusterNodes;
	}

	public void setClusterNodes(List<String> clusterNodes) {
		this.clusterNodes = clusterNodes;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(Integer minIdle) {
		this.minIdle = minIdle;
	}

	public Integer getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(Integer maxIdle) {
		this.maxIdle = maxIdle;
	}

	public Integer getMaxTotal() {
		return maxTotal;
	}

	public void setMaxTotal(Integer maxTotal) {
		this.maxTotal = maxTotal;
	}

	public boolean isSsl() {
		return ssl;
	}

	public void setSsl(boolean ssl) {
		this.ssl = ssl;
	}

	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}
}
*/
