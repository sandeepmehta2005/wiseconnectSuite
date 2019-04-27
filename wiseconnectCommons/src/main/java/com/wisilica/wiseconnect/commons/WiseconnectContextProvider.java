package com.wisilica.wiseconnect.commons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "singleton")
public final class WiseconnectContextProvider {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	private WiseconnectContextProvider() {}
	
	public <T> T getBean(Class<T> beanClass) {
        return applicationContext.getBean(beanClass);
    } 
   
    public Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }	
}
