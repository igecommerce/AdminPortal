<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body style="background:#F45867;">
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
            <h4>Forgot Password</h4>
            <p class="center">Enter Your Registered Email ID</p>
          </div>
        </div>
        <div class="row margin">
          <div class="input-field col s12">
            <i class="mdi-social-person-outline prefix"></i>
            <input id="username" type="text">
            <label for="username" class="center-align">Email ID</label>
          </div>
        </div>
        <div class="row">
          <div class="input-field col s12">
            <a href="resetpassword.html" class="btn waves-effect waves-light col s12">Reset Password</a>
          </div>
          <div class="input-field col s12">
            <p class="margin sign-up"><a href="#"></a> <a href="login.html" class="right">Cancel</a></p>
          </div>
        </div>
      </form>
    </div>
  </div>
</body>
</html>