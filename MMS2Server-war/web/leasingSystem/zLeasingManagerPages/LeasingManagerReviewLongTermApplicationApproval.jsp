<%-- 
    Document   : LeasingManagerReviewLongTermApplicationApproval
    Created on : Sep 27, 2015, 9:32:23 AM
    Author     : PhiLong
--%>

<%@page import="mms2.leasing.entity.LongTermApplicationEntity"%>
<%@page import="java.util.ArrayList"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>Merlion Leasing System | Floor Plan Review</title>
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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/leasingSystem/leasingSystemAssets/css/main.css" type="text/css">
         <!-- <link rel="stylesheet" href="${pageContext.request.contextPath}/leasingSystem/leasingSystemAssets/css/bootstrap.css" type="text/css">
         <link rel="stylesheet" href="${pageContext.request.contextPath}/leasingSystem/leasingSystemAssets/jquery-ui-1.11.4.custom/jquery-ui.css">-->
        <script src="${pageContext.request.contextPath}/leasingSystem/leasingSystemAssets/jquery-ui-1.11.4.custom/external/jquery/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/leasingSystem/leasingSystemAssets/jquery-ui-1.11.4.custom/jquery-ui.js"></script>
        <script src="${pageContext.request.contextPath}/leasingSystem/leasingSystemAssets/javascript/mainScript.js"></script> 
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

                                    <span class="username username-hide-mobile">Welcome, <%= (String) request.getSession().getAttribute("staffFirstName")%></span>
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
                                <a href="ViewAllRequests">View requests</a>
                            </li>
                            <li class="menu-dropdown classic-menu-dropdown">
                                <a data-hover="megamenu-dropdown" data-close-others="true" data-toggle="dropdown" href="javascript:;">
                                    #### <i class="fa fa-angle-down"></i>
                                </a>
                                <ul class="dropdown-menu pull-left">
                                    <li class="">
                                        <a href="####">
                                            #### </a>
                                    </li>
                                    <li class="">
                                        <a href="####">
                                            ####</a>
                                    </li>
                                    <li class="">
                                        <a href="####">
                                            #### </a>
                                    </li>
                                    <li class="">
                                        <a href="####">
                                            #### </a>
                                    </li>
                                </ul>
                            </li>
                            <li class="">
                                <a href="####">####</a>
                            </li>
                            <li class="">
                                <a href="####">####</a>
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
                        <h1>Application Review </h1>
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
                    

                    <%  
                        LongTermApplicationEntity longTermApplication = (LongTermApplicationEntity) request.getAttribute("longTermApplication");
                        String applicantName = longTermApplication.getApplicantName();
                        String applicantBusinessType = longTermApplication.getApplicantBusinessType();
                        ArrayList<String> applicantDescriptionList = longTermApplication.getApplicantDescription();
                        String applicantDescriptionString = "";
                        for (int i = 0; i < applicantDescriptionList.size(); i++) {
                            applicantDescriptionString = applicantDescriptionString + ""
                                    + applicantDescriptionList.get(i);
                        }
                        applicantDescriptionString = applicantDescriptionString.replaceAll("\n","<br>");
                        String applicantAddress = longTermApplication.getApplicantAddress();
                        String applicantEmail = longTermApplication.getApplicantEmail();
                        String applicantTel = longTermApplication.getApplicantTel();
                        ArrayList<String> applicantUnitList = longTermApplication.getApplyUnitList();
                        String applicantUnitString = "";
                        for (int i = 0; i < applicantUnitList.size(); i++) {
                            applicantUnitString = applicantUnitString + "  "
                                    + applicantUnitList.get(i);
                        }
                        double applicantBidRate = longTermApplication.getApplicantBidRate();

                        ArrayList<String> contractDescriptionList = longTermApplication.getContractDescription();
                        String contractDescriptionString = "";
                        for (int i = 0; i < contractDescriptionList.size(); i++) {
                            contractDescriptionString = contractDescriptionString
                                    + contractDescriptionList.get(i);
                        }
                        String contractStart = longTermApplication.getContractStart().toString();
                        String contractEnd = longTermApplication.getContractEnd().toString();
                        String contractDeposit = String.valueOf(longTermApplication.getContractDeposit());

                    %>
                    <table class="table table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
                        <tr>
                            <th>Applicant name:</th>
                            <td><%=applicantName%></td>
                        </tr>
                        <tr>
                            <th>Applicant business type:</th>
                            <td><%=applicantBusinessType%></td>
                        </tr>
                        <tr>
                            <th>Applicant description</th>
                            <td><%=applicantDescriptionString%></td>
                        </tr>
                        <tr>
                            <th>Applicant address</th>
                            <td><%=applicantAddress%></td>
                        </tr>
                        <tr>
                            <th>Applicant email</th>
                            <td><%=applicantEmail%></td>
                        </tr>
                        <tr>
                            <th>Applicant telephone</th>
                            <td><%=applicantTel%></td>
                        </tr>
                        <tr>
                            <th>Apply unit(s)</th>
                            <td><%=applicantUnitString%></td>
                        </tr>
                        <tr>
                            <th>Apply bid rate</th>
                            <td><%=applicantBidRate%></td>
                        </tr>
                        <tr>
                            <th>Contract start date</th>
                            <td><%=contractStart%></td>
                        </tr>
                        <tr>
                            <th>Contract end date</th>
                            <td><%=contractEnd%></td>
                        </tr>
                        <tr>
                            <th>Contract deposit</th>
                            <td><%=contractDeposit%></td>
                        </tr>
                        <tr>
                            <th>Contract description</th>
                            <td><%=contractDescriptionString%></td>
                        </tr>
                    </table>
                    <form action="BackToViewAllLeasingRequests">
                        <button type="submit" class="btn btn-default">BACK</button>
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
        <script>
            jQuery(document).ready(function () {
                Custom.init(); // init custom core components
                Layout.init(); // init current layout
                UIIdleTimeout.init(); // init Idle Timeout
                UIToastr.init(); // init Toastr Alert
            });
        </script>
       

        <!-- END JAVASCRIPTS -->

    </body>
    <!-- END BODY -->
</html>

</html>
