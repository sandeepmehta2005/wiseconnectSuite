package com.wisilica.wiseconnect.controller.device;
/**
 *  WiseConnect Controller for operate module
 *
 *  @modified By Josny Jose
 *  @Date  29-Mar-2019
 *
 * Description : operate endpoints
 *
 * */
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wisilica.wiseconnect.commons.WiseconnectContextProvider;
import com.wisilica.wiseconnect.commons.constants.WiseconnectStatusEnum;
import com.wisilica.wiseconnect.commons.exception.ControllerException;
import com.wisilica.wiseconnect.core.service.OperateService;
import com.wisilica.wiseconnect.model.request.OperateRequest;
import com.wisilica.wiseconnect.model.response.APIResponse;
import com.wisilica.wiseconnect.model.response.Data;
import com.wisilica.wiseconnect.model.response.Status;
import com.wisilica.wiseconnect.model.util.OperationResult;
import com.wisilica.wiseconnect.model.util.UserLoginContext;
import com.wisilica.wiseconnect.util.WebUtil;

@RestController
public class OperateController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OperateController.class);
	public static final String ROLE_ADMIN = "ADMIN";
	
	@Autowired
	private WiseconnectContextProvider wiseconnectContextProvider;

	@Secured({ROLE_ADMIN})
	@PostMapping("/public/operate/")
	public APIResponse operate(@RequestBody @Valid OperateRequest operateRequest, BindingResult bindingResult,
			HttpServletRequest request) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Operate request Received:  {}", operateRequest);
		}

		if (bindingResult.hasErrors()) {
			LOGGER.error("Input Validation errors. Unable to proceed with processing");
			throw new ControllerException(bindingResult, "Validation Error");
		}
		
		UserLoginContext userLoginContext = WebUtil.getUserLoginContext();
		
		APIResponse apiResponse = new APIResponse();
		Status status = new Status();
		Data data = new Data();
		apiResponse.setStatus(status);
		apiResponse.setData(data);	
		
		OperateService operateService = wiseconnectContextProvider.getBean(OperateService.class);
		
		OperationResult operationResult = operateService.operate(operateRequest, userLoginContext);
		
		status.setStatusCode(WiseconnectStatusEnum.SUCCESS.getValue());
		status.setStatusMessage("Operation logged");
		data.setOperateLogId(operationResult.getOperateLogId());
		
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Operate request Process completed, Operate LogId  {}", operationResult.getOperateLogId());
		}
		return apiResponse;
	}
}
