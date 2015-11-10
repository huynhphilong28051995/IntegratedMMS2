<%-- 
    Document   : index
    Created on : Sep 17, 2015, 4:07:14 PM
    Author     : AdminNUS
--%>

<%@page import="mms2.customer.entity.CustomerEntity"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MMA Customer Portal</title>
    </head>
    <body bgcolor="#E6E6E6">
        <%
            CustomerEntity cust = (CustomerEntity) request.getAttribute("customer");
            String firstName = cust.getFirstName();
            String lastName = cust.getLastName();
        %>
        <h1>Hi <%=firstName%> <%=lastName%>,</h1> 
        <h1>Welcome to MMA's Customer Self-Service Portal!</h1><br>
        <h3>How can we help you today?</h3>
        <a href="CustomerSystemControllerServlet/ViewMembership">View Membership</a><br>
        <a href="CustomerSystemControllerServlet/ViewPurchaseHistory">View Purchase History</a><br>
        <a href="CustomerSystemControllerServlet/RedeemGifts">Redeem Gifts</a><br>
        <a href="CustomerSystemControllerServlet/EditProfile">Edit Profile</a><br>
    </body>
</html>
