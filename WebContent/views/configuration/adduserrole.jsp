<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>GAIA -Add Userrole</title>
</head>
<body onload="loadadduserrole()">

<jsp:include page="../template/menu.jsp"></jsp:include>
<script type="text/javascript" src="../static/resources/js/util.js"></script>
<script type="text/javascript" src="../static/resources/js/forms/login/login.js"></script>
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
							<h4 class="header">Add User role</h4>
							<div class="row">
								<div class="row margin-bottom-70">
             <div class="col-md-12">
              	<a href="userrole" class="right btn font-12" style="margin-right: 25px; margin-top: -41px;">back</a>
			</div>	
			</div>
			
										
									<div class="row">
										<div class="input-field col s6">
										
										  <input id="userrole" type="text" class="validate" value="${roleObject.roledesc }" onkeypress="return onlyAlphabets(event,this);">
										  <input id="operation" type="hidden" value="INS" />
										  <input id="roleid" type="hidden" value="${roleid }" />
										  <input id="adminflag" type="hidden" value="${roleObject.adminflag}" />
										  <label for="last_name">Role Description *</label>
										</div>
										
										
										
										<div class="col s6 margin-top-10">
											<div class="col s4">
												<p for="last_name">Is Admin User ?</p>
											</div>
											<div class="col s8">
											<div class="onoffswitch margin-top-10">
						                        <input name="onoffswitch" class="onoffswitch-checkbox"  id="myonoffswitch1" checked="" type="checkbox">
						                        <label class="onoffswitch-label" for="myonoffswitch1" style="padding-left:0px !important; height:auto;">
						                        <span class="onoffswitch-inner">
						                        <span class="onoffswitch-active"><span class="onoffswitch-switch">YES</span></span>
						                        <span class="onoffswitch-inactive"><span class="onoffswitch-switch">NO</span></span>
						                        </span>
						                        </label>
						                      	</div>
						                    </div>
											</div>
										</div>
										
										<div class="row">	
											<div class="col s6 margin-top-10">
											<div class="col s4">
												<p for="last_name">Status</p>
											</div>
											<div class="col s8">
											<div class="onoffswitch margin-top-10">
											<c:choose>
                          						<c:when test = "${roleObject.status == 1}">
                          						<input name="onoffswitch" class="onoffswitch-checkbox"  id="myonoffswitch2" checked type="checkbox">
                          						</c:when>
									   			<c:otherwise>
									   			<input name="onoffswitch" class="onoffswitch-checkbox"  id="myonoffswitch2" type="checkbox">
									   			</c:otherwise>
									      	</c:choose>
						                        
						                        <label class="onoffswitch-label" for="myonoffswitch2" style="padding-left:0px !important; height:auto;">
						                        <span class="onoffswitch-inner">
						                        <span class="onoffswitch-active"><span class="onoffswitch-switch">Active</span></span>
						                        <span class="onoffswitch-inactive"><span class="onoffswitch-switch">Inactive</span></span>
						                        </span>
						                        </label>
						                      	</div>
						                    </div>
											</div>
											<!-- <div class="col s6 margin-top-10 ">
											  <button class="btn waves-effect waves-light" onclick="saveuserrole()" >Save User Role</button>
											</div> -->
											
									</div>
									<div class="row margin-top-35">
									
									<div class="col s8">
										<table id="moduleDt">
										<thead>
											<th>Access</th>
											<th>Module Name</th>
						
										</thead>
										<tbody>
											<c:forEach items="${usermenuList }" var="usermenu">
											
											<c:choose>
									         <c:when test = "${usermenu.submenu == 0}">
									            <tr class="parentstyle">
									            	<td class="padd-left-40" >
									            	
									            	<c:choose>
										         		<c:when test = "${usermenu.menu_access == 0}">
										         			<input name="usermenu" type="checkbox" id="menuid${usermenu.moduleid }"  dataid="${usermenu.moduleid }" class="parent-${usermenu.moduleid }" />
															<label for="menuid${usermenu.moduleid }"></label>
										            	</c:when>
												        <c:otherwise>
													        <input name="usermenu" type="checkbox" id="menuid${usermenu.moduleid }" checked dataid="${usermenu.moduleid }" class="parent-${usermenu.moduleid }" />
															<label for="menuid${usermenu.moduleid }"></label>
												        </c:otherwise>
											      	</c:choose>
														
													</td>
													<td>${usermenu.modulename }</td>
													
												</tr>
									         </c:when>
									         
									         <c:otherwise>
									            <tr class="childstyle">
									            	<td class="padd-left-80">
														<c:choose>
											         		<c:when test = "${usermenu.menu_access == 0}">
											         			<input name="usermenu" type="checkbox" id="menuid${usermenu.moduleid }"  dataid="${usermenu.moduleid }" class="child-${usermenu.parentid }" />
																<label for="menuid${usermenu.moduleid }"></label>
											            	</c:when>
													        <c:otherwise>
														        <input name="usermenu" type="checkbox" id="menuid${usermenu.moduleid }" checked dataid="${usermenu.moduleid }" class="child-${usermenu.parentid }" />
																<label for="menuid${usermenu.moduleid }"></label>
													        </c:otherwise>
												      	</c:choose>
														<label for="menuid${usermenu.moduleid }"></label>
													</td>
													<td class="padd-left-80">${usermenu.modulename }</td>
													
												</tr>
									         </c:otherwise>
									      </c:choose>
											
											</c:forEach>
										</tbody>
										</table>
										</div>
									</div>
									
									<div class="row">
										<div class="col s12 center-align margin-top-10">
										  <button class="btn waves-effect waves-light" onclick="saveuserrole()" >Submit</button>
										</div>
									</div>
								
							</div>
						</div><!--inpur-fields--->
					</div><!--section--->
				</div><!---container--->
			</section>
			 <jsp:include page="../template/footer.jsp"></jsp:include>
			 <script>
				$(document).ready(function(){
						$("#moduleDt tbody tr td input[type=checkbox]").on("change",function(){
							var checked = $(this).prop('checked');
								var classname = $(this).attr('class');
								var res = classname.split("-");
								var parentname = res[0];
								if(parentname=="parent"){
									var parentid = res[1];
									$("#moduleDt tbody tr td input[class=child-"+parentid+"]").each(function(){
										if(checked){
											$(this).prop('checked', true);
											}else{
												$(this).prop('checked', false);
												}
										});
								}else if(parentname == "child"){
									var parentid = res[1];
									var childchecked = $(this).prop('checked');
									if(childchecked){
										$("#moduleDt tbody tr td input[class=parent-"+parentid+"]").prop("checked", true);
									}else{
										var mycheckedchildaarray = [];
										$("#moduleDt tbody tr td input[class=child-"+parentid+"]").each(function(){
											mycheckedchildaarray.push($(this).prop('checked'));
										});
										var totalValid = jQuery.inArray( true, mycheckedchildaarray );
										if(totalValid<0){
											$("#moduleDt tbody tr td input[class=parent-"+parentid+"]").prop("checked", false);
										}
									}
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
</body>
</html>