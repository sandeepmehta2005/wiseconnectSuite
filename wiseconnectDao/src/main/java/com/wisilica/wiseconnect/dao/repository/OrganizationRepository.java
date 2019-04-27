package com.wisilica.wiseconnect.dao.repository;

/**
 *  Wiseconnect AppInfo
 *
 *  @author Bhaskaran
 *  @Date  19-Mar-2019
 *
 * Description : Repository for fetching & updating data to organization table
 *
 * */

import com.wisilica.wiseconnect.model.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

}