<%-- 
    Document   : LeasingOfficerViewAllRentRequest
    Created on : Nov 11, 2015, 12:44:36 AM
    Author     : PhiLong
--%>

<%@page import="mms.tenant.entity.RentRenewalRequestEntity"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>Merlion Leasing System | Rent Request</title>
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
         <!--<link rel="stylesheet" href="${pageContext.request.contextPath}/leasingSystem/leasingSystemAssets/css/bootstrap.css" type="text/css">-->
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css" type="text/css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.9/js/jquery.dataTables.min.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/leasingSystem/leasingSystemAssets/css/main.css" type="text/css">
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
                            <!-- BEGIN USER LOGIN DROPDOWN -->
                            <li class="dropdown dropdown-user dropdown-dark">
                                <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">

                                    <span class="username username-hide-mobile">Welcome, <%= (String) request.getSession().getAttribute("staffFirstName")%></span>
                                </a>
                                <ul class="dropdown-menu dropdown-menu-default">
                                    <li>
                                        <a href="extra_profile">
                                            <i class="icon-user"></i> User Settings </a>
                                    </li>
                                    <li>
                                        <a href="http://<%=IP%>:8080/MMS2Server-war/employee/logout">
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
                                <a href="DeclareZone">Zone declare</a>
                            </li>
                            <li class="">
                                <a href="ChooseUnitForPublicBidding">Open public bidding</a>
                            </li>
                            <li class="menu-dropdown classic-menu-dropdown">
                                <a data-hover="megamenu-dropdown" data-close-others="true" data-toggle="dropdown" href="javascript:;">
                                    Tenant<i class="fa fa-angle-down"></i>
                                </a>
                                <ul class="dropdown-menu pull-left">
                                    <li class="">
                                        <a href="ViewAllTenants">View current tenants</a>
                                    </li>
                                    <li class="">
                                        <a href="ViewAllPublicLongTermApplication">View application</a>
                                    </li>
                                    <li class="">
                                        <a href="ViewPendingTenant">View pending tenants</a>
                                    </li>
                                    <li class="">
                                        <a href="ViewExpiringTenant">View expiring tenants</a>
                                    </li>
                                </ul>
                            </li>
                            <li class="menu-dropdown classic-menu-dropdown">
                                <a data-hover="megamenu-dropdown" data-close-others="true" data-toggle="dropdown" href="javascript:;">
                                    Event<i class="fa fa-angle-down"></i>
                                </a>
                                <ul class="dropdown-menu pull-left">
                                    <li class="">
                                        <a href="ViewAllEvent">View all event</a>
                                    </li>
                                    <li class="">
                                        <a href="ViewEventApplication">View application</a>
                                    </li>
                                </ul>
                            </li>
                            <li class="menu-dropdown classic-menu-dropdown">
                                <a data-hover="megamenu-dropdown" data-close-others="true" data-toggle="dropdown" href="javascript:;">
                                    Request<i class="fa fa-angle-down"></i>
                                </a>
                                <ul class="dropdown-menu pull-left">
                                    <li class="active">
                                        <a href="ViewTenantRequest">Tenant Request</a>
                                    </li>
                                    <li class="">
                                        <a href="CheckLeasingOfficerRequestStatus">My Request</a>
                                    </li>
                                </ul>
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
                        <h1>All rent requests </h1>
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


                    <form>
                        <table id="leasingRequestTable" class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Tenant ID</th>
                                    <th>Tenant Name</th>
                                    <th>Unit</th>
                                    <th>Description</th>
                                    <th>Status</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    ArrayList<RentRenewalRequestEntity> requestList
                                            = (ArrayList<RentRenewalRequestEntity>) request.getAttribute("rentReqList");
                                    for (int i = 0; i < requestList.size(); i++) {
                                        RentRenewalRequestEntity req = requestList.get(i);
                                        Long requestID = req.getId();
                                        Long tenantID = req.getTenantID();
                                        String tenantName = req.getName();
                                        String unitCode = req.getUnitNumber();
                                        String status = req.getStatus();
                                        String description = req.getDescription();
                                %>
                                <tr>
                                    <td><%=requestID%></td>
                                    <td><%=tenantID%></td>
                                    <td><%=tenantName%></td>
                                    <td><%=unitCode%></td>
                                    <td><%=description%></td>
                                    <td><%=status%></td>
                                    <td>
                                        <a onclick="return confirm('Proceed accepting this request?')"
                                           href="AcceptRentRequest?rentReqId=<%=requestID%>">
                                            ACCEPT</a>
                                        <a onclick="return confirm('Proceed rejecting this request?')"
                                           href="RejectRentRequest?rentReqId=<%=requestID%>">
                                            REJECT</a>
                                    </td>
                                </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>
                    </form>
                    <script>
                        $(document).ready(function () {
                            $('#leasingRequestTable').DataTable({
                                "order": [[3, "desc"]]
                            });
                        });
                    </script>   

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
        <%
            String deleteRequestStatus = null;
            if (request.getAttribute("deleteRequestStatus") != null) {
                deleteRequestStatus = (String) request.getAttribute("deleteRequestStatus");
        %>
        <script language="javascript">
            $(document).ready(function () {
                // show when page load
                toastr.success('<%=deleteRequestStatus%>');
            });
        </script>
        <%
            }
        %>
        <!-- END JAVASCRIPTS -->
    </body>
    <!-- END BODY -->
</html>







