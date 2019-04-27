package com.wisilica.wiseconnect.dao.repository;
/**
 *  WiseConnect UserRoleRepository
 *
 *  @author Josny Jose
 *  @Date  29-Mar-2019
 *
 * Description : Repository for fetching & updating data to user_role table
 *
 * */
import com.wisilica.wiseconnect.model.entity.AppInfo;
import com.wisilica.wiseconnect.model.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository  extends JpaRepository<UserRole, Long> {

        @Query(value = "SELECT ur FROM  UserRole ur WHERE ur.userId = ?1 ")
        List<UserRole> findByUserId(int userId);
}
