<%-- 
    Document   : RedeemGifts
    Created on : Sep 18, 2015, 10:09:18 AM
    Author     : AdminNUS
--%>


<%@page import="mms2.customer.entity.CustomerEntity"%>
<%@page import="mms2.customer.entity.GiftEntity"%>
<%@page import="java.util.ArrayList"%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Redeem Gifts</title>
    </head>
    <body>
        <%
            CustomerEntity cust = (CustomerEntity) request.getAttribute("customer");
            Integer cuspoint = cust.getPoints();
        %>
        <h1><font color="green">Choose from the many gifts below!</font></h1>
        <h1><font color="green">Points available: <%=cuspoint%></font></h1> <a href="GoBackIndex">Back to Home Page</a><br>
        <br><hr>    

        <%
            ArrayList<GiftEntity> gifts = new ArrayList<GiftEntity>((List<GiftEntity>) request.getAttribute("gifts"));
            Integer size = gifts.size();
            for (int i = 0; i < size; i++) {
                GiftEntity g = gifts.get(i);
                Long id = g.getId();
                String name = g.getName();
                Integer points = g.getPoints();
                Integer quantity = g.getQuantity();
        %>
        <h2>Gift ID: <%=id%></h2>
        <h2>Name: <%=name%></h2>
        <h2>Points to redeem: <u><%=points%></u></h2><br>
                <% if (quantity > 0) {%>
        <h2>Quantity available: <%=quantity%>         
            <a href ="redeem?giftId=<%=id%>" onclick="return confirm('Proceed redeem gift ?')">Click to Redeem!</a></h2>
            <%
                }
            %>
            <% if (quantity.equals(0)) { %>
        <h2>Quantity available: <font color="red">OUT OF STOCK</font></h2>
            <%
                }
            %>
        <hr>
        <%
            }
        %>

        <%
            if (request.getAttribute("redeemStatus") != null) {
                String redeemStatus = (String) request.getAttribute("redeemStatus");
        %>
        <script>
            alert("<%=redeemStatus%>");
        </script>
        <%
            }
        %>

    </body>
</html>
