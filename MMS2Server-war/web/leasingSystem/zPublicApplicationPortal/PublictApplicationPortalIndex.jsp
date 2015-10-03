<%-- 
    Document   : indexTenantPortal
    Created on : Sep 19, 2015, 4:26:33 PM
    Author     : PhiLong
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TENANT APPLICATION PORTAL</title>
    </head>
    <body>
         <form action="PublicApplicationPortalServlet/EnterPublicPortal" method="GET">
                <div class="form-group">
                    <label for="mallName">Mall name:</label>
                    <input type="text" required="required" class="form-control"
                           id="mallName" name="mallName" placeholder="Enter mall name">
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
        </form>
    </body>
</html>
