package com.gaia.ecom.controller.view.communication;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gaia.beans.GAIA_ECOM_BEANS;
import com.gaia.conn.util.GAIA_CONN_UTIL_IMPL;
import com.gaia.ecom.controller.services.configuration.GAIA_SERVICES_CONFIG_ORDER_DETAILS;
@Controller
@RequestMapping("/communication")
public class GAIA_VIEW_COMMUNICATION_CTRL extends GAIA_ECOM_BEANS{
	@RequestMapping(value="sms",method = RequestMethod.GET)
    public ModelAndView getSmsPage() {
		ModelAndView model = new ModelAndView("template/sms");
		
		List smsList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				env.getRequiredProperty("smslist"), new Object[]{},
				GAIA_SERVICES_CONFIG_ORDER_DETAILS.class);
		//System.out.println();
		
		
		List smstemplateList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				env.getRequiredProperty("smstemplatelist"), new Object[]{},
				GAIA_SERVICES_CONFIG_ORDER_DETAILS.class);
		//System.out.println("smstemplateList "+smstemplateList);
		
		
		GAIA_SERVICES_CONFIG_ORDER_DETAILS smsemailObj = new GAIA_SERVICES_CONFIG_ORDER_DETAILS();
		if(smsList != null && smsList.size() >0)
		{
			smsemailObj = (GAIA_SERVICES_CONFIG_ORDER_DETAILS) smsList.get(0);
		}
		
		model.addObject("smstemplateList",smstemplateList);
		model.addObject("smsemailObj",smsemailObj);
		model.addObject("smsList",smsList);
		model.setViewName("template/sms");
		return model;
    }

	
	@RequestMapping(value="email",method = RequestMethod.GET)
    public ModelAndView getEmailPage() {
		ModelAndView model = new ModelAndView("template/email");
		
		List emailList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				env.getRequiredProperty("emaillist"), new Object[]{},
				GAIA_SERVICES_CONFIG_ORDER_DETAILS.class);
		GAIA_SERVICES_CONFIG_ORDER_DETAILS smsObj = new GAIA_SERVICES_CONFIG_ORDER_DETAILS();
		
		List emailtemplateList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				env.getRequiredProperty("emailtemplatelist"), new Object[]{},
				GAIA_SERVICES_CONFIG_ORDER_DETAILS.class);
		//System.out.println("emailtemplateList "+emailtemplateList);
		
		
		if(emailList != null && emailList.size() >0)
		{
			smsObj = (GAIA_SERVICES_CONFIG_ORDER_DETAILS) emailList.get(0);
		}
		
		model.addObject("smsObj",smsObj);
		model.addObject("emailList",emailList);
		model.addObject("emailtemplateList",emailtemplateList);
		model.setViewName("template/email");
		return model;
    }

}
