<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%
    String query = request.getQueryString();
    String message = (String)request.getAttribute("data");
    System.err.println("TEST 1 "+message);
%>
<!DOCTYPE html>
<html>
    <!-- BEGIN HEAD -->
    <head>
        <meta charset="utf-8"/>
        <title>Merlion Mall Asia | Admin Home - First Time Login Password Change</title>
        <%
            if (message.contains("Success")) {
        %> 
        <META HTTP-EQUIV="Refresh" CONTENT="10;URL=employee/logout">
        <% }%>
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
                        <a href="changePassword"><img src="../assets/admin/interface/img/logo_small.png" alt="logo" class="logo-default"></a>
                    </div>
                    <!-- END LOGO -->
                    <!-- BEGIN RESPONSIVE MENU TOGGLER -->
                    <a href="javascript:;" class="menu-toggler"></a>
                    <!-- END RESPONSIVE MENU TOGGLER -->
                    <!-- BEGIN TOP NAVIGATION MENU -->
                    <div class="top-menu">
                        <ul class="nav navbar-nav pull-right">
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
                                        <a href="../employee/logout">
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
                                <a href="changePassword">Password Change</a>
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
                        <h1>Initial Password Change </h1>
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
                            <a href="#">Password Change</a>
                        </li>
                    </ul>
                    <!-- END PAGE BREADCRUMB -->
                    <!-- BEGIN PAGE CONTENT INNER -->
                    <div class="row">
                        <div class="col-md-12">
                            <div class="note note-info note-bordered">
                                <p><span class="fa fa-info-circle"></span> &nbsp;&nbsp;&nbsp;&nbsp;Hi <%= session.getAttribute("Session2") %>, 
                                    under the MMA security policy, all first time user(s) are required to change their default password to a new password.</p>
                            </div>
                            <div class="row">

                                <div class="col-md-12">
                                    <div class="portlet light" id="form_wizard_1">   
                                        <div class="portlet-title">
                                            <div class="caption">
                                                <span class="caption-subject font-green-sharp bold uppercase">First Time Login -
                                                    <span class="step-title">Initial Password Change</span></span>
                                            </div>

                                            <div class="tools">
                                                <a href="javascript:;" class="collapse"></a> <a href=
                                                                                                "javascript:;" class="remove"></a>
                                            </div>
                                        </div>    
                                        <div class="portlet-body form">
                                            <% if(message.toString().contains("Error")) { %><div style="display: block;" class="alert alert-danger display-none">
                                                <button class="close" data-dismiss="alert"></button><%= message %>
                                            </div><% } else if (message.toString().contains("Success")) { %> <div class="alert alert-success alert-dismissable">
                                                <button class="close" data-dismiss="alert"></button><%= message %>
                                            </div><% }%>
                                            <% if (!message.toString().contains("Success")) { %>    
                                            <form action="changePassword?change=true" class="form-horizontal" id="submit_form" method="POST">   
                                                <div class="form-wizard">
                                                    <div class="form-body">   
                                                        <div class="form-group">
                                                            <label class="control-label col-md-3">Password <span class=
                                                                                                                 "required">*</span></label>

                                                            <div class="col-md-4">
                                                                <input type="password" class="form-control" name="password1" id=
                                                                       "submit_form_password" placeholder="Provide your password" />
                                                            </div>
                                                        </div>

                                                        <div class="form-group">
                                                            <label class="control-label col-md-3">Confirm Password <span class=
                                                                                                                         "required">*</span></label>

                                                            <div class="col-md-4">
                                                                <input type="password" class="form-control" name="password2" placeholder="Confirm your password" />
                                                            </div>
                                                            <button class="btn green button-submit" type="POST">
                                                                Change Password <i class="m-icon-swapright m-icon-white"></i>
                                                            </button>
                                                        </div>


                                                    </div>
                                                </div>
                                            </form>
                                            <% } %>
                                            <% if (message.toString().contains("Success")) { %>
                                            <div class="dashboard-stat purple-plum">
                                                <div class="visual">
                                                    <i class="fa fa-key"></i>
                                                </div>
                                                <div class="details">
                                                    <div class="number">
                                                        Relogin is required &nbsp;&nbsp;&nbsp;&nbsp;
                                                    </div>
                                                    <div class="desc">
                                                        System is redirecting you to Login Page &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                    </div>
                                                </div>
                                                <a class="more" href="../employee/logout">
                                                    Click here if page does not redirect you automatically. <i class="m-icon-swapright m-icon-white"></i>
                                                </a>
                                            </div><% } %>

                                        </div>
                                    </div>
                                </div>


                            </div>
                            <!-- END PAGE CONTENT INNER -->
                        </div>
                    </div>
                    <!-- END PAGE CONTENT -->
                </div></div></div>
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
            jQuery(document).ready(function () {
                Custom.init(); // init custom core components
                Layout.init(); // init current layout
                UIIdleTimeout.init(); // init Idle Timeout
                UIToastr.init(); // init Toastr Alert
            });
        </script>
        <% 
                if (!"change=true".equals(query)) {
        %>
        }    
        <script language="javascript">
            $(document).ready(function () {
                // show when page load
                toastr.info('Please Change Your Password.');

            });
        </script>
        <% } %>
        <!-- END JAVASCRIPTS -->

    </body>
    <!-- END BODY -->
</html>
