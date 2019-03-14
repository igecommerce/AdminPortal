 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GAIA - DashBoard</title>
</head>
<jsp:include page="../template/menu.jsp"></jsp:include>
<script type="text/javascript" src="../static/resources/js/forms/login/login.js"></script>
<body onload="loadusers()">
<script>
$(document).ready(function(){
	$(".collapsible>li>.collapsible-body").stop(!0, !1);
	$(".collapsible>li.2").addClass("active");
	$(".collapsible>li.2>.collapsible-body").stop(!0, !1).slideDown();
});
</script>
<div class="container">
          <div class="section">
			<div id="">
			<div class="row margin-bottom-70">
             <div class="col-md-12">
              	<h5 class="header">User<a href="adduser?v=0" class="right btn font-12">Create User</a></h5>
			</div>	
			</div>
             
              <div class="row">
                <div class="col s12 m12">
                  <table id="userDT" class=" responsive-table display ourstyletable" cellspacing="0">
					<!-- <button class="btn" type="submit" name="action" style="position:relative; top:30px;">Export X</button> -->
                    <thead>
                        <tr>
                            <th>User Name</th>
                            <th>Email</th>
                            <!-- <th>Phone</th> -->
                            <th>Role</th>
                             <th>Status</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                 
                    <tbody>
                       <!--  <tr>
                            <td>Saras</a></td>
                            <td>saras@gmail.com</td>
                            <td>0123456789</td>
                            <td>Senior Manager</td>
                            <td><a href="adduser?userid=0" ><i class="mdi-action-input"></i></a></td>
                        </tr>
						<tr>
                            <td>Priya</a></td>
                            <td>priya@gmail.com</td>
                            <td>7894561230</td>
                            <td>Manager</td>
                            <td><i class="mdi-action-input"></i></td>
                        </tr>
						<tr>
                            <td>Ram</a></td>
                            <td>ram@gmail.com</td>
                            <td>8794563210</td>
                            <td>Owner</td>
                            <td><i class="mdi-action-input"></i></td>
                        </tr> -->
                    </tbody>
                  </table>
                </div>
              </div>
            </div> 
			</div>
		</div>
		
      <jsp:include page="../template/footer.jsp"></jsp:include>
     <!--  <style>
      table.ourstyletable thead tr{
      background: #f56163;
    	color: #fff;
      }
      table.ourstyletable thead tr th{
      padding:5px 10px !important;
      border-bottom: none !important;
      }
      </style> -->
</body>
</html>