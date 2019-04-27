package com.wisilica.wiseconnect.core.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wisilica.wiseconnect.core.service.OperateService;
import com.wisilica.wiseconnect.model.request.OperateRequest;
import com.wisilica.wiseconnect.model.util.OperationResult;
import com.wisilica.wiseconnect.model.util.UserLoginContext;

@Service
public class OperateServiceImpl implements OperateService {	

	@Transactional	
	@Override
	public OperationResult operate(OperateRequest operateRequest, UserLoginContext userLoginContext) {

		OperationResult operationResult = new OperationResult();
		operationResult.setOperateLogId(1L);
		operationResult.setOperationCount(2);
		return operationResult;
	}
}
