package com.gaia.ecom.controller.viewservices.fileupload;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gaia.beans.GAIA_ECOM_BEANS;
import com.gaia.conn.util.GAIA_CONN_UTIL_IMPL;
import com.gaia.util.GAIA_UTILS;
 
@Controller
@RequestMapping("/uploadservice")
public class GAIA_VIEWSERVICES_FILE_UPLOAD extends GAIA_ECOM_BEANS{
	private static Logger log = Logger.getLogger(GAIA_CONN_UTIL_IMPL.class);
	@RequestMapping(value="/savefile",method=RequestMethod.POST)  
	public ModelAndView upload(@RequestParam(value="id") String id,@RequestParam(value="form")String form,@RequestParam(value="type")String type ,
			@RequestParam(value="sessionname")String sessionname ,@RequestParam(value="foldername")String foldername,
			 @RequestParam CommonsMultipartFile file,HttpServletRequest httpRequest){
		 int inttype = 0,intid = 0;
		 String filename=file.getOriginalFilename();
		if(type != null)
        {
      	  inttype = Integer.parseInt(type);
        }
        if(id != null)
        {
        	intid = Integer.parseInt(id);
        }
        
        	int extensionIndex = filename.lastIndexOf(".");
        	if(extensionIndex == -1  ){
        		
        		log.info("extensionIndex   ");
        		  ModelAndView model = new ModelAndView();
        		model.addObject("id",id);
      			model.addObject("type",type);
      			model.addObject("form",form);
      			model.addObject("sessionname",sessionname);
        		model.addObject("color","red");
      			model.addObject("message","Please Select the File");
      			model.addObject("foldername",foldername);
      			model.setViewName("upload/upload");
        		return model; 
        	}
        	else{
        		
        	String fileextension = filename.substring(extensionIndex,filename.length());
        	log.info("fileextension   "+fileextension);
			String path=GAIA_UTILS.getuploadfilepath(httpRequest, form, inttype,intid); 
			filename = GAIA_UTILS.getuploadfilename(filename, intid,fileextension);
			
			log.info("File Upload Values are   "+path+" "+filename+"   "+id+"   "+form+"   "+type);  
			log.info("path"+path);
	        
	        try{
	        	
	        byte barr[]=file.getBytes();  
	        File uploadPath = new File(path);
	        if(!uploadPath.exists())
	        {
	        	uploadPath.mkdirs();
	        }
	        filename = path+File.separator+filename;
	        BufferedOutputStream bout=new BufferedOutputStream(  
	                 new FileOutputStream(filename));  
	        
	        HttpSession session = httpRequest.getSession();
	        session.setAttribute(sessionname, filename);
	        
	        bout.write(barr);  
	        bout.flush();  
	        bout.close();  
	          
	        }
	        catch(Exception e)
	        {
	        	System.out.println(e);
	        }  
	        ModelAndView model = new ModelAndView();
	        
	    	model.addObject("id",id);
			model.addObject("type",type);
			model.addObject("form",form);
			model.addObject("sessionname",sessionname);
			model.addObject("foldername",foldername);
			
			model.addObject("color","green");
			model.addObject("message","File Uploaded Successfully");
			
			model.setViewName("upload/upload");
			return model;  
			
        	}
        	
	}
	 @RequestMapping(value = "submitFiles", method = RequestMethod.POST)
	  public String submitPapers(MultipartHttpServletRequest request,String random) {
	    List < MultipartFile > papers = request.getFiles("papers");
	   
	    try {
	    	
	    	
	      saveFilesToServer(papers, request,random);
	     
	 	 
	     
	    } catch (Exception e) {
	      return "error";
	    }
	    return "success";
	  }

