<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GAIA - DashBoard</title>
</head>
<jsp:include page="../template/menu.jsp"></jsp:include>
<script type="text/javascript" src="../static/resources/js/forms/config/product.js"></script>
<body onload="loadproduct()">
<div class="container">
          <div class="section">
			<div id="">
              <h4 class="header">Product List</h4>
				<div class="row">
					<div class="col s6">
					  <button class="btn waves-effect waves-light " type="button" onclick="createnewproduct()">create New Product</button>
					</div>
					<!-- <div class="input-field col s3" style="float:right; margin-top:-15px;">
						<input id="last_name" type="text" class="validate">
						<label for="last_name">Search</label>
					</div> -->
					
				</div>

              <div class="row">
                <div class="col s12 m12">
              
                  <table id="productDT" class="responsive-table display" cellspacing="0">
					
                    <thead style="background:linear-gradient(to right, #f3476f, #f66d5d);color:white">
                        <tr>
                            <th>ID</th>
                            <th style="width:350px;">Product</th>
                           <!--  <th>Category</th> -->
                            <th>SKU</th>
                            <th>Price</th>
							<th>QTY</th>
							<th>Status</th>
							<th>Action</th>
                        </tr>
                    </thead>
                 
                    <tbody>
	                   <c:forEach items="${productlist }" var="product" varStatus="loop">
	                    <tr>
	                    <td>${loop.index+1}</td>
                           <%--  <td>${product.intProductid }</td> --%>
                            <td style="width:350px;">${product.name }</td>
                            <td>${product.sku }</td>
                            <td>${product.origprice }</td>
							<td>${product.productcount }</td>
							<td>${product.stockstatus }</td>
							<td><button class="btn" onclick="editproduct('${product.intProductid }')" > <i class="mdi-content-create"></i> </button></td>
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