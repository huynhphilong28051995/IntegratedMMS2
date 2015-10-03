<%@page import="java.util.ArrayList"%>
<% 
   String query = request.getQueryString();
   
   String selected = "";
   String selected1 = "";
   String selected2 = "";
   String levelTypeField = "";
%>
<!DOCTYPE html>
<html lang="en">
   <head>
      <meta charset="utf-8"/>
      <title>Merlion Mall Asia | Add MMA Department</title>
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
      <link rel="stylesheet" type="text/css" href="../assets/global/plugins/bootstrap-toastr/toastr.min.css"/>
      <!-- END GLOBAL MANDATORY STYLES -->
      <!-- BEGIN PAGE LEVEL STYLES -->
      <link rel="stylesheet" type="text/css" href="../assets/global/plugins/select2/select2.css"/>
      <link rel="stylesheet" type="text/css" href="../assets/global/plugins/clockface/css/clockface.css"/>
      <link rel="stylesheet" type="text/css" href="../assets/global/plugins/bootstrap-select/bootstrap-select.min.css"/>
      <!-- END PAGE LEVEL SCRIPTS -->
      <!-- BEGIN STYLES -->
      <link href="../assets/global/css/components-rounded.css" id="style_components" rel="stylesheet" type="text/css">
      <link href="../assets/global/css/plugins.css" rel="stylesheet" type="text/css">
      <link href="../assets/admin/interface/css/layout.css" rel="stylesheet" type="text/css">
      <link href="../assets/admin/interface/css/custom.css" rel="stylesheet" type="text/css">
      <!-- END STYLES -->
      <link rel="shortcut icon" href="favicon.ico"/>
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
                        <span class="username username-hide-mobile">Welcome, <%= session.getAttribute("Session2") %></span>
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
               <form class="search-form" action="extra_search.html" method="GET">
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
                  <li>
                     <a href="adminHome">Admin Home</a>
                  </li>
                  <li class="menu-dropdown classic-menu-dropdown active">
                     <a data-hover="megamenu-dropdown" data-close-others="true" data-toggle="dropdown" href="javascript:;">
                     General Management <i class="fa fa-angle-down"></i>
                     </a>
                     <ul class="dropdown-menu pull-left">
                        <li class=" dropdown-submenu">
                           <a href="#">
                           Department </a>
                           <ul class="dropdown-menu">
                              <li class=" ">
                                 <a href="addDepartment">
                                 Add Department </a>
                              </li>
                              <li class=" ">
                                 <a href="manageDepartment">
                                 Manage Department </a>
                              </li>
                           </ul>
                        </li>
                        <li class=" dropdown-submenu">
                           <a href="#">
                           Employee </a>
                           <ul class="dropdown-menu">
                              <li class=" ">
                                 <a href="addEmployee">
                                 Add Employee </a>
                              </li>
                              <li class=" ">
                                 <a href="manageEmployee">
                                 Manage Employee </a>
                              </li>
                           </ul>
                        </li>
                        <li class=" dropdown-submenu">
                           <a href="#">
                           Position </a>
                           <ul class="dropdown-menu">
                              <li class=" ">
                                 <a href="addPosition">
                                 Add Position </a>
                              </li>
                              <li class=" ">
                                 <a href="managePosition">
                                 Manage Position </a>
                              </li>
                           </ul>
                        </li>
                     </ul>
                  </li>
                  <li class="menu-dropdown classic-menu-dropdown">
                     <a data-hover="megamenu-dropdown" data-close-others="true" data-toggle="dropdown" href="javascript:;">
                     Level Management <i class="fa fa-angle-down"></i>
                     </a>
                     <ul class="dropdown-menu pull-left">
                        <li class=" dropdown-submenu">
                           <a href="#">
                           Merlion Corporate Headquarter(s) </a>
                           <ul class="dropdown-menu">
                              <li class=" ">
                                 <a href="addLevel?corporate">
                                 Add Corporate Headquarter</a>
                              </li>
                              <li class=" ">
                                 <a href="manageLevel">
                                 Manage Headquarter</a>
                              </li>
                           </ul>
                        </li>
                        <li class=" dropdown-submenu">
                           <a href="#">
                           Merlion Office(s) </a>
                           <ul class="dropdown-menu">
                              <li class=" ">
                                 <a href="addLevel?local">
                                 Add Office </a>
                              </li>
                              <li class=" ">
                                 <a href="manageLevel">
                                 Manage Office(s) </a>
                              </li>
                           </ul>
                        </li>
                        <li class=" dropdown-submenu">
                           <a href="#">
                           Merlion Mall(s) </a>
                           <ul class="dropdown-menu">
                              <li class=" ">
                                 <a href="addLevel?mall">
                                 Add Mall </a>
                              </li>
                              <li class=" ">
                                 <a href="manageLevel">
                                 Manage Mall(s) </a>
                              </li>
                           </ul>
                        </li>
                     </ul>
					</li>
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
      <h1>Add and Assign Department </h1>
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
      <li>
      <a href="#">Home</a><i class="fa fa-circle"></i>
      </li>
      <li>
      <a href="#">General Management</a>
      <i class="fa fa-circle"></i>
      </li>
      <li class="active">
      Add Department
      </li>
      </ul>
      <!-- END PAGE BREADCRUMB -->
      <!-- BEGIN PAGE CONTENT INNER -->
      <div class="row">
      <div class="col-md-12">
      <div class="tab-pane" id="tab_6">
      <div class="portlet box blue-hoki">
      <div class="portlet-title">
      <div class="caption">
      <i class="fa fa-suitcase"></i>Add and Assign Department
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
      <% if ("retrieveLevel".equals(query)) { levelTypeField = request.getAttribute("levelType").toString(); }%>
      <form action="?retrieveLevel" method="post" class="form-horizontal form-bordered form-row-stripped">
      <div class="form-group">
      <label class="control-label col-md-3">Level Classification <span class="required">
      * </span> </label>
      <div class="col-md-4">
      <select style="width: 350px;" name="levelType" class="form-control select2me" placeholder="Please select"/>
	  <option value=""></option>
      <option value="C" <% if(levelTypeField.equals("C")) { selected = "selected"; }%>
         <%= selected %>>Corporate Headquarter</option>
      <option value="L" <% if(levelTypeField.equals("L")) { selected1 = "selected"; }%>
         <%= selected1 %>>Local Branch Office</option>
      <option value="M" <% if(levelTypeField.equals("M")) { selected2 = "selected"; }%>
         <%= selected2 %>>Shopping Mall</option>
      </select>
          <button type="submit" class="btn purple">1. Retrieve Level Information</button>
      </div>
      </div>
      </form>
      <form action="?retrieveDept" method="post" class="form-horizontal form-bordered form-row-stripped">    
      <div class="form-group">
      <label class="control-label col-md-3">Level Name<span class="required">
      * </span> </label>
      <div class="col-md-4">
      <select <% if (!("retrieveDept".equals(query) || "retrieveLevel".equals(query))) { %> disabled <% }%> style="width: 350px;" name="levelName" class="form-control select2me" placeholder="Please select"/>
      <option value=""></option>
      <% if ("retrieveLevel".equals(query)) {
         ArrayList levelNameList = (ArrayList)request.getAttribute("levelNameList");
         String levelName = "";
         for (int i=0; i<levelNameList.size(); i++) {
             %> <option><% levelName = levelNameList.get(i).toString(); %><%= levelName %></option>
             <% } }%>
      </select>
      <button type="submit" class="btn purple" <% if ("retrieveLevel".equals(query)) { } else {%>disabled <%}%>>2. Retrieve Department Information</button>
      </div>
      </div>
      </form>
      
      <form action="?addDept" method="post" class="form-horizontal form-bordered form-row-stripped">
      <div class="form-group">
      <label class="control-label col-md-3">Add Department <span class="required">
      * </span> </label>
      <div class="col-md-4">
        <a class="btn yellow" data-toggle="modal" href="#addDept" <% if ("retrieveDept".equals(query)) { } else {%>disabled <%}%>>3. Add New Department <i class="fa fa-external-link"></i></a>
        
        <div class="modal fade" id="addDept">
          <div class="modal-dialog">
              <div class="modal-content">
                  <div class="modal-header">
                      <button class="close" data-dismiss="modal" type=
                      "button"></button>

                      <h4 class="modal-title">Add a new department for this level (<%= session.getAttribute("TempLevelName") %>)</h4>
                  </div>

                  <div class="modal-body">
                      <form action="javascript:;" class="form-horizontal">
                          <div class="form-group">
                              <label class="control-label col-md-4">Dept. Name</label>
                              <div class="col-md-8">
                                  <input type="text" name="departmentName" placeholder="Specify new department name" class="form-control"/>
                              </div>
                          </div>
                          <div class="form-group">
                              <label class="control-label col-md-4">Dept. Address</label>
                              <div class="col-md-8">
                                  <input type="text" name="departmentAddress" placeholder="Specify new department address" class="form-control"
                                         value="<% if ("retrieveDept".equals(query)) { 
                ArrayList deptInfoList = (ArrayList)request.getAttribute("deptInfoList"); 
                String deptInfoAddress = deptInfoList.get(0).toString(); %><%= deptInfoAddress %><% } %>"/>
                              </div>
                          </div>
                          <div class="form-group">
                              <label class="control-label col-md-4">Dept. Postal Code</label>
                              <div class="col-md-8">
                                  <input type="text" name="departmentPostalCode" placeholder="Specify new department postal code" class="form-control"
                                         value="<% if ("retrieveDept".equals(query)) { 
                ArrayList deptInfoList = (ArrayList)request.getAttribute("deptInfoList"); 
                String deptInfoPostal = deptInfoList.get(1).toString(); %><%= deptInfoPostal %><% } %>"/>
                              </div>
                          </div>
                          <div class="form-group">
                              <label class="control-label col-md-4">Dept. Unit Number</label>
                              <div class="col-md-8">
                                  <input type="text" name="departmentUnitNumber" placeholder="Specify new department unit number" class="form-control"/>
                              </div>
                          </div>
                          <div class="form-group">
                              <label class="control-label col-md-4">Dept. Phone Number</label>
                              <div class="col-md-8">
                                  <input type="text" name="departmentPhoneNumber" placeholder="Specify new department phone number" class="form-control"/>
                              </div>
                          </div>
                      </form>
                  </div>

                  <div class="modal-footer">
                      <button class="btn default" data-dismiss=
                      "modal">Close</button> <button type="submit" class="btn green"
                      ><i class="fa fa-check"></i> Add Department</button>
                  </div>
              </div>
          </div>
      </div>
          
          
      </div>
      </div>
      </form>
      
      
      
      <form action="?true" method="post" class="form-horizontal form-bordered form-row-stripped"> 
      <div class="form-group">
      <label class="control-label col-md-3">Assigned Department(s)</label>
      <div class="col-md-9">
         <input style="width: 400px;" <% if (!"retrieveDept".equals(query)) { %> disabled <% }%> type="hidden" id="select2_sample5" name="departmentName" class="form-control select2" 
                value="<% if ("retrieveDept".equals(query)) { 
                ArrayList departmentNameList = (ArrayList)request.getAttribute("departmentNameList");
                String departmentName = departmentNameList.toString();
                departmentName = departmentName.substring(1, departmentName.length()-1);
                
                %><%= departmentName %><% }%>" 
                    placeholder="Available department(s) in this level" disabled/>		
      </div>
      </div>

      <div class="form-actions">
      <div class="row">
      <div class="col-md-offset-3 col-md-9">
      <button type="submit" class="btn green"><i class="fa fa-share"></i> Manage Department(s)</button>
      <a href="adminHome" class="btn default">Cancel</a>
      </div>
      </div>
      </div>
      </form>
      <!-- END FORM-->
      
      </div>
      </div> 
      </div>
      
      </div>
      </div>
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
      <!-- BEGIN CORE PLUGINS -->
      <!--[if lt IE 9]>
      <script src="../assets/global/plugins/respond.min.js"></script>
      <script src="../assets/global/plugins/excanvas.min.js"></script> 
      <![endif]-->
      <script src="../assets/global/plugins/jquery.min.js" type="text/javascript"></script>
      <script src="../assets/global/plugins/jquery-migrate.min.js" type="text/javascript"></script>
      <!-- IMPORTANT! Load jquery-ui.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
      <script src="../assets/global/plugins/jquery-ui/jquery-ui.min.js" type="text/javascript"></script>
      <script src="../assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
      <script src="../assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
      <script src="../assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
      <script src="../assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
      <script src="../assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
      <script src="../assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
      <!-- END CORE PLUGINS -->
      <!-- BEGIN PAGE LEVEL PLUGINS -->
      <script type="text/javascript" src="../assets/global/plugins/jquery-validation/js/jquery.validate.min.js"></script>
      <script type="text/javascript" src="../assets/global/plugins/jquery-validation/js/additional-methods.min.js"></script>
      <script type="text/javascript" src="../assets/global/plugins/select2/select2.min.js"></script>
      <script type="text/javascript" src="../assets/global/plugins/clockface/js/clockface.js"></script>
      <script src="../assets/global/plugins/bootstrap-toastr/toastr.min.js"></script>
      <!-- END PAGE LEVEL PLUGINS -->
      <!-- BEGIN PAGE LEVEL SCRIPTS -->
      <script src="../assets/global/scripts/custom.js" type="text/javascript"></script>
      <script src="../assets/admin/interface/scripts/layout.js" type="text/javascript"></script>
      <script src="../assets/admin/pages/scripts/form-wizard.js"></script>
      <script src="../assets/admin/pages/scripts/components-pickers.js"></script>
      <script src="../assets/admin/pages/scripts/ui-toastr.js"></script>
      <!-- END PAGE LEVEL SCRIPTS -->
      <script>
         jQuery(document).ready(function() {       
            // initiate layout and plugins
            Custom.init(); // init custom core components
            Layout.init(); // init current layout
            FormWizard.init();
            UIToastr.init(); // init Toastr Alert
         });
      </script>
      
      <% 
        if ("addDept".equals(query)) {
        String message = request.getAttribute("message").toString();
        if (message.equals("new")) {
      %>
      <script language="javascript">
        $(document).ready(function() {
            toastr.success('Department Successfully Created!');
        });
      </script>
      <% } else if (message.equals("missing")) { %>
      <script language="javascript">
        $(document).ready(function() {
            toastr.error('Please fill up all the mandatory fields in the form', 'Attention: Missing Input!');
        });
      </script>
      <% } else { %>
      <script language="javascript">
        $(document).ready(function() {
            toastr.error('The department name that is specified already exist in this level', 'Attention: Department Already Existed!');
        });
      </script>
      <% } } %>
      
<% 
    /* ArrayList departmentList = (ArrayList)request.getAttribute("departmentList");
    if (departmentList.size() != 0) {
    String allDepartment = "";
    for (int i=0; i<departmentList.size(); i++) {
       
       String department = allDepartment + departmentList.get(i).toString();
       if (i == departmentList.size()-1) {
           allDepartment = department;
       } 
       else {
           allDepartment = department + ", ";
       }
    }
    System.out.println(allDepartment);
    }
    */
    
%> 

      <script language="javascript">
	  $("#select2_sample5").select2({
            tags: []
        });
      </script>
      <!-- END JAVASCRIPTS -->
   </body>
   <!-- END BODY -->
</html>