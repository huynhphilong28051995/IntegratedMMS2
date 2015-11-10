<%-- 
    Document   : APRestartCampaign
    Created on : 6 Nov, 2015, 11:38:22 PM
    Author     : khushnaz
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Merlion APS System | Restart Campaign</title>
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
        <script src="${pageContext.request.contextPath}/javascript/mainScript.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-ui-1.11.4.custom/jquery-ui.theme.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-ui-1.11.4.custom/jquery-ui.css">
        <script src="${pageContext.request.contextPath}/jquery-ui-1.11.4.custom/external/jquery/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/jquery-ui-1.11.4.custom/jquery-ui.js"></script>

        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <title>Restart Campaign</title>
    </head>

    <body>
        <div class="container">
            <a href="APManagerIndex">
                <span class="glyphicon glyphicon-menu-left"></span>Back to Index Page
            </a>
        </div>
        <div class="container">
            <form action="APSControllerServlet" method="POST">
                <h3>Reactivate Campaign</h3>

                <div class="form_group">
                    <label>Start date</label>
                    <input type="text" id="datepickerStart" name="startDate"/>
               
                    <label>End date</label>
                    <input type="text" id="datepickerEnd" name="endDate"/>
                </div>

                <div class="form-group">
                    <label for="channel">Channel:</label>
                    <input type="text" required="required" class="form-control"
                           id="channel" name="channel" placeholder="Enter channel eg Mailer, Poster">
                </div>
                
                <input type="hidden" name="action" value="startPastCampaign" />
                <input type="submit" class="btn btn-success" value="Reactivate Campaign" onclick="msgRestart()"/>
                <input type="reset" class="btn btn-default" value="Reset"/>

            </form>
            <script>
                function msgRestart() {
                    alert("Campaign reactivated successfully!");
                }
                $(function () {
                    $("#datepickerStart").datepicker({
                        dateFormat: 'yy-mm-dd',
                        minDate: 0,
                        onSelect: function (selected) {
                            var dt = new Date(selected);
                            dt.setDate(dt.getDate());
                            $("#datepickerEnd").datepicker("option", "minDate", dt);
                        }
                    });
                    $("#datepickerEnd").datepicker({
                        dateFormat: 'yy-mm-dd',
                        onSelect: function (selected) {
                            var dt = new Date(selected);
                            dt.setDate(dt.getDate() + 1);
                            $("#datepickerStart").datepicker("option", "maxDate", dt);
                        }
                    });
                });
            </script>
        </div>
    </body>
</html>
