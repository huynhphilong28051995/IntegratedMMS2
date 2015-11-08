<%-- 
    Document   : FacilityManagerViewOutsourcing
    Created on : Oct 18, 2015, 11:30:33 AM
    Author     : linjiao_Zoe
--%>

<%@page import="mms.facility.entity.OutsourcingEntity"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Outsourcing Request Page</title>
    </head>
    <body>
        <h1>View Individual Outsourcing Request</h1>
        <table>
            <%
                OutsourcingEntity outsource = (OutsourcingEntity) request.getAttribute("data");
                Long Id = outsource.getOutsourcingId();
                String mall = outsource.getMallName();
                Long tenantId = outsource.getTenantId();
                String tenantEmail = outsource.getTenantEmail();
                String unit = outsource.getUnitNumber();
                String contractor = outsource.getContractorName();
                int numStaff = outsource.getNumStaff();
                String date = outsource.getOutsourcingDate().toString().substring(0, 10);
                String detail = outsource.getOutsourcingDetail();
                String startDate = outsource.getoServicingStartDate().toString().substring(0, 10);
                String endDate = outsource.getoServicingEndDate().toString().substring(0, 10);
                String link = outsource.getDocFileLink();
                String status = outsource.getOutsourcingStatus();
            %>
            <tr>
                <td>Outsourcing Id:</td>
                <td><%=Id%></td>
            </tr>
            <tr>
                <td>Mall Name:</td>
                <td><%=mall%></td>
            </tr>
            <tr>
                <td>Tenant Id:</td>
                <td><%=tenantId%></td> 
            </tr>
            <tr>
                <td>Tenant Email:</td>
                <td><%=tenantEmail%></td> 
            </tr>
            <tr>
                <td>Shop Unit Number:</td>
                <td><%=unit%></td>
            </tr>
            <tr>
                <td>Contractor Name:</td>
                <td><%=contractor%></td> 
            </tr>
            <tr>
                <td>Number of Staffs needed:</td>
                <td><%=numStaff%></td> 
            </tr>
            <tr>
                <td>Request Date:</td>
                <td><%=date%></td>
            </tr>
            <tr>
                <td>Outsourcing Detail:</td>
                <td><%=detail%></td>
            </tr>
            <tr>
                <td>Outsourcing Start Date:</td>
                <td><%=startDate%></td>
            </tr>
            <tr>
                <td>Outsourcing End Date:</td>
                <td><%=endDate%></td>
            </tr>
            <tr>
                <td>Technical Document:</td>
                <td><a href="">Download</a></td>                
            </tr>  
            <tr>
                <td>Outsourcing Status:</td>
                <td><%=status%></td>
            </tr>
            <tr>
                <td><a href="listOutsourcingRequest">Back to Outsourcing List</a></td>
                <td><a href="backToManagerIndexPage">Back to Manager Index Page</a></td>
            </tr>              
        </table>
    </body>
</html>
