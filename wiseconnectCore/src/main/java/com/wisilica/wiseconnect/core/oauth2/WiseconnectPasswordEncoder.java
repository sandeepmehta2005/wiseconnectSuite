package com.wisilica.wiseconnect.core.oauth2;
/**
 *  WWiseconnect PasswordEncoder
 *
 *  @Modified Josny Jose
 *  @Date  5-Apr-2019
 *
 * Description : implementation PasswordEncoder Interface
 * */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;

public class WiseconnectPasswordEncoder implements PasswordEncoder{

	private static final Logger LOGGER = LoggerFactory.getLogger(WiseconnectPasswordEncoder.class);

	@Override
	public String encode(CharSequence rawPassword) {
		return rawPassword.toString();
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		LOGGER.info("Checking the password");
		if (BCrypt.checkpw(rawPassword + "", encodedPassword)){
			LOGGER.info("Password Matches");
			return true;
		}else{
			LOGGER.info("Password not Matching");
			return false;
		}
	}
}
