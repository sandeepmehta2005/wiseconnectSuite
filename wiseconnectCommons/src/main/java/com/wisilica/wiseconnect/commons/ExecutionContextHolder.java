package com.wisilica.wiseconnect.commons;

import com.wisilica.wiseconnect.commons.constants.DatasourceTypeEnum;

public final class ExecutionContextHolder {

	private static final ThreadLocal<DatasourceTypeEnum> datasource = new ThreadLocal<>();
	
	private ExecutionContextHolder() {}

	public static void setDatasourceType(DatasourceTypeEnum datasourceType) {
		datasource.set(datasourceType);
	}

	public static DatasourceTypeEnum getDatasourceType() {
		return datasource.get();
	}

	public static void clearDatasourceType() {
		datasource.remove();
	}

	public static void clear() {
		datasource.remove();
	}
}
