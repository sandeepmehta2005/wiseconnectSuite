package com.wisilica.wiseconnect.core.service.impl;
/**
 *  WiseConnect Authentication Service Implementation
 *
 *  @Modified By Josny Jose
 *  @Date  14-Mar-2019
 *
 * Description : Service Methods Implementation of Authentication Service
 *
 * */
import com.wisilica.wiseconnect.core.util.StaticVariables;
import com.wisilica.wiseconnect.core.util.StatusCodes;
import com.wisilica.wiseconnect.core.util.StatusMessages;
import com.wisilica.wiseconnect.model.customdto.AppInfoDTO;
import com.wisilica.wiseconnect.model.entity.*;
import com.wisilica.wiseconnect.model.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wisilica.wiseconnect.core.service.AuthenticationService;
import com.wisilica.wiseconnect.dao.AuthenticationDao;
import com.wisilica.wiseconnect.model.util.UserLoginContext;

import java.util.Date;
import java.util.List;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{
	
	@Autowired
	private AuthenticationDao authenticationDao;

	@Override
	@Transactional(readOnly = true)
	public UserLoginContext getUserLoginContext(String token, Long organizationId) {
		String sessionId = token.split("#")[0];
		UserLoginInfo userLoginInfo = authenticationDao.getUserLoginInfo(sessionId, token, organizationId);
		if(userLoginInfo == null || userLoginInfo.isSessionExpired()) {
			return null;
		}
		if(userLoginInfo.getUser().getStatusId() != 1) {
			return null;
		}
		UserLoginContext userLoginContext = new UserLoginContext();
		userLoginContext.setUserId(userLoginInfo.getUserId());

		userLoginContext.setOrganizationId(userLoginInfo.getOrganizationId());
		userLoginContext.setPhoneId(userLoginInfo.getUserPhoneId());
		userLoginContext.setUserTypeId(userLoginInfo.getUser().getType());
		userLoginContext.setCustomerId(userLoginInfo.getUser().getCustomerId());
		return userLoginContext;
	}

	@Override
	@Transactional(readOnly = true)
	public UserLoginInfo getUserLoginContext(Long organizationId) {
		List<UserLoginInfo> userLoginInfoList = authenticationDao.getUserLoginInfo(organizationId);
		UserLoginInfo userLoginInfo = userLoginInfoList.get(0);
		if(userLoginInfo == null || userLoginInfo.isSessionExpired()) {
			return null;
		}
		if(userLoginInfo.getUser().getStatusId() != 1) {
			return null;
		}

		return userLoginInfo;
	}

	@Override
	@Transactional
	public AuthResponse appRegistration(String bundleClientId, String bundlePackage, String deviceId, String sessionId, Long organizationId, String token, String appVersion, String modelInfo, String osInfo){

	    AuthResponse authResponse = new AuthResponse();
		Bundle bundle = authenticationDao.getBundleByBundleClientId(bundleClientId);
        Long userId = 0l;
        Long device_id = 0l;
        String deviceUUID = null;
        Long userPhoneId = 0l;
        Long appId = 0l;

        if(bundle != null){
			BundleClientMapping bundleClientMapping = authenticationDao.getByBundlePackage(bundlePackage);
			if (bundleClientMapping != null){
                BundleClientMapping bundleClientMapping1 = authenticationDao.getBundleClientMappingByPackageOrClientId(bundlePackage, bundleClientId);
			    if(bundleClientMapping1 != null){
                    AppInfo appInfo = authenticationDao.getAppInfoByDeviceIdBundlePackage(deviceId, bundlePackage);
                    if(appInfo != null && appInfo.getOsType() == 3l){ /*checking for bridge device or not openwrt*/
                        appId = appInfo.getAppId();
                        if(appId != 0l) {
                            UserLoginInfo userLoginInfo = authenticationDao.getUserLoginInfo(sessionId, token, organizationId);
                            userId = userLoginInfo.getUserId();
                        }
                        if (userId != 0l) {
                            Phone phone = authenticationDao.getPhoneByAppIdUserId(appId, userId);
                            userPhoneId = phone.getUserPhoneId();
                        }
                        if(userPhoneId != 0l) {
                            BridgeListenerInfo bridgeListenerInfo = authenticationDao.getBridgeListenerInfoByAppIdUserIdPhoneId(appId, userId, userPhoneId);
                            device_id = bridgeListenerInfo.getDeviceId();
                        }
                        if(device_id != 0l){
                            deviceUUID = authenticationDao.findUUIDfromWideInfo(device_id);
                        }
                        WideInfo wideInfo = authenticationDao.findWideInfoByWideId(token, deviceUUID);
                        if(wideInfo != null) {                            
                            if (deviceId != "") {
                                authenticationDao.updateWideInfo(appVersion, token, deviceId);
                            }
                            int updateCount = authenticationDao.updateAppInfoWithOSInfo(appVersion, token, deviceId, bundlePackage, osInfo);
                            if (updateCount > 0) {
                                authResponse.setMessage("Success, App Details Updated");
                                authResponse.setStatusCode(2);
                                authResponse.setAppId(appId.intValue());
                            } else {
                                authResponse.setMessage("Error in update");
                                authResponse.setStatusCode(StaticVariables.IS_ZERO);
                                authResponse.setAppId(appId.intValue());
                            }
                        } else {
                            authResponse.setMessage("No device found please repair the device");
                            authResponse.setStatusCode(StatusCodes.NO_DEVICE_FOUND_IN_WIDE_INFO_REPAIR_THE_DEVICE);
                            authResponse.setAppId(StaticVariables.IS_ZERO);
                        }                        
                    }else{
                        if (appId != StaticVariables.IS_ZERO.longValue()) {
                            int updateCount = authenticationDao.updateAppInfo(appVersion, token, deviceId, bundlePackage);
                            if (updateCount > 0) {
                                authResponse.setMessage("Success, App Details Updated");
                                authResponse.setStatusCode(2);
                                authResponse.setAppId(appId.intValue());
                            } else {
                                authResponse.setMessage("Error in update");
                                authResponse.setStatusCode(StaticVariables.IS_ZERO);
                                authResponse.setAppId(StaticVariables.IS_ZERO);
                            }
                        }else{
                            Long osType = bundleClientMapping1.getType();
                            AppInfo appInfoInsert = new AppInfo();
                            appInfoInsert.setDeviceId(deviceId);
                            appInfoInsert.setOsType(osType);
                            appInfoInsert.setOsInfo(osInfo);
                            appInfoInsert.setAppVersion(appVersion);
                            appInfoInsert.setInstallationCount(1l);
                            appInfoInsert.setOrganizationId(organizationId);
                            appInfoInsert.setModelInfo(modelInfo);
                            appInfoInsert.setDeviceToken(token);
                            appInfoInsert.setBundlePackage(bundlePackage);
                            appInfoInsert.setBundleClientId(bundleClientId);
                            appInfoInsert.setCreatedTime(new Date());
                            appInfoInsert.setLastUpdated(new Date());
                            appInfoInsert.setProjectId(1l);// Default value of project_id is 1.
                            Long insertId = authenticationDao.insertAppInfo(appInfoInsert);
                            if(insertId != null && insertId > 0){
                                authResponse.setMessage("App registered");
                                authResponse.setAppId(insertId.intValue());
                                authResponse.setStatusCode(StaticVariables.IS_ONE);
                            }else{
                                authResponse.setMessage("App registration failed");
                                authResponse.setAppId(StaticVariables.IS_ZERO);
                                authResponse.setStatusCode(StaticVariables.IS_ZERO);
                            }
                        }
                    }
                }else{
                    authResponse.setMessage("Invalid bundle package and bundle client id combination");
                    authResponse.setAppId(StaticVariables.IS_ZERO);
                    authResponse.setStatusCode(StatusCodes.INVALID_BUNDLE_DETAILS);
                }
			}else{
				authResponse.setMessage("Invalid bundle package name");
				authResponse.setAppId(StaticVariables.IS_ZERO);
				authResponse.setStatusCode(StatusCodes.INVALID_BUNDLE_DETAILS);
			}
		}else{
			authResponse.setMessage("Invalid bundle details");
			authResponse.setAppId(StaticVariables.IS_ZERO);
			authResponse.setStatusCode(StatusCodes.INVALID_BUNDLE_DETAILS);
		}

		return authResponse;
	}

    @Override
    @Transactional(readOnly = true)
    public AuthResponse getAppDetails(Long appId, Long organizationId){
	    AuthResponse authResponse = new AuthResponse();
        AppInfo appInfo = authenticationDao.getAppInfoByAppId(appId);
        if(appInfo != null){
            authResponse.setMessage("valid data");
            authResponse.setStatusCode(StaticVariables.IS_ONE);
        }else{
            authResponse.setMessage("Invalid app id-organization combination");
            authResponse.setStatusCode(StaticVariables.IS_ZERO);
        }
	    return authResponse;
    }

    @Override
    @Transactional
    public AuthResponse deviceTokenUpdate(String deviceToken, Long organizationId, Long appId, Long userId){
        AuthResponse authResponse = new AuthResponse();
        UserLoginInfo userLoginInfo = authenticationDao.getByAppIdAndUserId(appId, userId);
        if(userLoginInfo != null){
            int updateCount = authenticationDao.updateAppInfoWithDeviceToken(deviceToken, appId);
            if(updateCount > StaticVariables.IS_ZERO){
                authResponse.setMessage("App info updated successfully");
                authResponse.setStatusCode(updateCount);
            }else{
                authResponse.setMessage("Nothing to update app info");
                authResponse.setStatusCode(StaticVariables.IS_ZERO);
            }
        }else{
            authResponse.setMessage("Invalid app id-organization combination");
            authResponse.setStatusCode(StaticVariables.IS_ZERO);
        }
        return authResponse;
    }


    @Override
    @Transactional
    public AuthResponse loginAuthorize(String userName, String password, Long appId){
	    int userType = 0;
	    int osType = 0;
	    Long rootOrgId = 0l;
	    Long featureId = 0l;
	    int bundleId = 0;
	    int web = 4; // osType
	    String bundlePackage, bundleClientId;
	    int openWrt = 3; /*For bridge user*/
        AuthResponse authResponse = new AuthResponse();
        AppInfo appInfo = authenticationDao.getAppInfoByAppId(appId);
        if(appInfo != null){
            User user = authenticationDao.getByUserNameOrUserEmailOrUserMobile(userName);
            if (user != null){
                user = authenticationDao.getByUserNameOrUserEmailOrUserMobileAndUserPassword(userName, password);
                userType = user.getType();
                if(user != null){
                    AppInfoDTO appInfoDTO = authenticationDao.getAppInfoBunCliMapBundleDataLeftJoin(appId);
                    osType = appInfoDTO.getOsType();
                    featureId = appInfoDTO.getFeatureId();
                    bundlePackage = appInfoDTO.getBundlePackage();
                    bundleClientId = appInfoDTO.getBundleClientId();
                    bundleId = appInfoDTO.getBundleId().intValue();
                    if(osType != openWrt){
                        User user1 = authenticationDao.getUserAppInfoBunCliMapJoin(userName, password, bundleId);
                        if(user1 != null){
                            if((userType == -1 || userType == 1 || userType == 2) && osType == web && osType != openWrt){

                                generateAuthResponse(rootOrgId, featureId, bundlePackage, bundleClientId, authResponse);

                            }else if((userType == 1 || userType == 2 || userType == 3 || userType == 4 || userType == 9) && osType != web && osType != openWrt) {

                                generateAuthResponse(rootOrgId, featureId, bundlePackage, bundleClientId, authResponse);

                            }else if(userType == 5 && osType  != web && osType == openWrt){

                                authResponse.setStatus(rootOrgId.intValue());
                                authResponse.setMessage(StatusMessages.USER_HAS_PERMISSION);
                                authResponse.setOrganizationId(rootOrgId.intValue());
                                authResponse.setFeatureId(featureId.intValue());
                                authResponse.setBundlePackage(bundlePackage);
                                authResponse.setBundleClientId(bundleClientId);

                            }else if(userType == 5 && osType  == web){

                                authResponse.setStatus(StaticVariables.IS_ZERO);
                                authResponse.setMessage("Cannot login as bridge user");
                                authResponse.setOrganizationId(StaticVariables.IS_ZERO);
                                authResponse.setFeatureId(StaticVariables.IS_ZERO);
                                authResponse.setBundlePackage(StaticVariables.IS_ZERO + "");
                                authResponse.setBundleClientId(StaticVariables.IS_ZERO + "");

                            }else if(userType == 4 && osType  == web){

                                authResponse.setStatus(StaticVariables.IS_ZERO);
                                authResponse.setMessage("Cannot login as tech user");
                                authResponse.setOrganizationId(StaticVariables.IS_ZERO);
                                authResponse.setFeatureId(StaticVariables.IS_ZERO);
                                authResponse.setBundlePackage(StaticVariables.IS_ZERO + "");
                                authResponse.setBundleClientId(StaticVariables.IS_ZERO + "");

                            }else if(userType == 3 && osType  == web){

                                authResponse.setStatus(StaticVariables.IS_ZERO);
                                authResponse.setMessage("Cannot login as standard user");
                                authResponse.setOrganizationId(StaticVariables.IS_ZERO);
                                authResponse.setFeatureId(StaticVariables.IS_ZERO);
                                authResponse.setBundlePackage(StaticVariables.IS_ZERO + "");
                                authResponse.setBundleClientId(StaticVariables.IS_ZERO + "");
                            }else if(userType == 9 && osType  == web) {

                                authResponse.setStatus(StaticVariables.IS_ZERO);
                                authResponse.setMessage("Cannot login as security user");
                                authResponse.setOrganizationId(StaticVariables.IS_ZERO);
                                authResponse.setFeatureId(StaticVariables.IS_ZERO);
                                authResponse.setBundlePackage(StaticVariables.IS_ZERO + "");
                                authResponse.setBundleClientId(StaticVariables.IS_ZERO + "");
                            }else {
                                authResponse.setStatus(StaticVariables.IS_ZERO);
                                authResponse.setMessage("permission denied");
                                authResponse.setOrganizationId(StatusCodes.PERMISIION_DENIED);
                                authResponse.setFeatureId(StaticVariables.IS_ZERO);
                                authResponse.setBundlePackage(StaticVariables.IS_ZERO + "");
                                authResponse.setBundleClientId(StaticVariables.IS_ZERO + "");
                            }
                        }else{
                            authResponse.setStatus(StaticVariables.IS_ZERO);
                            authResponse.setMessage("The app cannot support the login using this user");
                            authResponse.setOrganizationId(StaticVariables.IS_ZERO);
                            authResponse.setStatusCode(StatusCodes.PERMISIION_DENIED);
                            authResponse.setFeatureId(StaticVariables.IS_ZERO);
                            authResponse.setBundlePackage(StaticVariables.IS_ZERO + "");
                            authResponse.setBundleClientId(StaticVariables.IS_ZERO + "");
                        }
                    }else{
                        user = authenticationDao.getUserAppInfoDataJoin(userName, password);
                        if(user != null){
                            user = authenticationDao.getByUserNameAndUserPasswordAndUserType(userName, password, userType);
                            Long userId = user.getId();
                            userType = user.getType();
                            rootOrgId = user.getOrganizationId();

                            if (userType != 0 && user.getOrganizationId() != null && userId != null){
                                AppInfo appInfo1 = authenticationDao.getAppInfoWideInfoUserSubOrgPerm(appId, userId);
                                if(appInfo1 != null){
                                    if(userType == 5 && osType == openWrt){
                                        authResponse.setStatus(rootOrgId.intValue());
                                        authResponse.setMessage(StatusMessages.USER_HAS_PERMISSION);
                                        authResponse.setOrganizationId(rootOrgId.intValue());
                                        authResponse.setFeatureId(featureId.intValue());
                                        authResponse.setBundlePackage(bundlePackage);
                                        authResponse.setBundleClientId(bundleClientId);
                                    }

                                }else{
                                    authResponse.setStatus(StaticVariables.IS_ZERO);
                                    authResponse.setMessage("Invalid bridge user,login not allowed");
                                    authResponse.setStatusCode(StatusCodes.PERMISIION_DENIED);
                                    authResponse.setFeatureId(StaticVariables.IS_ZERO);
                                    authResponse.setBundlePackage(StaticVariables.IS_ZERO + "");
                                    authResponse.setBundleClientId(StaticVariables.IS_ZERO + "");
                                }

                            }else{
                                authResponse.setStatus(StaticVariables.IS_ZERO);
                                authResponse.setMessage("user not found ");
                                authResponse.setStatusCode(StatusCodes.INVALID_USERNAME_OR_PASSWORD);
                                authResponse.setOrganizationId(StaticVariables.IS_ZERO);
                            }
                        }else{
                            authResponse.setStatus(StaticVariables.IS_ZERO);
                            authResponse.setMessage("The app cannot support the login using this user");
                            authResponse.setStatusCode(StatusCodes.PERMISIION_DENIED);
                            authResponse.setOrganizationId(StaticVariables.IS_ZERO);
                            authResponse.setFeatureId(StaticVariables.IS_ZERO);
                            authResponse.setBundlePackage(StaticVariables.IS_ZERO + "");
                            authResponse.setBundleClientId(StaticVariables.IS_ZERO + "");
                        }
                    }
                }else{
                    authResponse.setStatus(StaticVariables.IS_ZERO);
                    authResponse.setMessage("Invalid user password");
                    authResponse.setStatusCode(StatusCodes.INVALID_USERNAME_OR_PASSWORD);
                    authResponse.setOrganizationId(StaticVariables.IS_ZERO);
                    authResponse.setFeatureId(StaticVariables.IS_ZERO);
                    authResponse.setBundlePackage(StaticVariables.IS_ZERO + "");
                    authResponse.setBundleClientId(StaticVariables.IS_ZERO + "");
                }
            }else{
                authResponse.setStatus(StaticVariables.IS_ZERO);
                authResponse.setMessage("Invalid username");
                authResponse.setStatusCode(StatusCodes.INVALID_USERNAME_OR_PASSWORD);
                authResponse.setOrganizationId(StaticVariables.IS_ZERO);
                authResponse.setFeatureId(StaticVariables.IS_ZERO);
                authResponse.setBundlePackage(StaticVariables.IS_ZERO + "");
                authResponse.setBundleClientId(StaticVariables.IS_ZERO + "");
            }
        }else{
            authResponse.setStatus(StaticVariables.IS_ZERO);
            authResponse.setMessage("App Id not found");
            authResponse.setStatusCode(StatusCodes.INVALID_APP_ID);
            authResponse.setOrganizationId(StaticVariables.IS_ZERO);
        }
        return authResponse;
    }

    private void generateAuthResponse(Long rootOrgId, Long featureId, String bundlePackage, String bundleClientId, AuthResponse authResponse) {
        authResponse.setStatus(StaticVariables.IS_ONE);
        authResponse.setMessage(StatusMessages.USER_HAS_PERMISSION);
        authResponse.setOrganizationId(rootOrgId.intValue());
        authResponse.setFeatureId(featureId.intValue());
        authResponse.setBundlePackage(bundlePackage);
        authResponse.setBundleClientId(bundleClientId);
    }
}
