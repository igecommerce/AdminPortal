 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GAIA - Orderdetails</title>
</head>
<script type="text/javascript" src="../static/resources/js/forms/order/order.js"></script>
<body onload="loadcustomerdetails()">
<jsp:include page="../template/menu.jsp"></jsp:include>
 <section id="content">
 
		
        <!--breadcrumbs start-->
        <!--<div id="breadcrumbs-wrapper">
            <div class="header-search-wrapper grey hide-on-large-only">
                <i class="mdi-action-search active"></i>
                <input type="text" name="Search" class="header-search-input z-depth-2" placeholder="Explore Materialize">
            </div>
          <div class="container">
            <div class="row">
              <div class="col s12 m12 l12">
                <h5 class="breadcrumbs-title">Invoice</h5>
                <ol class="breadcrumbs">
                  <li><a href="index.html">Dashboard</a>
                  </li>
                  <li><a href="#">Pages</a>
                  </li>
                  <li class="active">Invoice</li>
                </ol>
              </div>
            </div>
          </div>
        </div>-->
      
        <!--breadcrumbs end-->
			<div class="container">
				<div class="section">
				
					<div id="input-fields">
					
						<h4 class="header">Order Details</h4>
						<div class="row margin-bottom-70">
             <div class="col-md-12">
              	<a href="orderlist" class="right btn font-12" style="margin-right: 25px; margin-top: -41px;">back</a>
			</div>	
			</div>
						<!-----------------------------------------------------Order-Details & Account-info---------------------------------------->
						<div class="invoice-table">
						  <div class="row">
							<div class="col s6 m6">
							  <table class="striped" >
								<thead style="background:#E4E8EE;">
								  <tr>
									<th data-field="no">Order Details</th>
									<th data-field="no"></th>
								  </tr>
								</thead>
								<tbody>
								<c:forEach var="brand" items="${particularorderlist }">
								  <tr>
								  
									<td>Order ID</td>
									<td>${brand.orderid}</td>
								  </tr>
								  <tr>
									<td>Order Date</td>
									<td>${brand.orderdate}</td>
								  </tr>
								  <tr>
									<td>Order Status</td>
									<td></td>
								  </tr>
								  </c:forEach> 
								</tbody>
							  </table>
							</div>
							<div class="col s6 m6">
							  <table class="striped">
								<thead style="background:#E4E8EE;">
								  <tr >
									<th data-field="no">Account information</th>
									<th data-field="no"></th>
								  </tr>
								</thead>
								<tbody>
								
								<c:forEach var="order" items="${particularorderlist }">
								  <tr>
									<td>Customer Name</td>
									<td>${order.customername}</td>
								  </tr>
								  <tr>
									<td>Email ID</td>
									<td>${order.email}</td>
								  </tr>
								  </c:forEach> 
								</tbody>
							  </table>
							</div>
						  </div>
						</div>
						<!-----------------------------------------------------Billing & shippng address---------------------------------------->
						<div class="invoice-table">
						  <div class="row">
							<div class="col s6 m6">
							  <table class="striped" >
								<thead style="background:#E4E8EE;">
								  <tr>
									<th data-field="no">Billing Address</th>
									<th data-field="no" class="right-align"><i class="mdi-content-create"></i></i></th>
								  </tr>
								</thead>
								<tbody>
								
								  <c:forEach var="brand" items="${particularorderlist }">
								  <tr>
									<td>${brand.street},${brand.country},${brand.region},${brand.areaaddress},${brand.postcode}</td>
									<td></td>
								  </tr>	
								   </c:forEach> 
								</tbody>
							  </table>
							</div>
							<div class="col s6 m6">
							  <table class="striped">
								<thead style="background:#E4E8EE;">
								  <tr >
									<th data-field="no">Shipping Address</th>
									<th data-field="no" class="right-align"><i class="mdi-content-create"></i></th>
								  </tr>
								</thead>
								<tbody>
								 <c:forEach var="brand" items="${particularorderlist }">
								  <tr>
									<td>${brand.street},${brand.country},${brand.region},${brand.areaaddress},${brand.postcode}</td>
									<td></td>
								
								  </tr>	
								   </c:forEach> 
								</tbody>
							  </table>
							</div>
						  </div>
						</div>
						<!-----------------------------------------------------Payment info & shipping charges---------------------------------------->
						<div class="invoice-table">
						  <div class="row">
							<div class="col s6 m6">
							  <table class="striped" >
								<thead style="background:#E4E8EE;">
								  <tr>
									<th data-field="no">Payment Information</th>
									<th data-field="no"></th>
								  </tr>
								</thead>
								<tbody>
								 <c:forEach var="brand" items="${particularorderlist }">
								  <tr>
									<td></td>
									<td></td>
								  </tr>
								  </c:forEach> 
								</tbody>
							  </table>
							</div>
							<div class="col s6 m6">
							  <table class="striped">
								<thead style="background:#E4E8EE;">
								  <tr >
									<th data-field="no">Shipping Charges</th>
									<th data-field="no"></th>
								  </tr>
								</thead>
								<tbody>
								 <c:forEach var="brand" items="${particularorderlist }">
								  <tr>
									<td>${brand.shippingcharge}</td>
									<td></td>
								  </tr>
								   </c:forEach> 
								</tbody>
							  </table>
							</div>
						  </div>
						</div>
						<!-----------------------------------------------------Items Ordered---------------------------------------->
						<div class="invoice-table">
						  <div class="row">
							<div class="col s12 m12">
							  <table class="striped" >
								<thead style="background:#E4E8EE;">
								  <tr>
									<th data-field="no">Items Ordered</th>
									<th data-field="no"></th>
									<th data-field="no"></th>
									<th data-field="no"></th>
									<th data-field="no"></th>
									<!--<th data-field="no"></th>
									<th data-field="no"></th>
									 <th data-field="no"></th> -->
								  </tr>
								</thead>
								<tbody>
								  <tr>
									<td><b>Product</b></td>
									<!-- <td><b>Status</b></td> -->
									<td><b>Original Price</b></td>
									<td><b>QTY</b></td>
									<!-- <td><b>Sub Total</b></td> -->
									<td><b>Tax Amount</b></td>
									<td><b>Tax Percentage</b></td>
									
									<!-- <td><b>Discount Amount</b></td> -->
								  </tr>
								</tbody>
								<tbody>
								
								<c:forEach var="product" items="${particularorderproductlist }">
								  <tr>
									<td>${product.productname}</td>
									<td>${product.orginalprice}</td>
									<td>${product.qty}</td>
									<%-- <td>${product.subtotal}</td> --%>
									<td>${product.tax}</td>
									<td>${product.tax}%</td>
									
									
								  </tr>
								   </c:forEach> 
								</tbody>
								
							  </table>
							</div>
						  </div>
						</div>
						<!-----------------------------------------------------Order Totals & comment history---------------------------------------->
						<div class="invoice-table">
						  <div class="row">
							<div class="col s6 m6">
							  <table class="striped" >
								<thead style="background:#E4E8EE;">
								  <tr>
									<th data-field="no">Order Totals</th>
									<th data-field="no"></th>
								  </tr>
								</thead>
								<tbody>
								 <c:forEach var="brand" items="${particularorderlist }">
								 <%--  <tr>
									<td class="right-align">Sub Total</td>
									<td class="right-align">${brand.total}</td>
								  </tr> --%>
								  </c:forEach> 
								</tbody>
								<tbody>
								 <c:forEach var="brand" items="${particularorderlist }">
								  <tr>
									<td class="right-align">Shipping Charges</td>
									<td class="right-align">${brand.shippingcharge}</td>
								  </tr>
								   </c:forEach> 
								</tbody>
								<tbody>
								 <c:forEach var="brand" items="${particularorderlist }">
								  <tr>
									<td class="right-align"><b>Grand Total</b></td>
									<td class="right-align">${brand.totalprice}</td>
								  </tr>
								   </c:forEach> 
								</tbody>
							  </table>
							</div>
							<div class="col s6 m6">
							  <table class="striped">
								<thead style="background:#E4E8EE;">
								  <tr >
									<th data-field="no">Comments History</th>
									<th data-field="no"></th>
								  </tr>
								</thead>
								<tbody>
								  <tr>
									<td>Add Other Comments<br>
									<textarea id="textarea1" class="materialize-textarea" length=""></textarea>
									</td>
									<td></td>
								  </tr>
								</tbody>
								<tbody>
								  <tr>
									<td><div class="row">
										<div class="col s12 right-align">
										  <button class="btn waves-effect waves-light " type="submit" name="action">Submit Comment</button>
										</div>
									</div></td>
									<td></td>
								  </tr>
								</tbody>
							  </table>
							</div>
						  </div>
						</div>
						
					</div>
				</div>

			</div>
			
			
      </section>
      <jsp:include page="../template/footer.jsp"></jsp:include>
</body>
</html>