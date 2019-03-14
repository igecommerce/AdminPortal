package com.gaia.ecom.controller.view.settings;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gaia.beans.GAIA_ECOM_BEANS;
import com.gaia.conn.util.GAIA_CONN_UTIL_IMPL;
import com.gaia.ecom.controller.services.configuration.GAIA_SERVICES_CONFIG_CATEGORY_DETAIL;
import com.gaia.ecom.controller.services.customer.GAIA_SERVICES_CUSTOMER_DETAILS;
import com.gaia.ecom.controller.services.products.GAIA_SERVICES_PRODUCT_DETAIL;

@Controller
@RequestMapping("/settings")
public class GAIA_VIEW_CONTROLLER extends GAIA_ECOM_BEANS {

	@RequestMapping(value="product",method = RequestMethod.GET)
    public String getHeaderPage() {
        return "setting/productlist";
    }
	@RequestMapping(value="salesbyproduct",method = RequestMethod.GET)
    public ModelAndView getNewproductPage() {
		ModelAndView model = new ModelAndView();
		List productList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				env.getRequiredProperty("salesbyproductreport"), new Object[]{},
				GAIA_SERVICES_PRODUCT_DETAIL.class);
		model.addObject("productList",productList);
		model.setViewName("setting/salesbyproduct");
		return model;
    }
	@RequestMapping(value="salesbycustomer",method = RequestMethod.GET)
    public ModelAndView getCustomerPage() {
		ModelAndView model = new ModelAndView();
		List customerList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				env.getRequiredProperty("salesbycustomerreport"), new Object[]{},
				GAIA_SERVICES_CUSTOMER_DETAILS.class);
		model.addObject("customerList",customerList);
		model.setViewName("setting/salesbycustomer");
		return model;
    }
	
	@RequestMapping(value="reports",method = RequestMethod.GET)
    public String getReportsPage() {
        return "setting/reports";
    }
}
