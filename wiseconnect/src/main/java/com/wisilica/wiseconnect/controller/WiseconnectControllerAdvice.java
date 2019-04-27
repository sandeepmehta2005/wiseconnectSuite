package com.wisilica.wiseconnect.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.wisilica.wiseconnect.commons.constants.APIIdEnum;
import com.wisilica.wiseconnect.commons.constants.ResponseCodeEnum;
import com.wisilica.wiseconnect.commons.constants.WiseconnectConstants;
import com.wisilica.wiseconnect.commons.type.ResponseData;
import com.wisilica.wiseconnect.model.response.APIResponse;
import com.wisilica.wiseconnect.model.response.Data;
import com.wisilica.wiseconnect.model.response.Status;
import com.wisilica.wiseconnect.util.WebUtil;

@ControllerAdvice
public class WiseconnectControllerAdvice implements ResponseBodyAdvice<Object> {

	private static final Logger LOGGER = LoggerFactory.getLogger(WiseconnectControllerAdvice.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<APIResponse> handleException(HttpServletRequest request, HttpServletResponse response,
			Exception ex) {
		LOGGER.error("Exception occured while executing {}", request.getRequestURI(), ex);

		APIResponse apiResponse = new APIResponse();
		Status status = new Status();
		apiResponse.setStatus(status);
		Map<String, Map<String, String>> detailMessage = status.getDetailMessage();

		ResponseData responseData = WebUtil.getResponseFromException(ex, detailMessage);
		ResponseCodeEnum responseCode = responseData.getResponseCode();

		status.setStatusCode(responseCode.getWiseconnectStatus().getValue());
		if (responseData.getStatusMessage() != null) {
			status.setStatusMessage(responseData.getStatusMessage());
		} else {
			status.setStatusMessage(responseCode.getWiseconnectStatus().getDescription());
		}
		return new ResponseEntity<>(apiResponse, responseCode.getHttpStatus());
	}

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		if (body instanceof APIResponse) {
			APIResponse apiResponse = (APIResponse)body;
			Status status = apiResponse.getStatus();
			status.setVersion(WiseconnectConstants.API_VERSION);			
			APIIdEnum apiId = APIIdEnum.getByUrl(WebUtil.getRequestURI());
			if (apiId != null) {
				status.setApiId(apiId.getId());
			}
			Long startTimeVal = ((Long) ((ServletServerHttpRequest) request).getServletRequest()
					.getAttribute(WebUtil.API_START_TIME)) / 1000;
			Long endTimeVal = System.currentTimeMillis() / 1000;
			status.setStartTime(startTimeVal);
			status.setEndTime(endTimeVal);
			Data data = apiResponse.getData();
			if (data != null) {
				data.setApiToSocketTime(startTimeVal);
				data.setTotalExecTime(endTimeVal - startTimeVal);
			}
		}
		return body;
	}
}
