<%-- 
    Document   : ChangePassword
    Created on : Sep 26, 2015, 3:44:06 AM
    Author     : AdminNUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Password</title>
    </head>
    <body>
        <h2><u><font color="brown">Change Password</font></u></h2>
        <form action = "doChangePassword">
           <p>Old Password:</p>
            <input type="password" name="oldpassword" placeholder="Enter old password"/>
            <p>New Password:</p>
            <input type="password" name="newpassword1" placeholder="Enter password"/>
            <p>Enter New Password again:</p>
            <input type="password" name="newpassword2" placeholder="Enter password"/>
            <br><br>
            <button type="submit">Submit</button>
        </form>
        <%
          String  changePassStatus = (String) request.getAttribute("changePasswordStatus");
          if(changePassStatus != null){    
        %>
        <h4 style="color: red;"><%=changePassStatus%></h4>
        <%
          }
        %>
        <br>
        <a href="EditProfile">Back to Edit Page</a><br><br>
        <a href="GoBackIndex">Back to Home Page</a><br>
      </body>
</html>
