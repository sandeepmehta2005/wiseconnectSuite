package com.wisilica.wiseconnect.core.service;

import org.springframework.stereotype.Service;

import com.wisilica.wiseconnect.model.request.OperateRequest;
import com.wisilica.wiseconnect.model.util.OperationResult;
import com.wisilica.wiseconnect.model.util.UserLoginContext;

@Service
public interface OperateService {
	
	OperationResult operate(OperateRequest operateRequest, UserLoginContext userLoginContext);

}
