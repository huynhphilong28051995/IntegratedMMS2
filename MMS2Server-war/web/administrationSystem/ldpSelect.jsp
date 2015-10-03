<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<% 
   String query = request.getQueryString();
   
   String selected = "";
   String selected1 = "";
   String selected2 = "";
   String levelTypeField = "";
   String levelNameField = "";
   String departmentNameField = "";
%>

<!DOCTYPE html>
<html>
    <head>
<meta charset="utf-8"/>
<title>Merlion Mall Asia | Add Employee</title>
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
<!-- BEGIN PAGE LEVEL STYLES -->
<link rel="stylesheet" type="text/css" href="../assets/global/plugins/select2/select2.css"/>
<link rel="stylesheet" type="text/css" href="../assets/global/plugins/clockface/css/clockface.css"/>
<link rel="stylesheet" type="text/css" href="../assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css"/>
<link rel="stylesheet" type="text/css" href="../assets/global/plugins/bootstrap-timepicker/css/bootstrap-timepicker.min.css"/>
<link rel="stylesheet" type="text/css" href="../assets/global/plugins/bootstrap-colorpicker/css/colorpicker.css"/>
<link rel="stylesheet" type="text/css" href="../assets/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css"/>
<link rel="stylesheet" type="text/css" href="../assets/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css"/>
<!-- END PAGE LEVEL SCRIPTS -->
<!-- BEGIN STYLES -->
<link href="../assets/global/css/components-rounded.css" id="style_components" rel="stylesheet" type="text/css">
<link href="../assets/global/css/components-rounded.css" id="style_components" rel="stylesheet" type="text/css">
<link href="../assets/global/css/plugins.css" rel="stylesheet" type="text/css">
<link href="../assets/admin/interface/css/layout.css" rel="stylesheet" type="text/css">
<link href="../assets/admin/interface/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color">
<link href="../assets/admin/interface/css/custom.css" rel="stylesheet" type="text/css">
<!-- END STYLES -->
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-menu-fixed">
  
