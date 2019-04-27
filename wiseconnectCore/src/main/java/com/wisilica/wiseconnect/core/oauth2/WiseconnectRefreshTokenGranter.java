package com.wisilica.wiseconnect.core.oauth2;

import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.refresh.RefreshTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

public class WiseconnectRefreshTokenGranter extends RefreshTokenGranter {

	public WiseconnectRefreshTokenGranter(AuthorizationServerTokenServices tokenServices,
			ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
		super(tokenServices, clientDetailsService, requestFactory);
	}

}
