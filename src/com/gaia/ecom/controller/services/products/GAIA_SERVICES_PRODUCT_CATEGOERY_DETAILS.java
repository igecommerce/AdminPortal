package com.gaia.ecom.controller.services.products;

import java.io.Serializable;

public class GAIA_SERVICES_PRODUCT_CATEGOERY_DETAILS implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2846761941743310719L;
	private String categoryid;
	private String categoryname;
	private String categoryurl;
	private String categorylevel;
	private String precategoryid;
	private String status;
	private String uomname;
	private String displayorder;
	private String action;
	private String productcount;
	private String uomid;
	private String path;
	private String bannerid;
	private String bannername;
	private String bannerurl;
	private String bannerposition;
	private String bannerlayout;
	private String bannertype;
	private String mincartvalue;
	
	public String getMincartvalue() {
		return mincartvalue;
	}

	public void setMincartvalue(String mincartvalue) {
		this.mincartvalue = mincartvalue;
	}

	public String getMincartid() {
		return mincartid;
	}

	public void setMincartid(String mincartid) {
		this.mincartid = mincartid;
	}
	private String mincartid;
	
	public String getTaxid() {
		return taxid;
	}

	public void setTaxid(String taxid) {
		this.taxid = taxid;
	}

	public String getShippingid() {
		return shippingid;
	}

	public void setShippingid(String shippingid) {
		this.shippingid = shippingid;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
	private String taxid;
	private String shippingid;
	private String label;
	private String origin;
	private String percentage;
	private String amount;
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getUomid() {
		return uomid;
	}

	public void setUomid(String uomid) {
		this.uomid = uomid;
	}

	public String getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public String getCategoryurl() {
		return categoryurl;
	}

	public void setCategoryurl(String categoryurl) {
		this.categoryurl = categoryurl;
	}
	
	public String getCategorylevel() {
		return categorylevel;
	}

	public void setCategorylevel(String categorylevel) {
		this.categorylevel = categorylevel;
	}

	public String getPrecategoryid() {
		return precategoryid;
	}

	public void setPrecategoryid(String precategoryid) {
		this.precategoryid = precategoryid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDisplayorder() {
		return displayorder;
	}

	public void setDisplayorder(String displayorder) {
		this.displayorder = displayorder;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getProductcount() {
		return productcount;
	}

	public void setProductcount(String productcount) {
		this.productcount = productcount;
	}
	private String brandid;
	private String brandname;
	private String oprn;
	public String getBrandid() {
		return brandid;
	}
	public void setBrandid(String brandid) {
		this.brandid = brandid;
	}
	public String getBrandname() {
		return brandname;
	}
	public void setBrandname(String brandname) {
		this.brandname = brandname;
	}
	public String getOprn() {
		return oprn;
	}
	public String getBannerid() {
		return bannerid;
	}

	public void setBannerid(String bannerid) {
		this.bannerid = bannerid;
	}

	public String getBannername() {
		return bannername;
	}

	public void setBannername(String bannername) {
		this.bannername = bannername;
	}

	public String getBannerurl() {
		return bannerurl;
	}

	public void setBannerurl(String bannerurl) {
		this.bannerurl = bannerurl;
	}

	public String getBannerposition() {
		return bannerposition;
	}

	public void setBannerposition(String bannerposition) {
		this.bannerposition = bannerposition;
	}

	public String getBannerlayout() {
		return bannerlayout;
	}

	public void setBannerlayout(String bannerlayout) {
		this.bannerlayout = bannerlayout;
	}

	public String getBannertype() {
		return bannertype;
	}

	public void setBannertype(String bannertype) {
		this.bannertype = bannertype;
	}

	public void setOprn(String oprn) {
		this.oprn = oprn;
	}
	

	public String getUomname() {
		return uomname;
	}

	public void setUomname(String uomname) {
		this.uomname = uomname;
	}
	/*customer*/
	private String email;
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}

	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private String mobile;
	private String username;
	private String PASSWORD;
	private String street;
	private String country;
	private String region;
	private String areaname;
	private String postcode;
	private String customerid;
	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	

}
