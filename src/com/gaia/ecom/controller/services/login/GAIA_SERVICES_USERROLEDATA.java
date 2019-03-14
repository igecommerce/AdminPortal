package com.gaia.ecom.controller.services.login;

public class GAIA_SERVICES_USERROLEDATA {
	private String userrole;
	private String isadmin;
	private String roleid;
	private String operation;
	
	private String usermenusid;
	private String usertype;
	private String moduleid;
	private String menuaccess;
	private String status;
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUsertype() {
		return usertype;
	}
	public String getUsermenusid() {
		return usermenusid;
	}
	public void setUsermenusid(String usermenusid) {
		this.usermenusid = usermenusid;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getModuleid() {
		return moduleid;
	}
	public void setModuleid(String moduleid) {
		this.moduleid = moduleid;
	}
	public String getMenuaccess() {
		return menuaccess;
	}
	public void setMenuaccess(String menuaccess) {
		this.menuaccess = menuaccess;
	}
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getUserrole() {
		return userrole;
	}
	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}
	public String getIsadmin() {
		return isadmin;
	}
	public void setIsadmin(String isadmin) {
		this.isadmin = isadmin;
	}
}