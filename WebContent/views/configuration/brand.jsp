 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GAIA - Brand</title>
</head>
<jsp:include page="../template/menu.jsp"></jsp:include>
<script type="text/javascript" src="../static/resources/js/forms/config/product.js"></script>
<body onload="loadbrand()">
<script>
$(document).ready(function(){
	$(".collapsible>li>.collapsible-body").stop(!0, !1);
	$(".collapsible>li.9").addClass("active");
	$(".collapsible>li.9>.collapsible-body").stop(!0, !1).slideDown();
	
});
</script>
<div class="container">
          <div class="section">
			<div id="">
			<div class="row margin-bottom-70">
             <div class="col-md-12">
              	<h5 class="header">Brand<a href="addbrand?v=0" class="right btn font-12">Create brand</a></h5>
			</div>	
			</div>
             
              <div class="row">
                <div class="col s12 m12">
                  <table  id="brandDT" class=" responsive-table display ourstyletable" cellspacing="0">
					<!-- <button class="btn" type="submit" name="action" style="position:relative; top:30px;">Export X</button> -->
                    <thead style="background: #f56163;color: #fff;">
                        <tr>
                            <th>Brand Name</th>
                           <th>Status</th>
                            <th>Action</th>
                        </tr>
                    </thead>
               
                    <tbody> 
                       <c:forEach var="brand" items="${brandList }">
                       <tr> 										
								<td>	${brand.brandname}</td>
								<td>	${brand.status}</td>
								<td>	${brand.action}</td>	
									
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