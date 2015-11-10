<%-- 
    Document   : OutsourceStatus
    Created on : Oct 29, 2015, 12:45:39 PM
    Author     : AdminNUS
--%>

<%@page import="mms.facility.entity.OutsourcingEntity"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>OutsourceStatus</title>
    </head>
    <body bgcolor="#FBEFF8">
        <h1>These are your Outsourcing requests....</h1>

        <table style="border: 1px solid black; text-align: left">
            <tr align="left">
                <th>Outsource Request Id</th>
                <th>Tenant ID</th>
                <th>Tenant Email</th>
                <th>Mall Name</th>
                <th>Unit Number</th>
                <th>Contractor</th>
                <th>No. of Staff</th>
                <th>Request Date</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Description</th>
                <th>Status</th>
                <th></th>
            </tr>
            <%
                ArrayList<OutsourcingEntity> outsourcerequest = (ArrayList<OutsourcingEntity>) request.getAttribute("outsourcerequest");
                for (int i = 0; i < outsourcerequest.size(); i++) {
                    Long outsourceid = outsourcerequest.get(i).getOutsourcingId();
                    Long tenantID = outsourcerequest.get(i).getTenantId();
                    String email = outsourcerequest.get(i).getTenantEmail();
                    String mall = outsourcerequest.get(i).getMallName();
                    String unitnum = outsourcerequest.get(i).getUnitNumber();
                    String contractor = outsourcerequest.get(i).getContractorName();
                    int numstaff = outsourcerequest.get(i).getNumStaff();
                    String description = outsourcerequest.get(i).getOutsourcingDetail();
                    String status = outsourcerequest.get(i).getOutsourcingStatus();           
                    String requestDate = outsourcerequest.get(i).getOutsourcingDate().toString().substring(0,10);
                    String start = outsourcerequest.get(i).getoServicingStartDate().toString().substring(0,10);
                    String end = outsourcerequest.get(i).getoServicingEndDate().toString().substring(0,10);
                    //list all content
            %>
            <tr>
                <td><%=outsourceid%></td>
                <td><%=tenantID%></td>
                <td><%=email%></td>
                <td><%=mall%></td>
                <td><%=unitnum%></td>
                <td><%=contractor%></td>
                <td><%=numstaff%></td>
                <td><%=requestDate%></td>
                <td><%=start%></td>
                <td><%=end%></td>
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
