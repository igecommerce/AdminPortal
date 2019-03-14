package com.gaia.ecom.controller.services.products;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
@RequestMapping("/product")
public class GAIA_SERVICES_PRODUCT_CTRL extends GAIA_ECOM_BEANS {
	private static Logger log = Logger.getLogger(GAIA_CONN_UTIL_IMPL.class);
	
	@RequestMapping(value = "importtoproduct/{digits}", method = RequestMethod.POST)
	public ResponseEntity<Object> importtoproduct(@PathVariable("digits") String digits,
			@RequestBody String importData,HttpServletRequest httpRequest) {

		try {
			log.info("digits   "
					+ digits);
			
			responseInfoObj = productHelper.importtoproduct(importData,digits,
					productDAO,httpRequest);

			responseObj.setResponseValue(responseInfoObj);

			return new ResponseEntity<Object>(responseObj, new HttpHeaders(),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(),
					new HttpHeaders(), HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = "updateproduct", method = RequestMethod.POST)
	public ResponseEntity<Object> updateproduct(
			@RequestBody String importData,HttpServletRequest httpRequest) {

		try {
			log.info("jdbcTemplate   " + jdbcTemplate + "   "
					+ importData);
			
			responseInfoObj = productHelper.updateproduct(importData,
					productDAO,httpRequest);

			responseObj.setResponseValue(responseInfoObj);

			return new ResponseEntity<Object>(responseObj, new HttpHeaders(),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(),
					new HttpHeaders(), HttpStatus.OK);
		}

	}
	
	@RequestMapping(value="downloadsampleformat/{columnsheet}")
    public void downloadSampleFormat(@PathVariable("columnsheet") String columnsheet, HttpServletRequest httpRequest,
                                     HttpServletResponse httpResponse
                                    )
    {
		String fileName = "sampleformat.xlsx";
		log.info("fileName    "+columnsheet);
		productHelper.downloadSampleFormat(fileName,columnsheet, productDAO, httpRequest, httpResponse);
		
    }
	
	@RequestMapping(value = "createproduct", method = RequestMethod.POST)
	public ResponseEntity<Object> createproduct(
			@RequestBody String productData,HttpServletRequest httpRequest) {

		try {
			
			responseInfoObj = productHelper.createproduct(productData,
					productDAO,httpRequest);

			responseObj.setResponseValue(responseInfoObj);

			return new ResponseEntity<Object>(responseObj, new HttpHeaders(),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(),
					new HttpHeaders(), HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = "cancelimport/{digits}", method = RequestMethod.POST)
	public ResponseEntity<Object> cancelimport(@PathVariable("digits") String digits,
			@RequestBody String productData,HttpServletRequest httpRequest) {

		try {
			
			responseInfoObj = productHelper.cancelimport(digits,productData,
					productDAO,httpRequest);
            log.info("input "+digits);
			responseObj.setResponseValue(responseInfoObj);

			return new ResponseEntity<Object>(responseObj, new HttpHeaders(),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(),
					new HttpHeaders(), HttpStatus.OK);
		}

	}
	
	
	
	@RequestMapping(value = "savebrand", method = RequestMethod.POST)
	public ResponseEntity<Object> savebrand(
			@RequestBody String brandData,HttpServletRequest httpRequest) {

		try {
			
			
			responseInfoObj = productHelper.savebrand(brandData,
					productDAO,httpRequest);

			responseObj.setResponseValue(responseInfoObj);

			return new ResponseEntity<Object>(responseObj, new HttpHeaders(),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(),
					new HttpHeaders(), HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = "saveuom", method = RequestMethod.POST)
	public ResponseEntity<Object> saveuom(
			@RequestBody String uomData,HttpServletRequest httpRequest) {

		try {
			
			
			responseInfoObj = productHelper.saveuom(uomData,
					productDAO,httpRequest);

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
