package com.gaia.ecom.controller.view.order;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gaia.beans.GAIA_ECOM_BEANS;
import com.gaia.conn.util.GAIA_CONN_UTIL_IMPL;
import com.gaia.ecom.controller.services.configuration.GAIA_SERVICES_CONFIG_ORDER_DETAILS;
@Controller
@RequestMapping("/order")
public class GAIA_VIEW_ORDER_CTRL extends GAIA_ECOM_BEANS{
	private static Logger log = Logger.getLogger(GAIA_CONN_UTIL_IMPL.class);
	
	@RequestMapping(value="orderlist",method = RequestMethod.GET)
    public String getOrderPage() {
        return "order/order";
    }
	@RequestMapping(value="orderdetails",method = RequestMethod.GET)
    public ModelAndView getOrderdetailPage(@RequestParam(value="v")String orderid) {
		
		ModelAndView model = new ModelAndView("order/orderdetails");
		List particularorderlist = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				env.getRequiredProperty("particularorderlist"), new Object[]{}, GAIA_SERVICES_CONFIG_ORDER_DETAILS.class);
       
		List particularorderproductlist = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				env.getRequiredProperty("particularorderproductlist"), new Object[]{}, GAIA_SERVICES_CONFIG_ORDER_DETAILS.class);
       
		model.addObject("particularorderlist",particularorderlist);
		model.addObject("particularorderproductlist",particularorderproductlist);
		model.addObject("orderid",orderid);
		log.info("particularorderlist   "+particularorderlist);
		model.setViewName("order/orderdetails");
        return model;
        //return "order/orderdetails";
    }
	
	/*@RequestMapping(value="orderdetails",method = RequestMethod.GET)
    public ModelAndView getOrderdetailPage(@RequestParam(value="v")String orderid) {
		
		ModelAndView model = new ModelAndView("order/orderdetails");
		List particularorderlist = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				env.getRequiredProperty("particularorderlist"), new Object[]{orderid}, GAIA_SERVICES_CONFIG_ORDER_DETAILS.class);
       
		List particularorderproductlist = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				env.getRequiredProperty("particularorderproductlist"), new Object[]{orderid}, GAIA_SERVICES_CONFIG_ORDER_DETAILS.class);
       
		model.addObject("particularorderlist",particularorderlist);
		model.addObject("particularorderproductlist",particularorderproductlist);
		model.addObject("orderid",orderid);
		log.info("particularorderlist   "+particularorderlist);
		model.setViewName("order/orderdetails");
        return model;
        //return "order/orderdetails";
    }*/
	

}
