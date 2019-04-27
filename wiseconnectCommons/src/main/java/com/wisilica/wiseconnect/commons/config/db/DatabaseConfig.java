package com.wisilica.wiseconnect.commons.config.db;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.wisilica.wiseconnect.commons.constants.DatasourceTypeEnum;

@Configuration
public class DatabaseConfig {	
	
    private static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
    
    private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	
	@Resource
    private Environment environment;

	@Bean(name = "masterDataSource")
	@ConfigurationProperties(prefix = "master.datasource")
	public DataSource getMasterDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "readerDataSource")
	@ConfigurationProperties(prefix = "reader.datasource")
	public DataSource getReaderDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name = "dataSource")
	@DependsOn({"masterDataSource", "readerDataSource"})
	public DataSource getDataSource() {
		RoutingDatasource routingDatasource = new RoutingDatasource();
		Map<Object, Object> targetDatasources = new HashMap<>();
		targetDatasources.put(DatasourceTypeEnum.MASTER, getMasterDataSource());
		targetDatasources.put(DatasourceTypeEnum.READER, getReaderDataSource());
		routingDatasource.setTargetDataSources(targetDatasources);
		routingDatasource.setDefaultTargetDataSource(getMasterDataSource());
		return routingDatasource;
	}
	
	@Bean(name = "transactionManager")
    public JpaTransactionManager getTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();

        transactionManager.setEntityManagerFactory(getEntityManagerFactory().getObject());

        return transactionManager;
    }
    
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactoryBean.setDataSource(getDataSource());
        entityManagerFactoryBean.setPackagesToScan("com.wisilica.wiseconnect.model.entity");        
        entityManagerFactoryBean.setPersistenceProvider(new HibernatePersistenceProvider());

        Properties jpaProterties = new Properties();
        jpaProterties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        jpaProterties.put(PROPERTY_NAME_HIBERNATE_FORMAT_SQL, environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_FORMAT_SQL));        
        jpaProterties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, environment.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));

        entityManagerFactoryBean.setJpaProperties(jpaProterties);

        return entityManagerFactoryBean;
    }
}
