package com.gaia.ecom.controller.response;



import org.springframework.stereotype.Service;

@Service("gaiaresponseinfo")
public class GAIA_ECOM_RESPONSEINFO {
	private String responseType;
	private Object gaiaresponse;
	
	

	public String getResponseType() {
		return responseType;
	}

	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}

	public Object getGaiaresponse() {
		return gaiaresponse;
	}

	public void setGaiaresponse(Object gaiaresponse) {
		this.gaiaresponse = gaiaresponse;
	}

}
