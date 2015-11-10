<%-- 
    Document   : login
    Created on : Sep 22, 2015, 1:39:27 PM
    Author     : AdminNUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Login</title>
    </head>
    <body>
        <h1>MMA Customer System Login Page</h1>
        <form action="CustomerSystemControllerServlet/verifyLogin">
            <p>Email:</p>
            <input type="text" name="email" placeholder="Enter email"/>
            <p>Password:</p>
            <input type="password" name="password" placeholder="Enter password"/>
            <br><br>
            <button type="submit">Submit</button>
        </form>
    </body>
</html>