<% if ("retrieveLevel".equals(query)) { levelTypeField = request.getAttribute("levelType").toString(); }%>
      <% if ("retrieveDept".equals(query) || "retrievePos".equals(query)) { levelTypeField = session.getAttribute("TempLevelType").toString(); }%>
           <form action="?retrieveLevel" method="post">
      <div class="form-group">
      <label class="control-label col-md-3">Level Classification <span class="required">
      * </span> </label>
      <div class="col-md-4">
      <select style="width: 300px;" name="levelType" class="form-control select2me" placeholder="Please select"/>
	  <option value=""></option>
      <option value="C" <% if(levelTypeField.equals("C")) { selected = "selected"; }%>
         <%= selected %>>Corporate Headquarter</option>
      <option value="L" <% if(levelTypeField.equals("L")) { selected1 = "selected"; }%>
         <%= selected1 %>>Local Branch Office</option>
      <option value="M" <% if(levelTypeField.equals("M")) { selected2 = "selected"; }%>
         <%= selected2 %>>Shopping Mall</option>
      </select>
          <button type="submit" class="btn purple">1. Retrieve Level Name(s)</button>
      </div>
      </div>
      </form>
      
      <% if ("retrieveDept".equals(query) || "retrievePos".equals(query)) { levelNameField = request.getAttribute("levelName").toString(); }%>
      <form action="?retrieveDept" method="post">
          <div class="form-group">
      <label class="control-label col-md-3">Level Name<span class="required">
      * </span> </label>
      <div class="col-md-4">
      <select <% if (!"retrieveLevel".equals(query)) { %> disabled <% }%> style="width: 300px;" name="levelName" class="form-control select2me" placeholder="Please select"/>
      <option value=""></option>             
         <% if ("retrieveLevel".equals(query)) {
         ArrayList levelNameList = (ArrayList)request.getAttribute("levelNameList");
         String levelName = "";
         for (int i=0; i<levelNameList.size(); i++) {
             levelName = levelNameList.get(i).toString();
             %> <option><%= levelName %> </option>
             <% } }%>
             
         <% if ("retrieveDept".equals(query) || "retrievePos".equals(query)) {
         ArrayList levelNameList = (ArrayList)session.getAttribute("TempLevelNameList");
         String levelName = "";
         String selectedLevelName = "";
         for (int i=0; i<levelNameList.size(); i++) {
             levelName = levelNameList.get(i).toString();
             
         %> <option value="<% if(levelName.equals(levelNameField)) { 
                 selectedLevelName = levelName;
                 System.out.println("levelName"+levelName);
                 System.out.println("selectedLevelName"+selectedLevelName);
             }%><%= selectedLevelName %>" <% if(levelName.equals(levelNameField)) { selected = "selected"; }%>
             <%= selected %>><% if (levelName.equals(selectedLevelName)) { %><%= selectedLevelName %><% break; } %></option>
             <% } }%>    
      </select>
      <button type="submit" class="btn purple" <% if ("retrieveLevel".equals(query)) { } else {%>disabled <%}%>>2. Retrieve Level Department(s)</button>
      </div>
      </div>
      </form>
      
      <% if ("retrievePos".equals(query)) { departmentNameField = request.getAttribute("departmentName").toString(); }%>
      <form action="?retrievePos" method="post">    
      <div class="form-group">
      <label class="control-label col-md-3">Department Name<span class="required">
      * </span> </label>
      <div class="col-md-4">
      <select <% if ("retrieveDept".equals(query)) { } else { %> disabled <% }%> style="width: 300px;" name="departmentName" class="form-control select2me" placeholder="Please select"/>
      <option value=""></option>
         <% if ("retrieveDept".equals(query)) {
         ArrayList departmentNameList = (ArrayList)request.getAttribute("departmentNameList");
         String departmentName = "";
         for (int i=0; i<departmentNameList.size(); i++) {
             %> <option><% departmentName = departmentNameList.get(i).toString(); %><%= departmentName %></option>
             <% } }%>
             
         <% if ("retrievePos".equals(query)) {
         ArrayList departmentNameList = (ArrayList)session.getAttribute("TempDepartmentNameList");
         String departmentName = "";
         String selectedDepartmentName = "";
         for (int i=0; i<departmentNameList.size(); i++) {
             departmentName = departmentNameList.get(i).toString();
             
         %> <option value="<% if(departmentName.equals(departmentNameField)) { 
                 selectedDepartmentName = departmentName;
                 System.out.println("departmentName"+departmentName);
                 System.out.println("selectedLevelName"+selectedDepartmentName);
             }%><%= selectedDepartmentName %>" <% if(departmentName.equals(departmentNameField)) { selected = "selected"; }%>
             <%= selected %>><% if (departmentName.equals(selectedDepartmentName)) { %><%= selectedDepartmentName %><% break; } %></option>
             <% } }%>
             
      </select>
      <button type="submit" class="btn purple" <% if ("retrieveDept".equals(query)) { } else {%>disabled <%}%>>3. Retrieve Department Position(s)</button>
      </div>
      </div>
      </form>
      
      <form action="?retrievePos=true" method="post">    
      <div class="form-group">
      <label class="control-label col-md-3">Position Applied<span class="required">
      * </span> </label>
      <div class="col-md-4">
      <select <% if ("retrievePos".equals(query)) { } else { %> disabled <% }%> style="width: 300px;" name="positionName" class="form-control select2me" placeholder="Please select"/>
      <option value="">
         <% if ("retrievePos".equals(query)) { 
                ArrayList departmentPositionList = (ArrayList)request.getAttribute("departmentPositionList");
                String positionName = "";
                for (int i=0; i<departmentPositionList.size(); i++) {
             %> <option><% positionName = departmentPositionList.get(i).toString(); %><%= positionName %></option>
             <% } }%></option>
             
      </select>
      <button type="submit" class="btn purple" <% if ("retrievePos".equals(query)) { } else {%>disabled <%}%>>4. Done Position Selection</button>
      <span class="btn green" onclick="parent.location.reload();" <% if ("retrievePos=true".equals(query) || "restart".equals(query)) { } else {%>disabled <%}%>>Load Data <i class="fa fa-check"></i></span>
      <span class="btn grey" onclick="javascript:location.href='ldpSelect?restart'">Restart <i class="fa fa-refresh"></i></span>
      <br /><div class="badge badge-primary badge-roundless">Info: Follow step 1 to 4 then click on load data to proceed </div>
      <br /><div class="badge badge-success badge-roundless">Info: To change LDP, click on restart then load data</div>
      </div>
      </div>
      </form>
      
    

<!-- BEGIN CORE PLUGINS -->
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
<script type="text/javascript" src="../assets/global/plugins/bootstrap-wizard/jquery.bootstrap.wizard.min.js"></script>
<script type="text/javascript" src="../assets/global/plugins/select2/select2.min.js"></script>
<script type="text/javascript" src="../assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
<script type="text/javascript" src="../assets/global/plugins/bootstrap-timepicker/js/bootstrap-timepicker.min.js"></script>
<script type="text/javascript" src="../assets/global/plugins/clockface/js/clockface.js"></script>
<script type="text/javascript" src="../assets/global/plugins/bootstrap-daterangepicker/moment.min.js"></script>
<script type="text/javascript" src="../assets/global/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>
<script type="text/javascript" src="../assets/global/plugins/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script>
<script type="text/javascript" src="../assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<!-- END PAGE LEVEL PLUGINS -->
<!-- BEGIN PAGE LEVEL SCRIPTS -->
<script src="../assets/global/scripts/custom.js" type="text/javascript"></script>
<script src="../assets/admin/interface/scripts/layout.js" type="text/javascript"></script>
<script src="../assets/admin/pages/scripts/form-wizard.js"></script>
<script src="../assets/admin/pages/scripts/components-pickers.js"></script>
<!-- END PAGE LEVEL SCRIPTS -->
<script>
jQuery(document).ready(function() {       
   // initiate layout and plugins
   Custom.init(); // init custom core components
   Layout.init(); // init current layout
   FormWizard.init();
   ComponentsPickers.init();
});
</script>

<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>
