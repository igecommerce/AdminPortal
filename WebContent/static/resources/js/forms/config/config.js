function createcategory(){
	var categoryid = getParameterByName('v');
	
	var url = $("#categorydisplayid").val();
	var validurl = /^(http|https)?:\/\/[a-zA-Z0-9-\.]+\.[a-z]{2,4}/;
	
	if(!validurl.test(url))
	{
		displayAlertMsg("Invalid URL")
		return false;
		
	}
	
	var categoryObject = new Object();
	categoryObject.categoryid = categoryid;
	categoryObject.categoryname = $("#categoryname").val();
	categoryObject.categoryurl = $("#categorydisplayid").val();
	categoryObject.categorylevel = $("#categorylevel").val();
	categoryObject.precategoryid = $("#precategoryid").val();
	/*categoryObject.status = $("#categorystatus").val();*/
	
	categoryObject.displayorder = $("#categorypositionid").val();
	categoryObject.action = $('#actiontext').val();

	if($('#categorystatus').is(':checked')){
		categoryObject.status = 1;
	}else{
		categoryObject.status = 0;
	}
	
	console.log(JSON.stringify(categoryObject))
	$.ajax({
			type : 'POST',
			contentType : 'application/json',
			url : '../configurationservices/createcategory',
			data : JSON.stringify(categoryObject),
			success:function(data,textStatus,jqXHR){
				var responseType = data.responseValue.responseType;
				var responseValue = getResponseValue(data);
				 
				if(responseType == 'S')
					{
					displayAlertMsg(responseValue.o_out_msg);	
					if(responseValue.o_out_flag == 'S')
						{
						window.location.href = "../configuration/addcategory?v=0";
						/*if($('#categoryidtext').val() == '0')
							{
							resetFormfields();	
							}
						else
							{
							setTimeout(function(){ window.location.href = "../configuration/addcategory?v=0"; }, 3000);
							}*/
						}
					
					}
				else
					{
					console.log(data)
					}
			}
		});
}

function getpresubcategories(value)
{
	if ( value == '0')
    {
      $("#presubcategoryforhide").hide();
    }
    else
    {
      $("#presubcategoryforhide").show();
    
	
	$.ajax({
		type : 'GET',
		contentType : 'application/json',
		url : '../configurationservices/getpresubcategory/'+value,
		data : JSON.stringify(value),
		success:function(data,textStatus,jqXHR){
			console.log(data)
			var responseType = data.responseValue.responseType;
			var responseValue = getResponseValue(data);
			 
			if(responseType == 'S')
				{
				$('#precategoryid').html('');
				if(responseValue.length >0){
				for (i = 0; i < responseValue.length; i++)
				{ 
					var obj = responseValue[i];
					//console.log(obj);
				     $('#precategoryid').append($('<option>',
				     {
				        value: obj.categoryid,
				        text : obj.categoryname
				    }));
				}
				}else{
					$('#precategoryid').append($('<option>',
						     {
						        value: 0,
						        text : "--Select--"
						    }));
				}
				$('#precategoryid').material_select(); 
				
				
				var valueforsecondlevel = $("#precategoryid").attr("value");
				var res = valueforsecondlevel.split("/");
				if(res.length == 2){
					var parentcid = res[0];
					$("#precategoryid").val(parentcid);
					console.log(parentcid)
				}
				$("#precategoryid").material_select();
				
				}
			else
				{
				console.log(data)
				}
		}
	});	
    }
}
function resetFormfields()
{
	$("#categoryname").val("");
	$("#categorydisplayid").val("");
	$("#categorylevel").val("0");
	$('#categorylevel').material_select();
	$("#precategoryid").val("0");
	$('#precategoryid').material_select();
	$('#categorystatus').attr('checked',true);
	$('#categorystatus').material_select();
	$("#categorypositionid").val("");
}

function loadcategories(level)
{

	$("#categoryiframe").attr("src", '../configuration/categorylist?actionid='+level);
}
function navigatecategory(categoryid)
{
/*window.location.href = "addcategory?v="+categoryid*/
	window.top.location.href = "addcategory?v="+categoryid; 
}




