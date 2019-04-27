package com.wisilica.wiseconnect.commons.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.wisilica.wiseconnect.commons.constants.DatasourceTypeEnum;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Inherited
public @interface ExecutionContext {
	
	DatasourceTypeEnum datasourceType() default DatasourceTypeEnum.MASTER;

}
