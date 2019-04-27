package com.wisilica.wiseconnect.oauth2;
/**
 *  WiseConnect Security Configuration
 *
 *  @Modified By Josny Jose
 *  @Date  11-Mar-2019
 *
 * Description : Security implementation
 *
 * */

import com.wisilica.wiseconnect.core.oauth2.WiseconnectAuthenticationProvider;
import com.wisilica.wiseconnect.core.oauth2.WiseconnectEncoders;
import com.wisilica.wiseconnect.core.oauth2.WiseconnectUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@Import(WiseconnectEncoders.class)
public class WiseconnectSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private WiseconnectAuthenticationProvider wiseconnectAuthenticationProvider;

	@Autowired
	private WiseconnectUserDetailsServiceImpl userDetailsService;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(wiseconnectAuthenticationProvider)
				.userDetailsService(userDetailsService);
    }

    @Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/public/", "/oauth/token").permitAll()
				.antMatchers("/oauth/authorize").access("hasAuthority('ADMIN')")
				.and().exceptionHandling().accessDeniedPage("/403")
				.and()
				//.addFilterAt(customWiseconnectLoginFilter(), DefaultLoginPageGeneratingFilter.class)
				.formLogin()
				.permitAll()
				.and()
				.logout()
				.permitAll();
		http.cors().and().csrf().disable();
	}

}
