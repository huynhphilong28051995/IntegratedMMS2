<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
    <!-- BEGIN HEAD -->
<head>
<meta charset="utf-8"/>
<title>Merlion Mall Asia | Admin Home</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta content="width=device-width, initial-scale=1.0" name="viewport"/>
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta content="" name="description"/>
<meta content="" name="author"/>
<!-- BEGIN GLOBAL MANDATORY STYLES -->
<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css">
<link href="../assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="../assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css">
<link href="../assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="../assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css">
<!-- END GLOBAL MANDATORY STYLES -->
<link rel="stylesheet" type="text/css" href="../assets/global/plugins/bootstrap-toastr/toastr.min.css"/>
<!-- BEGIN CUSTOM STYLES -->
<link href="../assets/global/css/components-rounded.css" id="style_components" rel="stylesheet" type="text/css">
<link href="../assets/global/css/plugins.css" rel="stylesheet" type="text/css">
<link href="../assets/admin/interface/css/layout.css" rel="stylesheet" type="text/css">
<link href="../assets/admin/interface/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color">
<link href="../assets/admin/interface/css/custom.css" rel="stylesheet" type="text/css">
<!-- END CUSTOM STYLES -->
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-menu-fixed">
<!-- BEGIN HEADER -->
<div class="page-header">
	<!-- BEGIN HEADER TOP -->
	<div class="page-header-top">
		<div class="container">
			<!-- BEGIN LOGO -->
			<div class="page-logo">
				<a href="adminHome"><img src="../assets/admin/interface/img/logo_small.png" alt="logo" class="logo-default"></a>
			</div>
			<!-- END LOGO -->
			<!-- BEGIN RESPONSIVE MENU TOGGLER -->
			<a href="javascript:;" class="menu-toggler"></a>
			<!-- END RESPONSIVE MENU TOGGLER -->
			<!-- BEGIN TOP NAVIGATION MENU -->
			<div class="top-menu">
				<ul class="nav navbar-nav pull-right">
						<span class="separator"></span>
					</li>
					<!-- BEGIN USER LOGIN DROPDOWN -->
					<li class="dropdown dropdown-user dropdown-dark">
						<a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                        
						<span class="username username-hide-mobile">Welcome, <%= session.getAttribute("Session2") %></span>
						</a>
						<ul class="dropdown-menu dropdown-menu-default">
							<li>
								<a href="extra_profile">
								<i class="icon-user"></i> User Settings </a>
							</li>
							<li>
								<a href="logout">
								<i class="icon-key"></i> Log Out </a>
							</li>
						</ul>
					</li>
					<!-- END USER LOGIN DROPDOWN -->
				</ul>
			</div>
			<!-- END TOP NAVIGATION MENU -->
		</div>
	</div>
	<!-- END HEADER TOP -->
			<!-- BEGIN HEADER MENU -->
         <div class="page-header-menu">
            <div class="container">
               <!-- BEGIN HEADER SEARCH BOX -->
               <form class="search-form" action="search" method="GET">
                  <div class="input-group">
                     <input type="text" class="form-control" placeholder="Search" name="query">
                     <span class="input-group-btn">
                     <a href="javascript:;" class="btn submit"><i class="icon-magnifier"></i></a>
                     </span>
                  </div>
               </form>
               <!-- END HEADER SEARCH BOX -->
               <div class="hor-menu ">
                  <ul class="nav navbar-nav">
                  <li class="active">
                     <a href="adminHome">Admin Home</a>
                  </li>
                  <li class="menu-dropdown classic-menu-dropdown">
                     <a data-hover="megamenu-dropdown" data-close-others="true" data-toggle="dropdown" href="javascript:;">
                     General Management <i class="fa fa-angle-down"></i>
                     </a>
                     <ul class="dropdown-menu pull-left">
                        <li class=" dropdown-submenu">
                           <a href="#">
                           Department </a>
                           <ul class="dropdown-menu">
                              <li class=" ">
                                 <a href="addDepartment">
                                 Add Department </a>
                              </li>
                              <li class=" ">
                                 <a href="manageDepartment">
                                 Manage Department </a>
                              </li>
                           </ul>
                        </li>
                        <li class=" dropdown-submenu">
                           <a href="#">
                           Employee </a>
                           <ul class="dropdown-menu">
                              <li class=" ">
                                 <a href="addEmployee">
                                 Add Employee </a>
                              </li>
                              <li class=" ">
                                 <a href="manageEmployee">
                                 Manage Employee </a>
                              </li>
                           </ul>
                        </li>
                        <li class=" dropdown-submenu">
                           <a href="#">
                           Position </a>
                           <ul class="dropdown-menu">
                              <li class=" ">
                                 <a href="addPosition">
                                 Add Position </a>
                              </li>
                              <li class=" ">
                                 <a href="managePosition">
                                 Manage Position </a>
                              </li>
                           </ul>
                        </li>
                     </ul>
                  </li>
                  <li class="menu-dropdown classic-menu-dropdown">
                     <a data-hover="megamenu-dropdown" data-close-others="true" data-toggle="dropdown" href="javascript:;">
                     Level Management <i class="fa fa-angle-down"></i>
                     </a>
                     <ul class="dropdown-menu pull-left">
                        <li class=" dropdown-submenu">
                           <a href="#">
                           Merlion Corporate Headquarter(s) </a>
                           <ul class="dropdown-menu">
                              <li class=" ">
                                 <a href="addLevel?=corporate">
                                 Add Corporate Headquarter</a>
                              </li>
                              <li class=" ">
                                 <a href="manageLevel">
                                 Manage Headquarter</a>
                              </li>
                           </ul>
                        </li>
                        <li class=" dropdown-submenu">
                           <a href="#">
                           Merlion Office(s) </a>
                           <ul class="dropdown-menu">
                              <li class=" ">
                                 <a href="addLevel?=local">
                                 Add Office </a>
                              </li>
                              <li class=" ">
                                 <a href="manageLevel">
                                 Manage Office(s) </a>
                              </li>
                           </ul>
                        </li>
                        <li class=" dropdown-submenu">
                           <a href="#">
                           Merlion Mall(s) </a>
                           <ul class="dropdown-menu">
                              <li class=" ">
                                 <a href="addLevel?=mall">
                                 Add Mall </a>
                              </li>
                              <li class=" ">
                                 <a href="manageLevel">
                                 Manage Mall(s) </a>
                              </li>
                           </ul>
                        </li>
                     </ul>
					</li>
               </div>
               <!-- END MEGA MENU -->
            </div>
         </div>
         <!-- END HEADER MENU -->
