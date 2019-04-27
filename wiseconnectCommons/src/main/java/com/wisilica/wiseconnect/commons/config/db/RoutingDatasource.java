package com.wisilica.wiseconnect.commons.config.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.wisilica.wiseconnect.commons.ExecutionContextHolder;

public class RoutingDatasource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {		
		return ExecutionContextHolder.getDatasourceType();
	}
}
