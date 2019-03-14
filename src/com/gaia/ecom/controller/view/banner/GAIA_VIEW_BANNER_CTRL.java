package com.gaia.ecom.controller.view.banner;

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

@Controller
@RequestMapping("/banner")
public class GAIA_VIEW_BANNER_CTRL extends GAIA_ECOM_BEANS{
	private static Logger log = Logger.getLogger(GAIA_CONN_UTIL_IMPL.class);
	@RequestMapping(value="addbanner",method = RequestMethod.GET)
    public ModelAndView getAddbulkuploadPage() {
		ModelAndView model = new ModelAndView();
		
		model.setViewName("configuration/addbulkupload");
        return model;
        
    }
	@RequestMapping(value="bannerlist",method = RequestMethod.GET)
    public ModelAndView getBulkuploadPage(String bannerid) {
		ModelAndView model = new ModelAndView("configuration/bulkupload");
		List bannerList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				env.getRequiredProperty("bannerqueryforlist"), new Object[]{},
				GAIA_SERVICES_CONFIG_CATEGORY_DETAIL.class);
		model.addObject("bannerList",bannerList);
		
		model.setViewName( "configuration/bulkupload");
		return model;
    }
	
	@RequestMapping(value="editbanner",method = RequestMethod.GET)
    public ModelAndView getAddtaxPage(@RequestParam(value="v")String bannerid) {
		ModelAndView model = new ModelAndView("configuration/banneredit");
		List bannerList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("particularbannerbulkupload"), new Object[]{bannerid}, GAIA_SERVICES_CONFIG_CATEGORY_DETAIL.class);
		GAIA_SERVICES_CONFIG_CATEGORY_DETAIL bannerObject = new GAIA_SERVICES_CONFIG_CATEGORY_DETAIL();
		if(bannerList != null && bannerList.size() > 0)
		{
			bannerObject = (GAIA_SERVICES_CONFIG_CATEGORY_DETAIL) bannerList.get(0);
			
		}
		model.addObject("bannerid",bannerid);
		model.addObject("bannerObject",bannerObject);
		model.setViewName( "configuration/banneredit");
		return model;
       
    }

}
