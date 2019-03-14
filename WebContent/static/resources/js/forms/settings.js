function getproductlist()
{
	var productlistapiiurl = applncontexturl + productlistapi;
	console.log(productlistapiiurl);
	$.ajax({
		type:'GET',
		contentType:'application/json',
		url:productlistapiiurl,
		success:function(data,textStatus,jqXHR)
		{
			var productList = data.data;
			console.log(productList);
			var dt = new Date();
			var time = dt.getHours() + "-" + dt.getMinutes() + "-" + dt.getSeconds();
			console.log(time)
			$('#productlistDT').DataTable( {
		    	data: productList,
		    	dom: 'Bfrtip',
		        buttons: [
		             /*'excel'*/
		             {
                extend: 'excel',
                filename: 'Product List at '+time
            }
		        ],
		        columns: [
		            { data: 'id' },
		            { data: 'first_name' },
		            { data: 'last_name' },
		            { data: 'id' },
		            { data: 'first_name' },
		            { data: 'last_name' },
		            { data: 'last_name' }
		        ]
	            
		    } ); 
		}
	});
	
}