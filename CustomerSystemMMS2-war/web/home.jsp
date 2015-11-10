<%-- 
    Document   : home
    Created on : Sep 22, 2015, 3:55:38 PM
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
        <h2>How can we help you today?</h2>
        <br><br>
        <h3>Membership Deatils</h3>
        <a href="ViewMembership">View Membership</a><br>
        <a href="EditProfile">Edit Profile</a><br>
        <h3>Gift Redemption</h3>
         <a href="RedeemGifts">Redeem Gifts</a><br>
         <a href="RedemptionHistory">Redemption History</a><br>
        <h3>Transactions & Analytics</h3>
        <a href="ViewPurchaseHistory">View Purchase History</a><br>
        <br><br>
        <a href="logout">Logout</a><br>
    </body>
</html>
