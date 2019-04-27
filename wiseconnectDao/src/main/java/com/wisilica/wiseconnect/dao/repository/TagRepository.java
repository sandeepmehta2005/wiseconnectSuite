package com.wisilica.wiseconnect.dao.repository;
/**
 *  Wiseconnect AppInfo
 *
 *  @author Bhaskaran
 *  @Date  19-Mar-2019
 *
 * Description : Repository for fetching & updating data to Tag table
 *
 * */
import com.wisilica.wiseconnect.model.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
}
