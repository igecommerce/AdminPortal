package com.gaia.ecom.controller.services.category;

import java.io.Serializable;

public class GAIA_SERVICES_CATEGORY_DETAIL_HIERARCHY implements Serializable  {
	
	private static final long serialVersionUID = 2846761941743310719L;
	
	private String categoryid;
	private String categoryname;
	private String categorypageurl;
	private String categoryimageurl;
	private String categorythumbnailurl;
	private String position;

	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
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
	
	public String getCategorypageurl() {
		return categorypageurl;
	}
	public void setCategorypageurl(String categorypageurl) {
		this.categorypageurl = categorypageurl;
	}
	public String getCategoryimageurl() {
		return categoryimageurl;
	}
	public void setCategoryimageurl(String categoryimageurl) {
		this.categoryimageurl = categoryimageurl;
	}
	public String getCategorythumbnailurl() {
		return categorythumbnailurl;
	}
	public void setCategorythumbnailurl(String categorythumbnailurl) {
		this.categorythumbnailurl = categorythumbnailurl;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
