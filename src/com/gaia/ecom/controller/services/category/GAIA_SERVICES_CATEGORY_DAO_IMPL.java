package com.gaia.ecom.controller.services.category;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.gaia.beans.GAIA_ECOM_BEANS;
import com.gaia.conn.util.GAIA_CONN_UTIL_IMPL;
import com.gaia.ecom.controller.response.GAIA_ECOM_RESPONSEINFO;
import com.gaia.ecom.controller.services.configuration.GAIA_SERVICES_CONFIG_CATEGORY_DETAIL;
import com.gaia.ecom.controller.services.login.GAIA_SERVICES_LOGIN_USERDETAIL;
import com.gaia.util.GAIA_UTILS;

@Service
public class GAIA_SERVICES_CATEGORY_DAO_IMPL extends GAIA_ECOM_BEANS implements  GAIA_SERVICES_ICATEGORY_DAO{
	
	private static Logger log = Logger.getLogger(GAIA_SERVICES_CATEGORY_DAO_IMPL.class);
	
	@Override
	public GAIA_ECOM_RESPONSEINFO createcategory(String categoryData,HttpServletRequest httpRequest)
			 {
try
{
		GAIA_SERVICES_CATEGORY_DETAIL request = (GAIA_SERVICES_CATEGORY_DETAIL) GAIA_UTILS.convertJSONtooOBJECT(categoryData, GAIA_SERVICES_CATEGORY_DETAIL.class);
		log.info("Input Details are    "+request.getCategoryname()+"   "+request.getCategorylevel()+"   "+request.getCategoryurl()+"   "+request.getDisplayorder()
				+"   "+request.getPrecategoryid()+"   "+request.getStatus());
		
		String imagePath = "",thumbnailPath = "";
		
		HttpSession session = httpRequest.getSession();
		
		LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
		inParamMap.put("P_CATEGORY_ID", request.getCategoryid());
		inParamMap.put("P_CATEGORY_DESC", request.getCategoryname());
		inParamMap.put("P_CATEGORY_URI", request.getCategoryurl());
		inParamMap.put("P_IMAGE_PATH", imagePath);
		inParamMap.put("P_THUMBNAIL_PATH", thumbnailPath);
		
		inParamMap.put("P_CATEGORY_LEVEL", request.getCategorylevel());
		inParamMap.put("P_CATEGORY_PARENT_ID", request.getPrecategoryid());
		inParamMap.put("P_CATEGORY_POSITION", request.getDisplayorder());
		inParamMap.put("P_CATEGORY_STATUS", request.getStatus());
		inParamMap.put("P_ACTION", request.getAction());
		
		
		Map<String, Object> resultMap = GAIA_CONN_UTIL_IMPL.executeProcedure(jdbcTemplate,"P_GAIA_CATEGORY_OPRNS", inParamMap);
		log.info(resultMap);
		
		if(resultMap.get("o_out_flag") != null && resultMap.get("o_out_flag").equals("S") && session.getAttribute("categoryimage") != null)
		{
			String oldimagefilepath = (String) session.getAttribute("categoryimage"); 
			String oldthumbfilepath = (String) session.getAttribute("categorythumbnail");
			log.info("Uploaded Filename   "+oldimagefilepath);
			log.info("Uploaded Filename   "+oldthumbfilepath);
			int categoryid = (int) resultMap.get("o_out_generated_id");
			
			if(categoryid > 0)
			{
				/*Image File Move Start*/
				String newpath = GAIA_UTILS.getserverfilepath(httpRequest);
				String modulename = GAIA_UTILS.getmodulename("category", 1);
				//log.info("request.getCategorylevel()   "+request.getCategorylevel());
				newpath = newpath.concat(File.separator).concat(modulename);
				File uploadPath = new File(newpath);
				if(!uploadPath.exists())
				{
					uploadPath.mkdirs();
				}
				newpath = newpath.concat(File.separator).concat(categoryid+".jpg");
				
				if(oldimagefilepath!=null){
				/*if(request.getAction()  != null  && request.getAction().equals("UPD") ){*/
					File oldfile = new File(oldimagefilepath);
                    if(oldfile.exists()){
	        	  		File file = new File(newpath);
						if(file.exists())
						{
							Path fileToDeletePath = Paths.get(newpath);
							Files.delete(fileToDeletePath);
							new File(oldimagefilepath).renameTo(new File(newpath));
							log.info("thumbFilecopy deleted");
						}
                    }
				/*}*/
				
				log.info("newpath   "+newpath);
				
				
				File f = new File(oldimagefilepath);
				if(f.exists() && !f.isDirectory()) { 
					new File(oldimagefilepath).renameTo(new File(newpath));
					
				}
				}
				imagePath = GAIA_UTILS.filepath(newpath);
				/*End*/
				
				/*Thumbnail File Move Start*/
				newpath = GAIA_UTILS.getserverfilepath(httpRequest);
				modulename = GAIA_UTILS.getmodulename("category", 2);
				
				newpath = newpath.concat(File.separator).concat(modulename);
				
				uploadPath = new File(newpath);
				if(!uploadPath.exists())
				{
					uploadPath.mkdirs();
				}
				newpath = newpath.concat(File.separator).concat(categoryid+".jpg");
				
				if(oldthumbfilepath!=null){
				File oldfile = new File(oldthumbfilepath);
                     if(oldfile.exists()){
				/*if(request.getAction()  != null  && request.getAction().equals("UPD") ){*/
        	  		File file = new File(newpath);
					if(file.exists())
					{
						Path fileToDeletePath = Paths.get(newpath);
						Files.delete(fileToDeletePath);
						new File(oldthumbfilepath).renameTo(new File(newpath));
						
					}
				/*}*/
                     }
				
				File ft = new File(oldthumbfilepath);
				if(ft.exists() && !ft.isDirectory()) { 
					new File(oldthumbfilepath).renameTo(new File(newpath));
					
				}
				}
				/*End*/
				thumbnailPath = GAIA_UTILS.filepath(newpath);
				
				 
				inParamMap = new LinkedHashMap<String, Object>();
				inParamMap.put("P_CATEGORY_ID", categoryid);
				inParamMap.put("P_CATEGORY_DESC", request.getCategoryname());
				inParamMap.put("P_CATEGORY_URI", request.getCategoryurl());
				inParamMap.put("P_IMAGE_PATH", imagePath);
				inParamMap.put("P_THUMBNAIL_PATH", thumbnailPath);
				
				inParamMap.put("P_CATEGORY_LEVEL", request.getCategorylevel());
				inParamMap.put("P_CATEGORY_PARENT_ID", request.getPrecategoryid());
				inParamMap.put("P_CATEGORY_POSITION", request.getDisplayorder());
				inParamMap.put("P_CATEGORY_STATUS", request.getStatus());
				inParamMap.put("P_ACTION", "UPD_IMGS_PATH");
				
				
				Map<String, Object> uploadResultMap = GAIA_CONN_UTIL_IMPL.executeProcedure(jdbcTemplate,"P_GAIA_CATEGORY_OPRNS", inParamMap);
				log.info("uploadResultMap   "+uploadResultMap);
				
				session.setAttribute("categoryimage","");
				session.setAttribute("categorythumbnail","");
			}
			
			
		}
		responseInfoObj.setResponseType("S");
		responseInfoObj.setGaiaresponse(resultMap);
}
catch(Exception e)
{
	e.printStackTrace();
	responseInfoObj.setResponseType("F");
	responseInfoObj.setGaiaresponse("Exception Occured.Kindly contact system support.");
}
		return responseInfoObj;
	}
	@Override
	public GAIA_ECOM_RESPONSEINFO checkposition(String value,String level,HttpServletRequest httpRequest)
			 {
		log.info("value  "+value);
		log.info("level  "+level);
		Object[] inputValue = new Object[] { level,
				value };
		log.info("inputValue   " + inputValue);
		List checkpositionList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("checkpositionquery"), inputValue, 
				GAIA_SERVICES_CATEGORY_DETAIL.class);
		responseInfoObj.setResponseType("S");
		responseInfoObj.setGaiaresponse(checkpositionList);
		
