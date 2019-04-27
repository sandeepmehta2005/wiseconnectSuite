package com.wisilica.wiseconnect.oauth2;
/**
 *  WiseConnect Resource Server Configuration
 *
 *  @Modified By Josny Jose
 *  @Date  11-Mar-2019
 *
 * Description : Resource Server implementation
 *
 * */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableResourceServer
public class WiseconnectResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	private static final String RESOURCE_ID = "resource-server-rest-api";
	private static final String SECURED_READ_SCOPE = "#oauth2.hasScope('read')";
	private static final String SECURED_WRITE_SCOPE = "#oauth2.hasScope('write')";
	private static final String SECURED_PATTERN = "/secured/**";

	@Autowired
	private TokenStore tokenStore;

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) {
		resources.tokenStore(tokenStore).resourceId(RESOURCE_ID).stateless(false);
	}
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
				.requestMatchers()
				.antMatchers(SECURED_PATTERN)
				.and()
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, SECURED_PATTERN)
				.access(SECURED_WRITE_SCOPE)
				.anyRequest()
				.access(SECURED_READ_SCOPE);
	}

}
