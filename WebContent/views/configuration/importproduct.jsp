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
<body >
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
        <p class="modal-title">Do You Want to Cancel The Transaction ? </p>  
         <button id="canceltext" type="button" class="btn btn-secondary"  style="margin: 13px 169px;" >No</button>
          <button id="canceltext" type="button" class="btn btn-secondary" onclick="cancelimport()" style="margin: 13px -154px;">Yes</button>
        </div>
     
      
    </div>
  </div>
  
</div>
<div class="modal size fade" id="instruction" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <!-- <button type="button" class="close" data-dismiss="modal">&times;</button> -->
          <h5 class="modal-title">Instruction For Import</h5>
        </div>
 
        <p>1.We Need To Download Sample Format.</p>
        <p>2.We Should Fill All Mandatory Fields.</p>
         <p>3.If The Fields Has Any Dropdown,Then Select The List From Dropdown.Do Not Make a Field Manually. </p>
        <p>4.Move The Product Images On /var/lib/tomcat8/webapps/documents/product/temp Folder And Give The Image name Of Temp To Corresponding Image Fields. </p>
         <p>5.If You Want To Fill The Offer Start date/End date,Keep Follow Our System Date Formation. </p>
       
        <p>6.Then Upload The File By Using Upload File Button.</p>
        <p>7.After Finishing Uploaded,Import The Excel By Using Import Product Button.</p>
      
        <div class="modal-footer margin">
        <button type="button" class="btn btn-secondary" onclick="modalClose('instruction')">Close</button>
        </div>
     
      
    </div>
  </div>
  
</div>
<div class="container">                                   
          <div class="section">
			<div id="">
			<div class="row">
			  
             <div class="col s6">
              	<h5 class="header">Import Product</h5>
			</div>	 
			  
			<button class="btn waves-effect waves-light right " type="button" name="action" onclick="instructionforimport()" style="margin-top:10px;">Instructions For Import</button>
			 <div class="col s6">
						</div>	
			</div>
                <form class="form-horizontal" id="myForm"  enctype="multipart/form-data">
  
    <!-- File Button 
     <div class="form-group">
      <label class="col-md-4 control-label" for="papers" id="messagetext">
        Add Bulk Images                
      </label>
      <div class="col-md-4" >
         
        <input name="papers" id="modalPapers" type="file" class="filestyle" multiple data-input="false">
       </div>
      <label class="col-md-4 control-label" for="submit"></label>
       <div class="col-md-4" >
        <input type="button" value="Submit" name="submit" onclick="submitForm()"  >
     </div>
    </div>
     Button -->
   
 
</form>
                                                
              <div class="row" style="margin-bottom:0">
                <div class="col s5 m5">
                   
                  <iframe src="../uploadservice/uploadview?id=1&form=productimport&type=0&sessionname=productimport&foldername=import" width="500px;" style="border: none"></iframe>
                </div>
              
               <input type="hidden" id="uploadedfilename" 
                   value="<%= request.getAttribute("uploadfilepath") == null ? "" :  request.getAttribute("uploadfilepath")%>">
              
										<div class="col s3 margin-top-20">
										  <button class="btn waves-effect waves-light"  type="button" onclick="importtoproduct()">Import Product</button>
										</div>
										<div class="col s4 margin-top-20">
										<button class="btn waves-effect waves-light right " type="button" name="action" onclick="downloadsampleformat()">Download Sample Format</button>
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
		
		
		<div class="modal fade" id="productModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" style="margin-top: -246px;">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h6 class="modal-title" id="exampleModalLabel">How many products upload?</h6>
      </div>
      <div class="modal-body">
      	<form class="col s12" >
									<div class="row">
										<div class="input-field col s6">
										  <input id="sheetcount" type="number" class="validate">
										  <label for="oldpassword">Row Count</label>
										</div>
										
									</div>
								</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" onclick="modalClose('productModal')">Close</button>
        <button type="button" class="btn btn-primary" onclick="saveexcelcolumn()" style="margin: 8px 4px;">Create Sheet</button>
      </div>
    </div>
  </div>
</div>
	 <script type="text/javascript">
function submitForm() {

	  var paperElement = document.getElementById("modalPapers");

	  if (!paperElement.value) {
	    console.log("No files selected.")
	    return;
	  }else{
		  $('#messagetext').text("Upload Successfully");
		  $('#messagetext').css({"color":"green"});
	  }
	  var digits = Math.floor(Math.random() * 9000000000) + 1000000000;
	  var form = document.getElementById("myForm");
	  var formData = new FormData(form);
	  formData.append("random", digits);  
	   console.log(digits)
	  $.ajax({
			url : '../uploadservice/submitFiles', 
			
		
			type : 'POST',
			cache: false,
		    contentType: false,
		    processData: false,
		    data: formData,
		    success:function(data,textStatus,jqXHR){
		    	console.log(data)
		    }
	 /*  var xhr = getXMLHTTP();
	  xhr.open('POST', "submitFiles");
	  xhr.onreadystatechange = function() {
	    if (xhr.readyState == 4 && xhr.status == 200) {
	      console.log("Files Uploaded")
	    }
	  }
	  xhr.send(formData); */
	  });
}
</script>
			
	<script type="text/javascript">
	function instructionforimport(){
		$('#instruction').show();
	}
	
	</script>
      <jsp:include page="../template/footer.jsp"></jsp:include>
</body>
</html>