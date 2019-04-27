package com.wisilica.wiseconnect.oauth2;
/**
 *  WiseConnect Security Configuration
 *
 *  @Modified By Josny Jose
 *  @Date  11-Mar-2019
 *
 *  @Modified By Bhaskaran
 *  @Date 25-Mar-2019
 *
 * Description : Security implementation
 *
 * */

import com.wisilica.wiseconnect.core.oauth2.WiseconnectTokenEnhancer;
import com.wisilica.wiseconnect.core.oauth2.WiseconnectTokenStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
@Import(WiseconnectSecurityConfiguration.class)
public class WiseconnectAuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

	@Autowired
	@Qualifier("masterDataSource")
	private DataSource dataSource;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder oauthClientPasswordEncoder;

	@Bean
	public TokenStore tokenStore() {
		return new WiseconnectTokenStore(dataSource);
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.jdbc(dataSource);
	}
	@Bean
	public TokenEnhancer tokenEnhancer() {
		return new WiseconnectTokenEnhancer();
	}

	@Override
	public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		final WiseconnectTokenEnhancer wiseconnectTokenEnhancer = new WiseconnectTokenEnhancer();
		endpoints.tokenStore(tokenStore()).tokenEnhancer(wiseconnectTokenEnhancer).authenticationManager(authenticationManager)
        .userDetailsService(userDetailsService);
	}

	/*oauthserver access permitted to all clients*/
	@Override
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()")
				.passwordEncoder(oauthClientPasswordEncoder);
	}

}
