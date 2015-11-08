<%-- 
    Document   : FacilityOfficerEditAsset
    Created on : Oct 11, 2015, 11:50:45 PM
    Author     : linjiao_Zoe
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="mms.facility.entity.AssetEntity"%>
<%@page import="java.util.StringTokenizer"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Asset Page</title>
    </head>
    <body>
        <h1>Edit Asset</h1>
        <form action="changeAsset" method="GET">
            <table>
                <%
                    AssetEntity asset = (AssetEntity) request.getAttribute("data");
                    Long Id = asset.getAssetId();
                    String name = asset.getAssetName();
                    int quantity = asset.getAssetQuantity();
                    String condition = asset.getAssetCondition();
                    double cost = asset.getAssetCost();
                    request.getSession().setAttribute("assetId", Id);
                %>
                <tr>
                    <td>Asset ID:</td>
                    <td><%=Id%></td>
                </tr>
                <tr>
                    <td>Name:</td>
                    <td><input type="text" name="assetName" value="<%=name%>"/></td>
                </tr>
                <tr>
                    <td>Quantity:</td>
                    <td><input type="number" name="assetQuantity" value="<%=quantity%>" min="1" max="200"/></td>
                </tr>
                <tr>
                    <td>Condition:</td>
                    <td><select name="assetCondition">
                            <option value="<%=condition%>" selected="selected">No change: <%=condition%></option>
                            <option value="Good">Good</option>
                            <option value="Under Maintenance">Under Maintenance</option>
                            <option value="Bad">Bad</option>
                        </select>
                    </td>
                </tr> 
                <tr>
                    <td>Cost:</td>
                    <td><input type="number" name="assetCost" step="any" value="<%=cost%>"/></td>
                </tr>
            </table>
            <input type="submit" value="Submit Changes"/>
            <input type="reset" value="Reset"/>
            <p>        
                <a href="backToOfficerIndexPage">Back to Officer Index Page</a>
                <a href="backToListFacilityPage">Back To List Facility</a>
            </p>
        </form>
    </body>
</html>
