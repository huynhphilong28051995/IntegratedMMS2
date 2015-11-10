<%-- 
    Document   : index
    Created on : 27 Oct, 2015, 10:20:33 PM
    Author     : khushnaz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>APS index Page</title>
       </head>
    <body>
        <h1>Welcome to MMA's Advertising & Promotion System</h1>
        <form action="APSControllerServlet" method="POST">
            <table>
                <tr>
                    <td>User Role:</td>
                    <td>
                        <select name="userRole" required="required">
                            <option value="">Please select a role</option>
                            <option value="APOfficer">AP Officer</option>
                            <option value="APManager">AP Manager</option>             
                        </select>
                    </td> 
                </tr>
                <%-- 
                <tr>
                    <td>Mall Name:</td>
                    <td>
                        <select name="mallName" required="required">
                            <option value="">Please select a mall</option>
                            <option value="MerlionNorth">Merlion North</option>
                            <option value="MerlionSouth">Merlion South</option>
                            <option value="MerlionEast">Merlion East</option>
                            <option value="MerlionWest">Merlion West</option>
                        </select>
                    </td>
                </tr> 
                --%>
                <tr>
                    <td><input type="submit" name = "action" value="Login"/></td>
                    <td><input type="reset" name ="action" value="Reset"/></td>
                </tr>
            </table>
        </form>

    </body>
</html>