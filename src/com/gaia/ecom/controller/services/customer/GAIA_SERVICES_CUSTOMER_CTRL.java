package com.gaia.ecom.controller.services.customer;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gaia.beans.GAIA_ECOM_BEANS;
import com.gaia.conn.util.GAIA_CONN_UTIL_IMPL;

@Controller
@RequestMapping("/customer")
public class GAIA_SERVICES_CUSTOMER_CTRL extends GAIA_ECOM_BEANS{
	private static Logger log = Logger.getLogger(GAIA_CONN_UTIL_IMPL.class);
	
	@RequestMapping(value = "savestatusproduct", method = RequestMethod.POST)
	public ResponseEntity<Object> savestatusproduct(
			@RequestBody String productData,HttpServletRequest httpRequest) {

		try {
			
			responseInfoObj = customerHelper.savestatusproduct(productData,
					customerDAO,httpRequest);

			responseObj.setResponseValue(responseInfoObj);

			return new ResponseEntity<Object>(responseObj, new HttpHeaders(),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(),
					new HttpHeaders(), HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = "savestatuscustomer", method = RequestMethod.POST)
	public ResponseEntity<Object> savestatuscustomer(
			@RequestBody String customerData,HttpServletRequest httpRequest) {

		try {
			
			responseInfoObj = customerHelper.savestatuscustomer(customerData,
					customerDAO,httpRequest);

			responseObj.setResponseValue(responseInfoObj);

			return new ResponseEntity<Object>(responseObj, new HttpHeaders(),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(),
					new HttpHeaders(), HttpStatus.OK);
		}

	}
	

}
