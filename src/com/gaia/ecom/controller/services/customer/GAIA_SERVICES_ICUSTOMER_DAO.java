package com.gaia.ecom.controller.services.customer;

import javax.servlet.http.HttpServletRequest;

import com.gaia.ecom.controller.response.GAIA_ECOM_RESPONSEINFO;

public interface GAIA_SERVICES_ICUSTOMER_DAO {
	public GAIA_ECOM_RESPONSEINFO savestatusproduct(String productData,HttpServletRequest httpRequest);
	public GAIA_ECOM_RESPONSEINFO savestatuscustomer(String customerData,HttpServletRequest httpRequest);

}
