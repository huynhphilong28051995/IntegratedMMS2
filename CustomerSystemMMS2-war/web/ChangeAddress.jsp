<%-- 
    Document   : ChangeAddress
    Created on : Sep 26, 2015, 3:49:03 AM
    Author     : AdminNUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Address</title>
    </head>
    <body>
        <h2><u><font color="brown">Change Address</font></u></h2>
        <form action = "SaveNewAddress">
            
        <p>Enter Old Address:</p>
        <%if(request.getAttribute("address")!=null){
            String address = (String)request.getAttribute("address");
        %>
        <input type="text" name="oldaddress" value="<%=address%>"  placeholder="Enter old address"/>
        <%}else{%>
        
        <input type="text" name="oldaddress" placeholder="Enter old address"/>
        <%
        }
        %>
        <br><br>
        <p>Enter New Address:</p>
        <input type="text" name="newaddress" placeholder="Enter new address"/>
        <br><br>
        <button type="submit">Submit</button>
        </form>
        <%
          String  changeAddStatus = (String) request.getAttribute("changeAddressStatus");
          if(changeAddStatus != null){    
        %>
        <h4 style="color: red;"><%=changeAddStatus%></h4>
        <%
          }
        %>
        <br>
        <a href="EditProfile">Back to Edit Page</a><br><br>
        <a href="GoBackIndex">Back to Home Page</a><br>
    </body>
</html>
