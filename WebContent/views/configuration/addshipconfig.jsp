<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>GAIA - Shipping Configuration</title>
</head>
<jsp:include page="../template/menu.jsp"></jsp:include>

<script type="text/javascript" src="../static/resources/js/forms/config/config.js"></script>
<body onload="loadaddshipconfig()">
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
							<h4 class="header">Shipping configuration</h4>
							<div class="row">
							<div class="row margin-bottom-70">
             <div class="col-md-12">
              	<a href="shipconfig" class="right btn font-12" style="margin-right: 25px; margin-top: -41px;">back</a>
			</div>	
			</div>
							
									<div class="row">
										<div class="input-field col s6">
										  <input id="shiplabel" type="text" class="validate" value="${shipObject.label }">
										  <input id="operation" type="hidden" value="INS" />
										  <input id="shipid" type="hidden" value="${shippingid }" />
										
										  <label for="last_name">Shipping label *</label>
										</div>
										
										
										<div class="input-field col s6">
										  <input id="shipamount" type="number" class="validate" value="${shipObject.amount }">
										  <label for="last_name">Shipping Amount *</label>
										</div>
										<div class="input-field col s6">
										  <input id="shiporgin" type="text" class="validate" value="${shipObject.origin }">
										  <label for="last_name">Shipping origin *</label>
										</div>
										
										
										
									</div>
									<!-- <div class="row"> -->
										
										
									<!-- </div> -->
								
									 
									<div class="row">
										<div class="col s12 center-align">
										  <button class="btn waves-effect waves-light"  type="button" onclick="saveshipconfig()">Submit</button>
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