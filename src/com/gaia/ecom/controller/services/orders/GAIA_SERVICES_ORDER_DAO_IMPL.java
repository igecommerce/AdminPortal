package com.gaia.ecom.controller.services.orders;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.gaia.beans.GAIA_ECOM_BEANS;
import com.gaia.conn.util.GAIA_CONN_UTIL_IMPL;
import com.gaia.ecom.controller.response.GAIA_ECOM_RESPONSEINFO;
import com.gaia.ecom.controller.services.configuration.GAIA_SERVICES_CONFIG_DAO_IMPL;
import com.gaia.ecom.controller.services.configuration.GAIA_SERVICES_CONFIG_ORDER_DETAILS;
@Service
public class GAIA_SERVICES_ORDER_DAO_IMPL extends GAIA_ECOM_BEANS implements GAIA_SERVICES_IORDER_DAO{
	private static Logger log = Logger.getLogger(GAIA_SERVICES_ORDER_DAO_IMPL.class);
	
	
	public GAIA_ECOM_RESPONSEINFO orderlist() {
		// TODO Auto-generated method stub
		/*Object[] inputValue = new Object[] {};
		log.info("inputValue   " + inputValue);
		List userList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				env.getRequiredProperty("allusers"), inputValue,
				GAIA_SERVICES_LOGIN_USERDETAIL.class);
		log.info("userList   " + userList);

		responseInfoObj.setGaiaresponse(userList);*/
		/*ArrayList<HashMap<String, String>> prodArrayList = new ArrayList<HashMap<String, String>>();
		int i;
		for(i = 1; i<=5;i++){
			String istr = String.valueOf(i);
			HashMap<String, String> prodHashMap = new HashMap<String, String>();
			prodHashMap.put("serialno", istr);
		    prodHashMap.put("order", "order"+i);
		    prodHashMap.put("amount", i+"000");
		    prodHashMap.put("name", "name"+i);
		    prodHashMap.put("mobileno", "8056547040"+i);
		    prodHashMap.put("action", "<a href='orderdetails' class=\"edit myBtnTab\" style='background-color:#f3476f;padding:4px;color:white;'>view</a>");
		    prodArrayList.add(prodHashMap);
		    log.info("list3"  );
		    log.info(prodArrayList );
		}*/
		Object[] inputValue = new Object[] {};
		log.info("inputValue   " + inputValue);
		List orderList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				env.getRequiredProperty("allorders"), inputValue,
				GAIA_SERVICES_ORDER_DETAIL.class);
		log.info("orderList   " + orderList);

		responseInfoObj.setGaiaresponse(orderList);
		
		return responseInfoObj;
	}

}