function savebulkupload(){
	
	var uploadObject = new Object();
	uploadObject.bannername = $("#bannername").val();
	uploadObject.oprn = $("#operation").val();
	uploadObject.bannerid = $("#bannerid").val();
	uploadObject.bannerurl = $("#urltext").val();
	uploadObject.bannerposition = $("#positiontext").val();
	uploadObject.bannerlayout = $("#layouttext").val();
	uploadObject.bannertype = $("#typeofbanner").val();
	if($('#myonoffswitch1').is(':checked')){
		uploadObject.status = 1;
	}else{
		uploadObject.status = 0;
	}
	console.log(uploadObject.oprn)
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : '../configurationservices/savebulkupload',
		data : JSON.stringify(uploadObject),
		success:function(data,textStatus,jqXHR){
			var responseType = data.responseValue.responseType;
			var responseValue = getResponseValue(data);
			displayAlertMsg(responseValue.o_out_msg);	
			 setTimeout(function(){ window.location.href = "addbulkupload?v=0"; }, 3000);
			
		}
	});
	
}


function savetax(){
	
	var taxObject = new Object();
	taxObject.taxid = $("#taxid").val();
	taxObject.percentage = $("#taxpercentage").val();
	taxObject.label = $("#taxlabel").val();
	taxObject.origin = $("#taxorgin").val();
	taxObject.oprn = $("#operation").val();
	if(($('#taxlabel').val().length == 0)){
		displayAlertMsg("Please Fill The Mandatory Fields")
		return false;
	}
	
	else if( ($("#taxpercentage").val().length == 0)){
		displayAlertMsg("Please Fill The Mandatory Fields")
		return false;
	}
	else if( ($("#taxorgin").val().length == 0)){
		displayAlertMsg("Please Fill The Mandatory Fields")
		return false;
	}
	
	if($('#myonoffswitch1').is(':checked')){
		taxObject.status = 1;
	}else{
		taxObject.status = 0;
	}
	
	console.log(taxObject)
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : '../configurationservices/savetax',
		data : JSON.stringify(taxObject),
		success:function(data,textStatus,jqXHR){
			var responseType = data.responseValue.responseType;
			var responseValue = getResponseValue(data);
			if(responseValue.o_out_flag == 'S'){
				displayAlertMsg(responseValue.o_out_msg);	
				setTimeout(function(){ window.location.href = "tax"; }, 3000);
			}
			else{
				displayAlertMsg(responseValue.o_out_msg);	
			}
			
			
		}
	});
	
}


function saveshipconfig(){
	
	var shipObject = new Object();
	shipObject.shippingid = $("#shipid").val();
	shipObject.label = $("#shiplabel").val();
	shipObject.amount = $("#shipamount").val();
	shipObject.origin = $("#shiporgin").val();
	shipObject.oprn = $("#operation").val();
	if(($('#shiplabel').val().length == 0)){
		displayAlertMsg("Please Fill The Mandatory Fields")
		return false;
	}
	
	else if( ($("#shipamount").val().length == 0)){
		displayAlertMsg("Please Fill The Mandatory Fields")
		return false;
	}
	else if( ($("#shiporgin").val().length == 0)){
		displayAlertMsg("Please Fill The Mandatory Fields")
		return false;
	}
	/*if($('#myonoffswitch1').is(':checked')){
		taxObject.status = 1;
	}else{
		taxObject.status = 0;
	}*/
	console.log(shipObject)
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : '../configurationservices/saveshipconfig',
		data : JSON.stringify(shipObject),
		success:function(data,textStatus,jqXHR){
			var responseType = data.responseValue.responseType;
			var responseValue = getResponseValue(data);
			if(responseValue.o_out_flag == 'S'){
				displayAlertMsg(responseValue.o_out_msg);	
				setTimeout(function(){ window.location.href = "shipconfig"; }, 3000);
			}
			else{
				displayAlertMsg(responseValue.o_out_msg);	
			}
			
			
		}
	});
	
}



function edittax(taxid)
{
	
	window.location.href="addtax?v="+taxid;
}

function editshiplist(shippingid)
{
	
	window.location.href="addshipconfig?v="+shippingid;
}

function editbanner(bannerid)
{
	
	window.location.href="addbulkupload?v="+bannerid;
}
function loadaddtax(){
	
	var taxid = getParameterByName("v");
	
	if(parseInt(taxid)>0){
		console.log(taxid);
		$("#operation").val('UPD');
		
		
	}else{
		
		$("#operation").val('INS');
	}
	
	
}

function loadaddshipconfig(){
	
	var shippingid = getParameterByName("v");
	console.log(shippingid);
	if(parseInt(shippingid)>0){
		
		$("#operation").val('UPD');
		
		
	}else{
		
		$("#operation").val('INS');
	}
	
	
}


function loadaddbanner(){
	
	var bannerid = getParameterByName("v");
	
	if(parseInt(bannerid)>0){
		
		$("#operation").val('UPD');
		
		
	}else{
		
		$("#operation").val('INS');
	}
}


function downloadbulkuploadformat(){
	
	window.open('../configurationservices/downloadbulkuploadformat','_new');
}


