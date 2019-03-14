function signin(){
	var loginObject = new Object();
	loginObject.username = $("#username").val();
	loginObject.password = $("#password").val();
	if(($('#username').val().length == 0) && ($('#password').val().length == 0)){
		displayAlertMsg("Please Enter Username and Password")
		return false;
	}
	else if(($('#username').val().length == 0)){
		displayAlertMsg("Please Enter Username")
		return false;
	}
	else if(($('#password').val().length == 0)){
		displayAlertMsg("Please Enter Password")
		return false;
	}
	console.log(JSON.stringify(loginObject))
	$.ajax({
			type : 'POST',
			contentType : 'application/json',
			url : '../loginservices/validategaiasignin',
			data : JSON.stringify(loginObject),
			success:function(data,textStatus,jqXHR){
				var responseType = data.responseType;
				var responseValue = getResponseValue(data);
				console.log(responseValue)
				console.log(responseValue.length)
				if(responseValue.length == 1){
					
					$.ajax({
						type : 'GET',
						contentType : 'application/json',
						url : '../template/menu',
						success:function(data,textStatus,jqXHR){
							console.log(data);
							window.location.href = "../template/dashboard"
						}
					});

					
				}else{
					$("#alertMsg").text(responseValue);
					$("#alertModal").show();
					return false;
				}
				/*if(responseType == 'S')
					{
					if(responseValue.length > 0)
					{
						$.ajax({
							type : 'GET',
							contentType : 'application/json',
							url : '../template/menu',
							success:function(data,textStatus,jqXHR){
								console.log(data);
								//window.location.href = "../template/dashboard"
							}
						});
					
					}
					
					}else
					{
						console.log(responseValue)
					$("#password").val("");
					$("#alertMsg").text("Invalid Credentials.");
					$("#alertModal").show();
					return false;
						
					}*/
				
			}
		});
}

function changepassword()
{
	
	var applicationOrigin = window.location.origin;
	var applicationContext = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
	var applicationContextwithslash=applicationContext+"/";
	
	var applicationurl = applicationOrigin+applicationContextwithslash;	
	var loginObject = new Object();
	loginObject.password = $("#oldpassword").val();
	loginObject.newpassword = $("#confirmpassword").val();
	loginObject.action = "CHANGE_PASSWRD";
	if($("#newpassword").val() != $("#confirmpassword").val())
		{
		displayAlertMsg("New Password and Confirm password must be same");
		return false;
		}
	else if($("#newpassword").val() == $("#oldpassword").val())
		{
		displayAlertMsg("Old Password and New password must not be same");
		return false;
		}
	console.log(JSON.stringify(loginObject))
	$.ajax({
			type : 'POST',
			contentType : 'application/json',
			url : '../loginservices/changepassword',
			data : JSON.stringify(loginObject),
			success:function(data,textStatus,jqXHR){
				var responseType = data.responseType;
				var responseValue = getResponseValue(data);
				console.log(data);
				if(responseType == 'S')
					{
					if(responseValue.o_out_flag == 'S')	
						{
						displayAlertMsg(responseValue.o_out_msg);
						setTimeout(function(){ window.location.href = applicationurl; }, 3000);
						}
					}
				
				
			}
		});
}

function logout(){
	
	var applicationOrigin = window.location.origin;
	var applicationContext = window.location.pathname.substring(0, window.location.pathname.indexOf("/",2));
	var applicationContextwithslash=applicationContext+"/";
	
	var applicationurl = applicationOrigin+applicationContextwithslash;	
	var loginObject = new Object(); 
	console.log(JSON.stringify(loginObject))
	$.ajax({
			type : 'POST',
			contentType : 'application/json',
			url : '../loginservices/logout',
			data : JSON.stringify(loginObject),
			success:function(data,textStatus,jqXHR){
				 if(data.responseType == 'S')
					 {
					 window.location.href = applicationurl;
					 }
				
			}
		});
}


