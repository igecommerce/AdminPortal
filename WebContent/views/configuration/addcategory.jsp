<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>GAIA - Category Configuration</title>
</head>
<jsp:include page="../template/menu.jsp"></jsp:include>
<script type="text/javascript" src="../static/resources/js/forms/category/category.js"></script>
<body onload="loadaddcategory()">
<div class="modal size fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <!-- <button type="button" class="close" data-dismiss="modal">&times;</button> -->
          <h5 class="modal-title">Base Category Position</h5>
        </div>
       <table id="categoryDT" class=" responsive-table display ourstyletable" cellspacing="0">
					<!-- <button class="btn" type="submit" name="action" style="position:relative; top:30px;">Export X</button> -->
                    <thead>
                        <tr>
                            <th>Base Category</th>
                            <th>Position</th>
                        </tr>
                    </thead>
                 
                    <tbody>
                      </tbody>
                  </table>
        <div class="modal-footer margin">
        <button type="button" class="btn btn-secondary" onclick="modalClose('myModal')">Close</button>
        </div>
    </div>
  </div>
  
</div>
<div class="modal size fade" id="myModalprecategory" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <!-- <button type="button" class="close" data-dismiss="modal">&times;</button> -->
          <h5 class="modal-title">Pre Category Position</h5>
        </div>
       <table id="precategoryDT" class=" responsive-table display ourstyletable" cellspacing="0">
					<!-- <button class="btn" type="submit" name="action" style="position:relative; top:30px;">Export X</button> -->
                    <thead>
                        <tr>
                            <th>Base Category</th>
                            <th>Pre Category</th>
                             <th>Position</th>
                        </tr>
                    </thead>
                 
                    <tbody>
                      </tbody>
                  </table>
        <div class="modal-footer margin">
       
        <button type="button" class="btn btn-secondary" onclick="modalClose('myModalprecategory')">Close</button>
       
        </div>
    </div>
  </div>
  
</div>
<div class="modal size fade" id="myModalsubcategory" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <!-- <button type="button" class="close" data-dismiss="modal">&times;</button> -->
          <h5 class="modal-title">Sub Category Position</h5>
        </div>
       <table id="subcategoryDT" class=" responsive-table display ourstyletable" cellspacing="0">
					<!-- <button class="btn" type="submit" name="action" style="position:relative; top:30px;">Export X</button> -->
                    <thead>
                        <tr>
                            <th>Base Category</th>
                            <th>Pre Category</th>
                            <th>Sub Category</th>
                             <th>Position</th>
                        </tr>
                    </thead>
                 
                    <tbody>
                      </tbody>
                  </table>
        <div class="modal-footer margin">
         <button type="button" class="btn btn-secondary" onclick="modalClose('myModalsubcategory')">Close</button>
        </div>
    </div>
  </div>
  
