<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GAIA - Product Export</title> 
</head>
<jsp:include page="../template/menu.jsp"></jsp:include>
<script type="text/javascript" src="../static/resources/js/forms/config/product.js"></script>
<body onload="loadexportproduct()">
<script>
$(document).ready(function(){
	$(".collapsible>li>.collapsible-body").stop(!0, !1);
	$(".collapsible>li.13").addClass("active");
	$(".collapsible>li.13>.collapsible-body").stop(!0, !1).slideDown();
	
});
</script>
<div class="container">
          <div class="section">
			<div id="">
              <h4 class="header">Product List</h4>
				
              <div class="row">
                <div class="col s12 m12">
                  <table id="exportProductDt" class=" responsive-table display " cellspacing="0">
					
                    <thead style="background-color:red;color:white;">
                        <tr>
                            <th>Product ID</th>
                            <th> Website</th>
                            <th> SKU</th> 
                            <th> Name</th>
                            <th>Gender</th>
							<th> Brand</th>
							<th> UOM</th>
							<th> Usage</th>
							<th> Composition</th>
							<th> Description</th>
							<th>Deal / Offer Start Date</th>
							<th>Deal / Offer End Date</th>
							<th>Original Price</th>
							<th>Current Price</th>
							<th>Special Offer Price</th>
							<th>Current Stock Count</th>
							 <th>Stock Status</th>
							 <th>Image</th>
							 <th>Thumbnail1</th>
							 <th>Thumbnail2</th>
							 <th>Thumbnail3</th>
							 <th>Thumbnail4</th>
							 <th>Small image</th>
							<th>Category Name</th>
							 <th>Precategory Name</th>
							  <th>Subcategory Name</th> 
                        </tr>
                    </thead>
                 
                    <tbody>
	                   <c:forEach items="${productList }" var="product" varStatus="loop">
	                    <tr>
                           <td>${loop.index+1}</td>
                            <td>${product.websitedesc }</td>
                            <td>${product.sku }</td>
                            <td>${product.name }</td>
                            <td>${product.gender }</td>
							<td>${product.brand }</td>
							<td>${product.uom }</td>
							<td>${product.productusage }</td>
							<td>${product.composition }</td>
							<td>${product.descrition }</td>
							<td>${product.offerstartdate }</td>
							<td>${product.offerenddate }</td>
							<td>${product.origprice }</td>
							<td>${product.currentprice }</td>
							<td>${product.offerprice }</td>
							<td>${product.productcount }</td>
							<td>${product.status }</td>
							<td>${product.image }</td>
							<td>${product.thumbimage1 }</td>
							<td>${product.thumbimage2 }</td>
							<td>${product.thumbimage3 }</td>
							<td>${product.thumbimage4 }</td>
							<td>${product.smallimage }</td>
						    <td>${product.categoryname }</td>
							 <td>${product.precategoryname }</td>
							<td>${product.subcategoryname }</td> 
							
                        </tr>
	                    </c:forEach>
                   
                    </tbody>
                  </table>
                </div>
              </div>
            </div> 
			</div>
		</div>
		
		<jsp:include page="../template/footer.jsp"></jsp:include>
</body>
</html>