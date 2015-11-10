<%-- 
    Document   : OverallSales
    Created on : Nov 6, 2015, 5:06:50 PM
    Author     : AdminNUS
--%>

<%@page import="mms2.pos.entity.SaleEntity"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Overall Sales</title>
    </head>
    <body>
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
                double total = 0;
                double total0 = 0;
                double total1 = 0;
                double total2 = 0;
                double total3 = 0;
                double total4 = 0;
                double total5 = 0;
                double total6 = 0;
                double total7 = 0;
                double total8 = 0;
                double total9 = 0;
                double total10 = 0;
                double total11 = 0;

                for (int i = 0; i < saleList.size(); i++) {
                    String datecheck = saleList.get(i).getDate().toString().substring(0, 7);
                    Timestamp currentTimeStamp = new Timestamp(System.currentTimeMillis());
                    String currentMonth = (currentTimeStamp.toString()).substring(0, 7);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(new Date(currentTimeStamp.getTime()));
                    cal.add(Calendar.MONTH, -1);
                    String currentMonthless1 = (new Timestamp(cal.getTimeInMillis())).toString().substring(0, 7);
                    cal.add(Calendar.MONTH, -1);
                    String currentMonthless2 = (new Timestamp(cal.getTimeInMillis())).toString().substring(0, 7);
                    cal.add(Calendar.MONTH, -1);
                    String currentMonthless3 = (new Timestamp(cal.getTimeInMillis())).toString().substring(0, 7);
                    cal.add(Calendar.MONTH, -1);
                    String currentMonthless4 = (new Timestamp(cal.getTimeInMillis())).toString().substring(0, 7);
                    cal.add(Calendar.MONTH, -1);
                    String currentMonthless5 = (new Timestamp(cal.getTimeInMillis())).toString().substring(0, 7);
                    cal.add(Calendar.MONTH, -1);
                    String currentMonthless6 = (new Timestamp(cal.getTimeInMillis())).toString().substring(0, 7);
                    cal.add(Calendar.MONTH, -1);
                    String currentMonthless7 = (new Timestamp(cal.getTimeInMillis())).toString().substring(0, 7);
                    cal.add(Calendar.MONTH, -1);
                    String currentMonthless8 = (new Timestamp(cal.getTimeInMillis())).toString().substring(0, 7);
                    cal.add(Calendar.MONTH, -1);
                    String currentMonthless9 = (new Timestamp(cal.getTimeInMillis())).toString().substring(0, 7);
                    cal.add(Calendar.MONTH, -1);
                    String currentMonthless10 = (new Timestamp(cal.getTimeInMillis())).toString().substring(0, 7);
                    cal.add(Calendar.MONTH, -1);
                    String currentMonthless11 = (new Timestamp(cal.getTimeInMillis())).toString().substring(0, 7);

                    if (datecheck.equals(currentMonth)) {
                        double amount0 = saleList.get(i).getPrice();
                        total0 = amount0 + total0;
                    }
                    if (datecheck.equals(currentMonthless1)) {
                        double amount1 = saleList.get(i).getPrice();
                        total1 = amount1 + total1;
                    }
                    if (datecheck.equals(currentMonthless2)) {
                        double amount2 = saleList.get(i).getPrice();
                        total2 = amount2 + total2;
                    }
                    if (datecheck.equals(currentMonthless3)) {
                        double amount3 = saleList.get(i).getPrice();
                        total3 = amount3 + total3;
                    }
                    if (datecheck.equals(currentMonthless4)) {
                        double amount4 = saleList.get(i).getPrice();
                        total4 = amount4 + total4;
                    }
                    if (datecheck.equals(currentMonthless5)) {
                        double amount5 = saleList.get(i).getPrice();
                        total5 = amount5 + total5;
                    }
                    if (datecheck.equals(currentMonthless6)) {
                        double amount6 = saleList.get(i).getPrice();
                        total6 = amount6 + total6;
                    }
                    if (datecheck.equals(currentMonthless7)) {
                        double amount7 = saleList.get(i).getPrice();
                        total7 = amount7 + total7;
                    }
                    if (datecheck.equals(currentMonthless8)) {
                        double amount8 = saleList.get(i).getPrice();
                        total8 = amount8 + total8;
                    }
                    if (datecheck.equals(currentMonthless9)) {
                        double amount9 = saleList.get(i).getPrice();
                        total9 = amount9 + total9;
                    }
                    if (datecheck.equals(currentMonthless10)) {
                        double amount10 = saleList.get(i).getPrice();
                        total10 = amount10 + total10;
                    }
                    if (datecheck.equals(currentMonthless11)) {
                        double amount11 = saleList.get(i).getPrice();
                        total11 = amount11 + total11;
                    }
                    Long id = saleList.get(i).getId();
                    String customer = saleList.get(i).getCustomerEmail();
                    String mall = saleList.get(i).getMallName();
                    String date = saleList.get(i).getDate().toString().substring(0, 19);
                    double amount = saleList.get(i).getPrice();
                    total = amount + total;
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
            %>
            <h2><u><font color="blue">Overall Sales History</font></u><br>
                Total sales = $<%=total%></h2><br>
            <a href="ViewSales">Back Current Month Sales Page</a><br><br>
            <a href="SalesChart">View Sales Chart for last 12 months</a><br>
        </table>

        <a href="GoBackIndex">Back to Home Page</a><br>


    </body>
</html>
