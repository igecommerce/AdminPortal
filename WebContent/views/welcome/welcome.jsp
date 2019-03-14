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

<section id="content" style="padding:2% 1%">

                
                <div class="container">

                    <!--card stats start-->
                    <div id="card-stats">
                        <div class="row">
                            <div class="col s12 m6 l3">
                                <div class="card">
                                    <div class="card-content white-text box1">
										<span>Daily Orders</span>
                                        <h4 class="card-stats-number">566</h4>
                                    </div>
                                </div>
                            </div>
                            <div class="col s12 m6 l3">
                                <div class="card">
                                    <div class="card-content white-text box2">
										<span>Daily Sales</span>
                                        <h4 class="card-stats-number">1806</h4>
                                    </div>
                                </div>
                            </div>
                            <div class="col s12 m6 l3">
                                <div class="card">
                                    <div class="card-content white-text box3">
										<span>Total Users</span>
                                        <h4 class="card-stats-number">806</h4>
                                    </div>
                                </div>
                            </div>
                            <div class="col s12 m6 l3">
                                <div class="card">
                                    <div class="card-content white-text box4">
										<span>YTD Orders</span>
                                        <h4 class="card-stats-number">8990</h4>
                                    </div>
                                </div>
                            </div> 
							<div class="col s12 m6 l3">
                                <div class="card">
                                    <div class="card-content white-text box5">
										<span>YTD Sales</span>
                                        <h4 class="card-stats-number">8995</h4>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    
                            

                    </div>
                    

                </div>
                
            </section>
            <jsp:include page="../template/footer.jsp"></jsp:include>
</body>
</html>