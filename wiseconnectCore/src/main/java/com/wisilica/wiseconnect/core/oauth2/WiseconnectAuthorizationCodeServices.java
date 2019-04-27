package com.wisilica.wiseconnect.core.oauth2;

import com.wisilica.wiseconnect.core.service.AuthenticationService;
import com.wisilica.wiseconnect.model.entity.UserLoginInfo;
import com.wisilica.wiseconnect.model.response.AuthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class WiseconnectAuthorizationCodeServices extends JdbcAuthorizationCodeServices {

    private static final Logger LOGGER = LoggerFactory.getLogger(WiseconnectAuthorizationCodeServices.class);

    @Autowired
    private AuthenticationService authenticationService;

    public WiseconnectAuthorizationCodeServices(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public String createAuthorizationCode(OAuth2Authentication authentication) {
        Map<String, String> requestParameters = authentication.getOAuth2Request().getRequestParameters();

        requestParameters.entrySet().stream().forEach(kv ->{
            LOGGER.info("Parameter Name: {}, value: {}", kv.getKey(), kv.getValue());
        });

        String bundleClientId = null, bundlePackage = null, deviceId = null, sessionId = null, deviceToken = null, appVersion = null, modelInfo = null, osInfo = null;
        Long organizationId = null;

        final Map<String, Object> additionalInfo = new HashMap<>();

        for (Map.Entry<String,String> entry : requestParameters.entrySet()){

            LOGGER.info("Parameter Name from AuthorizationCodeServices: {}, value: {}", entry.getKey(), entry.getValue());

            switch (entry.getKey()){
                case "organizationId":
                    organizationId = Long.valueOf(entry.getValue());
                    break;
                case "bundleClientId":
                    bundleClientId = entry.getValue();
                    break;
                case "bundlePackage":
                    bundlePackage = entry.getValue();
                    break;
                case "deviceId":
                    deviceId = entry.getValue();
                    break;
                case "sessionId":
                    sessionId = entry.getValue();
                    break;
                case "deviceToken":
                    deviceToken = entry.getValue();
                    break;
                case "appVersion":
                    appVersion = entry.getValue();
                    break;
                case "modelInfo":
                    modelInfo = entry.getValue();
                    break;
                case "osInfo":
                    osInfo = entry.getValue();
                    break;
            }

        }

        LOGGER.info("Parameter Name from AuthorizationCodeServices organizationId: {} " , organizationId);
        LOGGER.info("Parameter Name from AuthorizationCodeServices bundleClientId: {} " , bundleClientId);
        LOGGER.info("Parameter Name from AuthorizationCodeServices bundlePackage: {} " , bundlePackage);
        LOGGER.info("Parameter Name from AuthorizationCodeServices deviceId: {} " , deviceId);
        LOGGER.info("Parameter Name from AuthorizationCodeServices sessionId: {} " , sessionId);

        AuthResponse authResponse = authenticationService.appRegistration(bundleClientId, bundlePackage, deviceId, sessionId, organizationId, deviceToken, appVersion, modelInfo, osInfo);

        return super.createAuthorizationCode(authentication);
    }

}
