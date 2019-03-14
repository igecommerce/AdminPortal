package com.gaia.ecom.controller.services.banner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.gaia.ecom.controller.response.GAIA_ECOM_RESPONSEINFO;
import com.gaia.ecom.controller.services.configuration.GAIA_SERVICES_ICONFIG_DAO;

@Service
public class GAIA_SERVICES_BANNER_HELPER {
	
	public GAIA_ECOM_RESPONSEINFO importbanner(String importData,GAIA_SERVICES_IBANNER_DAO bannerDAO,HttpServletRequest httpRequest)
	{
		return bannerDAO.importbanner(importData,httpRequest);
	}
	
	public GAIA_ECOM_RESPONSEINFO importbulkuploadbannertype(String importData,GAIA_SERVICES_IBANNER_DAO bannerDAO,HttpServletRequest httpRequest)
	{
		return bannerDAO.importbulkuploadbannertype(importData,httpRequest);
	}
	
	public GAIA_ECOM_RESPONSEINFO savebanner(String uploadData,GAIA_SERVICES_IBANNER_DAO bannerDAO,HttpServletRequest httpRequest)
	{
		return bannerDAO.savebanner(uploadData,httpRequest);
	}
	
	public void downloadbannerformat(String fileName,GAIA_SERVICES_IBANNER_DAO bannerDAO,HttpServletRequest httpRequest,HttpServletResponse httpResponse)
	{
		bannerDAO.downloadbannerformat(fileName,httpRequest,httpResponse);
	}
	
	public void downloadbannerformatforbannertype(String fileName,GAIA_SERVICES_IBANNER_DAO bannerDAO,HttpServletRequest httpRequest,HttpServletResponse httpResponse)
	{
		bannerDAO.downloadbannerformatforbannertype(fileName,httpRequest,httpResponse);
	}
	
	
	
}
