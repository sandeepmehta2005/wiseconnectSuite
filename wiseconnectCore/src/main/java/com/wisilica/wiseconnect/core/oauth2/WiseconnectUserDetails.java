package com.wisilica.wiseconnect.core.oauth2;
/**
 *  WWiseconnect UserDetails implements UserDetails extends User
 *
 *  @Modified Josny Jose
 *  @Date  27-Mar-2019
 *
 * Description : implementation Spring Boot Oauth2 UserDetails and User
 * */
import com.wisilica.wiseconnect.model.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class WiseconnectUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;

	private User user;

	private List<GrantedAuthority> authorities;

	public WiseconnectUserDetails(User user, List<GrantedAuthority> authorities) {
		this.user = user;
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