function importbulkupload(){
	var uploadedfilename = $('#uploadedfilename').val();
	var importObject =new Object();
	importObject.filename = uploadedfilename;
	console.log(importObject.filename)
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : '../configurationservices/importbulkupload',
		data : JSON.stringify(importObject),
		success:function(data,textStatus,jqXHR){
			var responseType = data.responseValue.responseType;
			var responseValue = getResponseValue(data);
			
			displayAlertMsg("Process Completed Successfully");	
			$('#statusDIVid').attr('style','display:""');
			$('#productUplaodStatusDT').DataTable( {
				"destroy":true,
		    	data: responseValue,
		        columns: [
		            { data: 'recindex' },
		            { data: 'uploadstatus' },
		            { data: 'errormsg' }
		        ]
	            
		    } );
			
			
		}
	});
	
	
}




function savesms(){
	
	var smsobject= new Object();
	smsobject.smsid = $("#smsid").val();
	smsobject.smskey = $("#smskey").val();
	smsobject.smsusername = $("#smsusername").val();
	smsobject.smspassword = $("#password").val();
	smsobject.oprn = $("#operation").val();
	
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : '../configurationservices/savesms',
		data : JSON.stringify(smsobject),
		success:function(data,textStatus,jqXHR){
			var responseType = data.responseValue.responseType;
			var responseValue = getResponseValue(data);
			displayAlertMsg(responseValue.o_out_msg);	
			console.log(smsobject.length)
			 setTimeout(function(){ window.location.href = "sms"; }, 3000);
			
		}
	});
	
}



function saveemail(){
	
	var emailobject= new Object();
	emailobject.emailid = $("#emailid").val();
	emailobject.serverup = $("#serveruptext").val();
	emailobject.smtpport = $("#smtpport").val();
	emailobject.smtpemail = $("#smtpemail").val();
	emailobject.emailusername = $("#username").val();
	emailobject.emailpassword = $("#password").val();
	emailobject.oprn = $("#operation").val();
	
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : '../configurationservices/saveemail',
		data : JSON.stringify(emailobject),
		success:function(data,textStatus,jqXHR){
			var responseType = data.responseValue.responseType;
			var responseValue = getResponseValue(data);
			displayAlertMsg(responseValue.o_out_msg);	
			console.log(smsobject.length)
			 setTimeout(function(){ window.location.href = "email"; }, 3000);
			
		}
	});
	
}

function loademailsettings(){
	
	
	
	$.ajax({
		type : 'GET',
		contentType : 'application/json',
		url : '../configurationservices/loademailsettings',
		
		success:function(data,textStatus,jqXHR){
			var responseType = data.responseValue.responseType;
			var emailList = getResponseValue(data);
			console.log(emailList)
			if(emailList.length>0){
			
				var emailObj =emailList[0];
				$('#serveruptext').val(emailObj.serverup);
				$('#smtpport').val(emailObj.smtpport);
				$('#smtpemail').val(emailObj.smtpemail);
				$('#username').val(emailObj.emailusername);
				$('#password').val(emailObj.emailpassword);
				$("#operation").val("UPD");
				console.log(emailObj)
			}
			else{
				
				$("#operation").val("INS");
				
			}
		}
	});
	
}
function loadsmssettings(){
	
	
	
	$.ajax({
		type : 'GET',
		contentType : 'application/json',
		url : '../configurationservices/loadsmssettings',
		
		success:function(data,textStatus,jqXHR){
			var responseType = data.responseValue.responseType;
			var smsList = getResponseValue(data);
			console.log(smsList)
			if(smsList.length>0){
			
				var smsObj =smsList[0];
				$('#smskey').val(smsObj.smskey);
				$('#smsusername').val(smsObj.smsusername);
				$('#password').val(smsObj.smspassword);
				$("#operation").val("UPD");
				console.log(smsObj)
			}
			else{
				
				$("#operation").val("INS");
				
			}
		}
	});
	
}


