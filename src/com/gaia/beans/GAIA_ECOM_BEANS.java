package com.gaia.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import com.gaia.ecom.controller.response.GAIA_ECOM_RESPONSE;
import com.gaia.ecom.controller.response.GAIA_ECOM_RESPONSEINFO;
import com.gaia.ecom.controller.services.configuration.GAIA_SERVICES_CONFIG_HELPER;
import com.gaia.ecom.controller.services.configuration.GAIA_SERVICES_ICONFIG_DAO;
import com.gaia.ecom.controller.services.customer.GAIA_SERVICES_CUSTOMER_HELPER;
import com.gaia.ecom.controller.services.customer.GAIA_SERVICES_ICUSTOMER_DAO;
import com.gaia.ecom.controller.services.login.GAIA_SERVICES_ILOGINDAO;
import com.gaia.ecom.controller.services.login.GAIA_SERVICES_LOGINHELPER;
import com.gaia.ecom.controller.services.orders.GAIA_SERVICES_IORDER_DAO;
import com.gaia.ecom.controller.services.orders.GAIA_SERVICES_ORDER_HELPER;
import com.gaia.ecom.controller.services.products.GAIA_SERVICES_PRODUCT_DAO;
import com.gaia.ecom.controller.services.products.GAIA_SERVICES_PRODUCT_HELPER;

import com.gaia.ecom.controller.services.banner.GAIA_SERVICES_BANNER_HELPER;
import com.gaia.ecom.controller.services.banner.GAIA_SERVICES_IBANNER_DAO;
import com.gaia.ecom.controller.services.category.GAIA_SERVICES_CATEGORY_HELPER;
import com.gaia.ecom.controller.services.category.GAIA_SERVICES_ICATEGORY_DAO;
import com.gaia.ecom.controller.services.communication.GAIA_SERVICES_COMMUNICATION_HELPER;
import com.gaia.ecom.controller.services.communication.GAIA_SERVICES_ICOMMUNICATION_DAO;

public class GAIA_ECOM_BEANS {

	@Autowired
	protected Environment env;
	@Autowired
	protected GAIA_ECOM_RESPONSE responseObj;
	@Autowired
	protected GAIA_ECOM_RESPONSEINFO responseInfoObj;
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	@Autowired
	protected GAIA_SERVICES_LOGINHELPER loginHelper;
	@Autowired
	protected GAIA_SERVICES_ILOGINDAO loginDAO;
	@Autowired
	protected GAIA_SERVICES_CONFIG_HELPER configHelper;
	@Autowired
	protected GAIA_SERVICES_ICONFIG_DAO configDAO;
	@Autowired
	protected GAIA_SERVICES_PRODUCT_HELPER productHelper;
	@Autowired
	protected GAIA_SERVICES_PRODUCT_DAO productDAO;
	@Autowired
	protected GAIA_SERVICES_CUSTOMER_HELPER customerHelper;
	@Autowired
	protected GAIA_SERVICES_ICUSTOMER_DAO customerDAO;
	@Autowired
	protected GAIA_SERVICES_ORDER_HELPER orderHelper;
	@Autowired
	protected GAIA_SERVICES_IORDER_DAO orderDAO;
	
	@Autowired
	protected GAIA_SERVICES_CATEGORY_HELPER categoryHelper;
	@Autowired
	protected GAIA_SERVICES_ICATEGORY_DAO categoryDAO;
	
	@Autowired
	protected GAIA_SERVICES_COMMUNICATION_HELPER communicationHelper;
	@Autowired
	protected GAIA_SERVICES_ICOMMUNICATION_DAO communicationDAO;
	
	@Autowired
	protected GAIA_SERVICES_BANNER_HELPER bannerHelper;
	@Autowired
	protected GAIA_SERVICES_IBANNER_DAO bannerDAO;
}
