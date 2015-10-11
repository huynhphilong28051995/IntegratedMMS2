<%-- 
    Document   : indexTenantPortalEventBooking
    Created on : Sep 26, 2015, 11:24:25 AM
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
        <title>Merlion Leasing System | Event Application</title>
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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/leasingSystem/leasingSystemAssets/jquery-ui-1.11.4.custom/jquery-ui.theme.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/leasingSystem/leasingSystemAssets/jquery-ui-1.11.4.custom/jquery-ui.css">
        <script src="${pageContext.request.contextPath}/leasingSystem/leasingSystemAssets/jquery-ui-1.11.4.custom/external/jquery/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/leasingSystem/leasingSystemAssets/jquery-ui-1.11.4.custom/jquery-ui.js"></script>

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
                                <a href="ApplyLongTerm">Long-term application</a>
                            </li>
                            <li class="active">
                                <a href="ApplyEvent">Event application</a>
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
                        String actionToTake = (String) request.getAttribute("actionToTake");
                        if (actionToTake.equals("ChooseDate")) {
                    %>
                    <div class="container">
                        <form action="DateChosen" method="GET">
                            <label>Event start date</label>
                            <input type="text" id="eventdatepickerStart" name="eventStart"/>
                            <label>Event end date</label>
                            <input type="text" id="eventdatepickerEnd" name="eventEnd"/>
                            <button type="submit" class="btn btn-default">Proceed</button>
                        </form>
                    </div>
                    <script>
                        $(function () {
                            $("#eventdatepickerStart").datepicker({dateFormat: 'yy-mm-dd'});
                            $("#eventdatepickerEnd").datepicker({dateFormat: 'yy-mm-dd'});
                        });
                    </script>
                    <%
                    } else if (actionToTake.equals("ReviewLocation")) {
                        String levelCode = (String) request.getSession().getAttribute("levelCode");
                        LevelEntity level = (LevelEntity) request.getSession().getAttribute("levelInstance");
                        String floorplanBackground = level.getFloorplanBackground();
                        boolean canProceed = false;
                        ArrayList<String> availableList
                                = (ArrayList<String>) request.getSession().getAttribute("availableEventUnitList");
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
                                if (unitLocationCode.contains("EV") && availableList.contains(unitLocationCode)) {
                                    unitList.add((UnitEntity) tempList.get(i));
                                }
                            }
                        }
                        for (int i = 1; i <= unitList.size(); i++) {
                            String locationCode = ((UnitEntity) unitList.get(i - 1)).getLocationCode();
                    %>
                    <div id="<%=locationCode%>" class="NonDragResize" style="height:100px; width: 100px;">
                        <button disabled id = "<%=locationCode%>_button" style="height:100%; width:100%;"><%=locationCode%></button>
                    </div>
                    <script>
                        document.getElementById("<%=locationCode%>_button").style.background = "#fcf898";
                    </script>
                    <%
                        }
                    %>
                    <image id="floorplanBackground" src="${pageContext.request.contextPath}/leasingSystem/leasingSystemAssets/floorplanBackground/<%=floorplanBackground%>.png"/>
                    <script>
                        var positions = <%=positionString%>;
                        $.each(positions, function (id, pos) {
                            $("#" + id).css(pos);
                        });
                    </script>
                    <!--SELECT UNIT TO ADD -->
                    <div id="AddUnitToApplyEventUnitList">
                        <form action="AddUnitToApplyEventUnitList">
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
                    <!--END SELECT UNIT TO ADD -->
                    <!--START PROCEED-->
                    <form action="FillApplicantInformationForEvent">
                        <button id="proceed"  class="btn btn-default" type="submit">Proceed</button>
                    </form>
                    <script>
                        var canProceed = <%=canProceed%>;
                        if (canProceed === false) {
                            document.getElementById("proceed").disabled = true;
                        }
                    </script>
                    <!--END PROCEED-->
                    <!--CHANGE FLOOR-->
                    <div id="changeFloor">
                        <form action="ChangeFloorplanLevelChooseUnitApplyEvent" method="GET">
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

                    <!--BACK TO CHOOSE DATE-->
                    <form action="ApplyEvent">
                        <button class="btn btn-default" type="submit">Choose date again</button>
                    </form>
                    <!--BACK TO CHOOSE DATE-->



                    <%
                        } else {
                            //do nothing. This is the end of this page
                        }
                    %>




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
            String chooseUnitStatus = null;
            String locationCodeList = "Chosen unit(s): ";
            if (request.getAttribute("chooseUnitStatus") != null) {
                chooseUnitStatus = (String) request.getAttribute("chooseUnitStatus");
                ArrayList<String> applyUnitList = (ArrayList<String>) request.getSession().
                        getAttribute("applyUnitList");
            }
            if (request.getSession().getAttribute("applyUnitList") != null) {
                ArrayList<String> applyUnitList = (ArrayList<String>) request.getSession().
                        getAttribute("applyUnitList");
                for (int i = 0; i < applyUnitList.size(); i++) {
                    String locationCode = applyUnitList.get(i);
                    locationCodeList = locationCodeList + locationCode + " ";
                }
            }

            if (chooseUnitStatus != null) {
                chooseUnitStatus = chooseUnitStatus + " | " + locationCodeList;
                if (chooseUnitStatus.contains("Successful!")) {
        %>
        <script language="javascript">
            $(document).ready(function () {
                // show when page load
                toastr.success('<%= chooseUnitStatus%>');
            });
        </script>
        <%
        } else {
        %>
        <script language="javascript">
            $(document).ready(function () {
                // show when page load
                toastr.error('<%=chooseUnitStatus%>');
            });
        </script>
        <%
                }
            }
        %>
        <!-- END JAVASCRIPTS -->
    </body>
    <!-- END BODY -->
</html>

