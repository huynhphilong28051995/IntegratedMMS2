<%-- 
    Document   : login
    Created on : Sep 26, 2015, 8:54:39 PM
    Author     : AdminNUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tenant Login</title>
    </head>
    <body>
        <h1>MMA Tenant System Login Page</h1>
        <form action="TenantSystemServlet/verifyLogin" method="GET">
            <p>Tenant Email:</p>
            <input type="text" name="email" placeholder="Enter email"/>
            <p>Password:</p>
            <input type="password" name="password" placeholder="Enter password"/>
            <br><br>
            <button type="submit">Submit</button>
        </form>
    </body>
</html>
