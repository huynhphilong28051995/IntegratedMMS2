<%-- 
    Document   : home
    Created on : Sep 26, 2015, 9:20:06 PM
    Author     : AdminNUS
--%>

<%@page import="mms2.leasing.entity.TenantEntity"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tenant Home Page</title>
    </head>
    <body bgcolor="#FBEFF8">
        <%
            TenantEntity tent = (TenantEntity) request.getSession().getAttribute("tenant");
            String name = tent.getName();
        %>
        <h1>Hi <%=name%>,</h1>
        <h1>Welcome to MMA's Tenant Self-Service Portal</h1><br>
        <h2>How can we help you today?</h2><br>
        <h3>Rent related options</h3>
        <a href="ViewRentInformation">View Rental Information</a><br>
        <a href="RentInvoice">View Rent Invoices</a><br>
        <h3>Make your requests here...</h3>
        <a href="RentRenewalRequest">Make a Rent Renewal Request</a><br>
        <a href="RentRenewalStatus">View Rent Renewal Status</a><br>
        <a href="OutsourceRequest">Make an Outsourcing Request</a><br>
        <a href="OutsourceStatus">View Outsourcing Request Status</a><br>
        <h3>Analytics</h3>
        <a href="ViewSales">View Sales</a><br>
        <br>
        <a href="logout">Logout</a><br>
    </body>
</html>
