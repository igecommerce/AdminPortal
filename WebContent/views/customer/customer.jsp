 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GAIA - Customer</title>
</head>
<script type="text/javascript" src="../static/resources/js/forms/order/order.js"></script>
<body onload="loadcustomer()">
<jsp:include page="../template/menu.jsp"></jsp:include>
<div class="container">
          <div class="section">
			<div id="">
              <h4 class="header">Customers</h4>
				
              <div class="row">
                <div class="col s12 m12">
                  <table id="customerDT" class="responsive-table display ourstyletable" cellspacing="0">
					<!-- <button class="btn" type="submit" name="action" style="position:relative; top:30px;">Export X</button> -->
                    <thead>
                        <tr>
                            <th>Customer Name</th>
                            <th>Email</th>
                            <th>Phone</th>
                          <!--   <th>Status</th> -->
                            <th>Action</th>
                        </tr>
                    </thead>
               
                    <tbody>
                    <c:forEach var="customer" items="${customerlist }">
                        <tr>
                       
                            <td>${customer.username}</td>
                            <td>${customer.email}</td>   
                            <td>${customer.mobile}</td>
                          <%--   <td>${customer.status}</td> --%>
                         
                          <!-- <div class="col s8" > -->
                         <%--  <c:choose>
                          <c:when test = "${customer.status == 1}">
                           <td>
									<div class="onoffswitch margin-top-10" >
						                        <input name="onoffswitch" class="onoffswitch-checkbox"  id="myonoffswitch1${customer.customerid }" checked=""  type="checkbox">
						                        <label class="onoffswitch-label" for="myonoffswitch1${customer.customerid }" style="padding-left:0px !important; height:auto;">
						                       
						                        <span class="onoffswitch-inner">
						                        <span class="onoffswitch-active"><span class="onoffswitch-switch">Active</span></span>
						                        <span class="onoffswitch-inactive"><span class="onoffswitch-switch">Inactive</span></span>
						                        </span>
						                        </label>
						                      	</div>
									<!-- </div> -->
									<!-- <div class="row"> -->
										<div class="col s12 center-align" style="width:1px;margin-top: 10px;">
										 <img class="save-btn" src="../static/resources/images/saveicon.png" onclick="savestatuscustomer('${customer.customerid }')" ">
										</div>
										</td>
									<!-- </div>	 -->	
									  </c:when>
									   <c:otherwise>
									    
                         
									   
									 <td>
									<div class="onoffswitch margin-top-10" >
						                        <input name="onoffswitch" class="onoffswitch-checkbox"  id="myonoffswitch1${customer.customerid }"   type="checkbox">
						                        <label class="onoffswitch-label" for="myonoffswitch1${customer.customerid }" style="padding-left:0px !important; height:auto;">
						                       
						                        <span class="onoffswitch-inner">
						                        <span class="onoffswitch-active"><span class="onoffswitch-switch">Active</span></span>
						                        <span class="onoffswitch-inactive"><span class="onoffswitch-switch">Inactive</span></span>
						                        </span>
						                        </label>
						                      	</div>
									<!-- </div> -->
									<!-- <div class="row"> -->
										<div class="col s12 center-align" style="width:1px;margin-top: 10px;">
										  <img  class="save-btn" src="../static/resources/images/saveicon.png" onclick="savestatuscustomer('${customer.customerid }')" ">
										  
										</div>
										</td>
									</c:otherwise>
									      </c:choose> --%>
                            <td>${customer.action}</td>
                            
                        </tr>
						 </c:forEach> 
						 
                    </tbody>
                  </table> 
                </div>
              </div>
            </div> 
			</div>
		</div>
		<script type="text/javascript">
		function customerdetails(customerid){
			window.location.href="customerdetails?v="+customerid;
		}
		</script>
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