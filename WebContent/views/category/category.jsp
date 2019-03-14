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
<body onload="loadcategory()">

<div class="container">
          <div class="section">
			<div id="">
			<div class="row "> 
			  
             <div class="col-md-6">
              	<h5 class="header">Category Configuration<a href="addcategory?v=0" class="right btn font-12">Create Category</a></h5>
			</div>	
			<!--  <h6  class="col-md-6" style="width:50%;margin-top:35px;color:#f3476f;font-size: 1.10rem;">Select Category:</h6> 
			<div class="col-md-6 margin_sapce"" style="width:50%;">
             <select id="categorylevel" onchange="loadcategories(this.value)">
										  <option value="0">Category</option>
										  <option value="1">PreCategory</option>
										  <option value="2">SubCategory</option>
										  </select>
										  </div>  -->
				
							<div class="col-md-12" style="margin-top:47px;width: 82%;margin-left:81px;">



<ul class="collapsible" data-collapsible="accordion">

<c:forEach items="${categoryList }" var="category">
<!-- category node -->
<li>
		<div class="collapsible-header" style="background-color: #e98876;color:white;">
			<div class="col s12 m5">
		${category.categoryname }
			</div>
		<div class="col s12 m2">
		${category.status }
		
	</div>
	 <div class="col s12 m1">
		              ${category.productcount }
		
	                 </div>
	<div class="col s12 m1">
	<a onclick="navigatecategory(${category.categoryid })"  style="color:white;"><i class="mdi-content-create"></i></a>
	</div>
	<div class="col s12 m3">
	<i class="mdi-hardware-keyboard-arrow-down" style="margin-left:151px;"></i>
	</div>
			</div>
		<div class="collapsible-body" style="margin-bottom:-25px;">
			<div class="row">
				<div class="col s12 m12">
					<ul class="collapsible" data-collapsible="accordion">
<c:forEach items="${preCategoryList }" var="precategory">
<c:choose>
<c:when test = "${ category.categoryid == precategory.parentid }">
<!-- pre category node -->
	<li>
							<div class="collapsible-header" style="background-color:#d7783c78;" >
							<div class="col s12 m5">
								${precategory.categoryname }
								</div>
								<div class="col s12 m2">
		              ${precategory.status }
		
	                 </div>
	                <div class="col s12 m1">
		              ${precategory.productcount }
		
	                 </div>
		<div class="col s12 m1">
	<a onclick="navigatecategory(${precategory.categoryid })"  style="color:black;"><i class="mdi-content-create"></i></a>
	</div>
	<div class="col s12 m3">
	<i class="mdi-hardware-keyboard-arrow-down" style="margin-left:151px;"></i>
	</div>
								</div>
</c:when>
</c:choose>
<c:forEach items="${subCategoryList }" var="subcategory">
<c:choose>
<c:when test = "${ precategory.categoryid == subcategory.parentid }">
<!-- sub category node -->

					 <div class="collapsible-body" style="background-color: #e9ddd5;"> 
				 	
					
								<div class="col s12 m5" >
								<div class="collapsible-header" style="background-color: #e9ddd5;" >
								${subcategory.categoryname }
								</div>
								</div>
								<div class="col s12 m2">
								<div class="collapsible-header" style="background-color: #e9ddd5;" >
								${subcategory.status }
								</div>
								</div>
								<div class="col s12 m1">
								<div class="collapsible-header" style="background-color: #e9ddd5;" >
								${subcategory.productcount }
								</div>
								</div>
								<div class="col s12 m1">
								<div class="collapsible-header" style="background-color: #e9ddd5;" >
	<a onclick="navigatecategory(${subcategory.categoryid })"  style="color:black;"><i class="mdi-content-create"></i></a>
	</div>
	</div>
	<div class="collapsible-header" style="background-color: #e9ddd5;" >
	
	<!-- <i class="mdi-hardware-keyboard-arrow-down"></i> -->
	</div>
	
							</div>
</c:when>
</c:choose>
</c:forEach>
</li>
</c:forEach>

</ul>
</div>
</div>
</div>
</li>


</c:forEach>
</ul>	


					
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
		<style>
		.margin_sapce{
		margin-left: 248px;
		margin-top: -40px;
		}
		
		</style>
      <jsp:include page="../template/footer.jsp"></jsp:include>
</body>
</html>