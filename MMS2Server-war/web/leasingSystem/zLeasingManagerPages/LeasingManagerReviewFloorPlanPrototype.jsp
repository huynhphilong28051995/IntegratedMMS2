<%-- 
    Document   : indexReviewSpacePlanPrototype
    Created on : Sep 24, 2015, 3:48:15 PM
    Author     : PhiLong
--%>

<%@page import="mms2.leasing.entity.LevelEntity"%>
<%@page import="mms2.leasing.entity.UnitEntity"%>
<%@page import="java.util.List"%>
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
                        <h1>Floor plan prototype </h1>
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
                        String levelCode = (String) request.getSession().getAttribute("levelCode");
                        LevelEntity level = (LevelEntity) request.getSession().getAttribute("levelInstance");
                        String floorplanBackground = level.getFloorplanBackground();
                        int numOfUnit = level.getUnits().size();

                        ArrayList<String> positionList = (ArrayList<String>) level.getUnitPositionListPrototype();
                        String positionString = "";
                        for (int i = 0; i < positionList.size(); i++) {
                            positionString = positionString + "" + positionList.get(i);
                        }
                        //Seperate arraList into pushcart and non-pushcart
                        List unitList = new ArrayList(level.getUnits());
                        //            List unitList  = new ArrayList();
                        //             for (int i = 0; i < allUnitList.size(); i++) {
                        //                UnitEntity unit =(UnitEntity) allUnitList.get(i);
                        //                if(!unit.isDeleteProposed())
                        //                    unitList.add(unit);
                        //            }
                        ArrayList<UnitEntity> listOfStoreUnits = new ArrayList();
                        ArrayList<UnitEntity> listOfPushCartUnits = new ArrayList();
                        ArrayList<UnitEntity> listOfKioskUnits = new ArrayList();
                        ArrayList<UnitEntity> listOfEventUnits = new ArrayList();
                        for (int i = 0; i < unitList.size(); i++) {
                            UnitEntity unit = (UnitEntity) unitList.get(i);
                            String unitLocationCode = unit.getLocationCode();
                            boolean show = unit.isShow();
                            if (unitLocationCode.contains("PC")) {
                                listOfPushCartUnits.add((UnitEntity) unitList.get(i));
                            }
                            if (unitLocationCode.contains("EV")) {
                                listOfEventUnits.add((UnitEntity) unitList.get(i));
                            }
                            if (unitLocationCode.contains("KS")) {
                                listOfKioskUnits.add((UnitEntity) unitList.get(i));
                            }
                            if (unitLocationCode.contains("ST")) {
                                listOfStoreUnits.add((UnitEntity) unitList.get(i));
                            }
                        }

                    %>

                    <image id="floorplanBackground" src="${pageContext.request.contextPath}/leasingSystem/leasingSystemAssets/floorplanBackground/<%=floorplanBackground%>.png" />
                    <div>
                        <%
                            for (int i = 0; i < listOfStoreUnits.size(); i++) {
                                UnitEntity unit = (UnitEntity) listOfStoreUnits.get(i);
                                String locationCode = unit.getLocationCode();
                                String showName = locationCode;
                                if (unit.isDeleteProposed()) {
                                    showName = "DELETE";
                                }
                        %>
                        <div id="<%=locationCode%>" class="NonDragResize">
                            <button disabled id = "<%=locationCode%>_button" style="height:100%; width:100%;"><%=showName%></button>
                        </div>
                        <%
                            }
                        %>
                    </div>
                    <div>
                        <%
                            for (int i = 0; i < listOfPushCartUnits.size(); i++) {
                                UnitEntity unit = (UnitEntity) listOfPushCartUnits.get(i);
                                String locationCode = unit.getLocationCode();
                                String showName = locationCode;
                                if (unit.isDeleteProposed()) {
                                    showName = "DELETE";
                                }
                        %>
                        <div id="<%=locationCode%>" class="NonDragResize" style="height:30px; width: 70px;">
                            <button disabled id = "<%=locationCode%>_button" style="height:100%; width:100%;"><%=showName%></button>
                        </div>
                        <%
                            }
                        %>
                    </div>
                    <div>
                        <%
                            for (int i = 0; i < listOfKioskUnits.size(); i++) {
                                UnitEntity unit = (UnitEntity) listOfKioskUnits.get(i);
                                String locationCode = unit.getLocationCode();
                                String showName = locationCode;
                                if (unit.isDeleteProposed()) {
                                    showName = "DELETE";
                                }
                        %>
                        <div id="<%=locationCode%>" class="NonDragResize" style="height:30px; width: 70px;">
                            <button  disabled id = "<%=locationCode%>_button" style="height:100%; width:100%;"><%=showName%></button>
                        </div>
                        <%
                            }
                        %>
                    </div>
                    <div>
                        <%
                            for (int i = 0; i < listOfEventUnits.size(); i++) {
                                UnitEntity unit = (UnitEntity) listOfEventUnits.get(i);
                                String locationCode = unit.getLocationCode();
                                String showName = locationCode;
                                if (unit.isDeleteProposed()) {
                                    showName = "DELETE";
                                }
                        %>
                        <div id="<%=locationCode%>" class="NonDragResize" style="height:100px; width: 100px;">
                            <button disabled id = "<%=locationCode%>_button" style="height:100%; width:100%;"><%=showName%></button>
                        </div>
                        <%
                            }
                        %>
                    </div>
                    <script>
                        var positions = <%=positionString%>;
                        $.each(positions, function (id, pos) {
                            $("#" + id).css(pos);
                        });
                    </script>
                    <!--CHANGE FLOOR-->
                    <div class="changeFloor">
                        <form action="ChangeFloorplanLevelReviewFloorPlanPrototype" method="GET">
                            <div class="form_group">
                                <label for="levelCode">Change floorplan view</label>
                                <select name="levelCode">
                                    <%
                                        for (int i = 1; i <= (Integer) request.getSession().getAttribute("numOfLevel"); i++) {
                                    %>
                                    <option value="LV<%=i%>">LV<%=i%></option>
                                    <%
                                        }
                                    %>
                                </select>
                                <button type="submit" class="btn btn-default" >Go</button>
                            </div>
                        </form>
                    </div>
                    <!--CHANGE FLOOR-->
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
