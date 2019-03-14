<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<jsp:include page="../template/style.jsp"></jsp:include>

<body>

<form action="savefile?id=${id }&type=${type}&form=${form}&sessionname=${sessionname}&foldername=${foldername}" method="post" enctype="multipart/form-data">  

<span id="messagetext" style="color:green;" > ${message}</span>
<div class="width_100 padd-top-bottm">
<div class="width_100" class="loader" id="image" style="margin-top: -42px;margin-bottom:-62px;">

</div> 
<div class="width_40 margin-top-30">
<div class="">
<!-- Select File to Upload:  --><input type="file" name="file" id="file"/>  
</div>
</div>
<div class="width_20 margin-top-20">
<!-- <input type="submit" value="Upload File" class="btn" />  --> 
 <button class="btn waves-effect waves-light"  type="button" onclick="uploadfile()">Upload File</button> 
</div>
<div class="width_30">
<img src="../gaiafiles/documents/${form}/${foldername }/${id}.jpg" class="myImg" >
</div>
<input type="hidden" id="extensionimagetext" value="${form}">
</div>
 <script>
function uploadfile(){
	if ($('#file').get(0).files.length === 0) {
		$('#messagetext').text("Please select the File");
		$('#messagetext').css({"color":"red"});
	    return false;
	}
	else{
		$('#messagetext').css({"color":"green"});
	}
var imagetype=$('#extensionimagetext').val();
console.log(imagetype)
	var form_data = new FormData();  
	var file_data = $("#file").prop("files")[0];
	form_data.append("file", file_data)  
	if(imagetype == "user" || imagetype == "category" || imagetype == "product"){
		if (file_data.type.match('image/jpeg') || file_data.type.match('image/png'))  {
			if (file_data.size > 5242880){
				$('#messagetext').text("Image size should be less than 2 MB");
				$('#messagetext').css({"color":"red"});
				return false;
				
				}  else{
					$('#messagetext').css({"color":"green"});
				}
			}
		
		else{
			$('#messagetext').text("This is not an valid image file!");
			$('#messagetext').css({"color":"red"});
			return false;
		}
	}
	if(imagetype == "productimport"){
		 var fileInput = document.getElementById('file');
		    var filePath = fileInput.value;
		    var allowedExtensions = /(\.xlsx)$/i;
		    if(!allowedExtensions.exec(filePath)){
		    	$('#messagetext').text("Please upload Valid Excel File");
		    	$('#messagetext').css({"color":"red"});
		        fileInput.value = '';
		        return false;
		    }
		
	}
	
	
	$('#image').html("<img src='../../ecommerce/static/resources/images/loader_green.gif' width='30%' />" );

	$.ajax({
		url : '../uploadservice/savefile?id=${id }&type=${type}&form=${form}&sessionname=${sessionname}&foldername=${foldername}', 
	
		type : 'POST',
		cache: false,
	    contentType: false,
	    processData: false,
	    data: form_data, 
		success:function(data,textStatus,jqXHR){
			
			 $('#image').hide();
			
			$('#messagetext').text("File Uploaded Successfully");
		}
		
		
		
	});
}

</script>
 
 
 </form>
 
<style type="text/css">

.margin-top-30{
margin-top:30px;
}
.margin-top-20{
margin-top:20px;
}
.myImg{
	width:50%;
	height:70px;
	padding:0px;
	border-radius:50%;
}
.width_100{
width:100%;
display:block;
float:left;
}

.width_50{
width:50%;
display:block;
float:left;
}
.width_30{
width:30%;
display:block;
float:left;
}
.width_40{
width:40%;
display:block;
float:left;
}
.width_20{
width:20%;
display:block;
float:left;
}
.width_70{
width:65%;
display:block;
float:left;
padding:20px 0;
}
		.logoUploadDis {
		    width: 100%;
		    max-height: 90px;
		    background-color: #FBFBFB;
		    border: 1px dashed #DDD;
		    border-radius: 4px;
		    float: left;
		    position: relative;
		    text-align: center;
		    font-size:13px;
		    font-family: Calibri;
		}
		.file_style{
			float: left;
		    font-size: 13px;
		    font-family: calibri;
		    padding-top: 5px;
		    width: 100%;
		}
		.btn{
		    color: #fff;
		    background-color: #ff5a92;
		    border-color: #ff5a92;
	    	box-shadow: inset 0 3px 5px rgba(0,0,0,.125);
	        display: inline-block;
	    padding: 4px 8px;
	    margin: 0;
	    margin-bottom: 0;
	    font-size: 11px;
	    font-weight: 400;
	    line-height: 1.42857143;
	    text-align: center;
        background-image: none;
	    border: 1px solid transparent;
	    border-radius: 4px;
			}
input[type="file"]:focus  {
    border:0;
	outline:0;
	background:transparent;
	box-shadow:none !important;
	border-radius:0px;
	font-size:12px;
	font-weight:normal;
}
.loader{
margin-left:240px;
margin-top: -500px;
}

	</style>

</body>
</html>
  --%>
  <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<jsp:include page="../template/style.jsp"></jsp:include>

<body>

<%-- <form action="savefile?id=${id }&type=${type}&form=${form}&sessionname=${sessionname}&foldername=${foldername}" method="post" enctype="multipart/form-data"> --%>  

