package com.wisilica.wiseconnect.core.oauth2;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.wisilica.wiseconnect.model.entity.User;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

public class WiseconnectAuthorizationCodeTokenGranter extends AuthorizationCodeTokenGranter{

	public WiseconnectAuthorizationCodeTokenGranter(AuthorizationServerTokenServices tokenServices,
			AuthorizationCodeServices authorizationCodeServices, ClientDetailsService clientDetailsService,
			OAuth2RequestFactory requestFactory) {
		super(tokenServices, authorizationCodeServices, clientDetailsService, requestFactory);		
	}
	
	@Override
	protected OAuth2AccessToken getAccessToken(ClientDetails client, TokenRequest tokenRequest) {		
		OAuth2AccessToken oAuth2AccessToken = super.getAccessToken(client, tokenRequest);
		WiseconnectOAuth2AccessToken wiseconnectOAuth2AccessToken = new WiseconnectOAuth2AccessToken(oAuth2AccessToken);
        User user = new User();
        user.setAppId(2L);
        Map<String, Object> additionalParamMap = new HashMap<>();
        additionalParamMap.put("additional_values", user);
        additionalParamMap.put("application ID", tokenRequest.getRequestParameters());
		additionalParamMap.put("client details", client.getAdditionalInformation().values());
		wiseconnectOAuth2AccessToken.setAdditionalInformation(additionalParamMap);
		return wiseconnectOAuth2AccessToken;
	}

}
