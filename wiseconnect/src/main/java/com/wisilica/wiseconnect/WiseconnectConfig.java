package com.wisilica.wiseconnect;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.wisilica.wiseconnect.interceptor.CommonInterceptor;
import com.wisilica.wiseconnect.interceptor.SecurityInterceptor;

@Configuration
public class WiseconnectConfig implements WebMvcConfigurer {

	@Autowired
	private SecurityInterceptor securityInterceptor;
	
	@Autowired
	private CommonInterceptor commonInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(commonInterceptor).addPathPatterns("/public/**").excludePathPatterns("/actuator/**");
		registry.addInterceptor(securityInterceptor).addPathPatterns("/public/**").excludePathPatterns("/actuator/**");
	}

	@Bean("messageSource")
	public MessageSource getMessageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:templates/messages/messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean()
	@Override
	public LocalValidatorFactoryBean getValidator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(getMessageSource());
		return bean;
	}
	
	@Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.featuresToEnable(SerializationFeature.WRAP_ROOT_VALUE); // enables wrapping for root elements
       return builder;
    }
	
	@Bean
	public ServletWebServerFactory servletContainer(@Value("${server.http.port}") int httpPort) {
		Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
		connector.setPort(httpPort);

		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
		tomcat.addAdditionalTomcatConnectors(connector);
		return tomcat;
	}
}
