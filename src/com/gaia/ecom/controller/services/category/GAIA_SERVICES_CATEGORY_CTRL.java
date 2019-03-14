package com.gaia.ecom.controller.services.category;

import javax.servlet.http.HttpServletRequest;

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

@Controller
@RequestMapping("/categoryservices")
public class GAIA_SERVICES_CATEGORY_CTRL extends GAIA_ECOM_BEANS {
	private static Logger log = Logger.getLogger(GAIA_SERVICES_CATEGORY_CTRL.class);
	
	@RequestMapping(value = "createcategory", method = RequestMethod.POST)
	public ResponseEntity<Object> createcategory(
			@RequestBody String categoryData,HttpServletRequest httpRequest) {

		try {
			log.info("jdbcTemplate   " + jdbcTemplate + "   "
					+ categoryData);
			
			responseInfoObj = categoryHelper.createcategory(categoryData,
					categoryDAO,httpRequest);

			responseObj.setResponseValue(responseInfoObj);

			return new ResponseEntity<Object>(responseObj, new HttpHeaders(),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(),
					new HttpHeaders(), HttpStatus.OK);
		}

	}
	
	
	@RequestMapping(value = "checkposition/{value}/{level}", method = RequestMethod.GET)
	public ResponseEntity<Object> checkposition(
			@PathVariable(value="value" )String value,@PathVariable(value="level" )String level,HttpServletRequest httpRequest) {

		try {
			log.info("checkposition  "
					+ value);
			
			responseInfoObj = categoryHelper.checkposition(value,level,
					categoryDAO,httpRequest);

			responseObj.setResponseValue(responseInfoObj);

			return new ResponseEntity<Object>(responseObj, new HttpHeaders(),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(),
					new HttpHeaders(), HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = "viewposition", method = RequestMethod.POST)
	public ResponseEntity<Object> viewposition(@RequestBody 
			String categorydata,HttpServletRequest httpRequest) {

		try {
			/*System.out.print("checkposition  "
					+ categorydata);
			*/
			responseInfoObj = categoryHelper.viewposition(categorydata,
					categoryDAO,httpRequest);

			responseObj.setResponseValue(responseInfoObj);

			return new ResponseEntity<Object>(responseObj, new HttpHeaders(),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(),
					new HttpHeaders(), HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = "getpresubcategory/{value}", method = RequestMethod.GET)
	public ResponseEntity<Object> getpresubcategory(
			@PathVariable(value="value" )String value,HttpServletRequest httpRequest) {
		log.info(" getpresubcategory "
				+ value);
		
		try {
			
			responseInfoObj = categoryHelper.getpresubcategory(value,
					categoryDAO,httpRequest);

			responseObj.setResponseValue(responseInfoObj);

			return new ResponseEntity<Object>(responseObj, new HttpHeaders(),
					HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>(e.getMessage(),
					new HttpHeaders(), HttpStatus.OK);
		}

	}
	
	@RequestMapping(value = "getcategoryhierarchylist", method = RequestMethod.GET)
	public ResponseEntity<Object> gethierarchylist(HttpServletRequest request) {

		try {
			//System.out.println("applicationurl  );
			
			responseInfoObj = categoryHelper.getcategoryhierarchylist(categoryDAO,request);
					

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