<span id="messagetext" style="color:green;" > ${message}</span>
<div class="width_100 padd-top-bottm">
<div class="width_100" class="loader" id="image" style="margin-top: -42px;margin-bottom:-62px;">

</div> 
<div class="width_40 margin-top-30">
<div class="">
<!-- Select File to Upload:  --><input type="file" name="file" id="file" onchange="loadFile(event)"/>  
</div>
</div>
<div class="width_20 margin-top-20">
<!-- <input type="submit" value="Upload File" class="btn" />  --> 
 <button class="btn waves-effect waves-light"  type="button" onclick="uploadfile()">Upload File</button> 
</div>
<div class="width_30">
<img src="../gaiafiles/documents/${form}/${foldername }/${id}.jpg" class="myImg" onchange="loadFile(event)" id="output" >
</div>
<input type="hidden" id="extensionimagetext" value="${form}">
</div>

 <script>
function uploadfile(){
	if ($('#file').get(0).files.length === 0) {
		$('#messagetext').text("Please select the File");
		$('#messagetext').css({"color":"red"});
	    return false;
	}
	else{
		$('#messagetext').css({"color":"green"});
	}
var imagetype=$('#extensionimagetext').val();
console.log(imagetype)
	var form_data = new FormData();  
	var file_data = $("#file").prop("files")[0];
	form_data.append("file", file_data)  
	if(imagetype == "user" || imagetype == "category" || imagetype == "product" ||imagetype =="banner"){
		if (file_data.type.match('image/jpeg') || file_data.type.match('image/png'))  {
			if (file_data.size > 5242880){
				$('#messagetext').text("Image size should be less than 2 MB");
				$('#messagetext').css({"color":"red"});
				return false;
				
				}  else{
					$('#messagetext').css({"color":"green"});
				}
			}
		
		else{
			$('#messagetext').text("This is not an valid image file!");
			$('#messagetext').css({"color":"red"});
			return false;
		}
	}
	if(imagetype == "productimport"){
		 var fileInput = document.getElementById('file');
		    var filePath = fileInput.value;
		    var allowedExtensions = /(\.xlsx)$/i;
		    if(!allowedExtensions.exec(filePath)){
		    	$('#messagetext').text("Please upload Valid Excel File");
		    	$('#messagetext').css({"color":"red"});
		        fileInput.value = '';
		        return false;
		    }
		
	}
	
	
	$('#image').html("<img src='../../ecommerce/static/resources/images/loader_green.gif' width='30%' />" );

	$.ajax({
		url : '../uploadservice/savefile?id=${id }&type=${type}&form=${form}&sessionname=${sessionname}&foldername=${foldername}', 
	
		type : 'POST',
		cache: false,
	    contentType: false,
	    processData: false,
	    data: form_data, 
		success:function(data,textStatus,jqXHR){
			
			 $('#image').hide();
			
			$('#messagetext').text("File Uploaded Successfully");
		}
		
		
		
	});
}

</script>
 <script>
 var imagetype=$('#extensionimagetext').val();
 if(imagetype == "user" || imagetype == "category" || imagetype == "product" || imagetype =="banner"){
	
  var loadFile = function(event) {
    var output = document.getElementById('output');
    output.src = URL.createObjectURL(event.target.files[0]);
  }
  };
</script>
 
 </form>
 
<style type="text/css">

.margin-top-30{
margin-top:30px;
}
.margin-top-20{
margin-top:20px;
}
.myImg{
	width:50%;
	height:70px;
	padding:0px;
	border-radius:50%;
}
.width_100{
width:100%;
display:block;
float:left;
}

.width_50{
width:50%;
display:block;
float:left;
}
.width_30{
width:30%;
display:block;
float:left;
}
.width_40{
width:40%;
display:block;
float:left;
}
.width_20{
width:20%;
display:block;
float:left;
}
.width_70{
width:65%;
display:block;
float:left;
padding:20px 0;
}
		.logoUploadDis {
		    width: 100%;
		    max-height: 90px;
		    background-color: #FBFBFB;
		    border: 1px dashed #DDD;
		    border-radius: 4px;
		    float: left;
		    position: relative;
		    text-align: center;
		    font-size:13px;
		    font-family: Calibri;
		}
		.file_style{
			float: left;
		    font-size: 13px;
		    font-family: calibri;
		    padding-top: 5px;
		    width: 100%;
		}
		.btn{
		    color: #fff;
		    background-color: #ff5a92;
		    border-color: #ff5a92;
	    	box-shadow: inset 0 3px 5px rgba(0,0,0,.125);
	        display: inline-block;
	    padding: 4px 8px;
	    margin: 0;
	    margin-bottom: 0;
	    font-size: 11px;
	    font-weight: 400;
	    line-height: 1.42857143;
	    text-align: center;
        background-image: none;
	    border: 1px solid transparent;
	    border-radius: 4px;
			}
input[type="file"]:focus  {
    border:0;
	outline:0;
	background:transparent;
	box-shadow:none !important;
	border-radius:0px;
	font-size:12px;
	font-weight:normal;
}
.loader{
margin-left:240px;
margin-top: -500px;
}

	</style>

</body>
</html>
 