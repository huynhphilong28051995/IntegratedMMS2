<%-- 
    Document   : FacilityManagerViewOutsourcing
    Created on : Oct 18, 2015, 11:30:33 AM
    Author     : linjiao_Zoe
--%>

<%@page import="mms.facility.entity.OutsourcingEntity"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Merlion Facility Management System | View Outsourcing</title>
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
                                <a href="backToManagerIndexPage">DashBoard</a>
                            </li>                               
                            <li class="active">
                                <a href="listMFacility">View Facility</a>
                            </li>                                   

                            <li class="active">
                                <a href="listMContractor">View Contractor</a>
                            </li>                                   

                            <li class="active">
                                <a href="listMServiceRequest">View Service Request</a>
                            </li>

                            <li class="active">
                                <a href="listOutsourcingRequest">View Outsourcing Request</a>
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
                        <h1>View Individual Outsourcing Request</h1>
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

                    <table>
                        <%
                            OutsourcingEntity outsource = (OutsourcingEntity) request.getAttribute("data");
                            Long Id = outsource.getOutsourcingId();
                            String mall = outsource.getMallName();
                            Long tenantId = outsource.getTenantId();
                            String tenantEmail = outsource.getTenantEmail();
                            String unit = outsource.getUnitNumber();
                            String contractor = outsource.getContractorName();
                            int numStaff = outsource.getNumStaff();
                            String date = outsource.getOutsourcingDate().toString().substring(0, 10);
                            String detail = outsource.getOutsourcingDetail();
                            String startDate = outsource.getoServicingStartDate().toString().substring(0, 10);
                            String endDate = outsource.getoServicingEndDate().toString().substring(0, 10);
                            String link = outsource.getDocFileLink();
                            String status = outsource.getOutsourcingStatus();
                        %>
                        <tr>
                            <td>Outsourcing Id:</td>
                            <td><%=Id%></td>
                        </tr>
                        <tr>
                            <td>Mall Name:</td>
                            <td><%=mall%></td>
                        </tr>
                        <tr>
                            <td>Tenant Id:</td>
                            <td><%=tenantId%></td> 
                        </tr>
                        <tr>
                            <td>Tenant Email:</td>
                            <td><%=tenantEmail%></td> 
                        </tr>
                        <tr>
                            <td>Shop Unit Number:</td>
                            <td><%=unit%></td>
                        </tr>
                        <tr>
                            <td>Contractor Name:</td>
                            <td><%=contractor%></td> 
                        </tr>
                        <tr>
                            <td>Number of Staffs needed:</td>
                            <td><%=numStaff%></td> 
                        </tr>
                        <tr>
                            <td>Request Date:</td>
                            <td><%=date%></td>
                        </tr>
                        <tr>
                            <td>Outsourcing Detail:</td>
                            <td><%=detail%></td>
                        </tr>
                        <tr>
                            <td>Outsourcing Start Date:</td>
                            <td><%=startDate%></td>
                        </tr>
                        <tr>
                            <td>Outsourcing End Date:</td>
                            <td><%=endDate%></td>
                        </tr>
                        <tr>
                            <td>Technical Document:</td>
                            <td><a href="">Download</a></td>                
                        </tr>  
                        <tr>
                            <td>Outsourcing Status:</td>
                            <td><%=status%></td>
                        </tr>            
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
        <!-- END JAVASCRIPTS -->
    </body>
    <!-- END BODY -->
</html>
