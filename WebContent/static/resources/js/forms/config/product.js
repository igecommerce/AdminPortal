var checkCategoryArray = [];
var digits = 0;
function saveproduct(){
	
	checkCategoryArray = [];
	var productObject = new Object();
	productObject.productid = $("#productid").val();
	productObject.operation = $("#operation").val();
	
	productObject.typeid = 1;
	productObject.websiteid = $("#websiteid").val();
	productObject.sku = $("#skuid").val();
	productObject.name = $("#productnameid").val();
	if($('#gendermale').is(':checked')){
		productObject.gender = "male";
	}else if($('#genderfemale').is(':checked')){
		productObject.gender = "female";
	}else{
		productObject.gender = "unisex";
	}
	console.log(productObject.gender);
	
	productObject.brand = $("#brandid").val();
	productObject.uom = $("#uomid").val();
	
	productObject.usage = $("#productusageid").val();
	productObject.composition = $("#productcompositionid").val();
	productObject.desc = $("#productdescid").val();
	
	$("#moduleDt tbody tr").each(function(){
		var checkCategory =  new Object();
		var id = $(this).find('input[type=checkbox]').attr('id');
		var checkcondition = $(this).find('input[type=checkbox]').is(':checked');
		console.log(checkcondition)
		var categoryid = $(this).find('input[type=checkbox]').attr('dataid');
		var categorylevel=$(this).find('input[type=checkbox]').attr('datalevel');
		var categoryparent=$(this).find('input[type=checkbox]').attr('dataparent');
		
		if(checkcondition == true){
			checkCategory.categoryid = categoryid;
			checkCategory.categorylevel = categorylevel;
			checkCategory.categoryparent = categoryparent;
			checkCategoryArray.push(checkCategory);
			
		} 
	});
	console.log(checkCategoryArray)
	
	 var	checkCategoryidArray = [];
		var	checkPrecategoryArray = [];
			var checkSubcategoryArray = [];
		$(checkCategoryArray).each(function(key,val){
			
			var checkCategory =  new Object();
			
			if((val.categorylevel) == 0){
				checkCategory.categoryid= val.categoryid;
				console.log(checkCategory.categoryid)
				checkCategoryidArray.push(checkCategory);
				console.log(checkCategoryidArray.push(checkCategory))
			}
			 if((val.categorylevel) == 1){
				checkCategory.categoryid= val.categoryid;
				checkCategory.categoryparent= val.categoryparent;
				checkPrecategoryArray.push(checkCategory);
				
			}
			 if((val.categorylevel) == 2){
				checkCategory.categoryid= val.categoryid;
				checkCategory.categoryparent= val.categoryparent;
				checkSubcategoryArray.push(checkCategory);
				
			}
		});
	
	
	var selectedCategoryNodeArray = [];
			var	afterSelectionCategoryidArray = [];

			$(checkCategoryidArray).each(function(key, value){
				
				var categoryid = value.categoryid;
				console.log("category id"+categoryid)
				console.log($.inArray(categoryid,afterSelectionCategoryidArray))
				 var categoryvalid = $.inArray(categoryid,afterSelectionCategoryidArray);
				if(categoryvalid < 0){
					afterSelectionCategoryidArray.push(categoryid);
					console.log(afterSelectionCategoryidArray)
					var	afterSelectionPrecategoryArray = [];
				$(checkPrecategoryArray).each(function(key, value){
					var pre_categoryid = value.categoryid;
					var pre_categoryvalid = $.inArray(pre_categoryid,afterSelectionPrecategoryArray);
					
					if(pre_categoryvalid < 0){
					afterSelectionPrecategoryArray.push(pre_categoryid);
					console.log("pre category id"+pre_categoryid);
					var pre_category_parent = value.categoryparent;
					console.log("pre_category_parent   "+pre_category_parent)
					if(pre_category_parent == categoryid){
						
						var afterSelectionSubcategoryArray = [];
						$(checkSubcategoryArray).each(function(key, value){
							var sub_categoryid = value.categoryid;
							var sub_categoryvalid = $.inArray(sub_categoryid,afterSelectionSubcategoryArray);
							console.log("sub ategory id"+sub_categoryid)
							
							if(sub_categoryvalid < 0){
							
							
							var sub_category_parent = value.categoryparent;
							if(sub_category_parent == pre_categoryid){
								var object = new Object();
								object.categoryid = categoryid;
								object.precatgeory = pre_categoryid;
								object.subcatgeory = sub_categoryid;
								
								selectedCategoryNodeArray.push(object);
								
								console.log("----------selected object ------------");
								
								afterSelectionSubcategoryArray.push(sub_categoryid);
							}
							}
						});
					}
					
					}
				});
				}
				
			});
			
			console.log("-----------------saras-------------------")
			console.log(selectedCategoryNodeArray);
			
	productObject.categoryarray =JSON.stringify(selectedCategoryNodeArray);
	var offerstartdate = $("#offerstartdate").val();
	var offerenddate = $("#offerenddate").val();
	offerstartdate = offerstartdate.replace(',', '');
	offerenddate = offerenddate.replace(',', '');
	//alert(offerstartdate+""+offerstartdate);
	productObject.offerstartdate = offerstartdate;
	productObject.offerenddate = offerenddate;
	productObject.origprice = $("#origprice").val();
	productObject.currentprice = $("#currentprice").val();
	productObject.offerprice = $("#offerprice").val();
	productObject.productcount = $("#productcount").val();
	productObject.stockstatus = $("#stockstatusid").val();
	productObject.image = $("#imagepathid").val();
	productObject.thumbimage = $("#thumbimageid").val();
	productObject.smallimage = $("#smallid").val();
	productObject.thumbimage1 = $("#thumbimageid1").val();
	productObject.thumbimage2 = $("#thumbimageid2").val();
	productObject.thumbimage3 = $("#thumbimageid3").val(); 
	productObject.thumbimage4 = $("#thumbimageid4").val();
	console.log(productObject)
	$('#myModal').show();
	
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : '../product/createproduct',
		data : JSON.stringify(productObject),
		success:function(data,textStatus,jqXHR){
			var responseType_menu = data.responseType;
			var responseValue_menu = getResponseValue(data);
			if(responseType_menu == 'S')
				{
				displayAlertMsg(responseValue_menu.o_out_msg);
				$('#myModal').hide();
				//window.location.href = "../products/addproduct?actionid=0";
				setTimeout(function(){ window.location.href = "productlist"; }, 3000);
				}else{
					displayAlertMsg(responseValue_menu.o_out_msg);
				}
		}
	});
	
}