</div>
<!-- END HEADER -->
<!-- BEGIN PAGE CONTAINER -->
<div class="page-container">
	<!-- BEGIN PAGE HEAD -->
	<div class="page-head">
		<div class="container">
			<!-- BEGIN PAGE TITLE -->
			<div class="page-title">  
				<h1>Admin Dashboard </h1>
			</div>
			<!-- END PAGE TITLE -->

		</div>
	</div>
	<!-- END PAGE HEAD -->
	<!-- BEGIN PAGE CONTENT -->
	<div class="page-content">
		<div class="container">
			<!-- BEGIN PAGE BREADCRUMB -->
			<ul class="page-breadcrumb breadcrumb">
				<li class="active">
					<a href="#">Home</a></i>
				</li>
			</ul>
			<!-- END PAGE BREADCRUMB -->
			<!-- BEGIN PAGE CONTENT INNER -->
			<div class="row">
				<div class="col-md-12">
                                    <div class="note note-info note-bordered">
				<p>Page content goes here.</p>
			</div>
			<div class="row">
				<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 margin-bottom-10">
					<a class="dashboard-stat dashboard-stat-light blue-madison" href="manageLevel">
					<div class="visual">
						<i class="fa fa-university fa-icon-medium"></i>
					</div>
					<div class="details">
						<div class="number">
							 <% int levelCount = Integer.parseInt(request.getAttribute("totalLevelSize").toString()); %>
                                                         <%= levelCount %>
						</div>
						<div class="desc">
							 Organizational Level(s)
						</div>
					</div>
					</a>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
					<a class="dashboard-stat dashboard-stat-light red-intense" href="manageDepartment">
					<div class="visual">
						<i class="fa fa-briefcase"></i>
					</div>
					<div class="details">
						<div class="number">
							 <% int deptCount = Integer.parseInt(request.getAttribute("totalDepartmentSize").toString()); %>
                                                         <%= deptCount %>
						</div>
						<div class="desc">
							 Department(s)
						</div>
					</div>
					</a>
				</div>
				<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
					<a class="dashboard-stat dashboard-stat-light green-haze" href="manageEmployee">
					<div class="visual">
						<i class="fa fa-group fa-icon-medium"></i>
					</div>
					<div class="details">
						<div class="number">
							 <% int employeeCount = Integer.parseInt(request.getAttribute("totalEmployeeSize").toString()); %>
                                                         <%= employeeCount %>
						</div>
						<div class="desc">
							 Employee(s)
						</div>
					</div>
					</a>
				</div>
			</div>

				</div>
			</div>
			<!-- END PAGE CONTENT INNER -->
		</div>
	</div>
	<!-- END PAGE CONTENT -->
