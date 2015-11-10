<%-- 
    Document   : ViewRentInformation
    Created on : Sep 26, 2015, 8:51:51 PM
    Author     : AdminNUS
--%>

<%@page import="mms2.leasing.entity.TenantEntity"%>
<%@page import="mms2.leasing.entity.TenantContractEntity"%>
<%@page import="mms2.leasing.entity.UnitEntity"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>About your rent</title>
    </head>
    <body bgcolor="#FBEFF8">
        <%
            TenantEntity tent = (TenantEntity) request.getAttribute("tenant");
            String name = tent.getName();
            String category = tent.getBusinessType();
            String email = tent.getEmail();
            String telephone = tent.getTel();
            Long id = tent.getId();
            String mall = tent.getMallName();
            TenantContractEntity contract = (TenantContractEntity) request.getAttribute("contract");
            String start = contract.getStartTimestamp().toString().substring(0,10);
            String end = contract.getEndTimestamp().toString().substring(0,10);
            Double deposit = contract.getDeposit();
            Double rate = contract.getRate();
            String status = contract.getStatus();
            ArrayList<UnitEntity> tenantunit = (ArrayList<UnitEntity>) request.getAttribute("tenantunit");
            String unitCodeString="";
            for(int i=0; i<tenantunit.size();i++){
                UnitEntity unit = tenantunit.get(i);
                unitCodeString = unitCodeString+" "+unit.getLocationCode();
            }
        %>
        <h1><u><font color="red"><%=name%></font></u></h1>
        <h3>Status of rent: <%=status%></h3>
        <h3>Your business type: <%=category%></h3>
        <h3>Mall Name: <%=mall%></h3>
        <h3>Unit number(s): <%=unitCodeString%></h3>
        <h3>Contract Start date: <%=start%> </h3>
        <h3>Contract End date: <%=end%></h3>
        <h3>Your deposit with us is <u>SGD$<%=deposit%></u></h3>
        <h3>Your monthly rental is <u>SGD$<%=rate%></u></h3>
        <br>
        <h3>Other Details</h3>
        <p>Your id: <%=id%></p>
        <p>Your email: <%=email%></p>
        <p>Your telephone number: <%=telephone%></p>
     <a href="GoBackIndex">Back to Home Page</a><br>
    </body>
</html>
