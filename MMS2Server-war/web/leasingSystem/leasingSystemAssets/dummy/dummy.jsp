<%-- 
    Document   : dummy
    Created on : Sep 9, 2015, 12:46:32 AM
    Author     : PhiLong
--%>

<%@page import="java.net.URI"%>
<%@page import="java.nio.file.FileSystems"%>
<%@page import="java.nio.file.Path"%>
<%@page import="java.io.File"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>DUMMY PAGE</h1>
        <form action="dummyUpload.jsp" method="post"
              enctype="multipart/form-data">
            <input type="file" name="file" size="50" />
            <br />
            <input type="submit" value="Upload File" />
        </form>
    </body>
</html>
