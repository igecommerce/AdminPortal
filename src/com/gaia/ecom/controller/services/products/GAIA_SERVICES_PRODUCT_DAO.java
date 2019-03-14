package com.gaia.ecom.controller.services.products;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gaia.ecom.controller.response.GAIA_ECOM_RESPONSEINFO;

public interface GAIA_SERVICES_PRODUCT_DAO {
	public GAIA_ECOM_RESPONSEINFO importtoproduct(String importData,String digits,HttpServletRequest httpRequest);
	 public GAIA_ECOM_RESPONSEINFO updateproduct(String importData,HttpServletRequest httpRequest);
	 public GAIA_ECOM_RESPONSEINFO createproduct(String productData,HttpServletRequest httpRequest);
	 public GAIA_ECOM_RESPONSEINFO cancelimport(String digits,String productData,HttpServletRequest httpRequest);
	 public GAIA_ECOM_RESPONSEINFO savebrand(String brandData,HttpServletRequest httpRequest);
	 public GAIA_ECOM_RESPONSEINFO saveuom(String uomData,HttpServletRequest httpRequest);
	 public void downloadSampleFormat(String fileName,String columnsheet,HttpServletRequest httpRequest,HttpServletResponse response);
}
