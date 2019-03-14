<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GAIA - DashBoard</title>

</head>
<body>

<jsp:include page="../template/menu.jsp"></jsp:include>

<section id="content" style="padding:2% 1%">

                
                <div class="container">

                    <!--card stats start-->
                    <div id="card-stats">
                        <div class="row">
                         <c:forEach var="order" items="${todayorderList }">
                            <div class="col s12 m6 l3" style="width: 25%;">
                                <div class="card">
                                    <div class="card-content white-text box1">
										<span>Daily Orders</span>
                                        <h4 class="card-stats-number">${order.qty}</h4>
                                    </div>
                                </div>
                            </div>
                             </c:forEach> 
                            <div class="col s12 m6 l3" style="width: 25%;">
                             <c:forEach var="order" items="${productList }">
                                <div class="card">
                                    <div class="card-content white-text box2">
										<span>Total Products</span>
                                        <h4 class="card-stats-number">${order.qty}</h4>
                                    </div>
                                </div>
                                 </c:forEach> 
                            </div>
                           <%--  <div class="col s12 m6 l3">
                             <c:forEach var="user" items="${totaluserList }">
                                <div class="card">
                                    <div class="card-content white-text box3">
										<span>Total Users</span>
                                        <h4 class="card-stats-number">${user.usercount}</h4>
                                    </div>
                                </div>
                                 </c:forEach> 
                            </div> --%>
                            <div class="col s12 m6 l3" style="width: 25%;">
                              <c:forEach var="user" items="${yesterdayorderList }">
                                <div class="card">
                                    <div class="card-content white-text box4">
										<span>YTD Cumulative Amt</span>
                                        <h4 class="card-stats-number">${user.qty}</h4>
                                    </div>
                                </div>
                                  </c:forEach> 
                            </div> 
							<div class="col s12 m6 l3" style="width: 25%;">
							 <c:forEach var="user" items="${customerList }">
                                <div class="card">
                                    <div class="card-content white-text box5">
										<span>Total customers</span>
                                        <h4 class="card-stats-number">${user.qty}</h4>
                                    </div>
                                </div>
                                  </c:forEach> 
                            </div>
                        </div>
                    </div>
                    
                     <div id="card-stats" class="recent_box">
                        	<ul class="tabs">
						        <li class="tab col s3"><a class="active recent_order" href="#product_setup"> Recent 5 Orders</a></li>
						        <li class="tab col s3"><a class="recent_product" href="#product_details">Recent 5 Products</a></li>
						        <!-- <li class="tab col s3" ><a class="recent_users" href="#product_attributes">Recent 5 Users</a></li> -->
						        <li class="tab col s3" ><a class="recent_y_order" href="#product_stock">YTD Orders Details</a></li>
						        <li class="tab col s3" ><a class="recent_customer" href="#product_image_upload">Recent 5 Customers</a></li>
						     </ul>
						     
                        
                      <div id="product_setup" class="col s12 padding-0" >
                      
                      <div class="row">
                      	<div class="col s12">
							  <table>
								<tbody>
								  <tr class="gradient-blue">
									<td ><b>Order Id</b></td>
									<td><b>Customer</b></td>
									<td><b>Mobile no</b></td>
									<td><b>Date</b></td>
									<td><b>Total Items</b></td>
									<td><b>Grand Total</b></td>
									
									
								  </tr>
								</tbody>
								<tbody>
								  <c:forEach var="order" items="${recentorderList }">
								  <tr>
									<td>${order.customerid}</td>
									<td>${order.customername}</td>
									<td>${order.mobileno}</td>
									<td>${order.orderdate}</td>
									<td>${order.qty}</td>
									<td>${order.totalprice}</td>
									
								  </tr>
								  </c:forEach> 
								</tbody>
								
							  </table>
							
                      </div>
                      </div>
                     </div>
                     
                       <div id="product_details" class="col s12 padding-0" >
								 	<div class="row">
								 
							<div class="col s12">
							  <table>
								
								<tbody>
								  <tr class="red-gradient">
									<td><b>Product</b></td>
									<td><b>Sku</b></td>
									<td><b>Amount</b></td>
									<td><b>Productcount</b></td>
									<!-- <td><b>brand</b></td> -->
									<td><b>Status</b></td>
									
									
								  </tr>
								</tbody>
								<tbody>
								  <c:forEach var="order" items="${recentproductList }">
								  <tr>
									<td>${order.name}</td>
									<td>${order.sku}</td>
									<td>${order.origprice}</td>
									<td>${order.productcount}</td>
									<%-- <td>${order.brand}</td> --%>
									<td>${order.stockstatus}</td>
									
								  </tr>
								  </c:forEach> 
								</tbody>
								
							  </table>
							</div>
						  </div>
						</div> 	
						 	
								 	
                     
                      <%--  <div id="product_attributes" class=" col s12 padding-0">
                        <div class="row">
                        	<div class="col s12">
							  <table >
								
								<tbody>
								  <tr class="green-gradient">
									<td><b>User Name</b></td>
									<td><b>Email</b></td>
									<td><b>Mobile No</b></td>
									<td><b>Role</b></td>
									<td><b>Superadmin</b></td>
									<td><b>Status</b></td>
									
									
								  </tr>
								</tbody>
								<tbody>
								  <c:forEach var="order" items="${recentuserList }">
								  <tr>
									<td>${order.username}</td>
									<td>${order.email}</td>
									<td>${order.phoneno}</td>
									<td>${order.roledesc}</td>
									<td>${order.superadminflag}</td>
									<td>${order.status}</td>
									
								  </tr>
								  </c:forEach> 
								</tbody>
								
							  </table>
							
                        </div>
                        </div>
                       
                       </div> --%>
                     
                     
                      <div id="product_stock" class="col s12 padding-0">
								<div class="row">
										
							<div class="col s12 ">
							<%--  <c:forEach var="order" items="${yesterdayordersList }">
							<table>
							<tbody >
							 <tr class="red-gradient">
							
							<td><b style="margin-left: 20px;">Total Orders</b> <b style="margin-left: 111px;color:black;">: ${order.customerid}</b></td>  
							
							   </tr>
							    <tr class="red-gradient">
							
							<td><b style="margin-left: 20px;">Total Products Sold</b><b style="margin-left: 63px;color:black;">: ${order.qty}</b></td> 
							 
							   </tr>
							    <tr class="red-gradient">
							
							<td><b style="margin-left: 20px;">Total Amount</b><b style="margin-left: 106px;color:black;">: ${order.totalprice}</b></td> 
							 
							   </tr>
							  </tbody>
							   </table>
							    </c:forEach>  --%>
						<table>
								
							 <tbody>
								  <tr class="red-gradient">
									<td><b>Total Orders</b></td>
									<td><b>Total Products Sold</b></td>
									<td><b>Total Amount</b></td>
									
									
									
								  </tr>
								</tbody> 
							<tbody> 
								  <c:forEach var="order" items="${yesterdayordersList }">
								  <tr>
									<td>${order.customerid}</td>
									<td>${order.qty}</td>
									<td>${order.totalprice}</td>
									
									
								  </tr>
								  </c:forEach> 
							 </tbody> 
								
							  </table>
							</div>
						  </div>
						</div> 	
								
								
								
                       <div id="product_image_upload" class="col s12 padding-0">
						    <div class="row">
						    
							<div class="col s12 ">
							  <table>
								
								<tbody>
								  <tr class="lavendar-gradient">
									<td><b>Customer Name</b></td>
									<td><b>Mobile</b></td>
									<td><b>Email</b></td>
									<td><b>Country</b></td>
									<td><b>Street</b></td>
									<td><b>Status</b></td>
									
									
								  </tr>
								</tbody>
								<tbody>
								  <c:forEach var="order" items="${recentcustomerList }">
								  <tr>
									<td>${order.username}</td>
									<td>${order.mobile}</td>
									<td>${order.email}</td>
									<td>${order.country}</td>
									<td>${order.street}</td>
									<td>${order.status}</td>
									
								  </tr>
								  </c:forEach> 
								</tbody>
								
							  </table>
							</div>
						  </div>
						</div>
						    
						   
                    
                    </div>
                     </div>
                    
                   
					
                   
            </section>
            <jsp:include page="../template/footer.jsp"></jsp:include>
            <script>
	  var instance = M.Tabs.init(el, options);
	  $(document).ready(function(){
	    $('.tabs').tabs();
	  });
	  </script>
	  <style>
	  tr.gradient-blue td{
	  color:#2b85dd;
	  }
	  tr.red-gradient td{
	  color:#ec5c41;
	  }
	  tr.green-gradient td{
	  color:#0bac83;
	  }
	  tr.lavendar-gradient td{
	  color:#cd3cf1;
	  }
	 .tabs .indicator {
    background-color: #000000;
}
	  </style>
</body>
</html>