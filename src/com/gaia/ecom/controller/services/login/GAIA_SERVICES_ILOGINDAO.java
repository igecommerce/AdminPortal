package com.gaia.ecom.controller.services.login;

import javax.servlet.http.HttpServletRequest;

import com.gaia.ecom.controller.response.GAIA_ECOM_RESPONSEINFO;


public interface GAIA_SERVICES_ILOGINDAO {
	public GAIA_ECOM_RESPONSEINFO validategaiasignin(String signinData,HttpServletRequest httpRequest) throws Exception;
	public GAIA_ECOM_RESPONSEINFO changepassword(String passwordData,HttpServletRequest httpRequest) throws Exception;
	public GAIA_ECOM_RESPONSEINFO resetpassword(String passwordData,HttpServletRequest httpRequest) throws Exception;
	public GAIA_ECOM_RESPONSEINFO registeruser(String regnValue,HttpServletRequest httpRequest) throws Exception;
	public GAIA_ECOM_RESPONSEINFO userlist() throws Exception;
	public GAIA_ECOM_RESPONSEINFO userroles() throws Exception;
	public GAIA_ECOM_RESPONSEINFO saveuserrole(String roleData) throws Exception;
	public GAIA_ECOM_RESPONSEINFO saveusermenus(String roleData) throws Exception;
	public GAIA_ECOM_RESPONSEINFO getuserdetails(String userid) throws Exception;
}
