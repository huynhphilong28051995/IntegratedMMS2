<%-- 
    Document   : PublicApplicationPortalEventApplicantConfirm
    Created on : Oct 11, 2015, 5:47:30 PM
    Author     : PhiLong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>YO YO YO </h1>
        <%
            String applicantName = (String)request.getSession().getAttribute("applicantName");
            String eventDescription = (String)request.getSession().getAttribute("eventDescription");
            String applicantTel = (String) request.getSession().getAttribute("applicantTel");
            String applicantEmail = (String) request.getSession().getAttribute("applicantEmail");
        %>
        <h2><%=applicantName%></h2><br>
        <h2><%=eventDescription%></h2><br>
        <h2><%=applicantTel%></h2><br>
        <h2><%=applicantEmail%></h2><br>
    </body>
</html>
