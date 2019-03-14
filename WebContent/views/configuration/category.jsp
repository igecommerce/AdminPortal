 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GAIA - Category Configuration</title>
</head>
<jsp:include page="../template/menu.jsp"></jsp:include>
<script type="text/javascript" src="../static/resources/js/forms/category/category.js"></script>
<body>

<div class="container">
          <div class="section">
			<div id="">
			<div class="row margin-bottom-70">
			  
             <div class="col-md-6">
              	<h5 class="header">Category Configuration<a href="addcategory?v=0" class="right btn font-12">Create Category</a></h5>
			</div>	
			<div class="col-md-6">
             <select id="categorylevel" onchange="loadcategories(this.value)">
										  <option value="0">Category</option>
										  <option value="1">Pre Category</option>
										  <option value="2">Sub Category</option>
										  </select>
									</div>	
									
						
					
             
              <div class="row">
                <div class="col s12 m12">
                  <%-- <table id="categoryDT" class="responsive-table display" cellspacing="0">
					<!-- <button class="btn" type="submit" name="action" style="position:relative; top:30px;">Export X</button> -->
                    <thead>
                        <tr>
                            <th>S.No</th>
                            <th>Category Name</th>
                            <th>Category Display URI</th>
                            <th>Category Level</th>
                            <th>Product Count</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                 
                    <tbody>
                    <c:forEach items="${categoryList }" var="category">
                         <tr>
                            <td>${category.categoryname }</a></td>
                            <td>${category.categoryname }</td>
                            <td>${category.categoryurl }</td>
                            <td>${category.categorylevel }</td>
                            <td>${category.productcount }</td>
                            <td><a href="addcategory?v=${category.categoryid }" ><i class="mdi-action-input"></i></a></td>
                        </tr>
                        </c:forEach>
                    </tbody>
                  </table> --%>
                  
                  <iframe src="../category/categorylist?actionid=0" width="100%" height="400px;" style="border: none;" id="categoryiframe"></iframe>
                </div>
              </div> 
              
            </div> 
			</div>
		</div>
		
      <jsp:include page="../template/footer.jsp"></jsp:include>
</body>
</html>