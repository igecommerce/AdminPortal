package com.gaia.ecom.controller.services.products;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.gaia.ecom.controller.response.GAIA_ECOM_RESPONSEINFO;
import com.gaia.ecom.controller.services.configuration.GAIA_SERVICES_ICONFIG_DAO;

@Service
public class GAIA_SERVICES_PRODUCT_HELPER {
	public GAIA_ECOM_RESPONSEINFO importtoproduct(String importData,String digits,GAIA_SERVICES_PRODUCT_DAO productDAO,HttpServletRequest httpRequest)
	{
		return productDAO.importtoproduct(importData,digits,httpRequest);
	}
	
	public GAIA_ECOM_RESPONSEINFO updateproduct(String importData,GAIA_SERVICES_PRODUCT_DAO productDAO,HttpServletRequest httpRequest)
	{
		return productDAO.updateproduct(importData,httpRequest);
	}
	
	public GAIA_ECOM_RESPONSEINFO createproduct(String productData,GAIA_SERVICES_PRODUCT_DAO productDAO,HttpServletRequest httpRequest)
	{
		return productDAO.createproduct(productData,httpRequest);
	}
	
	public GAIA_ECOM_RESPONSEINFO cancelimport(String digits,String productData,GAIA_SERVICES_PRODUCT_DAO productDAO,HttpServletRequest httpRequest)
	{
		return productDAO.cancelimport(digits,productData,httpRequest);
	}
	
	public GAIA_ECOM_RESPONSEINFO savebrand(String brandData,GAIA_SERVICES_PRODUCT_DAO productDAO,HttpServletRequest httpRequest)
	{
		return productDAO.savebrand(brandData,httpRequest);
	}
	
	public GAIA_ECOM_RESPONSEINFO saveuom(String uomData,GAIA_SERVICES_PRODUCT_DAO productDAO,HttpServletRequest httpRequest)
	{
		return productDAO.saveuom(uomData,httpRequest);
	}
	
	public void downloadSampleFormat(String fileName,String columnsheet,GAIA_SERVICES_PRODUCT_DAO productDAO,HttpServletRequest httpRequest,HttpServletResponse httpResponse)
	{
		productDAO.downloadSampleFormat(fileName,columnsheet,httpRequest,httpResponse);
	}

}
