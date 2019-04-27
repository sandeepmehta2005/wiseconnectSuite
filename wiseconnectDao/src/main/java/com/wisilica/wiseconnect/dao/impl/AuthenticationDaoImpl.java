package com.wisilica.wiseconnect.dao.impl;
/**
 *  Authentication DAO Impl
 *
 *  @Modified By Josny
 *  @Date  29-Mar-2019
 *
 * Description : Implementation Layer for AuthenticationDAO
 *
 * */
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.wisilica.wiseconnect.dao.repository.*;
import com.wisilica.wiseconnect.dao.repository.impl.AppInfoInsertRepository;
import com.wisilica.wiseconnect.model.customdto.AppInfoDTO;
import com.wisilica.wiseconnect.model.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wisilica.wiseconnect.dao.AuthenticationDao;

import java.util.List;

@Repository
public class AuthenticationDaoImpl implements AuthenticationDao{
	
	@Autowired
	private UserLoginInfoRepository userLoginInfoRepository;

	@Autowired
	private BundleRepository bundleRepository;

	@Autowired
	private BundleClientMappingRepository bundleClientMappingRepository;

	@Autowired
	private AppInfoRepository appInfoRepository;

	@Autowired
	private AppInfoInsertRepository appInfoInsertRepository;

	@Autowired
	private PhoneRepository phoneRepository;

	@Autowired
	private BridgeListenerInfoRepository bridgeListenerInfoRepository;

	@Autowired
	private WideInfoRepository wideInfoRepository;

	@Autowired
	private UserRepository userRepository;
	
	@PersistenceContext
    private EntityManager entityManager;

	@Override
	public UserLoginInfo getUserLoginInfo(String sessionId, String token, Long organizationId) {
		return userLoginInfoRepository.findBySessionIdAndTokenAndOrganizationId(sessionId, token, organizationId);
	}

	@Override
	public List<UserLoginInfo> getUserLoginInfo(Long organizationId) {
		return userLoginInfoRepository.findByOrganizationId(organizationId);
	}

	@Override
	public UserLoginInfo getByAppIdAndUserId(Long appId, Long userId){
		return userLoginInfoRepository.findByAppIdAndUserId(appId,userId);
	}

	@Override
	public Bundle getBundleByBundleClientId(String bundleClientId){
		return bundleRepository.findByBundleClientId(bundleClientId);
	}

	@Override
	public BundleClientMapping getByBundlePackage(String bundlePackage){
		return bundleClientMappingRepository.findByBundlePackage(bundlePackage);
	}

	@Override
    public BundleClientMapping getBundleClientMappingByPackageOrClientId(String bundlePackage, String bundleClientId){
	    return bundleClientMappingRepository.findBundleClientMappingByPackageOrClientId(bundlePackage, bundleClientId);
    }

    @Override
    public AppInfo getAppInfoByDeviceIdBundlePackage(String deviceId, String bundlePackage){
        return appInfoRepository.findAppInfoByDeviceIdAndBundlePackage(deviceId, bundlePackage);
    }

    @Override
	public int updateAppInfo(String appVersion, String deviceToken, String deviceId, String bundlePackage){
		return appInfoRepository.updateAppInfo(appVersion, deviceToken, deviceId, bundlePackage);
	}
	@Override
	public int updateAppInfoWithOSInfo(String appVersion, String deviceToken, String deviceId, String bundlePackage, String osInfo){
		return appInfoRepository.updateAppInfoWithOSInfo(appVersion, deviceToken, deviceId, bundlePackage, osInfo);
	}

	@Override
	public AppInfo getAppInfoByAppId(Long appId){
		return appInfoRepository.findAppInfoByAppId(appId);
	}

	@Override
	public int updateAppInfoWithDeviceToken(String deviceToken, Long appId){
		return appInfoRepository.updateAppInfoWithDeviceToken(deviceToken, appId);
	}

	@Override
	public AppInfo getAppInfoWideInfoUserSubOrgPerm(Long appId,Long userId){
		return appInfoRepository.fetchAppInfoWideInfoUserSubOrgPerm(appId, userId);
	}

	@Override
	public Long insertAppInfo(AppInfo appInfo){
		return appInfoInsertRepository.insertAppInfo(appInfo);
	}

	public AppInfoDTO getAppInfoBunCliMapBundleDataLeftJoin(Long appId){
		/*This code needs for login module Redesign*/
		/*return appInfoRepository.fetchAppInfoBunCliMapBundleDataLeftJoin(appId);*/
		return null;
	}

	@Override
    public Phone getPhoneByAppIdUserId(Long appId, Long userId){
		return phoneRepository.findPhoneByAppIdAndUserId(appId, userId);
	}

	@Override
	public BridgeListenerInfo getBridgeListenerInfoByAppIdUserIdPhoneId(Long appId, Long userId, Long userPhoneId){
		return bridgeListenerInfoRepository.findBridgeListenerInfoByAppIdAndUserIdAndPhoneLongId(appId, userId, userPhoneId);
	}

	@Override
	public String findUUIDfromWideInfo(Long wideId){
		return wideInfoRepository.findUUIDfromWideInfo(wideId);
	}

	@Override
	public WideInfo findWideInfoByWideId(String deviceToken, String deviceUUID){
		return wideInfoRepository.findWideInfoByWideId(deviceToken, deviceUUID);
	}

	@Override
	public int updateWideInfo(String appVersion, String deviceToken, String deviceId){
		return wideInfoRepository.updateWideInfo(appVersion, deviceToken, deviceId);
	}

	@Override
	public User getByUserNameOrUserEmailOrUserMobile(String userName){
		return userRepository.findByUserNameOrUserEmailOrUserMobile(userName);
	}

	@Override
	public User getByUserNameOrUserEmailOrUserMobileAndUserPassword(String userName, String userPassword){
		return userRepository.findByUserNameOrUserEmailOrUserMobileAndUserPassword(userName, userPassword);
	}

	@Override
	public User getUserAppInfoDataJoin(String userName, String password){
		return userRepository.fetchUserAppInfoDataJoin(userName, password);
	}
	@Override
	public User getByUserNameAndUserPasswordAndUserType(String userName, String userPassword, int userType){
		return userRepository.findByUserNameAndUserPasswordAndUserType(userName, userPassword, userType);
	}

	@Override
	public User getUserAppInfoBunCliMapJoin(String userName, String userPassword, int bundleId){
		return userRepository.fetchUserAppInfoBunCliMapJoin(userName, userPassword, bundleId);
	}

}
