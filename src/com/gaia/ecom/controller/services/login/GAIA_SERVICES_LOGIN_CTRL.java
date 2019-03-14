package com.gaia.ecom.controller.services.login;

import javax.servlet.RequestDispatcher;
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
@RequestMapping("/loginservices")
public class GAIA_SERVICES_LOGIN_CTRL extends GAIA_ECOM_BEANS {
	private static Logger log = Logger.getLogger(GAIA_CONN_UTIL_IMPL.class);
	@RequestMapping(value = "validategaiasignin", method = RequestMethod.POST)
	public ResponseEntity<Object> validategaiasignin(
			@RequestBody String signinData,HttpServletRequest httpRequest) {

		try {
			log.info("jdbcTemplate   " + jdbcTemplate + "   "
					+ signinData);

			responseInfoObj = loginHelper.validategaiasignin(signinData,
					loginDAO,httpRequest);

			responseObj.setResponseType("S");
			responseObj.setResponseValue(responseInfoObj);

			return new ResponseEntity<Object>(responseObj, new HttpHeaders(),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(),
					new HttpHeaders(), HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = "changepassword", method = RequestMethod.POST)
	public ResponseEntity<Object> changepassword(
			@RequestBody String passwordData,HttpServletRequest httpRequest) {

		try {
			log.info("jdbcTemplate   " + jdbcTemplate + "   "
					+ passwordData);

			responseInfoObj = loginHelper.changepassword(passwordData,loginDAO,httpRequest);

			responseObj.setResponseType("S");
			responseObj.setResponseValue(responseInfoObj);

			return new ResponseEntity<Object>(responseObj, new HttpHeaders(),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(),
					new HttpHeaders(), HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = "resetpassword", method = RequestMethod.POST)
	public ResponseEntity<Object> resetpassword(
			@RequestBody String username,HttpServletRequest httpRequest) {

		try {
			log.info("jdbcTemplate   " + jdbcTemplate + "   "
					+ username);

			responseInfoObj = loginHelper.resetpassword(username,loginDAO,httpRequest);

			responseObj.setResponseType("S");
			responseObj.setResponseValue(responseInfoObj);

			return new ResponseEntity<Object>(responseObj, new HttpHeaders(),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(),
					new HttpHeaders(), HttpStatus.OK);
		}

	}

	@RequestMapping(value = "logout", method = RequestMethod.POST)
	public ResponseEntity<Object> logout(HttpServletRequest httpRequest,HttpServletResponse httpResponse) {

		try {
			HttpSession session = httpRequest.getSession();
			session.invalidate();
			responseObj.setResponseType("S");
			responseObj.setResponseValue(responseInfoObj);
			
			return new ResponseEntity<Object>(responseObj, new HttpHeaders(),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(responseObj, new HttpHeaders(),
					HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = "registration", method = RequestMethod.POST)
	public ResponseEntity<Object> registeruser(@RequestBody String regnValue,HttpServletRequest httpRequest) {

		try {
			log.info("jdbcTemplate   " + jdbcTemplate + "   "
					+ regnValue);

			responseInfoObj = loginHelper.registeruser(regnValue, loginDAO,httpRequest);

			responseObj.setResponseType("S");
			responseObj.setResponseValue(responseInfoObj);

			return new ResponseEntity<Object>(responseObj, new HttpHeaders(),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(),
					new HttpHeaders(), HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = "userlist", method = RequestMethod.GET)
	public ResponseEntity<Object> userlist() {

		try {
			log.info("jdbcTemplate   " + jdbcTemplate );

			responseInfoObj = loginHelper.userlist(loginDAO);

			responseObj.setResponseType("S");
			responseObj.setResponseValue(responseInfoObj);

			return new ResponseEntity<Object>(responseObj, new HttpHeaders(),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(),
					new HttpHeaders(), HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = "userroles", method = RequestMethod.GET)
	public ResponseEntity<Object> userroles() {

		try {
			responseInfoObj = loginHelper.userroles(loginDAO);

			responseObj.setResponseType("S");
			responseObj.setResponseValue(responseInfoObj);

			return new ResponseEntity<Object>(responseObj, new HttpHeaders(),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(),
					new HttpHeaders(), HttpStatus.OK);
		}

	}
	@RequestMapping(value = "saveuserrole", method = RequestMethod.POST)
	public ResponseEntity<Object> saveuserrole(@RequestBody String roleData) {

		try {
			log.info("save user role");

			responseInfoObj = loginHelper.saveuserrole(roleData, loginDAO);

			responseObj.setResponseType("S");
			responseObj.setResponseValue(responseInfoObj);

			return new ResponseEntity<Object>(responseObj, new HttpHeaders(),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(),
					new HttpHeaders(), HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = "saveusermenus", method = RequestMethod.POST)
	public ResponseEntity<Object> saveusermenus(@RequestBody String roleData) {

		try {
			log.info("save user role");

			responseInfoObj = loginHelper.saveusermenus(roleData, loginDAO);

			responseObj.setResponseType("S");
			responseObj.setResponseValue(responseInfoObj);

			return new ResponseEntity<Object>(responseObj, new HttpHeaders(),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(),
					new HttpHeaders(), HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = "getuserdetails/{userid}", method = RequestMethod.GET)
	public ResponseEntity<Object> getuserdetails(@PathVariable("userid")String userid) {

		try {
			log.info("jdbcTemplate   " + jdbcTemplate );

			responseInfoObj = loginHelper.getuserdetails(loginDAO,userid);

			responseObj.setResponseType("S");
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
