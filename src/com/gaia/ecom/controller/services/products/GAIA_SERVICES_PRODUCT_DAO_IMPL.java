package com.gaia.ecom.controller.services.products;

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
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaia.beans.GAIA_ECOM_BEANS;
import com.gaia.conn.util.GAIA_CONN_UTIL_IMPL;
import com.gaia.ecom.controller.response.GAIA_ECOM_RESPONSEINFO;
import com.gaia.ecom.controller.services.configuration.GAIA_SERVICES_CONFIG_CATEGORY_DETAIL;
import com.gaia.ecom.controller.services.configuration.GAIA_SERVICES_CONFIG_IMPORT_PRODUCT_DETAILS;
import com.gaia.ecom.controller.services.configuration.GAIA_SERVICES_CONFIG_PRODUCT_DETAIL;
import com.gaia.util.GAIA_UTILS;

@Service
public class GAIA_SERVICES_PRODUCT_DAO_IMPL extends GAIA_ECOM_BEANS implements GAIA_SERVICES_PRODUCT_DAO {
	private static Logger log = Logger.getLogger(GAIA_SERVICES_PRODUCT_DAO_IMPL.class);
	
	@Override
	public GAIA_ECOM_RESPONSEINFO updateproduct(String importData,
			HttpServletRequest httpRequest) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap =  new HashMap<String, Object>();
		try {
			GAIA_SERVICES_PRODUCT_DETAIL request = (GAIA_SERVICES_PRODUCT_DETAIL) GAIA_UTILS.convertJSONtooOBJECT(importData, GAIA_SERVICES_PRODUCT_DETAIL.class);
			//List<GAIA_SERVICES_CONFIG_PRODUCT_DETAIL> itemList = convertJSONArraytoList(request.getCategoryarray(),"com.gaia.ecom.controller.services.configuration.GAIA_SERVICES_CONFIG_PRODUCT_DETAIL");
			HttpSession session = httpRequest.getSession();
			
	
			String imagePath="";
			String filepath = (String) session.getAttribute("productimport"); 
            log.info("filepath"+ filepath);
			
				int productid = 0;
				Random r = new Random( System.currentTimeMillis() );
				productid = 10000 + r.nextInt(20000);
				log.info("welcome for upload "+productid);
				/*Image File Move Start*/
				String newpath = GAIA_UTILS.getserverfilepath(httpRequest);
				String modulename = GAIA_UTILS.getmodulename("productimport", 0);
				log.info("   "+newpath+"  "+modulename);
				newpath = newpath.concat(File.separator).concat(modulename);
				File uploadPath = new File(newpath);
				if(!uploadPath.exists())
				{
					uploadPath.mkdirs();
				}
				String productidStr = String.valueOf(productid);
				newpath = newpath.concat(File.separator).concat(productidStr+".xlsx"); 
				log.info("newpath   "+newpath);
				new File(filepath).renameTo(new File(newpath));
				imagePath = GAIA_UTILS.filepath(newpath);
				
				String fileSeq = "";
				String filename = productidStr+".xlsx";
				StringTokenizer token = new StringTokenizer(filename,".");
				int count = 0;
				while(token.hasMoreTokens())
				{
					if(count == 0)
					{
						fileSeq = token.nextToken();	
					}
					else
					{
						token.nextToken();
					}
					
					
					count++;
				}
				
				
				int rowCount = 0;
				FileInputStream fileInputStream = new FileInputStream(newpath);
				
				XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
				log.info("workbook"+workbook);
				XSSFSheet worksheet = workbook.getSheetAt(0);
				log.info("worksheet"+worksheet);
				
				/*XSSFRow row1 = worksheet.getRow(0);
				XSSFCell cellA1 = row1.getCell((short) 0);
				String a1Val = cellA1.getStringCellValue();*/
				int totalCellCount = 0;
				List<Object> excelDatas = new ArrayList<Object>();
				
				Iterator rows = worksheet.rowIterator();
				
				while (rows.hasNext()) {
					/*log.info("rowCount       "+rowCount);*/
					XSSFRow row = (XSSFRow) rows.next();
					if(rowCount > 1)
					{
						Iterator cells = row.cellIterator();
						int cellCount = 0;
						Map<String, Object> map = new HashMap<String, Object>();
						
						//System.out.println(row.getLastCellNum());
						//System.out.println();
						
						int lastColumn = 26;
						log.info("lastColumn"+lastColumn);

					       for (int cn = 0; cn <=lastColumn; cn++) {
					          Cell c = row.getCell(cn, Row.RETURN_BLANK_AS_NULL);
					          int myno = cn+1;
					          if (c == null) {
					        	  map.put("cell"+myno, "-");
					          } else {
					        	  switch(c.getCellType())
				                    {
				                    case 0:
				                    	//System.out.println(row.getCell(cn));
				                    	//System.out.println(HSSFDateUtil.isCellDateFormatted(c));
				                    	if (HSSFDateUtil.isCellDateFormatted(c)) {
							                  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
							                  try {
							                    String date = dateFormat.format(c.getDateCellValue());
							                    map.put("cell"+myno, date);
							                    break;
							                  } catch (Exception e) {
							                    e.printStackTrace();
							                    break;
							                  }
							                  
							                }else{
							                	//double value = c.getNumericCellValue();
							                	int value = new Double(c.getNumericCellValue()).intValue();
						                    	map.put("cell"+myno, String.valueOf(value));
						                    	break;
						                    	}
				                    case 1:
				                    	map.put("cell"+myno, c.getStringCellValue());
				                    }
					        		log.info("cn"+myno);
					          }
					       }
					       map.put("cell"+(lastColumn + 1), fileSeq);
			               map.put("cell"+(lastColumn + 2), session.getAttribute("userid"));
					       excelDatas.add(map);  
					       //System.out.print("cellvalue"+map.get("cell26").toString());
		                /*log.info("-------------------------");*/	
					}
						
	                    rowCount++;
				}
			
				
				updateproductitems(excelDatas);
				
				log.info("cellvalue"+excelDatas);
			
				List produtimportstatuslist = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("productimportstatus"), new Object[]{fileSeq}, 
						GAIA_SERVICES_PRODUCT_IMPORT_DETAILS.class);
				
