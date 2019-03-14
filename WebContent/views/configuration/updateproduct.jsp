<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GAIA - Import product</title>
</head>
<jsp:include page="../template/menu.jsp"></jsp:include>
<script type="text/javascript" src="../static/resources/js/forms/config/product.js"></script>
<body>
<script>
$(document).ready(function(){
	$(".collapsible>li>.collapsible-body").stop(!0, !1);
	$(".collapsible>li.13").addClass("active");
	$(".collapsible>li.13>.collapsible-body").stop(!0, !1).slideDown();
	
});
</script>
<div class="modal size fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <!-- <button type="button" class="close" data-dismiss="modal">&times;</button> -->
          <h5 class="modal-title">Please Wait your Request will be processing</h5>
        </div>
       
        	<img src="../../ecommerce/static/resources/images/loader_green.gif" style="width: 131px; margin-left: 224px;margin-top: 10PX;"/>
        	
      
        <div class="modal-footer margin">
       
        </div>
     
      
    </div>
  </div>
  
</div>
<div class="container">
          <div class="section">
			<div id="">
			<div class="row">
			  
             <div class="col s6">
              	<h5 class="header">Update Product</h5>
			</div>	
			 <div class="col s6">
						</div>	
			</div>
             
              <div class="row" style="margin-bottom:0">
                <div class="col s5 m5">
                   
                  <iframe src="../uploadservice/uploadview?id=1&form=productimport&type=0&sessionname=productimport&foldername=import" width="500px;" style="border: none"></iframe>
                </div>
              
               <input type="hidden" id="uploadedfilename" 
                   value="<%= request.getAttribute("uploadfilepath") == null ? "" :  request.getAttribute("uploadfilepath")%>">
              
										<div class="col s3 margin-top-20">
										  <button class="btn waves-effect waves-light"  type="button" onclick="updateproduct()">Update Product</button>
										</div>
										
										
									</div> 
									
              <div class="row" id="statusDIVid">
		
	        <table  class="table table-hover"  id="productUplaodStatusDT">
                <thead>
                    <tr >
                        <th>Row No </th>
                        <th>Product SKU</th>
                        <th>Status</th>
                        <th>Status / Error Desc</th>
                       
                    </tr>
                </thead>
                
            </table>
	    </div>
            </div> 
			</div>
		</div>





      <jsp:include page="../template/footer.jsp"></jsp:include>
</body>
</html>