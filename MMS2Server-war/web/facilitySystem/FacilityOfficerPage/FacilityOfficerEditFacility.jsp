<%-- 
    Document   : FacilityOfficerEditFacility
    Created on : Sep 25, 2015, 5:02:15 PM
    Author     : linjiao_Zoe
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="mms.facility.entity.FacilityEntity"%>
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
        <title>Edit Facility Page</title>
    </head>
    <body>
        <h1>Edit Facility</h1>
        <form action="changeFacility" method="GET">
            <table>
                <%
                    FacilityEntity facility = (FacilityEntity) request.getAttribute("data");
                    Long Id = facility.getFacilityId();
                    String name = facility.getFacilityName();
                    String category = facility.getFacilityCategory();
                    int quantity = facility.getFacilityQuantity();
                    String condition = facility.getFacilityCondition();
                    String location = facility.getFacilityLocation();
                    String purchaseDate = facility.getFacilityPurchaseDate().toString();
                    String expiryDate = facility.getFacilityExpiryDate().toString();
                    double cost = facility.getFacilityCost();
                    request.getSession().setAttribute("facilityId", Id);
                %>
                <tr>
                    <td>Facility ID:</td>
                    <td><%=Id%></td>
                </tr>
                <tr>
                    <td>Name:</td>
                    <td><input type="text" name="facilityName" value="<%=name%>"/></td>
                </tr>
                <tr>
                    <td>Category:</td>
                    <td>
                        <select name="facilityCategory">
                            <option value="<%=category%>" selected="selected">No change: <%=category%></option>
                            <option value="Toilet">Toilet</option>
                            <option value="Fire Alarm">Fire Alarm</option>
                            <option value="Central Air Condition">Central Air Condition</option>                          
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Quantity:</td>
                    <td><input type="number" name="facilityQuantity" value="<%=quantity%>" min="1" max="200"/></td>
                </tr>
                <tr>
                    <td>Condition:</td>
                    <td><select name="facilityCondition">
                            <option value="<%=condition%>" selected="selected">No change: <%=condition%></option>
                            <option value="Good">Good</option>
                            <option value="Bad">Bad</option>
                            <option value="Pending Service">Pending Service</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Location:</td>
                    <td><input type="text" name="facilityLocation" value="<%=location%>"/></td>
                </tr>
                <tr>
                    <td>Purchase Date:</td>
                    <td><input type="text" id="datepickerPurchaseDate" name="facilityPurchaseDate" value=<%=purchaseDate%>/>
                        <script>
                            $(function () {
                                $("#datepickerPurchaseDate").datepicker({dateFormat: 'yy-mm-dd'});
                            });
                        </script>
                    </td>
                </tr>
                <tr>
                    <td>Expiry Date:</td>
                    <td><input type="text" id="datepickerExpiryDate" name="facilityExpiryDate" value=<%=expiryDate%>/>
                        <script>
                            $(function () {
                                $("#datepickerExpiryDate").datepicker({dateFormat: 'yy-mm-dd'});
                            });
                        </script>
                    </td>
                </tr>
                <tr>
                    <td>Cost:</td>
                    <td><input type="number" step="any" name="facilityCost" value="<%=cost%>"/></td>
                </tr>
            </table>
            <input type="submit" value="Submit Changes"/>
            <input type="reset" value="Reset"/>
            <p>
                <a href="backToOfficerIndexPage">Back to Officer Index Page</a>
                <a href="listFacility">List Facilities</a>
            </p>
        </form>
    </body>
</html>
