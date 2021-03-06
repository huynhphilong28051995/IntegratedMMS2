<%-- 
    Document   : FacilityOfficerAddAsset
    Created on : Sep 24, 2015, 8:32:51 PM
    Author     : linjiao_Zoe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Merlion Facility Management System | Add Contractor</title>
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
        <link rel="stylesheet" type="text/css" href="../assets/global/plugins/select2/select2.css"/>
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
                                        <a href="../employee/logout"><i class="icon-key"></i> Log Out </a>
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
                        <h1>Add Asset</h1>
                    </div>
                    <!-- END PAGE TITLE -->
                </div>
            </div>
            <!-- END PAGE HEAD -->
            <!-- BEGIN PAGE CONTENT -->            
            <div class="page-content">
                <div class="container">
                    <!-- BEGIN PAGE CONTENT INNER -->

                    <!-- BEGIN TABLE PORTLET-->
                    <div class="portlet box green">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="fa fa-cogs font-white-sharp"></i>
                                <span class="caption-subject font-white-sharp ">Add New Asset</span>
                            </div>
                            <div class="tools">
                                <a href="javascript:;" class="collapse">
                                </a>
                                <a href="javascript:;" class="remove">
                                </a>
                            </div>
                        </div>

                        <div class="portlet-body form">
                            <!-- BEGIN FORM-->
                            <form action="saveAsset" method="GET" class="form-horizontal form-bordered form-row-stripped">
                                <div class="form-group">
                                    <div class="form-body">
                                        <div class="form-group">
                                            <label class="control-label col-md-3">Name<span class="required">* </span> </label>
                                            <div class="col-md-4">
                                                <div class="input-icon right">
                                                    <i class="fa"></i>
                                                    <input type="text" name="assetName" placeholder="Specify new asset name"  required="required" style="width: 300px;" class="form-control"/>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="control-label col-md-3">Quantity<span class="required">* </span> </label>
                                            <div class="col-md-4">
                                                <div class="input-icon right">
                                                    <i class="fa"></i>
                                                    <input type="number" name="assetQuantity" required="required" min="1" max="200" placeholder="Specify new facility quantity" style="width: 250px;" class="form-control"/>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="form-group">
                                            <label class="control-label col-md-3">Cost<span class="required">* </span> </label>
                                            <div class="col-md-4">
                                                <div class="input-icon right">
                                                    <i class="fa"></i>
                                                    <input type="number" name="assetCost" step="any" required="required" min="0" max="2000000" placeholder="Specify new facility cost" style="width: 230px;" class="form-control"/>
                                                </div>
                                            </div>
                                        </div> 

                                        <div class="form-actions">
                                            <div class="row">
                                                <div class="col-md-offset-3 col-md-9">
                                                    <button type="submit" class="btn green"><i class="fa fa-check"></i> Submit</button>
                                                    <button type="reset" class="btn default">Reset</button>
                                                </div>
                                            </div>
                                        </div>

                                        <!-- END PAGE CONTENT INNER -->
                                    </div>
                                </div>                                                    
                            </form>
                        </div>
                        <!-- End TABLE PORTLET-->
                    </div>
                    <!-- End Container -->
                </div>
                <!-- End PAGE CONTENT -->
            </div>

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
            <script type="text/javascript" src="../assets/global/plugins/select2/select2.min.js"></script>
            <script src="../assets/global/plugins/bootstrap-toastr/toastr.min.js"></script>
            <!-- END PAGE PLUGINS -->
            <script src="../assets/global/scripts/custom.js" type="text/javascript"></script>
            <script src="../assets/admin/interface/scripts/layout.js" type="text/javascript"></script>
            <script src="../assets/admin/pages/scripts/ui-idletimeout.js"></script>
            <script src="../assets/admin/pages/scripts/ui-toastr.js"></script>       
    </body>
    <!-- END BODY -->
</html>