		log.info("checkpositionList  "+checkpositionList.size());
				return responseInfoObj;
		
			 }
	
	@Override
	public GAIA_ECOM_RESPONSEINFO viewposition(String categorydata,
			HttpServletRequest httpRequest) {
		log.info("applicationContextURL  "+categorydata);
		// TODO Auto-generated method stub
		GAIA_SERVICES_CONFIG_CATEGORY_DETAIL request;
		try {
			request = (GAIA_SERVICES_CONFIG_CATEGORY_DETAIL) GAIA_UTILS
					.convertJSONtooOBJECT(categorydata,
							GAIA_SERVICES_CONFIG_CATEGORY_DETAIL.class);
			Object[] inputValue = new Object[] { request.getCategorylevel(),
					request.getPrecategoryid() };
			if(request.getCategorylevel().equals("0")){
			
				List categoryList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("categoryviewposition"), new Object[]{}, GAIA_SERVICES_CONFIG_CATEGORY_DETAIL.class);
				responseInfoObj.setResponseType("S");
				responseInfoObj.setGaiaresponse(categoryList);
				System.out.print("categry "+categoryList);
				
			}
			if(request.getCategorylevel().equals("1")){
				List precategoryList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
						env.getRequiredProperty("precategoryviewposition"), inputValue,
						GAIA_SERVICES_CONFIG_CATEGORY_DETAIL.class);
				responseInfoObj.setResponseType("S");
				responseInfoObj.setGaiaresponse(precategoryList);
				System.out.print("precategoryList "+precategoryList);
				
			}
			if(request.getCategorylevel().equals("2")){
				List subcategoryList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
						env.getRequiredProperty("subcategoryviewposition"), inputValue,
						GAIA_SERVICES_CONFIG_CATEGORY_DETAIL.class);
				responseInfoObj.setResponseType("S");
				responseInfoObj.setGaiaresponse(subcategoryList);
				System.out.print("subcategoryList "+subcategoryList);
				
			}
			
		
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return responseInfoObj;
	}
	
	@Override
	public GAIA_ECOM_RESPONSEINFO getpresubcategory(String value,
			HttpServletRequest httpRequest) {
		log.info("applicationContextURL  "+value);
		// TODO Auto-generated method stub
		if(value.equals("2")){
			List presubList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("presubcategoryforleveltwo"), new Object[]{Integer.parseInt(value) - 1}, 
					GAIA_SERVICES_CATEGORY_DETAIL.class);
			responseInfoObj.setResponseType("S");
			responseInfoObj.setGaiaresponse(presubList);
		}
		else{
			List presubList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("presubcategory"), new Object[]{Integer.parseInt(value) - 1}, 
					GAIA_SERVICES_CATEGORY_DETAIL.class);
			responseInfoObj.setResponseType("S");
			responseInfoObj.setGaiaresponse(presubList);
		}
		
		return responseInfoObj;
	}
	
	public GAIA_ECOM_RESPONSEINFO getcategoryhierarchylist(HttpServletRequest request) {
		String applicationContextURL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/";
		log.info("applicationContextURL  "+applicationContextURL);
		Object[] inputValue = new Object[] {};
		List hierarchyList = new ArrayList();
		List mainCategoryList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("maincategoryqueryforservice"), new Object[]{}, GAIA_SERVICES_CATEGORY_DETAIL.class);
		Map<Integer, Object> preCategoryMap = new HashMap<Integer, Object>();
		Map<String, Object> displayMap = new HashMap<String, Object>();
		displayMap.put("categoryList", mainCategoryList);
		
		Iterator mainCategoryListITR = mainCategoryList.iterator();
		
		/*while(mainCategoryListITR.hasNext())
		{
			GAIA_SERVICES_CONFIG_CATEGORY_DETAIL_HIERARCHY mainCategoryMap = (GAIA_SERVICES_CONFIG_CATEGORY_DETAIL_HIERARCHY) mainCategoryListITR.next();
			String categoryId = mainCategoryMap.getCategoryid();
			log.info("categoryId   "+categoryId);
			List preCategoryList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("precategoryqueryforservice"), new Object[]{categoryId}, GAIA_SERVICES_CONFIG_CATEGORY_DETAIL_HIERARCHY.class);
			displayMap.put(categoryId, preCategoryList);
			
			Iterator preCategoryListITR = preCategoryList.iterator();
			while(preCategoryListITR.hasNext())
			{
				GAIA_SERVICES_CONFIG_CATEGORY_DETAIL_HIERARCHY subCategoryMap = (GAIA_SERVICES_CONFIG_CATEGORY_DETAIL_HIERARCHY) preCategoryListITR.next();
				categoryId = subCategoryMap.getCategoryid();
				List subCategoryList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("subcategoryqueryforservice"), new Object[]{categoryId}, GAIA_SERVICES_CONFIG_CATEGORY_DETAIL_HIERARCHY.class);
				displayMap.put(categoryId, subCategoryList);
			}
		}*/
		
		while(mainCategoryListITR.hasNext())
		{
			GAIA_SERVICES_CATEGORY_DETAIL_HIERARCHY mainCategoryMap = (GAIA_SERVICES_CATEGORY_DETAIL_HIERARCHY) mainCategoryListITR.next();
			String categoryId = mainCategoryMap.getCategoryid();
			log.info("categoryId   "+categoryId);
			List preCategoryList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("precategoryqueryforservice"), new Object[]{categoryId}, GAIA_SERVICES_CATEGORY_DETAIL_HIERARCHY.class);
			log.info("preCategoryList   "+preCategoryList);
			hierarchyList.add(mainCategoryMap);
			
			Iterator preCategoryListITR = preCategoryList.iterator();
			while(preCategoryListITR.hasNext())
			{
				GAIA_SERVICES_CATEGORY_DETAIL_HIERARCHY subCategoryMap = (GAIA_SERVICES_CATEGORY_DETAIL_HIERARCHY) preCategoryListITR.next();
				categoryId = subCategoryMap.getCategoryid();
				List subCategoryList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("subcategoryqueryforservice"), new Object[]{categoryId}, GAIA_SERVICES_CATEGORY_DETAIL_HIERARCHY.class);

				hierarchyList.add(subCategoryMap);
				
				hierarchyList.addAll(subCategoryList);
			}
		}
		
		
		
		Iterator hierarchyListITR = hierarchyList.iterator();
		List hierarchyListwithurl = new ArrayList();
		while(hierarchyListITR.hasNext())
		{
			GAIA_SERVICES_CATEGORY_DETAIL_HIERARCHY hierarchyMap = (GAIA_SERVICES_CATEGORY_DETAIL_HIERARCHY) hierarchyListITR.next();
			String hierarchycategoryId = hierarchyMap.getCategoryid();
			String imageurl = applicationContextURL+"gaiafiles?form=category&type=1&id="+hierarchycategoryId;
			String thumbnailurl = applicationContextURL+"gaiafiles?form=category&type=2&id="+hierarchycategoryId;
			hierarchyMap.setCategoryimageurl(imageurl);
			hierarchyMap.setCategorythumbnailurl(thumbnailurl);
			log.info("imageurl   "+imageurl);
			hierarchyListwithurl.add(hierarchyMap);
		}
		
		responseInfoObj.setGaiaresponse(hierarchyListwithurl);
		log.info("responseInfoObj   "+hierarchyList);
		return responseInfoObj;
	}
	
	
}
