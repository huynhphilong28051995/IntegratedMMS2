<%--
    Document   : APEditCampaign
    Created on : 20 Oct, 2015, 7:50:20 PM
    Author     : khushnaz
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Merlion APS System | Edit Campaign</title>
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

        <title>Edit Campaign</title>
    </head>

    <body>
        <div class="container">
            <a href="APManagerIndex">
                <span class="glyphicon glyphicon-menu-left"></span>Back to Index Page
            </a>
        </div>
        <div class="container">
            <form action="APSControllerServlet" method="POST">
                <h3>Edit Existing Campaign</h3>

                <div class="form-group">
                    <label for="objective">New Objective:</label>
                    <input type="text" required="required" class="form-control"
                           id="objective" name="objective" placeholder="Enter objective">
                </div>

                <div class="form-group">
                    <label for="channel">New Channel:</label>
                    <input type="text" required="required" class="form-control"
                           id="channel" name="channel" placeholder="Enter channel eg Mailer, Poster">
                </div>

                <input type="hidden" name="action" value="saveEditCampaign" />
                <input type="submit" class="btn btn-success" value="Save Changes" onclick="msgEdit()"/>
                <input type="reset" class="btn btn-default" value="Reset"/>

            </form>
            <script>
                function msgEdit() {
                    alert("Campaign edited successfully!");
                }
            </script>
        </div>
    </body>
</html>