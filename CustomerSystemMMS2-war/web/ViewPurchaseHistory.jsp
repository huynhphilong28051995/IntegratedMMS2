<%-- 
    Document   : ViewPurchaseHistory
    Created on : Sep 18, 2015, 10:08:36 AM
    Author     : AdminNUS
--%>

<%@page import="mms2.pos.entity.SaleEntity"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.jfree.chart.ChartUtilities"%>
<%@page import="org.jfree.chart.entity.StandardEntityCollection"%>
<%@page import="org.jfree.chart.ChartRenderingInfo"%>
<%@page import="org.jfree.chart.labels.StandardPieSectionLabelGenerator"%>
<%@page import="org.jfree.chart.plot.PiePlot"%>
<%@page import="org.jfree.chart.JFreeChart"%>
<%@page import="org.jfree.chart.ChartFactory"%>
<%@page import="org.jfree.data.general.DefaultPieDataset"%>
<%@page import="java.awt.Color"%>
<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PurchaseHistory</title>
    </head>
    <body>
        <h1>Your purchase history with Merlion Malls Asia</h1>
        <table style="border: 1px solid black; text-align: left">
            <tr align="left">
                <th>Receipt Id</th>
                <th>Date</th>
                <th>Tenant ID</th>
                <th>Tenant Name</th>
                <th>Mall Name</th>
                <th>Amount Spend</th>
                <th>Points Earned</th>
                <th></th>
            </tr>
            <%
                ArrayList<SaleEntity> purchaseList = (ArrayList<SaleEntity>) request.getAttribute("purchaseList");
                double total = 0;
                double numFB = 0;
                double numRT = 0;
                double numET = 0;
                double numEV = 0;
                double numUndefined = 0;
                for (int i = 0; i < purchaseList.size(); i++) {
                    Long id = purchaseList.get(i).getId();
                    Long tenantID = purchaseList.get(i).getTenantId();
                    String businessType = purchaseList.get(i).getBusinessType();
                    double price = purchaseList.get(i).getPrice();
                    if (businessType.equals("Food&Beverage")) {
                        numFB = numFB + price;
                    } else if (businessType.equals("Retail")) {
                        numRT = numRT + price;
                    } else if (businessType.equals("Entertainment")) {
                        numET = numET + price;
                    } else if (businessType.equals("Event")) {
                        numEV = numEV + price;
                    } else {
                        numUndefined = numUndefined + price;
                    }
                    String name = purchaseList.get(i).getTenantName();
                    int pointsEarned = purchaseList.get(i).getPointsEarned();
                    String mall = purchaseList.get(i).getMallName();
                    String date = purchaseList.get(i).getDate().toString().substring(0, 19);
                    total = price + total;
                    //list all content
%>
            <tr>
                <td><%=id%></td>
                <td><%=date%></td>
                <td><%=tenantID%></td>
                <td><%=name%></td>
                <td><%=mall%></td>
                <td><%=price%></td>
                <td><%=pointsEarned%></td>
            </tr>
            <%
                }
            %>
            <h2>So far you have spent a total of $<%=total%></h2>
        </table><br>
        <a href="ViewAnalytics">View Analytics</a><br>
        <br><br>
        <a href="GoBackIndex">Back to Home Page</a><br>

    </body>
</html>
