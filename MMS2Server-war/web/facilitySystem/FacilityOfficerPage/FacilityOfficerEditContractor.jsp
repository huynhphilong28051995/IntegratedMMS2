<%-- 
    Document   : FacilityOfficerEditContractor
    Created on : Oct 8, 2015, 5:14:53 AM
    Author     : linjiao_Zoe
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Entity.ContractorEntity"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Contractor Page</title>
    </head>
    <body>
        <h1>Edit Contractor</h1>
        <form action="changeContractor" method="GET">
            <table>
                <%
                    ContractorEntity contractor = (ContractorEntity) request.getAttribute("data");
                    Long Id = contractor.getContractorId();
                    String name = contractor.getContractorName();
                    String company = contractor.getCompanyName();
                    String service = contractor.getServiceType();
                    String contact = contractor.getContractorTel();
                    String email = contractor.getContractorEmail();
                    request.getSession().setAttribute("contractorId", Id);
                %>
                <tr>
                    <td>Contractor ID:</td>
                    <td><%=Id%></td>
                </tr>
                <tr>
                    <td>New Contractor Name:</td>
                    <td><input type="text" name="contractorName" value="<%=name%>"/></td>
                </tr>
                <tr>
                    <td>New Company Name:</td>
                    <td><input type="text" name="companyName" value="<%=company%>"/></td>
                </tr>
                <tr>
                    <td>New Service Offered:</td>
                    <td><select name="serviceType">
                            <option value="<%=service%>" selected="selected">No change: <%=service%></option>
                            <option value="Repair">Repair</option>
                            <option value="Maintenance">Maintenance</option>
                            <option value="Renovation">Renovation</option>
                        </select>
                </tr> 
                <tr>
                    <td>New Contact Number:</td>
                    <td><input type="text" name="contractorTel" value="<%=contact%>"/></td>
                </tr>
                <tr>
                    <td>New Email Address:</td>
                    <td><input type="email" name="contractorEmail" value="<%=email%>"/></td>
                </tr>
            </table>
            <input type="submit" value="Submit Changes"/>
            <input type="reset" value="Reset"/>
            <p>
                <a href="backToOfficerIndexPage">Back to Officer Index Page</a>
                <a href="listContractor">List Contractors</a>
            </p>
        </form>
    </body>
</html>