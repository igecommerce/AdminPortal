<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>GAIA - Bulkupload Configuration</title>
</head>
<jsp:include page="../template/menu.jsp"></jsp:include>

<script type="text/javascript" src="../static/resources/js/forms/banner/banner.js"></script>
<body >


			
			<div class="container">
          <div class="section">
			<div id="">
			<div class="row">
			  
             <div class="col s6">
              	<!-- <h5 class="header">Bulk Upload</h5> -->
			</div>	
			 <div class="col s6">
						</div>	
			</div>
              <div class="row" style="margin-bottom:0">
              <h5 class="header">Banner Upload</h5>
                <div class="col s5 m5">
                   
                  <iframe src="../uploadservice/uploadview?id=1&form=productimport&type=0&sessionname=productimport&foldername=import" width="500px;" style="border: none"></iframe>
                </div>
              
               <input type="hidden" id="uploadedfilename" 
                   value="<%= request.getAttribute("uploadfilepath") == null ? "" :  request.getAttribute("uploadfilepath")%>">
              
										<div class="col s3 margin-top-20">
										  <button class="btn waves-effect waves-light"  type="button" onclick="importbulkuploadbannertype()">Import Banner</button>
										</div>
										<div class="col s4 margin-top-20">
										<button class="btn waves-effect waves-light right " type="button" name="action" onclick="downloadbulkuploadformatforbannertype()">Download Sample Format</button>
										</div>
										
									</div> 
              <div class="row" style="margin-bottom:0">
                <h5 class="header">Banner Image Upload</h5>
                <div class="col s5 m5">
                   
                  <iframe src="../uploadservice/uploadview?id=1&form=productimport&type=0&sessionname=productimport&foldername=import" width="500px;" style="border: none"></iframe>
                </div>
              
               <input type="hidden" id="uploadedfilename" 
                   value="<%= request.getAttribute("uploadfilepath") == null ? "" :  request.getAttribute("uploadfilepath")%>">
              
										<div class="col s3 margin-top-20">
										  <button class="btn waves-effect waves-light"  type="button" onclick="importbulkupload()">Import Banner</button>
										</div>
										<div class="col s4 margin-top-20">
										<button class="btn waves-effect waves-light right " type="button" name="action" onclick="downloadbulkuploadformat()">Download Sample Format</button>
										</div>
										
									</div> 
									
              <div class="row" id="statusDIVid">
		
	        <table  class="table table-hover"  id="productUplaodStatusDT">
                <thead>
                    <tr >
                        <th>Row No </th>
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