function editproduct(id){
	window.location.href = "../products/addproduct?actionid="+id;
	
}

function createnewproduct()
{
window.location.href="../products/addproduct?actionid=0";	
}

function loadexportproduct(){
	$('#exportProductDt').DataTable( {
		responsive: true, 
		language: {
			 search: "_INPUT_",
			 searchPlaceholder: "Search..."},
    	dom: 'Bfrtip',
        buttons: [
              { extend: 'excel', className: 'btn', text:'EXPORT' }
        ],
        "scrollX": true
    	
    } ); 
}
function loadsalesbyproduct(){
	$('#exportProductDt').DataTable( {
		responsive: true, 
		language: {
			 search: "_INPUT_",
			 searchPlaceholder: "Search..."},
    	dom: 'Bfrtip',
        buttons: [
              { extend: 'excel', className: 'btn', text:'EXPORT' }
        ],
        "scrollX": true
    	
    } ); 
}
function savebrand(){
	
	var brandObject = new Object();
	brandObject.brandname = $("#brandname").val();
	brandObject.oprn = $("#operation").val();
	brandObject.brandid = $("#brandidtext").val();
	if(($('#brandname').val().length == 0)){
		displayAlertMsg("Please Fill The Mandatory Fields")
		return false;
	}
	if($('#myonoffswitch1').is(':checked')){
		brandObject.status = 1;
	}else{
		brandObject.status = 0;
	}
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : '../product/savebrand',
		data : JSON.stringify(brandObject),
		success:function(data,textStatus,jqXHR){
			var responseType = data.responseValue.responseType;
			var responseValue = getResponseValue(data);
			console.log(responseValue.o_out_flag)
			if(responseValue.o_out_flag == 'S'){
				displayAlertMsg(responseValue.o_out_msg);	
				 setTimeout(function(){ window.location.href = "../products/brand"; }, 3000);
			}
			else{
				displayAlertMsg(responseValue.o_out_msg);	
			}
			
			
		}
	});
	
}

