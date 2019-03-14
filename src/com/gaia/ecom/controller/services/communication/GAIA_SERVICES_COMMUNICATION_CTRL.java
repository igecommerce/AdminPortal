package com.gaia.ecom.controller.services.communication;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gaia.beans.GAIA_ECOM_BEANS;

@Controller
@RequestMapping("/communicationservices")
public class GAIA_SERVICES_COMMUNICATION_CTRL extends GAIA_ECOM_BEANS  {
	@RequestMapping(value = "saveemail", method = RequestMethod.POST)
	public ResponseEntity<Object> saveemail(
			@RequestBody String emailData,HttpServletRequest httpRequest) {

		try {
			
			
			responseInfoObj = communicationHelper.saveemail(emailData,
					communicationDAO,httpRequest);

			responseObj.setResponseValue(responseInfoObj);

			return new ResponseEntity<Object>(responseObj, new HttpHeaders(),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(),
					new HttpHeaders(), HttpStatus.OK);
		}

	}
	
	
	@RequestMapping(value = "loademailsettings", method = RequestMethod.GET)
	public ResponseEntity<Object> loademailsettings() {

		try {
			
			
			responseInfoObj = communicationHelper.loademailsettings(
					communicationDAO);

			responseObj.setResponseValue(responseInfoObj);

			return new ResponseEntity<Object>(responseObj, new HttpHeaders(),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(),
					new HttpHeaders(), HttpStatus.OK);
		}

	}
	
	
	@RequestMapping(value = "loadsmssettings", method = RequestMethod.GET)
	public ResponseEntity<Object> loadsmssettings() {

		try {
			
			
			responseInfoObj = communicationHelper.loadsmssettings(
					communicationDAO);

			responseObj.setResponseValue(responseInfoObj);

			return new ResponseEntity<Object>(responseObj, new HttpHeaders(),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(),
					new HttpHeaders(), HttpStatus.OK);
		}

	}
	
	
	@RequestMapping(value = "savesms", method = RequestMethod.POST)
	public ResponseEntity<Object> savesms(
			@RequestBody String smsData,HttpServletRequest httpRequest) {

		try {
			
			
			responseInfoObj = communicationHelper.savesms(smsData,
					communicationDAO,httpRequest);

			responseObj.setResponseValue(responseInfoObj);

			return new ResponseEntity<Object>(responseObj, new HttpHeaders(),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(),
					new HttpHeaders(), HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = "savemsg", method = RequestMethod.POST)
	public ResponseEntity<Object> savemsg(
			@RequestBody String smsData,HttpServletRequest httpRequest) {

		try {
			
			
			responseInfoObj = communicationHelper.savemsg(smsData,
					communicationDAO,httpRequest);

			responseObj.setResponseValue(responseInfoObj);

			return new ResponseEntity<Object>(responseObj, new HttpHeaders(),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(),
					new HttpHeaders(), HttpStatus.OK);
		}

	}
	

	@RequestMapping(value = "saveemailmsg", method = RequestMethod.POST)
	public ResponseEntity<Object> saveemailmsg(
			@RequestBody String emailData,HttpServletRequest httpRequest) {

		try {
			
			
			responseInfoObj = communicationHelper.saveemailmsg(emailData,
					communicationDAO,httpRequest);

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
