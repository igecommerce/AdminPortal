package com.gaia.ecom.controller.services.products;

public class GAIA_SERVICES_PRODUCT_IMPORT_DETAILS {
	private String uploadstatus;
	private String uploadstatusdesc;
	
	private String errormsg;
	private String recindex;
	private String productid;
	private String productsku;
	
	public String getProductsku() {
		return productsku;
	}
	public void setProductsku(String productsku) {
		this.productsku = productsku;
	}
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getRecindex() {
		return recindex;
	}
	public void setRecindex(String recindex) {
		this.recindex = recindex;
	}
	public String getUploadstatus() {
		return uploadstatus;
	}
	public void setUploadstatus(String uploadstatus) {
		this.uploadstatus = uploadstatus;
	}
	public String getErrormsg() {
		return errormsg;
	}
	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}
	public String getUploadstatusdesc() {
		return uploadstatusdesc;
	}
	public void setUploadstatusdesc(String uploadstatusdesc) {
		this.uploadstatusdesc = uploadstatusdesc;
	}
}
