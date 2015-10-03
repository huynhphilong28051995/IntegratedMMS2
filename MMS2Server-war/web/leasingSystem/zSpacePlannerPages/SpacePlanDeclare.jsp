<%-- 
    Document   : indexSpacePlan
    Created on : Sep 9, 2015, 12:36:02 AM
    Author     : PhiLong
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <!-- BEGIN HEAD -->
    <head>
        <meta charset="utf-8"/>
        <title>Merlion Leasing System | Initialization</title>
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
                        <h1>Floor plan initialization </h1>
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
                    </ul>
                    <!-- END PAGE BREADCRUMB -->
                    <!-- BEGIN PAGE CONTENT INNER -->



                    <%
                        String mallName = (String) request.getSession().getAttribute("mallName");
                        String actionToTake = (String) request.getSession().getAttribute("actionToTake");
                    %>
                    <h1><%=mallName%></h1>
                    <%
                        if (actionToTake.equals("UploadFloorplanBackground")) {
                    %>
                    <form action="SpacePlanUpload" method="post"
                          enctype="multipart/form-data">
                        <input type="file" name="file" size="50"/>
                        <br />
                        <input type="submit" value="Upload File" />
                    </form>
                    <%
                        }
                    %>

                    <%
                        if (actionToTake.equals("DeclareNumOfLevel")) {
                    %>
                    <form action="DeclareNumOfLevel" method="GET">
                        <div class="form-group">
                            <label for="numOfLevel">Number of level :</label>
                            <input type="number" min="1" step="1" required="required" class="form-control"
                                   id="numOfLevel" name="numOfLevel" placeholder="numOfLevel">
                        </div>

                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                    <%
                        }
                    %>

                    <%
                        if (actionToTake.equals("DeclareNumOfUnitPerLevel")) {
                            int numOfLevel = (Integer) request.getSession().getAttribute("numOfLevel");
                            ArrayList<String> levelCodeList = new ArrayList();
                            for (int i = 1; i <= numOfLevel; i++) {
                                levelCodeList.add("LV" + "" + i);
                            }
                    %>
                    <form action="DeclareNumOfUnitPerLevel" method="GET">
                        <%
                            for (int i = 0; i < levelCodeList.size(); i++) {
                                String levelCode = levelCodeList.get(i);
                        %>
                        <label for="">Number of unit on <%=levelCode%>:</label>
                        <input type="number" min="0" step="1" required="required" class="form-control"
                               id="numOfUnitOn<%=levelCode%>" name="numOfUnitOn<%=levelCode%>"
                               placeholder="numOfUnitOn<%=levelCode%>">
                        <br/>
                        <%
                            }
                        %>
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                    <form action="InitializeSpacePlan">
                        <button type="submit"  class="btn btn-default">Back</button>
                    </form>
                    <%
                        }
                    %>

                    <%
                        if (actionToTake.equals("DeclareFloorplanBackground")) {
                    %>
                    <form action="DeclareFloorplanBackground" method="GET">
                        <div class="form-group">
                            <%
                                int numOfLevel = (Integer) request.getSession().getAttribute("numOfLevel");
                                ArrayList<String> levelCodeList = new ArrayList();
                                for (int i = 1; i <= numOfLevel; i++) {
                                    levelCodeList.add("LV" + "" + i);
                                }
                                for (int i = 0; i < levelCodeList.size(); i++) {
                                    String levelCode = levelCodeList.get(i);
                            %>
                            <label for="">Floor plan format of <%=levelCode%>:</label>
                            <input required="required" class="form-control"
                                   id="floorplanBackground<%=levelCode%>" name="floorplanBackground<%=levelCode%>"
                                   placeholder="Floor plan for <%=levelCode%>">
                            <br/>
                            <%
                                }
                            %>
                        </div>
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                    <form action="DeclareNumOfLevel">
                        <button type="submit"  class="btn btn-default">Back</button>
                    </form>
                    <%
                        }
                    %>


                    <%
                        if (actionToTake.equals("FinalConfirmation")) {
                            int numOfLevel = (Integer) request.getSession().getAttribute("numOfLevel");
                            ArrayList<ArrayList<String>> levelNameNumUnitBackgroundList
                                    = (ArrayList<ArrayList<String>>) request.getSession().getAttribute("levelNameNumUnitBackgroundList");
                    %>
                    <h2>Please confirm following information</h2>
                    <h4>Number of levels: <%=numOfLevel%></h4>
                    <h4>Number of units per level: </h4>
                    <%
                        for (int i = 0; i < levelNameNumUnitBackgroundList.size(); i++) {
                            ArrayList<String> levelNameNumUnitBackground = levelNameNumUnitBackgroundList.get(i);
                            String statusString = ("On " + levelNameNumUnitBackground.get(0)
                                    + " has " + levelNameNumUnitBackground.get(1)
                                    + " store unit(s) and has background format " + levelNameNumUnitBackground.get(2));
                    %>
                    <h5><%=statusString%></h5>
                    <%
                        }
                    %>
                    <form action="FinalConfirmation" method="GET">
                        <button type="submit" class="btn btn-default">Confirm</button>
                    </form>
                    <form action="DeclareAgain">
                        <button type="submit"  class="btn btn-default">Declare again</button>
                    </form>
                    <%
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
        <% String referrer = request.getHeader("referer");
            String query = request.getQueryString();
            String timestamp = null;
            if (actionToTake.equals("DeclareNumOfLevel")) {
        %>
        <script language="javascript">
            $(document).ready(function () {
                toastr.info('Please initialize floor plan before entering system');
            });
        </script>
        <%
            }
        %>
        <%
             if (actionToTake.equals("UploadFloorplanBackground")) {
        %>
        <script language="javascript">
            $(document).ready(function () {
                toastr.info('Please upload floor plan background files before initialization');
            });
        </script>
        <%
            }
        %>

        <% if (referrer.matches("http://localhost:8080/MMS2Server-war/administration/login")
                    || referrer.matches("http://localhost:8080/MMS2Server-war/administration/logout")
                    || referrer.matches("http://localhost:8080/MMS2Server-war/administration/adminHome")) {
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
