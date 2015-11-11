<%-- 
    Document   : RentRenewalStatus
    Created on : Oct 29, 2015, 10:03:24 PM
    Author     : AdminNUS
--%>

<%@page import="mms.tenant.entity.RentRenewalRequestEntity"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rent Renewal Status</title>
    </head>
    <body>
        <h1>These are your rent renewal requests</h1>
        <table style="border: 1px solid black; text-align: left">
            <tr align="left">
                <th>Rent Request Id</th>
                <th>Tenant ID</th>
                <th>Tenant Name</th>
                <th>Mall Name</th>
                <th>Unit Number</th>
                <th>Description</th>
                <th>Status</th>
            </tr>
            <%
                ArrayList<RentRenewalRequestEntity> renewalList = (ArrayList<RentRenewalRequestEntity>) request.getAttribute("renewalList");
                for (int i = 0; i < renewalList.size(); i++) {
                    Long id = renewalList.get(i).getId();
                    Long tenantID = renewalList.get(i).getTenantID();
                    String name = renewalList.get(i).getName();
                    String mall = renewalList.get(i).getMall();
                    String unitnum = renewalList.get(i).getUnitNumber();
                    String description = renewalList.get(i).getDescription();
                    String status = renewalList.get(i).getStatus();
                    //list all content
            %>
            <tr>
                <td><%=id%></td>
                <td><%=tenantID%></td>
                <td><%=name%></td>
                <td><%=mall%></td>
                <td><%=unitnum%></td>
                <td><%=description%></td>
                <td><%=status%></td>
            </tr>
            <%
                }
            %>
        </table>
        <br>
        <a href="GoBackIndex">Back to Home Page</a><br>
    </body>
</html>
