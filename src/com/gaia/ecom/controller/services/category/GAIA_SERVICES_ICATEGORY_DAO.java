package com.gaia.ecom.controller.services.category;

import javax.servlet.http.HttpServletRequest;

import com.gaia.ecom.controller.response.GAIA_ECOM_RESPONSEINFO;

public interface GAIA_SERVICES_ICATEGORY_DAO {
	public GAIA_ECOM_RESPONSEINFO createcategory(String categoryData,HttpServletRequest httpRequest);
	public GAIA_ECOM_RESPONSEINFO checkposition(String value,String level,HttpServletRequest httpRequest);
	public GAIA_ECOM_RESPONSEINFO viewposition(String categorydata,HttpServletRequest httpRequest);
	public GAIA_ECOM_RESPONSEINFO getpresubcategory(String value,HttpServletRequest httpRequest);
	public GAIA_ECOM_RESPONSEINFO getcategoryhierarchylist(HttpServletRequest request);
}
