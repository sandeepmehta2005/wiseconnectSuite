package com.wisilica.wiseconnect.dao;
/**
 *  Authentication DAO
 *
 *  @Modified By Josny
 *  @Date  29-Mar-2019
 *
 * Description : DAO Layer for Authentication
 *
 * */
import com.wisilica.wiseconnect.model.customdto.AppInfoDTO;
import com.wisilica.wiseconnect.model.entity.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthenticationDao {

    /** UserLoginInfo methods*/
	UserLoginInfo getUserLoginInfo(String sessionId, String token, Long organizationId);

	List<UserLoginInfo> getUserLoginInfo(Long organizationId);

    UserLoginInfo getByAppIdAndUserId(Long appId, Long userId);


	/** Bundle Methods*/
	Bundle getBundleByBundleClientId(String bundleClientId);


    /** BundleClientMapping Methods*/
	BundleClientMapping getByBundlePackage(String bundlePackage);

    BundleClientMapping getBundleClientMappingByPackageOrClientId(String bundlePackage, String bundleClientId);


    /** AppInfo Methods*/
    AppInfo getAppInfoByDeviceIdBundlePackage(String deviceId, String bundlePackage);

    int updateAppInfo(String appVersion, String deviceToken, String deviceId, String bundlePackage);

    int updateAppInfoWithOSInfo(String appVersion, String deviceToken, String deviceId, String bundlePackage, String osInfo);

    Long insertAppInfo(AppInfo appInfo);

    AppInfo getAppInfoByAppId(Long appId);

    int updateAppInfoWithDeviceToken(String deviceToken, Long appId);

    AppInfoDTO getAppInfoBunCliMapBundleDataLeftJoin(Long appId);

    AppInfo getAppInfoWideInfoUserSubOrgPerm(Long appId,Long userId);


    /** Phone Methods*/
    Phone getPhoneByAppIdUserId(Long appId, Long userId);

    /** BridgeListenerInfo Methods*/
    BridgeListenerInfo getBridgeListenerInfoByAppIdUserIdPhoneId(Long appId, Long userId, Long userPhoneId);


    /** WideInfo Methods*/
    String findUUIDfromWideInfo(Long wideId);

    WideInfo findWideInfoByWideId(String deviceToken, String deviceUUID);

    int updateWideInfo(String appVersion, String deviceToken, String deviceId);

    /** User Methods*/
    User getByUserNameOrUserEmailOrUserMobile(String userName);

    User getByUserNameOrUserEmailOrUserMobileAndUserPassword(String userName, String userPassword);

    User getUserAppInfoDataJoin(String userName, String password);

    User getByUserNameAndUserPasswordAndUserType(String userName, String userPassword, int userType);

    User getUserAppInfoBunCliMapJoin(String userName, String userPassword, int bundleId);

}
