package com.wisilica.wiseconnect.interceptor;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.wisilica.wiseconnect.commons.util.CommonsUtil;
import com.wisilica.wiseconnect.util.WebUtil;

@Component
public class CommonInterceptor extends HandlerInterceptorAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommonInterceptor.class);
	
	@Value("${wiseconnect.instance.uniqueId}")
	private String instanceUniqueId;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {		
		setMDC();
		logRequest(request);
		request.setAttribute(WebUtil.API_START_TIME, System.currentTimeMillis());
		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			@Nullable Exception ex) throws Exception {
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Response sent: {}", response);
		}
		resetMDC();
	}
	
	private void logRequest(HttpServletRequest request) {
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("Request received: {} ", WebUtil.getRequestDetailsForLog(request, System.lineSeparator()));
		}
	}
	
	private void setMDC() {
		String uniqueRequestId = instanceUniqueId + ":" + UUID.randomUUID().toString();
		MDC.put(CommonsUtil.UNIQUE_INSTANCE_ID, uniqueRequestId);
	}

	private void resetMDC() {
		MDC.clear();
	}
}
