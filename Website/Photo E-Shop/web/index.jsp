<%-- 
    Document   : index
    Created on : 15-mrt-2016, 15:01:03
    Author     : Milton
--%>

<%@page import="Package.Database"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>E-Copy</title>
        <link rel="stylesheet" href="bootstrap.css"/>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div id="content" class="row col-md-12">
            <h1><fmt:message key="index.label.welcome" /></h1>
        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
