<%-- 
    Document   : FacilityOfficerEditFacility
    Created on : Sep 25, 2015, 5:02:15 PM
    Author     : linjiao_Zoe
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="mms.facility.entity.FacilityEntity"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>       
        <title>Merlion Facility Management System | Edit Facility</title>
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
        <!-- BEGIN PERSONAL STYLES -->
        <script src="${pageContext.request.contextPath}/javascript/mainScript.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css">   
        <link rel="stylesheet" href="${pageContext.request.contextPath}/facilitySystem/jquery-ui-1.11.4.custom/jquery-ui.theme.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/facilitySystem/jquery-ui-1.11.4.custom/jquery-ui.css">
        <script src="${pageContext.request.contextPath}/facilitySystem/jquery-ui-1.11.4.custom/external/jquery/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/facilitySystem/jquery-ui-1.11.4.custom/jquery-ui.js"></script>
        <!-- END PERSONAL STYLES -->
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
                        <h1>Edit Facility</h1>
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

                    <form action="changeFacility" method="GET">
                        <table>
                            <%
                                FacilityEntity facility = (FacilityEntity) request.getAttribute("data");
                                Long Id = facility.getFacilityId();
                                String name = facility.getFacilityName();
                                String category = facility.getFacilityCategory();
                                int quantity = facility.getFacilityQuantity();
                                String condition = facility.getFacilityCondition();
                                String location = facility.getFacilityLocation();
                                String purchaseDate = facility.getFacilityPurchaseDate().toString();
                                String expiryDate = facility.getFacilityExpiryDate().toString();
                                double cost = facility.getFacilityCost();
                                request.getSession().setAttribute("facilityId", Id);
                            %>
                            <tr>
                                <td>Facility ID:</td>
                                <td><%=Id%></td>
                            </tr>
                            <tr>
                                <td>Name:</td>
                                <td><input type="text" name="facilityName" value="<%=name%>"/></td>
                            </tr>
                            <tr>
                                <td>Category:</td>
                                <td>
                                    <select name="facilityCategory">
                                        <option value="<%=category%>" selected="selected">No change: <%=category%></option>
                                        <option value="Toilet">Toilet</option>
                                        <option value="Fire Alarm">Fire Alarm</option>
                                        <option value="Central Air Condition">Central Air Condition</option>                          
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>Quantity:</td>
                                <td><input type="number" name="facilityQuantity" value="<%=quantity%>" min="1" max="200"/></td>
                            </tr>
                            <tr>
                                <td>Condition:</td>
                                <td><select name="facilityCondition">
                                        <option value="<%=condition%>" selected="selected">No change: <%=condition%></option>
                                        <option value="Good">Good</option>
                                        <option value="Bad">Bad</option>
                                        <option value="Pending Service">Pending Service</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>Location:</td>
                                <td><input type="text" name="facilityLocation" value="<%=location%>"/></td>
                            </tr>
                            <tr>
                                <td>Purchase Date:</td>
                                <td><input type="text" id="datepickerPurchaseDate" name="facilityPurchaseDate" value=<%=purchaseDate%>/>
                                    <script>
                                        $(function () {
                                            $("#datepickerPurchaseDate").datepicker({dateFormat: 'yy-mm-dd'});
                                        });
                                    </script>
                                </td>
                            </tr>
                            <tr>
                                <td>Expiry Date:</td>
                                <td><input type="text" id="datepickerExpiryDate" name="facilityExpiryDate" value=<%=expiryDate%>/>
                                    <script>
                                        $(function () {
                                            $("#datepickerExpiryDate").datepicker({dateFormat: 'yy-mm-dd'});
                                        });
                                    </script>
                                </td>
                            </tr>
                            <tr>
                                <td>Cost:</td>
                                <td><input type="number" step="any" name="facilityCost" value="<%=cost%>"/></td>
                            </tr>
                        </table>
                        <br/>
                        <input type="submit" value="Submit Changes"/>&nbsp;&nbsp;
                        <input type="reset" value="Reset"/>                    
                    </form>

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
        <!-- END JAVASCRIPTS -->
    </body>
    <!-- END BODY -->
</html>
