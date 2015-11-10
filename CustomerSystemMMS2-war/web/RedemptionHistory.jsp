<%-- 
    Document   : RedemptionHistory
    Created on : Nov 9, 2015, 9:40:39 PM
    Author     : AdminNUS
--%>

<%@page import="mms2.customer.entity.RedemptionHistoryEntity"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Your Redemption History</h1>
        <table style="border: 1px solid black; text-align: left">
            <tr align="left">
                <th>Redemption ID</th>
                <th>Customer</th>
                <th>Gift Name</th>
                <th>Country</th>
                <th>Address</th>
                <th>Date Redeemed</th>
                <th>Status</th>
                <th></th>
            </tr>
            <%
                ArrayList<RedemptionHistoryEntity> redemptionHistoryList = (ArrayList<RedemptionHistoryEntity>) request.getAttribute("redemptionHistoryList");
                for (int i = 0; i < redemptionHistoryList.size(); i++) {
                    Long id = redemptionHistoryList.get(i).getId();
                    String tenantEmail = redemptionHistoryList.get(i).getCustomerEmail();
                    String giftName = redemptionHistoryList.get(i).getGiftName();
                    String country = redemptionHistoryList.get(i).getCountry();
                    String address = redemptionHistoryList.get(i).getCustomerAddress();
                    String dateRedeemed = redemptionHistoryList.get(i).getDateRedeemed().toString().substring(0, 10);
                    String status = redemptionHistoryList.get(i).getStatus();
                    //list all content
            %>
            <tr>
                <td><%=id%></td>
                <td><%=tenantEmail%></td>
                <td><%=giftName%></td>
                <td><%=country%></td>
                <td><%=address%></td>
                <td><%=dateRedeemed%></td>
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
