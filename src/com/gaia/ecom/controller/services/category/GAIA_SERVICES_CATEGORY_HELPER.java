package com.gaia.ecom.controller.services.category;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.gaia.ecom.controller.response.GAIA_ECOM_RESPONSEINFO;

@Service
public class GAIA_SERVICES_CATEGORY_HELPER {
	
	public GAIA_ECOM_RESPONSEINFO createcategory(String categoryData,GAIA_SERVICES_ICATEGORY_DAO categoryDAO,HttpServletRequest httpRequest)
	{
		return categoryDAO.createcategory(categoryData,httpRequest);
	}
	
	public GAIA_ECOM_RESPONSEINFO checkposition(String value,String level,GAIA_SERVICES_ICATEGORY_DAO categoryDAO,HttpServletRequest httpRequest)
	{
		return categoryDAO.checkposition(value,level,httpRequest);
	}
	
	public GAIA_ECOM_RESPONSEINFO viewposition(String categorydata,GAIA_SERVICES_ICATEGORY_DAO categoryDAO,HttpServletRequest httpRequest)
	{
		return categoryDAO.viewposition(categorydata,httpRequest);
	}
	
	public GAIA_ECOM_RESPONSEINFO getpresubcategory(String value,GAIA_SERVICES_ICATEGORY_DAO categoryDAO,HttpServletRequest httpRequest)
	{
		return categoryDAO.getpresubcategory(value,httpRequest);
	}
	public GAIA_ECOM_RESPONSEINFO getcategoryhierarchylist(GAIA_SERVICES_ICATEGORY_DAO categoryDAO,HttpServletRequest request)
	{
		return categoryDAO.getcategoryhierarchylist(request);
	}
}
