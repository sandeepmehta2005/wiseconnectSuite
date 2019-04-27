package com.wisilica.wiseconnect.model.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.wisilica.wiseconnect.model.AbstractWisconnectBean;

public class OperateRequest extends AbstractWisconnectBean{
	
	@NotNull(message = "{validation.field.empty}")	
	@Min(value = 0, message = "{validation.field.grpDvc.not_between}")
	@Max(value = 6, message = "{validation.field.grpDvc.not_between}")
	private Integer grpDvc;
	
	@NotNull(message = "{validation.field.empty}")
	@Positive(message = "{validation.field.not_digit}")
    private Long grpDvcId;
    
    private String rgb;
    
    private Integer cool;
    
    private Integer intensity;
    
    @NotNull(message = "{validation.field.empty}")
    private Integer operationId;
    
    @NotNull(message = "{validation.field.empty}")
    @Positive(message = "{validation.field.not_digit}")
    private Long organizationId;
    
    private String serverOperationKey;
    
    private Long rootOrgId;
    
    private String controlElement;
    
    private Integer assocId;

	public Integer getGrpDvc() {
		return grpDvc;
	}

	public void setGrpDvc(Integer grpDvc) {
		this.grpDvc = grpDvc;
	}

	public Long getGrpDvcId() {
		return grpDvcId;
	}

	public void setGrpDvcId(Long grpDvcId) {
		this.grpDvcId = grpDvcId;
	}

	public String getRgb() {
		return rgb;
	}

	public void setRgb(String rgb) {
		this.rgb = rgb;
	}

	public Integer getCool() {
		return cool;
	}

	public void setCool(Integer cool) {
		this.cool = cool;
	}

	public Integer getIntensity() {
		return intensity;
	}

	public void setIntensity(Integer intensity) {
		this.intensity = intensity;
	}

	public Integer getOperationId() {
		return operationId;
	}

	public void setOperationId(Integer operationId) {
		this.operationId = operationId;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getServerOperationKey() {
		return serverOperationKey;
	}

	public void setServerOperationKey(String serverOperationKey) {
		this.serverOperationKey = serverOperationKey;
	}

	public Long getRootOrgId() {
		return rootOrgId;
	}

	public void setRootOrgId(Long rootOrgId) {
		this.rootOrgId = rootOrgId;
	}

	public String getControlElement() {
		return controlElement;
	}

	public void setControlElement(String controlElement) {
		this.controlElement = controlElement;
	}

	public Integer getAssocId() {
		return assocId;
	}

	public void setAssocId(Integer assocId) {
		this.assocId = assocId;
	}	
}
