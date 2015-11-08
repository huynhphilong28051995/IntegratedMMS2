<%-- 
    Document   : FacilityManagerListOutsourcing
    Created on : Oct 13, 2015, 11:36:15 AM
    Author     : linjiao_Zoe
--%>
<%@page import="mms.facility.entity.OutsourcingEntity"%>
<%@page import="mms.facility.entity.FacilityEntity"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List All Outsourcing Request Page</title>
    </head>
    <body>
        <h1>List All Outsourcing Requests</h1>
        <form action="submitRespond" method="Get">
            <table>
                <tr align="left">
                    <th>Id</th>
                    <th>Sender Email</th> 
                    <th>Date</th>
                    <th>Detail</th>
                    <th>Respond Options</th>
                    <th>Next Option</th>
                </tr>
                <%
                    ArrayList outsourcing = (ArrayList) request.getAttribute("data");
                    for (Object o : outsourcing) {
                        OutsourcingEntity outsource = (OutsourcingEntity) o;
                        Long Id = outsource.getOutsourcingId();
                        String sender = outsource.getTenantEmail();
                        String date = outsource.getOutsourcingDate().toString().substring(0, 10);
                        String detail = outsource.getOutsourcingDetail();
                %>
                <tr>
                    <td><%=Id%></td>
                    <td><%=sender%></td>
                    <td><%=date%></td>            
                    <td><%=detail%></td>           
                    <td>
                        <input type="radio" name="status" value="Accepted<%=Id%>">Accept
                        <input type="radio" name="status" value="Rejected<%=Id%>">Reject
                    </td>               
                    <td>
                        <a href="readOutsourcingRequest?outsourcingId=<%=Id%>">View More</a>
                    </td>
                </tr>           
                <%
                    }
                %>      
            </table>
            <input type="submit" value="Submit Respond">
            <input type="reset" value="Reset"/>
        </form>
        <br/>    
        <a href="backToManagerIndexPage">Back to Manager Index Page</a></
    </body>
</html>