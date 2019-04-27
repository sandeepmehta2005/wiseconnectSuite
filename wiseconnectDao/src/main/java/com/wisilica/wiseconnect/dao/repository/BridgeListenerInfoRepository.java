package com.wisilica.wiseconnect.dao.repository;
/**
 *  WiseConnect Bridge ListenerInfo Repository
 *
 *  @author Josny Jose
 *  @Date  29-Mar-2019
 *
 * Description : Repository for fetching & updating data to bridge_listener_info table
 *
 * */
import com.wisilica.wiseconnect.model.entity.BridgeListenerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BridgeListenerInfoRepository extends JpaRepository<BridgeListenerInfo, Long> {

        BridgeListenerInfo findBridgeListenerInfoByAppIdAndUserIdAndPhoneLongId(Long appId, Long userId, Long userPhoneId);
}
