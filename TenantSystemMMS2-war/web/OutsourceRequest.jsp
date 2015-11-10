<%-- 
    Document   : OutsourceRequest
    Created on : Oct 27, 2015, 11:45:36 PM
    Author     : AdminNUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Outsourcing Request</title>
        <style>
            textarea{
                resize: none;
            }
        </style>
        <!-- BEGIN PERSONAL STYLES -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-ui-1.11.4.custom/jquery-ui.theme.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-ui-1.11.4.custom/jquery-ui.css">
        <script src="${pageContext.request.contextPath}/jquery-ui-1.11.4.custom/external/jquery/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/jquery-ui-1.11.4.custom/jquery-ui.js"></script>
        <!-- END PERSONAL STYLES -->
    </head>
    <body bgcolor="#FBEFF8">
        <h1>Outsourcing Request Online Form</h1>
        <form action="SubmitOutsourceRequest" method="POST">
            <table>
                Contractor Name:<br>
                <input type="text" name="contractorName" placeholder="Enter contractor name" required="required"/>
                <br><br>
                Number of Staff:<br>
                <input type="number" name="numStaff" placeholder="Enter number of staff" required="required"/>
                <br><br>
                Start Date:<br>
                <input type="text" id="startDatePicker" name="oServicingStartDate" required="required"/>
                <br><br>
                End Date:<br>
                <input type="text" id="endDatePicker" name="oServicingEndDate" required="required"/>
                <br><br>
                Description:<br>
                <textarea  id="textArea"  wrap="hard" maxlength="250" rows="5" cols="50" 
                           type="text" name="description" placeholder="Enter outsourcing description">
                </textarea>
                <script>
                    $(function () {
                        $('#textArea').keyup(function () {
                            var x = $('#test').val();

                            var newLines = x.match(/(\r\n|\n|\r)/g);
                            var addition = 0;
                            if (newLines != null) {
                                addition = newLines.length;
                            }

                            $('#length').html(x.length + addition);
                        })
                        
                        $("#startDatePicker").datepicker({dateFormat: 'yy-mm-dd'});
                        $("#endDatePicker").datepicker({dateFormat: 'yy-mm-dd'});
                    });
                </script>
                <br>
                (Please enter date and duration of work here too)
                <br><br>
                Document link:<br>
                <input type="text"  name="docFileLink" required="required"/><br>
                (Upload document online and copy link here)
                <!--AUTO SET DATE-->
                <!--CAPTCHA-->

                <br><br>
                <button type="submit">Submit</button>


                <%
                    if (request.getAttribute("outsourceStatus") != null) {
                        String outsourceStatus = (String) request.getAttribute("outsourceStatus");
                %>
                <script>
                    alert("<%=outsourceStatus%>");
                </script>
                <%
                    }
                %>

            </table>
            <br>
            <a href="GoBackIndex">Back to Home Page</a><br>
        </form>
    </body>
</html>
