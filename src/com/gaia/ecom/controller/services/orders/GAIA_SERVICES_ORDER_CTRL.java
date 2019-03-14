package com.gaia.ecom.controller.services.orders;

import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gaia.beans.GAIA_ECOM_BEANS;
import com.gaia.conn.util.GAIA_CONN_UTIL_IMPL;

@Controller
@RequestMapping("/order")
public class GAIA_SERVICES_ORDER_CTRL extends GAIA_ECOM_BEANS{
	private static Logger log = Logger.getLogger(GAIA_CONN_UTIL_IMPL.class);
	@RequestMapping(value = "orderlistpage", method = RequestMethod.GET)
	public ResponseEntity<Object> orderlist() {

		try {
			log.info("listprod");
			
			responseInfoObj = orderHelper.orderlist(orderDAO);
					

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
