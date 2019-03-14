<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GAIA - DashBoard</title>
</head>
<body>
<jsp:include page="../template/menu.jsp"></jsp:include>
	<section id="content">
				<div class="container">
				  <div class="section">
						<div id="input-fields">
							<h4 class="header">Add New Product</h4>
							<div class="row">
								<form class="col s12" action="projectlist.html">
									<div class="row">
										<div class="input-field col s6">
										  <input id="last_name" type="text" class="validate">
										  <label for="last_name">Name</label>
										</div>
										<div class="input-field col s6" style="padding:0; margin:0;">
											<!-- <label for="input_text">Gender</label> -->
											<div class="input-field col s2" style="padding:0; margin:0;">
												<input name="status" type="radio" value="Active" id="active" />
												<label for="active">Male</label>
											</div>
											<div class="input-field col s2" style="padding:0; margin:0;">
												<input name="status" type="radio" value="Inactive" id="inactive" />
												<label for="inactive">Female</label>
											</div>
										</div>	
									</div>
									<div class="row">
										<div class="input-field col s6">
											<!-- <label for="category">Category</label> -->
											<select id="category" class="selectpicker" multiple data-live-search="false">
												<option>option 1</option>
												<option>Option 2</option>
											</select>
										</div>
										
										<div class="input-field col s6" value="sa">
											<select class="selectpicker" multiple data-live-search="true" >
												<option>option 1</option>
												<option>Option 2</option>
											</select>
										</div>
									</div>
									<div class="row">
										<div class="input-field col s6">
										  <input id="brands" type="text">
										  <label for="brands">Brands</label>
										</div>
										<div class="input-field col s6">
											<input id="sku" type="text" >
											<label for="sku">SKU</label>
										</div>	
									</div>
									<div class="row">
										<div class="input-field col s6">
											<label for="last_name">Ratings</label>
											<div class="rating">
												<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.1.0/css/all.css" integrity="sha384-lKuwvrZot6UHsBSfcMvOkWwlCMgc0TaWr+30HWe3a4ltaBwTZhyTEggF5tJv8tbt" crossorigin="anonymous">
												<input class="ratinp" type="radio" name="rating[03]" value="10" id="rating_03_10">
												<span class="ratlab" for="rating_03_10"></span>
												<input class="ratinp" type="radio" name="rating[03]" value="9" id="rating_03_9">
												<span class="ratlab" for="rating_03_9"></span>
												<input class="ratinp" type="radio" name="rating[03]" value="8" id="rating_03_8">
												<span class="ratlab" for="rating_03_8"></span>
												<input class="ratinp" type="radio" name="rating[03]" value="7" id="rating_03_7">
												<span class="ratlab" for="rating_03_7"></span>
												<input class="ratinp" type="radio" name="rating[03]" value="6" id="rating_03_6">
												<span class="ratlab" for="rating_03_6"></span>
												<input class="ratinp" type="radio" name="rating[03]" value="5" id="rating_03_5">
												<span class="ratlab" for="rating_03_5"></span>
												<input class="ratinp" type="radio" name="rating[03]" value="4" id="rating_03_4">
												<span class="ratlab" for="rating_03_4"></span>
												<input class="ratinp" type="radio" name="rating[03]" value="3" id="rating_03_3">
												<span class="ratlab" for="rating_03_3"></span>
												<input class="ratinp" type="radio" name="rating[03]" value="2" id="rating_03_2">
												<span class="ratlab" for="rating_03_2"></span>
												<input class="ratinp" type="radio" name="rating[03]" value="1" id="rating_03_1">
												<span class="ratlab" for="rating_03_1"></span>
											</div>
										</div>	
									</div>
									<div class="row">
										<div class="input-field col s6">
										  <input id="measurement" type="text">
										  <label for="measurement">Measurement</label>
										</div>
										<div class="input-field col s6">
											<input id="input_text" type="text" >
											<label for="input_text">Unit Price</label>
										</div>	
									</div>			
									<div class="row">
										<div class="input-field col s6">
											<input id="special_price" type="text">
										    <label for="special_price">Special Price</label>
										</div>	
										<div class="input-field col s6">
											<select>
											  <option value="">Currency</option>
											  <option value="1">option 1</option>
											  <option value="2">option 2</option>
											</select>
										</div>
									</div>
									<div class="row">
										<div class="input-field col s6">
											<input id="qty" type="text">
										    <label for="qty">Quantity</label>
										</div>	
									</div>
									<div class="row">
										<div class="input-field col s6">
											<textarea id="textarea1" class="materialize-textarea" length=""></textarea>
											<label for="textarea1">Description</label>
										</div>
										<div class="input-field col s6">
											<textarea id="textarea1" class="materialize-textarea" length=""></textarea>
											<label for="textarea1">Usage</label>
										</div>
									</div>
									<div class="row">
										<div class="input-field col s6">
											<textarea id="textarea1" class="materialize-textarea" length=""></textarea>
											<label for="textarea1">Composition</label>
										</div>
									</div>
									<div class="row">
									  <div class="col s12 m4 l3">
										 <p>Images</p><br>
										 <input type="file" value="" id="files" class="dropify" data-default-file=""/>
										 <label for="files">Base</label><br><br>										 
										 <input type="file" id="input-file-now" class="dropify" data-default-file=""/>
										 <label for="files">Thumbnail 01</label><br><br>
										 <input type="file" id="input-file-now" class="dropify" data-default-file=""/>
										 <label for="files">Thumbnail 02</label><br><br>
										 <input type="file" id="input-file-now" class="dropify" data-default-file=""/>
										 <label for="files">Thumbnail 03</label><br><br>
									  </div>
									  <div class="col s12 m8 l9">
										  
									  </div>
									</div>
									<div class="row">
										<div class="input-field col s6">
											<input id="related_products" type="text" class="validate">
										    <label for="related_products">Related Products</label>
										</div>	
									</div>
									<br><br>
									<div class="row">
										<div class="col s12 center-align">
										  <button class="btn waves-effect waves-light " type="submit" name="action">Submit</button>
										</div>
									</div>
								</form>
							</div>
						</div><!--inpur-fields--->
					</div><!--section--->
				</div><!---container--->
			</section>
			 <jsp:include page="../template/footer.jsp"></jsp:include>
</body>
</html>