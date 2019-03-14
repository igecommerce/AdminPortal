package com.gaia.ecom.controller.services.banner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gaia.ecom.controller.response.GAIA_ECOM_RESPONSEINFO;

public interface GAIA_SERVICES_IBANNER_DAO {
	public GAIA_ECOM_RESPONSEINFO importbanner(String importData,HttpServletRequest httpRequest);
	public GAIA_ECOM_RESPONSEINFO importbulkuploadbannertype(String importData,HttpServletRequest httpRequest);
	public void downloadbannerformat(String fileName,HttpServletRequest httpRequest,HttpServletResponse response);
	public void downloadbannerformatforbannertype(String fileName,HttpServletRequest httpRequest,HttpServletResponse response);
	public GAIA_ECOM_RESPONSEINFO savebanner(String uploadData,HttpServletRequest httpRequest);
}
