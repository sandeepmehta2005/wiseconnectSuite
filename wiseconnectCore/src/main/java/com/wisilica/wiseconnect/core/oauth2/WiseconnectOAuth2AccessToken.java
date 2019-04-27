package com.wisilica.wiseconnect.core.oauth2;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

public class WiseconnectOAuth2AccessToken extends DefaultOAuth2AccessToken{
	
	private static final long serialVersionUID = 1L;	
	
	public WiseconnectOAuth2AccessToken(OAuth2AccessToken accessToken) {
		super(accessToken);
	}
}
