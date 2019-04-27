package com.wisilica.wiseconnect.core.oauth2;
/**
 *  WWiseconnect UserDetailsServiceImpl implements UserDetailsService
 *
 *  @Modified Josny Jose
 *  @Date  27-Mar-2019
 *
 * Description : implementation Spring Boot Oauth2 UserDetailsService
 *
 * */

import com.wisilica.wiseconnect.dao.repository.RolesRepository;
import com.wisilica.wiseconnect.dao.repository.UserRepository;
import com.wisilica.wiseconnect.dao.repository.UserRoleRepository;
import com.wisilica.wiseconnect.model.entity.Roles;
import com.wisilica.wiseconnect.model.entity.User;
import com.wisilica.wiseconnect.model.entity.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class WiseconnectUserDetailsServiceImpl implements UserDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(WiseconnectUserDetailsServiceImpl.class);
	@Autowired
	private UserRepository userRepository;

	@Autowired
    private UserRoleRepository userRoleRepository;

	@Autowired
	private RolesRepository rolesRepository;

	@Transactional(readOnly = true)
	@Override
	public UserDetails loadUserByUsername(String username) {
	    /* Fetching User by username from the DB*/
		User user = userRepository.findByUserName(username);
        if(user == null){
            LOGGER.error("User not found for : {}", username);
            throw new UsernameNotFoundException("User not found for " + username);
        }

        /*Fetching UserRoles by userId from db*/
        List<UserRole> userRoles = userRoleRepository.findByUserId(user.getId().intValue());
        List<String> roleNames = new ArrayList();
        if(userRoles != null){
            for(UserRole userRole : userRoles){
                Roles roles = rolesRepository.findByRoleId(userRole.getUserRoleId());
                roleNames.add(roles.getRoleName());/*Adding roleNames to a list*/
            }
        }

        /*Iterating the user roles and set to granted authority*/
        List<GrantedAuthority> grantList = new ArrayList<>();
        if (!roleNames.isEmpty()) {
            for (String role : roleNames) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }

        return new WiseconnectUserDetails(user,grantList);
	}
}
