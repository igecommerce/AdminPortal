<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>GAIA -SMS</title>
</head>


<script type="text/javascript" src="../static/resources/js/forms/communication/communication.js"></script>
<body onload="loadsmssettings()">

<jsp:include page="../template/menu.jsp"></jsp:include>
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
							<h4 class="header">SMS</h4>
							<div class="row">
							
							 <div class="col s12 " id="product_box">
						      <ul class="tabs">
						        <li class="tab col s3"><a class="active" href="#sms_configuration"></i> SMS</a></li>
						        <li class="tab col s3"><a  href="#sms_template">SMS Template</a></li>
						       
						      </ul>
						    </div>
							  <div id="sms_configuration" class="col s12 padding-10">
							  
							  <form class="col s12" onsubmit="saveuser()">
									<div class="row">
										<div class="input-field col s6">
										  <input id="smskey" type="text" class="validate" value="${smsemailObj.smskey }">
										  <input id="operation" type="hidden" value="INS" />
										  <input id="smsid" type="hidden" value="${userid }" />
										 

										  <label for="last_name">Enter Key</label>
										</div>
										
										
										<div class="input-field col s6">
										  <input id="smsusername" type="text" class="validate" value="${smsemailObj.smsusername }">
										  <label for="last_name">Username</label>
										</div>
										
										<div class="input-field col s6">
										  <input id="password" type="password" class="validate" value="${smsemailObj.smspassword }">
										  <label for="last_name">Password</label>
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
										  <button class="btn waves-effect waves-light"  type="button" onclick="savesms()">Submit</button>
										</div>
									</div> 
									</div>
								</form>
							  
							  
							  </div>
						
		      <div id="sms_template" padding='5' >
				
					
						<div class="row">
						<div class="col s12">
							 <div class="col s12">
							 	<div class="sidetempnav">
								 <c:forEach items="${smstemplateList}" var="sms" >
									 <div class="lefttempdiv">
									 <a class="lefttempstyle" onclick="displaymsgcontent('${sms.msgcontent}','${sms.description}','${sms.tempid }')">${sms.tempname}</a>
									  <input  type="hidden" id="templateidtext" value="${sms.tempid }">
									  <input  type="hidden" id="paramdesctext" value="${sms.description }">
									 </div> 
									 </c:forEach>
									 </div>
									 <div class="sidetempcontent">
									 <textarea style="height:200px;padding:5px;" class="templatetextarea" row="12" id="msgcontenttext">${smstemplateList.get(0).getMsgcontent()} </textarea>
									 </div>
									 
							 </div>
							      
										<div class="row">
										<div class="col s12 center-align margin-top-10">
										  <button class="btn waves-effect waves-light"  type="button" onclick="savemsgcontent()">Submit</button>
										</div>
									</div> 	      
	           </div>
						  
					    </div>
					     </div>
							</div><!--input-fields--->
					</div><!--section--->
							
				</div><!---container--->
					</div>		
			</section>
			 <jsp:include page="../template/footer.jsp"></jsp:include>
</body>
</html>