function resetpassword(){
	if($('#usernametext').val()==""){
		$("#errrormsg").text("Please Enter Username");
		return false;
	}else{
		$('#myModal').hide();
		displayAlertMsg("Your Password Resetted Successfully.Please Contact Administrator.")
	var loginObject = new Object();
	loginObject.username = $('#usernametext').val();
	loginObject.action = "RESET_PASSWRD";
	console.log(JSON.stringify(loginObject))
	$.ajax({
			type : 'POST',
			contentType : 'application/json',
			url : '../loginservices/resetpassword',
			data : JSON.stringify(loginObject),
			success:function(data,textStatus,jqXHR){
				console.log(data);
				 if(data.responseType == 'S')
					 {
					 $('#myModal').hide();
					 var responseValue = getResponseValue(data);
					 displayAlertMsg(responseValue.o_out_msg);
					 }
				
			}
		});
	}
}

function loadroles()
{
	$.ajax({
		type : 'GET',
		contentType : 'application/json',
		url : '../loginservices/userroles',
		success:function(data,textStatus,jqXHR){
			var responseType = data.responseType;
			var roleList = getResponseValue(data);
			if(responseType == 'S')
				{
				console.log(roleList);
				
				$('#roleDT').DataTable( {
			    	data: roleList,
			    	columns: [
			            { data: 'roleid' },
			            { data: 'roledesc' },
			           /* { data: 'superadminflag' },*/
			            { data: 'status' },
			            { data: 'action' }
			        ],
			        responsive: true, 
					language: {
						 search: "_INPUT_",
						 searchPlaceholder: "Search..."},
						 retrieve: true,
							"bLengthChange": false,
							 "aoColumnDefs": [{ "bSortable": false, "aTargets": [-1]}]
		            
			    } ); 
				}
		}
	});	
	selectuserrole();
}

function selectuserrole() {
	
	$('#roleDT tbody').on('click', '.edit.btn', function() {
		var table = $('#roleDT').DataTable({
			responsive: true, 
			language: {
				 search: "_INPUT_",
				 searchPlaceholder: "Search..."}
		});
		var roleObject = [];
		roleObject.push(table.row( $(this).parents('tr') ).data());
		
		var object = roleObject[0];
		console.log(object);
		
	});
}



var userroleArray = [];


function saveuserrole(){
	var userObject = new Object();
	userObject.userrole = $("#userrole").val();
	userObject.roleid = $("#roleid").val();
	userObject.operation = $("#operation").val();
	if($('#myonoffswitch1').is(':checked')){
		userObject.isadmin = 1;
	}else{
		userObject.isadmin = 0;
	}
	if($('#myonoffswitch2').is(':checked')){
		userObject.status = 1;
	}else{
		userObject.status = 0;
	}
	
	if ($("#userrole").val()=="")
		
	{
	displayAlertMsg("Enter Role Description");
	return false;
	}
	//console.log(userObject);

	
	
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : '../loginservices/saveuserrole',
		data : JSON.stringify(userObject),
		success:function(data,textStatus,jqXHR){
			var responseType = data.responseType;
			var responseValue = getResponseValue(data);
			console.log(data)
			if(responseType == 'S')
				{
				var roleid = responseValue.o_out_id;
				
				if(roleid != 0){
				$("#moduleDt tbody tr").each(function(){
					var usermenuObj = new Object();
					var id = $(this).find('input[type=checkbox]').attr('id');
					console.log(id)
					var checkcondition = $(this).find('input[type=checkbox]').is(':checked');
					console.log(checkcondition);
					if(checkcondition == true){
						var selected = 1;
					}else{
						var selected = 0;
					}
					var moduleid = $(this).find('input[type=checkbox]').attr('dataid');
					usermenuObj.usertype = roleid;
					usermenuObj.moduleid = moduleid;
					usermenuObj.usermenusid = moduleid;
					usermenuObj.menuaccess = selected;
					usermenuObj.operation = $("#operation").val();
					usermenuObj.userrole = $("#userrole").val();
					//console.log(usermenuObj);
					
					userroleArray.push(usermenuObj);
					//console.log(userroleArray)
				});
				
				//console.log(userroleArray)
				
				
				$.ajax({
					type : 'POST',
					contentType : 'application/json',
					url : '../loginservices/saveusermenus',
					data : JSON.stringify(userroleArray),
					success:function(data,textStatus,jqXHR){
						var responseType_menu = data.responseType;
						var responseValue_menu = getResponseValue(data);
				
						if(responseType_menu == 'S')
							{
							displayAlertMsg(responseValue_menu.o_out_msg);
							setTimeout(function(){ window.location.href = "userrole"; }, 3000);
							//window.location.href = "adduserrole?v=0";
							}
					}
				});

				}
				else{
					displayAlertMsg(responseValue.o_out_msg);
					//setTimeout(function(){ window.location.href = "adduserrole?v=0"; }, 3000);
					//window.location.href = "adduserrole?v=0";
				}
				
				}
			
		}
	});
	return false;
}
function loadadduserrole(){
	var roleid = getParameterByName("v");
	if(parseInt(roleid)>0){
		$("#operation").val('UPD');
		var adminflag = $("#adminflag").val();
		if(parseInt(adminflag) == 0){
			$("#myonoffswitch1").prop('checked', false);	
		}else{
			$("#myonoffswitch1").prop('checked', true);
		}
	}else{
		$("#operation").val('INS');
	}
}
function edituserrole(roleid)
{
	window.location.href="adduserrole?v="+roleid;
}

