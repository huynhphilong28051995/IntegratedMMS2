<%-- 
    Document   : MainPage
    Created on : Oct 13, 2015, 9:01:52 PM
    Author     : linjiao_Zoe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FMS Page</title>
    </head>
    <body>
        <h1>Facilities Management System</h1>
        <form action="FMSControlServlet/directLogin" method="GET">
            <table>
                <tr>
                    <td>User Role:</td>
                    <td>
                        <select name="userRole" required="required">
                            <option value="">Please select a role</option>
                            <option value="Facility Officer">Facility Officer</option>
                            <option value="Facility Manager">Facility Manager</option>             
                        </select>
                    </td> 
                </tr>
                <tr>
                    <td>Mall Name:</td>
                    <td>
                        <select name="mallName" required="required">
                            <option value="">Please select a mall</option>
                            <option value="MerlionNorth">MerlionNorth</option>
                            <option value="MerlionSouth">MerlionSouth</option>
                            <option value="MerlionEast">MerlionEast</option>
                            <option value="MerlionWest">MerlionWest</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="Submit"/></td>
                    <td><input type="reset" value="Reset"/></td>
                </tr>
            </table>
        </form>
    </body>
</html>
