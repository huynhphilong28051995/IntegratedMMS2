<%-- 
    Document   : RentInvoice
    Created on : Oct 29, 2015, 11:12:50 PM
    Author     : AdminNUS
--%>

<%@page import="mms.tenant.entity.RentInvoiceEntity"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RentInvoice</title>
    </head>
    <body>
        <h1>Your Rent Invoices...</h1>
        <p>Dear Tenants,</p>
            <p>Do remember that you are given 15 days from the month end to make payments.<br>
                Thank you for your continuous support!</p>
        <p>The Management.</p>
        <h2><u><font color="red">Outstanding Invoice(s)</font></u></h2>
        <table style="border: 1px solid black; text-align: left">
            <tr align="left">
                <th>Invoice Id</th>
                <th>Tenant ID</th>
                <th>Description</th>
                <th>Amount</th>
                <th>Status</th>
                <th></th>
            </tr>
            <%
                ArrayList<RentInvoiceEntity> rentInvoiceList = (ArrayList<RentInvoiceEntity>) request.getAttribute("rentInvoiceList");
                for (int i = 0; i < rentInvoiceList.size(); i++) {
                    String status = rentInvoiceList.get(i).getStatus();
                    if (status.equals("Pending Payment")) {
                        
                    Long id = rentInvoiceList.get(i).getId();
                    Long tenantID = rentInvoiceList.get(i).getTenantID();
                    double amount = rentInvoiceList.get(i).getAmount();
                    String description = rentInvoiceList.get(i).getDescription();
                    //list all content
            %>
            <tr>
                <td><%=id%></td>
                <td><%=tenantID%></td>
                <td><%=description%></td>
                <td><%=amount%></td>
                <td><%=status%></td>
            </tr>
            <%
                    }
                }
            %>
        </table>
        <br><br>

        <h2><font color="blue"><u>Paid Invoice(s)</u></font></h2>        
        <table style="border: 1px solid black; text-align: left">
            <tr align="left">
                <th>Invoice Id</th>
                <th>Tenant ID</th>
                <th>Description</th>
                <th>Amount</th>
                <th>Status</th>
                <th></th>
            </tr>

            <%
                for (int i = 0; i < rentInvoiceList.size(); i++) {
                    String status = rentInvoiceList.get(i).getStatus();
                    if (status.equals("Paid")) {
                        Long id = rentInvoiceList.get(i).getId();
                        Long tenantID = rentInvoiceList.get(i).getTenantID();
                        double amount = rentInvoiceList.get(i).getAmount();
                        String description = rentInvoiceList.get(i).getDescription();
                        //list all content
            %>
            <tr>
                <td><%=id%></td>
                <td><%=tenantID%></td>
                <td><%=description%></td>
                <td><%=amount%></td>
                <td><%=status%></td>
            </tr>
            <%
                    }
                }
            %>
        </table>
        <br>
        <a href="GoBackIndex">Back to Home Page</a><br>

    </body>
</html>
