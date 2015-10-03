<%-- 
    Document   : indexLeasingOfficerAddTenantChooseUnit
    Created on : Sep 15, 2015, 11:06:40 PM
    Author     : PhiLong
--%>
<%@page import="mms2.leasing.entity.LevelEntity"%>
<%@page import="mms2.leasing.entity.UnitEntity"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Vector"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/leasingSystem/css/main.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/leasingSystem/css/bootstrap.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/leasingSystem/jquery-ui-1.11.4.custom/jquery-ui.css">
        <script src="${pageContext.request.contextPath}/leasingSystem/jquery-ui-1.11.4.custom/external/jquery/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/leasingSystem/jquery-ui-1.11.4.custom/jquery-ui.js"></script>
        <script src="${pageContext.request.contextPath}/leasingSystem/javascript/mainScript.js"></script>   
        <title>ADD TENANT AND CONTRACT</title>
    </head>
    <body>
        <%
            String levelCode = (String) request.getSession().getAttribute("levelCode");
            LevelEntity level = (LevelEntity) request.getSession().getAttribute("levelInstance");
            String floorplanBackground = level.getFloorplanBackground();
            int numOfUnit = level.getUnits().size();
            boolean canProceed = true;
            ArrayList<String> unitListToOpenBidding =
                    (ArrayList<String>) request.getSession().getAttribute("unitListToAddTenant");
            if (unitListToOpenBidding.isEmpty()) {
                canProceed = false;
            }
            ArrayList<String> positionList = level.getUnitPositionList();
            String positionString = "";
            for (int i = 0; i < positionList.size(); i++) {
                positionString = positionString + "" + positionList.get(i);
            }
            //Seperate arraList into pushcart and non-pushcart
            List unitList = new ArrayList(level.getUnits());
            ArrayList<UnitEntity> listOfStoreUnits = new ArrayList();
            ArrayList<UnitEntity> listOfPushCartUnits = new ArrayList();
            ArrayList<UnitEntity> listOfKioskUnits = new ArrayList();
            ArrayList<UnitEntity> listOfEventUnits = new ArrayList();
            for (int i = 0; i < unitList.size(); i++) {
                UnitEntity unit = (UnitEntity) unitList.get(i);
                String unitLocationCode = unit.getLocationCode();
                boolean show = unit.isShow();
                if (show) {
                    if (unitLocationCode.contains("PC")) {
                        listOfPushCartUnits.add((UnitEntity) unitList.get(i));
                    }
                    if (unitLocationCode.contains("EV")) {
                        listOfEventUnits.add((UnitEntity) unitList.get(i));
                    }
                    if (unitLocationCode.contains("KS")) {
                        listOfKioskUnits.add((UnitEntity) unitList.get(i));
                    }
                    if (unitLocationCode.contains("ST")) {
                        listOfStoreUnits.add((UnitEntity) unitList.get(i));
                    }
                }
            }
        %>

        <image id="floorplanBackground" src="${pageContext.request.contextPath}/leasingSystem/floorplanBackground/<%=floorplanBackground%>.png" 
               stype="width:1000px; height: 1000px;"/>
        <div>
            <%
                for (int i = 0; i < listOfStoreUnits.size(); i++) {
                    String locationCode = ((UnitEntity) listOfStoreUnits.get(i)).getLocationCode();
            %>
            <div id="<%=locationCode%>" class="NonDragResize">
                <button disabled id = "<%=locationCode%>_button" style="height:100%; width:100%;"><%=locationCode%></button>
            </div>
            <%
                }
            %>
        </div>
        <div>
            <%
                for (int i = 1; i <= listOfPushCartUnits.size(); i++) {
                    String locationCode = ((UnitEntity) listOfPushCartUnits.get(i - 1)).getLocationCode();
            %>
            <div id="<%=locationCode%>" class="NonDragResize" style="height:30px; width: 70px;">
                <button disabled id = "<%=locationCode%>_button" style="height:100%; width:100%;"><%=locationCode%></button>
            </div>
            <%
                }
            %>
        </div>
        <div>
            <%
                for (int i = 1; i <= listOfKioskUnits.size(); i++) {
                    String locationCode = ((UnitEntity) listOfKioskUnits.get(i - 1)).getLocationCode();
            %>
            <div id="<%=locationCode%>" class="NonDragResize" style="height:30px; width: 70px;">
                <button disabled id = "<%=locationCode%>_button" style="height:100%; width:100%;"><%=locationCode%></button>
            </div>
            <%
                }
            %>
        </div>
        <div>
            <%
                for (int i = 1; i <= listOfEventUnits.size(); i++) {
                    String locationCode = ((UnitEntity) listOfEventUnits.get(i - 1)).getLocationCode();
            %>
            <div id="<%=locationCode%>" class="NonDragResize" style="height:100px; width: 100px;">
                <button disabled id = "<%=locationCode%>_button" style="height:100%; width:100%;"><%=locationCode%></button>
            </div>
            <%
                }
            %>
        </div>
        <script>
            var positions = <%=positionString%>;
            $.each(positions, function (id, pos) {
                $("#" + id).css(pos);
            });
        </script>
        <!--SELECT UNIT TO ADD TENANT-->
        <div id="SelectUnitToAddTenantForm">
            <form action="AddUnitToListToAddTenant">
                <div class="form-group">
                    <label class="test">From</label>
                    <select required="required" name="firstLocationCode" class="test">
                        <%
                            for (int i = 0; i < listOfStoreUnits.size(); i++) {
                                String locationCode = listOfStoreUnits.get(i).getLocationCode();

                        %>
                        <option value="<%=locationCode%>"><%=locationCode%></option>
                        <%
                            }
                        %>
                    </select>
                </div>
                <div class="form-group">
                    <label>To</label>
                    <select required="required" name="lastLocationCode" >
                        <%
                            for (int i = 0; i < listOfStoreUnits.size(); i++) {
                                String locationCode = listOfStoreUnits.get(i).getLocationCode();

                        %>
                        <option value="<%=locationCode%>"> <%=locationCode%></option>
                        <%
                            }
                        %>
                    </select>
                </div>
                <br/><br/>
                <br/><br/><br/><button type="submit">Add unit(s)</button>
            </form>
        </div>
        <form action="AddTenantAndContractInformation">
            <button id="proceed"  type="submit">Proceed</button>
        </form>
        <script>
            var canProceed = <%=canProceed%>;
            if (canProceed === false) {
                document.getElementById("proceed").disabled= true;
            }
        </script>
        <!--SELECT UNIT TO ADD TENANT-->
        <!--COLOR BUTTONS-->
        <%
            Vector unitColorVector = (Vector) request.getSession().getAttribute("unitColorVector");
            if (!unitColorVector.isEmpty()) {
                ArrayList<String> arrayLocationCode = (ArrayList) unitColorVector.get(0);
                ArrayList<String> arrayColor = (ArrayList) unitColorVector.get(1);
                for (int i = 0; i < arrayColor.size(); i++) {
                    String locationCode = arrayLocationCode.get(i);
                    String color = arrayColor.get(i);

        %> 
        <script>
            document.getElementById("<%=locationCode%>_button").style.background = "<%=color%>";
        </script>
        <%
                }
            }
        %>
        <!--COLOR BUTTONS-->
        <!--CHANGE FLOOR-->
        <div id="changeFloor">
            <form action="ChangeFloorplanLevelAddTenant" method="GET">
                <div class="form_group">
                    <label for="levelCode">Change floorplan view</label>
                    <select name="levelCode">
                        <%
                            for (int i = 1; i <= (Integer) request.getSession().getAttribute("numOfLevel"); i++) {
                        %>
                        <option value="LV<%=i%>">LV<%=i%></option>
                        <%
                            }
                        %>
                    </select>
                    <button type="submit" style="width:50px;height:20px;">Go</button>
                </div>
            </form>
        </div>
        <!--CHANGE FLOOR-->

        <!--BACK TO MAIN-->
        <form action="LeasingOfficerMain"><button type="submit">BACK</button></form>
        <!--BACK TO MAIN-->

        <%
            if (request.getSession().getAttribute("errorMessage") != null) {
                String errorMessage = (String) request.getSession().getAttribute("errorMessage");
                ArrayList<String> unitListToAddTenant = (ArrayList<String>) request.getSession().getAttribute("unitListToAddTenant");
        %>
        <h1><%=errorMessage%></h1>
        <%
            }
        %>
        <%
            if (request.getSession().getAttribute("unitListToAddTenant") != null) {
                ArrayList<String> unitListToAddTenant = (ArrayList<String>) request.getSession().getAttribute("unitListToAddTenant");
                for (int i = 0; i < unitListToAddTenant.size(); i++) {
                    String locationCode = unitListToAddTenant.get(i);
        %>
        <h1><%=locationCode%></h1>
        <%
                }
            }
        %>
    </body>
</html>
