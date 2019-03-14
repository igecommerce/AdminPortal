function savesms(){
	if(($('#smskey').val().length == 0)){
		displayAlertMsg("Please Fill The Mandatory Fields");
		return false;
	}
	if(($('#smsusername').val().length == 0)){
		displayAlertMsg("Please Fill The Mandatory Fields");
		return false;
	}
	if(($('#password').val().length == 0)){
		displayAlertMsg("Please Fill The Mandatory Fields");
		return false;
	}
	var smsobject= new Object();
	smsobject.smsid = $("#smsid").val();
	smsobject.smskey = $("#smskey").val();
	smsobject.smsusername = $("#smsusername").val();
	smsobject.smspassword = $("#password").val();
	smsobject.oprn = $("#operation").val();
	
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : '../communicationservices/savesms',
		data : JSON.stringify(smsobject),
		success:function(data,textStatus,jqXHR){
			var responseType = data.responseValue.responseType;
			var responseValue = getResponseValue(data);
			displayAlertMsg(responseValue.o_out_msg);	
			//console.log(smsobject.length)
			setTimeout(function(){ window.location.href = "sms"; }, 2000);
			//window.location.href="sms";
		}
	});
	
}

function saveemail(){
	if(($('#serveruptext').val().length == 0)){
		displayAlertMsg("Please Fill The Mandatory Fields");
		return false;
	}
	if(($('#smtpport').val().length == 0)){
		displayAlertMsg("Please Fill The Mandatory Fields");
		return false;
	}
	if(($('#smtpemail').val().length == 0)){
		displayAlertMsg("Please Fill The Mandatory Fields");
		return false;
	}
	if(($('#username').val().length == 0)){
		displayAlertMsg("Please Fill The Mandatory Fields");
		return false;
	}
	if(($('#password').val().length == 0)){
		displayAlertMsg("Please Fill The Mandatory Fields");
		return false;
	}
	
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
		url : '../communicationservices/saveemail',
		data : JSON.stringify(emailobject),
		success:function(data,textStatus,jqXHR){
			var responseType = data.responseValue.responseType;
			var responseValue = getResponseValue(data);
			displayAlertMsg(responseValue.o_out_msg);	
			//console.log(smsobject.length)
			setTimeout(function(){ window.location.href = "email"; }, 2000);
			//window.location.href="email";
		}
	});
	
}

function loademailsettings(){
	

	$.ajax({
		type : 'GET',
		contentType : 'application/json',
		url : '../communicationservices/loademailsettings',
		
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
		url : '../communicationservices/loadsmssettings',
		
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
		url:'../communicationservices/savemsg',
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
		url : '../communicationservices/saveemailmsg',
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
