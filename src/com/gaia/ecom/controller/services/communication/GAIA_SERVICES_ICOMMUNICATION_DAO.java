package com.gaia.ecom.controller.services.communication;

import javax.servlet.http.HttpServletRequest;

import com.gaia.ecom.controller.response.GAIA_ECOM_RESPONSEINFO;

public interface GAIA_SERVICES_ICOMMUNICATION_DAO {
	public GAIA_ECOM_RESPONSEINFO savesms(String smsData,HttpServletRequest httpRequest);
	public GAIA_ECOM_RESPONSEINFO savemsg(String smsData,HttpServletRequest httpRequest);
	public GAIA_ECOM_RESPONSEINFO saveemailmsg(String emailData,HttpServletRequest httpRequest);
	public GAIA_ECOM_RESPONSEINFO saveemail(String emailData,HttpServletRequest httpRequest);
	public GAIA_ECOM_RESPONSEINFO loademailsettings();
	public GAIA_ECOM_RESPONSEINFO loadsmssettings();
}
