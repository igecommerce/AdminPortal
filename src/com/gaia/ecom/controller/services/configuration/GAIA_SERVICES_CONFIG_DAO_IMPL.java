package com.gaia.ecom.controller.services.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaia.beans.GAIA_ECOM_BEANS;
import com.gaia.conn.util.GAIA_CONN_UTIL_IMPL;
import com.gaia.ecom.controller.response.GAIA_ECOM_RESPONSEINFO;
import com.gaia.ecom.controller.services.login.GAIA_SERVICES_LOGIN_USERDETAIL;
import com.gaia.util.GAIA_UTILS;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;

@Service
public class GAIA_SERVICES_CONFIG_DAO_IMPL extends GAIA_ECOM_BEANS implements GAIA_SERVICES_ICONFIG_DAO {
	private static Logger log = Logger.getLogger(GAIA_SERVICES_CONFIG_DAO_IMPL.class);

	
	public static <T> List<T> convertJSONArraytoList(String jsonArrayString,String className) throws JSONException, ClassNotFoundException
	{
		List<T> list = new ArrayList<T>();
		JSONArray jsonArr = new JSONArray(jsonArrayString);
		for (int i = 0; i < jsonArr.length(); i++) {
			JSONObject jsonObj = jsonArr.getJSONObject(i);
			T item = (T) GAIA_UTILS.convertJSONtooOBJECT(jsonObj.toString(), className);
			list.add(item);
		}
		return list;
	}
	
	
	
	@Override
	public GAIA_ECOM_RESPONSEINFO savetax(String taxData,HttpServletRequest httpRequest)
			 {
		try{
		// TODO Auto-generated method stub
		GAIA_SERVICES_CONFIG_CATEGORY_DETAIL request = (GAIA_SERVICES_CONFIG_CATEGORY_DETAIL) GAIA_UTILS.convertJSONtooOBJECT(taxData, GAIA_SERVICES_CONFIG_CATEGORY_DETAIL.class);
		

		LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
		inParamMap.put("P_TAX_ID", request.getTaxid());
		inParamMap.put("P_TAX_PERCENTAGE", request.getPercentage());
		inParamMap.put("P_TAX_LABEL", request.getLabel());
		inParamMap.put("P_TAX_ORIGIN", request.getOrigin());
		inParamMap.put("P_TAX_STATUS", request.getStatus());
		inParamMap.put("P_OPERATION", request.getOprn());
		log.info(inParamMap);

		Map<String, Object> resultMap = GAIA_CONN_UTIL_IMPL.executeProcedure(
				jdbcTemplate, "P_GAIA_TAX", inParamMap);
		log.info(resultMap);
		responseInfoObj.setGaiaresponse(resultMap);

		}catch(Exception e)
		{
			e.printStackTrace();
			responseInfoObj.setResponseType("F");
			responseInfoObj.setGaiaresponse("Exception Occured.Kindly contact system support.");
		}
		
		return responseInfoObj;
	}
	
	@Override
	public GAIA_ECOM_RESPONSEINFO saveshipconfig(String shipData,HttpServletRequest httpRequest)
			 {
		try{
		// TODO Auto-generated method stub
		GAIA_SERVICES_CONFIG_CATEGORY_DETAIL request = (GAIA_SERVICES_CONFIG_CATEGORY_DETAIL) GAIA_UTILS.convertJSONtooOBJECT(shipData, GAIA_SERVICES_CONFIG_CATEGORY_DETAIL.class);
		

		LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
		inParamMap.put("P_SHIP_ID", request.getShippingid());
		inParamMap.put("P_SHIP_LABEL", request.getLabel());
		inParamMap.put("P_SHIP_AMOUNT", request.getAmount());
		inParamMap.put("P_SHIP_ORIGIN", request.getOrigin());
		inParamMap.put("P_OPERATION", request.getOprn());
		log.info(inParamMap);

		Map<String, Object> resultMap = GAIA_CONN_UTIL_IMPL.executeProcedure(
				jdbcTemplate, "P_GAIA_SHIPCONFIG", inParamMap);
		log.info(resultMap);
		responseInfoObj.setGaiaresponse(resultMap);

		}catch(Exception e)
		{
			e.printStackTrace();
			responseInfoObj.setResponseType("F");
			responseInfoObj.setGaiaresponse("Exception Occured.Kindly contact system support.");
		}
		
		return responseInfoObj;
	}
	
	
	
	
	
