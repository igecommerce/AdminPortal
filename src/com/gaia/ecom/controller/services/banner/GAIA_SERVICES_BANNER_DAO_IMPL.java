package com.gaia.ecom.controller.services.banner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFDataValidationHelper;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gaia.beans.GAIA_ECOM_BEANS;
import com.gaia.conn.util.GAIA_CONN_UTIL_IMPL;
import com.gaia.ecom.controller.response.GAIA_ECOM_RESPONSEINFO;
import com.gaia.ecom.controller.services.configuration.GAIA_SERVICES_CONFIG_CATEGORY_DETAIL;
import com.gaia.ecom.controller.services.configuration.GAIA_SERVICES_CONFIG_IMPORT_PRODUCT_DETAILS;
import com.gaia.util.GAIA_UTILS;

@Service
public class GAIA_SERVICES_BANNER_DAO_IMPL extends GAIA_ECOM_BEANS implements GAIA_SERVICES_IBANNER_DAO {
	
	private static Logger log = Logger.getLogger(GAIA_SERVICES_BANNER_DAO_IMPL.class);
	
	@Override
	public void downloadbannerformat(String fileName,
			HttpServletRequest httpRequest, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int extensionIndex = fileName.lastIndexOf(".");
		String uploadedFilename = fileName.substring(0,extensionIndex)+"_"+new Date().toString()+fileName.substring(extensionIndex,fileName.length());
		log.info("uploadedfilename "+uploadedFilename);
		try {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=SamplebulkuploadExcel.xlsx");
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
			XSSFSheet sheet=(XSSFSheet) wb.createSheet("bulkupload");
	
			Row rowSample = sheet.createRow(0);
			int columnCount = 0;
			String columnNames = env.getRequiredProperty("bulkuploadexcelformatcolumns");
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
			
		
			List<String> bannerstockstatusList = GAIA_UTILS.getStringList(jdbcTemplate, env.getRequiredProperty("statusqueryforbulkuploadexcel"));
			List<String> imagestockstatusList = GAIA_UTILS.getStringList(jdbcTemplate, env.getRequiredProperty("bannerlist"));
			String[] bannerstockstatusListArr = GAIA_UTILS.convertListtoStringArray(bannerstockstatusList);
			String[] imagestockstatusListArr = GAIA_UTILS.convertListtoStringArray(imagestockstatusList);
			validationHelper=new XSSFDataValidationHelper(sheet);
			
			dataValidation = GAIA_UTILS.getCellList(validationHelper,bannerstockstatusListArr, sheet,true,5,5);
			dataValidation = GAIA_UTILS.getCellList(validationHelper,imagestockstatusListArr, sheet,true,5,1);
			
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
	public void downloadbannerformatforbannertype(String fileName,
			HttpServletRequest httpRequest, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int extensionIndex = fileName.lastIndexOf(".");
		String uploadedFilename = fileName.substring(0,extensionIndex)+"_"+new Date().toString()+fileName.substring(extensionIndex,fileName.length());
		log.info("uploadedfilename "+uploadedFilename);
		try {
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=SamplebulkuploadExcel.xlsx");
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
			XSSFSheet sheet=(XSSFSheet) wb.createSheet("bulkupload");
	
			Row rowSample = sheet.createRow(0);
			int columnCount = 0;
			String columnNames = env.getRequiredProperty("bulkuploadexcelcolumnsforbanner");
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
			
		
			List<String> bannerstockstatusList = GAIA_UTILS.getStringList(jdbcTemplate, env.getRequiredProperty("statusqueryforbulkuploadexcel"));
			
			String[] bannerstockstatusListArr = GAIA_UTILS.convertListtoStringArray(bannerstockstatusList);
			
			validationHelper=new XSSFDataValidationHelper(sheet);
			
			dataValidation = GAIA_UTILS.getCellList(validationHelper,bannerstockstatusListArr, sheet,true,5,2);
		
			
			for(int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
			     sheet.autoSizeColumn(columnIndex);
			}
			
			wb.write(response.getOutputStream());
			
        } catch (Exception e) {
            /*throw new ServletException("Exception in DownLoad Excel Servlet", e);*/
        	e.printStackTrace();
        }
	}
	
	//bulk upload start
	
	@Override
	public GAIA_ECOM_RESPONSEINFO importbanner(String importData,
			HttpServletRequest httpRequest) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap =  new HashMap<String, Object>();
		try {
			GAIA_SERVICES_CONFIG_CATEGORY_DETAIL request = (GAIA_SERVICES_CONFIG_CATEGORY_DETAIL) GAIA_UTILS.convertJSONtooOBJECT(importData, GAIA_SERVICES_CONFIG_CATEGORY_DETAIL.class);
			//List<GAIA_SERVICES_CONFIG_PRODUCT_DETAIL> itemList = convertJSONArraytoList(request.getCategoryarray(),"com.gaia.ecom.controller.services.configuration.GAIA_SERVICES_CONFIG_PRODUCT_DETAIL");
			HttpSession session = httpRequest.getSession();
			
	
			String imagePath="";
			String filepath = (String) session.getAttribute("productimport"); 
            log.info("filepath"+ filepath);
			
				int bulkuploadid = 0;
				Random r = new Random( System.currentTimeMillis() );
				bulkuploadid = 10000 + r.nextInt(20000);
				log.info("welcome for upload "+bulkuploadid);
				/*Image File Move Start*/
				String newpath = GAIA_UTILS.getserverfilepath(httpRequest);
				String modulename = GAIA_UTILS.getmodulename("bulkupload", 0);
				log.info("   "+newpath+"  "+modulename);
				newpath = newpath.concat(File.separator).concat(modulename);
				File uploadPath = new File(newpath);
				if(!uploadPath.exists())
				{
					uploadPath.mkdirs();
				}
				String productidStr = String.valueOf(bulkuploadid);
				newpath = newpath.concat(File.separator).concat(productidStr+".xlsx"); 
				log.info("newpath   "+newpath);
				new File(filepath).renameTo(new File(newpath));
				imagePath = GAIA_UTILS.filepath(newpath);
				log.info("imagePath   "+imagePath);
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
				
				XSSFSheet worksheet = workbook.getSheet("bulkupload");
				
				
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
		               /* while (cells.hasNext()) {
		                    XSSFCell cell = (XSSFCell) cells.next();
		                    totalCellCount = row.getPhysicalNumberOfCells();
		                    cellCount++;
		                    
		                    switch(cell.getCellType())
		                    {
		                    case 0:
		                    	double value = cell.getNumericCellValue();
		                    	map.put("cell"+cellCount, String.valueOf(value));
		                    	break;
		                    case 1:
		                    	map.put("cell"+cellCount, cell.getStringCellValue());
		                    }
		                    
		                    
		                    log.info(cell.getCellType()+"   "+map);
		                       
		                }*/
						int lastColumn = 9;
						 for (int cn = 0; cn <=lastColumn; cn++) {
							 Cell c = row.getCell(cn, Row.RETURN_BLANK_AS_NULL);
							 int myno = cn+1;
							 if (c == null) {
					        	  map.put("cell"+myno, "-");
					          }   else {
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
							                	double value = c.getNumericCellValue();
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
		                /*log.info("-------------------------");*/	
					}
						
	                    rowCount++;
				}
			
				System.out.print("excelDatas"+excelDatas);
				importbulkuploaditems(excelDatas);
				
				log.info("excelDatas"+excelDatas);
			
				List produtimportstatuslist = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("importstatusforbulkupload"), new Object[]{fileSeq}, 
						GAIA_SERVICES_CONFIG_IMPORT_PRODUCT_DETAILS.class);
				
				Iterator produtimportstatuslistITR = produtimportstatuslist.iterator();
				while(produtimportstatuslistITR.hasNext())
				{
					
					GAIA_SERVICES_CONFIG_IMPORT_PRODUCT_DETAILS mainproductMap = (GAIA_SERVICES_CONFIG_IMPORT_PRODUCT_DETAILS) produtimportstatuslistITR.next();
					String productId = mainproductMap.getProductid();	
					int indexId = Integer.parseInt(mainproductMap.getRecindex());
					indexId = indexId-1;
					log.info(mainproductMap.getUploadstatusdesc());
					if(mainproductMap.getUploadstatusdesc().equals("Success")){
						String imagePath1 = "";
						log.info("productId"+productId);
						Map<String, Object> map = (Map<String, Object>) excelDatas.get(indexId);
						String img5name = map.get("cell5").toString();
						
						
						String oldimagefilepath = GAIA_UTILS.getuploadfilepath(httpRequest, "banner", 1, 1).concat(File.separator)+img5name;
						String newpath1 = GAIA_UTILS.getserverfilepath(httpRequest);
						modulename = GAIA_UTILS.getmodulename("banner", 1);
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
						
						
						
						
						
						log.info("imagePath "+imagePath1);
						
					}
					log.info("excelDatas"+excelDatas);
				}
			//System.out.print("excelDatascopy "+excelDatas);
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
		} 
		
		
		return responseInfoObj;
	}
	@Transactional
	public void importbulkuploaditems( final List<Object> list) 
	{
		try
		{
			StringBuilder parametersSB = new StringBuilder();
			
			int totalCellCount = 11;
			
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
			
			System.out.print("parametersSB        "+parametersSB);
			
			jdbcTemplate.batchUpdate("{call P_GAIA_BANNER( "+parametersSB+" )}",
				new BatchPreparedStatementSetter() {					
				public int getBatchSize()
				{
					return list.size();
				}
	
			public void setValues(PreparedStatement ps, int index) throws SQLException 
			{
				
				Map<String, Object> map = (Map<String, Object>) list.get(index);
				for(int cell = 1;cell <= 11;cell++)
					
				{

					 if(map.get("cell"+cell) == null){
						ps.setString(cell, "string");
					  }
					 else{
					//System.out.print("List"+map.get("cell13"+cell).toString());
					/*log.info("P_CELL"+cell+","+map.get("cell"+cell)+"   "+(map.get("cell"+cell).getClass()));*/
					ps.setString(cell, map.get("cell"+cell).toString());
					 }
					 ps.setInt(12, index);	
				}
				
				
				
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
	public GAIA_ECOM_RESPONSEINFO importbulkuploadbannertype(String importData,
			HttpServletRequest httpRequest) {
		// TODO Auto-generated method stub
		Map<String, Object> resultMap =  new HashMap<String, Object>();
		try {
			GAIA_SERVICES_CONFIG_CATEGORY_DETAIL request = (GAIA_SERVICES_CONFIG_CATEGORY_DETAIL) GAIA_UTILS.convertJSONtooOBJECT(importData, GAIA_SERVICES_CONFIG_CATEGORY_DETAIL.class);
			//List<GAIA_SERVICES_CONFIG_PRODUCT_DETAIL> itemList = convertJSONArraytoList(request.getCategoryarray(),"com.gaia.ecom.controller.services.configuration.GAIA_SERVICES_CONFIG_PRODUCT_DETAIL");
			HttpSession session = httpRequest.getSession();
			
	
			String imagePath="";
			String filepath = (String) session.getAttribute("productimport"); 
            log.info("filepath"+ filepath);
			
				int bulkuploadid = 0;
				Random r = new Random( System.currentTimeMillis() );
				bulkuploadid = 10000 + r.nextInt(20000);
				log.info("welcome for upload "+bulkuploadid);
				/*Image File Move Start*/
				String newpath = GAIA_UTILS.getserverfilepath(httpRequest);
				String modulename = GAIA_UTILS.getmodulename("bulkupload", 0);
				log.info("   "+newpath+"  "+modulename);
				newpath = newpath.concat(File.separator).concat(modulename);
				File uploadPath = new File(newpath);
				if(!uploadPath.exists())
				{
					uploadPath.mkdirs();
				}
				String productidStr = String.valueOf(bulkuploadid);
				newpath = newpath.concat(File.separator).concat(productidStr+".xlsx"); 
				log.info("newpath   "+newpath);
				new File(filepath).renameTo(new File(newpath));
				imagePath = GAIA_UTILS.filepath(newpath);
				log.info("imagePath   "+imagePath);
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
				
				XSSFSheet worksheet = workbook.getSheet("bulkupload");
				
				
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
		               /* while (cells.hasNext()) {
		                    XSSFCell cell = (XSSFCell) cells.next();
		                    totalCellCount = row.getPhysicalNumberOfCells();
		                    cellCount++;
		                    
		                    switch(cell.getCellType())
		                    {
		                    case 0:
		                    	double value = cell.getNumericCellValue();
		                    	map.put("cell"+cellCount, String.valueOf(value));
		                    	break;
		                    case 1:
		                    	map.put("cell"+cellCount, cell.getStringCellValue());
		                    }
		                    
		                    
		                    log.info(cell.getCellType()+"   "+map);
		                       
		                }*/
						int lastColumn = 5;
						 for (int cn = 0; cn <=lastColumn; cn++) {
							 Cell c = row.getCell(cn, Row.RETURN_BLANK_AS_NULL);
							 int myno = cn+1;
							 if (c == null) {
					        	  map.put("cell"+myno, "-");
					          }   else {
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
							                	double value = c.getNumericCellValue();
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
		                /*log.info("-------------------------");*/	
					}
						
	                    rowCount++;
				}
			
				//System.out.print("excelDatas"+excelDatas);
				importbulkuploaditemsforbannertype(excelDatas);
				List produtimportstatuslist = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("importstatusforbulkupload"), new Object[]{fileSeq}, 
						GAIA_SERVICES_CONFIG_IMPORT_PRODUCT_DETAILS.class);
				
			//System.out.print("excelDatascopy "+excelDatas);
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
		} 
		
		
		return responseInfoObj;
	}
	@Transactional
	public void importbulkuploaditemsforbannertype( final List<Object> list) 
	{
		try
		{
			StringBuilder parametersSB = new StringBuilder();
			
			int totalCellCount = 7;
			
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
			
			jdbcTemplate.batchUpdate("{call P_GAIA_BANNER_TYPE( "+parametersSB+" )}",
				new BatchPreparedStatementSetter() {					
				public int getBatchSize()
				{
					return list.size();
				}
	
			public void setValues(PreparedStatement ps, int index) throws SQLException 
			{
				
				Map<String, Object> map = (Map<String, Object>) list.get(index);
				for(int cell = 1;cell <= 7;cell++)
					
				{

					 if(map.get("cell"+cell) == null){
						ps.setString(cell, "string");
					  }
					 else{
					//System.out.print("List"+map.get("cell13"+cell).toString());
					/*log.info("P_CELL"+cell+","+map.get("cell"+cell)+"   "+(map.get("cell"+cell).getClass()));*/
					ps.setString(cell, map.get("cell"+cell).toString());
					 }
					 ps.setInt(8, index);	
				}
				
				
				
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
	public GAIA_ECOM_RESPONSEINFO savebanner(String uploadData,HttpServletRequest httpRequest)
			 {
		try{
		// TODO Auto-generated method stub
		GAIA_SERVICES_CONFIG_CATEGORY_DETAIL request = (GAIA_SERVICES_CONFIG_CATEGORY_DETAIL) GAIA_UTILS.convertJSONtooOBJECT(uploadData, GAIA_SERVICES_CONFIG_CATEGORY_DETAIL.class);
		HttpSession session = httpRequest.getSession();
		String imagePath = "";
		LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
		inParamMap.put("P_BANNERID", request.getBannerid());
		inParamMap.put("P_LAYOUT_NAME", request.getBannerlayout());
		inParamMap.put("P_STATUS", request.getAction());
		inParamMap.put("P_PATH", request.getBannerurl());
		inParamMap.put("P_BANNERTYPE", request.getBannertype());
		
		inParamMap.put("P_IMAGENAME", request.getBannername());
		inParamMap.put("P_IAMGE_DESC", request.getDescbanner());
		inParamMap.put("P_IMAGE_URL", imagePath);
		inParamMap.put("P_IMAGE_POSITION", request.getBannerposition());
		inParamMap.put("P_IMAGE_TYPE", request.getLabel());
		log.info(inParamMap);

		Map<String, Object> resultMap = GAIA_CONN_UTIL_IMPL.executeProcedure(
				jdbcTemplate, "P_GAIA_BANNER_EDIT_OPRN", inParamMap);
		System.out.println("idforupload   "+inParamMap);
		
		System.out.println("idforupload   "+resultMap);
		
		if(resultMap.get("o_out_flag") != null && resultMap.get("o_out_flag").equals("S") && session.getAttribute("bannerimage") != null)
		{
			
			System.out.println("welcome to upload");
			String oldimagefilepath = (String) session.getAttribute("bannerimage"); 
			
			System.out.println("Uploaded Filename   "+oldimagefilepath);
			int bannerid = (int) resultMap.get("o_out_id");
			System.out.println("idforupload   "+bannerid);
			if(bannerid > 0)
			{
				/*Image File Move Start*/
				String newpath = GAIA_UTILS.getserverfilepath(httpRequest);
				String modulename = GAIA_UTILS.getmodulename("banner", 1);
				//System.out.println("request.getCategorylevel()   "+request.getCategorylevel());
				newpath = newpath.concat(File.separator).concat(modulename);
				File uploadPath = new File(newpath);
				if(!uploadPath.exists())
				{
					uploadPath.mkdirs();
				}
				newpath = newpath.concat(File.separator).concat(bannerid+".jpg");
				//System.out.println("newpath   "+newpath);
				
				imagePath = GAIA_UTILS.filepath(newpath);
				//System.out.println("imagepath   "+imagePath);
				/*End*/
				
					if(request.getOprn()  != null  && request.getOprn().equals("UPD") ){
        	  		File file = new File(newpath);
					if(file.exists())
					{
						Path fileToDeletePath = Paths.get(newpath);
						Files.delete(fileToDeletePath);
						/*System.out.println("File deleted");*/
					}
				}
				new File(oldimagefilepath).renameTo(new File(newpath));
				 
				inParamMap.put("P_BANNERID", request.getBannerid());
				inParamMap.put("P_LAYOUT_NAME", request.getBannerlayout());
				inParamMap.put("P_STATUS", request.getAction());
				inParamMap.put("P_PATH", request.getBannerurl());
				inParamMap.put("P_BANNERTYPE", request.getBannertype());
				
				inParamMap.put("P_IMAGENAME", request.getBannername());
				inParamMap.put("P_IAMGE_DESC", request.getDescbanner());
				inParamMap.put("P_IMAGE_URL", imagePath);
				inParamMap.put("P_IMAGE_POSITION", request.getBannerposition());
				inParamMap.put("P_IMAGE_TYPE", request.getLabel());
				
				
				Map<String, Object> uploadResultMap = GAIA_CONN_UTIL_IMPL.executeProcedure(jdbcTemplate,"P_GAIA_BANNER_EDIT_OPRN", inParamMap);
				System.out.println("uploadResultMap   "+uploadResultMap);
				
				session.setAttribute("bannerimage","");
				
			}
			
			
		}
		
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
	
	
}
