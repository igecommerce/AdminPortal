<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>GAIA - Add Uom</title>
</head>
<jsp:include page="../template/menu.jsp"></jsp:include>

<script type="text/javascript" src="../static/resources/js/forms/config/product.js"></script>
<body onload="loadadduom()">
<script>
$(document).ready(function(){
	$(".collapsible>li>.collapsible-body").stop(!0, !1);
	$(".collapsible>li.9").addClass("active");
	$(".collapsible>li.9>.collapsible-body").stop(!0, !1).slideDown();
	
});
</script>
	<section id="content">
				<div class="container">
				  <div class="section">
						<div id="input-fields">
							<h4 class="header">Add Uom</h4>
							<div class="row">
								<div class="row margin-bottom-70">
             <div class="col-md-12">
              	<a href="uom" class="right btn font-12" style="margin-right: 25px; margin-top: -41px;">back</a>
			</div>	
			</div>
									<div class="row">
										<div class="input-field col s6">
										  <input id="uomname" type="text" class="validate" value="${uomObject.uomname }">
										  <input id="operation" type="hidden" value="INS" />
										<input id="uomid" type="hidden" value="${uomid }" />
										  
										  <label for="last_name">Uom *</label>
										</div>
										
										<div class="col s6 margin-top-10">
											<div class="col s4">
												<p for="last_name">status</p>
											</div>
											<c:choose>
                          <c:when test = "${uomObject.status == 1}">
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
											</div>
									
										
										
										
									</div>
									
									<div class="row">
										<div class="col s12 center-align">
										  <button class="btn waves-effect waves-light"  type="button" onclick="saveuom()">Submit</button>
										</div>
									</div>
								
							</div>
						</div><!--inpur-fields--->
					</div><!--section--->
				</div><!---container--->
			</section>
			 <jsp:include page="../template/footer.jsp"></jsp:include>
</body>
</html>