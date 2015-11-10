<%-- 
    Document   : ViewMembership
    Created on : Sep 17, 2015, 4:08:42 PM
    Author     : AdminNUS
--%>

<%@page import="mms2.customer.entity.CustomerEntity"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Membership</title>
    </head>
    <body bgcolor="#E6E6E6">
        <%
            CustomerEntity cust = (CustomerEntity) request.getAttribute("customer");
            String firstName = cust.getFirstName();
            String lastName = cust.getLastName();
            Integer points = cust.getPoints();
            String type = cust.getMemberType();
            Long customerid = cust.getId();
            String customeremail = cust.getEmail();
            String address = cust.getAddress();
            String telephone = cust.getTelephone();
            String country = cust.getCountry();
        %>
        <h2>Your name is <u><%=firstName%> <%=lastName%></u></h2>
        <h2>Your membership type is <u><%=type%></u></h2>
        <h2>Your points accumulated so far is <u><%=points%></u></h2><br>

        <h3>Personal Details</h3>
        <p>Your ID: <%=customerid%> </p>
        <p>Your email: <%=customeremail%></p>
        <p>Your country: <%=country%></p>
        <p>Your address: <%=address%></p>
        <p>Your telephone number: <%=telephone%></p>
        
        <a href="GoBackIndex">Back to Home Page</a><br>
   

</body>
</html>
