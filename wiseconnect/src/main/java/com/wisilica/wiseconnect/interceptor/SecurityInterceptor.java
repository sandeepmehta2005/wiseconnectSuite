package com.wisilica.wiseconnect.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.wisilica.wiseconnect.commons.constants.ResponseCodeEnum;
import com.wisilica.wiseconnect.commons.exception.ControllerException;
import com.wisilica.wiseconnect.core.service.AuthenticationService;
import com.wisilica.wiseconnect.model.util.UserLoginContext;
import com.wisilica.wiseconnect.util.WebUtil;

@Component
public class SecurityInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityInterceptor.class);

	private static final String SECURITY_TOKEN_HEADER_NAME = "token";

	private static final String ORGANIZATION_ID_HEADER_NAME = "organizationId";
	
	private static final String PHONE_ID_HEADER_NAME = "phoneId";

	@Autowired
	private AuthenticationService authenticationService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// completed only basic validation. Many checks are pending
		String authToken = WebUtil.getHeader(SECURITY_TOKEN_HEADER_NAME);		
		Long organiZationId = null;
		try{
			organiZationId = Long.valueOf(WebUtil.getHeader(ORGANIZATION_ID_HEADER_NAME).trim());
		}catch(Exception ex) {
			LOGGER.error("Invalid organiZationId");
			throw new ControllerException(ResponseCodeEnum.INVALID_AUTH, "Invlid organiZationId");
		}
		
		if(authToken == null || organiZationId == null) {
			LOGGER.error("Invalid Header  authToken: {}, organiZationId: {}", authToken, organiZationId);
			throw new ControllerException(ResponseCodeEnum.INVALID_HEADER, "Invalid Header");
		}
		Long phoneId = null;
		try{
			phoneId = Long.valueOf(WebUtil.getHeader(PHONE_ID_HEADER_NAME).trim());
		}catch(Exception ex) {
			LOGGER.error("Invalid phoneId");
			throw new ControllerException(ResponseCodeEnum.INVALID_AUTH, "Invlid phoneId");
		}
		
		UserLoginContext userLoginContext = authenticationService.getUserLoginContext(authToken, organiZationId);
		if(userLoginContext == null) {
			LOGGER.error("Invalid Auth details ");
			throw new ControllerException(ResponseCodeEnum.INVALID_AUTH, "Invlid Auth details");			
		}
		
		if(phoneId != null && userLoginContext.getPhoneId() != null && !phoneId.equals(userLoginContext.getPhoneId())) {
			LOGGER.error("Invalid phoneId. Not matching with user phoneId");
			throw new ControllerException(ResponseCodeEnum.INVALID_AUTH, "Invlid Auth details");
		}
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Logged in user context ==>  userId: {} userTypeId: {} userPhoneId: {} OrganizationId: {} customerId: {}",
					userLoginContext.getUserId(), userLoginContext.getUserTypeId(), userLoginContext.getPhoneId(),
					userLoginContext.getOrganizationId(), userLoginContext.getCustomerId());
		}
		
		userLoginContext.setLocale(request.getLocale());
		WebUtil.setUserLoginContext(userLoginContext);		
		return true;
	}	
}
