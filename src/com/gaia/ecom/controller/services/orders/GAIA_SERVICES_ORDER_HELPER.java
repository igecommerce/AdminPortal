package com.gaia.ecom.controller.services.orders;

import org.springframework.stereotype.Service;

import com.gaia.ecom.controller.response.GAIA_ECOM_RESPONSEINFO;


@Service
public class GAIA_SERVICES_ORDER_HELPER {

	public GAIA_ECOM_RESPONSEINFO orderlist(GAIA_SERVICES_IORDER_DAO orderDAO)
	{
		return orderDAO.orderlist();
	}

}
