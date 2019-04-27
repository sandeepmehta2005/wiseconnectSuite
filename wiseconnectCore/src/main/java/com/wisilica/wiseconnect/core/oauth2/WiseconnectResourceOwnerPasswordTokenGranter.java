package com.wisilica.wiseconnect.core.oauth2;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.password.ResourceOwnerPasswordTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import com.wisilica.wiseconnect.core.oauth2.WiseconnectOAuth2AccessToken;

public class WiseconnectResourceOwnerPasswordTokenGranter extends ResourceOwnerPasswordTokenGranter{

	public WiseconnectResourceOwnerPasswordTokenGranter(AuthenticationManager authenticationManager,
			AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService,
			OAuth2RequestFactory requestFactory) {
		super(authenticationManager, tokenServices, clientDetailsService, requestFactory);		
	}

	@Override
	protected OAuth2AccessToken getAccessToken(ClientDetails client, TokenRequest tokenRequest) {		
		OAuth2AccessToken oAuth2AccessToken = super.getAccessToken(client, tokenRequest);
		WiseconnectOAuth2AccessToken wiseconnectOAuth2AccessToken = new WiseconnectOAuth2AccessToken(oAuth2AccessToken);
		Map<String, Object> additionalParamMap = new HashMap<>();
		additionalParamMap.put("Param1", "Value1");
		additionalParamMap.put("CurrentDate", new Date());
		wiseconnectOAuth2AccessToken.setAdditionalInformation(additionalParamMap);
		return wiseconnectOAuth2AccessToken;
	}
	
	
}
