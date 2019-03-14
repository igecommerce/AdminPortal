package com.gaia.ecom.controller.services.communication;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.springframework.stereotype.Service;

import com.gaia.beans.GAIA_ECOM_BEANS;
import com.gaia.conn.util.GAIA_CONN_UTIL_IMPL;
import com.gaia.ecom.controller.response.GAIA_ECOM_RESPONSEINFO;
import com.gaia.ecom.controller.services.configuration.GAIA_SERVICES_CONFIG_ORDER_DETAILS;
import com.gaia.util.GAIA_UTILS;

@Service
public class GAIA_SERVICES_COMMUNICATION_DAO_IMPL extends GAIA_ECOM_BEANS implements GAIA_SERVICES_ICOMMUNICATION_DAO{
	
	private static Logger log = Logger.getLogger(GAIA_SERVICES_COMMUNICATION_DAO_IMPL.class);

	
	public GAIA_ECOM_RESPONSEINFO loademailsettings() {
		
		Object[] inputValue = new Object[] {};
		log.info("inputValue   " + inputValue);
		List emailList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				env.getRequiredProperty("emaillist"), inputValue,
				GAIA_SERVICES_CONFIG_ORDER_DETAILS.class);
		log.info("emailList   " + emailList);

		responseInfoObj.setGaiaresponse(emailList);
		
		return responseInfoObj;
	}
	
public GAIA_ECOM_RESPONSEINFO loadsmssettings() {
		
		Object[] inputValue = new Object[] {};
		log.info("inputValue   " + inputValue);
		List smsList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				env.getRequiredProperty("smslist"), inputValue,
				GAIA_SERVICES_CONFIG_ORDER_DETAILS.class);
		log.info("emailList   " + smsList);

		responseInfoObj.setGaiaresponse(smsList);
		
		return responseInfoObj;
	}
	
	@Override
	public GAIA_ECOM_RESPONSEINFO saveemail(String emailData,HttpServletRequest httpRequest)
			 {
		try{
		// TODO Auto-generated method stub
			GAIA_SERVICES_CONFIG_ORDER_DETAILS request = (GAIA_SERVICES_CONFIG_ORDER_DETAILS) GAIA_UTILS.convertJSONtooOBJECT(emailData, GAIA_SERVICES_CONFIG_ORDER_DETAILS.class);
		

		LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
		inParamMap.put("P_EMAIL_ID", request.getEmailid());
		inParamMap.put("P_EMAIL_SERVERUP", request.getServerup());
		inParamMap.put("P_SMTP_PORT_NAME", request.getSmtpport());
		inParamMap.put("P_SMTP_MAIL", request.getSmtpemail());
		inParamMap.put("P_USERNAME", request.getEmailusername());
		inParamMap.put("P_PASSWORD", request.getEmailpassword());
		inParamMap.put("P_OPERATION", request.getOprn());

		log.info(inParamMap);

		Map<String, Object> resultMap = GAIA_CONN_UTIL_IMPL.executeProcedure(
				jdbcTemplate, "P_EMAIL_SETTINGS", inParamMap);
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
	public GAIA_ECOM_RESPONSEINFO savesms(String smsData,HttpServletRequest httpRequest)
			 {
		try{
		// TODO Auto-generated method stub
			GAIA_SERVICES_CONFIG_ORDER_DETAILS request = (GAIA_SERVICES_CONFIG_ORDER_DETAILS) GAIA_UTILS.convertJSONtooOBJECT(smsData, GAIA_SERVICES_CONFIG_ORDER_DETAILS.class);
		

		LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
		inParamMap.put("P_SMS_ID", request.getSmsid());
		inParamMap.put("P_SMS_KEY", request.getSmskey());
		inParamMap.put("P_USER_NAME", request.getSmsusername());
		inParamMap.put("P_PASSWORD", request.getSmspassword());
		inParamMap.put("P_OPERATION", request.getOprn());

		log.info(inParamMap);

		Map<String, Object> resultMap = GAIA_CONN_UTIL_IMPL.executeProcedure(
				jdbcTemplate, "P_SMS_SETTINGS", inParamMap);
		Log.info(resultMap);
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
	public GAIA_ECOM_RESPONSEINFO savemsg(String smsData,HttpServletRequest httpRequest)
			 {
		try{
		// TODO Auto-generated method stub
			GAIA_SERVICES_CONFIG_ORDER_DETAILS request = (GAIA_SERVICES_CONFIG_ORDER_DETAILS) GAIA_UTILS.convertJSONtooOBJECT(smsData, GAIA_SERVICES_CONFIG_ORDER_DETAILS.class);
		

		LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
		inParamMap.put("P_MSG_ID", request.getTempid());
		inParamMap.put("P_MSG_CONTENT", request.getMsgcontent());
		inParamMap.put("P_OPRN", "SAVEMSG");
		inParamMap.put("P_EMAILNAME", request.getEmailname());
		//System.out.println(inParamMap);

		Map<String, Object> resultMap = GAIA_CONN_UTIL_IMPL.executeProcedure(
				jdbcTemplate, "P_GAIA_SAVE_MSGCONTENT", inParamMap);
		//System.out.println(resultMap);
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
	public GAIA_ECOM_RESPONSEINFO saveemailmsg(String emailData,HttpServletRequest httpRequest)
			 {
		try{
		// TODO Auto-generated method stub
			GAIA_SERVICES_CONFIG_ORDER_DETAILS request = (GAIA_SERVICES_CONFIG_ORDER_DETAILS) GAIA_UTILS.convertJSONtooOBJECT(emailData, GAIA_SERVICES_CONFIG_ORDER_DETAILS.class);
		

		LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
		inParamMap.put("P_MSG_ID", request.getEmailid());
		inParamMap.put("P_MSG_CONTENT", request.getMsgcontent());
		inParamMap.put("P_EMAILNAME", request.getEmailname());
		inParamMap.put("P_OPRN", "SAVEEMAIL");

		//System.out.println(inParamMap);

		Map<String, Object> resultMap = GAIA_CONN_UTIL_IMPL.executeProcedure(
				jdbcTemplate, "P_GAIA_SAVE_MSGCONTENT", inParamMap);
		//System.out.println(resultMap);
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
