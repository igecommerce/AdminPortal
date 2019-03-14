package com.gaia.ecom.controller.view.configuration;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
import com.gaia.ecom.controller.services.login.GAIA_SERVICES_LOGIN_USERDETAIL;
import com.gaia.util.GAIA_UTILS;



@Controller
@RequestMapping("/configuration")
public class GAIA_VIEW_CONFIG_CTRL extends GAIA_ECOM_BEANS{
	private static Logger log = Logger.getLogger(GAIA_CONN_UTIL_IMPL.class);
	
	
	
	
	
	
	
	@RequestMapping(value="tax",method = RequestMethod.GET)
    public ModelAndView getTaxPage() {
		ModelAndView model = new ModelAndView("configuration/tax");
		List taxlist = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				env.getRequiredProperty("taxlist"), new Object[]{}, GAIA_SERVICES_CONFIG_CATEGORY_DETAIL.class);
		model.addObject("taxlist",taxlist);
		model.setViewName("configuration/tax");
		return model;
    }
	
	
	
	@RequestMapping(value="userrole")
    public ModelAndView getUserrolePage() {
		ModelAndView model = new ModelAndView();
		model.setViewName("configuration/userrole");
		return model;
		
    }
	@RequestMapping(value="adduserrole",method = RequestMethod.GET)
    public ModelAndView getAddUserrolePage(@RequestParam(value="v")String roleid) {
		log.info("roleid   "+roleid);
		ModelAndView model = new ModelAndView("configuration/adduserrole");
		try
		{
		List usermenuList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("gaiamodules"), new Object[]{}, GAIA_SERVICES_LOGIN_USERDETAIL.class);
		List roleList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("particularuserrole"), new Object[]{roleid}, GAIA_SERVICES_LOGIN_USERDETAIL.class);
		GAIA_SERVICES_LOGIN_USERDETAIL roleObject = new GAIA_SERVICES_LOGIN_USERDETAIL();
		if(roleList != null && roleList.size() > 0)
		{
			roleObject = (GAIA_SERVICES_LOGIN_USERDETAIL) roleList.get(0);
			usermenuList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("particulargaiamodules"), new Object[]{roleid}, GAIA_SERVICES_LOGIN_USERDETAIL.class);
		}
		
		model.addObject("usermenuList",usermenuList);
		model.addObject("roleObject",roleObject);
		model.addObject("roleid",roleid);
		model.addObject("action",GAIA_UTILS.getAction(roleid));
		
		model.setViewName("configuration/adduserrole");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return model;
    }
	@RequestMapping(value="gaiausers",method = RequestMethod.GET)
    public String getGAIAUserPage() {
		return "configuration/user";
    }
	@RequestMapping(value="adduser",method = RequestMethod.GET)
    public ModelAndView getAddUserPage(@RequestParam(value="v")String userid) {
		ModelAndView model = new ModelAndView();
		try
		{
			log.info("userid   "+userid);
		List roleList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("alluserroles"), new Object[]{}, GAIA_SERVICES_LOGIN_USERDETAIL.class);
		List userList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("particularuser"), new Object[]{userid}, 
				GAIA_SERVICES_LOGIN_USERDETAIL.class);
		log.info("userList   "+userList);
		GAIA_SERVICES_LOGIN_USERDETAIL userObject = null;
		if(userList != null && userList.size() > 0)
		{
			userObject = (GAIA_SERVICES_LOGIN_USERDETAIL) userList.get(0);
			log.info("userObject.getPassword()   "+userObject.getPassword());
			String password = userObject.getPassword();
			log.info("password   "+password);
			model.addObject("userpassword",password);
		}
		
		/*String password = GAIA_UTILS.decrypt(userObject.getPassword(), env.getRequiredProperty("gaia_key"));*/
		
		model.addObject("roleList",roleList);
		model.addObject("userid",userid);
		model.addObject("userObject",userObject);
		
		model.addObject("action",GAIA_UTILS.getAction(userid));
		
		model.setViewName("configuration/adduser");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return model;
    }
	
	
	@RequestMapping(value="addtax",method = RequestMethod.GET)
    public ModelAndView getAddtaxPage(@RequestParam(value="v")String taxid) {
		ModelAndView model = new ModelAndView("configuration/addtax");
		List taxList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("particulartax"), new Object[]{taxid}, GAIA_SERVICES_CONFIG_CATEGORY_DETAIL.class);
		GAIA_SERVICES_CONFIG_CATEGORY_DETAIL taxObject = new GAIA_SERVICES_CONFIG_CATEGORY_DETAIL();
		if(taxList != null && taxList.size() > 0)
		{
			taxObject = (GAIA_SERVICES_CONFIG_CATEGORY_DETAIL) taxList.get(0);
			
		}
		model.addObject("taxid",taxid);
		model.addObject("taxObject",taxObject);
		model.setViewName( "configuration/addtax");
		return model;
       
    }
	
	@RequestMapping(value="addshipconfig",method = RequestMethod.GET)
    public ModelAndView getAddshipconfigPage(@RequestParam(value="v")String shippingid) {
		ModelAndView model = new ModelAndView("configuration/addshipconfig");
		
		List shippingList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("particularshippinglist"), new Object[]{shippingid}, GAIA_SERVICES_CONFIG_CATEGORY_DETAIL.class);
		GAIA_SERVICES_CONFIG_CATEGORY_DETAIL shipObject = new GAIA_SERVICES_CONFIG_CATEGORY_DETAIL();
		if(shippingList != null && shippingList.size() > 0)
		{
			shipObject = (GAIA_SERVICES_CONFIG_CATEGORY_DETAIL) shippingList.get(0);
			
		}
		model.addObject("shipObject",shipObject);
		model.addObject("shippingid",shippingid);
		model.setViewName("configuration/addshipconfig");
		return model;
		
    }
	
	@RequestMapping(value="shipconfig",method = RequestMethod.GET)
    public ModelAndView getShipconfigPage() {
		ModelAndView model = new ModelAndView("configuration/shipconfig");
		
		List shippingList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				env.getRequiredProperty("shippinglist"), new Object[]{},
				GAIA_SERVICES_CONFIG_CATEGORY_DETAIL.class);
		model.addObject("shippingList",shippingList);
		model.setViewName("configuration/shipconfig");
		return model;
		
    }
	
	
	@RequestMapping(value="mincartvalue",method = RequestMethod.GET)
    public ModelAndView getmincartPage(String mincartid) {
		ModelAndView model = new ModelAndView("template/mincart");

		List mincartlist = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				env.getRequiredProperty("mincartlist"), new Object[]{},
				GAIA_SERVICES_CONFIG_CATEGORY_DETAIL.class);
		
		model.addObject("mincartlist",mincartlist);
		model.addObject("mincartid",mincartid);
		model.addObject("action",GAIA_UTILS.getAction(mincartid));
		//System.out.println("brand   "+brandList);
		
		model.setViewName("configuration/mincartvalue");
		return model;
    }
	
	@RequestMapping(value="addmincart",method = RequestMethod.GET)
    public ModelAndView getAddmincartPage(@RequestParam(value="v")String mincartid) {
		ModelAndView model = new ModelAndView("configuration/addmincart");
		List mincartList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("particularmincart"), new Object[]{mincartid}, GAIA_SERVICES_CONFIG_CATEGORY_DETAIL.class);
		GAIA_SERVICES_CONFIG_CATEGORY_DETAIL mincartObject = new GAIA_SERVICES_CONFIG_CATEGORY_DETAIL();
		if(mincartList != null && mincartList.size() > 0)
		{
			mincartObject = (GAIA_SERVICES_CONFIG_CATEGORY_DETAIL) mincartList.get(0);
			
		}
		model.addObject("mincartList",mincartList);
		model.addObject("mincartObject",mincartObject);
		model.addObject("mincartid",mincartid);
		model.addObject("action",GAIA_UTILS.getAction(mincartid));
		model.setViewName("configuration/addmincartvalue");
		return model;
    }
	
}
