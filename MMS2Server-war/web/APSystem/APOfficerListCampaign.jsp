<%-- 
    Document   : APOfficerListCampaign
    Created on : 7 Nov, 2015, 11:40:20 PM
    Author     : khushnaz
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>Merlion APS System | List Campaigns</title>
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

        <!--PERSONAL STYLE-->
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css" type="text/css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.9/js/jquery.dataTables.min.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--PERSONAL STYLE-->
    </head>
    <!-- BEGIN BODY -->
    <body class="page-header-menu-fixed">
        <%String IP = (String) request.getSession().getAttribute("IP");%>
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

                                    <span class="username username-hide-mobile">Welcome, <%=(String) request.getSession().getAttribute("staffFirstName")%></span>
                                </a>
                                <ul class="dropdown-menu dropdown-menu-default">
                                    <li>
                                        <a href="extra_profile">
                                            <i class="icon-user"></i> User Settings </a>
                                    </li>
                                    <li>
                                        <a href="http://<%=IP%>:8080/MMS2Server-war/administration/logout">
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
                            <li class="">
                                <form action="APSControllerServlet" method="Post" name="CurrentOff">
                                    <input type="hidden" name="action" value="ViewCurrentCampaignsO" />
                                </form>
                                <a href="javascript:CurrentOff.submit()">View Current Campaigns</a>
                            </li>
                            <li class="">
                                <form action="APSControllerServlet" method="Post" name="ViewDailyD">
                                    <input type="hidden" name="action" value="View Daily Data" />
                                </form>
                                <a href="javascript:ViewDailyD.submit()">View All Daily Data</a>
                            </li>
                          
                        </ul>    
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
                        <h1>List All Campaigns</h1>
                    </div>
                    <!-- END PAGE TITLE -->

                </div>
            </div>
            <!-- END PAGE HEAD -->
            <!-- BEGIN PAGE CONTENT -->
            <div class="page-content">
                <div class="container">
                    <!-- BEGIN PAGE BREADCRUMB -->
                    <!-- END PAGE BREADCRUMB -->
                    <!-- BEGIN PAGE CONTENT INNER -->
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Campaign Name</th>
                                    <th>Objective</th>
                                    <th>Channel</th>
                                    <th>Start Date</th>
                                    <th>End Date</th>
                                    <th>Target</th>
                                    <th>Coupon code</th>
                                    <th>Loyalty Points</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${requestScope.list}" var="campaign">
                                    <tr class="success">
                                <form action="APSControllerServlet" method="POST">
                                    <td>${campaign.campaignName}</td>
                                    <td>${campaign.objective}</td>
                                    <td>${campaign.channel}</td>
                                    <td>${campaign.startDate}</td>
                                    <td>${campaign.endDate}</td>
                                    <td>${campaign.target}</td>
                                    <td>${campaign.coupon}</td>
                                    <td>${campaign.point}</td>
                                    
                                    <input type="hidden" name="campaignId" value="${campaign.campaignId}" />

                                    <script>
                                        function addD() {
                                            var x = confirm("Are you sure you want to add daily data?");
                                            if (x)
                                                return true;
                                            else
                                                return false;
                                        }
                                    </script>
                                    <td><input type="submit" name="action" value="Add Campaign Daily Data" onclick="return addD()"/></td>
                                </form>
                                </tr>
                            </c:forEach>
                        </table>

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
            <!--        <script src="../assets/global/plugins/jquery.min.js" type="text/javascript"></script>-->
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
            <% String referrer = request.getHeader("referer");
                String query = request.getQueryString();
                String timestamp = null;
            %>
            <% if (referrer.matches("http://" + IP + ":8080/MMS2Server-war/administration/login")
                        || referrer.matches("http://" + IP + ":8080/MMS2Server-war/administration/logout")
                        || referrer.matches("http://" + IP + ":8080/MMS2Server-war/administration/adminHome")) {
                    timestamp = "Your last login was on: " + session.getAttribute("Session5").toString();
                    if ("=continue".equals(query)) {
            %>        
            <script language="javascript">
                var ts = '<%= timestamp%>';
                $(document).ready(function () {
                    // show when page load
                    toastr.info('Welcome back!');

                });
            </script>
            <% } else {%>
            <script language="javascript">
                var ts = '<%= timestamp%>';
                $(document).ready(function () {
                    // show when page load
                    toastr.success(ts, 'Login Successful!');

                });
            </script>
            <%}
                }%>


            <!-- END JAVASCRIPTS -->

    </body>
    <!-- END BODY -->
</html>

