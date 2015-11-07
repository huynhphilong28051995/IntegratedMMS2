<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String message = "";
    message = request.getAttribute("message").toString();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <% if (message.equals("false")) { %>
        <meta http-equiv="refresh" content="1; url=login?false" />
        <% } else {
            String IP = request.getHeader("referer");
            int begin = IP.indexOf(":") + 3;
            int end = IP.indexOf(":", begin);
            IP = IP.substring(begin, end);
            request.getSession().setAttribute("IP", IP);
            System.err.println("IP = "+IP);
        %>
        <meta http-equiv="refresh" content="1; url=<%=message%>"/>
        <% }%>
        <title>Page Redirect</title>
    </head>
    <body>
    <center>
        <h1>Page is redirecting...</h1><br>
        <img src="../assets/images/loading.gif" width="120" height="120"/>
    </center>
</body>
</html>