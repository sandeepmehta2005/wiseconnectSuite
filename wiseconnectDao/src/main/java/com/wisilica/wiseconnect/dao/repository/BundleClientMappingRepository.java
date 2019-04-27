package com.wisilica.wiseconnect.dao.repository;
/**
 *  WiseConnect BundleClientMapping Repository
 *
 *  @author Josny Jose
 *  @Date  29-Mar-2019
 *
 * Description : Repository for fetching & updating data to Bundle_client_mapping table
 *
 * */
import com.wisilica.wiseconnect.model.entity.BundleClientMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BundleClientMappingRepository extends JpaRepository<BundleClientMapping, Long> {

    BundleClientMapping findByBundlePackage(String bundlePackage);

    @Query("SELECT bcm FROM BundleClientMapping bcm WHERE (bcm.bundlePackage = ?1 and bcm.bundleClientId = ?2) or bcm.bundlePackage=?1")
    BundleClientMapping findBundleClientMappingByPackageOrClientId(String bundlePackage, String bundleClientId);

}
