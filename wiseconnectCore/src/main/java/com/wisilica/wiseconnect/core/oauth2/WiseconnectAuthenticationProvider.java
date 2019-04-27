package com.wisilica.wiseconnect.core.oauth2;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
@Import(WiseconnectEncoders.class)
public class WiseconnectAuthenticationProvider extends DaoAuthenticationProvider{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(WiseconnectAuthenticationProvider.class);

	@Autowired
    private UserDetailsService userDetailsService;
	
    @Autowired
    private PasswordEncoder userPasswordEncoder;	
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		LOGGER.info("On Custom auth provider: {}", request);
		super.additionalAuthenticationChecks(userDetails, authentication);
	}	
	
	@Override
	protected void doAfterPropertiesSet() throws Exception {
		setUserDetailsService(userDetailsService);
		setPasswordEncoder(userPasswordEncoder);
		super.doAfterPropertiesSet();
	}
}