function loadaddcategory(){
	var categoryid = getParameterByName("v");

	//alert(productid)
	if(parseInt(categoryid)>0){
		var level = 0;
		var valueforselect = $("#categorylevel").attr("value");
		if(valueforselect == "PreCategory"){
			level = 1;
			$("#categorylevel").val(level);
		}else if(valueforselect == "SubCategory"){
			level = 2;
			$("#categorylevel").val(level);
		}else{
			$("#categorylevel").val(level);
		}
		$("#categorylevel").material_select();
		getpresubcategories(level);
		
	}
	
}
function loadtax(){
	$('#taxDT').DataTable( {
		retrieve: true,
		"bLengthChange": false,
		 "aoColumnDefs": [{ "bSortable": false, "aTargets": [-1]}],
		responsive: true, 
		language: {
			 search: "_INPUT_",
			 searchPlaceholder: "Search..."}
	    } ); 


}
function loadshipconfig(){
	$('#shipconfigDT').DataTable( {
		retrieve: true,
		"bLengthChange": false,
		 "aoColumnDefs": [{ "bSortable": false, "aTargets": [-1]}],
		responsive: true, 
		language: {
			 search: "_INPUT_",
			 searchPlaceholder: "Search..."}
	    } ); 
	}

function loadbulkupload(){
	$('#bulkuploadDT').DataTable( {
		responsive: true, 
		language: {
			 search: "_INPUT_",
			 searchPlaceholder: "Search..."}
	    } ); 
	}

function loadmincart(){
	$('#mincartDT').DataTable( {
		retrieve: true,
		"bLengthChange": false,
		 "aoColumnDefs": [{ "bSortable": false, "aTargets": [-1]}],
		responsive: true, 
		language: {
			 search: "_INPUT_",
			 searchPlaceholder: "Search..."}
	    } ); 
	}

function savemsgcontent()
{
	
	var msgContent = $('#msgcontenttext').val();
	var smsObject = new Object();
	smsObject.msgcontent = $('#msgcontenttext').val();
	smsObject.tempid= $('#templateidtext').val();
	smsObject.oprn = "SAVEMSG";
	console.log(smsObject)
	$.ajax({
		type:'POST',
		contentType:'application/json',
		url:'../configurationservices/savemsg',
		data:JSON.stringify(smsObject),
		success:function(data,textStatus,jqXHR)
		{
			var responseType = data.responseValue.responseType;
			var responseValue = getResponseValue(data);
			displayAlertMsg(responseValue.o_out_msg);	
			console.log(smsobject.length);
			window.location.href = "sms";
			// setTimeout(function(){ window.location.href = "email"; }, 3000);
		      
		}
	});
}
function saveemailmsg(){

	var msgContent = $('#emailcontenttext').val();
	var emailobject= new Object();
	emailobject.msgcontent = $('#emailcontenttext').val();
	emailobject.emailid= $('#templateidtext').val();
	emailobject.oprn = "SAVEEMAIL";
	console.log(emailobject)
	
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : '../configurationservices/saveemailmsg',
		data : JSON.stringify(emailobject),
		success:function(data,textStatus,jqXHR){
			var responseType = data.responseValue.responseType;
			var responseValue = getResponseValue(data);
			displayAlertMsg(responseValue.o_out_msg);	
			setTimeout(function(){ window.location.href = "email"; }, 3000);
			
		}
	});
	
}

function displayemailcontent(msgcontent,param_desc,emailid)
{
	
	$('#templateidtext').val(emailid);
	$('#emailcontenttext').val(msgcontent);
	$('#paramdesctext').val(param_desc);
	
}

function displaymsgcontent(msgcontent,description,tempid)
{
	
	$('#templateidtext').val(tempid);
	$('#msgcontenttext').val(msgcontent);
	$('#paramdesctext').val(description);
	
}

function savemincart(){
	
	var mincartObject = new Object();
	mincartObject.mincartid = $("#mincartid").val();
	mincartObject.mincartvalue = $("#mincartvalue").val();
    mincartObject.oprn = $("#operation").val();
    if(($('#mincartvalue').val().length == 0)){
		displayAlertMsg("Please Fill The Mandatory Fields")
		return false;
	}
	if($('#myonoffswitch1').is(':checked')){
		mincartObject.status = 1;
	}else{
		mincartObject.status = 0;
	}
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : '../configurationservices/savemincart',
		data : JSON.stringify(mincartObject),
		success:function(data,textStatus,jqXHR){
			var responseType = data.responseValue.responseType;
			var responseValue = getResponseValue(data);
			if(responseValue.o_out_flag == 'S'){
				displayAlertMsg(responseValue.o_out_msg);	
				setTimeout(function(){ window.location.href = "mincartvalue"; }, 3000);
			}
			else{
				displayAlertMsg(responseValue.o_out_msg);	
			}
			
			
		}
	});
}

function editmincart(mincartid)
{
	
	window.location.href="addmincart?v="+mincartid;
}
function loadaddmincart(){
	
	var mincartid = getParameterByName("v");
	console.log(mincartid);
	if(parseInt(mincartid)>0){
		
		$("#operation").val('UPD');
		
		
	}else{
		
		$("#operation").val('INS');
	}
}