</div>
<!-- END PAGE CONTAINER -->

<!-- BEGIN FOOTER -->
<div class="page-footer">
	<div class="container">
		 2015 &copy; Merlion Mall Asia. 
	</div>
</div>
<div class="scroll-to-top">
	<i class="icon-arrow-up"></i>
</div>
<!-- END FOOTER -->
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
<!-- BEGIN PAGE PLUGINS -->
<!--[if lt IE 9]>
<script src="../assets/global/plugins/respond.min.js"></script>
<script src="../assets/global/plugins/excanvas.min.js"></script> 
<![endif]-->
<script src="../assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="../assets/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>
<script src="../assets/global/plugins/jquery-ui/jquery-ui.min.js" type="text/javascript"></script>
<script src="../assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="../assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
<script src="../assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="../assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="../assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
<script src="../assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="../assets/global/plugins/jquery-idle-timeout/jquery.idletimeout.js" type="text/javascript"></script>
<script src="../assets/global/plugins/jquery-idle-timeout/jquery.idletimer.js" type="text/javascript"></script>
<script src="../assets/global/plugins/bootstrap-toastr/toastr.min.js"></script>
<!-- END PAGE PLUGINS -->
<script src="../assets/global/scripts/custom.js" type="text/javascript"></script>
<script src="../assets/admin/interface/scripts/layout.js" type="text/javascript"></script>
<script src="../assets/admin/pages/scripts/ui-idletimeout.js"></script>
<script src="../assets/admin/pages/scripts/ui-toastr.js"></script>
<script>
      jQuery(document).ready(function() {    
         Custom.init(); // init custom core components
	 Layout.init(); // init current layout
         UIIdleTimeout.init(); // init Idle Timeout
         UIToastr.init(); // init Toastr Alert
      });
</script>
<% String referrer = request.getHeader("referer");
   String query = request.getQueryString();
   String timestamp = null;
%>
<% if(referrer.matches("http://localhost:8080/MMS2Server-war/administration/login") || 
        referrer.matches("http://localhost:8080/MMS2Server-war/administration/logout") || 
        referrer.matches("http://localhost:8080/MMS2Server-war/administration/adminHome")) { 
         timestamp = "Your last login was on: " + session.getAttribute("Session5").toString();
         if ("=continue".equals(query)) {
%>        
<script language="javascript">
var ts = '<%= timestamp %>';
$(document).ready(function() {
	// show when page load
	toastr.info('Welcome back!');
        
});
</script>
<% } else {%>
<script language="javascript">
var ts = '<%= timestamp %>';
$(document).ready(function() {
	// show when page load
	toastr.success(ts , 'Login Successful!');
        
});
</script>
<%}
} %>
<!-- END JAVASCRIPTS -->

</body>
<!-- END BODY -->
</html>
