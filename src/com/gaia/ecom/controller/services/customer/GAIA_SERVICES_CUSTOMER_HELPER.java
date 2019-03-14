package com.gaia.ecom.controller.services.customer;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.gaia.ecom.controller.response.GAIA_ECOM_RESPONSEINFO;
import com.gaia.ecom.controller.services.configuration.GAIA_SERVICES_ICONFIG_DAO;

@Service
public class GAIA_SERVICES_CUSTOMER_HELPER {
	
	public GAIA_ECOM_RESPONSEINFO savestatusproduct(String productData,GAIA_SERVICES_ICUSTOMER_DAO customerDAO,HttpServletRequest httpRequest)
	{
		return customerDAO.savestatusproduct(productData,httpRequest);
	}
	
	
	public GAIA_ECOM_RESPONSEINFO savestatuscustomer(String customerData,GAIA_SERVICES_ICUSTOMER_DAO customerDAO,HttpServletRequest httpRequest)
	{
		return customerDAO.savestatuscustomer(customerData,httpRequest);
	}
}
