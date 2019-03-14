package com.gaia.ecom.controller.services.configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gaia.beans.GAIA_ECOM_BEANS;
import com.gaia.conn.util.GAIA_CONN_UTIL_IMPL;

@Controller
@RequestMapping("/configurationservices")
public class GAIA_SERVICES_CONFIG_CTRL extends GAIA_ECOM_BEANS {
	private static Logger log = Logger.getLogger(GAIA_CONN_UTIL_IMPL.class);
	
	

	
	@RequestMapping("/exportOrderTable")
    public void exportTable( HttpServletRequest httpRequest,
                                     HttpServletResponse httpResponse/*,
                                     @PathVariable("fileName") String fileName*/)
    {
		String fileName = "order.xlsx";
		log.info("fileName    "+fileName);
		configHelper.exportOrderTable(fileName, configDAO, httpRequest, httpResponse);
    }
	
	
	
	@RequestMapping(value = "savetax", method = RequestMethod.POST)
	public ResponseEntity<Object> savetax(
			@RequestBody String taxData,HttpServletRequest httpRequest) {

		try {
			
			
			responseInfoObj = configHelper.savetax(taxData,
					configDAO,httpRequest);

			responseObj.setResponseValue(responseInfoObj);

			return new ResponseEntity<Object>(responseObj, new HttpHeaders(),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(),
					new HttpHeaders(), HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = "saveshipconfig", method = RequestMethod.POST)
	public ResponseEntity<Object> saveshipconfig(
			@RequestBody String shipData,HttpServletRequest httpRequest) {

		try {
			
			
			responseInfoObj = configHelper.saveshipconfig(shipData,
					configDAO,httpRequest);

			responseObj.setResponseValue(responseInfoObj);

			return new ResponseEntity<Object>(responseObj, new HttpHeaders(),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(),
					new HttpHeaders(), HttpStatus.OK);
		}

	}
	
	
	
	
	/*
	@RequestMapping(value = "getcategoryhierarchylist", method = RequestMethod.GET)
	public ResponseEntity<Object> gethierarchylist(HttpServletRequest request) {

		try {
			//System.out.println("applicationurl  );
			
			responseInfoObj = configHelper.getcategoryhierarchylist(configDAO,request);
					

			responseObj.setResponseValue(responseInfoObj);

			return new ResponseEntity<Object>(responseObj, new HttpHeaders(),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(),
					new HttpHeaders(), HttpStatus.OK);
		}

	}
	*/

	@RequestMapping(value = "savemincart", method = RequestMethod.POST)
	public ResponseEntity<Object> savemincart(
			@RequestBody String mincartData,HttpServletRequest httpRequest) {

		try {
			
			
			responseInfoObj = configHelper.savemincart(mincartData,
					configDAO,httpRequest);

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
