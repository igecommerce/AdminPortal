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
							<h4 class="header">Sub Category</h4>
							<div class="row">
								<form class="col s12" action="">
									<div class="row">
										<!-- <div class="col s3">&nbsp;</div> -->
										<div class="input-field col s3"></div>
										<div class="input-field col s6">
											<select>
											  <option value="">Select Category</option>
											  <option value="1">Category 1</option>
											  <option value="2">Category 2</option>
											</select>
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
			