	@Override
	public void exportOrderTable(String fileName, HttpServletRequest httpRequest,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		int extensionIndex = fileName.lastIndexOf(".");
		String uploadedFilename = fileName.substring(0,extensionIndex)+"_"+new Date().toString()+fileName.substring(extensionIndex,fileName.length());
		log.info("uploadedfilename "+uploadedFilename);
		try {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=ExportOrders.xlsx");
			
			DataValidation dataValidation = null;
			DataValidationConstraint constraint = null;
			DataValidationHelper validationHelper = null;
	
			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sheet=(XSSFSheet) wb.createSheet("export");
	
			Row row = sheet.createRow(0);
			int columnCount = 0;
			String columnNames = env.getRequiredProperty("orderexportexcelcolumns");
			StringTokenizer columnToken = new StringTokenizer(columnNames,",");
			while(columnToken.hasMoreTokens())
			{
				String columnName = columnToken.nextToken();
				Cell cell = row.createCell(columnCount);
				cell.setCellValue(columnName);
				
				CellStyle cellStyle = wb.createCellStyle();
				CreationHelper createHelper = wb.getCreationHelper();
				cellStyle.setDataFormat(
				    createHelper.createDataFormat().getFormat("dd/mm/yyyy"));
				/*cell = row.createCell(1);
				cell.setCellValue(new Date());
				cell.setCellStyle(cellStyle);*/
				
				columnCount++;
				
			}
			
			Object[] inputValue = new Object[] {};
			List orderList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
					env.getRequiredProperty("exportorders"), inputValue,
					GAIA_SERVICES_CONFIG_ORDER_DETAILS.class);
			
			if(orderList.size() > 0){
			int sheetsno = 1;
			Iterator orderListITR = orderList.iterator();
			while(orderListITR.hasNext())
			{
				GAIA_SERVICES_CONFIG_ORDER_DETAILS orderMap = (GAIA_SERVICES_CONFIG_ORDER_DETAILS) orderListITR.next();
				Row rowList = sheet.createRow(sheetsno);
				CellStyle cellStyle = wb.createCellStyle();
				CreationHelper createHelper = wb.getCreationHelper();
				cellStyle.setDataFormat(
				    createHelper.createDataFormat().getFormat("dd/mm/yyyy"));
				
				log.info(orderMap.getCustomerid());
				
				Cell cell1 = rowList.createCell(0);
				String cell1v = orderMap.getCustomerid();
				cell1.setCellValue(cell1v);
				cell1.setCellStyle(cellStyle);
				
				Cell cell2 = rowList.createCell(1);
				String cell2v = orderMap.getCustomername();
				cell2.setCellValue(cell2v);
				cell2.setCellStyle(cellStyle);
				
				Cell cell3 = rowList.createCell(2);
				String cell3v = orderMap.getProductname();
				cell3.setCellValue(cell3v);
				cell3.setCellStyle(cellStyle);
				
				Cell cell4 = rowList.createCell(3);
				String cell4v = orderMap.getTotalprice();
				cell4.setCellValue(cell4v);
				cell4.setCellStyle(cellStyle);
				
				Cell cell5 = rowList.createCell(4);
				String cell5v = orderMap.getMobileno();
				cell5.setCellValue(cell5v);
				cell5.setCellStyle(cellStyle);
				
				sheetsno++;
			}
			}
			
			validationHelper=new XSSFDataValidationHelper(sheet);
			
			for(int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
			     sheet.autoSizeColumn(columnIndex);
			}
			
			wb.write(response.getOutputStream());
			
        } catch (Exception e) {
            /*throw new ServletException("Exception in DownLoad Excel Servlet", e);*/
        	e.printStackTrace();
        }
	}

	
	
	
	public GAIA_ECOM_RESPONSEINFO getcategoryhierarchylist(HttpServletRequest request) {
		String applicationContextURL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()+"/";
		log.info("applicationContextURL  "+applicationContextURL);
		Object[] inputValue = new Object[] {};
		List hierarchyList = new ArrayList();
		List mainCategoryList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("maincategoryqueryforservice"), new Object[]{}, GAIA_SERVICES_CONFIG_CATEGORY_DETAIL_HIERARCHY.class);
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
			GAIA_SERVICES_CONFIG_CATEGORY_DETAIL_HIERARCHY mainCategoryMap = (GAIA_SERVICES_CONFIG_CATEGORY_DETAIL_HIERARCHY) mainCategoryListITR.next();
			String categoryId = mainCategoryMap.getCategoryid();
			log.info("categoryId   "+categoryId);
			List preCategoryList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("precategoryqueryforservice"), new Object[]{categoryId}, GAIA_SERVICES_CONFIG_CATEGORY_DETAIL_HIERARCHY.class);
			log.info("preCategoryList   "+preCategoryList);
			hierarchyList.add(mainCategoryMap);
			
			Iterator preCategoryListITR = preCategoryList.iterator();
			while(preCategoryListITR.hasNext())
			{
				GAIA_SERVICES_CONFIG_CATEGORY_DETAIL_HIERARCHY subCategoryMap = (GAIA_SERVICES_CONFIG_CATEGORY_DETAIL_HIERARCHY) preCategoryListITR.next();
				categoryId = subCategoryMap.getCategoryid();
				List subCategoryList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("subcategoryqueryforservice"), new Object[]{categoryId}, GAIA_SERVICES_CONFIG_CATEGORY_DETAIL_HIERARCHY.class);

				hierarchyList.add(subCategoryMap);
				
				hierarchyList.addAll(subCategoryList);
			}
		}
		
		
		
		Iterator hierarchyListITR = hierarchyList.iterator();
		List hierarchyListwithurl = new ArrayList();
		while(hierarchyListITR.hasNext())
		{
			GAIA_SERVICES_CONFIG_CATEGORY_DETAIL_HIERARCHY hierarchyMap = (GAIA_SERVICES_CONFIG_CATEGORY_DETAIL_HIERARCHY) hierarchyListITR.next();
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
	
	
	
	
	public static Date convertToSQLDate(String stringDate) throws ParseException
	{
		Date parsedstringDate = null;
		java.sql.Date sqlDate = null;
		
		SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy");
		parsedstringDate = format.parse(stringDate);
		sqlDate = new java.sql.Date(parsedstringDate.getTime());		
		log.info(sqlDate);
		return sqlDate;
		}


	public static Date convertUTILDATEToSQLDate(String stringDate, String informat) throws ParseException
	{
		Date parsedstringDate = null;
		java.sql.Date sqlDate = null;
		
		SimpleDateFormat format = new SimpleDateFormat(informat);
		parsedstringDate = format.parse(stringDate);
		sqlDate = new java.sql.Date(parsedstringDate.getTime());		
		log.info(sqlDate);
		return sqlDate;
		}


	
	@Override
	public GAIA_ECOM_RESPONSEINFO savemincart(String mincartData,HttpServletRequest httpRequest)
			 {
		try{
		// TODO Auto-generated method stub
		GAIA_SERVICES_CONFIG_CATEGORY_DETAIL request = (GAIA_SERVICES_CONFIG_CATEGORY_DETAIL) GAIA_UTILS.convertJSONtooOBJECT(mincartData, GAIA_SERVICES_CONFIG_CATEGORY_DETAIL.class);
		
		
		LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
		inParamMap.put("P_MINCART_ID", request.getMincartid());
		inParamMap.put("P_MINCART_VALUE", request.getMincartvalue());
		inParamMap.put("P_STATUS", request.getStatus());
		inParamMap.put("P_OPERATION", request.getOprn());

		//System.out.println(inParamMap);

		Map<String, Object> resultMap = GAIA_CONN_UTIL_IMPL.executeProcedure(
				jdbcTemplate, "P_GAIA_MINCARTVALUE", inParamMap);
		//System.out.println(resultMap);
		responseInfoObj.setGaiaresponse(resultMap);

		}catch(Exception e)
		{
			e.printStackTrace();
			responseInfoObj.setResponseType("F");
			responseInfoObj.setGaiaresponse("Exception Occured.Kindly contact system support.");
		}
		
		return responseInfoObj;
	}
	
}
