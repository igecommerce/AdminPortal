 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GAIA - Banner Upload Configuration</title>
</head>
<jsp:include page="../template/menu.jsp"></jsp:include>
<script type="text/javascript" src="../static/resources/js/forms/banner/banner.js"></script>
<body onload="loadbulkupload()">

<div class="container">
          <div class="section">
			<div id="">
			<div class="row margin-bottom-70">
             <div class="col-md-12">
              	<h5 class="header">Banner Bulk Upload<a href="addbanner" class="right btn font-12">Bulk upload</a></h5>
			</div>	
			</div>
             
              <div class="row">
                <div class="col s12 m12">
                  <table id="bulkuploadDT" class=" responsive-table display ourstyletable" cellspacing="0">
					<!-- <button class="btn" type="submit" name="action" style="position:relative; top:30px;">Export X</button> -->
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Status</th>
                            <!-- <th>Phone</th> -->
                            <th>Type</th>
                           <th>Action</th>  
                            
                        </tr>
                    </thead>
                 
                    <tbody>
                      <c:forEach var="banner" items="${bannerList }">
                      <tr>
                            <td>${banner.bannername}</td>
                            <td>${banner.status}</td>
                            <td>${banner.bannertype}</td>
                             <td>${banner.action}</td> 
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