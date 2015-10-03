<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList, java.util.StringTokenizer" %>
<% 
   String query = request.getQueryString();
   %>
<!DOCTYPE html>
<html lang="en">
   <head>
      <meta charset="utf-8"/>
      <title>Merlion Mall Asia | Manage Department</title>
      <% 
        if ("del".equals(query)) { 
      %> 
      <META HTTP-EQUIV="Refresh" CONTENT="2;URL=manageDepartment">
      <% } %>
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
      <link rel="stylesheet" type="text/css" href="../assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css"/>
      <link rel="stylesheet" type="text/css" href="../assets/global/plugins/bootstrap-timepicker/css/bootstrap-timepicker.min.css"/>
      <link rel="stylesheet" type="text/css" href="../assets/global/plugins/bootstrap-colorpicker/css/colorpicker.css"/>
      <link rel="stylesheet" type="text/css" href="../assets/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css"/>
      <link rel="stylesheet" type="text/css" href="../assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"/>
      <link rel="stylesheet" type="text/css" href="../assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.css"/>
      <link href="../assets/global/plugins/bootstrap-modal/css/bootstrap-modal-bs3patch.css" rel="stylesheet" type="text/css"/>
      <link href="../assets/global/plugins/bootstrap-modal/css/bootstrap-modal.css" rel="stylesheet" type="text/css"/>
      <!-- END PAGE LEVEL SCRIPTS -->
      <!-- BEGIN STYLES -->
      <link href="../assets/global/css/components-rounded.css" id="style_components" rel="stylesheet" type="text/css">
      <link href="../assets/global/css/components-rounded.css" id="style_components" rel="stylesheet" type="text/css">
      <link href="../assets/global/css/plugins.css" rel="stylesheet" type="text/css">
      <link href="../assets/admin/interface/css/layout.css" rel="stylesheet" type="text/css">
      <link href="../assets/admin/interface/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color">
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
      <h1>Manage Department </h1>
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
      Manage Department
      </li>
      </ul>
      <!-- END PAGE BREADCRUMB -->
			<!-- BEGIN PAGE CONTENT INNER -->
			<div class="row">
				<div class="col-md-12">
					<!-- BEGIN EXAMPLE TABLE PORTLET-->
					<div class="portlet light">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-cogs font-green-sharp"></i>
								<span class="caption-subject font-green-sharp bold uppercase">Manage MMA Department(s)</span>
							</div>
							<div class="tools">
								<a href="javascript:;" class="collapse">
								</a>
								<a href="javascript:;" class="remove">
								</a>
							</div>
						</div>
						<div class="portlet-body">
							<div class="table-toolbar">
								<div class="row">
									<div class="col-md-6">
										<div class="btn-group">
										<form method="post" action="addDepartment" method="post" action="adminHome">
											<button class="btn green">
											Add New Department <i class="fa fa-plus"></i>
											</button>
										</form>
										</div>
									</div>
								</div>
							</div>
							<table class="table table-striped table-hover table-bordered" id="sample_editable_1">
							<thead>
							<tr>
								<th>
									 Level Classification
								</th>
                                                                <th>
									 Level Name
								</th>
								<th>
									 Dept Name
								</th>
								<th>
									 Dept Address
								</th>
								<th>
									 Dept Postal Code
								</th>
								<th>
									 Dept Unit Number
								</th>
                                                                <th>
									 Dept Phone Number
								</th>
								<th>
									 Edit
								</th>
								<th>
									 Delete&nbsp;
								</th>
							</tr>
							</thead>
                                                        
                                                        <tbody>
                                                            <% 
                                                            ArrayList departmentList = (ArrayList)request.getAttribute("departmentList");
                                                            int count = 0;
                                                            for (Object o: departmentList) {
                                                                String department = (String)o;
                                                                StringTokenizer st = new StringTokenizer(department, "#");       
                                                            
                                                        %>
							<tr>
								<td>
                                                                         <% String type = st.nextToken();
                                                                         if ("C".equals(type)) {
                                                                             type = "Corporate Headquarter";
                                                                         }
                                                                         if ("L".equals(type)) {
                                                                             type = "Local/Country Office";
                                                                         }
                                                                         if ("M".equals(type)) {
                                                                             type = "Shopping Mall";
                                                                         }
                                                                         %>
                                                                         <%= type %>                   
								</td>
                                                                <td>
									 <%= st.nextToken()%>
                                                                         <% st.nextToken(); //skipping departmentID %>
								</td>
								<td>
									 <%= st.nextToken()%>
								</td>
								<td>
                                                                         <%= st.nextToken() %>
								</td>
								<td>
									 <%= st.nextToken()%>
								</td>
								<td>
									 #<%= st.nextToken()%>
								</td>
                                                                <td>
									 <%= st.nextToken()%>
								</td>
								<td>
									<a href="editLevel?edit=<%= count %>">
									Edit </a>
								</td>
								<td>
                                                                    <a onclick='location.href="?del=<%= count %>"'><i <% 
                                                                    String currQuery = "del=" + String.valueOf(count);
if (currQuery.equals(query)) { %>class="fa fa-check-square-o" <% } else { %> class="fa fa-square-o" <% } %>></i></a>
                                                                    <a data-target="#static" data-toggle="modal">
									Delete </a>
                                                                    <div id="static" class="modal fade" tabindex="-1" data-backdrop="static" data-keyboard="false">
                                                                    <div class="modal-body">
                                                                            <p>
                                                                                     Are you sure you want to delete this department?
                                                                            </p>
                                                                    </div>
                                                                    <div class="modal-footer">
                                                                            <button type="button" data-dismiss="modal" class="btn btn-default">Cancel</button>
                                                                            <a href="?del" class="btn blue">Confirm Delete</a>
                                                                    </div>
                                                                    </div>
								</td>
							</tr>
                                                        <% count++; } %>
							</tbody>
							
                                                        
							</table>
                                                        <br /><div class="label label-info">Info: To delete a department, click on the line item <i class="fa fa-check-square-o"></i>
                                                         and followed by "Delete"</div>
                                                        <br /><div class="label label-info">Note: Single delete entry only.</div>
						</div>
					</div>
					<!-- END EXAMPLE TABLE PORTLET-->
				</div>
			</div>
			<!-- END PAGE CONTENT -->
		</div>                                                        
	</div>
	<!-- END PAGE CONTENT -->
