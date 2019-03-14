package com.gaia.ecom.controller.services.customer;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.gaia.beans.GAIA_ECOM_BEANS;
import com.gaia.conn.util.GAIA_CONN_UTIL_IMPL;
import com.gaia.ecom.controller.response.GAIA_ECOM_RESPONSEINFO;
import com.gaia.ecom.controller.services.configuration.GAIA_SERVICES_CONFIG_CATEGORY_DETAIL;
import com.gaia.ecom.controller.services.configuration.GAIA_SERVICES_CONFIG_DAO_IMPL;
import com.gaia.ecom.controller.services.configuration.GAIA_SERVICES_CONFIG_PRODUCT_DETAIL;
import com.gaia.util.GAIA_UTILS;

@Service
public class GAIA_SERVICES_CUSTOMER_DAO_IMPL  extends GAIA_ECOM_BEANS implements GAIA_SERVICES_ICUSTOMER_DAO  {
	private static Logger log = Logger.getLogger(GAIA_SERVICES_CUSTOMER_DAO_IMPL.class);
	
	@Override
	public GAIA_ECOM_RESPONSEINFO savestatuscustomer(String customerData,HttpServletRequest httpRequest)
			 {
		try{
		// TODO Auto-generated method stub
			GAIA_SERVICES_CUSTOMER_DETAILS request = (GAIA_SERVICES_CUSTOMER_DETAILS) GAIA_UTILS.convertJSONtooOBJECT(customerData,GAIA_SERVICES_CUSTOMER_DETAILS.class);
		

		LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
		inParamMap.put("P_CUSTOMER_ID", request.getCustomerid());
		inParamMap.put("P_STATUS", request.getStatus());
		inParamMap.put("P_ACTION", "UPD");
		
		log.info(inParamMap);

		Map<String, Object> resultMap = GAIA_CONN_UTIL_IMPL.executeProcedure(
				jdbcTemplate, "P_CUSTOMER_OPRN", inParamMap);
		log.info(resultMap);
		responseInfoObj.setGaiaresponse(resultMap);

		}catch(Exception e)
		{
			e.printStackTrace();
			responseInfoObj.setResponseType("F");
			responseInfoObj.setGaiaresponse("Exception Occured.Kindly contact system support.");
		}
		
		return responseInfoObj;
	}
	
	@Override
	public GAIA_ECOM_RESPONSEINFO savestatusproduct(String productData,HttpServletRequest httpRequest)
			 {
		try{
		// TODO Auto-generated method stub
			GAIA_SERVICES_CUSTOMER_PRODUCT_DETAILS request = (GAIA_SERVICES_CUSTOMER_PRODUCT_DETAILS) GAIA_UTILS.convertJSONtooOBJECT(productData, GAIA_SERVICES_CUSTOMER_PRODUCT_DETAILS.class);
		

		LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
		inParamMap.put("P_PRODUCT_ID", request.getProductid());
		inParamMap.put("P_STATUS", request.getStatus());
		inParamMap.put("P_ACTION", "UPD");
		
		log.info(inParamMap);

		Map<String, Object> resultMap = GAIA_CONN_UTIL_IMPL.executeProcedure(
				jdbcTemplate, "P_PRODUCT_STATUS_OPRN", inParamMap);
		log.info(resultMap);
		responseInfoObj.setGaiaresponse(resultMap);

		}catch(Exception e)
		{
			e.printStackTrace();
			responseInfoObj.setResponseType("F");
			responseInfoObj.setGaiaresponse("Exception Occured.Kindly contact system support.");
		}
		
		return responseInfoObj;
	}
	
	

}
