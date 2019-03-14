package com.gaia.ecom.controller.services.banner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
@RequestMapping("/bannerservices")
public class GAIA_SERVICES_BANNER_CTRL extends GAIA_ECOM_BEANS {
	
	private static Logger log = Logger.getLogger(GAIA_SERVICES_BANNER_CTRL.class);
	
	@RequestMapping(value = "importbanner", method = RequestMethod.POST)
	public ResponseEntity<Object> importbanner(
			@RequestBody String importData,HttpServletRequest httpRequest) {

		try {
			log.info("jdbcTemplate   " + jdbcTemplate + "   "
					+ importData);
			
			responseInfoObj = bannerHelper.importbanner(importData,
					bannerDAO,httpRequest);

			responseObj.setResponseValue(responseInfoObj);

			return new ResponseEntity<Object>(responseObj, new HttpHeaders(),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(),
					new HttpHeaders(), HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = "importbulkuploadbannertype", method = RequestMethod.POST)
	public ResponseEntity<Object> importbulkuploadbannertype(
			@RequestBody String importData,HttpServletRequest httpRequest) {

		try {
			log.info("jdbcTemplate   " + jdbcTemplate + "   "
					+ importData);
			
			responseInfoObj = bannerHelper.importbulkuploadbannertype(importData,
					bannerDAO,httpRequest);

			responseObj.setResponseValue(responseInfoObj);

			return new ResponseEntity<Object>(responseObj, new HttpHeaders(),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(),
					new HttpHeaders(), HttpStatus.OK);
		}

	}
	
	
	@RequestMapping("/downloadbannerformat")
    public void downloadbannerformat(HttpServletRequest httpRequest,
                                     HttpServletResponse httpResponse
                                    )
    {
		String fileName = "sampleformat.xlsx";
		log.info("fileName    "+fileName);
		bannerHelper.downloadbannerformat(fileName, bannerDAO, httpRequest, httpResponse);
		
    }
	
	@RequestMapping("/downloadbannerformatforbannertype")
    public void downloadbannerformatforbannertype(HttpServletRequest httpRequest,
                                     HttpServletResponse httpResponse
                                    )
    {
		String fileName = "sampleformat.xlsx";
		log.info("fileName    "+fileName);
		bannerHelper.downloadbannerformatforbannertype(fileName, bannerDAO, httpRequest, httpResponse);
		
    }
	
	
	@RequestMapping(value = "savebanner", method = RequestMethod.POST)
	public ResponseEntity<Object> savebanner(
			@RequestBody String uploadData,HttpServletRequest httpRequest) {

		try {
			
			
			responseInfoObj = bannerHelper.savebanner(uploadData,
					bannerDAO,httpRequest);

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
