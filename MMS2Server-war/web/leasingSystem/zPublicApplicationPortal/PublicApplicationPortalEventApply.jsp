<%-- 
    Document   : indexTenantPortalEventBooking
    Created on : Sep 26, 2015, 11:24:25 AM
    Author     : PhiLong
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="container">
            <form action="SaveTenantApplication" method="GET">
                <div class="form-group">
                    <label for="tenantName">Tenant name :</label>
                    <input type="text" required="required" class="form-control"
                           id="tenantName" name="tenantName" placeholder="Enter tenant name">
                </div>

                <div class="form_group">
                    <label for="businessType">Business type</label>
                    <br/>
                    <select name="businessType"> <%
                        ArrayList<String> businessTypeList = (ArrayList<String>) request.getSession().getAttribute("businessTypeList");
                        int size = businessTypeList.size();
                        for (int i = 0; i < size; i++) {
                        %>
                        <option value="<%=businessTypeList.get(i)%>"><%=businessTypeList.get(i)%></option>
                        <%
                            }
                        %>
                    </select>
                </div>
                <div class="form-group">
                    <label for="tenantDescription">Tenant description :</label><br/>
                    <textarea resize="none" name="tenantDescription" rows="10" cols="180" placeholder="Enter tenant description"></textarea>
                </div>
                <div class="form-group">
                    <label for="tenantAddress">Tenant address :</label>
                    <input type="text" required="required" class="form-control"
                           id="tenantAddress" name="tenantAddress" placeholder="Enter tenant address">
                </div>
                <div class="form-group">
                    <label for="tenantTel">Tenant tel:</label>
                    <input type="text" required="required" class="form-control"
                           id="tenantTel" name="tenantTel" placeholder="Enter tenant tel">
                </div>
                <div class="form-group">
                    <label for="tenantEmail">Tenant email :</label>
                    <input type="email" required="required" class="form-control"
                           id="tenantEmail" name="tenantEmail" placeholder="Enter tenant email">
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form>
        </div>
    </body>
</html>
