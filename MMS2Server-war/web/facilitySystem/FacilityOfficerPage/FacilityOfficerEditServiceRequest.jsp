<%-- 
    Document   : FacilityOfficerEditServiceRequest
    Created on : Oct 8, 2015, 5:14:53 AM
    Author     : linjiao_Zoe
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="mms.facility.entity.ServiceEntity"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="${pageContext.request.contextPath}/javascript/mainScript.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-ui-1.11.4.custom/jquery-ui.theme.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-ui-1.11.4.custom/jquery-ui.css">
        <script src="${pageContext.request.contextPath}/jquery-ui-1.11.4.custom/external/jquery/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/jquery-ui-1.11.4.custom/jquery-ui.js"></script>
        <title>Edit Service Request Page</title>
    </head>
    <body>
        <h1>Edit Service Request</h1>
        <form action="changeServiceRequest" method="GET">
            <table>
                <%
                    ServiceEntity service = (ServiceEntity) request.getAttribute("data");
                    Long Id = service.getServiceId();
                    String type = service.getServiceType();
                    String status = service.getServiceRequestStatus();
                    String date = service.getServiceRequestDate().toString().substring(0,10);
                    String detail = service.getServiceRequestDetail();
                    int fee = service.getServiceFee();
                    String servicingDate = service.getServicingDate().toString().substring(0,10); 
                    request.getSession().setAttribute("serviceId", Id);
                %>
                <tr>
                    <td>Service Request ID:</td>
                    <td><%=Id%></td>
                </tr>
                <tr>
                    <td>New Service Type:</td>
                    <td><select name="serviceType">
                            <option value="<%=type%>" selected="selected">No change: <%=type%></option>
                            <option value="Repair">Repair</option>
                            <option value="Maintenance">Maintenance</option>
                            <option value="Renovation">Renovation</option>
                        </select>
                    </td>
                </tr> 
                <tr>
                    <td>New Service Request Status:</td>
                    <td><select name="serviceRequestStatus">
                            <option value="<%=status%>" selected="selected">No change: <%=status%></option>
                            <option value="Work Done">Work Done</option> 
                            <option value="Pending Service">Pending Request</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>New Service Request Date:</td>
                    <td><input type="text" id="datepickerServiceRequestDate" name="serviceRequestDate" value="<%=date%>"/>
                        <script>
                            $(function () {
                                $("#datepickerServiceRequestDate").datepicker({dateFormat: 'yy-mm-dd'});
                            });
                        </script>
                    </td>
                </tr>
                <tr>
                    <td>New Service Request Detail:</td>
                    <td><textarea rows="10" cols="20" name="serviceRequestDetail" value="<%=detail%>">Please enter the detail of service request here.
                        </textarea>
                    </td>
                </tr>
                <tr>
                    <td>New Service Fee: SG$</td>
                    <td><input type="number" name="serviceFee" value="<%=fee%>"/></td>
                </tr>
                <tr>
                    <td>New Servicing Date:</td>
                    <td><input type="text" id="datepickerServicingDate" name="servicingDate" value="<%=servicingDate%>"/>
                        <script>
                            $(function () {
                                $("#datepickerServicingDate").datepicker({dateFormat: 'yy-mm-dd'});
                            });
                        </script>
                    </td>
                </tr>  
            </table>
            <input type="submit" value="Submit Changes"/>
            <input type="reset" value="Reset"/>
            <p>
                <a href="backToOfficerIndexPage">Back to Officer Index Page</a>
                <a href="listServiceRequest">List Service Requests</a>
            </p>
        </form>
    </body>
</html>