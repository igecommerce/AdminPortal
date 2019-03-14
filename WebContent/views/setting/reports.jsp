 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GAIA - Reports</title>
</head>
<body>
<jsp:include page="../template/menu.jsp"></jsp:include>
<div class="container">
          <div class="section">
			<div id="">
              <h4 class="header">Reports</h4>
				
              <div class="row">
                <div class="col s12 m12">
                  <table id="data-table-simple" class="responsive-table display" cellspacing="0">
					<button class="btn" type="submit" name="action" style="position:relative; top:30px;">Export X</button>
                    <thead>
                        <tr>
                            <th>Customer Report</th>
                            <th>Product Report</th>
                            <th>Sales Report</th>
                            <th>Sales Order Item Report</th>
                        </tr>
                    </thead>
                 
                    <tbody>
                        <tr>
                            <td>Lorem Ipsum</td>
                            <td>Lorem Ipsum</td>
                            <td>Lorem Ipsum</td>
                            <td>Lorem Ipsum</td>
                        </tr>
						<tr>
                            <td>Lorem Ipsum</td>
                            <td>Lorem Ipsum</td>
                            <td>Lorem Ipsum</td>
                            <td>Lorem Ipsum</td>
                        </tr>
						<tr>
                            <td>Lorem Ipsum</td>
                            <td>Lorem Ipsum</td>
                            <td>Lorem Ipsum</td>
                            <td>Lorem Ipsum</td>
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