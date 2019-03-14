package com.gaia.ecom.controller.services.login;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.gaia.ecom.controller.response.GAIA_ECOM_RESPONSEINFO;
 
@Service
public class GAIA_SERVICES_LOGINHELPER {

	public GAIA_ECOM_RESPONSEINFO validategaiasignin(String signinData,GAIA_SERVICES_ILOGINDAO loginDAO,HttpServletRequest httpRequest) throws Exception
	{
		return loginDAO.validategaiasignin(signinData,httpRequest);
	}
	
	public GAIA_ECOM_RESPONSEINFO changepassword(String passwordData,GAIA_SERVICES_ILOGINDAO loginDAO,HttpServletRequest httpRequest) throws Exception
	{
		return loginDAO.changepassword(passwordData,httpRequest);
	}
	
	public GAIA_ECOM_RESPONSEINFO resetpassword(String username,GAIA_SERVICES_ILOGINDAO loginDAO,HttpServletRequest httpRequest) throws Exception
	{
		return loginDAO.resetpassword(username,httpRequest);
	}
	
	public GAIA_ECOM_RESPONSEINFO registeruser(String regnValue,GAIA_SERVICES_ILOGINDAO loginDAO,HttpServletRequest httpRequest ) throws Exception
	{
		return loginDAO.registeruser(regnValue,httpRequest);
	}
	
	public GAIA_ECOM_RESPONSEINFO userlist(GAIA_SERVICES_ILOGINDAO loginDAO) throws Exception
	{
		return loginDAO.userlist();
	}
	
	public GAIA_ECOM_RESPONSEINFO userroles(GAIA_SERVICES_ILOGINDAO loginDAO) throws Exception
	{
		return loginDAO.userroles();
	}
	public GAIA_ECOM_RESPONSEINFO saveuserrole(String roleData,GAIA_SERVICES_ILOGINDAO loginDAO) throws Exception
	{
		return loginDAO.saveuserrole(roleData);
	}
	
	public GAIA_ECOM_RESPONSEINFO saveusermenus(String roleData,GAIA_SERVICES_ILOGINDAO loginDAO) throws Exception
	{
		return loginDAO.saveusermenus(roleData);
	}
	
	public GAIA_ECOM_RESPONSEINFO getuserdetails(GAIA_SERVICES_ILOGINDAO loginDAO,String userid) throws Exception
	{
		return loginDAO.getuserdetails(userid);
	}
}