function saveuom(){
	
	var uomObject = new Object();
	uomObject.uomid = $("#uomid").val();
	uomObject.uomname = $("#uomname").val();
	uomObject.oprn = $("#operation").val();
	if(($('#uomname').val().length == 0)){
		displayAlertMsg("Please Fill The Mandatory Fields")
		return false;
	}
	if($('#myonoffswitch1').is(':checked')){
		uomObject.status = 1;
	}else{
		uomObject.status = 0;
	}
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : '../product/saveuom',
		data : JSON.stringify(uomObject),
		success:function(data,textStatus,jqXHR){
			var responseType = data.responseValue.responseType;
			var responseValue = getResponseValue(data);
			if(responseValue.o_out_flag == 'S'){
				displayAlertMsg(responseValue.o_out_msg);	
				 setTimeout(function(){ window.location.href = "../products/uom"; }, 3000);
			}
			else{
				displayAlertMsg(responseValue.o_out_msg);	
			}
			
			
			
		}
	});
	
}
function edituom(uomid)
{
	
	window.location.href="adduom?v="+uomid;
}

function loadadduom(){
	
	var uomid = getParameterByName("v");
	console.log(uomid);
	if(parseInt(uomid)>0){
		
		$("#operation").val('UPD');
		
		
	}else{
		
		$("#operation").val('INS');
	}
}
function editbrand(brandid)
{
	
	window.location.href="addbrand?v="+brandid;
}
function loadaddbrand(){
	
	var brandid = getParameterByName("v");
	console.log(brandid);
	if(parseInt(brandid)>0){
		
		$("#operation").val('UPD');
		
		
	}else{
		
		$("#operation").val('INS');
	}
}

function loadproduct(){
	$('#productDT').DataTable( {
		retrieve: true,
		"bLengthChange": false,
		 "aoColumnDefs": [{ "bSortable": false, "aTargets": [-1]}],
		responsive: true, 
		language: {
			 search: "_INPUT_",
			 searchPlaceholder: "Search..."}
	    } ); 
	}


function downloadsampleformat()
{
$("#productModal").show();

}
function saveexcelcolumn(){
	$("#productModal").hide();
	var columnsheet = $('#sheetcount').val();
	window.open('../product/downloadsampleformat/'+columnsheet,'_new');

}
function cancelimport(){
	$("#myModal").hide();
	var importObject =new Object();
	importObject.cancelimport = digits;
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : '../product/cancelimport/'+digits,
		data : JSON.stringify(importObject),
		success:function(data,textStatus,jqXHR){
			var responseType = data.responseValue.responseType;
			var responseValue = getResponseValue(data);
			console.log(responseValue)
			
			displayAlertMsg("Product Deleted Successfully");	
			
			
		}
	});
}	

function importtoproduct(){
	digits = Math.floor(Math.random() * 9000000000) + 1000000000;
	console.log(digits)
	var uploadedfilename = $('#uploadedfilename').val();
	var importObject =new Object();
	importObject.filename = uploadedfilename;
	console.log(importObject.filename)
	$('#myModal').show();
	
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : '../product/importtoproduct/'+digits,
		data : JSON.stringify(importObject),
		success:function(data,textStatus,jqXHR){
			var responseType = data.responseValue.responseType;
			var responseValue = getResponseValue(data);
			console.log(responseValue)
			$("#myModal").hide();
			displayAlertMsg("Process Completed Successfully");	
			$('#statusDIVid').attr('style','display:""');
			$('#productUplaodStatusDT').DataTable( {
				"destroy":true,
		    	data: responseValue,
		    	responsive: true, 
		    	language: {
		    		 search: "_INPUT_",
		    		 searchPlaceholder: "Search..."},
		        columns: [
		            { data: 'recindex' },
		            { data: 'productsku' },
		            { data: 'uploadstatus' },
		            { data: 'errormsg' }
		           
		        ]
	            
		    } );
			
			
		}
	});
	
	
}
function updateproduct(){
	var uploadedfilename = $('#uploadedfilename').val();
	var importObject =new Object();
	importObject.filename = uploadedfilename;
	console.log(importObject.filename)

	$('#myModal').show();
	
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : '../product/updateproduct',
		data : JSON.stringify(importObject),
		success:function(data,textStatus,jqXHR){
			var responseType = data.responseValue.responseType;
			var responseValue = getResponseValue(data);
			console.log(responseValue)
			$("#myModal").hide();
			displayAlertMsg("Process Completed Successfully");	
			$('#statusDIVid').attr('style','display:""');
			$('#productUplaodStatusDT').DataTable( {
				responsive: true, 
				language: {
					 search: "_INPUT_",
					 searchPlaceholder: "Search..."},
				"destroy":true,
		    	data: responseValue,
		        columns: [
		            { data: 'recindex' },
		            { data: 'productsku' },
		            { data: 'uploadstatus' },
		            { data: 'errormsg' }
		        ]
	            
		    } );
			
			
		}
	});
	
	
	
}

