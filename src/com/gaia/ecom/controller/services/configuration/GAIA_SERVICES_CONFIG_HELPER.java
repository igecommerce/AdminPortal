package com.gaia.ecom.controller.services.configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.gaia.ecom.controller.response.GAIA_ECOM_RESPONSEINFO;


@Service
public class GAIA_SERVICES_CONFIG_HELPER {

	
	public GAIA_ECOM_RESPONSEINFO savetax(String taxData,GAIA_SERVICES_ICONFIG_DAO configDAO,HttpServletRequest httpRequest)
	{
		return configDAO.savetax(taxData,httpRequest);
	}
	
	public GAIA_ECOM_RESPONSEINFO saveshipconfig(String shipData,GAIA_SERVICES_ICONFIG_DAO configDAO,HttpServletRequest httpRequest)
	{
		return configDAO.saveshipconfig(shipData,httpRequest);
	}
	
	
	public void exportOrderTable(String fileName,GAIA_SERVICES_ICONFIG_DAO configDAO,HttpServletRequest httpRequest,HttpServletResponse httpResponse)
	{
		configDAO.exportOrderTable(fileName,httpRequest,httpResponse);
	}
	

	public GAIA_ECOM_RESPONSEINFO savemincart(String mincartData,GAIA_SERVICES_ICONFIG_DAO configDAO,HttpServletRequest httpRequest)
	{
		return configDAO.savemincart(mincartData,httpRequest);
	}
}
