package com.wisilica.wiseconnect.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.wisilica.wiseconnect.model.AbstractWisconnectBean;

@JsonRootName(value = "Response")
public class APIResponse extends AbstractWisconnectBean{

	@JsonProperty("Status")
	private Status status;

	@JsonProperty("Data")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Data data;
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}
}
