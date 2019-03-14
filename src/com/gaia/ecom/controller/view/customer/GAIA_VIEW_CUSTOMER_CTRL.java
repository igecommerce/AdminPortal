package com.gaia.ecom.controller.view.customer;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gaia.beans.GAIA_ECOM_BEANS;
import com.gaia.conn.util.GAIA_CONN_UTIL_IMPL;
import com.gaia.ecom.controller.services.configuration.GAIA_SERVICES_CONFIG_CATEGORY_DETAIL;
import com.gaia.ecom.controller.services.configuration.GAIA_SERVICES_CONFIG_PRODUCT_DETAIL;

@Controller
@RequestMapping("/customer")
public class GAIA_VIEW_CUSTOMER_CTRL extends GAIA_ECOM_BEANS{
	private static Logger log = Logger.getLogger(GAIA_CONN_UTIL_IMPL.class);
	@RequestMapping(value="customerlist",method = RequestMethod.GET)
    public ModelAndView getCustomerPage() {
		ModelAndView model = new ModelAndView("customer/customer");
		/*Object[] inputValue = new Object[] {};*/
		List customerlist = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				env.getRequiredProperty("customerlist"), new Object[]{}, GAIA_SERVICES_CONFIG_CATEGORY_DETAIL.class);
		
		model.addObject("customerlist",customerlist);
	
		model.setViewName("customer/customer");
		return model;
		
		
       
    }
	@RequestMapping(value="customerdetails",method = RequestMethod.GET)
    public ModelAndView getCustomerdetailsPage(@RequestParam(value="v")String customerid) {
		ModelAndView model = new ModelAndView("customer/customerdetails");
		/*Object[] inputValue = new Object[] {};*/
		List particularcustomerlist = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				env.getRequiredProperty("particularcustomerlist"), new Object[]{}, GAIA_SERVICES_CONFIG_CATEGORY_DETAIL.class);
				
		List productstocklist = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				env.getRequiredProperty("productstocklist"), new Object[]{}, GAIA_SERVICES_CONFIG_PRODUCT_DETAIL.class);
		List customerlist = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				env.getRequiredProperty("customerliststatusforcustomerdetails"), new Object[]{customerid}, GAIA_SERVICES_CONFIG_CATEGORY_DETAIL.class);
		
		model.addObject("customerlist",customerlist);
	
		
		model.addObject("particularcustomerlist",particularcustomerlist);
		
		
		model.addObject("productstocklist",productstocklist);
		model.addObject("customerid",customerid);
		log.info("productstocklist   "+productstocklist);
		model.setViewName("customer/customerdetails");
		return model;
		
		
       
    }
	
	/*@RequestMapping(value="customerdetails",method = RequestMethod.GET)
    public ModelAndView getCustomerdetailsPage(@RequestParam(value="v")String customerid) {
		ModelAndView model = new ModelAndView("customer/customerdetails");
		Object[] inputValue = new Object[] {};
		List particularcustomerlist = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				env.getRequiredProperty("particularcustomerlist"), new Object[]{customerid}, GAIA_SERVICES_CONFIG_CATEGORY_DETAIL.class);
				
		List productstocklist = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				env.getRequiredProperty("productstocklist"), new Object[]{customerid}, GAIA_SERVICES_CONFIG_PRODUCT_DETAIL.class);
		
		model.addObject("particularcustomerlist",particularcustomerlist);
		
		
		model.addObject("productstocklist",productstocklist);
		model.addObject("customerid",customerid);
		log.info("productstocklist   "+productstocklist);
		model.setViewName("customer/customerdetails");
		return model;
		
		
       
    }
*/
}
