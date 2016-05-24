<%-- 
    Document   : PhotoProfile
    Created on : 17-mei-2016, 11:58:19
    Author     : Nick
--%>
<%@page import="javax.swing.JTable"%>
<%@page import="BusinessLayer.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>E-Copy</title>
        <link rel="stylesheet" href="bootstrap.css"/>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div class="col-md-2">
            <a href="PhotoProfile.jsp"> <!-- change to right path -->
            <img id="logo" src="images/logo.jpg"/>
        </div>
        <div class="col-md-2">
            <a href="PhotoProfile.jsp"> <!-- change to right path -->
            <img id="logo" src="images/logo.jpg"/>
        </div>
        <div class="col-md-2">
            <a href="PhotoProfile.jsp"> <!-- change to right path -->
            <img id="logo" src="images/logo.jpg"/>
        </div>
        <div class="col-md-1">
                        <input type="submit" value="Nick is Kewl">
                    </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
