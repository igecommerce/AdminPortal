package com.gaia.ecom.controller.view.category;

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
import com.gaia.util.GAIA_UTILS;

@Controller
@RequestMapping("/category")
public class GAIA_VIEW_CATEGORY_CTRL extends GAIA_ECOM_BEANS{
	private static Logger log = Logger.getLogger(GAIA_VIEW_CATEGORY_CTRL.class);
	
	@RequestMapping(value="category",method = RequestMethod.GET)
    public ModelAndView getCategoryPage() {
		ModelAndView model = new ModelAndView();
		
		
		List categoryList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("allcategoryinlevel"), new Object[]{}, GAIA_SERVICES_CONFIG_CATEGORY_DETAIL.class);
		model.addObject("categoryList",categoryList);
		
		List preCategoryList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("allcategoryinbaselevel"), new Object[]{}, GAIA_SERVICES_CONFIG_CATEGORY_DETAIL.class);
		model.addObject("preCategoryList",preCategoryList);

		List subCategoryList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("allcategoryinprecategorylevel"), new Object[]{}, GAIA_SERVICES_CONFIG_CATEGORY_DETAIL.class);
		model.addObject("subCategoryList",subCategoryList);
		
		return model;
	}
	
	/*@RequestMapping(value="categorylist",method = RequestMethod.GET)
    public ModelAndView getCategoryListPage(@RequestParam(value="actionid")String categorylevel) {
		log.info("categorylevel   "+categorylevel);
		ModelAndView model = new ModelAndView();
		List categoryList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("allcategoryinlevel"), new Object[]{categorylevel}, GAIA_SERVICES_CONFIG_CATEGORY_DETAIL.class);
		model.addObject("categoryList",categoryList);
		//model.setViewName("configuration/categorylist");
		return model;
	}*/
	@RequestMapping(value="categorylist",method = RequestMethod.GET)
    public ModelAndView getCategoryListPage(@RequestParam(value="actionid")String categorylevel) {
		log.info("categorylevel   "+categorylevel);
		ModelAndView model = new ModelAndView();
		if(categorylevel.equals("0")){
		
		List categoryList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("allcategoryinlevel"), new Object[]{}, GAIA_SERVICES_CONFIG_CATEGORY_DETAIL.class);
		model.addObject("categoryList",categoryList);
		}
		else if(categorylevel.equals("1")){
			
		List categoryList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("allcategoryinbaselevel"), new Object[]{}, GAIA_SERVICES_CONFIG_CATEGORY_DETAIL.class);
		model.addObject("categoryList",categoryList);
		}
		else if(categorylevel.equals("2")){
			List categoryList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("allcategoryinprecategorylevel"), new Object[]{}, GAIA_SERVICES_CONFIG_CATEGORY_DETAIL.class);
			model.addObject("categoryList",categoryList);
		}
		model.addObject("categorylevel",categorylevel);
		return model;
	}
	@RequestMapping(value="addcategory",method = RequestMethod.GET)
    public ModelAndView getAddCategoryPage(@RequestParam(value="v")String categoryid) {
		log.info("categoryid   "+categoryid);
		ModelAndView model = new ModelAndView();
		model.addObject("id",categoryid);
		List categoryList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("particularcategory"), new Object[]{categoryid}, GAIA_SERVICES_CONFIG_CATEGORY_DETAIL.class);
		GAIA_SERVICES_CONFIG_CATEGORY_DETAIL categoryObj = new GAIA_SERVICES_CONFIG_CATEGORY_DETAIL();
		
		if(categoryList != null && categoryList.size() >0)
		{
			categoryObj = (GAIA_SERVICES_CONFIG_CATEGORY_DETAIL) categoryList.get(0);
		}
		
		model.addObject("categoryObj",categoryObj);
		model.addObject("id",categoryid);
		model.addObject("action",GAIA_UTILS.getAction(categoryid));
		model.setViewName("configuration/addcategory");
        return model;
    }
	@RequestMapping(value="subcategory",method = RequestMethod.GET)
    public String getSubcategoryPage() {
        return "configuration/subcategory";
    }
}
