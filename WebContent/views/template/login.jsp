<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript" src="../static/resources/js/forms/login/login.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GAIA - Login</title>

</head>


<body style="background:#F45867;" >
<%-- <jsp:include page="stylelogin.jsp"></jsp:include> --%>
<jsp:include page="style.jsp"></jsp:include>
 <script type="text/javascript">
		$(document).ready(function(){
			
			$( "#password" ).keyup(function(event) {
				 if ( event.which == 13 ) {
					 signin();
				  }
			}); 
			//focuscursor('#name');
		});
		

	</script>
<div class="modal fade" id="alertModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" >Info</h5>
      </div>
      <div class="modal-body">
      	<p id="alertMsg">Invalid Credentials</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" onclick="modalClose('alertModal')">Close</button>
      </div>
    </div>
  </div>
</div>
  <!-- Start Page Loading -->
  <div id="loader-wrapper">
      <div id="loader"></div>        
      <div class="loader-section section-left"></div>
      <div class="loader-section section-right"></div>
  </div>
  <!-- End Page Loading -->

  <div id="login-page" class="row">
    <div class="col s12 z-depth-4 card-panel">
      <form class="login-form">
        <div class="row">
          <div class="input-field col s12 center">
            <img src="../static/resources/images/gaia_logo64X54.png" alt="" class="responsive-img valign profile-image-login">
          </div>
        </div>
        <div class="row margin">
          <div class="input-field col s12">
            <i class="mdi-social-person-outline prefix"></i>
            <input id="username" type="text" >
            <label for="username " class="center-align username active">Username</label>
          </div>
        </div>
        <div class="row margin">
          <div class="input-field col s12">
            <i class="mdi-action-lock-outline prefix"></i>
            <input id="password" type="password" >
            <label for="password" class="password active">Password</label>
          </div>
        </div>
       
        <div class="row">
          <div class="input-field col s12">
            <a  onclick="signin()" class="btn waves-effect waves-light col s12">Login</a>
          </div>
        </div>
        <div class="row">
          
          <div class="input-field col s6 m6 l6">
              <p class="margin right-align medium-small"><a onclick="modal()" >Forgot password ?</a></p>
          </div>          
        </div>

      </form>
    </div>
  </div>




<!-- Modal Structure -->
  <div class="modal size fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <!-- <button type="button" class="close" data-dismiss="modal">&times;</button> -->
          <h6 class="modal-title">Forgot password</h6>
        </div>
        <div ><span style="color:red" id="errrormsg"></span>
        </div>
        <div class="row">
			<div class="input-field col s6">
				
				<input id="usernametext" type="text" class="validate">
				<label for="mail">User Name</label>
        </div>
         </div>
        <div class="modal-footer margin">
         <button type="button" class="btn btn-secondary" onclick="modalClose('myModal')">Close</button>
           <button type="button" class="btn btn-default" onclick="resetpassword();">Go</button> 
        </div>
     
      
    </div>
  </div>
  
</div>

<style type="text/css">
.input-field label {
    font-size: 0.8rem;
    -webkit-transform: translateY(-140%);
    -moz-transform: translateY(-140%);
    -ms-transform: translateY(-140%);
    -o-transform: translateY(-140%);
    transform: translateY(-140%);
}
</style>

<script>
function modal(){
	$('#myModal').show();
}

</script>

<style>
html,
body {
    height: 100%;
}
html {
    display: table;
    margin: auto;
}
body {
    display: table-cell;
    vertical-align: middle;
}
.size {
    width: 48%;
    height:250px;
    /* margin-bottom: 158px; */
    margin-top: -413px;
}
h6{
background-color:#f3476f;
padding:10px;
}

</style>

<script type="text/javascript">
	$(document).ready(function(){
		console.log($("#username").val()+"  "+$("#password").val());
		if($("#username").val()!=""){
			$("label.username").addClass("active");
		}
		if($("#password").val()!=""){
			console.log("saras")
			$("label.password").addClass("active");
		}
	});
</script>

</body>
</html>