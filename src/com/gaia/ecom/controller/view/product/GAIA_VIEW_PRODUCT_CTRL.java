package com.gaia.ecom.controller.view.product;

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
import com.gaia.ecom.controller.services.products.GAIA_SERVICES_PRODUCT_DETAIL;
import com.gaia.util.GAIA_UTILS;

@Controller
@RequestMapping("/products")
public class GAIA_VIEW_PRODUCT_CTRL extends GAIA_ECOM_BEANS{
	private static Logger log = Logger.getLogger(GAIA_CONN_UTIL_IMPL.class);
	@RequestMapping(value="productlist",method = RequestMethod.GET)
    public ModelAndView getProductList() {
		ModelAndView model = new ModelAndView();
		log.info("query"+env.getRequiredProperty("getprodutlist"));
		List productlist = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("allactiveandinactiveproductlist"), new Object[]{}, GAIA_SERVICES_CONFIG_PRODUCT_DETAIL.class);
		model.addObject("productlist",productlist);
		model.setViewName("configuration/productlist");
		return model;
    }
	
	@RequestMapping(value="addproduct",method = RequestMethod.GET)
    public ModelAndView addProduct(@RequestParam(value="actionid")String productid) {
		log.info("productid   "+productid);
		ModelAndView model = new ModelAndView();
		GAIA_SERVICES_CONFIG_PRODUCT_DETAIL productObject = new GAIA_SERVICES_CONFIG_PRODUCT_DETAIL();
		List producttytypeList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("getproducttypes"), new Object[]{}, GAIA_SERVICES_CONFIG_PRODUCT_DETAIL.class);
		List websiteList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("websitelists"), new Object[]{}, GAIA_SERVICES_CONFIG_PRODUCT_DETAIL.class);
		List brandList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("brandlist"), new Object[]{}, GAIA_SERVICES_CONFIG_CATEGORY_DETAIL.class);
		List uomList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("uomlist"), new Object[]{}, GAIA_SERVICES_CONFIG_CATEGORY_DETAIL.class);
		
		List hierarchyList = new ArrayList();
		List mainCategoryList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("maincategoryquery"), new Object[]{}, GAIA_SERVICES_CONFIG_PRODUCT_DETAIL.class);
		Map<Integer, Object> preCategoryMap = new HashMap<Integer, Object>();
		Iterator mainCategoryListITR = mainCategoryList.iterator();
		while(mainCategoryListITR.hasNext())
		{
			GAIA_SERVICES_CONFIG_PRODUCT_DETAIL mainCategoryMap = (GAIA_SERVICES_CONFIG_PRODUCT_DETAIL) mainCategoryListITR.next();
			String categoryId = mainCategoryMap.getCategoryid();
			//System.out.println("categoryId   "+categoryId);
			List preCategoryList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("precategoryquery"), new Object[]{categoryId}, GAIA_SERVICES_CONFIG_PRODUCT_DETAIL.class);
			//System.out.println("preCategoryList   "+preCategoryList);
			hierarchyList.add(mainCategoryMap);
			
			Iterator preCategoryListITR = preCategoryList.iterator();
			while(preCategoryListITR.hasNext())
			{
				GAIA_SERVICES_CONFIG_PRODUCT_DETAIL subCategoryMap = (GAIA_SERVICES_CONFIG_PRODUCT_DETAIL) preCategoryListITR.next();
				categoryId = subCategoryMap.getCategoryid();
				List subCategoryList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("subcategoryquery"), new Object[]{categoryId}, GAIA_SERVICES_CONFIG_PRODUCT_DETAIL.class);

				hierarchyList.add(subCategoryMap);
				
				hierarchyList.addAll(subCategoryList);
			}
		}
		
		List product = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("particularproduct"), new Object[]{productid}, GAIA_SERVICES_CONFIG_PRODUCT_DETAIL.class);
		List productCategoryList = new ArrayList();
		if(product != null && product.size() > 0)
		{
			productObject = (GAIA_SERVICES_CONFIG_PRODUCT_DETAIL) product.get(0);
			
			productCategoryList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("particularproductcategorylist"), new Object[]{productid}, GAIA_SERVICES_CONFIG_PRODUCT_DETAIL.class);
		}
	
		//System.out.println("hierarchyList   "+hierarchyList.size());
		model.addObject("productid",productid);
		model.addObject("productObject",productObject);
		model.addObject("productCategoryList",productCategoryList);
		model.addObject("uomList",uomList);
		model.addObject("brandList",brandList);
		model.addObject("producttytypeList",producttytypeList);
		model.addObject("websiteList",websiteList);
		model.addObject("hierarchyList",hierarchyList);
		model.setViewName("configuration/product");
		return model;
    }
	
	@RequestMapping(value="brand",method = RequestMethod.GET)
    public ModelAndView getBrandPage(String brandid) {
		ModelAndView model = new ModelAndView("configuration/brand");
		
		List brandList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				env.getRequiredProperty("allbrandactiveandinactive"), new Object[]{},
				GAIA_SERVICES_CONFIG_CATEGORY_DETAIL.class);
		
		model.addObject("brandList",brandList);
		model.addObject("brandid",brandid);
		model.addObject("action",GAIA_UTILS.getAction(brandid));
		log.info("action   "+brandList);
		
		model.setViewName("configuration/brand");
		return model;
    }
	
	@RequestMapping(value="addbrand",method = RequestMethod.GET)
    public ModelAndView getAddbrandPage(@RequestParam(value="v")String brandid) {
		ModelAndView model = new ModelAndView("configuration/addbrand");
		List brandList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("particularbrand"), new Object[]{brandid}, GAIA_SERVICES_CONFIG_CATEGORY_DETAIL.class);
		GAIA_SERVICES_CONFIG_CATEGORY_DETAIL brandObject = new GAIA_SERVICES_CONFIG_CATEGORY_DETAIL();
		if(brandList != null && brandList.size() > 0)
		{
			brandObject = (GAIA_SERVICES_CONFIG_CATEGORY_DETAIL) brandList.get(0);
			
		}
		model.addObject("brandList",brandList);
		model.addObject("brandObject",brandObject);
		model.addObject("brandid",brandid);
		model.addObject("action",GAIA_UTILS.getAction(brandid));
		model.setViewName("configuration/addbrand");
		return model;
    }

	@RequestMapping(value="adduom",method = RequestMethod.GET)
    public ModelAndView getAdduomPage(@RequestParam(value="v")String uomid) {
		ModelAndView model = new ModelAndView("configuration/adduom");
		List uomList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("particularuom"), new Object[]{uomid}, GAIA_SERVICES_CONFIG_CATEGORY_DETAIL.class);
		GAIA_SERVICES_CONFIG_CATEGORY_DETAIL uomObject = new GAIA_SERVICES_CONFIG_CATEGORY_DETAIL();
		if(uomList != null && uomList.size() > 0)
		{
			uomObject = (GAIA_SERVICES_CONFIG_CATEGORY_DETAIL) uomList.get(0);
			
		}
		model.addObject("uomList",uomList);
		model.addObject("uomObject",uomObject);
		model.addObject("uomid",uomid);
		model.addObject("action",GAIA_UTILS.getAction(uomid));
		model.setViewName("configuration/adduom");
		return model;
    }
	
	@RequestMapping(value="importproduct",method = RequestMethod.GET)
    public String getImportproductPage() {
        return "configuration/importproduct";
    }
	@RequestMapping(value="updateproduct",method = RequestMethod.GET)
    public String getUpdateproductPage() {
        return "configuration/updateproduct";
    }
	
	@RequestMapping(value="export",method = RequestMethod.GET)
    public ModelAndView getExportPage() {
        ModelAndView model = new ModelAndView("configuration/export");
		
        //int productId =  0;
        List productList = new ArrayList();
		List productListexport = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				env.getRequiredProperty("productforexportlist"), new Object[]{},
				GAIA_SERVICES_CONFIG_PRODUCT_DETAIL.class);
		Iterator mainproductListITR = productListexport.iterator();
		while(mainproductListITR.hasNext()){
			
			GAIA_SERVICES_CONFIG_PRODUCT_DETAIL mainproductMap = (GAIA_SERVICES_CONFIG_PRODUCT_DETAIL) mainproductListITR.next();
			int productId = mainproductMap.getIntProductid();
			
			 log.info("mainproductMap  "+productId);
			 
			 
			 List categorylistexport = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
						env.getRequiredProperty("categorylistforexport"), new Object[]{productId},
						GAIA_SERVICES_CONFIG_PRODUCT_DETAIL.class); 
			 if(categorylistexport !=null && categorylistexport.size()>0 ){
			 
			 GAIA_SERVICES_CONFIG_PRODUCT_DETAIL categoryMap=(GAIA_SERVICES_CONFIG_PRODUCT_DETAIL) categorylistexport.get(0);
			 mainproductMap.setCategoryname(categoryMap.getCategoryname());
			 
			 }
			 List precategorylistexport = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
						env.getRequiredProperty("precategorylistforexport"), new Object[]{productId},
						GAIA_SERVICES_CONFIG_PRODUCT_DETAIL.class); 
			 if(precategorylistexport !=null && precategorylistexport.size()>0 ){
			 GAIA_SERVICES_CONFIG_PRODUCT_DETAIL precategoryMap=(GAIA_SERVICES_CONFIG_PRODUCT_DETAIL) precategorylistexport.get(0);
			 mainproductMap.setPrecategoryname(precategoryMap.getPrecategoryname());
			 log.info("precategorylistexport  "+precategorylistexport.size());
			 
			 }
			 
			 List subcategorylistexport = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
						env.getRequiredProperty("subcategorylistforexport"), new Object[]{productId},
						GAIA_SERVICES_CONFIG_PRODUCT_DETAIL.class); 
			 if(subcategorylistexport !=null && subcategorylistexport.size()>0 ){
			 GAIA_SERVICES_CONFIG_PRODUCT_DETAIL subcategoryMap=(GAIA_SERVICES_CONFIG_PRODUCT_DETAIL) subcategorylistexport.get(0);
			 mainproductMap.setSubcategoryname(subcategoryMap.getSubcategoryname());
			 
			 }
			
			 productList.add(mainproductMap);
			
			
		}
		
		
		
		model.addObject("productList",productList);
		
		
		model.setViewName("configuration/export");
		return model;
		
		
       
    }
	

	@RequestMapping(value="uom",method = RequestMethod.GET)
    public ModelAndView getUomPage(String uomid) {
		ModelAndView model = new ModelAndView("configuration/uom");

		List uomlist = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				env.getRequiredProperty("alluomactiveandinactive"), new Object[]{},
				GAIA_SERVICES_CONFIG_CATEGORY_DETAIL.class);
		
		model.addObject("uomlist",uomlist);
		model.addObject("uomid",uomid);
		model.addObject("action",GAIA_UTILS.getAction(uomid));
		//System.out.println("brand   "+brandList);
		
		model.setViewName("configuration/uom");
		return model;
    }
	

}
