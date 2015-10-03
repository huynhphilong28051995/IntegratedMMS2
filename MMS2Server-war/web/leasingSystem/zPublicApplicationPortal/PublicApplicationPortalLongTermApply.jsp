<%-- 
    Document   : indexTenantPortalLongTermApply
    Created on : Sep 26, 2015, 11:26:20 AM
    Author     : PhiLong
--%>

<%@page import="java.util.Vector"%>
<%@page import="EntityPackage.UnitEntity"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="EntityPackage.LevelEntity"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>Merlion Leasing System | Long-term Application</title>
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
<!--        <link rel="stylesheet" href="${pageContext.request.contextPath}/leasingSystem/leasingSystemAssets/css/bootstrap.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/leasingSystem/leasingSystemAssets/jquery-ui-1.11.4.custom/jquery-ui.css">-->
        <script src="${pageContext.request.contextPath}/leasingSystem/leasingSystemAssets/jquery-ui-1.11.4.custom/external/jquery/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/leasingSystem/leasingSystemAssets/jquery-ui-1.11.4.custom/jquery-ui.js"></script>
        <script src="${pageContext.request.contextPath}/leasingSystem/leasingSystemAssets/javascript/mainScript.js"></script> 
        <!--PERSONAL STYLE-->
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
                            <span class="separator"></span>
                            </li>
                            <!-- BEGIN USER LOGIN DROPDOWN -->
                            <li class="dropdown dropdown-user dropdown-dark">
                                <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">

                                    <span class="username username-hide-mobile">Welcome, guest</span>
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
                                <a href="ApplyLongTerm">Long-term application</a>
                            </li>
                            <li class="">
                                <a href="ApplyEvent">Event application</a>
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
                        <h1>Leasing Public Portal Main </h1>
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
            boolean canProceed = false;
            if (request.getSession().getAttribute("applyUnitList") != null) {
                ArrayList<String> unitListToOpenBidding
                        = (ArrayList<String>) request.getSession().getAttribute("applyUnitList");
                if (!unitListToOpenBidding.isEmpty()) {
                    canProceed = true;
                }
            }
            ArrayList<String> positionList = level.getUnitPositionList();
            String positionString = "";
            for (int i = 0; i < positionList.size(); i++) {
                positionString = positionString + "" + positionList.get(i);
            }
            //Seperate arraList into pushcart and non-pushcart
            List tempList = new ArrayList(level.getUnits());
            ArrayList<UnitEntity> unitList = new ArrayList();
            for (int i = 0; i < tempList.size(); i++) {
                UnitEntity unit = (UnitEntity) tempList.get(i);
                String unitLocationCode = unit.getLocationCode();
                boolean openPublic = unit.isOpenForPublicBidding();
                if (openPublic) {
                    if (!unitLocationCode.contains("EV")) {
                        unitList.add((UnitEntity) tempList.get(i));
                    }
                }
            }
            ArrayList<UnitEntity> listOfStoreUnits = new ArrayList();
            ArrayList<UnitEntity> listOfPushCartUnits = new ArrayList();
            ArrayList<UnitEntity> listOfKioskUnits = new ArrayList();
            for (int i = 0; i < unitList.size(); i++) {
                UnitEntity unit = (UnitEntity) unitList.get(i);
                String unitLocationCode = unit.getLocationCode();
                boolean show = unit.isShow();
                if (show) {
                    if (unitLocationCode.contains("PC")) {
                        listOfPushCartUnits.add((UnitEntity) unitList.get(i));
                    }
                    if (unitLocationCode.contains("KS")) {
                        listOfKioskUnits.add((UnitEntity) unitList.get(i));
                    }
                    if (unitLocationCode.contains("ST")) {
                        listOfStoreUnits.add((UnitEntity) unitList.get(i));
                    }
                }
            }
        %>

        <image id="floorplanBackground" src="${pageContext.request.contextPath}/leasingSystem/leasingSystemAssets/floorplanBackground/<%=floorplanBackground%>.png" 
               stype="width:1000px; height: 1000px;"/>
        <div>
            <%
                for (int i = 0; i < listOfStoreUnits.size(); i++) {
                    String locationCode = ((UnitEntity) listOfStoreUnits.get(i)).getLocationCode();
            %>
            <div id="<%=locationCode%>" class="NonDragResize">
                <button disabled id = "<%=locationCode%>_button" style="height:100%; width:100%;"><%=locationCode%></button>
            </div>
            <%
                }
            %>
        </div>
        <div>
            <%
                for (int i = 1; i <= listOfPushCartUnits.size(); i++) {
                    String locationCode = ((UnitEntity) listOfPushCartUnits.get(i - 1)).getLocationCode();
            %>
            <div id="<%=locationCode%>" class="NonDragResize" style="height:30px; width: 70px;">
                <button disabled id = "<%=locationCode%>_button" style="height:100%; width:100%;"><%=locationCode%></button>
            </div>
            <%
                }
            %>
        </div>
        <div>
            <%
                for (int i = 1; i <= listOfKioskUnits.size(); i++) {
                    String locationCode = ((UnitEntity) listOfKioskUnits.get(i - 1)).getLocationCode();
            %>
            <div id="<%=locationCode%>" class="NonDragResize" style="height:30px; width: 70px;">
                <button disabled id = "<%=locationCode%>_button" style="height:100%; width:100%;"><%=locationCode%></button>
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
        <!--SELECT UNIT TO ADD -->
        <div id="AddUnitToApplyUnitList">
            <form action="AddUnitToApplyUnitList">
                <div class="form-group">
                    <label class="test">Unit location :</label>
                    <select required="required" name="locationCode" class="test">
                        <%
                            for (int i = 0; i < unitList.size(); i++) {
                                String locationCode = unitList.get(i).getLocationCode();

                        %>
                        <option value="<%=locationCode%>"><%=locationCode%></option>
                        <%
                            }
                        %>
                    </select>
                </div>
                <br/><br/><br/><button class="btn btn-default" type="submit">Add unit</button>
            </form>
        </div>
        <form action="FillApplicantInformationForLongTerm">
            <button id="proceed"  class="btn btn-default" type="submit">Proceed</button>
        </form>
        <script>
            var canProceed = <%=canProceed%>;
            if (canProceed === false) {
                document.getElementById("proceed").disabled = true;
            }
        </script>
        <!--SELECT UNIT TO ADD TENANT-->
        <!--COLOR BUTTONS-->
        <%
            Vector unitColorVector = (Vector) request.getSession().getAttribute("unitColorVector");
            if (!unitColorVector.isEmpty()) {
                ArrayList<String> arrayLocationCode = (ArrayList) unitColorVector.get(0);
                ArrayList<String> arrayColor = (ArrayList) unitColorVector.get(1);
                for (int i = 0; i < arrayColor.size(); i++) {
                    String locationCode = arrayLocationCode.get(i);
                    String color = arrayColor.get(i);

        %> 
        <script>
            document.getElementById("<%=locationCode%>_button").style.background = "<%=color%>";
        </script>
        <%
                }
            }
        %>
        <!--COLOR BUTTONS-->
        <!--CHANGE FLOOR-->
        <div id="changeFloor">
            <form action="ChangeFloorplanLevelChooseUnitApplyLongTerm" method="GET">
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
                    <button type="submit" class="btn btn-default">Go</button>
                </div>
            </form>
        </div>
        <!--CHANGE FLOOR-->

        <!--BACK TO MAIN-->
        <form action="BackToPublicPortalMain"><button class="btn btn-default" type="submit">BACK</button></form>
        <!--BACK TO MAIN-->

        <%
            if (request.getSession().getAttribute("errorMessage") != null) {
                String errorMessage = (String) request.getSession().getAttribute("errorMessage");
                ArrayList<String> applyUnitList = (ArrayList<String>) request.getSession().
                        getAttribute("applyUnitList");
        %>
        <h3><%=errorMessage%></h3><h3>Chosen unit(s)</h3>
        <%
            }
        %>
        <%
            if (request.getSession().getAttribute("applyUnitList") != null) {
                ArrayList<String> applyUnitList = (ArrayList<String>) request.getSession().
                        getAttribute("applyUnitList");
                for (int i = 0; i < applyUnitList.size(); i++) {
                    String locationCode = applyUnitList.get(i);
        %>
        <h4><%=locationCode%></h4>
        <%
                }
            }
        %>
    </body>
</html>
