<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GAIA - Sales By Product</title> 
</head>
<jsp:include page="../template/menu.jsp"></jsp:include>
<script type="text/javascript" src="../static/resources/js/forms/config/product.js"></script>
<body onload="loadsalesbyproduct()" >
 <script>
$(document).ready(function(){
	$(".collapsible>li>.collapsible-body").stop(!0, !1);
	$(".collapsible>li.23").addClass("active");
	$(".collapsible>li.23>.collapsible-body").stop(!0, !1).slideDown();
	
});
</script> 
<div class="container">
          <div class="section">
			<div id="">
              <h4 class="header">Product List</h4>
				
              <div class="row">
                <div class="col s12 m12">
                  <table id="exportProductDt" class="table" cellspacing="0" style="width:100%;">
					
                    <thead>
                        <tr>
                            <th>Product SKU</th>
                            <th> Product Name</th>
                            <th> Price</th> 
                            
                        </tr>
                    </thead>
                 
                    <tbody>
	                   <c:forEach items="${productList }" var="product">
	                    <tr>
                          <td>${product.sku }</td>
                            <td>${product.name }</td>
                            <td>${product.origprice }</td>
                           
							
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