				Iterator produtimportstatuslistITR = produtimportstatuslist.iterator();
				while(produtimportstatuslistITR.hasNext())
				{
					
					GAIA_SERVICES_PRODUCT_IMPORT_DETAILS mainproductMap = (GAIA_SERVICES_PRODUCT_IMPORT_DETAILS) produtimportstatuslistITR.next();
					String productId = mainproductMap.getProductid();	
					int indexId = Integer.parseInt(mainproductMap.getRecindex());
					indexId = indexId-1;
					log.info(mainproductMap.getUploadstatusdesc());
					if(mainproductMap.getUploadstatusdesc().equals("Success")){
						String imagePath1 = "",thumbnailPath1 = "",thumbnailPath2 = "",thumbnailPath3 = "",thumbnailPath4 = "",smallimage = "";
						log.info("productId"+productId);
						
						Map<String, Object> map = (Map<String, Object>) excelDatas.get(indexId);
						log.info(map);
						log.info("map 19"+map.get("cell19").toString());
						
						if(!map.get("cell18").equals("-")){
							
							String img18name = map.get("cell18").toString();
						String oldimagefilepath = GAIA_UTILS.getuploadfilepath(httpRequest, "product", 1, 1).concat(File.separator)+img18name;
						String newpath1 = GAIA_UTILS.getserverfilepath(httpRequest);
						modulename = GAIA_UTILS.getmodulename("product", 1);
						log.info("oldimagefilepath "+oldimagefilepath);
						newpath1 = newpath1.concat(File.separator).concat(modulename);
						uploadPath = new File(newpath1);
						if(!uploadPath.exists())
						{
							uploadPath.mkdirs();
						}
						newpath1 = newpath1.concat(File.separator).concat(productId+".jpg");
						if(oldimagefilepath!=null){
						File file = new File(newpath1);
						if(file.exists())
						{
							Path fileToDeletePath = Paths.get(newpath1);
							Files.delete(fileToDeletePath);
							/*log.info("File deleted");*/
						}
						}
						new File(oldimagefilepath).renameTo(new File(newpath1));
						log.info("newpath1"+newpath1);
						imagePath1 = GAIA_UTILS.filepath(newpath1);
						}
						
						if(!map.get("cell19").equals("-")){
							//log.info("priya");
						
							String img19name = map.get("cell19").toString();
						String oldthumb1filepath = GAIA_UTILS.getuploadfilepath(httpRequest, "product", 1, 1).concat(File.separator)+img19name;
						String newpath2 = GAIA_UTILS.getserverfilepath(httpRequest);
						modulename = GAIA_UTILS.getmodulename("product", 2);
						log.info("oldthumb1filepath "+oldthumb1filepath);
						newpath2 = newpath2.concat(File.separator).concat(modulename);
						uploadPath = new File(newpath2);
						if(!uploadPath.exists())
						{
							uploadPath.mkdirs();
						}
						newpath2 = newpath2.concat(File.separator).concat(productId+".jpg");
						if(oldthumb1filepath!=null){
						File file2 = new File(newpath2);
						if(file2.exists())
						{
							Path fileToDeletePath = Paths.get(newpath2);
							Files.delete(fileToDeletePath);
							/*log.info("File deleted");*/
						}
						}
						new File(oldthumb1filepath).renameTo(new File(newpath2));
						log.info("newpath2"+newpath2);
						thumbnailPath1 = GAIA_UTILS.filepath(newpath2);
						}
					
						if(!map.get("cell20").equals("-")){
							String img20name = map.get("cell20").toString();
						String oldthumb2filepath = GAIA_UTILS.getuploadfilepath(httpRequest, "product", 1, 1).concat(File.separator)+img20name;
						String newpath3 = GAIA_UTILS.getserverfilepath(httpRequest);
						modulename = GAIA_UTILS.getmodulename("product", 3);
						log.info("oldthumb1filepath "+oldthumb2filepath);
						newpath3 = newpath3.concat(File.separator).concat(modulename);
						uploadPath = new File(newpath3);
						if(!uploadPath.exists())
						{
							uploadPath.mkdirs();
						}
						newpath3 = newpath3.concat(File.separator).concat(productId+".jpg");
						if(oldthumb2filepath!=null){
						File file3 = new File(newpath3);
						if(file3.exists())
						{
							Path fileToDeletePath = Paths.get(newpath3);
							Files.delete(fileToDeletePath);
							/*log.info("File deleted");*/
						}
						}
						new File(oldthumb2filepath).renameTo(new File(newpath3));
						log.info("newpath2"+newpath3);
						thumbnailPath2 = GAIA_UTILS.filepath(newpath3);
						}
						
						if(!map.get("cell21").equals("-")){
							String img21name = map.get("cell21").toString();
						String oldthumb3filepath = GAIA_UTILS.getuploadfilepath(httpRequest, "product", 1, 1).concat(File.separator)+img21name;
						String newpath4 = GAIA_UTILS.getserverfilepath(httpRequest);
						modulename = GAIA_UTILS.getmodulename("product", 4);
						log.info("oldthumb1filepath "+oldthumb3filepath);
						newpath4 = newpath4.concat(File.separator).concat(modulename);
						uploadPath = new File(newpath4);
						if(!uploadPath.exists())
						{
							uploadPath.mkdirs();
						}
						newpath4 = newpath4.concat(File.separator).concat(productId+".jpg");
						if(oldthumb3filepath!=null){
						File file4 = new File(newpath4);
						if(file4.exists())
						{
							Path fileToDeletePath = Paths.get(newpath4);
							Files.delete(fileToDeletePath);
							/*log.info("File deleted");*/
						}
						}
						new File(oldthumb3filepath).renameTo(new File(newpath4));
						
						thumbnailPath3 = GAIA_UTILS.filepath(newpath4);
						}
						
						if(!map.get("cell22").equals("-")){
							String img22name = map.get("cell22").toString();
						String oldthumb4filepath = GAIA_UTILS.getuploadfilepath(httpRequest, "product", 1, 1).concat(File.separator)+img22name;
						String newpath5 = GAIA_UTILS.getserverfilepath(httpRequest);
						modulename = GAIA_UTILS.getmodulename("product", 5);
						
						newpath5 = newpath5.concat(File.separator).concat(modulename);
						uploadPath = new File(newpath5);
						if(!uploadPath.exists())
						{
							uploadPath.mkdirs();
						}
						newpath5 = newpath5.concat(File.separator).concat(productId+".jpg");
						if(oldthumb4filepath!=null){
						File file5 = new File(newpath5);
						if(file5.exists())
						{
							Path fileToDeletePath = Paths.get(newpath5);
							Files.delete(fileToDeletePath);
							/*log.info("File deleted");*/
						}
						}
						new File(oldthumb4filepath).renameTo(new File(newpath5));
						
						thumbnailPath4 = GAIA_UTILS.filepath(newpath5);
						}
						
						if(!map.get("cell23").equals("-")){
							String img23name = map.get("cell23").toString();
						String oldsmallimagefilepath = GAIA_UTILS.getuploadfilepath(httpRequest, "product", 1, 1).concat(File.separator)+img23name;
						String newpath6 = GAIA_UTILS.getserverfilepath(httpRequest);
						modulename = GAIA_UTILS.getmodulename("product", 6);
						
						newpath6 = newpath6.concat(File.separator).concat(modulename);
						uploadPath = new File(newpath6);
						if(!uploadPath.exists())
						{
							uploadPath.mkdirs();
						}
						newpath6 = newpath6.concat(File.separator).concat(productId+".jpg");
						if(oldsmallimagefilepath!=null){
						File file6 = new File(newpath6);
						if(file6.exists())
						{
							Path fileToDeletePath = Paths.get(newpath6);
							Files.delete(fileToDeletePath);
							/*log.info("File deleted");*/
						}
						}
						new File(oldsmallimagefilepath).renameTo(new File(newpath6));
						log.info("newpath2"+newpath6);
						smallimage = GAIA_UTILS.filepath(newpath6);
						}
						
						log.info("imagePath "+imagePath1);
						LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
						inParamMap.put("P_IMAGE_ID", "0");
						inParamMap.put("P_PRODUCTD_ID", productId);
						inParamMap.put("P_PRODUCT_IMAGE_PATH",imagePath1 );
						inParamMap.put("P_PRODUCT_IMAGE_LABEL", "label");
						inParamMap.put("P_PRODUCT_THUMB_IMAGE1", thumbnailPath1);
						inParamMap.put("P_PRODUCT_THUMB_LABEL1", "label");
						inParamMap.put("P_PRODUCT_THUMB_IMAGE2", thumbnailPath2);
						inParamMap.put("P_PRODUCT_THUMB_LABEL2", "label");
						inParamMap.put("P_PRODUCT_THUMB_IMAGE3", thumbnailPath3);
						inParamMap.put("P_PRODUCT_THUMB_LABEL3", "label");
						inParamMap.put("P_PRODUCT_THUMB_IMAGE4", thumbnailPath4);
						inParamMap.put("P_PRODUCT_THUMB_LABEL4", "label");
						inParamMap.put("P_PRODUCT_SMALL_IMAGE", smallimage);
						inParamMap.put("P_PRODUCT_SMALL_LABEL", "label");
						inParamMap.put("P_POSITION", "0");
						inParamMap.put("P_ACTION", "UPD");
						
						Map<String, Object> uploadResultMap = GAIA_CONN_UTIL_IMPL.executeProcedure(jdbcTemplate,"P_GAIA_PRODUCTIMAGE", inParamMap);
						log.info("uploadResultMap   "+uploadResultMap);
						
						String img24name = map.get("cell24").toString();
						String columnNames = img24name;
		                StringTokenizer columnToken = new StringTokenizer(columnNames,",");
		                StringBuilder categoryStr = new StringBuilder("[");
		                while(columnToken.hasMoreTokens())
		                {
		                        String columnName = columnToken.nextToken();
		                        categoryStr.append("{categoryid:"+columnName+"},");
		                }
		                categoryStr.append("]");
		               // System.out.println(categoryStr);
		                
						List<GAIA_SERVICES_PRODUCT_DETAIL> itemList = convertJSONArraytoList(categoryStr.toString(),"com.gaia.ecom.controller.services.products.GAIA_SERVICES_PRODUCT_DETAIL");
						
						
						String img25name = map.get("cell25").toString();
						String precolumnnames = img25name;
		                StringTokenizer precolumntoken = new StringTokenizer(precolumnnames,",");
		                StringBuilder precategoryStr = new StringBuilder("[");
		                while(precolumntoken.hasMoreTokens())
		                {
		                        String columnname = precolumntoken.nextToken();
		                        precategoryStr.append("{categoryid:"+columnname+"},");
		                }
		                precategoryStr.append("]");
		               // System.out.println("precategory"+precategoryStr );
		                
						List<GAIA_SERVICES_PRODUCT_DETAIL> precategoryList = convertJSONArraytoList(precategoryStr.toString(),"com.gaia.ecom.controller.services.products.GAIA_SERVICES_PRODUCT_DETAIL");
						
						
						
						String img26name = map.get("cell26").toString();
						String subcolumnnames = img26name;
		                StringTokenizer subcolumntoken = new StringTokenizer(subcolumnnames,",");
		                StringBuilder subcategoryStr = new StringBuilder("[");
		                while(subcolumntoken.hasMoreTokens())
		                {
		                        String columnname = subcolumntoken.nextToken();
		                        subcategoryStr.append("{categoryid:"+columnname+"},");
		                }
		                subcategoryStr.append("]");
		                //System.out.println("sub categoiry"+subcategoryStr);
		                
						List<GAIA_SERVICES_PRODUCT_DETAIL> subcategoryList = convertJSONArraytoList(subcategoryStr.toString(),"com.gaia.ecom.controller.services.products.GAIA_SERVICES_PRODUCT_DETAIL");
						List<GAIA_SERVICES_PRODUCT_DETAIL> fullCategoryList = new ArrayList<GAIA_SERVICES_PRODUCT_DETAIL>();
						for(int i=0; i < itemList.size(); i++){
							GAIA_SERVICES_PRODUCT_DETAIL fullCategoryMap = new GAIA_SERVICES_PRODUCT_DETAIL();
							fullCategoryMap.setCategoryid(itemList.get(i).getCategoryid());
							fullCategoryMap.setPrecatgeory(precategoryList.get(i).getCategoryid());
							fullCategoryMap.setSubcatgeory(subcategoryList.get(i).getCategoryid());		
							fullCategoryList.add(fullCategoryMap);
						}	
						
						Iterator<GAIA_SERVICES_PRODUCT_DETAIL> fullCategoryListItr = fullCategoryList.iterator();
						while(fullCategoryListItr.hasNext())
						{
							GAIA_SERVICES_PRODUCT_DETAIL obj = fullCategoryListItr.next();
							obj.setProductid(productId);
							
							obj.setOperation("INS");
							System.out.println("catgeory"+obj.getCategoryid()+"  "+obj.getPrecatgeory()+"  "+obj.getSubcatgeory());
						}
						
						savefullcategoryidsforupdate(fullCategoryList,productId);
						
					}
					log.info("excelDatas"+excelDatas);
				}
				log.info("excelDatascopy "+excelDatas);
				responseInfoObj.setResponseType("S");
				
