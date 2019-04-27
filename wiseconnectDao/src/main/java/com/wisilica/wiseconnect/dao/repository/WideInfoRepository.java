package com.wisilica.wiseconnect.dao.repository;
/**
 *  WiseConnect WideInfo Repository
 *
 *  @Modified By Josny
 *  @Date  24-Mar-2019
 *
 * Description : Repository for fetching & updating data to wide_info table
 *
 * */

import com.wisilica.wiseconnect.model.entity.WideInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WideInfoRepository extends JpaRepository<WideInfo, Long> {

    @Query(value = "select wide_uuid from tbl_wide_info where wideId = ?1", nativeQuery = true)
    String findUUIDfromWideInfo(Long wideId);

    @Query(value = "SELECT  * FROM  tbl_wide_info WHERE (wide_uuid = ?1 OR wide_uuid = ?2)", nativeQuery = true)/*removed this condition OR _device_token = 'n-a'*/
    WideInfo findWideInfoByWideId(String deviceToken, String deviceUUID);

    @Modifying
    @Query("update WideInfo wi set wi.swVersion = :appVersion WHERE wi.wideUUID = :deviceToken and wi.swVersion != :appVersion and wi.wideId = :deviceId")
    int updateWideInfo(@Param("appVersion") String appVersion, @Param("deviceToken") String deviceToken, @Param("deviceId") String deviceId);
}
