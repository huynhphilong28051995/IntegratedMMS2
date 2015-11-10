<%-- 
    Document   : ChangeNumber
    Created on : Sep 26, 2015, 3:51:48 AM
    Author     : AdminNUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Number</title>
    </head>
    <body>
        <h2><u><font color="brown">Change Number</font></u></h2>
        <form action = "doChangeNumber">
            <p>Enter Current Number:</p>
            <%if (request.getAttribute("oldnumber") != null) {
                    String number = (String) request.getAttribute("oldnumber");
            %>
            <input type="text" name="oldnumber" value="<%=number%>"  placeholder="Enter old number"/>
            <%} else {%>

            <input type="text" name="oldnumber" placeholder="Enter old number"/>
            <%
                }
            %>
            <br><br>
            <p>Enter New Number:</p>
            <input type="text" name="newnumber" placeholder="Enter new number"/>
            <br><br>
            <button type="submit">Submit</button>
        </form>
        <%
            String changeNumberStatus = (String) request.getAttribute("changeNumberStatus");
            if (changeNumberStatus != null) {
        %>
        <h4 style="color: red;"><%=changeNumberStatus%></h4>
        <%
            }
        %>
        <br>
        <a href="EditProfile">Back to Edit Page</a><br><br>
        <a href="GoBackIndex">Back to Home Page</a><br>
    </body>
</html>
