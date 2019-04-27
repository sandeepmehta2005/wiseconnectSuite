package com.wisilica.wiseconnect.core.oauth2;
/**
 *  WiseConnect Token
 *
 *  @author Josny Jose
 *  @Date  24-Mar-2019
 *
 * Description : Enhancing access Token with custom parameter
 *
 * */

import com.wisilica.wiseconnect.core.service.AuthenticationService;
import com.wisilica.wiseconnect.core.util.*;
import com.wisilica.wiseconnect.model.entity.UserLoginInfo;
import com.wisilica.wiseconnect.model.response.AuthResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;


public class WiseconnectTokenEnhancer implements TokenEnhancer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WiseconnectTokenEnhancer.class);

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        final Map<String, String> requestParameters = authentication.getOAuth2Request().getRequestParameters();

       /* define the variables for input parameters*/
        String bundleClientId = null;
        String bundlePackage = null;
        String deviceId = null;
        String sessionId = null;
        String deviceToken = null;
        String appVersion = null;
        String modelInfo = null;
        String osInfo = null;
        Long organizationId = null;
        Long appId = null;

        /** Setting custom input parameters to variables*/
        for (Map.Entry<String,String> entry : requestParameters.entrySet()){

            switch (entry.getKey()){
                case StaticVariables.ORGANIZATION_ID:
                    organizationId = Long.valueOf(entry.getValue());
                    LOGGER.info("Parameter Name - organizationId: {} " , organizationId);
                    break;
                case StaticVariables.BUNDLE_CLIENT_ID:
                    bundleClientId = entry.getValue();
                    LOGGER.info("Parameter Name - bundleClientId: {} " , bundleClientId);
                    break;
                case StaticVariables.BUNDLE_PACKAGE:
                    bundlePackage = entry.getValue();
                    LOGGER.info("Parameter Name - bundlePackage: {} " , bundlePackage);
                    break;
                case StaticVariables.DEVICE_ID:
                    deviceId = entry.getValue();
                    LOGGER.info("Parameter Name - deviceId: {} " , deviceId);
                    break;
                case StaticVariables.SESSION_ID:
                    sessionId = entry.getValue();
                    LOGGER.info("Parameter Name - sessionId: {} " , sessionId);
                    break;
                case StaticVariables.DEVICE_TOKEN:
                    deviceToken = entry.getValue();
                    LOGGER.info("Parameter Name - deviceToken: {} " , deviceToken);
                    break;
                case StaticVariables.APP_VERSION:
                    appVersion = entry.getValue();
                    LOGGER.info("Parameter Name - appVersion: {} " , appVersion);
                    break;
                case StaticVariables.MODEL_INFO:
                    modelInfo = entry.getValue();
                    LOGGER.info("Parameter Name - modelInfo: {} " , modelInfo);
                    break;
                case StaticVariables.OS_INFO:
                    osInfo = entry.getValue();
                    LOGGER.info("Parameter Name - osInfo: {} " , osInfo);
                    break;
                case StaticVariables.APP_ID:
                    appId = Long.valueOf(entry.getValue());
                    LOGGER.info("Parameter Name - appId: {} " , appId);
                    break;
                default:
                    LOGGER.info("Getting input parameters");
                    break;

            }
        }

        WiseStatus wiseStatus = new WiseStatus();

//        if(appId != null ){
//
//            //
//            AuthResponse authResponse = authenticationService.getAppDetails(appId, organizationId);
//            if(authResponse.getStatusCode() != StaticVariables.IS_ONE){
//                wiseStatus.setStatusCode(StatusCodes.INVALID_ORGANIZATION_APP_ID);
//                wiseStatus.setStatusMessage(StatusMessages.INVALID_ORG_APPID_MSG);
//            }
//        }

        /* Code For Application Registration starts here
         * Register app information/update app registration count*/

        wiseStatus.setApiId(APIIdConstants.APP_REGISTRATION_API);


        if(deviceId != null && !deviceId.matches("/^[a-zA-Z0-9._]+$/")){ /*Validation for device id & organization id*/
            wiseStatus.setStatusCode(StatusCodes.INPUT_VALIDATION_ERROR);
            wiseStatus.setStatusMessage(StatusMessages.INVALID_DEVICE_ID);
        }else{
            AuthResponse authResponse = authenticationService.appRegistration(bundleClientId, bundlePackage, deviceId, sessionId, organizationId, deviceToken, appVersion, modelInfo, osInfo);
            if(authResponse.getAppId() > StaticVariables.IS_ZERO ){
                wiseStatus.setStatusCode(StatusCodes.SUCCESS_CODE);
                wiseStatus.setStatusMessage(authResponse.getMessage());

            }else{
                wiseStatus.setStatusCode(StatusCodes.FAILED);
                wiseStatus.setStatusMessage(StatusMessages.APP_INFO_FAILED);
            }

        }
         /*Application Registration code ends here*/


        /*Setting additional information to accesstoken*/
        final Map<String, Object> additionalInfo = new HashMap<>();
        additionalInfo.put("wiseStatus", wiseStatus);
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);

        return accessToken;
    }

}