</div>
	 
	 
	 
	 
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
      <script type="text/javascript" src="../assets/global/plugins/select2/select2.min.js"></script>
      <script type="text/javascript" src="../assets/global/plugins/datatables/media/js/jquery.dataTables.min.js"></script>
      <script type="text/javascript" src="../assets/global/plugins/datatables/plugins/bootstrap/dataTables.bootstrap.js"></script>
      <script src="../assets/global/plugins/bootstrap-modal/js/bootstrap-modalmanager.js" type="text/javascript"></script>
      <script src="../assets/global/plugins/bootstrap-modal/js/bootstrap-modal.js" type="text/javascript"></script> 
      <script src="../assets/global/plugins/bootstrap-toastr/toastr.min.js"></script>
      <!-- END PAGE LEVEL PLUGINS -->
      <!-- BEGIN PAGE LEVEL SCRIPTS -->
      <script src="../assets/global/scripts/custom.js" type="text/javascript"></script>
      <script src="../assets/admin/interface/scripts/layout.js" type="text/javascript"></script>
      <script src="../assets/admin/pages/scripts/table-editable.js"></script>
      <script src="../assets/admin/pages/scripts/ui-toastr.js"></script>
      <!-- END PAGE LEVEL SCRIPTS -->
      <script>
         jQuery(document).ready(function() {       
            // initiate layout and plugins
            Custom.init(); // init custom core components
            Layout.init(); // init current layout
            TableEditable.init();
            UIToastr.init(); // init Toastr Alert
         });
      </script>
      
      <% 
        if ("del".equals(query)) {
      %>
      <script language="javascript">
        $(document).ready(function() {
            toastr.success('Department Successfully Deleted!');
        });
      </script>
      <% } %>
      
      <!-- END JAVASCRIPTS -->
   </body>
   <!-- END BODY -->
</html>