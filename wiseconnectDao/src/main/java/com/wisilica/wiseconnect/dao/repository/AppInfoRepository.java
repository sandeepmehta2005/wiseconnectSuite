package com.wisilica.wiseconnect.dao.repository;
/**
 *  WiseConnect App Info Repository
 *
 *  @author Josny Jose
 *  @Date  29-Mar-2019
 *
 * Description : Repository for fetching & updating data to app_info table
 *
 * */
import com.wisilica.wiseconnect.model.entity.AppInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AppInfoRepository extends JpaRepository<AppInfo, Long> {

    //@Query(value = "SELECT * FROM  tbl_app_info AS tai WHERE  tai.device_id = ?1 AND tai.bundle_package = ?2", nativeQuery = true)
    AppInfo findAppInfoByDeviceIdAndBundlePackage(String deviceId, String bundlePackage);

    @Modifying
    @Query(value = "update AppInfo ai set ai.installationCount = ai.installationCount + 1, ai.appVersion = :appVersion, ai.lastUpdated = NOW(), ai.deviceToken = :deviceToken " +
            "WHERE ai.deviceId = :deviceId AND ai.bundlePackage = :bundlePackage")
    int updateAppInfo(@Param("appVersion") String appVersion, @Param("deviceToken") String deviceToken, @Param("deviceId") String deviceId, @Param("bundlePackage") String bundlePackage);


    @Modifying
    @Query(value = "update AppInfo ai set ai.installationCount = ai.installationCount + 1, ai.appVersion = :appVersion, ai.lastUpdated = NOW(), ai.deviceToken = :deviceToken, ai.osInfo = :osInfo" +
            " WHERE ai.deviceId = :deviceId AND ai.bundlePackage = :bundlePackage")
    int updateAppInfoWithOSInfo(@Param("appVersion") String appVersion, @Param("deviceToken") String deviceToken, @Param("deviceId") String deviceId, @Param("bundlePackage") String bundlePackage,
                      @Param("osInfo") String osInfo);


    AppInfo findAppInfoByAppId(Long appId);

    @Modifying
    @Query(value = "update AppInfo ai set ai.deviceToken = :deviceToken WHERE ai.appId = :appId")
    int updateAppInfoWithDeviceToken(@Param("deviceToken") String deviceToken, @Param("appId") Long appId);

    /*This code needs for login module Redesign*/
    /***@Query(value = "SELECT AppInfoDTO(ai.osType, ai.deviceId, ai.bundleClientId, IFNULL(bcm.featureId,0), bcm.bundlePackage, bcm.bundleId) FROM AppInfo ai" +

            " LEFT JOIN ai.BundleClientMapping bcm LEFT JOIN Bundle b" +
            " WHERE ai.bundleClientId = bcm.bundleClientId AND bcm.bundlePackage = ai.bundlePackage and b.bundleClientId = bcm.bundleClientId and appId = ?1 ")*/
    /*AppInfoDTO fetchAppInfoBunCliMapBundleDataLeftJoin(@Param("appId") Long appId);*/

    @Query(value = "SELECT ai FROM AppInfo ai Join ai.wideInfo wi JOIN wi.userSubOrgPermissions usop WHERE ai.deviceToken = wi.wideUUID and usop.organizationId = wi.orgId" +
            " and ai.appId = ?1 AND usop.userId = ?2")
    AppInfo fetchAppInfoWideInfoUserSubOrgPerm(@Param("appId") Long appId, @Param("userId") Long userId);


}
