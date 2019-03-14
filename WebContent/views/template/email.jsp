<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>GAIA -EMAIL</title>
</head>
<jsp:include page="../template/menu.jsp"></jsp:include>

<script type="text/javascript" src="../static/resources/js/forms/communication/communication.js"></script>
<body onload="loademailsettings()">
<script>
$(document).ready(function(){
	$(".collapsible>li>.collapsible-body").stop(!0, !1);
	$(".collapsible>li.19").addClass("active");
	$(".collapsible>li.19>.collapsible-body").stop(!0, !1).slideDown();
	
});
</script>
         <section id="content">
				<div class="container">
				  <div class="section">
						<div id="input-fields">
							<h4 class="header">EMAIL</h4>
							
							
							
	
				<div class="row">
							
							 <div class="col s12" id="product_box">
						      <ul class="tabs">
						        <li class="tab col s3"><a class="active" href="#email_configuration"></i> Email</a></li>
						        <li class="tab col s3"><a  href="#email_template">Email Template</a></li>
						       
						      </ul>
						    </div>
							
							<div id="email_configuration" padding='10' >
							<form class="col s12" >
									<div class="row">
										<div class="input-field col s6">
										  <input id="operation" type="hidden" value="INS" />
										  <input id="emailid" type="hidden" value="${emailid }" />
										  <input id="serveruptext" type="text" value="${smsObj.serverup }" />
										  <label for="last_name">Server up</label>
										</div>
										
										
										<div class="input-field col s6">
										  <input id="smtpport" type="text" class="validate" value="${smsObj.smtpport }">
										  <label for="last_name">smtp port</label>
										</div>
										
										<div class="input-field col s6">
										  <input id="smtpemail" type="text" class="validate" value="${smsObj.smtpemail }">
										  <label for="last_name">smtp mail id</label>
										</div>
										<div class="input-field col s6">
										  <input id="username" type="text" class="validate" value="${smsObj.emailusername }">
										  <label for="last_name">username</label>
										</div>
									<div class="input-field col s6">
										  <input id="password" type="password" class="validate" value="${smsObj.emailpassword }">
										  <label for="last_name"> password</label>
										</div>
										<!-- <div class="input-field col s6" style="padding:0; margin:0;">
											<label for="input_text">Gender</label>
											<div class="input-field col s2" style="padding:0; margin:0;">
												<input name="status" type="radio" value="Active" id="active" />
												<label for="active">Male</label>
											</div>
											
											<div class="input-field col s2" style="padding:0; margin:0;">
												<input name="status" type="radio" value="Inactive" id="inactive" />
												<label for="inactive">Female</label>
											</div>
										</div> -->	
									
									<div class="row">
										<div class="col s12 center-align">
										  <button class="btn waves-effect waves-light"  type="button" onclick="saveemail()">Submit</button>
										</div>
									</div> 
									</div>
								</form>
							</div>
					</div>	
	 
 <div id="email_template" padding='5' >
				
	<div class="row">
			
		<div class="col s12">
				
				<div class="sidetempnav">			
				 
				    <c:forEach items="${emailtemplateList}" var="email" >
						<div class="lefttempdiv">		 
					     <a class="lefttempstyle" onclick="displayemailcontent('${email.msgcontent}','${email.param_desc}','${email.emailid }')">${email.emailname}<br></a>
								 
					     <input  type="hidden" id="templateidtext" value="${email.emailid }">
						 <input  type="hidden" id="paramdesctext" value="${email.param_desc }"> 
					 </div>
				
				
				    </c:forEach>
						</div> 	
						       <div class="sidetempcontent">
						               <textarea style="height:200px;padding:5px;" class="templatetextarea" row="12"  id="emailcontenttext">${emailtemplateList.get(0).getMsgcontent()} </textarea>
				                     <br>
	                           </div> 			
					
		</div> 			
					<div class="row">
			
					            <div class="col s12 center-align margin-top-10">
								  
					                <button class="btn waves-effect waves-light"  type="button" onclick="saveemailmsg()">Submit</button>
								
					            </div>
		               
		   	        </div>
			
	</div>
					</div>
				
			</div>
			</div>
			</div>
			
		
	</section>	 	
	 	 		
		 <jsp:include page="../template/footer.jsp"></jsp:include>
			 
</body>
</html>