 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GAIA - Banner Upload Configuration</title>
</head>
<jsp:include page="../template/menu.jsp"></jsp:include>
<script type="text/javascript" src="../static/resources/js/forms/banner/banner.js"></script>
<body onload="loadbulkupload()">

<section id="content">
				<div class="container">
				  <div class="section">
						<div id="input-fields">
							<h4 class="header">Banner</h4>
							<div class="row">
							<div class="row margin-bottom-70">
             <div class="col-md-12">
              	<a href="bannerlist" class="right btn font-12" style="margin-right: 25px; margin-top: -41px;">back</a>
			</div>	
			</div>
								<form class="col s12" >
									<div class="row">
										<div class="input-field col s6">
										  <input id="bannerlayoutname" type="text" class="validate" value="${bannerObject.bannerlayout }" >
										 
										  <input id="bannerid" type="hidden" value="${bannerid }" />
										  <label for="last_name">Layout Name *</label>
										</div>
										
										
										<div class="input-field col s6">
										  <input id="bannerurl" type="text" class="validate" value="${bannerObject.bannerurl }">
										  <label for="last_name">Banner Path *</label>
										</div>
										
										<div class="input-field col s6">
										  <input id="bannertype" type="text" class="validate" value="${bannerObject.bannertype }">
										  <label for="last_name">Banner Type *</label>
										</div>
										<div class="input-field col s6">
										  <input id="imagename" type="text" class="validate" value="${bannerObject.bannername }">
										  <label for="last_name">Banner Image Name *</label>
										</div>
										
										<div class="input-field col s6">
										  <input id="imagedesc" type="text" class="validate" value="${bannerObject.descbanner }">
										  <label for="last_name">Description</label>
									</div>
									<div class="input-field col s6">
										  <input id="imageposition" type="text" class="validate" value="${bannerObject.bannerposition }">
										  <label for="last_name">Banner Image Position</label>
									</div>
									<div class="input-field col s6">
										  <input id="imagetype" type="text" class="validate" value="${bannerObject.label }">
										  <label for="last_name">Banner Image Type</label>
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
										
										
									</div>
									<!-- <div class="row"> -->
										
										
									<!-- </div> -->
									<div class="col s6">
										 	<div class="col s4">
												<p for="categorytype">Banner Status</p>
											</div>
											<div class="col s8">
												<div class="onoffswitch margin-top-10">
												<c:choose>
                          						<c:when test = "${bannerObject.action == 1}">
                          						<input name="onoffswitch" class="onoffswitch-checkbox"  id="categorystatus" checked type="checkbox">
                          						</c:when>
									   			<c:otherwise>
									   			<input name="onoffswitch" class="onoffswitch-checkbox"  id="categorystatus"  type="checkbox">
									   			</c:otherwise>
									      		</c:choose>
						                       
						                        <label class="onoffswitch-label" for="categorystatus" style="padding-left:0px !important; height:auto;">
						                        <span class="onoffswitch-inner" width="200px">
						                        <span class="onoffswitch-active"><span class="onoffswitch-switch" width="100px">Active</span></span>
						                        <span class="onoffswitch-inactive"><span class="onoffswitch-switch" width="100px">InActive</span></span>
						                        </span>
						                        </label>
						                      	</div>
						                    </div>
										  
										</div>
									   <%--  <div class="col s6">
										 	<div class="col s4">
												<p for="categorytype">Image Status</p>
											</div>
											<div class="col s8">
												<div class="onoffswitch margin-top-10">
												<c:choose>
                          						<c:when test = "${bannerObject.status == 1}">
                          						<input name="onoffswitch1" class="onoffswitch-checkbox"  id="categorystatus1" checked type="checkbox">
                          						</c:when>
									   			<c:otherwise>
									   			<input name="onoffswitch1" class="onoffswitch-checkbox"  id="categorystatus1"  type="checkbox">
									   			</c:otherwise>
									      		</c:choose>
						                       
						                        <label class="onoffswitch-label" for="categorystatus1" style="padding-left:0px !important; height:auto;">
						                        <span class="onoffswitch-inner" width="200px">
						                        <span class="onoffswitch-active"><span class="onoffswitch-switch" width="100px">Active</span></span>
						                        <span class="onoffswitch-inactive"><span class="onoffswitch-switch" width="100px">InActive</span></span>
						                        </span>
						                        </label>
						                      	</div>
						                    </div>
										  
										</div> --%>
									  <div class="col s6">
									
		<label for="input-file-now">Upload Image</label>
  
 <iframe src="../uploadservice/uploadview?id=${bannerid }&form=banner&type=1&sessionname=bannerimage&foldername=image" width="500px;" style="border: none" ></iframe>
										  </div>
									<div class="row">
										<div class="col s12 center-align">
										  <button class="btn waves-effect waves-light"  type="button" onclick="savebulkupload()">Submit</button>
										</div>
									</div> 
								</form>
							</div>
						</div><!--inpur-fields--->
					</div><!--section--->
				</div><!---container--->
			</section>
		
      <jsp:include page="../template/footer.jsp"></jsp:include>
     
</body>
</html>