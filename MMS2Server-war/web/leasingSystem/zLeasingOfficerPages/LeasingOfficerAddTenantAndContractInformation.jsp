<%-- 
    Document   : indexLeasingOfficerAddTenantAndContractInformation
    Created on : Sep 16, 2015, 10:32:37 PM
    Author     : PhiLong
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
          <title>Merlion Mall Asia | Admin Home</title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
        <meta http-equiv="Content-type" content="text/html; charset=utf-8">
        <meta content="" name="description"/>
        <meta content="" name="author"/>
        <!-- BEGIN GLOBAL MANDATORY STYLES -->
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css">
        <link href="../assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="../assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css">
        <link href="../assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="../assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css">
        <!-- END GLOBAL MANDATORY STYLES -->
        <link rel="stylesheet" type="text/css" href="../assets/global/plugins/bootstrap-toastr/toastr.min.css"/>
        <!-- BEGIN CUSTOM STYLES -->
        <link href="../assets/global/css/components-rounded.css" id="style_components" rel="stylesheet" type="text/css">
        <link href="../assets/global/css/plugins.css" rel="stylesheet" type="text/css">
        <link href="../assets/admin/interface/css/layout.css" rel="stylesheet" type="text/css">
        <link href="../assets/admin/interface/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color">
        <link href="../assets/admin/interface/css/custom.css" rel="stylesheet" type="text/css">
        <!-- END CUSTOM STYLES -->
        <!--PERSONAL STYLE-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/leasingSystem/leasingSystemAssets/css/main.css" type="text/css">
<!--        <link rel="stylesheet" href="${pageContext.request.contextPath}/leasingSystem/leasingSystemAssets/css/bootstrap.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/leasingSystem/leasingSystemAssets/jquery-ui-1.11.4.custom/jquery-ui.css">-->
        <script src="${pageContext.request.contextPath}/leasingSystem/leasingSystemAssets/jquery-ui-1.11.4.custom/external/jquery/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/leasingSystem/leasingSystemAssets/jquery-ui-1.11.4.custom/jquery-ui.js"></script>
        <script src="${pageContext.request.contextPath}/leasingSystem/leasingSystemAssets/javascript/mainScript.js"></script>   
        <!--PERSONAL STYLE-->
    </head>
    
        <div class="container">
            <form action="SaveTenantAndContractInformationManual" method="GET">
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
                <div class="form_group">
                    <label>Contract start date</label>
                    <input type="text" id="datepickerStart" name="contractStartDate"/>
                    <label>Contract end date</label>
                    <input type="text" id="datepickerEnd" name="contractEndDate"/>
                </div>
                <div class="form-group">
                    <label for="contractDeposit">Deposit :</label>
                    <input type="text" required="required" class="form-control"
                           id="contractDeposit" name="contractDeposit" placeholder="Enter contract deposit">
                </div>
                <div class="form-group">
                    <label for="contractRate">Rent per month :</label>
                    <input type="text" required="required" class="form-control"
                           id="contractRate" name="contractRate" placeholder="Enter contract rate">
                </div>

                <button type="submit" class="btn btn-default">Submit</button>
            </form>
            <script>
                $(function () {
                    $("#datepickerStart").datepicker({dateFormat: 'yy-mm-dd'});
                    $("#datepickerEnd").datepicker({dateFormat: 'yy-mm-dd'});
                });
            </script>
        </div>
    </body>
</html>
