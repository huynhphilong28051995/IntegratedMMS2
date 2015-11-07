<%-- 
    Document   : FacilityOfficerListFacility
    Created on : Sep 25, 2015, 4:59:09 PM
    Author     : linjiao_Zoe
--%>
<%@page import="Entity.FacilityEntity"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Merlion Facility Management System | List Facility</title>
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
                            <!-- BEGIN USER LOGIN DROPDOWN -->
                            <li class="dropdown dropdown-user dropdown-dark">
                                <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                                    <span class="username username-hide-mobile">Welcome, <%= (String) request.getSession().getAttribute("staffFirstName")%></span>
                                </a>
                                <ul class="dropdown-menu dropdown-menu-default">
                                    <li>
                                        <a href="extra_profile"><i class="icon-user"></i> User Settings </a>
                                    </li>
                                    <li>
                                        <a href=""><i class="icon-key"></i> Log Out </a>
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
                                <a href="backToOfficerIndexPage">DashBoard</a>
                            </li>
                            <li class="menu-dropdown classic-menu-dropdown">
                                <a data-hover="megamenu-dropdown" data-close-others="true" data-toggle="dropdown" href="javascript:;">
                                    Facility<i class="fa fa-angle-down"></i>
                                </a>
                                <ul class="dropdown-menu pull-left">
                                    <li class="active">
                                        <a href="addFacility">Add Facility</a>
                                    </li>                                 
                                    <li class="active">
                                        <a href="listFacility">View Facility</a>
                                    </li>                                   
                                </ul>
                            </li>

                            <li class="menu-dropdown classic-menu-dropdown">
                                <a data-hover="megamenu-dropdown" data-close-others="true" data-toggle="dropdown" href="javascript:;">
                                    Contractor<i class="fa fa-angle-down"></i>
                                </a>
                                <ul class="dropdown-menu pull-left">
                                    <li class="active">
                                        <a href="addContractor">Add Contractor</a>
                                    </li>                                 
                                    <li class="active">
                                        <a href="listContractor">View Contractor</a>
                                    </li>                                   
                                </ul>
                            </li>

                            <li class="menu-dropdown classic-menu-dropdown">
                                <a data-hover="megamenu-dropdown" data-close-others="true" data-toggle="dropdown" href="javascript:;">
                                    Service Request<i class="fa fa-angle-down"></i>
                                </a>
                                <ul class="dropdown-menu pull-left">
                                    <li class="active">
                                        <a href="addServiceRequest">Add Service Request</a>
                                    </li>                                 
                                    <li class="active">
                                        <a href="listServiceRequest">View Service Request</a>
                                    </li>                                   
                                </ul>
                            </li>

                            <li class="active">
                                <a href="listFeedback">View Feedbacks</a>
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
                        <h1>List Facilities</h1>
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

                    <table border="3">
                        <tr>
                            <th> Id </th>
                            <th> Name </th>
                            <th> Category </th>
                            <th> Quantity </th>
                            <th> Condition </th>
                            <th> Location </th>
                            <th> Purchase Date </th>
                            <th> Expiry Date </th>
                            <th> Cost </th>
                            <th> Update Options </th>
                            <th> Asset Management </th>
                        </tr>
                        <%
                            ArrayList facilities = (ArrayList) request.getAttribute("data");
                            for (Object o : facilities) {
                                FacilityEntity facility = (FacilityEntity) o;
                                Long Id = facility.getFacilityId();
                                String name = facility.getFacilityName();
                                String category = facility.getFacilityCategory();
                                int quantity = facility.getFacilityQuantity();
                                String condition = facility.getFacilityCondition();
                                String location = facility.getFacilityLocation();
                                String purchaseDate = facility.getFacilityPurchaseDate().toString().substring(0, 10);
                                String expiryDate = facility.getFacilityExpiryDate().toString().substring(0, 10);
                                double cost = facility.getFacilityCost();
                        %>
                        <tr>
                            <td><%=Id%></td>
                            <td><%=name%></td>
                            <td><%=category%></td>            
                            <td><%=quantity%></td>           
                            <td><%=condition%></td>            
                            <td><%=location%></td>          
                            <td><%=purchaseDate%></td>           
                            <td><%=expiryDate%></td>
                            <td><%=cost%></td>
                            <td>
                                <a onclick="return confirm('Are you sure you want to delete this facility?')" 
                                   href="deleteFacility?facilityId=<%=Id%>">&nbsp;&nbsp;&nbsp;Delete&nbsp;</a> 
                                <a href="editFacility?facilityId=<%=Id%>">&nbsp;&nbsp;&nbsp;Edit&nbsp;</a>
                            </td>
                            <td>
                                <a href="addAsset?facilityId=<%=Id%>">&nbsp;&nbsp;&nbsp;Add New Asset</a>
                                <a href="listAsset?facilityId=<%=Id%>">&nbsp;&nbsp;&nbsp;List Assets&nbsp;&nbsp;&nbsp;</a>
                            </td>
                        </tr>           
                        <%
                            }
                        %>                     
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
            if (request.getAttribute("editFacilityStatus") != null) {
                String editFacilityStatus = (String) request.getAttribute("editFacilityStatus");
        %>
        <script language="javascript">
            $(document).ready(function () {
                // show when page load
                toastr.success('<%=editFacilityStatus%>');
            });
        </script>
        <%
            }
        %>

        <%
            if (request.getAttribute("deleteFacilityStatus") != null) {
                String deleteFacilityStatus = (String) request.getAttribute("deleteFacilityStatus");
        %>
        <script language="javascript">
            $(document).ready(function () {
                // show when page load
                toastr.success('<%=deleteFacilityStatus%>');
            });
        </script>
        <%
            }
        %>


        <%
            if (request.getAttribute("addAssetStatus") != null) {
                String addAssetStatus = (String) request.getAttribute("addAssetStatus");
        %>
        <script language="javascript">
            $(document).ready(function () {
                // show when page load
                toastr.success('<%= addAssetStatus%>');
            });
        </script>
        <%
            }
        %>

        <%
            if (request.getAttribute("alertAssetStatus") != null) {
                String alertAssetStatus = (String) request.getAttribute("alertAssetStatus");
        %>
        <script language="javascript">
            $(document).ready(function () {
                // show when page load
                toastr.success('<%=alertAssetStatus%>');
            });
        </script>
        <%
            }
        %>

        <%
            if (request.getAttribute("editAssetStatus") != null) {
                String editAssetStatus = (String) request.getAttribute("editAssetStatus");
        %>
        <script language="javascript">
            $(document).ready(function () {
                // show when page load
                toastr.success('<%=editAssetStatus%>');
            });
        </script>
        <%
            }
        %>

        <%
            if (request.getAttribute("deleteAssetStatus") != null) {
                String deleteAssetStatus = (String) request.getAttribute("deleteAssetStatus");
        %>
        <script language="javascript">
            $(document).ready(function () {
                // show when page load
                toastr.success('<%=deleteAssetStatus%>');
            });
        </script>
        <%
            }
        %>
        <!-- END JAVASCRIPTS -->
    </body>
    <!-- END BODY -->
</html>
