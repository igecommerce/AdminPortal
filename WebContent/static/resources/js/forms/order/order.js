function loadorders(){
	
	$.ajax({
		type : 'GET',
		contentType : 'application/json',
		url : '../order/orderlistpage',
		success:function(data,textStatus,jqXHR){
			
			var responseType = data.responseType;
			var orderList = getResponseValue(data);
			
			if(responseType == 'S')
				{
				console.log(orderList);
				
				$('#orderDT').DataTable( {
			    	data: orderList,
			    	dom: 'Bfrtip',
			    	buttons: [
			    	              { extend: 'excel', className: 'btn', text:' EXPORT ' }
			    	        ],
			    	columns: [
			            { data: 'customerid' },
			            { data: 'customername' },
			            { data: 'productname' },
			            { data: 'totalprice' },
			            { data: 'mobileno' },
			            { data: 'action' }
			        ],
			        responsive: true, 
					language: {
						 search: "_INPUT_",
						 searchPlaceholder: "Search..."},
						 retrieve: true,
							"bLengthChange": false,
							 "aoColumnDefs": [ { "bSortable": false, "aTargets": [-1]}],
						 "order": [
				                      [0, 'desc']      
				                     ]
							

		           
			    } ); 
				}
		}
	});	
	
	
}
function loadorderdetails(){
	
	
	
	$.ajax({
		type : 'GET',
		contentType : 'application/json',
		url : '../configurationservices/orderdetails',
		success:function(data,textStatus,jqXHR){
			console.log(data)
			var responseType = data.responseType;
			var prodArrayList = getResponseValue(data);
			if(responseType == 'S')
				{
			
				
				
				}
		}
	});	
	
	
}



function savestatuscustomer(customerid){
	var customerid =$('#customeridtext').val();
	var orderObject = new Object();
	
	orderObject.customerid = customerid;
	if($('#myonoffswitch2'+customerid).is(':checked')){
		alert("priya")
		orderObject.status = 1;
	}else{
		
		orderObject.status = 0;
	}
	console.log(orderObject)
	
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : '../customer/savestatuscustomer',
		data : JSON.stringify(orderObject),
		success:function(data,textStatus,jqXHR){
			var responseType = data.responseValue.responseType;
			var responseValue = getResponseValue(data);
			if(responseValue.o_out_flag == 'S'){
				displayAlertMsg(responseValue.o_out_msg);	
				 setTimeout(function(){ window.location.href = "../customer/customerlist"; }, 3000);
			}
			else{
				displayAlertMsg(responseValue.o_out_msg);	
			}
		}
	});
	
}


function savestatusproduct(productid){
	
	var productObject = new Object();
	productObject.productid = productid;
	if($('#myonoffswitch1'+productid).is(':checked')){
		productObject.status = 1;
	}else{
		productObject.status = 0;
	}
	console.log(productObject.productid)
	$.ajax({
		type : 'POST',
		contentType : 'application/json',
		url : '../customer/savestatusproduct',
		data : JSON.stringify(productObject),
		success:function(data,textStatus,jqXHR){
			console.log(data)
			var responseType = data.responseValue.responseType;
			var responseValue = getResponseValue(data);
			displayAlertMsg(responseValue.o_out_msg);
			
		}
	});
	
}
function orderdetails(orderid){
	window.location.href="orderdetails?v="+orderid;
}

function exportOrderTable(){
	window.open('../configurationservices/exportOrderTable','_new');
}
function loadcustomer(){
	$('#customerDT').DataTable( {
		retrieve: true,
		"bLengthChange": false,
		 "aoColumnDefs": [{ "bSortable": false, "aTargets": [-1]}],
		 responsive: true, 
			language: {
				 search: "_INPUT_",
				 searchPlaceholder: "Search..."}
	    } ); 
	
	}
