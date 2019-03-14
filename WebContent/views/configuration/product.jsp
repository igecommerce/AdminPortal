<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="../static/resources/js/forms/config/product.js"></script>
<title>GAIA - Product Configuration</title>
</head>
<c:forEach items="${productCategoryList }" var="category">
							<script>
								var checkCategory =  new Object();
								var categoryid = ${category.categoryid};
								var precategoryid=${category.precategoryname};
								var subcategoryid=${category.subcategoryname};
								checkCategory.categoryid = categoryid;
								checkCategory.precategoryid = precategoryid;
								checkCategory.subcategoryid = subcategoryid;
								checkCategoryArray.push(checkCategory);
								console.log(checkCategoryArray)
							</script>
							</c:forEach>
							
<body onload="loadaddproduct()">
<jsp:include page="../template/menu.jsp"></jsp:include>
<div class="modal size fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <!-- <button type="button" class="close" data-dismiss="modal">&times;</button> -->
          <h5 class="modal-title">Please Wait your Request will be processing</h5>
        </div>
       
        		<img src="../../ecommerce/static/resources/images/loader_green.gif" style="width: 131px; margin-left: 224px;margin-top: 10PX;"/>
        	
      
        <div class="modal-footer margin">
       
        </div>
     
      
    </div>
  </div>
  
