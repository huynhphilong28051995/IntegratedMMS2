<%-- 
    Document   : RentRenewalRequest
    Created on : Oct 29, 2015, 3:29:18 PM
    Author     : AdminNUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rent Renewal Request</title>
    </head>
    <body bgcolor="#FBEFF8">
        <h1>Rent Renewal Request Form</h1>
        <form action="SubmitRentRenewalRequest" method="POST">
            <table>
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
                    })
                </script>
                <br>
                (Please include requested renewal duration here too)
                <br><br>

                <!--AUTO SET DATE-->
                <!--CAPTCHA-->

                <br><br>
                <button type="submit">Submit</button>

                <%
                    if (request.getAttribute("renewalStatus") != null) {
                        String renewalStatus = (String) request.getAttribute("renewalStatus");
                %>
                <script>
                    alert("<%=renewalStatus%>");
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
