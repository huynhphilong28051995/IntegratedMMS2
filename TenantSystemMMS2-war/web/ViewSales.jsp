<%-- 
    Document   : ViewSales
    Created on : Oct 29, 2015, 11:18:08 PM
    Author     : AdminNUS
--%>

<%@page import="mms2.pos.entity.SaleEntity"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ViewSales</title>
    </head>
    <body>
        <h1>Your Sales History</h1>

        <table style="border: 1px solid black; text-align: left">
            <tr align="left">
                <th>Receipt Id</th>
                <th>Date</th>
                <th>Customer</th>
                <th>Mall</th>
                <th>Amount</th>
                <th></th>
            </tr>
            <%
                ArrayList<SaleEntity> saleList = (ArrayList<SaleEntity>) request.getAttribute("saleList");
                double totalmonth = 0;
                for (int i = 0; i < saleList.size(); i++) {
                    String datecheck = saleList.get(i).getDate().toString().substring(0, 7);
                    Timestamp currentTimeStamp = new Timestamp(System.currentTimeMillis());
                    String currentMonth = (currentTimeStamp.toString()).substring(0, 7);
                    if (datecheck.equals(currentMonth)) {
                        Long id = saleList.get(i).getId();
                        String customer = saleList.get(i).getCustomerEmail();
                        String mall = saleList.get(i).getMallName();
                        String date = saleList.get(i).getDate().toString().substring(0, 19);
                        double amount = saleList.get(i).getPrice();
                        totalmonth = amount + totalmonth;

            %>

            <tr>
                <td><%=id%></td>
                <td><%=date%></td>
                <td><%=customer%></td>
                <td><%=mall%></td>
                <td><%=amount%></td>
            </tr>

            <%
                    }
                }
            %>
            <h2><u><font color="red">Current Month Sales</font></u><br>
                Total sales for month = $<%=totalmonth%></h2>
        </table><br>
        <a href="OverallSales">View All Sales</a><br>
        <br><br>
        <a href="GoBackIndex">Back to Home Page</a><br>
    </body>
</html>
