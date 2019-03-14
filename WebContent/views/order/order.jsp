<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GAIA - Orders</title>
</head>
<script type="text/javascript" src="../static/resources/js/forms/order/order.js"></script>
<body onload="loadorders()">
<jsp:include page="../template/menu.jsp"></jsp:include>
	<div class="container">
          <div class="section">
			<div id="">
              <h4 class="header">Orders</h4>
				
              <div class="row">
                <div class="col s12 m12">
                <!-- <button class="btn" onclick="exportTable()" style="position:relative; top:30px;">Export </button> -->
               <!--  <button class="btn" onclick="exportOrderTable()">Export </button> -->
                <div style="clear:both" /></div>
                  <table id="orderDT" class="responsive-table display ourstyletable" cellspacing="0">
					
                    <thead>
                        <tr>
                             <th>Order ID</th> 
                            <th>Customer</th>
                            <th>Product</th> 
                            <th>Total Price</th>
                            <!-- <th>Status</th>
							<th>Ordered Date</th> -->
							<th>MobileNo</th>
							<th>Action</th>
                        </tr>
                    </thead>
                 
                  <!--   <tbody>
                        <tr>
                            <td>51212458</a></td>
                            <td>Your Name</td>
                            <td>AD78631</td>
                            <td>AED 12.00</td>
                            <td>Delivered</td>
							<td>15.03.2018</td>
							<td>0123456789</td>
                        </tr>
						<tr>
                            <td>2547862</a></td>
                            <td>Name</td>
                            <td>AD74531</td>
                            <td>AED 24.00</td>
                            <td>Pending</td>
							<td>05.03.2018</td>
							<td>9874563210</td>
                        </tr>
						<tr>
                            <td>21547863</a></td>
                            <td>Yr Name</td>
                            <td>RD24587</td>
                            <td>AED 34.00</td>
                            <td>Delivered</td>
							<td>25.03.2018</td>
							<td>4567891230</td>
                        </tr>
						<tr>
                            <td>95478631</a></td>
                            <td>Your Nme</td>
                            <td>124759</td>
                            <td>AED 44.00</td>
                            <td>Delivered</td>
							<td>28.03.2018</td>
							<td>1234567890</td>
                        </tr>
                    </tbody> -->
                  </table>
                </div>
              </div>
            </div> 
			</div>
		</div>
		
<style>
div.dt-buttons {
   
    margin-bottom: 10px;
}
a {
     color:black;
    text-decoration: none;
    -webkit-tap-highlight-color: transparent;
}
</style>
			 <jsp:include page="../template/footer.jsp"></jsp:include>
</body>
</html>