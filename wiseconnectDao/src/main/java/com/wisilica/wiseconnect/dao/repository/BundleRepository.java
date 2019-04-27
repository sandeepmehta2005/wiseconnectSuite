package com.wisilica.wiseconnect.dao.repository;
/**
 *  WiseConnect Bundle Repository
 *
 *  @author Josny Jose
 *  @Date  29-Mar-2019
 *
 * Description : Repository for fetching & updating data to bundle table
 *
 * */
import com.wisilica.wiseconnect.model.entity.Bundle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BundleRepository extends JpaRepository<Bundle, Long> {

    Bundle findByBundleClientId(String bundleClientId);
}
