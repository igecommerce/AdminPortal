package com.gaia.ecom.controller.response;

import org.springframework.stereotype.Service;

@Service("gaiaresponse")
public class GAIA_ECOM_RESPONSE {

	private String responseType;
	private Object responseValue;
	public String getResponseType() {
		return responseType;
	}
	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}
	public Object getResponseValue() {
		return responseValue;
	}
	public void setResponseValue(Object responseValue) {
		this.responseValue = responseValue;
	}
	
	
}
