package com.wisilica.wiseconnect.dao.repository;
/**
 *  WiseConnect Roles Repository
 *
 *  @author Josny Jose
 *  @Date  2-Apr-2019
 *
 * Description : Repository for fetching & updating data to roles table
 *
 * */
import com.wisilica.wiseconnect.model.entity.Roles;
import com.wisilica.wiseconnect.model.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolesRepository  extends JpaRepository<Roles, Long> {

    Roles findByRoleId(Long roleId);
}
