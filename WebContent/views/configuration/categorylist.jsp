 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GAIA - Category Configuration</title>
</head>
<jsp:include page="../template/style.jsp"></jsp:include>
<body>
<script type="text/javascript" src="../static/resources/js/forms/category/category.js"></script>
<div class="container">
          <div class="section">
			<div id="">
			 
             
              <div class="row">
                <div class="col s12 m12">
                  <table id="categoryDT" class="" cellspacing="0">
					<!-- <button class="btn" type="submit" name="action" style="position:relative; top:30px;">Export X</button> -->
                    <thead>
                        <tr>
                            <th>S.N</th>
                            <th>Category Name</th>
                            <th>Category Display URI</th>
                            <th>Category Level</th>
                            <th>Product Count</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                 
                    <tbody>
                    <c:forEach items="${categoryList }" var="category">
                         <tr>
                            <td>${category.categoryname }</td>
                            <td>${category.categoryname }</td>	
                            <td>${category.categoryurl }</td>
                            <td>${category.categorylevel }</td>
                            <td>${category.productcount }</td>
                            <td>${category.status }</td> 
                            <td><a onclick="navigatecategory(${category.categoryid })" ><i class="mdi-action-input"></i></a></td>
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