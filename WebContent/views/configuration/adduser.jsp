<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>GAIA - Add User</title>
</head>
<jsp:include page="../template/menu.jsp"></jsp:include>

<script type="text/javascript" src="../static/resources/js/forms/login/login.js"></script>
<body onload="loadadduser()">
<script>
$(document).ready(function(){
	$(".collapsible>li>.collapsible-body").stop(!0, !1);
	$(".collapsible>li.2").addClass("active");
	$(".collapsible>li.2>.collapsible-body").stop(!0, !1).slideDown();
});
</script>

	<section id="content">
				<div class="container">
				  <div class="section">
						<div id="input-fields">
							<h4 class="header">Add User</h4>
							<div class="row">
							<div class="row margin-bottom-70">
             <div class="col-md-12">
              	<a href="gaiausers" class="right btn font-12" style="margin-right: 25px; margin-top: -41px;">back</a>
			</div>	
			</div>
								<form class="col s12" onsubmit="saveuser()">
									<div class="row">
										<div class="input-field col s6">
										  <input id="username" type="text" class="validate" value="${userObject.username }" onkeypress="return onlyAlphabets(event,this);">
										  <input id="operation" type="hidden" value="${action }" />
										  <input id="userid" type="hidden" value="${userid }" />
										  <input id="userroleid" type="hidden" value="${userObject.roleid }" />
										  <label for="last_name">Name *</label>
										</div>
										
										
										<div class="input-field col s6">
										  <input id="password" type="password" class="validate" value="${userpassword }">
										  <label for="last_name">Password *</label>
										</div>
										
										<div class="input-field col s6">
										  <input id="confirm_password" type="password" class="validate" value="${userpassword }">
										  <label for="last_name">Confirm Password *</label>
										</div>
										<div class="input-field col s6">
										  <input id="email" type="email" class="validate" value="${userObject.email }">
										  <label for="last_name">Email - Id *</label>
										</div>
										
										<div class="input-field col s6">
										  <input id="mobileno" type="number" class="validate" value="${userObject.phoneno }">
										  <label for="last_name">Phone</label>
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
										
										<div class="input-field col s6">
											<!-- <label for="category">Category</label> -->
											<select id="userrole" name="userrole" value="${userObject.roleid }">
											<option value=""> -- Select User Role *--  </option>
												<c:forEach items="${roleList }" var="role">
													<option value="${role.roleid }">${role.roledesc }</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<!-- <div class="row"> -->
										
										
									<!-- </div> -->
									<c:choose>
                          <c:when test = "${userObject.status == 1}">
									<div class="col s8">
									<div class="onoffswitch margin-top-10" >
						                        <input name="onoffswitch" class="onoffswitch-checkbox"  id="myonoffswitch1" checked="" type="checkbox">
						                        <label class="onoffswitch-label" for="myonoffswitch1" style="padding-left:0px !important; height:auto;">
						                        <span class="onoffswitch-inner">
						                        <span class="onoffswitch-active"><span class="onoffswitch-switch">Active</span></span>
						                        <span class="onoffswitch-inactive"><span class="onoffswitch-switch">Inactive</span></span>
						                        </span>
						                        </label>
						                      	</div>
									</div>
									</c:when>
									   <c:otherwise>
									   
									   <div class="col s8">
									<div class="onoffswitch margin-top-10" >
						                        <input name="onoffswitch" class="onoffswitch-checkbox"  id="myonoffswitch1"  type="checkbox">
						                        <label class="onoffswitch-label" for="myonoffswitch1" style="padding-left:0px !important; height:auto;">
						                        <span class="onoffswitch-inner">
						                        <span class="onoffswitch-active"><span class="onoffswitch-switch">Active</span></span>
						                        <span class="onoffswitch-inactive"><span class="onoffswitch-switch">Inactive</span></span>
						                        </span>
						                        </label>
						                      	</div>
									</div>
									</c:otherwise>
									      </c:choose>
									  <div class="col s6">
									
		<label for="input-file-now">Upload Image</label>
<iframe src="../uploadservice/uploadview?id=${userid }&form=user&type=1&sessionname=userimage&foldername=image" width="500px;" style="border: none" ></iframe>
 
										  </div>
									<div class="row">
										<div class="col s12 center-align">
										  <button class="btn waves-effect waves-light"  type="button" onclick="registration()">Submit</button>
										</div>
									</div> 
								</form>
							</div>
						</div><!--inpur-fields--->
					</div><!--section--->
				</div><!---container--->
			</section>
			
			<script> 
						 $(document).ready(function(){
							  
							 $("#mobileno").on("keydown", function(e){
								 if ([69, 187, 188, 189, 190].includes(e.keyCode)) {
			                          e.preventDefault();
			                          }
							 });
						 });
                           
                          </script>
                           <script language="Javascript" type="text/javascript">

        function onlyAlphabets(e, t) {
            try {
                if (window.event) {
                    var charCode = window.event.keyCode;
                }
                else if (e) {
                    var charCode = e.which;
                }
                else { return true; }
                if ((charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123))
                    return true;
                else
                    return false;
            }
            catch (err) {
                alert(err.Description);
            }
        }

    </script>
                          
                          <style>
			 		
			    input[type=number]::-webkit-inner-spin-button, 
                input[type=number]::-webkit-outer-spin-button { 
                                   
                           -webkit-appearance: none;
                           -moz-appearance: none;
                            appearance: none;
                            margin: 0; 
                         }
			 </style>
			
			 <jsp:include page="../template/footer.jsp"></jsp:include>
</body>
</html>