	  public void saveFilesToServer(List<MultipartFile> multipartFiles,HttpServletRequest request,String random) throws IOException {
		  String path=request.getRealPath("/");
			//System.out.println("Server path   "+path);
			File fserverpath = new File(path);
			String rootpath = (fserverpath.getParent());
		
		  String directory=rootpath.concat(File.separator).concat("documents").concat(File.separator).concat("product").concat(File.separator).concat("temp");
		 
		 
	  
		File file = new File(directory);
		file.mkdirs();
		for (MultipartFile multipartFile : multipartFiles) {
			try{
				byte barr[]=multipartFile.getBytes();  
				String filename=multipartFile.getOriginalFilename();
				
				file = new File(directory +File.separator+ multipartFile.getOriginalFilename());
			
				BufferedOutputStream bout=new BufferedOutputStream(  
		                 new FileOutputStream(directory +File.separator+ multipartFile.getOriginalFilename()));
				
				bout.write(barr);
				bout.flush();  
		        bout.close();
		       
		        extractfile(file, request, random);
		       
			}
	        catch(Exception e)
	        {
	        	System.out.println(e);
	        }  
			
			//new File(file).renameTo(new File(file));
		}
	  }
	  public static void extractfile(File file,HttpServletRequest request , String random)throws IOException {
		 
/*
		    ZipFile zipFile = new ZipFile(file);

		    Enumeration<? extends ZipEntry> entries = zipFile.entries();

		    while(entries.hasMoreElements()){
		    	System.out.print("priya ");
		        ZipEntry entry = entries.nextElement();
		        InputStream stream = zipFile.getInputStream(entry);
		       
		    }*/
		  ZipFile zipFile = null;
			try {
				
				zipFile = new ZipFile(file);
				
				// get an enumeration of the ZIP file entries
				Enumeration<? extends ZipEntry> entries = zipFile.entries();
				
				while (entries.hasMoreElements()) {
					
					ZipEntry entry = entries.nextElement();
					 
					String path=request.getRealPath("/");
					//System.out.println("Server path   "+path);
					File fserverpath = new File(path);
					String rootpath = (fserverpath.getParent());
				
				  String directory=rootpath.concat(File.separator).concat("documents").concat(File.separator).concat("product").concat(File.separator).concat("temp");
					File destinationPath = new File(directory, entry.getName());
					
					//create parent directories
					destinationPath.getParentFile().mkdirs();
					
					 
					// if the entry is a file extract it
					if (entry.isDirectory()) {
						continue;
					}
					else {
						
						System.out.println("Extracting file: " + destinationPath);
						
						BufferedInputStream bis = new BufferedInputStream(zipFile.getInputStream(entry));

						int b;
						byte buffer[] = new byte[1024];

						FileOutputStream fos = new FileOutputStream(destinationPath);
						
						BufferedOutputStream bos = new BufferedOutputStream(fos, 1024);

						while ((b = bis.read(buffer, 0, 1024)) != -1) {
							bos.write(buffer, 0, b);
						}
						
						bos.close();
						bis.close();
						
					}
					
				}
				
			}
			catch (IOException ioe) {
				System.out.println("Error opening zip file" + ioe);
			}
		}
	/*@RequestMapping(value = "submitFiles", method = RequestMethod.POST)
	public String add(@RequestParam("file") MultipartFile file,HttpServletRequest request) throws IOException {

	    *//**
	     * save file to temp
	     *//*
		String path=request.getRealPath("/");
		//System.out.println("Server path   "+path);
		File fserverpath = new File(path);
		String rootpath = (fserverpath.getParent());
	
	  String directory=rootpath.concat(File.separator).concat("documents").concat(File.separator).concat("product").concat(File.separator).concat("temp");
	    File zip = File.createTempFile(UUID.randomUUID().toString(), "temp");
	    FileOutputStream o = new FileOutputStream(zip);
	    IOUtils.copy(file.getInputStream(), o);
	    o.close();

	    *//**
	     * unizp file from temp by zip4j
	     *//*
	    String destination = directory;
	    try {
	         ZipFile zipFile = new ZipFile(zip);
	         zipFile.extractAll(destination);
	    } catch (ZipException e) {
	        e.printStackTrace();
	    } finally {
	        *//**
	         * delete temp file
	         *//*
	        zip.delete();
	    }

	    return "redirect:/";
	}*/
	@RequestMapping(value="uploadview",method = RequestMethod.GET)
	public ModelAndView getUpload(@RequestParam(value="id")String id,@RequestParam(value="type")String type,@RequestParam(value="form")String form,
			@RequestParam(value="sessionname")String sessionname,@RequestParam(value="foldername")String foldername) {
		ModelAndView model = new ModelAndView();
		
		model.addObject("id",id);
		model.addObject("type",type);
		model.addObject("form",form);
		model.addObject("sessionname",sessionname);
		model.addObject("foldername",foldername);
		
		model.addObject("color","red");
		/*model.addObject("message","Please Select a File");*/
		model.addObject("message","");
		
		model.setViewName("upload/upload");
		return model;
    }

	
}