function loadbrand(){
	$('#brandDT').DataTable( {
		retrieve: true,
		"bLengthChange": false,
		 "aoColumnDefs": [{ "bSortable": false, "aTargets": [-1]}],
		responsive: true, 
		language: {
			 search: "_INPUT_",
			 searchPlaceholder: "Search..."}
	    } ); 
	}

function loaduom(){
	$('#uomDT').DataTable( {
		retrieve: true,
		"bLengthChange": false,
		 "aoColumnDefs": [{ "bSortable": false, "aTargets": [-1]}],
		responsive: true, 
		language: {
			 search: "_INPUT_",
			 searchPlaceholder: "Search..."}
	    } ); 
	}
function loadsalesbycustomers(){
	$('#exportProductDt').DataTable( {
		dom: 'Bfrtip',
        buttons: [
              { extend: 'excel', className: 'btn', text:'EXPORT' }
        ],
        "scrollX": true,
	language: {
				 search: "_INPUT_",
				 searchPlaceholder: "Search..."}
		
	} );
}

function loadaddproduct(){
	//console.log("inside")
	var productid = getParameterByName("actionid");
    var productsku=$("#skuid").val();
	
	if(parseInt(productid)>0){
		$('.header').html(productsku);
		$("#operation").val('UPD');
		var genderflag = $("#gendertext").val();
		console.log(genderflag)
		if(genderflag == "unisex"){
			$("#genderunisex").prop('checked', true);	
			$("#gendermale").prop('checked', false);
			$("#genderfemale").prop('checked', false);
		}else if(genderflag == "male"){
			$("#gendermale").prop('checked', true);	
			$("#genderunisex").prop('checked', false);
			$("#genderfemale").prop('checked', false);
		}
		else if(genderflag == "female"){
			$("#genderfemale").prop('checked', true);	
			$("#genderunisex").prop('checked', false);
			$("#gendermale").prop('checked', false);
		}
		selectoption("websiteid");
		selectoption("brandid");
		selectoption("uomid");
		selectoption("stockstatusid");
		
		$(checkCategoryArray).each(function(key,value){
			$("#categoryid"+value.categoryid).prop("checked", true);
		});
		$(checkCategoryArray).each(function(key,value){
			$("#categoryid"+value.precategoryid).prop("checked", true);
		});
		$(checkCategoryArray).each(function(key,value){
			$("#categoryid"+value.subcategoryid).prop("checked", true);
		});
		$("#productid").val(productid);
	}else{
		$("#operation").val('INS');
		$("#productid").val(0);
	}
	
	
	
	/*$.ajax({
		type : 'GET',
		contentType : 'application/json',
		url : '../configurationservices/getcategoryhierarchylist',
		
		success:function(data,textStatus,jqXHR){
			console.log(data)
			var responseType = data.responseType;
			var responseValue = getResponseValue(data);
			console.log(responseType)
			
			
		}
	});*/
	
	
	
}

function stepValidation_product_setup(){
	if($("#websiteid").val() == "" || $("#skuid").val() == "" ||  $("#productnameid").val() == "" ||
			 $("#brandid").val() == "" ||  $("#uomid").val() == ""){
		displayAlertMsg("Please Enter All Mandatory Items");	
		return false;
	}else{
	$(".product_details").click();
	}
}

function stepValidation_product_details(){
	$(".product_attributes").click();
}