</div>
	<section id="content">
				<div class="container">
				  <div class="section">
						<div id="input-fields">
							<h4 class="header">Category</h4>
							<div class="row">
							<div class="row margin-bottom-70">
             <div class="col-md-12">
              	<a href="category" class="right btn font-12" style="margin-right: 25px; margin-top: -41px;">back</a>
			</div>	
			</div>
								<form class="col s12" action="">
									<div class="row">
										<div class="input-field col s6">
										  <input id="categoryname" type="text" placeholder="Catgeory name" class="validate" value="${categoryObj.categoryname }">
										  <input type="hidden" id="actiontext" value="${action }">
										  <input type="hidden" id="categoryidtext" value="${id }">
										  <label for="categoryname">Name *</label>
										</div>
										<div class="input-field col s6">
										  <input id="categorydisplayid" type="text" placeholder="Catgeory Display URL" class="validate" value="${categoryObj.categoryurl }">
										  <label for="categorydisplayid">Category Display URL *</label>
										</div>
									</div>

									<div class="row">
										<div class="input-field col s6">
										  <select id="categorylevel" onchange="getpresubcategories(this.value)" value="${categoryObj.categorylevel }">
										  <option value="0">Base Category</option>
										  <option value="1">Pre Category</option>
										  <option value="2">Sub Category</option>
										  </select>
										  <label for="categorytype">Category Level *</label>
										</div>
										
										<div class="input-field col s6" id="presubcategoryforhide" style="display:none;width: 35%;">
										
										<select id="precategoryid" value="${categoryObj.path }">
										  <option value="0">--Select--</option>
										  </select>
										
										 
										  <label for="presubcategorydiv">Pre/Sub Category - BaseCategory</label>
										  	
										</div>
										
									</div>
								<button  type="button" name="action" onclick="viewcategoryposition()"  class="right btn font-12" style="margin-right: 4px; margin-top: -51px;">View Position</button>
									<div class="row">
										<div class="col s6">
										 	<div class="col s4">
												<p for="categorytype">Status</p>
											</div>
											<div class="col s8">
												<div class="onoffswitch margin-top-10">
												<c:choose>
                          						<c:when test = "${categoryObj.status == 1}">
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
										
										<div class="input-field col s6" style="margin-top:-4px;">
										<span id="messagetext" style="color:red;margin-left: 134px;" > </span>
										  <input id="categorypositionid" type="text" placeholder="Catgeory Position in numbers" class="validate" onkeypress="return isNumberKey(event)" onkeyup="checkposition(this.value)" value="${categoryObj.displayorder }">
										  <label for="categorypositionid">Display Position *</label>
										</div>
									</div>
									
									<!--file-upload-->
									<div id="imagefileupload" class="section">
										<div class="row section">
										  <div class="col s6 m2 l3">
											 <!-- <p>Default version</p> -->
										  </div>
										  <div class="col s6">
		<label for="input-file-now">Upload Category Image</label>
<iframe src="../uploadservice/uploadview?id=${id }&form=category&type=1&sessionname=categoryimage&foldername=image" width="500px;" style="border: none"></iframe>
										  </div>
										  
										  <div class="col s6 m2 l3">
											 <!-- <p>Default version</p> -->
										  </div>
										  <div class="col s6">
												<label for="input-file-now">Upload Category Thumbnail</label>
<iframe src="../uploadservice/uploadview?id=${id }&form=category&type=2&sessionname=categorythumbnail&foldername=thumbnails" width="500px;" style="border: none"></iframe>
										  </div>
										</div>
									</div>
									
									<div id="thumbnailfileupload" >
										<div >
										  
										</div>
									</div>
									
									<div class="row">
										<div class="col s12 center-align">
										  <button class="btn waves-effect waves-light " type="button" name="action" onclick="createcategory()">Submit</button>
										</div>
									</div>
								</form>
							</div>
						</div><!--inpur-fields--->
					</div><!--section--->
				</div><!---container--->
			</section>
		<!-- 	<script>  
$(document).ready(function(){
    $('#categorylevel').on('change', function() {
      if ( this.value == '0')
      {
        $("#presubcategoryforhide").hide();
      }
      else
      {
        $("#presubcategoryforhide").show();
      }
    });
});
</script> -->
 <script language=Javascript>
 
      function isNumberKey(evt)
      {
    	 
         var charCode = (evt.which) ? evt.which : event.keyCode
         if (charCode > 31 && (charCode < 48 || charCode > 57))
            return false;

         return true;
      }
    function checkposition(value){
    	
    
    	var level = $("#categorylevel").val();
    	
      	$.ajax({
      		type : 'GET',
 			contentType : 'application/json',
 			url : '../categoryservices/checkposition/'+value+'/'+level,
 			data : JSON.stringify(value),
 			success:function(data,textStatus,jqXHR){
 				 var responseType = data.responseValue.responseType;
 				var responseValue = getResponseValue(data); 
 				console.log(responseValue.length)
 				if(responseValue.length == 1){
 					$('#messagetext').show();
 					$('#messagetext').text("Category Position Exists");
 				}else{
 					$('#messagetext').hide();
 				}
 			}
     	});
    }
   </script>
			
	</body>
</html>