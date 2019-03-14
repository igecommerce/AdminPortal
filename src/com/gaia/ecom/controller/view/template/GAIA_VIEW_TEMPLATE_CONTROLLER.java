package com.gaia.ecom.controller.view.template;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.jdbc.core.RowMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gaia.beans.GAIA_ECOM_BEANS;
import com.gaia.conn.util.GAIA_CONN_UTIL_IMPL;
import com.gaia.ecom.controller.services.configuration.GAIA_SERVICES_CONFIG_CATEGORY_DETAIL;
import com.gaia.ecom.controller.services.configuration.GAIA_SERVICES_CONFIG_ORDER_DETAILS;
import com.gaia.ecom.controller.services.configuration.GAIA_SERVICES_CONFIG_PRODUCT_DETAIL;
import com.gaia.ecom.controller.services.login.GAIA_SERVICES_LOGIN_USERDETAIL;

@Controller
@RequestMapping("/template")
public class GAIA_VIEW_TEMPLATE_CONTROLLER extends GAIA_ECOM_BEANS{
	private static Logger log = Logger.getLogger(GAIA_CONN_UTIL_IMPL.class);
	/*@RequestMapping(value="/",method = RequestMethod.GET)
    public String getLoginPage() {
        return "template/login";
    }
	*/
@RequestMapping(value="header",method = RequestMethod.GET)
    public String getHeaderPage() {
        return "template/header";
    }
	
	@RequestMapping(value="footer",method = RequestMethod.GET)
    public String getFooterPage() {
        return "template/footer";
    }
	
	@RequestMapping(value="menu",method = RequestMethod.GET)
    public ModelAndView getMenuPage(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		try
		{
		HttpSession session = request.getSession();
		log.info("userid   "+session.getAttribute("usertypeid"));
		/*List menuList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("menuquery"), new Object[]{session.getAttribute("usertypeid")}, 
				GAIA_SERVICES_LOGIN_USERDETAIL.class);
		model.addObject("menuList",menuList);
		System.out.println("menuList   "+menuList);*/
        model.setViewName("template/menu");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
        return model;
    }
	
	@RequestMapping(value="dashboard",method = RequestMethod.GET)
    public ModelAndView getDashboardPage(HttpServletRequest httpRequest) {
		ModelAndView model = new ModelAndView("template/dashboard");
	  
		List excelList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				env.getRequiredProperty("recentexcellastfive"), new Object[]{},
				GAIA_SERVICES_CONFIG_ORDER_DETAILS.class);
		List excellistwithfullurl = new ArrayList();
		String path=httpRequest.getRealPath("/");
		Iterator excelListITR = excelList.iterator();
		while(excelListITR.hasNext())
		{
			GAIA_SERVICES_CONFIG_ORDER_DETAILS excelMap = (GAIA_SERVICES_CONFIG_ORDER_DETAILS) excelListITR.next();
			String fileseq =excelMap.getQty();
			
			
			String excelpath=path.concat("gaiafiles").concat(File.separator).concat("documents").concat(File.separator).concat("productimport").concat(File.separator).concat("import").concat(File.separator).concat(fileseq).concat(".xlsx");
			//System.out.print("excelpath"+excelpath);
			excelMap.setQty(excelpath);
			
			excellistwithfullurl.add(excelMap);
		}
		
		
		List totaluserList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				env.getRequiredProperty("totaluser"), new Object[]{},
				GAIA_SERVICES_CONFIG_ORDER_DETAILS.class);
		List todayorderList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				env.getRequiredProperty("todayorderlist"), new Object[]{},
				GAIA_SERVICES_CONFIG_ORDER_DETAILS.class);
		List yesterdayorderList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				env.getRequiredProperty("yesterdayorderlist"), new Object[]{},
				GAIA_SERVICES_CONFIG_ORDER_DETAILS.class);
		List productList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				env.getRequiredProperty("productlistfordashboard"), new Object[]{},
				GAIA_SERVICES_CONFIG_ORDER_DETAILS.class);
		List customerList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				env.getRequiredProperty("customerlistfordashboard"), new Object[]{},
				GAIA_SERVICES_CONFIG_ORDER_DETAILS.class);
		List recentorderList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				env.getRequiredProperty("recentorders"), new Object[]{},
				GAIA_SERVICES_CONFIG_ORDER_DETAILS.class);
		List recentcustomerList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				env.getRequiredProperty("recentcustomers"), new Object[]{},
				GAIA_SERVICES_CONFIG_CATEGORY_DETAIL.class);
		List recentproductList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				env.getRequiredProperty("recentproducts"), new Object[]{},
				GAIA_SERVICES_CONFIG_PRODUCT_DETAIL.class);
		List recentuserList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				env.getRequiredProperty("recentusers"), new Object[]{},
				GAIA_SERVICES_LOGIN_USERDETAIL.class);
		List yesterdayordersList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				env.getRequiredProperty("yesterdayorders"), new Object[]{},
				GAIA_SERVICES_CONFIG_ORDER_DETAILS.class);
		model.addObject("excelList",excellistwithfullurl);
		model.addObject("yesterdayordersList",yesterdayordersList);
		model.addObject("recentuserList",recentuserList);
		model.addObject("recentproductList",recentproductList);
		model.addObject("recentcustomerList",recentcustomerList);
		model.addObject("recentorderList",recentorderList);
		model.addObject("customerList",customerList);
		model.addObject("productList",productList);
		model.addObject("yesterdayorderList",yesterdayorderList);
		model.addObject("totaluserList",totaluserList);
		model.addObject("todayorderList",todayorderList);
		model.setViewName("dashboard/dashboard");
		return model;
       
    }
	
	
}
