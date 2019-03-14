function viewcategoryposition(){
	
	var categoryObject = new Object();
	categoryObject.categorylevel = $("#categorylevel").val();
	categoryObject.precategoryid = $("#precategoryid").val();
	console.log(categoryObject)
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : '../categoryservices/viewposition',
		data : JSON.stringify(categoryObject),
		success:function(data,textStatus,jqXHR){
			var responseType = data.responseValue.responseType;
			var responseValue = getResponseValue(data);
			//console.log(categorylevel)
			if(categoryObject.categorylevel == 0){
				$('#myModal').show();
				$('#categoryDT').DataTable( {
			    	data: responseValue,
			    	paging: false,
			    	searching: false,
			    	 retrieve: true,
			    	columns: [
			            { data: 'categoryname' },
			            { data: 'position' }
			          
			        ]
		            
			    } ); 
			}
			if(categoryObject.categorylevel == 1){
				$('#myModalprecategory').show();
				$('#precategoryDT').DataTable( {
			    	data: responseValue,
			    	paging: false,
			    	searching: false,
			    	 retrieve: true,
			    	columns: [
			            { data: 'categoryname' },
			            { data: 'precategory' },
			            { data: 'position' }
			          
			        ]
		            
			    } ); 
			}
			if(categoryObject.categorylevel == 2){
				$('#myModalsubcategory').show();
				$('#subcategoryDT').DataTable( {
			    	data: responseValue,
			    	paging: false,
			    	searching: false,
			    	 retrieve: true,
			    	columns: [
			            { data: 'baseategory' },
			            { data: 'precategory' },
			            { data: 'categoryname' },
			            { data: 'position' }
			          
			        ]
		            
			    } ); 
			}
			
		}
			
		
	});
	
}



function createcategory(){
	
	var isValid;
	$("input").each(function() {
	   var element = $(this);
	   if (element.val() == "") {
	       isValid = false;
	   }
	   else{
		   isValid = true; 
	   }
	});
	if(isValid == false){
		displayAlertMsg("Please Fill The Mandatory Fields")
		return false;
	}
  console.log(isValid)
  if(($('#categoryname').val().length == 0)){
		displayAlertMsg("Please Fill The Mandatory Fields")
		return false;
	}
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
	
	console.log(categoryObject)
	
	$.ajax({
			type : 'POST',
			contentType : 'application/json',
			url : '../categoryservices/createcategory',
			data : JSON.stringify(categoryObject),
			success:function(data,textStatus,jqXHR){
				var responseType = data.responseValue.responseType;
				var responseValue = getResponseValue(data);
				 
				if(responseType == 'S')
					{
					displayAlertMsg(responseValue.o_out_msg);	
					if(responseValue.o_out_flag == 'S') 
						{
						if(categoryObject.action == "UPD"){
							setTimeout(function(){ window.location.href = "../category/category"; }, 3000);
							localStorage.setItem("updsesstion","1");
						}
						else{
							setTimeout(function(){ window.location.href = "../category/addcategory?v=0"; }, 3000);
							
						}
						
						}
					
					}
				else
					{
					displayAlertMsg(responseValue.o_out_msg);	
					}
			}
		});
}

function getpresubcategories(value)
{
	console.log(value)
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
		url : '../categoryservices/getpresubcategory/'+value,
		data : JSON.stringify(value),
		success:function(data,textStatus,jqXHR){
			console.log(data)
			var responseType = data.responseValue.responseType;
			var responseValue = getResponseValue(data);
			 console.log(data)
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
	
	
	$("#categoryiframe").attr("src", '../category/categorylist?actionid='+level);
}
function navigatecategory(categoryid)
{
/*window.location.href = "addcategory?v="+categoryid*/
	window.top.location.href = "addcategory?v="+categoryid; 
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
function loadcategorylist(level){
	
	$('#categoryDT').DataTable( {
		retrieve: true,
		"bLengthChange": false,
		 "aoColumnDefs": [{ "bSortable": false, "aTargets": [-1]}],
		 responsive: true, 
			language: {
				 search: "_INPUT_",
				 searchPlaceholder: "Search..."}
		
    } ); 
}

function loadcategory(){
	if(localStorage.getItem("updsession") == 1){
		window.location.reload();
	}
}