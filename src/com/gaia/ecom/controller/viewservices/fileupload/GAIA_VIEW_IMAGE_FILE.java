/*package com.gaia.ecom.controller.viewservices.fileupload;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gaia.util.GAIA_UTILS;

*//**
 * Servlet implementation class GAIA_VIEW_IMAGE_FILE
 *//*
@WebServlet("/GAIA_VIEW_IMAGE_FILE")
public class GAIA_VIEW_IMAGE_FILE extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    *//**
     * @see HttpServlet#HttpServlet()
     *//*
    public GAIA_VIEW_IMAGE_FILE() {
        super();
        // TODO Auto-generated constructor stub
    }

	*//**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String form = request.getParameter("form");
		String type = request.getParameter("type");
		String id = request.getParameter("id");
		String filename = request.getParameter("filename");
		int inttype = 0,intid = 0;
		if(type != null)
		{
			inttype = Integer.parseInt(type);
		}
		if(id != null)
		{
			intid = Integer.parseInt(id);
		}
		
		String path=GAIA_UTILS.viewuploadfilepath(request, form, inttype,intid); 
		System.out.println("path    "+path);
		path = path.concat(File.separator).concat(id+".jpg");
		
		if(!new File(path).exists())
		{
			path = request.getRealPath("/").concat(File.separator).concat("static").concat(File.separator).concat("resources").concat(File.separator).concat("images").
					concat(File.separator).concat("noimage.png");
		}
		System.out.println("path at second   "+path);

		response.setContentType("image/jpeg");  
	    ServletOutputStream out;  
	    out = response.getOutputStream();  
	    FileInputStream fin = new FileInputStream(path);  
	      
	    BufferedInputStream bin = new BufferedInputStream(fin);  
	    BufferedOutputStream bout = new BufferedOutputStream(out);  
	    int ch =0; ;  
	    while((ch=bin.read())!=-1)  
	    {  
	    bout.write(ch);  
	    }  
	      
	    bin.close();  
	    fin.close();  
	    bout.close();  
	    out.close();  
	}

	*//**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
*/



package com.gaia.ecom.controller.viewservices.fileupload;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gaia.conn.util.GAIA_CONN_UTIL_IMPL;
import com.gaia.util.GAIA_UTILS;


/**
 * Servlet implementation class GAIA_VIEW_IMAGE_FILE
 */
@WebServlet(
		urlPatterns={"/gaiafiles/*"}
		)
public class GAIA_VIEW_IMAGE_FILE extends HttpServlet {
	private static Logger log = Logger.getLogger(GAIA_CONN_UTIL_IMPL.class);
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GAIA_VIEW_IMAGE_FILE() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String pathInfo = request.getPathInfo();
		
		String columnNames = pathInfo;
		StringTokenizer columnToken = new StringTokenizer(columnNames,"/");
		String form="",type="",id="",filename="";
		ArrayList<String> ar = new ArrayList<String>();
		while(columnToken.hasMoreTokens())
		{
			String columnName = columnToken.nextToken();
			ar.add(columnName);
		}
		form = ar.get(1);
		type = ar.get(2);
		filename = ar.get(3);
		StringTokenizer fileToken = new StringTokenizer(filename,".");
		ArrayList<String> arListFile = new ArrayList<String>();
		while(fileToken.hasMoreTokens())
		{
			String columnName = fileToken.nextToken();
			arListFile.add(columnName);
		}
		id=arListFile.get(0);
		/*String form = request.getParameter("form");
		String type = request.getParameter("type");
		String id = request.getParameter("id");
		String filename = request.getParameter("filename");*/
		int /*inttype = 0,*/intid = 0;
//		if(type != null)
//		{
//			inttype = Integer.parseInt(type);
//		}
		if(id != null)
		{
			intid = Integer.parseInt(id);
		}
		
		String path=GAIA_UTILS.viewuploadfilepath(request, form, type,intid); 
		log.info("path    "+path);
		path = path.concat(File.separator).concat(id+".jpg");
		
		if(!new File(path).exists())
		{
			if(form != null && !form.equalsIgnoreCase("productimport"))
			{
				path = request.getRealPath("/").concat(File.separator).concat("static").concat(File.separator).concat("resources").concat(File.separator).concat("images").
						concat(File.separator).concat("noimage.png");	
			}
			else
			{
				path = request.getRealPath("/").concat(File.separator).concat("static").concat(File.separator).concat("resources").concat(File.separator).concat("images").
						concat(File.separator).concat("excel.png");
			}
		}
		log.info("path at second   "+path);

		response.setContentType("image/jpeg");  
	    ServletOutputStream out;  
	    out = response.getOutputStream();  
	    FileInputStream fin = new FileInputStream(path);  
	      
	    BufferedInputStream bin = new BufferedInputStream(fin);  
	    BufferedOutputStream bout = new BufferedOutputStream(out);  
	    int ch =0; ;  
	    while((ch=bin.read())!=-1)  
	    {  
	    bout.write(ch);  
	    }  
	      
	    bin.close();  
	    fin.close();  
	    bout.close();  
	    out.close();  
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
