package com.wisilica.wiseconnect.dao.repository;
/**
 *  WiseConnect Phone Repository
 *
 * @Author Bhaskaran
 *  @ModifiedBy Josny Jose
 *  @Date  29-Mar-2019
 *
 * Description : Repository for fetching & updating data to Phone table
 *
 * */
import com.wisilica.wiseconnect.model.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

    Phone findPhoneByAppIdAndUserId(Long appId, Long userId);
}
