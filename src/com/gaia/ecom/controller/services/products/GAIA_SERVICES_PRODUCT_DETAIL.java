package com.gaia.ecom.controller.services.products;

import java.io.Serializable;

public class GAIA_SERVICES_PRODUCT_DETAIL implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1642395814631687237L;
	private String typeid;
	private String typedesc;
	private String websiteid; 
	private String websitedesc;
	
	private String categoryid;
	private String categoryname;
	private String categoryurl;
	private String categorylevel;
	private String productcount;
	private String parentid;
	
	private int intProductid;
	
	private String productid;
	private String operation;
	
	private String sku;
	private String name;
	private String gender;
	private String brand;
	private String uom;
	
	private String usage;
	private String composition;
	private String desc;
	private String descrition;
	private String status;
	private String filename;
	private String imageurl;
	private String thumbnailurl;
	private String applicationContextURL;
	private String productusage;
	private String cancelimport;
	
	public String getCancelimport() {
		return cancelimport;
	}
	public void setCancelimport(String cancelimport) {
		this.cancelimport = cancelimport;
	}
	public String getProductusage() {
		return productusage;
	}
	public void setProductusage(String productusage) {
		this.productusage = productusage;
	} 
	public String getApplicationContextURL() {
		return applicationContextURL;
	}
	public void setApplicationContextURL(String applicationContextURL) {
		this.applicationContextURL = applicationContextURL;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}
	public String getThumbnailurl() {
		return thumbnailurl;
	}
	public void setThumbnailurl(String thumbnailurl) {
		this.thumbnailurl = thumbnailurl;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	private String categoryarray;

	private String offerstartdate;
	private String offerenddate;
	private String origprice;
	private String currentprice;
	private String offerprice;
	private String stockstatus;
	private String position;
	private String image;
	private String thumbimage1;
	private String thumbimage2;
	private String thumbimage3;
	private String thumbimage4;
	private String smallimage;
	private String precatgeory;
	private String subcatgeory;
	public String getPrecatgeory() {
		return precatgeory;
	}
	public void setPrecatgeory(String precatgeory) {
		this.precatgeory = precatgeory;
	}
	public String getSubcatgeory() {
		return subcatgeory;
	}
	public void setSubcatgeory(String subcatgeory) {
		this.subcatgeory = subcatgeory;
	}
	public String getThumbimage1() {
		return thumbimage1;
	}
	public void setThumbimage1(String thumbimage1) {
		this.thumbimage1 = thumbimage1;
	}
	public String getThumbimage2() {
		return thumbimage2;
	}
	public void setThumbimage2(String thumbimage2) {
		this.thumbimage2 = thumbimage2;
	}
	public String getThumbimage3() {
		return thumbimage3;
	}
	public void setThumbimage3(String thumbimage3) {
		this.thumbimage3 = thumbimage3;
	}
	public String getThumbimage4() {
		return thumbimage4;
	}
	public void setThumbimage4(String thumbimage4) {
		this.thumbimage4 = thumbimage4;
	}
	
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public String getSmallimage() {
		return smallimage;
	}
	public void setSmallimage(String smallimage) {
		this.smallimage = smallimage;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getCategoryarray() {
		return categoryarray;
	}
	public void setCategoryarray(String categoryarray) {
		this.categoryarray = categoryarray;
	}
	
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public String getUsage() {
		return usage;
	}
	public void setUsage(String usage) {
		this.usage = usage;
	}
	public String getComposition() {
		return composition;
	}
	public void setComposition(String composition) {
		this.composition = composition;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getOfferstartdate() {
		return offerstartdate;
	}
	public void setOfferstartdate(String offerstartdate) {
		this.offerstartdate = offerstartdate;
	}
	public String getOfferenddate() {
		return offerenddate;
	}
	public void setOfferenddate(String offerenddate) {
		this.offerenddate = offerenddate;
	}
	public String getOrigprice() {
		return origprice;
	}
	public void setOrigprice(String origprice) {
		this.origprice = origprice;
	}
	public String getCurrentprice() {
		return currentprice;
	}
	public void setCurrentprice(String currentprice) {
		this.currentprice = currentprice;
	}
	public String getOfferprice() {
		return offerprice;
	}
	public void setOfferprice(String offerprice) {
		this.offerprice = offerprice;
	}
	public String getStockstatus() {
		return stockstatus;
	}
	public void setStockstatus(String stockstatus) {
		this.stockstatus = stockstatus;
	}
	
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	public String getTypedesc() {
		return typedesc;
	}
	public void setTypedesc(String typedesc) {
		this.typedesc = typedesc;
	}
	public String getWebsiteid() {
		return websiteid;
	}
	public void setWebsiteid(String websiteid) {
		this.websiteid = websiteid;
	}
	public String getWebsitedesc() {
		return websitedesc;
	}
	public void setWebsitedesc(String websitedesc) {
		this.websitedesc = websitedesc;
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
	public String getProductcount() {
		return productcount;
	}
	public void setProductcount(String productcount) {
		this.productcount = productcount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public int getIntProductid() {
		return intProductid;
	}
	public void setIntProductid(int intProductid) {
		this.intProductid = intProductid;
	}
	public String getDescrition() {
		return descrition;
	}
	public void setDescrition(String descrition) {
		this.descrition = descrition;
	}

}
