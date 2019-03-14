 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GAIA - Customer details</title>
</head>
<script type="text/javascript" src="../static/resources/js/forms/order/order.js"></script>
<body >
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
					
						<h4 class="header">Customer Details</h4>
						<div class="row margin-bottom-70">
             <div class="col-md-12">
              	<a href="customerlist" class="right btn font-12" style="margin-right: 25px; margin-top: -41px;"><i class="mdi-hardware-keyboard-arrow-left" >back</i></a>
			</div>	
			</div>
			
							<input type="hidden" id="customeridtext" value="${customerid }">
						<!-----------------------------------------------------Order-Details & Account-info---------------------------------------->
						<div class="invoice-table">
						  <div class="row">
							<div class="col s6 m6">
							  <table class="striped" >
								<thead style="background:#E4E8EE;">
								  <tr>
									<th data-field="no">Customer Details</th>
									<th data-field="no"></th>
								  </tr>
								</thead>
								
								<tbody>
								<tr>
								 
									</tr>
								<c:forEach var="brand" items="${particularcustomerlist }">
								  <tr>
									<td>Customer Name</td>
									<td>${brand.username}</td>
								  </tr>
								  <tr>
									<td>Email</td>
									<td>${brand.email}</td>
								  </tr>
								  <tr>
									<td>Mobile No</td>
									<td>${brand.mobile}</td>
								  </tr> 
								  </c:forEach> 
								  
								</tbody>
								
							  </table>
							</div>
							<div class="col s6 m6">
							  <table class="striped">
								<thead style="background:#E4E8EE;">
								  <tr >
									<th data-field="no">Address</th>
									<th data-field="no"></th>
								  </tr>
								</thead>
								<tbody>
								
							<c:forEach var="order" items="${particularcustomerlist }">
								  <tr>
									<td>Street </td>
									<td>${order.street}</td>
								  </tr>
								  <tr>
									<td>Country</td>
									<td>${order.country}</td>
								  </tr>
								   <tr>
									<td>Region</td>
									<td>${order.region}</td>
									</tr>
									 <tr>
									<td>Area</td>
									<td>${order.areaname}</td>
									</tr>
									 <tr>
									<td>Postcode</td>
									<td>${order.postcode}</td>
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
							<div class="col s12 m12">
							  <table class="striped" >
								<thead style="background:#E4E8EE;">
								  <tr>
									<th data-field="no">Product Details</th>
									<th data-field="no"></th>
									<th data-field="no"></th>
									
								  </tr>
								</thead>
								<tbody>
								  <tr>
									<td><b>Product</b></td>
									<td><b>Available Stock</b></td>
									<td><b>Status</b></td>
									
									
								</tbody>
								<tbody>
								
								<c:forEach var="product" items="${productstocklist }">
								  <tr>
								  <td>${product.name}</td>
									<td>${product.productcount}</td>
									 <c:choose>
                          <c:when test = "${product.status == 1}">
									<td><div class="onoffswitch margin-top-10" >
						                        <input name="onoffswitch" class="onoffswitch-checkbox"  id="myonoffswitch1${product.productid }" value="${product.status}" checked="" type="checkbox">
						                        <label class="onoffswitch-label" for="myonoffswitch1${product.productid }" style="padding-left:0px !important; height:auto;">
						                       
						                        <span class="onoffswitch-inner">
						                        <span class="onoffswitch-active"><span class="onoffswitch-switch">Active</span></span>
						                        <span class="onoffswitch-inactive"><span class="onoffswitch-switch">Inactive</span></span>
						                        </span>
						                        </label>
						                      	</div>
						                      	
									<!-- </div> -->
									<!-- <div class="row"> -->
									
										<div class="col s12 center-align" style="width:1px;margin-top: 10px;">
										  <button class="btn waves-effect waves-light"  type="button" onclick="savestatusproduct('${product.productid }')" " style="width: 88px;    height: 31px;">Save</button>
										  
										</div>
									<!-- </div>	 -->	</td>
									 </c:when>
									   <c:otherwise>
									<td><div class="onoffswitch margin-top-10" >
						                        <input name="onoffswitch" class="onoffswitch-checkbox"  id="myonoffswitch1${product.productid }" value="${product.status}"  type="checkbox">
						                        <label class="onoffswitch-label" for="myonoffswitch1${product.productid }" style="padding-left:0px !important; height:auto;">
						                       
						                        <span class="onoffswitch-inner">
						                        <span class="onoffswitch-active"><span class="onoffswitch-switch">Active</span></span>
						                        <span class="onoffswitch-inactive"><span class="onoffswitch-switch">Inactive</span></span>
						                        </span>
						                        </label>
						                      	</div>
						                      	
									<!-- </div> -->
									<!-- <div class="row"> -->
										<div class="col s12 center-align" style="width:1px;margin-top: 10px;">
										   <button class="btn waves-effect waves-light"  type="button" onclick="savestatusproduct('${product.productid }')" " style="width: 88px;    height: 31px;">Save</button>
										  
										</div>
									<!-- </div>	 -->	</td>
									</c:otherwise>
									      </c:choose>
								  </tr>
								   </c:forEach> 
								</tbody>
								
							  </table>
							</div>
						  </div>
						</div>
						<!-----------------------------------------------------Payment info & shipping charges---------------------------------------->
						
						 <c:forEach var="customer" items="${customerlist }">
                    <p>Status</p>
                          <c:choose>
                          <c:when test = "${customer.status == 1}">
                         
									<div class="onoffswitch margin-top-10" >
						                        <input name="onoffswitch" class="onoffswitch-checkbox"  id="myonoffswitch2${customer.customerid }" checked=""  type="checkbox">
						                        <label class="onoffswitch-label" for="myonoffswitch2${customer.customerid }" style="padding-left:0px !important; height:auto;">
						                       
						                        <span class="onoffswitch-inner">
						                        <span class="onoffswitch-active"><span class="onoffswitch-switch">Active</span></span>
						                        <span class="onoffswitch-inactive"><span class="onoffswitch-switch">Inactive</span></span>
						                        </span>
						                        </label>
						                      	</div>
									<!-- </div> -->
									<!-- <div class="row"> -->
									<div class="row">
										<div class="col s12 center-align">
										  <button class="btn waves-effect waves-light"  type="button" onclick="savestatuscustomer('${customer.customerid }')">Submit</button>
										</div>
									</div> 
										<%-- <div class="col s12 center-align" style="width:1px;margin-top: 10px;">
										 <img class="save-btn" src="../static/resources/images/saveicon.png" onclick="savestatuscustomer('${customer.customerid }')" ">
										</div> --%>
										
									<!-- </div>	 -->	
									  </c:when>
									   <c:otherwise>
									    
                         
									   
									
									<div class="onoffswitch margin-top-10" >
						                        <input name="onoffswitch" class="onoffswitch-checkbox"  id="myonoffswitch2${customer.customerid }"   type="checkbox">
						                        <label class="onoffswitch-label" for="myonoffswitch2${customer.customerid }" style="padding-left:0px !important; height:auto;">
						                       
						                        <span class="onoffswitch-inner">
						                        <span class="onoffswitch-active"><span class="onoffswitch-switch">Active</span></span>
						                        <span class="onoffswitch-inactive"><span class="onoffswitch-switch">Inactive</span></span>
						                        </span>
						                        </label>
						                      	</div>
									<!-- </div> -->
									<!-- <div class="row"> -->
										<div class="row">
										<div class="col s12 center-align">
										  <button class="btn waves-effect waves-light"  type="button" onclick="savestatuscustomer('${customer.customerid }')">Submit</button>
										</div>
									</div> 
									</c:otherwise>
									      </c:choose>
                          
						 </c:forEach> 
			
						  </div>
						</div>
						
					</div>
				</div>

			</div>
			
						 
			
      </section>
      <style>
		.save-btn{
		    border: 0;
    width: 24px;
    margin-top: 5px;
    color:blue;
		}
		
		</style>
      <jsp:include page="../template/footer.jsp"></jsp:include>
</body>
</html>