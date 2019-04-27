package com.wisilica.wiseconnect.core.service;
/**
 *  WiseConnect Authentication Service
 *
 *  @Modified By Josny Jose
 *  @Date  14-Mar-2019
 *
 * Description : Service Methods declaration for authorization Module
 *
 * */

import com.wisilica.wiseconnect.model.entity.Bundle;
import com.wisilica.wiseconnect.model.entity.UserLoginInfo;
import com.wisilica.wiseconnect.model.response.AuthResponse;
import org.springframework.stereotype.Service;

import com.wisilica.wiseconnect.model.util.UserLoginContext;

@Service
public interface AuthenticationService {
	
	UserLoginContext getUserLoginContext(String token, Long organizationId);

	/* Methods used for App Registration */

	UserLoginInfo getUserLoginContext(Long organizationId);

	AuthResponse appRegistration(String bundleClientId, String bundlePackage, String deviceId, String sessionId, Long organizationId, String token, String appVersion, String modelInfo, String osInfo);

	/* Methods used for App Registration updation */

	AuthResponse getAppDetails(Long appId, Long organizationId);

	AuthResponse deviceTokenUpdate(String deviceToken, Long organizationId, Long appId, Long userId);

	/* Methods used for Login */
	AuthResponse loginAuthorize(String userName, String password, Long appId);
}
