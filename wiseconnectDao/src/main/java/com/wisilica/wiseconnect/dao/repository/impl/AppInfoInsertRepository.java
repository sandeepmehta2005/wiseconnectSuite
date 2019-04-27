package com.wisilica.wiseconnect.dao.repository.impl;
/**
 *  WiseConnect App Info Insert Repository
 *
 *  @author Josny Jose
 *  @Date  22-Mar-2019
 *
 * Description : Repository for inserting data to table
 *
 * */

import com.wisilica.wiseconnect.model.entity.AppInfo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
public  class AppInfoInsertRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Long insertAppInfo(AppInfo appInfo) {
        entityManager.persist(appInfo);
        entityManager.flush();
        return appInfo.getAppId();
    }

}