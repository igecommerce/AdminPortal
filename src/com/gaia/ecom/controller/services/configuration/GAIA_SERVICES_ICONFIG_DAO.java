package com.gaia.ecom.controller.services.configuration;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gaia.ecom.controller.response.GAIA_ECOM_RESPONSEINFO;


public interface GAIA_SERVICES_ICONFIG_DAO {
	
	
	public GAIA_ECOM_RESPONSEINFO savetax(String taxData,HttpServletRequest httpRequest);
	public GAIA_ECOM_RESPONSEINFO saveshipconfig(String shipData,HttpServletRequest httpRequest);
	public GAIA_ECOM_RESPONSEINFO getcategoryhierarchylist(HttpServletRequest request);
	
	public void exportOrderTable(String fileName,HttpServletRequest httpRequest,HttpServletResponse response);
	
	public GAIA_ECOM_RESPONSEINFO savemincart(String mincartData,HttpServletRequest httpRequest);
}
