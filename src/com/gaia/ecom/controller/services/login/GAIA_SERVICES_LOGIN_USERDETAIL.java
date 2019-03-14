package com.gaia.ecom.controller.services.login;

import java.util.Map;

public class GAIA_SERVICES_LOGIN_USERDETAIL {


	private int id;
	private String username;
	private String password;
	private String email;
	private String roleid;
	private String superadminflag;
	private String loginattempts;
	private String islocked;
	
	private String action;
	private Map<String, Object> resultMap;
	private String roledesc;
	private String adminflag;
	
	private String moduleid;
	private String modulename;
	private String parentid;
	private String submenu;
	private String menu_access;
	private String modulepath;
	private String phoneno;
	private String status;
	private String icon;
	
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getModulepath() {
		return modulepath;
	}
	public void setModulepath(String modulepath) {
		this.modulepath = modulepath;
	}
	public String getMenu_access() {
		return menu_access;
	}
	public void setMenu_access(String menu_access) {
		this.menu_access = menu_access;
	}
	private String newpassword;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	
	public String getLoginattempts() {
		return loginattempts;
	}
	public void setLoginattempts(String loginattempts) {
		this.loginattempts = loginattempts;
	}
	public String getIslocked() {
		return islocked;
	}
	public void setIslocked(String islocked) {
		this.islocked = islocked;
	}
	public String getSuperadminflag() {
		return superadminflag;
	}
	public void setSuperadminflag(String superadminflag) {
		this.superadminflag = superadminflag;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Map<String, Object> getResultMap() {
		return resultMap;
	}
	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	public String getRoledesc() {
		return roledesc;
	}
	public void setRoledesc(String roledesc) {
		this.roledesc = roledesc;
	}
	public String getAdminflag() {
		return adminflag;
	}
	public void setAdminflag(String adminflag) {
		this.adminflag = adminflag;
	}
	public String getModuleid() {
		return moduleid;
	}
	public void setModuleid(String moduleid) {
		this.moduleid = moduleid;
	}
	public String getModulename() {
		return modulename;
	}
	public void setModulename(String modulename) {
		this.modulename = modulename;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getSubmenu() {
		return submenu;
	}
	public void setSubmenu(String submenu) {
		this.submenu = submenu;
	}
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	
	

}