</div>
	<section id="content">
				<div class="container">
				  <div class="section">
						<div id="input-fields">
							<h4 class="header"  >Add New Product</h4>
							
							
							
							<div calss="row">
							
							<div class="row margin-bottom-70">
             <div class="col-md-12">
              	<a href="productlist" class="right btn font-12" style="margin-right: 25px; margin-top: -58px;">Back</a>
			</div>	
			</div>
				   <div class="row">
				   
						    <div class="col s12" id="product_box">
						      <ul class="tabs">
						        <li class="tab col s3"><a class="active" href="#product_setup"><i class="mdi-content-create"></i> General Info.</a></li>
						        <li class="tab col s3"><a href="#product_details" class="product_details">Product Details</a></li>
						        <li class="tab col s3"><a href="#product_attributes" class="product_attributes">Category Hierarchy</a></li>
						        <li class="tab col s3"><a href="#product_stock" class="product_stock">Stock & Price</a></li>
						        <li class="tab col s3"><a href="#product_image_upload" class="product_image_upload">Upload Image</a></li>
						      </ul>
						    </div>
						    
						    <div id="product_setup" class="col s12 padding-10">
						    		<div class="row">
										<div >
										  <%-- <select id="producttype" value="${productObject.typeid }" >
										  <option value="">--Select--</option>
										  <c:forEach items="${producttytypeList }" var="producttype">
										  <option value="${producttype.typeid }">${producttype.typedesc }</option>
										  </c:forEach>
										  </select>
										  <label for="producttype">Product Type</label> --%>
										  <input type="hidden" id="productid" value="" />
										  <input type="hidden" id="operation" value="INS" />
										</div>
										<div class="input-field col s6">
											
											
												<select id="websiteid" value="${productObject.websiteid }">
												<option value="">--Select--</option>
												<c:forEach items="${websiteList }" var="website">
										  <option value="${website.websiteid }">${website.websitedesc }</option>
										  </c:forEach>
										  </select>
										  <label for="websiteid">Product Display Website *</label>
										</div>	
										<div class="input-field col s6">
										  <input type="text" id="skuid" value="${productObject.sku}">
										  <label for="skuid">Product SKU *</label>
										</div>
									</div>
									
									<div class="row">
										
										<div class="input-field col s6">
										  <input id="productnameid" type="text" value="${productObject.name}">
										  <label for="productnameid">Product Name *</label>
										</div>
										<div class="input-field col s6">
										<div class="col s4">
												<p for="last_name">Usable Gender</p>
											</div>
											<input id="gendertext" type="hidden" value="${productObject.gender}" />
										<div class="onoffswitch margin-top-10">
										<table id="moduleDt" class="no-padd-table">
									
										<tbody>
										
										<tr >
										
										<td style="background: transparent!important; " >
										<input name="hierarchy" type="radio" id="gendermale" checked="true"  />
										<label  for="gendermale">Male</label>
										</td>
										
										<td style="background: transparent!important; ">
										<input name="hierarchy" type="radio" id="genderfemale"   />
										<label  for="genderfemale">Female</label>
										</td>
										<td style="background: transparent!important; ">
										<input name="hierarchy" type="radio" id="genderunisex"   />
										<label  for="genderunisex">Unisex</label>
										</td>
										</tr>
										</tbody>
										</table>
											<%-- <input id="gendertext" type="hidden" value="${productObject.gender}" />
											<input name="onoffswitch" class="onoffswitch-checkbox"  id="genderid" checked="" type="checkbox">
						                        <label class="onoffswitch-label" for="genderid" style="padding-left:0px !important; height:auto;">
						                        <span class="onoffswitch-inner">
						                        <span class="onoffswitch-active"><span class="onoffswitch-switch">Male</span></span>
						                        <span class="onoffswitch-inactive"><span class="onoffswitch-switch">FeMale</span></span>
						                        </span>
						                        </label> --%>
						                        </div>
										</div>	
									</div>
									
									<div class="row">
										
										<div class="input-field col s6">
										  <select id="brandid" value="${productObject.brand}">
										  <option value="">--Select--</option>
										  <c:forEach items="${brandList }" var="brand">
										  <option value="${brand.brandid }">${brand.brandname }</option>
										  </c:forEach>
										  </select>
										  <label for="productnameid">Brand * </label>
										</div>
								
										
										<div class="input-field col s6">
										  <select id="uomid" value="${productObject.uom}">
										  <option value="">--Select--</option>
										  <c:forEach items="${uomList }" var="website">
										  <option value="${website.uomid }">${website.uomname }</option>
										  </c:forEach>
										  
										  </select>
										  <label for="productnameid">UOM * </label>
										</div>
										
									</div>

									<div class="input-field col s12" align="right">
											<button class="btn waves-effect waves-light" onclick="stepValidation_product_setup()" >Next</button>
									</div>
									
						    </div>
						    
						    <div id="product_details" class="col s12 padding-10" >
								 	<div class="row">
										
										<div class="col s6">
											<label for="productusageid">Product Usage</label>
										  	<textarea rows="5" cols="100" id="productusageid">${productObject.usage}</textarea>
										  
										</div>
										<div class="col s6">
										 <label for="productcompositionid">Product Composition</label>
										  <textarea rows="5" cols="100" id="productcompositionid" >${productObject.composition}</textarea>
										</div>
									</div>
									
									<div class="row">
										
										<div class="col s12 margin-top-10">
											<label for="productdescid">Product Description</label>
										  <textarea rows="5" cols="100" id="productdescid" >${productObject.descrition}</textarea>
										</div>
										 
									</div>
									<div class="input-field col s12" align="right">
											<button class="btn waves-effect waves-light" onclick="stepValidation_product_details()" >Next</button>
									</div>
							</div>
						    
						    <div id="product_attributes" class=" col s12 padding-10">
						     <div class="table-wrapper-scroll-y">
								 	<div class="row">
										<div class="col s12">
										
										<table id="moduleDt" class="no-padd-table">
									
										<tbody>
										
										<c:forEach items="${hierarchyList }" var="hierarchy">
										
										<c:choose>
										<c:when test = "${hierarchy.categorylevel == 0}">
										<tr  class="parentstyle-pink">
										<td class="padd-left-40">
										<input name="hierarchy" type="checkbox" id="categoryid${hierarchy.categoryid }"  dataid="${hierarchy.categoryid }" datalevel="${hierarchy.categorylevel}" dataparent="${hierarchy.parentid}" class="parent-${hierarchy.categoryid }" />
										<label class="white-font" for="categoryid${hierarchy.categoryid }">${hierarchy.categoryname } </label>
										</td>
										
										</tr>
										</c:when>
										
										<c:when test = "${hierarchy.categorylevel == 1}">
										<tr class="childstyle">
										<td class="padd-left-80">
										<input name="hierarchy" type="checkbox" id="categoryid${hierarchy.categoryid }"  dataid="${hierarchy.categoryid }" datalevel="${hierarchy.categorylevel}" dataparent="${hierarchy.parentid}" class="prechild-${hierarchy.parentid }"  />
										<label class="black-font" for="categoryid${hierarchy.categoryid }">${hierarchy.categoryname } </label>
										</td>
										
										</tr>
										</c:when>
										
										<c:when test = "${hierarchy.categorylevel == 2}">
										<tr class="childstyle">
										<td class="padd-left-120">
										<input name="hierarchy" type="checkbox" id="categoryid${hierarchy.categoryid }"  dataid="${hierarchy.categoryid }" datalevel="${hierarchy.categorylevel}" dataparent="${hierarchy.parentid}" class="child-${hierarchy.parentid }" />
										<label class="black-font" for="categoryid${hierarchy.categoryid }">${hierarchy.categoryname } </label>
										</td>
										
										</tr>
										</c:when>
										
										</c:choose>
										</c:forEach>
										</tbody>
										</table>
										  
										</div>
										 
									</div>
									</div>
									<div class="input-field col s12" align="right">
											<button class="btn waves-effect waves-light" onclick="stepValidation_product_category()" >Next</button>
									</div>
							</div>
							
						    <div id="product_stock" class="col s12 padding-10">
								<div class="row">
										<div class="input-field col s6">
											<input id="offerstartdate" type="text" class="datepicker" value="${productObject.offerstartdate}">
										    <label for="offerstartdate">Deal / Offer Start Date </label>
										</div>	
										<div class="input-field col s6">
											<input id="offerenddate" type="text" class="datepicker" value="${productObject.offerenddate}">
										    <label for="offerenddate">Deal / Offer End Date </label>
										</div>
									</div>
									
									<div class="row">
										<div class="input-field col s6">
											<input id="origprice" type="number" value="${productObject.origprice}">
										    <label for="origprice">Original Price *</label>
										</div>	
										<div class="input-field col s6">
											<input id="currentprice" type="number" value="${productObject.currentprice}">
										    <label for="currentprice">Current Price *</label>
										</div>
									</div>
									
									<div class="row">
										<div class="input-field col s6">
											<input id="offerprice" type="number" value="${productObject.offerprice}">
										    <label for="offerprice">Special Offer Price *</label>
										</div>	
										<div class="input-field col s6">
											<input id="productcount" type="number" value="${productObject.productcount}">
										    <label for="productcount">Current Stock Count *</label>
										</div>
									</div>
									
									<div class="row">
										<div class="input-field col s6">
											<select id="stockstatusid" value="${productObject.stockstatus}">
											<option value="">--Select--</option>
											<option value="1">InStore</option>
											<option value="0">Out of Stock</option>
											</select>
										    <label for="stockstatus">Stock Status *</label>
										</div>

										
										
									</div>
									<div class="input-field col s12" align="right">
											<button class="btn waves-effect waves-light" onclick="stepValidation_product_stock()" >Next</button>
									</div>
							</div>
						    
						    <div id="product_image_upload" class="col s12 padding-10">
						   
										
										
						    	<div class="col s6">
						    	<label for="input-file-now ">Upload Product Image</label>
						    		<div class="input-field">
										<input id="imagepathid" type="text" value="${productObject.image}">
										<label for="imagepathid">Product Image Label </label>
									</div>
									
									<iframe src="../uploadservice/uploadview?id=${productid }&form=product&type=1&sessionname=productimage&foldername=image" width="500px;" style="height:100px;border: none"></iframe>
									
								</div>
								 
								<div class="col s6">
									<label for="input-file-now">Upload Product Thumbnail 1 Image</label>
									<div class="input-field">
										<input id="thumbimageid1" type="text" value="${productObject.thumbimage1}">
										  <label for="thumbimageid1">Product Thumbnail 1 Label </label>
									</div>
									<iframe src="../uploadservice/uploadview?id=${productid }&form=product&type=2&sessionname=thumb1&foldername=thumbnail1" width="500px;" style="height:100px;border: none"></iframe>
								</div>
								
								
								
								<div class="col s6">
									<label for="input-file-now">Upload Thumbnail 2 Image</label>
									<div class="input-field">
										<input id="thumbimageid2" type="text" value="${productObject.thumbimage2}">
										  <label for="thumbimageid2">Product Thumbnail 2 Label </label>
									</div>
									<iframe src="../uploadservice/uploadview?id=${productid }&form=product&type=3&sessionname=thumb2&foldername=thumbnail2" width="500px;" style="height:100px;border: none"></iframe>
								</div>
								 
								<div class="col s6">
									<label for="input-file-now">Upload Product Thumbnail 3 Image</label>
									<div class="input-field">
										<input id="thumbimageid3" type="text" value="${productObject.thumbimage3}">
										  <label for="thumbimageid3">Product Thumbnail 3 Label </label>
									</div>
									<iframe src="../uploadservice/uploadview?id=${productid }&form=product&type=4&sessionname=thumb3&foldername=thumbnail3" width="500px;" style="height:100px;border: none"></iframe>
								</div>
								
								
										
								<div class="col s6">
									<label for="input-file-now">Upload Product Thumbnail 4 Image</label>
									<div class="input-field">
										<input id="thumbimageid4" type="text" value="${productObject.thumbimage4}">
										  <label for="thumbimageid4">Product Thumbnail 4 Label </label>
									</div>
									<iframe src="../uploadservice/uploadview?id=${productid }&form=product&type=5&sessionname=thumb4&foldername=thumbnail4" width="500px;" style="height:100px;border: none"></iframe>
								</div>		
								<div class="col s6">
									<label for="input-file-now">Upload Product Small Image</label>
									<div class="input-field">
										<input id="smallid" type="text" value="${productObject.smallimage}">
										  <label for="smallid">Product Small Image Label </label>
									</div>
									<iframe src="../uploadservice/uploadview?id=${productid }&form=product&type=6&sessionname=smallimage&foldername=smallimage" width="500px;" style="height:100px;border: none"></iframe>
								</div>
								
								<div class="input-field col s6" align="right">
											<button class="btn waves-effect waves-light" onclick="saveproduct()" >Submit</button>
								</div>

						    </div>
						    
						  </div>
  
							</div>
							
							
						</div><!--inpur-fields--->
					</div><!--section--->
				</div><!---container--->
			</section>
			 <jsp:include page="../template/footer.jsp"></jsp:include>
			
			 <script>
	  var instance = M.Tabs.init(el, options);
	  $(document).ready(function(){
	    $('.tabs').tabs();
	  });
	  </script>
	   	  <script>
				$(document).ready(function(){
						$("#moduleDt tbody tr td input[type=checkbox]").on("change",function(){
							var checked = $(this).prop('checked');
								
								var classname = $(this).attr('class');
								var res = classname.split("-");
								var parentname = res[0];
								if(parentname=="parent"){ 
									var parentid = res[1];
									
									$("#moduleDt tbody tr td input[class=prechild-"+parentid+"]").each(function(){
										var preparentid = $(this).attr('dataid');
										if(checked){
											$(this).prop('checked', true);
											}else{
												$(this).prop('checked', false);
												}
										
										var preparentchecked = $(this).prop('checked');
										$("#moduleDt tbody tr td input[class=child-"+preparentid+"]").each(function(){
											
											if(preparentchecked){
												$(this).prop('checked', true);
												}else{
													$(this).prop('checked', false);
													}
											
											});
										
										});
									
								}else if(parentname=="prechild"){
									var parentid = res[1];
									var preparentid = $(this).attr('dataid');
									var preparentchecked = $(this).prop('checked');
									$("#moduleDt tbody tr td input[class=child-"+preparentid+"]").each(function(){
										
										if(preparentchecked){
											$(this).prop('checked', true);
											}else{
												$(this).prop('checked', false);
												}
										
										});
									
									if(preparentchecked){
										$("#moduleDt tbody tr td input[class=parent-"+parentid+"]").prop("checked", true);
									}else{
										var mycheckedchildaarray = [];
										$("#moduleDt tbody tr td input[class=prechild-"+parentid+"]").each(function(){
											mycheckedchildaarray.push($(this).prop('checked'));
										});
										var totalValid = jQuery.inArray( true, mycheckedchildaarray );
										if(totalValid<0){
											$("#moduleDt tbody tr td input[class=parent-"+parentid+"]").prop("checked", false);
										}
									}
								}else if(parentname == "child"){
									var childid = res[1];
									var childchecked = $(this).prop('checked');
									
									var preparentnode = $("#moduleDt tbody tr td input[dataid='"+childid+"']");
									console.log(preparentnode)
									var preparetclass = preparentnode.attr("class");
									console.log(preparetclass)
									var preres = preparetclass.split("-");
									
									var parentid = preres[1];
									if(childchecked){
										$("#moduleDt tbody tr td input[class=parent-"+parentid+"]").prop("checked", true);
										
										$("#moduleDt tbody tr td input[dataid='"+childid+"']").prop("checked", true);
									}else{
										var mycheckedchildaarray = [];
										$("#moduleDt tbody tr td input[class=child-"+childid+"]").each(function(){
											mycheckedchildaarray.push($(this).prop('checked'));
										});
										var totalValid = jQuery.inArray( true, mycheckedchildaarray );
										if(totalValid<0){
											
											$("#moduleDt tbody tr td input[dataid='"+childid+"']").prop("checked", false);
											
											var mycheckedchildaarraypreparent = [];
											$("#moduleDt tbody tr td input[class=prechild-"+parentid+"]").each(function(){
												mycheckedchildaarraypreparent.push($(this).prop('checked'));
											});
											var totalValidPreParent = jQuery.inArray( true, mycheckedchildaarraypreparent );
											if(totalValidPreParent<0){
												$("#moduleDt tbody tr td input[class=parent-"+parentid+"]").prop("checked", false);
											}
											
											
										}
									}
								}
								
							});
						
					});
				

			 </script>

	  </div>
	   <style>
	 
	 .table-wrapper-scroll-y {
  display: block;
  max-height: 400px;
  overflow-y: scroll;
   overflow-x: hidden; 
  
  -ms-overflow-style: -ms-autohiding-scrollbar;
}


	 </style>
</body>

</html>