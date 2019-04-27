package com.wisilica.wiseconnect.controller;
/**
 *  WiseConnect Security Configuration
 *
 *  @Author Bhaskaran
 *  @Date 28-Mar-2019
 *
 * Description : Security implementation
 *
 * */
import com.wisilica.wiseconnect.core.oauth2.WiseconnectTokenStore;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@FrameworkEndpoint
public class RevokeTokenEndpoint {

    private WiseconnectTokenStore tokenStore;

    @GetMapping(value = "tokens/revokeAccessToken/{token}")
    public @ResponseBody ResponseEntity<HttpStatus> logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null) {
            try {
                String tokenValue = authHeader.replace("Bearer", "").trim();
                OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenValue);
                tokenStore.removeAccessToken(accessToken);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}