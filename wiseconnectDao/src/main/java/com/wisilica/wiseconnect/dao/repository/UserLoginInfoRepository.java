package com.wisilica.wiseconnect.dao.repository;
/**
 *  WiseConnect UserLoginInfo Repository
 *
 *  @author Josny Jose
 *  @Date  29-Mar-2019
 *
 * Description : Repository for fetching & updating data to user_login_info table
 *
 * */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wisilica.wiseconnect.model.entity.UserLoginInfo;

import java.util.List;

@Repository
public interface UserLoginInfoRepository extends JpaRepository<UserLoginInfo, Long>{
	
	UserLoginInfo findBySessionIdAndTokenAndOrganizationId(String sessionId, String token, Long organizationId);

	List<UserLoginInfo> findByOrganizationId(Long organizationId);

	UserLoginInfo findByAppIdAndUserId(Long appId, Long userId);

}