function stepValidation_product_category(){
	$(".product_stock").click();
}
/*function stepValidation_product_attributes(){
	checkCategoryArray = [];
	$("#moduleDt tbody tr").each(function(){
		var checkCategory =  new Object();
		var id = $(this).find('input[type=checkbox]').attr('id');
		var checkcondition = $(this).find('input[type=checkbox]').is(':checked');
		console.log(checkcondition)
		var categoryid = $(this).find('input[type=checkbox]').attr('dataid');
		var categorylevel=$(this).find('input[type=checkbox]').attr('datalevel');
		var categoryparent=$(this).find('input[type=checkbox]').attr('dataparent');
		
		if(checkcondition == true){
			checkCategory.categoryid = categoryid;
			checkCategory.categorylevel = categorylevel;
			checkCategory.categoryparent = categoryparent;
			checkCategoryArray.push(checkCategory);
		} 
	});
	

	
	console.log(checkCategoryArray)
	 var	checkCategoryidArray = [];
		var	checkPrecategoryArray = [];
			var checkSubcategoryArray = [];
		$(checkCategoryArray).each(function(key,val){
			
			var checkCategory =  new Object();
			
			if((val.categorylevel) == 0){
				checkCategory.categoryid= val.categoryid;
				console.log(checkCategory.categoryid)
				checkCategoryidArray.push(checkCategory);
				console.log(checkCategoryidArray.push(checkCategory))
			}
			 if((val.categorylevel) == 1){
				checkCategory.categoryid= val.categoryid;
				checkCategory.categoryparent= val.categoryparent;
				checkPrecategoryArray.push(checkCategory);
				
			}
			 if((val.categorylevel) == 2){
				checkCategory.categoryid= val.categoryid;
				checkCategory.categoryparent= val.categoryparent;
				checkSubcategoryArray.push(checkCategory);
				
			}
		});
		
		
		if(checkCategoryidArray.length == 0){
			displayAlertMsg("Please Select anyone Category");
	    	return false;
		}
		else if(checkPrecategoryArray.length == 0){
			displayAlertMsg("Please Select anyone Precategory");
	    	return false;
		}
		else if(checkSubcategoryArray.length == 0){
			displayAlertMsg("Please Select anyone Subcategory");
	    	return false;
		}
		else{
			var selectedCategoryNodeArray = [];
			var	afterSelectionCategoryidArray = [];
			
			
			$(checkCategoryidArray).each(function(key, value){
				
				var categoryid = value.categoryid;
				console.log("category id"+categoryid)
				console.log($.inArray(categoryid,afterSelectionCategoryidArray))
				 var categoryvalid = $.inArray(categoryid,afterSelectionCategoryidArray);
				if(categoryvalid < 0){
					afterSelectionCategoryidArray.push(categoryid);
					console.log(afterSelectionCategoryidArray)
					var	afterSelectionPrecategoryArray = [];
				$(checkPrecategoryArray).each(function(key, value){
					var pre_categoryid = value.categoryid;
					var pre_categoryvalid = $.inArray(pre_categoryid,afterSelectionPrecategoryArray);
					
					if(pre_categoryvalid < 0){
					afterSelectionPrecategoryArray.push(pre_categoryid);
					console.log("pre category id"+pre_categoryid);
					var pre_category_parent = value.categoryparent;
					console.log("pre_category_parent   "+pre_category_parent)
					if(pre_category_parent == categoryid){
						
						var afterSelectionSubcategoryArray = [];
						$(checkSubcategoryArray).each(function(key, value){
							var sub_categoryid = value.categoryid;
							var sub_categoryvalid = $.inArray(sub_categoryid,afterSelectionSubcategoryArray);
							console.log("sub ategory id"+sub_categoryid)
							
							if(sub_categoryvalid < 0){
							
							
							var sub_category_parent = value.categoryparent;
							if(sub_category_parent == pre_categoryid){
								var object = new Object();
								object.categoryid = categoryid;
								object.precategory = pre_categoryid;
								object.subcategory = sub_categoryid;
								selectedCategoryNodeArray.push(object);
								console.log("----------selected object ------------");
								console.log(object);
								afterSelectionSubcategoryArray.push(sub_categoryid);
							}
							}
						});
					}
					
					}
				});
				}
				
			});
			
			console.log("-----------------saras-------------------")
			console.log(selectedCategoryNodeArray);
		}
		
		
	$(".product_stock").click();
}*/

function stepValidation_product_stock(){
	if($("#origprice").val() == "" || $("#currentprice").val() == "" ||  $("#offerprice").val() == "" ||
			 $("#productcount").val() == "" ||  $("#stockstatusid").val() == ""){
		displayAlertMsg("Please Enter All Mandatory Items");	
		return false;
	}else{
		$(".product_image_upload").click();
	}
	
}
