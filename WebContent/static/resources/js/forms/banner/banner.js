function savebulkupload(){
	
	var uploadObject = new Object();
	uploadObject.bannerlayout = $("#bannerlayoutname").val();
	uploadObject.bannerid = $("#bannerid").val();
	uploadObject.bannerurl = $("#bannerurl").val();
	uploadObject.bannertype = $("#bannertype").val();
	uploadObject.bannername = $("#imagename").val();
	uploadObject.descbanner = $("#imagedesc").val();
	uploadObject.bannerposition = $("#imageposition").val();
	uploadObject.label = $("#imagetype").val();
	
/*	if($('#myonoffswitch1').is(':checked')){
		uploadObject.status = 1;
	}else{
		uploadObject.status = 0;
	}*/
	if($('#myonoffswitch').is(':checked')){
		uploadObject.action = 1;
	}else{
		uploadObject.action = 0;
	}
	console.log(uploadObject.oprn)
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : '../bannerservices/savebanner',
		data : JSON.stringify(uploadObject),
		success:function(data,textStatus,jqXHR){
			var responseType = data.responseValue.responseType;
			var responseValue = getResponseValue(data);
			displayAlertMsg(responseValue.o_out_msg);	
			 setTimeout(function(){ window.location.href = "bannerlist"; }, 3000);
			
		}
	});
	
}

function downloadbulkuploadformat(){
	
	window.open('../bannerservices/downloadbannerformat','_new');
}

function downloadbulkuploadformatforbannertype(){
	
	window.open('../bannerservices/downloadbannerformatforbannertype','_new');
}

function editbanner(id){
	
	window.location.href="editbanner?v="+id;
}
function importbulkupload(){
	var uploadedfilename = $('#uploadedfilename').val();
	var importObject =new Object();
	importObject.filename = uploadedfilename;
	console.log(importObject.filename)
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : '../bannerservices/importbanner',
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
function importbulkuploadbannertype(){
	var uploadedfilename = $('#uploadedfilename').val();
	var importObject =new Object();
	importObject.filename = uploadedfilename;
	console.log(importObject.filename)
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : '../bannerservices/importbulkuploadbannertype',
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

function loadbulkupload(){
	$('#bulkuploadDT').DataTable( {
		responsive: true, 
		language: {
			 search: "_INPUT_",
			 searchPlaceholder: "Search..."}
	    } ); 
	}
