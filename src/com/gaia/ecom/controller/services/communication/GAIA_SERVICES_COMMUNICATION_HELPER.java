package com.gaia.ecom.controller.services.communication;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.gaia.ecom.controller.response.GAIA_ECOM_RESPONSEINFO;

@Service
public class GAIA_SERVICES_COMMUNICATION_HELPER {
	
	public GAIA_ECOM_RESPONSEINFO saveemail(String emailData,GAIA_SERVICES_ICOMMUNICATION_DAO communicationDAO,HttpServletRequest httpRequest)
	{
		return communicationDAO.saveemail(emailData,httpRequest);
	}
	
	public GAIA_ECOM_RESPONSEINFO loadsmssettings(GAIA_SERVICES_ICOMMUNICATION_DAO communicationDAO)
	{
		return communicationDAO.loadsmssettings();
	}
	
	
	public GAIA_ECOM_RESPONSEINFO loademailsettings(GAIA_SERVICES_ICOMMUNICATION_DAO communicationDAO)
	{
		return communicationDAO.loademailsettings();
	}
	
	public GAIA_ECOM_RESPONSEINFO savesms(String smsData,GAIA_SERVICES_ICOMMUNICATION_DAO communicationDAO,HttpServletRequest httpRequest)
	{
		return communicationDAO.savesms(smsData,httpRequest);
	}
	public GAIA_ECOM_RESPONSEINFO savemsg(String smsData,GAIA_SERVICES_ICOMMUNICATION_DAO communicationDAO,HttpServletRequest httpRequest)
	{
		return communicationDAO.savemsg(smsData,httpRequest);
	}
	
	public GAIA_ECOM_RESPONSEINFO saveemailmsg(String emailData,GAIA_SERVICES_ICOMMUNICATION_DAO communicationDAO,HttpServletRequest httpRequest)
	{
		return communicationDAO.saveemailmsg(emailData,httpRequest);
	}
}
