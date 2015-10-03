<%-- 
    Document   : sendSms
    Created on : 25 Sep, 2015, 2:12:27 AM
    Author     : GOH ENG CHI
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="post" action="postSms">
          <div class="form-group">
          <h3 class="font-size-20">Send Sms</h3>
            <label>Number</label>
            <input type="text" name="SMSNumber">
          </div>
          <button type="submit" class="btn btn-primary btn-block">Send SMS</button>
        </form>
    </body>
</html>