				responseInfoObj.setGaiaresponse(produtimportstatuslist);
				log.info("produtimportstatuslist  "+produtimportstatuslist);
		}  catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		return responseInfoObj;
	}
	@Transactional
	public void updateproductitems( final List<Object> list) 
	{
		try
		{
			StringBuilder parametersSB = new StringBuilder();
			
			int totalCellCount = 28;
			
			for(int cell = 1;cell <= totalCellCount;cell++)
			{
				/*if(cell != totalCellCount+1)
				{*/
					parametersSB.append("?,");
				/*}
				else
				{
					parametersSB.append("?");
				}*/
			}
			parametersSB.append("?");
			
		//System.out.print("parametersSB        "+parametersSB);
			
			jdbcTemplate.batchUpdate("{call P_GAIA_UPDATE_PRODUCT_OPRNS( "+parametersSB+" )}",
				new BatchPreparedStatementSetter() {					
				public int getBatchSize()
				{
					return list.size();
				}
				
			public void setValues(PreparedStatement ps, int index) throws SQLException 
			{
				
				Map<String, Object> map = (Map<String, Object>) list.get(index);
				for(int cell = 1;cell <= 28;cell++)
				{
					
					/*log.info("P_CELL"+cell+","+map.get("cell"+cell)+"   "+(map.get("cell"+cell).getClass()));*/
					if(map.get("cell"+cell)== null){
						ps.setString(cell, "str");
					}else{
					/*if(cell == 11||cell == 12){
						String value = map.get("cell"+cell).toString();
						String date = null;
						try {
							date = String.valueOf(convertUTILDATEToSQLDate(value, "mm/dd/yyyy"));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						ps.setString(cell, date);
						log.info("date"+date);
                    }else{
                    	ps.setString(cell, map.get("cell"+cell).toString());
                    	log.info("List"+map.get("cell"+cell).toString());
				}*/
						ps.setString(cell, map.get("cell"+cell).toString());
				}
				}
				ps.setInt(29, index);
				
			log.info(index+" ) Imported Successfully");
				 
				
			}
		});
		}
		catch( Exception e )
		{
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public GAIA_ECOM_RESPONSEINFO importtoproduct(String importData,String digits,
			HttpServletRequest httpRequest) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap =  new HashMap<String, Object>();
		try {
			GAIA_SERVICES_PRODUCT_DETAIL request = (GAIA_SERVICES_PRODUCT_DETAIL) GAIA_UTILS.convertJSONtooOBJECT(importData, GAIA_SERVICES_PRODUCT_DETAIL.class);
			//List<GAIA_SERVICES_CONFIG_PRODUCT_DETAIL> itemList = convertJSONArraytoList(request.getCategoryarray(),"com.gaia.ecom.controller.services.configuration.GAIA_SERVICES_CONFIG_PRODUCT_DETAIL");
			HttpSession session = httpRequest.getSession();
			 log.info("digitsimpl"+ digits);
			// session.setAttribute("productimportid", digits);
			
			
			String imagePath="";
			String filepath = (String) session.getAttribute("productimport"); 
            log.info("filepath"+ filepath);
			
				int productid = 0;
				Random r = new Random( System.currentTimeMillis() );
				productid = 10000 + r.nextInt(20000);
				log.info("welcome for upload "+productid);
				/*Image File Move Start*/
				String newpath = GAIA_UTILS.getserverfilepath(httpRequest);
				String modulename = GAIA_UTILS.getmodulename("productimport", 0);
				log.info("   "+newpath+"  "+modulename);
				newpath = newpath.concat(File.separator).concat(modulename);
				File uploadPath = new File(newpath);
				if(!uploadPath.exists())
				{
					uploadPath.mkdirs();
				}
				String productidStr = String.valueOf(productid);
				newpath = newpath.concat(File.separator).concat(productidStr+".xlsx"); 
				log.info("newpath   "+newpath);
				new File(filepath).renameTo(new File(newpath));
				imagePath = GAIA_UTILS.filepath(newpath);
				
				String fileSeq =digits ;
				String filename = productidStr+".xlsx";
				StringTokenizer token = new StringTokenizer(filename,".");
				int count = 0;
				/*while(token.hasMoreTokens())
				{
					if(count == 0)
					{
						fileSeq = token.nextToken();	
					}
					else
					{
						token.nextToken();
					}
					
					
					count++;
				}*/
				
				 session.setAttribute("productimportid", fileSeq);
				
				int rowCount = 0;
				FileInputStream fileInputStream = new FileInputStream(newpath);
				
				XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
				
				XSSFSheet worksheet = workbook.getSheet("productlist");
				
				
				/*XSSFRow row1 = worksheet.getRow(0);
				XSSFCell cellA1 = row1.getCell((short) 0);
				String a1Val = cellA1.getStringCellValue();*/
				int totalCellCount = 0;
				List<Object> excelDatas = new ArrayList<Object>();
				
				Iterator rows = worksheet.rowIterator();
				
				while (rows.hasNext()) {
					/*log.info("rowCount       "+rowCount);*/
					XSSFRow row = (XSSFRow) rows.next();
					if(rowCount > 0)
					{
						Iterator cells = row.cellIterator();
						int cellCount = 0;
						Map<String, Object> map = new HashMap<String, Object>();
						
						//System.out.println(row.getLastCellNum());
						//System.out.println();
						
						int lastColumn = 26;
						//System.out.println("lastColumn "+lastColumn);
					       for (int cn = 0; cn < 26; cn++) {
					          Cell c = row.getCell(cn, Row.RETURN_BLANK_AS_NULL);
					          int myno = cn+1;
					          if (c == null) {
					        	  map.put("cell"+myno, "-");
					          } else {
					        	  switch(c.getCellType())
				                    {
				                    case 0:
				                    	//System.out.println(row.getCell(cn));
				                    	//System.out.println(HSSFDateUtil.isCellDateFormatted(c));
				                    	if (HSSFDateUtil.isCellDateFormatted(c)) {
							                  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
							                  try {
							                    String date = dateFormat.format(c.getDateCellValue());
							                    map.put("cell"+myno, date);
							                    break;
							                  } catch (Exception e) {
							                    e.printStackTrace();
							                    break;
							                  }
							                  
							                }else{
							                	int value = new Double(c.getNumericCellValue()).intValue();
							                	//double value = c.getNumericCellValue();
						                    	map.put("cell"+myno, String.valueOf(value));
						                    	break;
						                    	}
				                    case 1:
				                    	//System.out.println(row.getCell(cn));
				                    	map.put("cell"+myno, c.getStringCellValue());
				                    }
					          }
					       }
					       map.put("cell"+(lastColumn + 1), fileSeq);
			               map.put("cell"+(lastColumn + 2), session.getAttribute("userid"));
					       excelDatas.add(map);  
					   
		                /*while (cells.hasNext()) {
		                    XSSFCell cell = (XSSFCell) cells.next();
		                    totalCellCount = row.getPhysicalNumberOfCells();
		                    cellCount++;
		                    System.out.println(cellCount+"  "+cell.getCellType());
		                    switch(cell.getCellType())
		                    {
		                    case 0:
		                    	double value = cell.getNumericCellValue();
		                    	map.put("cell"+cellCount, String.valueOf(value));
		                    	break;
		                    case 1:
		                    	map.put("cell"+cellCount, cell.getStringCellValue());
		                    }
		                }
		                map.put("cell"+(totalCellCount + 1), fileSeq);
		                map.put("cell"+(totalCellCount + 2), session.getAttribute("userid"));
		                excelDatas.add(map);*/
		                /*log.info("-------------------------");*/	
					}
						
	                    rowCount++;
				}
				log.info("excelDatas"+excelDatas);
			
				importproductitems(excelDatas);
				log.info("fileSeq"+totalCellCount);
				
			
				List produtimportstatuslist = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("productimportstatus"), new Object[]{fileSeq}, 
						GAIA_SERVICES_PRODUCT_IMPORT_DETAILS.class);
				
				Iterator produtimportstatuslistITR = produtimportstatuslist.iterator();
				while(produtimportstatuslistITR.hasNext())
				{
					
					GAIA_SERVICES_PRODUCT_IMPORT_DETAILS mainproductMap = (GAIA_SERVICES_PRODUCT_IMPORT_DETAILS) produtimportstatuslistITR.next();
					String productId = mainproductMap.getProductid();	
					int indexId = Integer.parseInt(mainproductMap.getRecindex());
					indexId = indexId-1;
					log.info(mainproductMap.getUploadstatusdesc());
					if(mainproductMap.getUploadstatusdesc().equals("Success")){
						
						String imagePath1 = "",thumbnailPath1 = "",thumbnailPath2 = "",thumbnailPath3 = "",thumbnailPath4 = "",smallimage = "";
						log.info("productId"+productId);
						Map<String, Object> map = (Map<String, Object>) excelDatas.get(indexId);
						
						String img18name = map.get("cell18").toString();
						String img19name = map.get("cell19").toString();
						String img20name = map.get("cell20").toString();
						String img21name = map.get("cell21").toString();
						String img22name = map.get("cell22").toString();
						String img23name = map.get("cell23").toString();
						
						String oldimagefilepath = GAIA_UTILS.getuploadfilepath(httpRequest, "product", 1, 1).concat(File.separator)+img18name;
						String newpath1 = GAIA_UTILS.getserverfilepath(httpRequest);
						modulename = GAIA_UTILS.getmodulename("product", 1);
						log.info("oldimagefilepath "+oldimagefilepath);
						newpath1 = newpath1.concat(File.separator).concat(modulename);
						uploadPath = new File(newpath1);
						if(!uploadPath.exists())
						{
							uploadPath.mkdirs();
						}
						newpath1 = newpath1.concat(File.separator).concat(productId+".jpg");
						new File(oldimagefilepath).renameTo(new File(newpath1));
						log.info("newpath1"+newpath1);
						imagePath1 = GAIA_UTILS.filepath(newpath1);
						
						
						
						String oldthumb1filepath = GAIA_UTILS.getuploadfilepath(httpRequest, "product", 1, 1).concat(File.separator)+img19name;
						String newpath2 = GAIA_UTILS.getserverfilepath(httpRequest);
						modulename = GAIA_UTILS.getmodulename("product", 2);
						log.info("oldthumb1filepath "+oldthumb1filepath);
						newpath2 = newpath2.concat(File.separator).concat(modulename);
						uploadPath = new File(newpath2);
						if(!uploadPath.exists())
						{
							uploadPath.mkdirs();
						}
						newpath2 = newpath2.concat(File.separator).concat(productId+".jpg");
						new File(oldthumb1filepath).renameTo(new File(newpath2));
						log.info("newpath2"+newpath2);
						thumbnailPath1 = GAIA_UTILS.filepath(newpath2);
						
						
						String oldthumb2filepath = GAIA_UTILS.getuploadfilepath(httpRequest, "product", 1, 1).concat(File.separator)+img20name;
						String newpath3 = GAIA_UTILS.getserverfilepath(httpRequest);
						modulename = GAIA_UTILS.getmodulename("product", 3);
						log.info("oldthumb1filepath "+oldthumb1filepath);
						newpath3 = newpath3.concat(File.separator).concat(modulename);
						uploadPath = new File(newpath3);
						if(!uploadPath.exists())
						{
							uploadPath.mkdirs();
						}
						newpath3 = newpath3.concat(File.separator).concat(productId+".jpg");
						new File(oldthumb2filepath).renameTo(new File(newpath3));
						log.info("newpath2"+newpath2);
						thumbnailPath2 = GAIA_UTILS.filepath(newpath3);
						
						
						String oldthumb3filepath = GAIA_UTILS.getuploadfilepath(httpRequest, "product", 1, 1).concat(File.separator)+img21name;
						String newpath4 = GAIA_UTILS.getserverfilepath(httpRequest);
						modulename = GAIA_UTILS.getmodulename("product", 4);
						log.info("oldthumb1filepath "+oldthumb1filepath);
						newpath4 = newpath4.concat(File.separator).concat(modulename);
						uploadPath = new File(newpath4);
						if(!uploadPath.exists())
						{
							uploadPath.mkdirs();
						}
						newpath4 = newpath4.concat(File.separator).concat(productId+".jpg");
						new File(oldthumb3filepath).renameTo(new File(newpath4));
						log.info("newpath2"+newpath2);
						thumbnailPath3 = GAIA_UTILS.filepath(newpath4);
						
						
						
						String oldthumb4filepath = GAIA_UTILS.getuploadfilepath(httpRequest, "product", 1, 1).concat(File.separator)+img22name;
						String newpath5 = GAIA_UTILS.getserverfilepath(httpRequest);
						modulename = GAIA_UTILS.getmodulename("product", 5);
						log.info("oldthumb1filepath "+oldthumb1filepath);
						newpath5 = newpath5.concat(File.separator).concat(modulename);
						uploadPath = new File(newpath5);
						if(!uploadPath.exists())
						{
							uploadPath.mkdirs();
						}
						newpath5 = newpath5.concat(File.separator).concat(productId+".jpg");
						new File(oldthumb4filepath).renameTo(new File(newpath5));
						log.info("newpath2"+newpath2);
						thumbnailPath4 = GAIA_UTILS.filepath(newpath5);
						
						
						String oldsmallimagefilepath = GAIA_UTILS.getuploadfilepath(httpRequest, "product", 1, 1).concat(File.separator)+img23name;
						String newpath6 = GAIA_UTILS.getserverfilepath(httpRequest);
						modulename = GAIA_UTILS.getmodulename("product", 6);
						log.info("oldthumb1filepath "+oldthumb1filepath);
						newpath6 = newpath6.concat(File.separator).concat(modulename);
						uploadPath = new File(newpath6);
						if(!uploadPath.exists())
						{
							uploadPath.mkdirs();
						}
						newpath6 = newpath6.concat(File.separator).concat(productId+".jpg");
						new File(oldsmallimagefilepath).renameTo(new File(newpath6));
						log.info("newpath2"+newpath2);
						smallimage = GAIA_UTILS.filepath(newpath6);
						
						log.info("imagePath "+imagePath1);
						LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
						inParamMap.put("P_IMAGE_ID", "0");
						inParamMap.put("P_PRODUCTD_ID", productId);
						inParamMap.put("P_PRODUCT_IMAGE_PATH",imagePath1 );
						inParamMap.put("P_PRODUCT_IMAGE_LABEL", "label");
						inParamMap.put("P_PRODUCT_THUMB_IMAGE1", thumbnailPath1);
						inParamMap.put("P_PRODUCT_THUMB_LABEL1", "label");
						inParamMap.put("P_PRODUCT_THUMB_IMAGE2", thumbnailPath2);
						inParamMap.put("P_PRODUCT_THUMB_LABEL2", "label");
						inParamMap.put("P_PRODUCT_THUMB_IMAGE3", thumbnailPath3);
						inParamMap.put("P_PRODUCT_THUMB_LABEL3", "label");
						inParamMap.put("P_PRODUCT_THUMB_IMAGE4", thumbnailPath4);
						inParamMap.put("P_PRODUCT_THUMB_LABEL4", "label");
						inParamMap.put("P_PRODUCT_SMALL_IMAGE", smallimage);
						inParamMap.put("P_PRODUCT_SMALL_LABEL", "label");
						inParamMap.put("P_POSITION", "0");
						inParamMap.put("P_ACTION", "INS");
						
						Map<String, Object> uploadResultMap = GAIA_CONN_UTIL_IMPL.executeProcedure(jdbcTemplate,"P_GAIA_PRODUCTIMAGE", inParamMap);
						log.info("uploadResultMap   "+uploadResultMap);
						
						String img24name = map.get("cell24").toString();
						String columnNames = img24name;
		                StringTokenizer columnToken = new StringTokenizer(columnNames,",");
		                StringBuilder categoryStr = new StringBuilder("[");
		                while(columnToken.hasMoreTokens())
		                {
		                        String columnName = columnToken.nextToken();
		                        categoryStr.append("{categoryid:"+columnName+"},");
		                }
		                categoryStr.append("]");
		                //System.out.println("category"+categoryStr);
		                
						List<GAIA_SERVICES_PRODUCT_DETAIL> itemList = convertJSONArraytoList(categoryStr.toString(),"com.gaia.ecom.controller.services.products.GAIA_SERVICES_PRODUCT_DETAIL");
						
						
					
						String img25name = map.get("cell25").toString();
						String precolumnnames = img25name;
		                StringTokenizer precolumntoken = new StringTokenizer(precolumnnames,",");
		                StringBuilder precategoryStr = new StringBuilder("[");
		                while(precolumntoken.hasMoreTokens())
		                {
		                        String columnname = precolumntoken.nextToken();
		                        precategoryStr.append("{categoryid:"+columnname+"},");
		                }
		                precategoryStr.append("]");
		               // System.out.println("precategory"+precategoryStr );
		                
						List<GAIA_SERVICES_PRODUCT_DETAIL> precategoryList = convertJSONArraytoList(precategoryStr.toString(),"com.gaia.ecom.controller.services.products.GAIA_SERVICES_PRODUCT_DETAIL");
						
					
						
						String img26name = map.get("cell26").toString();
						String subcolumnnames = img26name;
		                StringTokenizer subcolumntoken = new StringTokenizer(subcolumnnames,",");
		                StringBuilder subcategoryStr = new StringBuilder("[");
		                while(subcolumntoken.hasMoreTokens())
		                {
		                        String columnname = subcolumntoken.nextToken();
		                        subcategoryStr.append("{categoryid:"+columnname+"},");
		                }
		                subcategoryStr.append("]");
		                //System.out.println("sub categoiry"+subcategoryStr);
		                
						List<GAIA_SERVICES_PRODUCT_DETAIL> subcategoryList = convertJSONArraytoList(subcategoryStr.toString(),"com.gaia.ecom.controller.services.products.GAIA_SERVICES_PRODUCT_DETAIL");
						
						
						List<GAIA_SERVICES_PRODUCT_DETAIL> fullCategoryList = new ArrayList<GAIA_SERVICES_PRODUCT_DETAIL>();
						for(int i=0; i < itemList.size(); i++){
							GAIA_SERVICES_PRODUCT_DETAIL fullCategoryMap = new GAIA_SERVICES_PRODUCT_DETAIL();
							fullCategoryMap.setCategoryid(itemList.get(i).getCategoryid());
							fullCategoryMap.setPrecatgeory(precategoryList.get(i).getCategoryid());
							fullCategoryMap.setSubcatgeory(subcategoryList.get(i).getCategoryid());		
							fullCategoryList.add(fullCategoryMap);
						}	
						
						Iterator<GAIA_SERVICES_PRODUCT_DETAIL> fullCategoryListItr = fullCategoryList.iterator();
						while(fullCategoryListItr.hasNext())
						{
							GAIA_SERVICES_PRODUCT_DETAIL obj = fullCategoryListItr.next();
							obj.setProductid(productId);
							
							obj.setOperation("INS");
							System.out.println("catgeory"+obj.getCategoryid()+"  "+obj.getPrecatgeory()+"  "+obj.getSubcatgeory());
						}
						
						savefullcategoryids(fullCategoryList,productId);
						
					}
					
					
					log.info("excelDatas"+excelDatas);
				}
				log.info("excelDatascopy "+excelDatas);
				responseInfoObj.setResponseType("S");
				
				responseInfoObj.setGaiaresponse(produtimportstatuslist);
				log.info("produtimportstatuslist  "+produtimportstatuslist);
		}  catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		return responseInfoObj;
	}
	@Transactional
	public void importproductitems( final List<Object> list) 
	{
		try
		{
			StringBuilder parametersSB = new StringBuilder();
			
			int totalCellCount = 28;
			
			for(int cell = 1;cell <= totalCellCount;cell++)
			{
				/*if(cell != totalCellCount+1)
				{*/
					parametersSB.append("?,");
				/*}
				else
				{
					parametersSB.append("?");
				}*/
			}
			parametersSB.append("?");
			
			log.info("parametersSB        "+parametersSB);
			
			jdbcTemplate.batchUpdate("{call P_GAIA_IMPORT_PRODUCT_OPRNS( "+parametersSB+" )}",
				new BatchPreparedStatementSetter() {					
				public int getBatchSize()
				{
					return list.size();
				}
	
			public void setValues(PreparedStatement ps, int index) throws SQLException 
			{
				
				
				Map<String, Object> map = (Map<String, Object>) list.get(index);
				
				for(int cell = 1;cell <= 28;cell++)
				{
					 if(map.get("cell"+cell) == null){
						ps.setString(cell, "string");
					  }
					 else{
						 /*if(cell == 11||cell == 12){
								String value = map.get("cell"+cell).toString();
								String date = null;
								try {
									date = String.valueOf(convertUTILDATEToSQLDate(value, "mm/dd/yyyy"));
								} catch (ParseException e) {
									e.printStackTrace();
								}
								ps.setString(cell, date);
								log.info("date"+date);
		                    }else{*/
		                    	ps.setString(cell, map.get("cell"+cell).toString());
		                   /* }*/
						}
						ps.setInt(29, index);
						log.info(index+" ) Imported Successfully");
						
					 }
				
				
			}
		});
		}
		catch( Exception e )
		{
			e.printStackTrace();
			throw e;
		}
	}
	@Override
	public GAIA_ECOM_RESPONSEINFO cancelimport(String digits,String productData,
			HttpServletRequest httpRequest) {
		try {
			String productId = null;
			GAIA_SERVICES_PRODUCT_DETAIL request = (GAIA_SERVICES_PRODUCT_DETAIL) GAIA_UTILS.convertJSONtooOBJECT(productData, GAIA_SERVICES_PRODUCT_DETAIL.class);
			String item = digits;
			log.info("Input Details are    "+item);
			List mainproductList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("cancelimportproduct"), new Object[]{item}, GAIA_SERVICES_PRODUCT_DETAIL.class);
			log.info("Input Details are    "+mainproductList);
			Iterator mainproductListITR = mainproductList.iterator();
			while(mainproductListITR.hasNext())
			{
				GAIA_SERVICES_PRODUCT_DETAIL mainproductMap = (GAIA_SERVICES_PRODUCT_DETAIL) mainproductListITR.next();
				 productId = mainproductMap.getProductid();
				log.info("cancelimportproduct id   "+productId);
				mainproductMap.setProductid(productId);
				mainproductMap.setOperation("DEL");
			}
			deleteimportproduct(mainproductList,productId);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
				return responseInfoObj;
		
		
	}
	private void deleteimportproduct(final List<GAIA_SERVICES_PRODUCT_DETAIL> mainproductList, String productId) {
		//jdbcTemplate.execute("DELETE FROM `categories_products` WHERE `product_id` = '"+productid+"'");
		try
		{
			
			jdbcTemplate.batchUpdate("{call P_GAIA_CANCEL_IMPORT_PRODUCT( ?,?)}",
				new BatchPreparedStatementSetter() {					
				public int getBatchSize()
				{
					return mainproductList.size();
				}
	
			public void setValues(PreparedStatement ps, int i) throws SQLException 
			{
				GAIA_SERVICES_PRODUCT_DETAIL item = mainproductList.get(i);
				//log.info(item.getCategoryid()+"  "+i+"  "+item.getProductid()+"  "+item.getOperation());
				
				ps.setString(1, item.getProductid());
				ps.setString(2, item.getOperation());

			}
		});
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
	@Override
	public GAIA_ECOM_RESPONSEINFO createproduct(String productData,
			HttpServletRequest httpRequest) {
		Map<String, Object> resultMap =  new HashMap<String, Object>();
		try {
			GAIA_SERVICES_PRODUCT_DETAIL request = (GAIA_SERVICES_PRODUCT_DETAIL) GAIA_UTILS.convertJSONtooOBJECT(productData, GAIA_SERVICES_PRODUCT_DETAIL.class);
			List<GAIA_SERVICES_PRODUCT_DETAIL> itemList = convertJSONArraytoList(request.getCategoryarray(),"com.gaia.ecom.controller.services.products.GAIA_SERVICES_PRODUCT_DETAIL");
		log.info("Input Details are    "+itemList);
		HttpSession session = httpRequest.getSession();
		String imagePath = "",thumbnailPath1 = "",thumbnailPath2 = "",thumbnailPath3 = "",thumbnailPath4 = "",smallimage = "";
		
		LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
		
		inParamMap.put("P_PRODUCT_ID", request.getProductid());
		inParamMap.put("P_ACTION", request.getOperation());
		
		inParamMap.put("P_PRODUCT_TYPE", request.getTypeid());
		inParamMap.put("P_PRODUCT_WEBSITE", request.getWebsiteid());
		inParamMap.put("P_PRODUCT_SKU", request.getSku());
		inParamMap.put("P_PRODUCT_NAME", request.getName());
		inParamMap.put("P_PRODUCT_GENDER", request.getGender());
		inParamMap.put("P_PRODUCT_BRAND", request.getBrand());
		inParamMap.put("P_PRODUCT_UOM", request.getUom());
		
		inParamMap.put("P_PRODUCT_USAGE", request.getUsage());
		inParamMap.put("P_PRODUCT_COMPOSITION", request.getComposition());
		inParamMap.put("P_PRODUCT_DESCRIPTION", request.getDesc());
		
		inParamMap.put("P_PRODUCT_OFFER_STARTDATE", request.getOfferstartdate().equals("") ? " " : convertToSQLDate(request.getOfferstartdate()));
		inParamMap.put("P_PRODUCT_OFFER_ENDDATE", request.getOfferenddate().equals("") ? " " : convertToSQLDate(request.getOfferenddate()));
		inParamMap.put("P_PRODUCT_ORIGINAL_PRICE", request.getOrigprice());
		inParamMap.put("P_PRODUCT_CURRENT_PRICE", request.getCurrentprice());
		inParamMap.put("P_PRODUCT_OFFER_PRICE", request.getOfferprice());
		inParamMap.put("P_PRODUCT_COUNT", request.getProductcount());
		inParamMap.put("P_PRODUCT_STOCK_STATUS", request.getStockstatus());
		
		
		resultMap = GAIA_CONN_UTIL_IMPL.executeProcedure(jdbcTemplate,"P_GAIA_PRODUCT_OPRNS", inParamMap);
		log.info(resultMap);
		if(resultMap.get("o_out_flag") != null && resultMap.get("o_out_flag").equals("S") && session.getAttribute("productimage") != null)
		{
			if(session.getAttribute("productimage") !=null){
			String oldimagefilepath = (String) session.getAttribute("productimage"); 
			String oldthumb1filepath = (String) session.getAttribute("thumb1");
			String oldthumb2filepath = (String) session.getAttribute("thumb2");
			String oldthumb3filepath = (String) session.getAttribute("thumb3");
			String oldthumb4filepath = (String) session.getAttribute("thumb4");
			String oldsmallfilepath = (String) session.getAttribute("smallimage");
			log.info("Uploaded Filename   "+oldimagefilepath);
			int productid = (int) resultMap.get("o_out_generated_id");
			/*int productid =1;*/
			log.info("productid   "+productid);
			if(productid > 0)
			{
				log.info("welcome for upload "+productid);
				/*Image File Move Start*/
				String newpath = GAIA_UTILS.getserverfilepath(httpRequest);
				String modulename = GAIA_UTILS.getmodulename("product", 1);
				log.info("request.getCategorylevel()   "+request.getCategorylevel());
				newpath = newpath.concat(File.separator).concat(modulename);
				File uploadPath = new File(newpath);
				if(!uploadPath.exists())
				{
					uploadPath.mkdirs();
				}
				newpath = newpath.concat(File.separator).concat(productid+".jpg");
				
				if(oldimagefilepath!=null){
				/*if(request.getOperation()  != null  && request.getOperation().equals("UPD") ){*/
					  File oldfile = new File(oldimagefilepath);
					  if(oldfile.exists()){
        	  		File file = new File(newpath);
					if(file.exists())
					{
						Path fileToDeletePath = Paths.get(newpath);
						Files.delete(fileToDeletePath);
						/*log.info("File deleted");*/
					}
					  }
				/*}*/
				log.info("newpath   "+newpath);
				new File(oldimagefilepath).renameTo(new File(newpath));
				}
				
				imagePath = GAIA_UTILS.filepath(newpath);
				/*End*/
				
				/*Thumbnail File 1 Move Start*/
				newpath = GAIA_UTILS.getserverfilepath(httpRequest);
				modulename = GAIA_UTILS.getmodulename("product", 2);
				newpath = newpath.concat(File.separator).concat(modulename);
				uploadPath = new File(newpath);
				if(!uploadPath.exists())
				{
					uploadPath.mkdirs();
				}
				newpath = newpath.concat(File.separator).concat(productid+".jpg");
				
				if(oldthumb1filepath!=null){
				/*if(request.getOperation()  != null  && request.getOperation().equals("UPD") ){*/
					  File oldfile = new File(oldthumb1filepath);
					  if(oldfile.exists()){
        	  		File file = new File(newpath);
					if(file.exists())
					{
						Path fileToDeletePath = Paths.get(newpath);
						Files.delete(fileToDeletePath);
						/*log.info("File deleted");*/
					}
					 /* }*/
				}
				log.info("newpath   "+newpath);
				new File(oldthumb1filepath).renameTo(new File(newpath));
				}
				thumbnailPath1 = GAIA_UTILS.filepath(newpath);
				/*End*/
				
				/*Thumbnail File 2 Move Start*/
				newpath = GAIA_UTILS.getserverfilepath(httpRequest);
				modulename = GAIA_UTILS.getmodulename("product", 3);
				newpath = newpath.concat(File.separator).concat(modulename);
				uploadPath = new File(newpath);
				if(!uploadPath.exists())
				{
					uploadPath.mkdirs();
				}
				newpath = newpath.concat(File.separator).concat(productid+".jpg");
				
				if(oldthumb2filepath!=null){
				/*if(request.getOperation()  != null  && request.getOperation().equals("UPD") ){*/
				
                        File oldfile = new File(oldthumb2filepath);
                        if(oldfile.exists()){
        	  		File file = new File(newpath);
					if(file.exists())
					{
						Path fileToDeletePath = Paths.get(newpath);
						Files.delete(fileToDeletePath);
						/*log.info("File deleted");*/
					}
                      /*  }*/
				}
				log.info("newpath   "+newpath);
				new File(oldthumb2filepath).renameTo(new File(newpath));
				}
				thumbnailPath2 = GAIA_UTILS.filepath(newpath);
				/*End*/
				
				/*Thumbnail File 3 Move Start*/
				newpath = GAIA_UTILS.getserverfilepath(httpRequest);
				modulename = GAIA_UTILS.getmodulename("product", 4);
				newpath = newpath.concat(File.separator).concat(modulename);
				uploadPath = new File(newpath);
				if(!uploadPath.exists())
				{
					uploadPath.mkdirs();
				}
				newpath = newpath.concat(File.separator).concat(productid+".jpg");
				
				if(oldthumb3filepath!=null){
				/*if(request.getOperation()  != null  && request.getOperation().equals("UPD") ){*/
					File oldfile = new File(oldthumb3filepath);
                    if(oldfile.exists()){
        	  		File file = new File(newpath);
					if(file.exists())
					{
						Path fileToDeletePath = Paths.get(newpath);
						Files.delete(fileToDeletePath);
						/*log.info("File deleted");*/
					}
                   /* }*/
				}
				log.info("newpath   "+newpath);
				new File(oldthumb3filepath).renameTo(new File(newpath));
				}
				thumbnailPath3 = GAIA_UTILS.filepath(newpath);
				/*End*/
				
				/*Thumbnail File 4 Move Start*/
				newpath = GAIA_UTILS.getserverfilepath(httpRequest);
				modulename = GAIA_UTILS.getmodulename("product", 5);
				newpath = newpath.concat(File.separator).concat(modulename);
				uploadPath = new File(newpath);
				if(!uploadPath.exists())
				{
					uploadPath.mkdirs();
				}
				newpath = newpath.concat(File.separator).concat(productid+".jpg");
				
				if(oldthumb4filepath!=null){
				/*if(request.getOperation()  != null  && request.getOperation().equals("UPD") ){*/
					 File oldfile = new File(oldthumb4filepath);
                     if(oldfile.exists()){
        	  		File file = new File(newpath);
					if(file.exists())
					{
						Path fileToDeletePath = Paths.get(newpath);
						Files.delete(fileToDeletePath);
						/*log.info("File deleted");*/
					}
                     /*}*/
				}
				log.info("newpath   "+newpath);
				new File(oldthumb4filepath).renameTo(new File(newpath));
				}
				thumbnailPath4 = GAIA_UTILS.filepath(newpath);
				/*End*/
				
				/*smallimage File Move Start*/
				newpath = GAIA_UTILS.getserverfilepath(httpRequest);
				modulename = GAIA_UTILS.getmodulename("product", 6);
				log.info("request.getCategorylevel()   "+request.getCategorylevel());
				newpath = newpath.concat(File.separator).concat(modulename);
				uploadPath = new File(newpath);
				if(!uploadPath.exists())
				{
					uploadPath.mkdirs();
				}
				newpath = newpath.concat(File.separator).concat(productid+".jpg");
				
				if(oldsmallfilepath!=null){
				/*if(request.getOperation()  != null  && request.getOperation().equals("UPD") ){*/
					 File oldfile = new File(oldsmallfilepath);
                     if(oldfile.exists()){
        	  		File file = new File(newpath);
					if(file.exists())
					{
						Path fileToDeletePath = Paths.get(newpath);
						Files.delete(fileToDeletePath);
						/*log.info("File deleted");*/
					}
                    /* }*/
				}
				log.info("newpath   "+newpath);
				new File(oldsmallfilepath).renameTo(new File(newpath));
				}
				smallimage = GAIA_UTILS.filepath(newpath);
				/*End*/
				 
				
				inParamMap.put("P_IMAGE_ID", request.getProductid());
				inParamMap.put("P_PRODUCTD_ID", productid);
				inParamMap.put("P_PRODUCT_IMAGE_PATH", imagePath);
				inParamMap.put("P_PRODUCT_IMAGE_LABEL", request.getImage());
				inParamMap.put("P_PRODUCT_THUMB_IMAGE1", thumbnailPath1);
				inParamMap.put("P_PRODUCT_THUMB_LABEL1", request.getThumbimage1());
				inParamMap.put("P_PRODUCT_THUMB_IMAGE2", thumbnailPath2);
				inParamMap.put("P_PRODUCT_THUMB_LABEL2", request.getThumbimage2());
				inParamMap.put("P_PRODUCT_THUMB_IMAGE3", thumbnailPath3);
				inParamMap.put("P_PRODUCT_THUMB_LABEL3", request.getThumbimage3());
				inParamMap.put("P_PRODUCT_THUMB_IMAGE4", thumbnailPath4);
				inParamMap.put("P_PRODUCT_THUMB_LABEL4", request.getThumbimage4());
				inParamMap.put("P_PRODUCT_SMALL_IMAGE", smallimage);
				inParamMap.put("P_PRODUCT_SMALL_LABEL", request.getSmallimage());
				inParamMap.put("P_POSITION", "0");
				inParamMap.put("P_ACTION", request.getOperation());
				
				Map<String, Object> uploadResultMap = GAIA_CONN_UTIL_IMPL.executeProcedure(jdbcTemplate,"P_GAIA_PRODUCTIMAGE", inParamMap);
				log.info("uploadResultMap   "+uploadResultMap);
				
				session.setAttribute("productimage","");
				session.setAttribute("thumb1","");
				session.setAttribute("thumb2","");
				session.setAttribute("thumb3","");
				session.setAttribute("thumb4","");
				session.setAttribute("smallimage","");
			}
			
			}
		}
		String productid = null;
		String resultType = (String)resultMap.get("o_out_flag");
		if(resultType != null && resultType.equals("S"))
		{
		productid = String.valueOf(resultMap.get("o_out_generated_id"));
		Iterator<GAIA_SERVICES_PRODUCT_DETAIL> itr = itemList.iterator();
		while(itr.hasNext())
		{
			GAIA_SERVICES_PRODUCT_DETAIL obj = itr.next();
			obj.setProductid(productid);
			obj.setOperation("INS");
			System.out.print("category"+obj.getCategoryid()+"  "+obj.getProductid()+"  "+obj.getCategorylevel()+" ");
		}	
		}
		
		savecategoryproductids(itemList,productid);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		responseInfoObj.setResponseType("S");
		responseInfoObj.setGaiaresponse(resultMap);
		
		return responseInfoObj;
	}
	private void savecategoryproductids(final List<GAIA_SERVICES_PRODUCT_DETAIL> itemList, String productid) {
		jdbcTemplate.execute("DELETE FROM `categories_products` WHERE `product_id` = '"+productid+"'");
		try
		{
			
			jdbcTemplate.batchUpdate("{call P_GAIA_CREATE_PRODUCT_CATEGORIES( ?,?,?,?,?)}",
				new BatchPreparedStatementSetter() {					
				public int getBatchSize()
				{
					return itemList.size();
				}
	
			public void setValues(PreparedStatement ps, int i) throws SQLException 
			{
				GAIA_SERVICES_PRODUCT_DETAIL item = itemList.get(i);
				
				log.info("item "+item.getCategoryid()+"  "+i+"  "+item.getPrecatgeory()+" "+item.getProductid()+" "+item.getSubcatgeory());
				ps.setString(1, item.getCategoryid());
				ps.setString(2, item.getPrecatgeory());
				ps.setString(3, item.getSubcatgeory());
				ps.setString(4, item.getProductid());
				ps.setString(5, item.getOperation());
				
				/*else if(item.getCategorylevel().equals("1")){
					log.info("item "+item.getCategoryid()+"  "+i+"  "+item.getProductid()+" "+item.getCategorylevel()+" "+item.getOperation());
					ps.setString(1, "0");
					ps.setString(2, item.getCategoryid());
					ps.setString(3, "0");
					ps.setString(4, item.getProductid());
					ps.setString(5, item.getOperation());
					}
				else if(item.getCategorylevel().equals("2")){
					log.info("item "+item.getCategoryid()+"  "+i+"  "+item.getProductid()+" "+item.getCategorylevel()+" "+item.getOperation());
					ps.setString(1, "0");
					ps.setString(2, "0");
					ps.setString(3, item.getCategoryid());
					ps.setString(4, item.getProductid());
					ps.setString(5, item.getOperation());
					}*/
			}
		});
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
	
	
	private void savefullcategoryids(final List<GAIA_SERVICES_PRODUCT_DETAIL> fullCategoryList, String productid) {
		//jdbcTemplate.execute("DELETE FROM `categories_products` WHERE `product_id` = '"+productid+"'");
		try
		{
			
			jdbcTemplate.batchUpdate("{call P_GAIA_PRODUCT_CATEGORIES( ?,?,?,?,?)}",
				new BatchPreparedStatementSetter() {					
				public int getBatchSize()
				{
					return fullCategoryList.size();
				}
	
			public void setValues(PreparedStatement ps, int i) throws SQLException 
			{
				GAIA_SERVICES_PRODUCT_DETAIL item = fullCategoryList.get(i);
				System.out.print("item"+item.getCategoryid()+"  "+i+"  "+item.getPrecatgeory()+"  "+item.getSubcatgeory());
				ps.setString(1, item.getCategoryid());
				ps.setString(2, item.getPrecatgeory());
				ps.setString(3, item.getSubcatgeory());
				ps.setString(4, item.getProductid());
				ps.setString(5, item.getOperation());


			}
		});
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
	}
	
	
	
	private void savefullcategoryidsforupdate(final List<GAIA_SERVICES_PRODUCT_DETAIL> precategoryList, String productid) {
		jdbcTemplate.execute("DELETE FROM `categories_products` WHERE `product_id` = '"+productid+"'");
		try
		{
			
			jdbcTemplate.batchUpdate("{call P_GAIA_PRODUCT_CATEGORIES( ?,?,?,?,?)}",
				new BatchPreparedStatementSetter() {					
				public int getBatchSize()
				{
					return precategoryList.size();
				}
	
			public void setValues(PreparedStatement ps, int i) throws SQLException 
			{
				GAIA_SERVICES_PRODUCT_DETAIL item = precategoryList.get(i);
				log.info(item.getCategoryid()+"  "+i+"  "+item.getProductid()+"  "+item.getOperation());
				ps.setString(1, item.getCategoryid());
				ps.setString(2, item.getPrecatgeory());
				ps.setString(3, item.getSubcatgeory());
				ps.setString(4, item.getProductid());
				ps.setString(5, item.getOperation());

			}
		});
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
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
	
	@Override
	public GAIA_ECOM_RESPONSEINFO saveuom(String uomData,HttpServletRequest httpRequest)
			 {
		try{
		// TODO Auto-generated method stub
			GAIA_SERVICES_PRODUCT_CATEGOERY_DETAILS request = (GAIA_SERVICES_PRODUCT_CATEGOERY_DETAILS) GAIA_UTILS.convertJSONtooOBJECT(uomData, GAIA_SERVICES_PRODUCT_CATEGOERY_DETAILS.class);
		
		
		LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
		inParamMap.put("P_UOM_ID", request.getUomid());
		inParamMap.put("P_UOM_NAME", request.getUomname());
		inParamMap.put("P_STATUS", request.getStatus());
		inParamMap.put("P_OPERATION", request.getOprn());

		log.info(inParamMap);

		Map<String, Object> resultMap = GAIA_CONN_UTIL_IMPL.executeProcedure(
				jdbcTemplate, "P_GAIA_UOM", inParamMap);
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
	public void downloadSampleFormat(String fileName,String columnsheet,
			HttpServletRequest httpRequest, HttpServletResponse response) {
		// TODO Auto-generated method stub
				log.info("columnsheet"+columnsheet);
				int nofocolumn = Integer.parseInt(columnsheet);
				int extensionIndex = fileName.lastIndexOf(".");
				String uploadedFilename = fileName.substring(0,extensionIndex)+"_"+new Date().toString()+fileName.substring(extensionIndex,fileName.length());
				log.info("uploadedfilename "+uploadedFilename);
				try {
		            response.setContentType("application/vnd.ms-excel");
		            response.setHeader("Content-Disposition", "attachment; filename=SampleExcel.xlsx");
		            /*HSSFWorkbook workbook = new HSSFWorkbook();
		            HSSFSheet worksheet = workbook.createSheet("My First POI Worksheet");
		     
		            HSSFRow row1 = worksheet.createRow(0);
		     
		            HSSFCell cellA1 = row1.createCell(0);
		            cellA1.setCellValue("Hurray! You did it.");
		            HSSFCellStyle cellStyle = workbook.createCellStyle();
		            cellA1.setCellStyle(cellStyle);
		            workbook.write(response.getOutputStream());*/
					
					DataValidation dataValidation = null;
					DataValidationConstraint constraint = null;
					DataValidationHelper validationHelper = null;
			
					XSSFWorkbook wb = new XSSFWorkbook();
					XSSFSheet sheet=(XSSFSheet) wb.createSheet("productlist");
			
					Row rowSample = sheet.createRow(0);
					int columnCount = 0;
					String columnNames = env.getRequiredProperty("productexcelformatcolumns");
					StringTokenizer columnToken = new StringTokenizer(columnNames,",");
					while(columnToken.hasMoreTokens())
					{
						 
						String columnName = columnToken.nextToken();
						
						Cell cell = rowSample.createCell(columnCount);
						cell.setCellValue(columnName);
						
						CellStyle cellStyle = wb.createCellStyle();
						/*CreationHelper createHelper = wb.getCreationHelper();
						cellStyle.setDataFormat(
						    createHelper.createDataFormat().getFormat("dd/mm/yyyy"));
						cell = rowSample.createCell(1);
						cell.setCellValue(new Date());*/
						cell.setCellStyle(cellStyle);
						
						columnCount++;
						
					}
					
					//sheet = GAIA_UTILS.convertCellToDateFormatDefault(sheet,wb,10,nofocolumn);
					
					
					List<String> brandList = GAIA_UTILS.getStringList(jdbcTemplate, env.getRequiredProperty("brandqueryforimport"));
					List<String> uomList = GAIA_UTILS.getStringList(jdbcTemplate, env.getRequiredProperty("uomqueryforimport"));
					List<String> websiteList = GAIA_UTILS.getStringList(jdbcTemplate, env.getRequiredProperty("websitequery"));
					List<String> genderList = GAIA_UTILS.getStringList(jdbcTemplate, env.getRequiredProperty("genderquery"));
					List<String> stockstatusList = GAIA_UTILS.getStringList(jdbcTemplate, env.getRequiredProperty("stockstatusquery"));
					String[] brandArr = GAIA_UTILS.convertListtoStringArray(brandList);
					String[] uomArr = GAIA_UTILS.convertListtoStringArray(uomList);
					String[] websiteArr = GAIA_UTILS.convertListtoStringArray(websiteList);
					String[] genderArr = GAIA_UTILS.convertListtoStringArray(genderList);
					String[] stockstatusArr = GAIA_UTILS.convertListtoStringArray(stockstatusList);
					validationHelper=new XSSFDataValidationHelper(sheet);
					
					/*CellRangeAddressList brandCellList = new  CellRangeAddressList(1,5,4,4);
					constraint =validationHelper.createExplicitListConstraint(brandArr);
					dataValidation = validationHelper.createValidation(constraint, brandCellList);
					dataValidation.setSuppressDropDownArrow(true);      
					sheet.addValidationData(dataValidation);
					
					CellRangeAddressList uomCellList = new  CellRangeAddressList(1, 5, 5, 5);
					constraint =validationHelper.createExplicitListConstraint(uomArr);
					dataValidation = validationHelper.createValidation(constraint, uomCellList);
					dataValidation.setSuppressDropDownArrow(true);      
					sheet.addValidationData(dataValidation);*/
					
					dataValidation = GAIA_UTILS.getCellList(validationHelper,brandArr, sheet,true,nofocolumn,5);
					dataValidation = GAIA_UTILS.getCellList(validationHelper,uomArr, sheet,true,nofocolumn,6);
					dataValidation = GAIA_UTILS.getCellList(validationHelper,websiteArr, sheet,true,nofocolumn,1);
					dataValidation = GAIA_UTILS.getCellList(validationHelper,genderArr, sheet,true,nofocolumn,4);
					dataValidation = GAIA_UTILS.getCellList(validationHelper,stockstatusArr, sheet,true,nofocolumn,16);
					for(int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
					     sheet.autoSizeColumn(columnIndex);
					}
					
					wb.write(response.getOutputStream());
					
		        } catch (Exception e) {
		            /*throw new ServletException("Exception in DownLoad Excel Servlet", e);*/
		        	e.printStackTrace();
		        }
	}
	
	@Override
	public GAIA_ECOM_RESPONSEINFO savebrand(String brandData,HttpServletRequest httpRequest)
			 {
		try{
		// TODO Auto-generated method stub
			GAIA_SERVICES_PRODUCT_CATEGOERY_DETAILS request = (GAIA_SERVICES_PRODUCT_CATEGOERY_DETAILS) GAIA_UTILS.convertJSONtooOBJECT(brandData, GAIA_SERVICES_PRODUCT_CATEGOERY_DETAILS.class);
		

		LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
		inParamMap.put("P_BRAND_ID", request.getBrandid());
		inParamMap.put("P_BRAND_NAME", request.getBrandname());
		inParamMap.put("P_STATUS", request.getStatus());
		inParamMap.put("P_OPERATION", request.getOprn());

		log.info(inParamMap);

		Map<String, Object> resultMap = GAIA_CONN_UTIL_IMPL.executeProcedure(
				jdbcTemplate, "P_GAIA_BRAND", inParamMap);
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
	

}
