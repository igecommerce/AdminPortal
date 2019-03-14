package com.gaia.ecom.controller.services.login;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.gaia.beans.GAIA_ECOM_BEANS;
import com.gaia.conn.util.GAIA_CONN_UTIL_IMPL;
import com.gaia.ecom.controller.response.GAIA_ECOM_RESPONSEINFO;
import com.gaia.ecom.controller.services.orders.GAIA_SERVICES_ORDER_DETAIL;
import com.gaia.util.GAIA_UTILS;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@Service
public class GAIA_SERVICES_LOGINDAO_IMPL extends GAIA_ECOM_BEANS implements
		GAIA_SERVICES_ILOGINDAO {

	private static Logger log = Logger.getLogger(GAIA_SERVICES_LOGINDAO_IMPL.class);
	
	private static final Class List = null;
	@Override
	public GAIA_ECOM_RESPONSEINFO validategaiasignin(String signinData,
			HttpServletRequest httpRequest) throws Exception {
		// TODO Auto-generated method stub
		GAIA_SERVICES_LOGIN_USERDETAIL request = (GAIA_SERVICES_LOGIN_USERDETAIL) GAIA_UTILS
				.convertJSONtooOBJECT(signinData,
						GAIA_SERVICES_LOGIN_USERDETAIL.class);
		Object[] inputValue = new Object[] { request.getUsername(),
				request.getPassword() };
		log.info("inputValue   " + inputValue);
		
		List userList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				env.getRequiredProperty("validatelogin"), inputValue,
				GAIA_SERVICES_LOGIN_USERDETAIL.class);
		List usernameList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				env.getRequiredProperty("validateusernamequery"), inputValue,
				GAIA_SERVICES_LOGIN_USERDETAIL.class);
		List userpasswordList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				env.getRequiredProperty("validatepasswordquery"), inputValue,
				GAIA_SERVICES_LOGIN_USERDETAIL.class);
		List usernameandpasswordList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				env.getRequiredProperty("validateusernameandpassworwrong"), inputValue,
				GAIA_SERVICES_LOGIN_USERDETAIL.class);
		
		
		log.info("userList   " + userList);

		if (userList != null && userList.size() > 0) {
			HttpSession session = httpRequest.getSession();
			GAIA_SERVICES_LOGIN_USERDETAIL userObject = (GAIA_SERVICES_LOGIN_USERDETAIL) userList
					.get(0);
			session.setAttribute("userid", userObject.getId());
			session.setAttribute("loginuserid", userObject.getId());
			session.setAttribute("usertypeid", userObject.getRoleid());
			session.setAttribute("username", userObject.getUsername());
			session.setAttribute("roledesc", userObject.getRoledesc());
			
			
			List mainMenuList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("mainmenuquery"), new Object[]{session.getAttribute("usertypeid")},
					GAIA_SERVICES_LOGIN_USERDETAIL.class);
			List subMenuIds = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("submenuids"), new Object[]{}, GAIA_SERVICES_LOGIN_USERDETAIL.class);
			Iterator subMenuIdsITR = subMenuIds.iterator();
			Map<String, Object> subMenuMap = new HashMap<String, Object>();
			while(subMenuIdsITR.hasNext())
			{
				GAIA_SERVICES_LOGIN_USERDETAIL subMenuIdObj = (GAIA_SERVICES_LOGIN_USERDETAIL) subMenuIdsITR.next();
				log.info("SubMenu Inputs are   "+userObject.getRoleid()+"   "+subMenuIdObj.getParentid());
				List subMenuList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate, env.getRequiredProperty("submenuquery"), new Object[]{userObject.getRoleid(),
					subMenuIdObj.getParentid()},
						GAIA_SERVICES_LOGIN_USERDETAIL.class);
				subMenuMap.put(subMenuIdObj.getParentid(), subMenuList);
				log.info(subMenuMap);
				
			}
			session.setAttribute("subMenuMap", subMenuMap);
			session.setAttribute("mainMenuList", mainMenuList);
			
			responseInfoObj.setGaiaresponse(userList);
			return responseInfoObj;
		}else{
		if(usernameList != null && usernameList.size() > 0) {
		
			
			HttpSession session = httpRequest.getSession();
			
			responseInfoObj.setGaiaresponse("Invalid Username");

			return responseInfoObj;
		}
		else if(userpasswordList != null && userpasswordList.size() > 0) {
			
			HttpSession session = httpRequest.getSession();
			
			responseInfoObj.setGaiaresponse("Invalid Password");

			return responseInfoObj;
		}
		
     else if(usernameandpasswordList != null && usernameandpasswordList.size() > 0) {
			
			HttpSession session = httpRequest.getSession();
			
			responseInfoObj.setGaiaresponse("Invalid Username and Password");

			return responseInfoObj;
		}
		
		}
	
		return responseInfoObj;

		
	}


	@Override
	public GAIA_ECOM_RESPONSEINFO changepassword(String passwordData,
			HttpServletRequest httpRequest) throws Exception {
		// TODO Auto-generated method stub
		GAIA_SERVICES_LOGIN_USERDETAIL request = (GAIA_SERVICES_LOGIN_USERDETAIL) GAIA_UTILS
				.convertJSONtooOBJECT(passwordData,
						GAIA_SERVICES_LOGIN_USERDETAIL.class);
		log.info("request   " + request.getUsername());
		String key = env.getRequiredProperty("gaia_key");
		log.info("key   " + key);
		HttpSession session = httpRequest.getSession();
		
		LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
		inParamMap.put("P_USERID", session.getAttribute("userid"));
		inParamMap.put("P_USERNAME", request.getUsername());
		/*inParamMap.put(
				"P_PASSWORD",
				GAIA_UTILS.encrypt(request.getPassword(),
						env.getRequiredProperty("gaia_key")));
		inParamMap.put(
				"P_OLD_PASSWORD",
				GAIA_UTILS.encrypt(request.getPassword(),
						env.getRequiredProperty("gaia_key")));
		inParamMap.put(
				"P_NEW_PASSWORD",
				GAIA_UTILS.encrypt(request.getNewpassword(),
						env.getRequiredProperty("gaia_key")));*/
		
		inParamMap.put(
				"P_PASSWORD",request.getPassword());
		inParamMap.put(
				"P_OLD_PASSWORD",request.getPassword());
		inParamMap.put(
				"P_NEW_PASSWORD",request.getNewpassword());
		
		inParamMap.put("P_PHONENO", "0");
		inParamMap.put("P_STATUS", "0");
		inParamMap.put("P_EMAIL", request.getEmail());
		inParamMap.put("P_ROLE", request.getRoleid());
		inParamMap.put("P_LOGIN_ATTEMPTS", request.getLoginattempts());
		inParamMap.put("P_LOCKED", "0");
		inParamMap.put("P_IMAGEPATH","");
		inParamMap.put("P_ACTION", request.getAction());

		Map<String, Object> resultMap = GAIA_CONN_UTIL_IMPL.executeProcedure(
				jdbcTemplate, "P_GAIA_LOGINMODULE_OPRNS", inParamMap);
		log.info(resultMap);

		responseInfoObj.setGaiaresponse(resultMap);
		log.info("result Flag   "+resultMap.get("o_out_flag"));
		
		if(resultMap != null && resultMap.get("o_out_flag") != null)
		{
			if(((String)(resultMap.get("o_out_flag"))).equalsIgnoreCase("S"))
			{
				session.invalidate();
			}
		}
		return responseInfoObj;
	}


	@Override
	public GAIA_ECOM_RESPONSEINFO resetpassword(String passwordData,
			HttpServletRequest httpRequest) throws Exception {
		// TODO Auto-generated method stub
		GAIA_SERVICES_LOGIN_USERDETAIL request = (GAIA_SERVICES_LOGIN_USERDETAIL) GAIA_UTILS
				.convertJSONtooOBJECT(passwordData,
						GAIA_SERVICES_LOGIN_USERDETAIL.class);
		log.info("request   " + request.getUsername());
		
		String key = env.getRequiredProperty("gaia_key");
		log.info("key   " + key);
		Object[] inputValue = new Object[]{ request.getUsername() };
				
		List userList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				env.getRequiredProperty("mailforforgetpassword"), inputValue,
				GAIA_SERVICES_LOGIN_USERDETAIL.class);
		String mailid=null;
		if (userList != null && userList.size() > 0) {
			HttpSession session = httpRequest.getSession();
			GAIA_SERVICES_LOGIN_USERDETAIL userObject = (GAIA_SERVICES_LOGIN_USERDETAIL) userList
					.get(0);
			mailid =userObject.getEmail();
		}
		
		String reset_pswd = GAIA_UTILS.randomstring();
		log.info("mailid"+mailid);
		HttpSession session = httpRequest.getSession();
		LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
		inParamMap.put("P_USERID", session.getAttribute("userid"));
		inParamMap.put("P_USERNAME", request.getUsername());
		inParamMap.put("P_EMAIL", "");
		/*inParamMap.put(
				"P_PASSWORD",
				GAIA_UTILS.encrypt(request.getPassword(),
						env.getRequiredProperty("gaia_key")));
		inParamMap.put(
				"P_OLD_PASSWORD",
				GAIA_UTILS.encrypt(request.getPassword(),
						env.getRequiredProperty("gaia_key")));
		inParamMap.put(
				"P_NEW_PASSWORD",
				GAIA_UTILS.encrypt(request.getNewpassword(),
						env.getRequiredProperty("gaia_key")));*/
		
		inParamMap.put(
				"P_PASSWORD",reset_pswd);
		inParamMap.put(
				"P_OLD_PASSWORD",request.getPassword());
		inParamMap.put(
				"P_NEW_PASSWORD",request.getNewpassword());
		inParamMap.put("P_STATUS", request.getStatus());
		inParamMap.put("P_PHONENO", request.getPhoneno());
		
		inParamMap.put("P_ROLE", request.getRoleid());
		inParamMap.put("P_LOGIN_ATTEMPTS", request.getLoginattempts());
		inParamMap.put("P_LOCKED", "0");
		inParamMap.put("P_IMAGEPATH","");
		inParamMap.put("P_ACTION", request.getAction());

		Map<String, Object> resultMap = GAIA_CONN_UTIL_IMPL.executeProcedure(
				jdbcTemplate, "P_GAIA_LOGINMODULE_OPRNS", inParamMap);
		log.info(resultMap);

		sendmail(mailid,reset_pswd);
		
		responseInfoObj.setGaiaresponse(resultMap);
		log.info("result Flag   "+resultMap.get("o_out_flag"));
		
		return responseInfoObj;
	}
	
	private String sendmail(String mailid, String reset_pswd) {
		// TODO Auto-generated method stub
		
		log.info("welcome to send mail");
		try{
			  
			@SuppressWarnings("unchecked")
		/*	
			Object[] inputValue = new Object[] {};
			List emailSettingsList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
					env.getRequiredProperty("allorders"), inputValue,
					GAIA_SERVICES_ORDER_DETAIL.class);;*/
			
			final StringBuffer stringBuffer = new StringBuffer();
			String EMAILresult = "";
			//log.info("Before List history records size "+emailSettingsList.size());
			/*if( emailSettingsList.size() > 0)
			{
			*/	log.info("After List");
				
				String templateSubject = "", templateMessage="", emailid="";
					
					templateSubject = "Forgot Password";
					templateMessage = "Dear Customer your password Resetted  successfully Please Re_login.Your password is "+reset_pswd;
					emailid = mailid;
				
				//Map<String, Object> settingsMap = (Map<String, Object>) emailSettingsList.get(0);
				
				final String from_mail = "schedulertestjbintech@gmail.com";
				final String from_mail_password = "@admin123";
				 
				log.info("Email Settings Values are   "+from_mail+"   "+from_mail_password +"  "+emailid);
				
				 //Get properties object    
			      Properties props = new Properties();    
			      props.put("mail.smtp.host", "smtp.gmail.com");    
			      props.put("mail.smtp.socketFactory.port", "465");    
			      props.put("mail.smtp.socketFactory.class",    
			                "javax.net.ssl.SSLSocketFactory");    
			      props.put("mail.smtp.auth", "true");    
			      props.put("mail.smtp.port", "465");  
			      //get Session   
			      Session session = Session.getDefaultInstance(props,    
			       new javax.mail.Authenticator() {    
			       protected PasswordAuthentication getPasswordAuthentication() {    
			       return new PasswordAuthentication(from_mail,from_mail_password);  
			       }    
			      });    
			      //compose message   
			      int status = 0;
			      try {    
			       MimeMessage message = new MimeMessage(session);    
			       message.addRecipient(Message.RecipientType.TO,new InternetAddress(emailid));    
			       message.setSubject(templateSubject);    
			       //message.setText(msg);   
			       message.setContent(templateMessage, "text/html; charset=utf-8");
			       //send message  
			       Transport.send(message);    
			       status = 1; 
			      } catch (Exception e) {
			    	  status = 0;
			    	  log.info("error    "+e);
			      }  
				log.info("status"+status);
			
			/*}*/
			
			return EMAILresult;
			
		}catch (Exception e) {
			log.info("Error SMS "+e);
			e.printStackTrace();
			return "Error "+e;
		}
		
	}


	@Override
	public GAIA_ECOM_RESPONSEINFO registeruser(String regnValue,HttpServletRequest httpRequest)
			throws IllegalStateException, Exception {
		// TODO Auto-generated method stub
		HttpSession session = httpRequest.getSession();
		String imagePath = "";
		GAIA_SERVICES_LOGIN_USERDETAIL request = (GAIA_SERVICES_LOGIN_USERDETAIL) GAIA_UTILS
				.convertJSONtooOBJECT(regnValue,
						GAIA_SERVICES_LOGIN_USERDETAIL.class);
		log.info("request   " + request.getUsername());

		LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
		
		inParamMap.put("P_USERID", request.getId());
		inParamMap.put("P_USERNAME", request.getUsername());
		/*inParamMap.put(
				"P_PASSWORD",
				GAIA_UTILS.encrypt(request.getPassword(),
						env.getRequiredProperty("gaia_key")));*/
		inParamMap.put(
				"P_PASSWORD",request.getPassword());
		inParamMap.put(
				"P_OLD_PASSWORD",request.getPassword());
		inParamMap.put(
				"P_NEW_PASSWORD",request.getPassword());
		/*inParamMap.put(
				"P_OLD_PASSWORD",
				GAIA_UTILS.encrypt(request.getPassword(),
						env.getRequiredProperty("gaia_key")));
		inParamMap.put(
				"P_NEW_PASSWORD",
				GAIA_UTILS.encrypt(request.getPassword(),
						env.getRequiredProperty("gaia_key")));*/

		inParamMap.put("P_EMAIL", request.getEmail());
		inParamMap.put("P_PHONENO", request.getPhoneno());
		inParamMap.put("P_STATUS", request.getStatus());
		inParamMap.put("P_ROLE", request.getRoleid());
		inParamMap.put("P_LOGIN_ATTEMPTS", request.getLoginattempts());
		inParamMap.put("P_LOCKED", "0");
		inParamMap.put("P_IMAGEPATH",imagePath);
		inParamMap.put("P_ACTION", request.getAction());

		Map<String, Object> resultMap = GAIA_CONN_UTIL_IMPL.executeProcedure(
				jdbcTemplate, "P_GAIA_LOGINMODULE_OPRNS", inParamMap);
	
		
		
		
		if(resultMap.get("o_out_flag") != null && resultMap.get("o_out_flag").equals("S") && session.getAttribute("userimage") != null)
		{
			
			log.info("welcome to upload");
			String oldimagefilepath = (String) session.getAttribute("userimage"); 
			
			log.info("Uploaded Filenameuser  "+oldimagefilepath);
			int userid = (int) resultMap.get("o_out_generated_id");
			log.info("idforupload   "+userid);
			if(userid > 0)
			{
				
				/*Image File Move Start*/
				String newpath = GAIA_UTILS.getserverfilepath(httpRequest);
				String modulename = GAIA_UTILS.getmodulename("user", 1);
				newpath = newpath.concat(File.separator).concat(modulename);
				File uploadPath = new File(newpath);
				if(!uploadPath.exists())
				{
					uploadPath.mkdirs();
				}
				newpath = newpath.concat(File.separator).concat(userid+".jpg");
				log.info("newpath   "+newpath);
				
				imagePath = GAIA_UTILS.filepath(newpath);
				log.info("imagepath   "+imagePath);
				/*End*/
				
					/*if(request.getAction()  != null  && request.getAction().equals("UPD") ){*/
				
				if(oldimagefilepath!=null){
						File oldfile = new File(oldimagefilepath);
						if(oldfile.exists()){
		        	  		File file = new File(newpath);
							if(file.exists())
							{
								Path fileToDeletePath = Paths.get(newpath);
								Files.delete(fileToDeletePath);
								/*log.info("File deleted");*/
							}
						}
				
				/*}*/
						/* FileUtils.copyFileToDirectory(new File(oldimagefilepath),
		                        new File(newpath));*/
						
				new File(oldimagefilepath).renameTo(new File(newpath));
				} 
				
				inParamMap = new LinkedHashMap<String, Object>();
				inParamMap.put("P_USERID", request.getId());
				inParamMap.put("P_USERNAME", request.getUsername());
				/*inParamMap.put(
						"P_PASSWORD",
						GAIA_UTILS.encrypt(request.getPassword(),
								env.getRequiredProperty("gaia_key")));*/
				inParamMap.put(
						"P_PASSWORD",request.getPassword());
				inParamMap.put(
						"P_OLD_PASSWORD",request.getPassword());
				inParamMap.put(
						"P_NEW_PASSWORD",request.getPassword());
				/*inParamMap.put(
						"P_OLD_PASSWORD",
						GAIA_UTILS.encrypt(request.getPassword(),
								env.getRequiredProperty("gaia_key")));
				inParamMap.put(
						"P_NEW_PASSWORD",
						GAIA_UTILS.encrypt(request.getPassword(),
								env.getRequiredProperty("gaia_key")));*/

				inParamMap.put("P_EMAIL", request.getEmail());
				inParamMap.put("P_PHONENO", request.getPhoneno());
				inParamMap.put("P_STATUS", request.getStatus());
				inParamMap.put("P_ROLE", request.getRoleid());
				inParamMap.put("P_LOGIN_ATTEMPTS", request.getLoginattempts());
				inParamMap.put("P_LOCKED", "0");
				inParamMap.put("P_IMAGEPATH",imagePath);
				inParamMap.put("P_ACTION", "UPD_IMGS_PATH");
				
				
				Map<String, Object> uploadResultMap = GAIA_CONN_UTIL_IMPL.executeProcedure(jdbcTemplate,"P_GAIA_LOGINMODULE_OPRNS", inParamMap);
				log.info("uploadResultMap   "+uploadResultMap);
				
				session.setAttribute("userimage","");
				}
			
			
			
		}
		responseInfoObj.setGaiaresponse(resultMap);
		log.info("flag  "+responseInfoObj);
		return responseInfoObj;
	}


	@Override
	public GAIA_ECOM_RESPONSEINFO userroles() throws Exception {
		// TODO Auto-generated method stub
		Object[] inputValue = new Object[] {};
		log.info("inputValue   " + inputValue);
		List roleList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				env.getRequiredProperty("allactiveandinactiveuserroles"), inputValue,
				GAIA_SERVICES_LOGIN_USERDETAIL.class);
		log.info("roleList   " + roleList);

		
		
		responseInfoObj.setGaiaresponse(roleList);

		return responseInfoObj;
	}

	@Override
	public GAIA_ECOM_RESPONSEINFO userlist() throws Exception {
		// TODO Auto-generated method stub
		Object[] inputValue = new Object[] {};
		log.info("inputValue   " + inputValue);
		List userList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				env.getRequiredProperty("allactiveandinactiveusers"), inputValue,
				GAIA_SERVICES_LOGIN_USERDETAIL.class);
		log.info("userList   " + userList);

		responseInfoObj.setGaiaresponse(userList);

		return responseInfoObj;
	}

	@Override
	public GAIA_ECOM_RESPONSEINFO saveuserrole(String roleData)
			throws Exception {
		// TODO Auto-generated method stub
		GAIA_SERVICES_USERROLEDATA request = (GAIA_SERVICES_USERROLEDATA) GAIA_UTILS
				.convertJSONtooOBJECT(roleData,
						GAIA_SERVICES_USERROLEDATA.class);
		log.info("request " + request.getRoleid());

		LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
		inParamMap.put("P_USERROLEID", request.getRoleid());
		inParamMap.put("P_USERROLE_NAME", request.getUserrole());
		inParamMap.put("P_ADMIN_FLAG", request.getIsadmin());
		inParamMap.put("P_STATUS", request.getStatus());
		inParamMap.put("P_ACTION", request.getOperation());

		log.info(inParamMap);

		Map<String, Object> resultMap = GAIA_CONN_UTIL_IMPL.executeProcedure(
				jdbcTemplate, "P_GAIA_USERROLE", inParamMap);
		log.info(resultMap);

		responseInfoObj.setGaiaresponse(resultMap);

		return responseInfoObj;
	}

	@Override
	public GAIA_ECOM_RESPONSEINFO saveusermenus(String roleData)
			throws Exception {		
			try
			{
			log.info(roleData);
			final List<GAIA_SERVICES_USERROLEDATA> userroleList = convertJSONArraytoList(roleData,"com.gaia.ecom.controller.services.login.GAIA_SERVICES_USERROLEDATA");
			
			Map<String, Object> resultMap = new HashMap<String, Object>();
			
			Iterator<GAIA_SERVICES_USERROLEDATA> roleItr = userroleList.iterator();
			
			String usertypeid = "0";
			while(roleItr.hasNext())
			{
				GAIA_SERVICES_USERROLEDATA request = roleItr.next();
				usertypeid = request.getUsertype();
			}
			jdbcTemplate.execute("DELETE FROM admin_user_menu WHERE user_type_id = '"+usertypeid+"'");
			
			try
			{
				
				jdbcTemplate.batchUpdate("{call P_GAIA_USER_ACCESS( ?,?,?,?,?)}",
					new BatchPreparedStatementSetter() {					
					public int getBatchSize()
					{
						return userroleList.size();
					}
		
				public void setValues(PreparedStatement ps, int i) throws SQLException 
				{
					GAIA_SERVICES_USERROLEDATA item = userroleList.get(i);
					log.info(item.getUsermenusid()+" "+ item.getUsertype());
					ps.setString(1, item.getUsermenusid());
					ps.setString(2, item.getUsertype());
					ps.setString(3, item.getModuleid());
					ps.setString(4, item.getMenuaccess());
					ps.setString(5, item.getOperation());
				}
			});
			}
			catch( Exception e )
			{
				e.printStackTrace();
				throw e;
			}
			
			/*Iterator<GAIA_SERVICES_USERROLEDATA> roleItr = userroleList.iterator();
			
			while(roleItr.hasNext())
			{
				GAIA_SERVICES_USERROLEDATA request = roleItr.next();
				
				
				LinkedHashMap<String, Object> inParamMap = new LinkedHashMap<String, Object>();
				inParamMap.put("P_USERMENU_SID", request.getUsermenusid());
				inParamMap.put("P_USERTYPE_ID", request.getUsertype());
				inParamMap.put("P_USER_ID", request.getUsertype());
				inParamMap.put("P_MODULE_ID", request.getModuleid());
				inParamMap.put("P_MENU_ACCESS", request.getMenuaccess());
				inParamMap.put("P_OPRN", request.getOperation());

				log.info(inParamMap);

				resultMap = GAIA_CONN_UTIL_IMPL.executeProcedure(jdbcTemplate, "P_GAIA_USER_ACCESS", inParamMap);
				log.info(resultMap);

			}
			*/
			resultMap.put("id", 1);
			resultMap.put("o_out_msg", "Role Added and Role Access Saved Successfully");
			responseInfoObj.setGaiaresponse(resultMap);
			
			}
			catch(Exception e)
			{
				e.printStackTrace();
				
			}
			
			return responseInfoObj;
			
	}

	@Override
	public GAIA_ECOM_RESPONSEINFO getuserdetails(String userid)
			throws Exception {
		// TODO Auto-generated method stub
		Object[] inputValue = new Object[] {};
		String query = "select * from `admin_user` where id = '" + userid + "'";
		List getuserList = GAIA_CONN_UTIL_IMPL.getQueryResult(jdbcTemplate,
				query, inputValue, GAIA_SERVICES_LOGIN_USERDETAIL.class);
		log.info("userList   "+getuserList);

		responseInfoObj.setGaiaresponse(getuserList);

		return responseInfoObj;
	}
	public static <T> List<T> convertJSONArraytoList(String jsonArrayString,String className) throws JSONException, ClassNotFoundException
	{
		List<T> list = new ArrayList<T>();
		JSONArray jsonArr = new JSONArray(jsonArrayString);
		for (int i = 0; i < jsonArr.length(); i++) {
			JSONObject jsonObj = jsonArr.getJSONObject(i);
			T item = (T) GAIA_UTILS.convertJSONtooOBJECT(jsonObj.toString(), className);
			list.add(item);
		}
		return list;
	}

}
