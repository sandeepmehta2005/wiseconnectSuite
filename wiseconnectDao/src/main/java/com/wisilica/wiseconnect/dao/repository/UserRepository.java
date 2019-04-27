package com.wisilica.wiseconnect.dao.repository;
/**
 *  WiseConnect User Repository
 *
 *  @Modified By Josny
 *  @Date  24-Mar-2019
 *
 * Description : Repository for fetching & updating data to user table
 *
 * */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wisilica.wiseconnect.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByUserName(String userName);

	@Query(value = "SELECT u FROM  User AS u WHERE u.userName = ?1 OR u.email = ?1 OR u.mobile = ?1")
	User findByUserNameOrUserEmailOrUserMobile(@Param("userName") String userName);

	@Query(value = "SELECT u FROM  User AS u WHERE (u.userName = ?1 OR u.email = ?1 OR u.mobile = ?1) and u.password = ?2")
	User findByUserNameOrUserEmailOrUserMobileAndUserPassword(@Param("userName") String userName, @Param("userPassword") String userPassword);

	@Query(value = "SELECT u FROM User u JOIN u.appInfo ai where ai.appId = u.appId and (u.userName = ?1 OR u.email = ?1 OR u.mobile = ?1) AND u.password = ?2")
	User fetchUserAppInfoDataJoin(@Param("userName") String userName, @Param("password") String password);

	@Query(value = "SELECT u FROM  User u WHERE (u.userName = ?1 OR u.email = ?1 OR u.mobile = ?1) and u.password = ?2 AND userType = ?3")
	User findByUserNameAndUserPasswordAndUserType(@Param("userName") String userName, @Param("userPassword") String userPassword, @Param("userType") int userType);

	@Query(value = "SELECT u FROM User u JOIN u.appInfo ai JOIN ai.bundleClientMapping bcm WHERE ai.appId = u.appId and ai.bundleClientId =  bcm.bundleClientId" +
			" AND bcm.bundlePackage = ai.bundlePackage and (u.userName = ?1 OR u.email = ?1 OR u.mobile = ?1) AND u.password = ?2 AND bcm.bundleId = ?3")
	User fetchUserAppInfoBunCliMapJoin(@Param("userName") String userName, @Param("userPassword") String userPassword, @Param("bundleId") int bundleId);



}
