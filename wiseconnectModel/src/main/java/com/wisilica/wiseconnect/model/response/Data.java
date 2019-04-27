package com.wisilica.wiseconnect.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wisilica.wiseconnect.model.AbstractWisconnectBean;

public class Data extends AbstractWisconnectBean{

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long operateLogId;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long apiToSocketTime;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long totalExecTime;

	public Long getOperateLogId() {
		return operateLogId;
	}

	public void setOperateLogId(Long operateLogId) {
		this.operateLogId = operateLogId;
	}

	public Long getApiToSocketTime() {
		return apiToSocketTime;
	}

	public void setApiToSocketTime(Long apiToSocketTime) {
		this.apiToSocketTime = apiToSocketTime;
	}

	public Long getTotalExecTime() {
		return totalExecTime;
	}

	public void setTotalExecTime(Long totalExecTime) {
		this.totalExecTime = totalExecTime;
	}
}
