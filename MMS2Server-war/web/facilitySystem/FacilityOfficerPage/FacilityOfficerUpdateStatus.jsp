<%-- 
    Document   : FacilityOfficerUpdateStatus
    Created on : Oct 20, 2015, 1:42:17 PM
    Author     : linjiao_Zoe
--%>

<%@page import="Entity.FeedbackEntity"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Status Page</title>
    </head>
    <body>
        <h1>Update Status</h1>
        <form action="changeStatus" method="GET">
            <table>
                <%
                    FeedbackEntity feedback = (FeedbackEntity) request.getAttribute("data");
                    Long Id = feedback.getFeedbackId();
                    String mallName = feedback.getMallName();                   
                    String subject = feedback.getSubject();                    
                    String status = feedback.getStatus();
                    request.getSession().setAttribute("feedbackId", Id);
                %>
                <tr>
                    <td>Feedback ID: <%=Id%></td>
                </tr>
                <tr>
                    <td>Mall Name: <%=mallName%></td>
                </tr>
                <tr>
                    <td>Subject: <%=subject%></td>
                </tr>
                <tr>
                    <td>New Status:
                        <select name="status">
                            <option value="<%=status%>" selected="selected">No change: <%=status%></option>
                            <option value="Read">Read</option> 
                            <option value="Solved">Solved</option>
                        </select>
                    </td>
                </tr>                
            </table>
            <input type="submit" value="Submit Changes"/>
            <input type="reset" value="Reset"/>
            <p>
                <a href="backToOfficerIndexPage">Back to Officer Index Page</a>
                <a href="listFeedback">List Feedbacks</a>
            </p>
        </form>
    </body>
</html>
