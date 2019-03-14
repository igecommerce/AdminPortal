<%@page import="com.gaia.ecom.controller.services.login.GAIA_SERVICES_LOGIN_USERDETAIL"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<script type="text/javascript">
$('a').on('click', function(){

    if(!$(this).parents().hasClass('open')){
    	alert("priyamp")
        $('li').removeClass('open');    
    }
    $(this).parent().addClass('open');
});
</script>
<aside id="left-sidebar-nav">
                <ul id="slide-out" class="side-nav fixed leftside-navigation">
                <li class="user-details">
                <div class="row">
                    <div class="col col s2 m4 l4" style="width:45px;padding-left:0 !important;padding-right:0 !important;padding-top:10px !important;">
                    <%
    				session = request.getSession(); 
                    String loginuserid = null, username= null, roledesc = null;
                    if(session.getAttribute("loginuserid") != null){
                    	loginuserid = String.valueOf(session.getAttribute("loginuserid"));
                    	username = String.valueOf(session.getAttribute("username"));
                    	roledesc = String.valueOf(session.getAttribute("roledesc"));
                    	%>
                    	<img alt="" src="../gaiafiles/documents/user/image/${loginuserid}.jpg"  class="circle responsive-img valign profile-image">
                    	
                       <%--  <img alt="" src="../GAIA_VIEW_IMAGE_FILE?form=user&type=1&id=${id}"  class="circle responsive-img valign profile-image"> --%>
                    </div>
                    <div class="col col s8 m8 l8" style="width:180px;">
                        <ul id="profile-dropdown" class="dropdown-content" >
                            <!-- <li><a href="#"><i class="mdi-action-face-unlock"></i> Profile</a>
                            </li> -->
                            <li><a onclick="changepswd()"><i class="mdi-action-settings"></i> Change Password</a>
                            </li>
                            <!-- <li><a href="#"><i class="mdi-communication-live-help"></i> Help</a>
                            </li>
                            <li class="divider"></li>
                            <li><a href="#"><i class="mdi-action-lock-outline"></i> Lock</a>
                            </li> -->
                            <li><a onclick="logout();"><i class="mdi-hardware-keyboard-tab"></i> Logout</a>
                            </li>
                        </ul>
                        <a class="btn-flat dropdown-button waves-effect waves-light white-text profile-btn" href="#" data-activates="profile-dropdown">${username }<i  class="mdi-navigation-arrow-drop-down right white-icon"></i></a>
                        <p class="user-roal">${roledesc }</p>
                        <%
                    } %>
                    </div>
                </div>
				</li>
				<%-- <li class="bold active"><a href="../template/dashboard"><i class="mdi-action-dashboard"></i> Dashboard</a></li>
				<li class="no-padding">
                    <ul class="collapsible collapsible-accordion">
                        <li class="bold"><a class="collapsible-header waves-effect waves-cyan"><i class="mdi-action-settings"></i>Configuration</a>
                            <div class="collapsible-body">
                                <ul>
                                <li><a href="<s:url value="/configuration/userrole"/>">User Role</a>
                                <li><a href="<s:url value="/configuration/gaiausers"/>">Users</a>
                                   <li><a href="<s:url value="/configuration/category"/>">Category</a>
                                    </li>
                                    <li><a href="<s:url value="/config/subcategory"/>">Sub-Category</a>
                                    </li>
									<li><a href="category.html">Brands</a>
                                    </li>
									<li><a href="sub-category.html">Currency</a>
                                    </li>
									<li><a href="category.html">UOM</a>
                                    </li>
									<li><a href="sub-category.html">State</a>
                                    </li>
									<li><a href="category.html">Country</a>
                                    </li>
									<li><a href="sub-category.html">Terms & Condition</a>
                                    </li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                </li>
                <li class="bold"><a href="<s:url value="/config/customer"/>"><i class="mdi-action-account-circle"></i>Customers</a></li>
				<li class="bold"><a href="<s:url value="/settings/product"/>" ><i class="mdi-action-list"></i> Product List</a></li>
				<li class="bold"><a href="<s:url value="/settings/addnewproduct"/>"><i class="mdi-av-queue"></i> Add New Product</a></li>
				<li class="bold"><a href="<s:url value="/order/orders"/>"><i class="mdi-action-view-carousel"></i> Orders</a></li>
				<li class="bold"><a href="<s:url value="/order/orderdetails"/>"><i class="mdi-av-my-library-books"></i> Order Details</a></li>
				<li class="bold"><a href="<s:url value="/order/invoice"/>"><i class="mdi-action-description"></i>Invoice</a></li>
				
				<li class="bold"><a href="<s:url value="/settings/reports"/>"><i class="mdi-device-now-widgets"></i> Reports</a></li> --%>
				
				<%
				
				if(session.getAttribute("mainMenuList") != null){
				List menuList = (List)session.getAttribute("mainMenuList");
				Iterator mainMenuITR = menuList.iterator();
				while(mainMenuITR.hasNext())
				{
					GAIA_SERVICES_LOGIN_USERDETAIL mainMenuObj = (GAIA_SERVICES_LOGIN_USERDETAIL)mainMenuITR.next();
					//System.out.println("Sub Menu Values are   "+mainMenuObj.getSubmenu()+"   "+mainMenuObj.getModuleid());
					int intsubmenu = (mainMenuObj.getSubmenu() != null ? Integer.parseInt(mainMenuObj.getSubmenu()):0);
					if(intsubmenu > 0)
					{
						Map subMenuMap = (HashMap)session.getAttribute("subMenuMap"); 
						//System.out.println("subMenuMap   "+mainMenuObj.getParentid()+"   "+subMenuMap.get(mainMenuObj.getModuleid()));
						List subMenuList = (List)subMenuMap.get(mainMenuObj.getModuleid());
						Iterator subMenuListITR = subMenuList.iterator();
						
						%>
					<li class="no-padding">
                    <ul class="collapsible collapsible-accordion">
                        <li class="bold <%=mainMenuObj.getModuleid() %>"> <a class="collapsible-header waves-effect waves-cyan"><i class="<%=mainMenuObj.getIcon() %>" aria-hidden="true"></i><%=mainMenuObj.getModulename() %></a>
                            <div class="collapsible-body">
                                <ul>
						<%
						while(subMenuListITR.hasNext())
						{
							GAIA_SERVICES_LOGIN_USERDETAIL subMenuObj = (GAIA_SERVICES_LOGIN_USERDETAIL)subMenuListITR.next();
							%> 
							
                                <li><a href="<s:url value="<%=subMenuObj.getModulepath() %>"/>"><i class="<%=subMenuObj.getIcon() %>" aria-hidden="true"></i> <%=subMenuObj.getModulename() %></a>
                                
                                
							<%
							
						}
						
					%>
					</ul>
                            </div>
                        </li>
                    </ul>
                </li> 
                <%--  <li class="bold"> <a class="collapsible-header waves-effect waves-cyan"><i class="<%=mainMenuObj.getIcon() %>" ></i><%=mainMenuObj.getModulename() %></a> --%>
               <%--  <%
						while(subMenuListITR.hasNext())
						{
							GAIA_SERVICES_LOGIN_USERDETAIL subMenuObj = (GAIA_SERVICES_LOGIN_USERDETAIL)subMenuListITR.next();
							%>
							
                                <li class="bold" style="margin-left:40px;"><a href="<s:url value="<%=subMenuObj.getModulepath() %>"/>">   <i class="mdi-hardware-keyboard-arrow-right"></i> <%=subMenuObj.getModulename() %></a>
                                
                              
							<%
							
						}
						
					%> --%>
                
					<%	
					}
					else
					{
						%>
						 
						<li class="bold"><a href="<s:url value="<%=mainMenuObj.getModulepath() %>"/>"><i class="<%=mainMenuObj.getIcon() %>" ></i>  <%=mainMenuObj.getModulename() %></a></li>
						
						<%
						
					}
					%>
					 
					<%
				}
				
				}else{
					%>
					<script>
					window.location.href = "../welcomeboard/login";
					</script>
					<% 
				}
				%>
				</ul>
                <a href="#" data-activates="slide-out" class="sidebar-collapse btn-floating btn-medium waves-effect waves-light hide-on-large-only cyan"><i class="mdi-navigation-menu"></i></a>
            </aside>
            <!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Change Your Password Here</h5>
      </div>
      <div class="modal-body">
      	<form class="col s12" action="projectlist.html">
									<div class="row">
										<div class="input-field col s6">
										  <input id="oldpassword" type="password" class="validate">
										  <label for="oldpassword">Old Password</label>
										</div>
										<div class="input-field col s6">
										  <input id="newpassword" type="password" class="validate">
										  <label for="newpassword">New Password</label>
										</div>
										<div class="input-field col s6">
										  <input id="confirmpassword" type="password" class="validate">
										  <label for="confirmpassword">Confirm New Password</label>
										</div>
									</div>
								</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" onclick="modalClose('exampleModal')">Close</button>
        <button type="button" class="btn btn-primary" onclick="changepassword()">Save changes</button>
      </div>
    </div>
  </div>
</div>


<!-- Modal -->
<div class="modal fade" id="alertModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Info</h5>
      </div>
      <div class="modal-body">
      	<p id="alertMsg"></p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" onclick="modalClose('alertModal')">Close</button>
      </div>
    </div>
  </div>
</div>

<script>
function changepswd(){
	$("#exampleModal").show();
}

</script>
<%--  <style>
 li ul {
    display: none;
}

li.open > ul, li.open > div {
    display: block;
}
 </style>  --%> 
</body>
</html>