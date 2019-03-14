<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
 
<!-- <link href="../static/resources/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection">
<link href="../static/resources/css/style.css" type="text/css" rel="stylesheet" media="screen,projection">
<link href="../static/resources/css/custom/custom-style.css" type="text/css" rel="stylesheet" media="screen,projection">
<link href="../static/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.css" type="text/css" rel="stylesheet" media="screen,projection">
<link href="../static/resources/js/plugins/jvectormap/jquery-jvectormap.css" type="text/css" rel="stylesheet" media="screen,projection">
<link href="../static/resources/js/plugins/chartist-js/chartist.min.css" type="text/css" rel="stylesheet" media="screen,projection">
<link href="../static/resources/js/plugins/chartist-js/chartist.min.css" type="text/css" rel="stylesheet" media="screen,projection">

<script type="text/javascript" src="../static/resources/js/plugins/jquery-1.11.2.min.js"></script>    
<script type="text/javascript" src="../static/resources/js/materialize.js"></script>
<script type="text/javascript" src="../static/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>
<script type="text/javascript" src="../static/resources/js/plugins/sparkline/jquery.sparkline.min.js"></script>
<script type="text/javascript" src="../static/resources/js/plugins/sparkline/sparkline-script.js"></script>

<script type="text/javascript" src="../static/resources/js/plugins.js"></script>
<script type="text/javascript" src="../static/resources/js/custom-script.js"></script>

<script type="text/javascript" src="../static/resources/js/plugins/sparkline/sparkline-script.js"></script>
<script type="text/javascript" src="../static/resources/js/plugins/sparkline/sparkline-script.js"></script>

<link href="../static/resources/js/plugins/data-tables/css/jquery.dataTables.css" type="text/css" rel="stylesheet" media="screen,projection">
<link href="../static/resources/js/plugins/data-tables/css/buttons.dataTables.css" type="text/css" rel="stylesheet" media="screen,projection">

<script type="text/javascript" src="../static/resources/js/plugins/data-tables/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="../static/resources/js/plugins/data-tables/js/jszip.min.js"></script>
<script type="text/javascript" src="../static/resources/js/plugins/data-tables/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="../static/resources/js/plugins/data-tables/js/pdfmake.min.js"></script>
<script type="text/javascript" src="../static/resources/js/plugins/data-tables/js/vfs_fonts.js"></script>
<script type="text/javascript" src="../static/resources/js/plugins/data-tables/js/buttons.html5.min.js"></script>

<script type="text/javascript" src="../static/resources/js/forms/urldetails.js"></script>
<script type="text/javascript" src="../static/resources/js/forms/util/gaiautil.js"></script>
<script type="text/javascript" src="../static/resources/js/forms/login/login.js"></script>

<script type="text/javascript" src="../static/resources/js/util.js"></script>
<link href="../static/resources/css/gaia-style.css" rel="stylesheet" type="text/css" /> -->


<link href="../static/resources/css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection">
<link href="../static/resources/css/style.css" type="text/css" rel="stylesheet" media="screen,projection">
<link href="../static/resources/css/custom/custom-style.css" type="text/css" rel="stylesheet" media="screen,projection">
<link href="../static/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.css" type="text/css" rel="stylesheet" media="screen,projection">
<link href="../static/resources/js/plugins/jvectormap/jquery-jvectormap.css" type="text/css" rel="stylesheet" media="screen,projection">
<link href="../static/resources/js/plugins/chartist-js/chartist.min.css" type="text/css" rel="stylesheet" media="screen,projection">
<link href="../static/resources/js/plugins/chartist-js/chartist.min.css" type="text/css" rel="stylesheet" media="screen,projection">
<link rel="stylesheet" type="text/css" href="../static/resources/js/plugins/data-tables-new/jquery.dataTables.1.10.19.min.css">
<link rel="stylesheet" type="text/css" href="../static/resources/js/plugins/data-tables-new/buttons.dataTables.1.5.2.min.css">

<script type="text/javascript" src="../static/resources/js/plugins/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../static/resources/js/plugins/data-tables-new/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="../static/resources/js/plugins/data-tables-new/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="../static/resources/js/plugins/data-tables-new/buttons.flash.min.js"></script>
<script type="text/javascript" src="../static/resources/js/plugins/data-tables-new/jszip.min.js"></script>
<script type="text/javascript" src="../static/resources/js/plugins/data-tables-new/pdfmake.min.js"></script>
<script type="text/javascript" src="../static/resources/js/plugins/data-tables-new/vfs_fonts.js"></script>
<script type="text/javascript" src="../static/resources/js/plugins/data-tables-new/buttons.html5.min.js"></script>
<script type="text/javascript" src="../static/resources/js/plugins/data-tables-new/buttons.print.min.js"></script>

<script type="text/javascript" src="../static/resources/js/materialize.js"></script>
<script type="text/javascript" src="../static/resources/js/plugins/perfect-scrollbar/perfect-scrollbar.min.js"></script>
<script type="text/javascript" src="../static/resources/js/plugins/sparkline/jquery.sparkline.min.js"></script>
<script type="text/javascript" src="../static/resources/js/plugins/sparkline/sparkline-script.js"></script>
<script type="text/javascript" src="../static/resources/js/plugins.js"></script>
<script type="text/javascript" src="../static/resources/js/custom-script.js"></script>
<script type="text/javascript" src="../static/resources/js/plugins/sparkline/sparkline-script.js"></script>
<script type="text/javascript" src="../static/resources/js/plugins/sparkline/sparkline-script.js"></script>
<script type="text/javascript" src="../static/resources/js/forms/urldetails.js"></script>
<script type="text/javascript" src="../static/resources/js/forms/util/gaiautil.js"></script>
<script type="text/javascript" src="../static/resources/js/forms/login/login.js"></script>

<script type="text/javascript" src="../static/resources/js/util.js"></script>
<link href="../static/resources/css/gaia-style.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="../static/resources/js/forms/order/order.js"></script>


</head>
<body>

</body>
</html>