function loadadduser()
{
	var userid = getParameterByName("v");
	if(parseInt(userid)>0){
		selectoption("userrole");
		//$('#userrole').val($('#userroleid').val());
	}
}

function registration(){
	var userpassword = $("#password").val();
	var emailaddress = $("#email").val();
	var confirmpassword = $("#confirm_password").val();
	var phoneno = $("#mobileno").val();
	if(phoneno.length > 12){
		 displayAlertMsg("Phone numbers should not be more than 12 digits");
		return false;
	}
	var specialCharacterCount = specialcharactercheck(userpassword); 
	if(specialCharacterCount == 0)
	{
		displayAlertMsg("Password must contain Special Characters");
	return false;
	}
	var pattern = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	
	if(!pattern.test(emailaddress))
	
	{
		displayAlertMsg("Invalid Email");
		return false;
	}
	if(userpassword != confirmpassword){
		displayAlertMsg("password and confirm password must be same");
		return false;
	}
	if($('#userrole option:selected').val()=="")
	{
	displayAlertMsg("Please Select User Role");
	return false;
	}
	var loginObject = new Object();
	loginObject.id = $("#userid").val();
	loginObject.username = $("#username").val();
	loginObject.password = $("#password").val();
	loginObject.email = $("#email").val();
	loginObject.phoneno = $("#mobileno").val();
	if($('#myonoffswitch1').is(':checked')){
		loginObject.status = 1;
	}else{
		loginObject.status = 0;
	}
	loginObject.roleid = $('#userrole option:selected').val();
	loginObject.action = $("#operation").val();
	console.log(JSON.stringify(loginObject))
	$.ajax({
			type : 'POST',
			contentType : 'application/json',
			url : '../loginservices/registration',
			data : JSON.stringify(loginObject),
			success:function(data,textStatus,jqXHR){
			console.log(data)
				var responseType = data.responseType;
				var responseValue = getResponseValue(data);
				
				if(responseValue.o_out_flag == 'S')
					{
					displayAlertMsg(responseValue.o_out_msg);
					setTimeout(function(){ window.location.href = "gaiausers"; }, 3000);
					
					}
					 
				else{
					displayAlertMsg(responseValue.o_out_msg);
				}
				
			}
		});
}


function edituser(id){
	
	window.location.href="adduser?v="+id;
}

function loadusers(){
	
	$.ajax({
		type : 'GET',
		contentType : 'application/json',
		url : '../loginservices/userlist',
		success:function(data,textStatus,jqXHR){
			var responseType = data.responseType;
			var userlist = getResponseValue(data);
			if(responseType == 'S')
				{
				console.log(userlist);
				
				$('#userDT').DataTable( {
			    	data: userlist,
			    	columns: [
			            { data: 'username' },
			            { data: 'email' },
			            { data: 'roledesc' },
			            { data: 'status' },
			            { data: 'action' }
			        ],
			        responsive: true, 
					language: {
						 search: "_INPUT_",
						 searchPlaceholder: "Search..."},
						 retrieve: true,
							"bLengthChange": false,
							 "aoColumnDefs": [{ "bSortable": false, "aTargets": [-1]}]
				
		           
			    } ); 
				}
		}
	});	
	
}


