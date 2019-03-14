 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GAIA - DashBoard</title>
</head>
<body>
<jsp:include page="../template/menu.jsp"></jsp:include>
 <div class="container">
          <div class="section">
			<div id="">
              <h4 class="header">Invoice</h4>
				
              <div class="row">
                <div class="col s12 m12">
                  <table id="data-table-simple" class="responsive-table display" cellspacing="0">
					<!-- <button class="btn" type="submit" name="action" style="position:relative; top:30px;">Export X</button> -->
                    <thead>
                        <tr>
                            <th>Invoice No</th>
                            <th>Invoice Date</th>
                            <th>Order#</th>
                            <th>Order Date</th>
                            <th>Bill to Name</th>
							<th>Status</th>
							<th>Amount</th>
							<th>Take Print PDF Format</th>
                        </tr>
                    </thead>
                 
                    <tbody>
                        <tr>
                            <td>51212458</a></td>
                            <td>18.03.2018</td>
                            <td>BG25486</td>
                            <td>25.02.2018</td>
                            <td>Delivered</td>
							<td>Your Name</td>
							<td>AED 24.00</td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="mdi-action-print print-icon"></i></td>
                        </tr>
						<tr>
                            <td>4521364</a></td>
                            <td>15.03.2018</td>
                            <td>AD78631</td>
                            <td>20.02.2018</td>
                            <td>Pending</td>
							<td>Name</td>
							<td>AED 14.00</td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="mdi-action-print print-icon"></i></td>
                        </tr>
						<tr>
                            <td>2458796</a></td>
                            <td>25.03.2018</td>
                            <td>AD78631</td>
                            <td>05.03.2018</td>
                            <td>Delivered</td>
							<td>Your Name</td>
							<td>AED 20.00</td>
							<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class="mdi-action-print print-icon"></i></td>
                        </tr>
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