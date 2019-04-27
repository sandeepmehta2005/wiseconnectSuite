package com.wisilica.wiseconnect.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.wisilica.wiseconnect.commons.constants.ErrorCodeEnum;
import com.wisilica.wiseconnect.commons.constants.ResponseCodeEnum;
import com.wisilica.wiseconnect.commons.exception.ControllerException;
import com.wisilica.wiseconnect.commons.exception.ServiceException;
import com.wisilica.wiseconnect.commons.type.ResponseData;
import com.wisilica.wiseconnect.model.util.UserLoginContext;

public final class WebUtil {

	public static final String API_START_TIME = "API_START_TIME";

	private static final String USER_LOGIN_CONTEXT_ATTRIBUTE = "USER_LOGIN_CONTEXT_ATTRIBUTE";

	private WebUtil() {

	}

	public static ResponseData getResponseFromException(Exception ex, Map<String, Map<String, String>> detailMessage) {

		ResponseData responseData = new ResponseData();
		responseData.setResponseCode(ResponseCodeEnum.INTERNAL_SERVER_ERROR);

		if (ex instanceof ServiceException) {
			populateServiceExceptionDetails((ServiceException) ex, responseData, detailMessage);
			return responseData;
		}

		if (ex instanceof ControllerException) {
			populateControllerExceptionDetails((ControllerException) ex, responseData, detailMessage);
			return responseData;
		}		
		
		populateMiscExceptionDetails(ex, responseData);		
		return responseData;
	}	

	public static HttpServletRequest getHttpServletRequest() {
		RequestAttributes reqAttr = RequestContextHolder.getRequestAttributes();
		if (!(reqAttr instanceof ServletRequestAttributes)) {
			return null;
		}
		ServletRequestAttributes servReqAttr = (ServletRequestAttributes) reqAttr;
		return servReqAttr.getRequest();
	}

	public static String getHeader(String headerName) {
		RequestAttributes reqAttr = RequestContextHolder.getRequestAttributes();
		if (!(reqAttr instanceof ServletRequestAttributes)) {
			return null;
		}
		ServletRequestAttributes servReqAttr = (ServletRequestAttributes) reqAttr;
		return servReqAttr.getRequest().getHeader(headerName);
	}

	public static void setUserLoginContext(UserLoginContext userLoginContext) {
		RequestContextHolder.getRequestAttributes().setAttribute(USER_LOGIN_CONTEXT_ATTRIBUTE, userLoginContext,
				RequestAttributes.SCOPE_REQUEST);
	}

	public static UserLoginContext getUserLoginContext() {
		return (UserLoginContext) RequestContextHolder.getRequestAttributes().getAttribute(USER_LOGIN_CONTEXT_ATTRIBUTE,
				RequestAttributes.SCOPE_REQUEST);
	}

	public static String getRequestDetailsForLog(HttpServletRequest request, String delimiter) {

		StringBuilder sb = new StringBuilder();
		sb.append("Request = [" + request.getMethod() + " " + request.getRequestURL() + "]" + delimiter);
		sb.append("Remote address = [" + request.getRemoteAddr() + "]" + delimiter);
		sb.append("Remote host = [" + request.getRemoteHost() + "]" + delimiter);
		sb.append("Remote port = [" + request.getRemotePort() + "]" + delimiter);
		sb.append("Remote user = [" + request.getRemoteUser() + "]" + delimiter);
		sb.append("Auth type = [" + request.getAuthType() + "]" + delimiter);
		String headers = Collections.list(request.getHeaderNames()).stream()
				.map(headerName -> headerName + " : " + Collections.list(request.getHeaders(headerName)))
				.collect(Collectors.joining(delimiter));

		if (headers.isEmpty()) {
			sb.append("Headers : NONE" + delimiter);
		} else {
			sb.append("Headers: [" + headers + "]" + delimiter);
		}

		String parameters = Collections.list(request.getParameterNames()).stream()
				.map(p -> p + " : " + Arrays.asList(request.getParameterValues(p)))
				.collect(Collectors.joining(delimiter));

		if (parameters.isEmpty()) {
			sb.append("Parameters: NONE" + delimiter);
		} else {
			sb.append("Parameters: [" + parameters + "]" + delimiter);
		}
		return sb.toString();
	}
	
	public static String getRequestURI() {
		HttpServletRequest request = getHttpServletRequest();
		if(request == null) {
			return null;
		}
		return request.getRequestURI().replaceFirst(request.getContextPath(), "");
	}
	
	private static void populateMiscExceptionDetails(Exception ex, ResponseData responseData) {
		if (ex instanceof HttpMessageNotReadableException || ex instanceof MethodArgumentTypeMismatchException || ex instanceof BindException) {
			responseData.setResponseCode(ResponseCodeEnum.BAD_REQUEST);
		}		
	}
	
	private static void populateServiceExceptionDetails(ServiceException se, ResponseData responseData, Map<String, Map<String, String>> detailMessage) {		
		if (ErrorCodeEnum.REQUEST_VALIDATION_FAILED.equals(se.getErrorCode()) && se.getCustomErrors() != null && !se.getCustomErrors().isEmpty()) {
			for (String field : se.getCustomErrors().keySet()) {
				String message = se.getCustomErrors().get(field);
				Map<String, String> msg = new HashMap<>();
				msg.put("message", message);
				detailMessage.put(field, msg);
			}
		}
		responseData.setResponseCode(ResponseCodeEnum.getByErrorCode(se.getErrorCode()));
		responseData.setStatusMessage(se.getStatusMessage());
	}
	
	private static void populateControllerExceptionDetails(ControllerException ce, ResponseData responseData, Map<String, Map<String, String>> detailMessage) {
		if (ce.getBindingErrors() != null && !ce.getBindingErrors().getFieldErrors().isEmpty()) {
			for (FieldError fieldError : ce.getBindingErrors().getFieldErrors()) {
				String message = fieldError.getDefaultMessage();
				Map<String, String> msg = new HashMap<>();
				msg.put("message", message);
				detailMessage.put(fieldError.getField(), msg);
			}
		}
		responseData.setResponseCode(ce.getResponseCode());
	}
}
